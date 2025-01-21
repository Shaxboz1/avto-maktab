package org.example.server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class OraliqTestController {
//fftugug
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

    private int currentQuestionIndex = 0;
    private final List<Questions> questionsList = new ArrayList<>();

    @FXML
    private void initialize() {
        populateQuestions();
        createDynamicButtons();
        showQuestion();


        nextBtn.setOnAction(actionEvent -> {
            if (currentQuestionIndex < questionsList.size() - 1) {
                currentQuestionIndex++;
                showQuestion();
            }
        });

        previousBtn.setOnAction(actionEvent -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                showQuestion();
            }
        });

        BoshMenu.setOnAction(actionEvent -> openScene("/org/example/server/Welcome.fxml"));
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
            questionButton.setLayoutX(startX + i * (buttonWidth+spacing));
            questionButton.setLayoutY(0);
            questionButton.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 5; -fx-background-radius: 5");
            int questionIndex = i;
            questionButton.setOnAction(event -> {
                currentQuestionIndex = questionIndex;
                showQuestion();
            });

            buttonContainer.getChildren().add(questionButton);
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
        resetButtonStyles();
        String correctAnswer = currentQuestion.getCorrect();
        if ("A".equals(correctAnswer)) {
            f1Btn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else if ("B".equals(correctAnswer)) {
            f2Btn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else if ("C".equals(correctAnswer)) {
            f3Btn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        }
    }
    private void resetButtonStyles() {
        f1Btn.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 5; -fx-background-radius: 5");
        f2Btn.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 5; -fx-background-radius: 5");
        f3Btn.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 5; -fx-background-radius: 5");
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
