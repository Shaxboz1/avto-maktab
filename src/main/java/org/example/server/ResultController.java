package org.example.server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class ResultController {
    @Setter
    private int correctAnswersCount;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button asosiyButton;
    @FXML
    private Button idLabel;
    @FXML
    private Button ismLabel;
    @FXML
    private Button famLabel;
    @FXML
    private Button qaytish;
    @FXML
    public Button button;

    @FXML
    private void initialize() {
        button.setText("Correct Answers: " + correctAnswersCount);
        qaytish.setOnAction(actionEvent -> openScene("/org/example/server/Welcome.fxml"));
        idLabel.setText("ID");
        ismLabel.setText("Ism");
        famLabel.setText("Familiya");
        qaytish.setText("Tugatish!");

        setupButtonLayout();

        rootPane.widthProperty().addListener((obs, oldVal, newVal) -> setupButtonLayout());
        rootPane.heightProperty().addListener((obs, oldVal, newVal) -> setupButtonLayout());
    }

    private void setupButtonLayout() {
        double width = rootPane.getWidth();
        double height = rootPane.getHeight();

        asosiyButton.setPrefSize(width * 0.9, height * 0.85);
        asosiyButton.setLayoutX(width * 0.05);
        asosiyButton.setLayoutY(height * 0.1);

        double buttonWidth = width * 0.2;
        double buttonHeight = height * 0.1;

        idLabel.setPrefSize(buttonWidth, buttonHeight);
        idLabel.setLayoutX(width * 0.1);
        idLabel.setLayoutY(height * 0.3);

        ismLabel.setPrefSize(buttonWidth, buttonHeight);
        ismLabel.setLayoutX(width * 0.4);
        ismLabel.setLayoutY(height * 0.3);

        famLabel.setPrefSize(buttonWidth, buttonHeight);
        famLabel.setLayoutX(width * 0.7);
        famLabel.setLayoutY(height * 0.3);

        button.setPrefSize(buttonWidth, buttonHeight);
        button.setLayoutX(width * 0.4);
        button.setLayoutY(height * 0.5);

        qaytish.setPrefSize(buttonWidth, buttonHeight);
        qaytish.setLayoutX(width * 0.4);
        qaytish.setLayoutY(height * 0.7);
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
                stage.setFullScreen(true);
                stage.show();
            }
        } catch (IOException e) {
            System.err.println("FXML faylini ochishda xatolik: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
