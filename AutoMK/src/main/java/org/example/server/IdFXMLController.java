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
    public void initialize() {
        Platform.runLater(() -> IdField.requestFocus());
        Tastiqlash.setOnAction(event -> validateAndOpen());
        IdField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                validateAndOpen();
            }
        });
    }
    private void validateAndOpen() {
        if (IdField.getText().equals("15")) {
            openScene();
            System.out.println("Test Boshlandi");
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
            throw new RuntimeException(e);
        }
    }
}
