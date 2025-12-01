package com.simpleapp.miniproject3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    public void openItemsMng(ActionEvent event) {
        openLogin("/com/simpleapp/miniproject3/login-item.fxml", event);
    }

    public void openSportsEquipmentMng(ActionEvent event) {
        openLogin("/com/simpleapp/miniproject3/login-sport.fxml", event);
    }

    private void openLogin(String fxml, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //EXIT APPLICATION
    @FXML
    public void onExitApp(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
