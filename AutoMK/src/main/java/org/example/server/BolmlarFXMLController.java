package org.example.server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BolmlarFXMLController {
    @FXML
    private Button BoshMenu, langUZ, LangRU, langCRL, bolm1, bolm2, bolm3, ortga;

    @FXML
    private void initialize() {
        BoshMenu.setOnAction(event -> openScene("/org/example/server/Welcome.fxml"));
        bolm1.setOnAction(event -> openScene("/org/example/server/MavzularFXML.fxml"));
        bolm2.setOnAction(event -> openScene("/org/example/server/MavzularFXML.fxml"));
        bolm3.setOnAction(event -> openScene("/org/example/server/MavzularFXML.fxml"));
        ortga.setOnAction(event -> openScene("/org/example/server/Welcome.fxml"));
    }

    private void openScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane anchorPane = loader.load();
            Stage currentStage = (Stage) bolm1.getScene().getWindow();
            Scene scene = new Scene(anchorPane);
            currentStage.setScene(scene);
            currentStage.setFullScreen(true);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sahna ochishda xato yuz berdi: " + e.getMessage());
        }
    }
}
