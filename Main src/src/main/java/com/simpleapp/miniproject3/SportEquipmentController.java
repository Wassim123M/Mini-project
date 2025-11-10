package com.simpleapp.miniproject3;

import com.simpleapp.miniproject3.models.SportEquipment;
import com.simpleapp.miniproject3.models.SportEquipmentStore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class SportEquipmentController {

    @FXML private TableView<SportEquipment> equipmentTable;
    @FXML private TableColumn<SportEquipment, String> equipmentNameCol;
    @FXML private TableColumn<SportEquipment, String> typeCol;
    @FXML private TableColumn<SportEquipment, Integer> quantityCol;
    @FXML private TableColumn<SportEquipment, Double> priceCol;
    @FXML private TextField equipmentNameFld;
    @FXML private TextField typeFld;
    @FXML private TextField quantityFld;
    @FXML private TextField priceFld;
    @FXML private Text errorMsg;

    @FXML
    public void initialize() {
        equipmentNameCol.setCellValueFactory(data -> data.getValue().nameProperty());
        typeCol.setCellValueFactory(data -> data.getValue().typeProperty());
        quantityCol.setCellValueFactory(data -> data.getValue().quantityProperty().asObject());
        priceCol.setCellValueFactory(data -> data.getValue().priceProperty().asObject());
        equipmentTable.setItems(SportEquipmentStore.getEquipmentList());

        equipmentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) populateFields(newSel);
        });
    }
    
    @FXML
    private void addEquipment() {
        String name = equipmentNameFld.getText().trim();
        String type = typeFld.getText().trim();
        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(quantityFld.getText().trim());
            price = Double.parseDouble(priceFld.getText().trim());
        } catch (NumberFormatException e) {
            errorMsg.setText("Quantity and Price must be numeric!");
            return;
        }

        if (name.isEmpty() || type.isEmpty()) {
            errorMsg.setText("Please fill all required fields!");
            return;
        }

        SportEquipment equipment = new SportEquipment(name, type, quantity, price);
        SportEquipmentStore.addEquipment(equipment);
        clearFields();
        errorMsg.setText("");
    }
    
    @FXML
    private void updateEquipment() {
        SportEquipment selected = equipmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            errorMsg.setText("Select an equipment to update!");
            return;
        }
        String name = equipmentNameFld.getText().trim();
        String type = typeFld.getText().trim();
        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(quantityFld.getText().trim());
            price = Double.parseDouble(priceFld.getText().trim());
        } catch (NumberFormatException e) {
            errorMsg.setText("Quantity and Price must be numeric!");
            return;
        }
        if (name.isEmpty() || type.isEmpty()) {
            errorMsg.setText("Please fill all required fields!");
            return;
        }
        selected.setName(name);
        selected.setType(type);
        selected.setQuantity(quantity);
        selected.setPrice(price);
        equipmentTable.refresh();
        clearFields();
        errorMsg.setText("");
    }
    
    @FXML
    private void deleteEquipment() {
        SportEquipment selected = equipmentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            SportEquipmentStore.removeEquipment(selected);
            clearFields();
            errorMsg.setText("");
        } else {
            errorMsg.setText("Select an equipment to delete!");
        }
    }
    
    @FXML
    private void onBackHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) equipmentTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorMsg.setText("Failed to load Home screen!");
        }
    }
    
    private void populateFields(SportEquipment equipment) {
        equipmentNameFld.setText(equipment.getName());
        typeFld.setText(equipment.getType());
        quantityFld.setText(String.valueOf(equipment.getQuantity()));
        priceFld.setText(String.valueOf(equipment.getPrice()));
    }
    
    private void clearFields() {
        equipmentNameFld.clear();
        typeFld.clear();
        quantityFld.clear();
        priceFld.clear();
    }
}

