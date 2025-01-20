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

public class ResultController {


    @FXML
    private Label idLabel;
    @FXML
    private Pane  container;
    @FXML
    private Label ismLabel;
    // Declare it at the top
    @FXML
    private Label famLabel;
    @FXML
    private Button button;
    Label  natijaLabel = new Label();
    public void setId(String id) {
        idLabel.setText(id);
    }

    public void setIsm(String ism) {
        ismLabel.setText(ism);
    }


    public void setFam(String fam) {
        famLabel.setText(fam);
    }

    @FXML
    private void initialize() {
        if (button != null) {
            button.setOnAction(actionEvent -> openScene("/org/example/server/Welcome.fxml"));
        }

        natijaLabel.setText();

//        natijaLabel.setLayoutX(50); // Set the X position (adjust as needed)
//        natijaLabel.setLayoutY(50); // Set the Y position (adjust as needed)
        natijaLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20");
        container.getChildren().add(natijaLabel);
        System.out.println(natijaLabel.getText());
    }

    private void openScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) button.getScene().getWindow();
            if (stage != null) {
                stage.close();
                stage.setScene(scene);
                stage.setOnCloseRequest(event -> {
                    event.consume();
                    System.out.println("Chiqish bloklandi! Dasturdan chiqib bo'lmaydi.");
                });
                stage.setFullScreen(true);
                stage.setFullScreenExitHint("");
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
