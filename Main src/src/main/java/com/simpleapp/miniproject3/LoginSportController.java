package com.simpleapp.miniproject3;

import com.simpleapp.miniproject3.db.DBUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class LoginSportController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    public void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (checkLogin(user, pass)) {
            openSportScreen();
        } else {
            errorLabel.setText("Invalid login!");
        }
    }

    private boolean checkLogin(String user, String pass) {
        String sql = "SELECT * FROM accounts WHERE username=? AND password=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void openSportScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/simpleapp/miniproject3/sport-equipment-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
