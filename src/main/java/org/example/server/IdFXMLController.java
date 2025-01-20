package org.example.server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class IdFXMLController {

    @FXML
    private Button Tastiqlash;
    @FXML
    private TextField IdField;
    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        // Dastlabki fokusni o'rnatish
        Platform.runLater(() -> IdField.requestFocus());

        // Tugmalar uchun hodisalarni bog'lash
        Tastiqlash.setOnAction(event -> validateAndOpen());
        IdField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                validateAndOpen();
            }
        });
        resizeComponents();
    }

    private void validateAndOpen() {
        if ("15".equals(IdField.getText())) {
            openScene();
            System.out.println("Test boshlandi.");
        }
    }

    private void openScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/server/FORMA.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Tastiqlash.getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            System.err.println("FXML faylni ochishda xatolik: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void resizeComponents() {
        // Oynaning kengligi va balandligi
        double width = rootPane.getWidth();
        double height = rootPane.getHeight();

        // IdField joylashuvi va o‘lchami
        if (IdField != null) {
            IdField.setPrefWidth(width * 0.4); // Ekran kengligining 40% qismini egallaydi
            IdField.setLayoutX((width - IdField.getPrefWidth()) / 2); // Markazga joylashtirish
            IdField.setLayoutY(height * 0.3); // Oynaning 30% balandligida joylashadi
        }

        // Tastiqlash tugmasi joylashuvi va o‘lchami
        if (Tastiqlash != null) {
            Tastiqlash.setPrefWidth(width * 0.2); // Ekran kengligining 20% qismini egallaydi
            Tastiqlash.setPrefHeight(height * 0.1); // Ekran balandligining 10% qismini egallaydi
            Tastiqlash.setLayoutX((width - Tastiqlash.getPrefWidth()) / 2); // Markazga joylashtirish
            Tastiqlash.setLayoutY(height * 0.5); // Oynaning 50% balandligida joylashadi
        }
    }

}
