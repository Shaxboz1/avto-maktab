package org.example.server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.IOException;

public class BolmlarFXMLController {
    @FXML
    private Button BoshMenu, langUZ, LangRU, langCRL, bolm1, bolm2, bolm3, ortga;

    private DoubleProperty sceneWidth = new SimpleDoubleProperty();
    private DoubleProperty sceneHeight = new SimpleDoubleProperty();

    @FXML
    private void initialize() {
        sceneWidth.bind(BoshMenu.sceneProperty().get().widthProperty());
        sceneHeight.bind(BoshMenu.sceneProperty().get().heightProperty());

        // Ekran o'lchamiga moslash uchun binding
        BoshMenu.prefWidthProperty().bind(sceneWidth.divide(6));  // Ekranning 6-dan bir qismi
        BoshMenu.prefHeightProperty().bind(sceneHeight.divide(15)); // Ekranning 15-dan bir qismi

        langUZ.prefWidthProperty().bind(sceneWidth.divide(12));
        LangRU.prefWidthProperty().bind(sceneWidth.divide(12));
        langCRL.prefWidthProperty().bind(sceneWidth.divide(12));

        bolm1.prefWidthProperty().bind(sceneWidth.divide(3));
        bolm2.prefWidthProperty().bind(sceneWidth.divide(3));
        bolm3.prefWidthProperty().bind(sceneWidth.divide(3));

        ortga.prefWidthProperty().bind(sceneWidth.divide(5));

        // Button'larning o'zaro bog'lanishlari
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
