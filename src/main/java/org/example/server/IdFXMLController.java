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
import java.util.ArrayList;
import java.util.List;

public class IdFXMLController {

    @FXML
    private Button Tastiqlash;
    @FXML
    private TextField IdField;
    @FXML
    private AnchorPane rootPane;

    private final List<User> userList = new ArrayList<>();

    @FXML
    public void initialize() {
        userList.add(new User("15", "Shaxboz Boyhanov"));
        userList.add(new User("20", "Botir Nurmuxammedov"));
        userList.add(new User("30", "Temur Axmadjanov"));

        Platform.runLater(() -> IdField.requestFocus());
        Tastiqlash.setOnAction(event -> validateAndOpen());
        IdField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                validateAndOpen();
            }
        });
        resizeComponents();
    }

    private void validateAndOpen() {
        String enteredId = IdField.getText();
        User user = findUserById(enteredId);
        if (user != null) {
            openScene(user.getId(),user.getName());
            System.out.println("Test boshlandi.");
        } else {
            System.out.println("Foydalanuvchi topilmadi.");
        }
    }

    private User findUserById(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
    private void openScene(String userId,String userName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/server/FORMA.fxml"));
            AnchorPane root = loader.load();
            QuestionFXMLController questionFXMLController = loader.getController();
            questionFXMLController.nameLabel.setText(userName);
            questionFXMLController.id = "ID: " + userId;
            questionFXMLController.name = "Ism: " + userName;
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
        double width = rootPane.getWidth();
        double height = rootPane.getHeight();

        if (IdField != null) {
            IdField.setPrefWidth(width * 0.4);
            IdField.setLayoutX((width - IdField.getPrefWidth()) / 2);
            IdField.setLayoutY(height * 0.3);
        }

        if (Tastiqlash != null) {
            Tastiqlash.setPrefWidth(width * 0.2);
            Tastiqlash.setPrefHeight(height * 0.1);
            Tastiqlash.setLayoutX((width - Tastiqlash.getPrefWidth()) / 2);
            Tastiqlash.setLayoutY(height * 0.5);
        }
    }
}
