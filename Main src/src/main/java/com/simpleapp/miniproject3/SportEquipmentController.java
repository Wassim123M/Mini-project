package com.simpleapp.miniproject3;

import com.simpleapp.miniproject3.db.DBUtil;
import com.simpleapp.miniproject3.models.SportEquipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class SportEquipmentController {

    @FXML private TableView<SportEquipment> equipmentTable;
    @FXML private TableColumn<SportEquipment, String> nameColumn;
    @FXML private TableColumn<SportEquipment, String> typeColumn;
    @FXML private TableColumn<SportEquipment, Integer> quantityColumn;
    @FXML private TableColumn<SportEquipment, Double> priceColumn;

    @FXML private TextField equipmentNameFld;
    @FXML private TextField typeFld;
    @FXML private TextField quantityFld;
    @FXML private TextField priceFld;

    private final ObservableList<SportEquipment> equipmentList = FXCollections.observableArrayList();


    // --------------------- INITIALIZE -------------------------
    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        typeColumn.setCellValueFactory(data -> data.getValue().typeProperty());
        quantityColumn.setCellValueFactory(data -> data.getValue().quantityProperty().asObject());
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty().asObject());

        equipmentTable.setItems(equipmentList);

        loadEquipment();

        equipmentTable.setOnMouseClicked(e -> fillForm());
    }

    private void fillForm() {
        SportEquipment se = equipmentTable.getSelectionModel().getSelectedItem();
        if (se == null) return;

        equipmentNameFld.setText(se.getName());
        typeFld.setText(se.getType());
        quantityFld.setText(String.valueOf(se.getQuantity()));
        priceFld.setText(String.valueOf(se.getPrice()));
    }


    // --------------------- LOAD -------------------------
    private void loadEquipment() {
        equipmentList.clear();

        String sql = "SELECT * FROM sport_equipment";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SportEquipment se = new SportEquipment(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                equipmentList.add(se);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --------------------- ADD -------------------------
    @FXML
    public void addEquipment() {

        String name = equipmentNameFld.getText();
        String type = typeFld.getText();
        int qty = Integer.parseInt(quantityFld.getText());
        double price = Double.parseDouble(priceFld.getText());

        String sql = "INSERT INTO sport_equipment (name, type, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, type);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.executeUpdate();

            equipmentList.add(new SportEquipment(name, type, qty, price));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --------------------- UPDATE -------------------------
    @FXML
    public void updateEquipment() {

        SportEquipment selected = equipmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String newName = equipmentNameFld.getText();
        String newType = typeFld.getText();
        int newQty = Integer.parseInt(quantityFld.getText());
        double newPrice = Double.parseDouble(priceFld.getText());

        String sql = "UPDATE sport_equipment SET name=?, type=?, quantity=?, price=? WHERE name=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setString(2, newType);
            ps.setInt(3, newQty);
            ps.setDouble(4, newPrice);
            ps.setString(5, selected.getName());

            ps.executeUpdate();

            selected.setName(newName);
            selected.setType(newType);
            selected.setQuantity(newQty);
            selected.setPrice(newPrice);

            equipmentTable.refresh();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --------------------- DELETE -------------------------
    @FXML
    public void deleteEquipment() {

        SportEquipment selected = equipmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String sql = "DELETE FROM sport_equipment WHERE name=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, selected.getName());
            ps.executeUpdate();

            equipmentList.remove(selected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --------------------- BACK HOME -------------------------
    @FXML
    private void onBackHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/simpleapp/miniproject3/home-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) equipmentTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
