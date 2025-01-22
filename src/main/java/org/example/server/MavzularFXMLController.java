package org.example.server;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class MavzularFXMLController {
    @FXML
    private Button BoshSahifa, LangCRL, LangRU, LangUZB, ortga;
    @FXML
    private GridPane gridPane;

    public void initialize() {

        BoshSahifa.setOnAction(event -> openScene("/org/example/server/Welcome.fxml"));
        ortga.setOnAction(event -> openScene("/org/example/server/BolmlarFXML.fxml"));

        String[] buttonTexts = readButtonTextsFromFile("/org/example/server/Mavzular.txt");

        int numberOfButtons = buttonTexts.length;
        for (int i = 0; i < numberOfButtons; i++) {
            Button button = new Button();
            button.setText(buttonTexts[i]);
            button.setPrefHeight(57.0);
            button.setPrefWidth(710.0);
            button.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #00BFFF, #001F4D); -fx-border-color: white; -fx-background-radius: 15; -fx-border-radius: 15;-fx-text-fill: #ffff;-fx-font-size: 20;");
            button.setId("button" + (i + 1));

            // Button bosilganda nimani qilish kerakligini aniqlash
            int buttonIndex = i + 1;

            // Agar buttonIndex 1 bo'lsa, OraliqTest sahifasini ochish
            if (buttonIndex == 1) {
                button.setOnAction(event -> openScene("/org/example/server/MavzuTest.fxml"));
            } else {
                // Boshqa tugmalar uchun umumiy voqealar qo'shish
                button.setOnAction(event -> System.out.println(buttonIndex + " tugma bosildi!"));
            }

            // GridPane'ga button qo'shish
            gridPane.add(button, 0, i);
            applyHoverAnimation(button);
        }
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
    private String[] readButtonTextsFromFile(String filePath) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return text.toString().split("\n");
    }

    public void openScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) BoshSahifa.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                event.consume();
                System.out.println("Chiqish bloklandi! Dasturdan chiqib bo'lmaydi.");
            });
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}