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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class OraliqTestController {

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
        Options option1 = new Options("Yengil mashina", "Ikkalasi ham yo'l", "Yuk mashinasi");
        questionsList.add(new Questions("Hozirda qaysi avtomobil yo'lni kesib o'tish uchun yo'l berish kerak?", option1, "A"));
        Options option2 = new Options("Yengil mashina", "Ikkalasi ham yo'l", "Yuk mashinasi");
        questionsList.add(new Questions("Hozirda qaysi ?", option2, "A"));
        Options option3 = new Options("Yengil mashina", "Ikkalasi ham yo'l", "Yuk mashinasi");
        questionsList.add(new Questions("Hozirda qaysi avtomobil yo'lni kesib o'tish uchun yo'l berish kerak?", option3, "A"));
        Options option4 = new Options("Yuk mashinasi", "Ikkalasi ham yo'l", "Yengil mashina");
        questionsList.add(new Questions("Agar siz to'xtash belgisini ko'rsangiz, nima qilishingiz kerak??", option4, "B"));
        Options option5 = new Options("Yuk mashinasi", "Yengil mashina", "Ikkalasi ham yo'l");
        questionsList.add(new Questions("Quyidagi holatda kimga yo'l berish kerak:", option5, "B"));
        Options option6 = new Options("Yuk mashinasi", "Yengil mashina", "Ikkalasi ham yo'l");
        questionsList.add(new Questions(" Agar siz qizil svetofor signalini ko'rsangiz, nima qilish kerak?", option6, "A"));
        Options option7 = new Options("Yuk mashinasi", "Yengil mashina", "Ikkalasi ham yo'l");
        questionsList.add(new Questions("Yo'lni kesib o'tish belgisiga yaqin bo'lgan avtobusga qanday harakat qilish kerak??", option7, "C"));
        Options option8 = new Options("Yuk mashinasi", "Yengil mashina", "Ikkalasi ham yo'l");
        questionsList.add(new Questions("Foydalanilmayotgan svetofor ustuniga qanday harakat qilish kerak??", option8, "C"));
        Options option9 = new Options("Yuk mashinasi", "Yengil mashina", "Ikkalasi ham yo'l");
        questionsList.add(new Questions("Avtomobillar keskin tormoz berib, bir-biriga yaqinlashganida nima qilish kerak?", option9, "C"));
        Options option10 = new Options("Yuk mashinasi", "Yengil mashina", "Ikkalasi ham yo'l");
        questionsList.add(new Questions("Piyodalar uchun o'rnatilgan yo'l belgilari nima uchun kerak?", option10, "B"));
        Options option11 = new Options("To'xtash", "Harakatni davom ettirish", "E'tiborsizlik");
        questionsList.add(new Questions("Qizil svetofor yonib turganda nima qilish kerak?", option11, "A"));
        Options option12 = new Options("Signal bermaslik", "Signal berib to'xtash", "Shoshilish");
        questionsList.add(new Questions("Favqulodda to'xtash belgisi ko'rsangiz, nima qilish kerak?", option12, "B"));
        Options option13 = new Options("Yengil mashina", "Yuk mashinasi", "Ikkalasi ham yo'l");
        questionsList.add(new Questions("Piyodalarga nisbatan qaysi avtomobil yo'l berishi kerak?", option13, "C"));
        Options option14 = new Options("Qoidalarga rioya qilish", "Harakatni davom ettirish", "Ogohlantirish signalini yoqish");
        questionsList.add(new Questions("Tumanli ob-havoda qanday harakat qilish kerak?", option14, "A"));
        Options option15 = new Options("Ogohlantirish signalini yoqish", "Tormoz bermaslik", "Yuqori tezlikda harakat qilish");
        questionsList.add(new Questions("Yomon ob-havoda qanday harakat qilasiz?", option15, "A"));
        Options option16 = new Options("To'xtab, yo'l berish", "Harakatni davom ettirish", "Ogohlantirish signalini yoqish");
        questionsList.add(new Questions("Tirbandlikda to'xtash belgisiga yaqinlashsangiz, nima qilishingiz kerak?", option16, "A"));
        Options option17 = new Options("Tormozlash", "Harakatni davom ettirish", "Ogohlantirish signalini yoqish");
        questionsList.add(new Questions("Avtomobil keskin tormoz berganida qanday harakat qilish kerak?", option17, "A"));
        Options option18 = new Options("Qoidalarga rioya qilish", "Signal bermaslik", "Shoshilish");
        questionsList.add(new Questions("Keskin burilishda qanday harakat qilish kerak?", option18, "A"));
        Options option19 = new Options("Signal berib to'xtash", "Signal bermaslik", "Shoshilish");
        questionsList.add(new Questions("Yo'lga chiqayotgan piyodalarga qanday harakat qilish kerak?", option19, "A"));
        Options option20 = new Options("Piyodalarga yo'l berish", "Avtomobillarga yo'l berish", "Ikkalasi ham yo'l");
        questionsList.add(new Questions("Piyodalar va avtomobillar o'zaro uchrashganda kimga yo'l berish kerak?", option20, "A"));
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
