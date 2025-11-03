package com.simpleapp.miniproject3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    // ===== Open the Item Management View (Full Screen) =====
    @FXML
    void openItemsMng(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("item-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true); // ✅ Opens full screen
        stage.show();
    }

    // ===== Exit the Application =====
    @FXML
    void onExitApp(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
