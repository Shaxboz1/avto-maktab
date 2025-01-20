package org.example.server;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionFXMLController {
    @FXML
    private Label savoLabel;
    @FXML
    private Button nextBtn;
    @FXML
    private Button previousBtn;
    @FXML
    private Button BoshMenu;
    @FXML
    private Button f1Btn;
    @FXML
    private Button f2Btn;
    @FXML
    private Button f3Btn;
    @FXML
    private Label f1Label;
    @FXML
    private Label f2Label;
    @FXML
    private Label f3Label;
    @FXML
    private Pane buttonContainer;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label timerLabel;
    private int currentQuestionIndex = 0;
    private int wrongAnswersCount = 0;
    private int trueAnswerCount = 0;
    String csvFile = "savollar.csv";
    String csvSplitBy = ",";
    private final List<Questions> questionsList = new ArrayList<>();

    private int timeRemainingInSeconds = 10;

    @FXML
    private void initialize() {
        populateQuestions();
        showQuestion();
        createDynamicButtons();
        startTimer();

        buttonContainer.setFocusTraversable(true);
        buttonContainer.requestFocus();


        rootPane.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.F1)) {
                f1Btn.fire();
            } else if (event.getCode().equals(javafx.scene.input.KeyCode.F2)) {
                f2Btn.fire();
            } else if (event.getCode().equals(javafx.scene.input.KeyCode.F3)) {
                f3Btn.fire();
            }
        });

        nextBtn.setOnAction(actionEvent -> {
            if (currentQuestionIndex < questionsList.size() - 1) {
                currentQuestionIndex++;
                showQuestion();
                enableAnswerButtons();
            }
        });

        previousBtn.setOnAction(actionEvent -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                showQuestion();
                enableAnswerButtons();
            }
        });
        f1Btn.setOnAction(e -> handleAnswerSelection("A"));
        f2Btn.setOnAction(event -> handleAnswerSelection("B"));
        f3Btn.setOnAction(event -> handleAnswerSelection("C"));

        BoshMenu.setOnAction(actionEvent -> openScene("/org/example/server/Welcome.fxml"));
    }

    private void startTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (timeRemainingInSeconds > 0) {
                timeRemainingInSeconds--;
                updateTimerLabel();
            } else {
                timerLabel.setText("Time's up!");
                openResultScene(trueAnswerCount);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimerLabel() {
        int minutes = timeRemainingInSeconds / 60;
        int seconds = timeRemainingInSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void enableAnswerButtons() {
        f1Btn.setDisable(false);
        f2Btn.setDisable(false);
        f3Btn.setDisable(false);
    }

    private void disableAnswerButtons() {
        f1Btn.setDisable(true);
        f2Btn.setDisable(true);
        f3Btn.setDisable(true);
    }

    private void createDynamicButtons() {
        buttonContainer.getChildren().clear();

        double containerWidth = buttonContainer.getPrefWidth();
        double buttonWidth = 50.0;
        double buttonHeight = 50.0;
        double spacing = 3;

        int totalButtons = questionsList.size();
        double totalWidth = totalButtons * buttonWidth + (totalButtons - 1) * spacing;

        double startX = (containerWidth - totalWidth) / 2;

        for (int i = 0; i < totalButtons; i++) {
            Button questionButton = new Button(String.valueOf(i + 1));
            questionButton.setPrefWidth(buttonWidth);
            questionButton.setPrefHeight(buttonHeight);
            questionButton.setLayoutX(startX + i * (buttonWidth + spacing));
            questionButton.setLayoutY(0);
            questionButton.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 5; -fx-background-radius: 5");
            int questionIndex = i;
            questionButton.setOnAction(event -> {
                currentQuestionIndex = questionIndex;
                showQuestion();
            });

            buttonContainer.getChildren().add(questionButton);
        }
        updateQuestionButtonColors();
    }

    private void updateQuestionButtonColors() {
        for (int i = 0; i < questionsList.size(); i++) {
            Button questionButton = (Button) buttonContainer.getChildren().get(i);
            Questions question = questionsList.get(i);
            if (question.isAnswered()) {
                if (question.getCorrect().equals(question.getSelectedAnswer())) {
                    questionButton.setStyle("-fx-background-color: green;-fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 5;-fx-background-radius: 5");
                } else {
                    questionButton.setStyle("-fx-background-color: red;-fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 5;-fx-background-radius: 5");
                }
            }
        }
    }

    private void populateQuestions() {
        String filePath = "src/main/resources/savollar.csv";

        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                if (data.length != 5) {
                    System.err.println("Xatolik: noto‘g‘ri formatdagi qator: " + line);
                    continue;
                }

                String questionText = data[0];
                String optionA = data[1];
                String optionB = data[2];
                String optionC = data[3];
                String correctAnswer = data[4];


                Options options = new Options(optionA, optionB, optionC);
                Questions question = new Questions(questionText, options, correctAnswer);
                questionsList.add(question);
                if (questionsList.isEmpty()) {
                    System.err.println("Savollar ro‘yxati bo‘sh!");
                    return;
                }

            }

            Collections.shuffle(questionsList);
        } catch (IOException e) {
            System.err.println("CSV faylni o‘qishda xatolik: " + e.getMessage());
        }
    }


    private void showQuestion() {

        Questions currentQuestion = questionsList.get(currentQuestionIndex);
        savoLabel.setText(currentQuestion.getQuestion());
        f1Label.setText(currentQuestion.getOptions().getA());
        f2Label.setText(currentQuestion.getOptions().getB());
        f3Label.setText(currentQuestion.getOptions().getC());

        f1Btn.setStyle("-fx-background-color: #00BFFF; -fx-border-color: white;-fx-background-radius: 10px 0 0 10px; -fx-border-radius: 10px 0 0 10px;");
        f2Btn.setStyle("-fx-background-color: #00BFFF; -fx-border-color: white;-fx-background-radius: 10px 0 0 10px; -fx-border-radius: 10px 0 0 10px;");
        f3Btn.setStyle("-fx-background-color: #00BFFF; -fx-border-color: white;-fx-background-radius: 10px 0 0 10px; -fx-border-radius: 10px 0 0 10px;");

        if (currentQuestion.getSelectedAnswer() != null) {
            if (currentQuestion.getSelectedAnswer().equals("A")) {
                f1Btn.setStyle(currentQuestion.getCorrect().equals("A") ? "-fx-background-color: green;-fx-background-radius: 10px 0 0 10px; -fx-border-color: white; -fx-border-radius: 10px 0 0 10px;" : "-fx-background-color: red;-fx-background-radius: 10px 0 0 10px; -fx-border-color: white; -fx-border-radius: 10px 0 0 10px;");
            } else if (currentQuestion.getSelectedAnswer().equals("B")) {
                f2Btn.setStyle(currentQuestion.getCorrect().equals("B") ? "-fx-background-color: green;-fx-background-radius: 10px 0 0 10px; -fx-border-color: white; -fx-border-radius: 10px 0 0 10px;" : "-fx-background-color: red;-fx-background-radius: 10px 0 0 10px; -fx-border-color: white; -fx-border-radius: 10px 0 0 10px;");
            } else if (currentQuestion.getSelectedAnswer().equals("C")) {
                f3Btn.setStyle(currentQuestion.getCorrect().equals("C") ? "-fx-background-color: green;-fx-background-radius: 10px 0 0 10px; -fx-border-color: white; -fx-border-radius: 10px 0 0 10px;" : "-fx-background-color: red;-fx-background-radius: 10px 0 0 10px; -fx-border-color: white; -fx-border-radius: 10px 0 0 10px;");
            }
        }

        if (!currentQuestion.isAnswered()) {
            enableAnswerButtons();
        } else {
            disableAnswerButtons();
        }
    }

    private void handleAnswerSelection(String selectedOption) {
        Questions currentQuestion = questionsList.get(currentQuestionIndex);

        if (currentQuestion.isAnswered()) {
            return;
        }

        boolean isCorrect = currentQuestion.getCorrect().equals(selectedOption);

        Button selectedButton = null;
        if (selectedOption.equals("A")) {
            selectedButton = f1Btn;
        } else if (selectedOption.equals("B")) {
            selectedButton = f2Btn;
        } else if (selectedOption.equals("C")) {
            selectedButton = f3Btn;
        }

        if (isCorrect) {
            assert selectedButton != null;
            selectedButton.setStyle("-fx-background-color: green; -fx-border-color: white;-fx-background-radius: 10px 0 0 10px; -fx-border-radius: 10px 0 0 10px;");
            trueAnswerCount++;
        } else {
            assert selectedButton != null;
            selectedButton.setStyle("-fx-background-color: red; -fx-border-color: white;-fx-background-radius: 10px 0 0 10px; -fx-border-radius: 10px 0 0 10px;");
            wrongAnswersCount++;
        }

        currentQuestion.setSelectedAnswer(selectedOption);
        currentQuestion.setAnswered(true);

        disableAnswerButtons();

        updateQuestionButtonColors();

        if (wrongAnswersCount >= 3) {
            openResultScene(trueAnswerCount);
            return;
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            if (currentQuestionIndex < questionsList.size() - 1) {
                currentQuestionIndex++;
                showQuestion();
                enableAnswerButtons();
            }
        });
        pause.play();
    }

    private void openResultScene(int correctAnswersCount) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/server/Result.fxml"));
            AnchorPane root = loader.load();

            ResultController resultController = loader.getController();
            resultController.setCorrectAnswersCount(correctAnswersCount);

            resultController.button.setText("Correct Answers: " + correctAnswersCount);

            Scene scene = new Scene(root);
            Stage stage = (Stage) BoshMenu.getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void openScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) BoshMenu.getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                event.consume();
                System.out.println("Chiqish bloklandi! Dasturdan chiqib bo'lmaydi.");
            });
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}