package org.example.server;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


public class UnifiedFXMLController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button Darslik;

    @FXML
    private Button Oralik_Test;

    @FXML
    private Button Imtihon;

    @FXML
    private Button Variant;

    @FXML
    private void initialize() {
        Darslik.setOnAction(event -> openScene("/org/example/server/BolmlarFXML.fxml")); // Darslik sahnasi
        Oralik_Test.setOnAction(event -> openScene("/org/example/server/OraliqTest.fxml")); // Oralik Test sahnasi
        Imtihon.setOnAction(event -> openIDWindow("/org/example/server/IDFXML.fxml")); // Imtihon sahnasi
        Variant.setOnAction(event -> openScene("/org/example/server/VariantFXML.fxml")); // Variant sahnasi

        applyHoverAnimation(Darslik);
        applyHoverAnimation(Oralik_Test);
        applyHoverAnimation(Imtihon);
        applyHoverAnimation(Variant);

        // Sahna o'lchami o'zgarganda tugmalarni qayta joylashtirish
        rootPane.widthProperty().addListener((obs, oldVal, newVal) -> adjustButtonLayout());
        rootPane.heightProperty().addListener((obs, oldVal, newVal) -> adjustButtonLayout());
    }

    @FXML
    private void adjustButtonLayout() {
        double width = rootPane.getWidth();
        double height = rootPane.getHeight();

        // Har bir tugma kengligi (ekran kengligiga qarab)
        double buttonWidth = width * 0.2;
        double buttonHeight = height * 0.3;

        // Tugmalar orasidagi masofa (dinamik hisoblash)
        double totalButtonWidth = 4 * buttonWidth; // 4 ta tugmaning umumiy kengligi
        double spacing = (width - totalButtonWidth) / 5; // Tugmalar orasidagi masofa (chap va o'ng chekkalar uchun qo‘shiladi)

        if (spacing < 10) {
            spacing = 10; // Minimal masofa cheklovchi
            buttonWidth = (width - 5 * spacing) / 4; // Tugmalarni kichiklashtirish
        }

        // Tugmalar o'lchamini o'rnatish
        Darslik.setPrefWidth(buttonWidth);
        Darslik.setPrefHeight(buttonHeight);

        Oralik_Test.setPrefWidth(buttonWidth);
        Oralik_Test.setPrefHeight(buttonHeight);

        Imtihon.setPrefWidth(buttonWidth);
        Imtihon.setPrefHeight(buttonHeight);

        Variant.setPrefWidth(buttonWidth);
        Variant.setPrefHeight(buttonHeight);

        // Tugmalarni joylashtirish
        Darslik.setLayoutX(spacing);
        Darslik.setLayoutY(height / 2 - buttonHeight / 2);

        Oralik_Test.setLayoutX(spacing * 2 + buttonWidth);
        Oralik_Test.setLayoutY(height / 2 - buttonHeight / 2);

        Imtihon.setLayoutX(spacing * 3 + 2 * buttonWidth);
        Imtihon.setLayoutY(height / 2 - buttonHeight / 2);

        Variant.setLayoutX(spacing * 4 + 3 * buttonWidth);
        Variant.setLayoutY(height / 2 - buttonHeight / 2);
    }



    private void applyHoverAnimation(Button button) {
        button.setOnMouseEntered(e -> playScaleAnimation(button, 1.05));

        button.setOnMouseExited(e -> playScaleAnimation(button, 1.0));
    }

    private void playScaleAnimation(Button button, double scaleValue) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setToX(scaleValue);
        scaleTransition.setToY(scaleValue);
        scaleTransition.play();
    }

    private void openScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane root = loader.load();


            Scene scene = new Scene(root);

            Stage currentStage = (Stage) Imtihon.getScene().getWindow();

            currentStage.setScene(scene);

            currentStage.setFullScreen(true);
            currentStage.setTitle("Auto Maktab");
            currentStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sahna ochishda xato yuz berdi.");
        }
    }

    private void openIDWindow(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Auto Maktab");
            stage.setOnCloseRequest(event -> {
                event.consume();
                System.out.println("Chiqish bloklandi! Dasturdan chiqib bo'lmaydi.");
            });
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sahna ochishda xato yuz berdi.");
        }
        Stage currentStage = (Stage) Imtihon.getScene().getWindow();
        currentStage.close();
    }
}
