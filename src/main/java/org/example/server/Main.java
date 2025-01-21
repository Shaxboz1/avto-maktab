package org.example.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/server/Welcome.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setOnCloseRequest(event -> {
                event.consume();
                System.out.println("Chiqish bloklandi! Dasturdan chiqib bo'lmaydi.");
            });
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setTitle("Monitor Razmerida Oyna");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fayl yuklashda xato yuz berdi.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}