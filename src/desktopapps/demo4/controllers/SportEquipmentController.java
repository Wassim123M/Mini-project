package desktopapps.demo4.controllers;

import desktopapps.demo4.models.SportEquipment;
import desktopapps.demo4.models.SportEquipmentStore;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class SportEquipmentController {

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<SportEquipment, String> equipmentNameCol;

    @FXML
    private TextField equipmentNameFld;

    @FXML
    private TableColumn<SportEquipment, String> typeCol;

    @FXML
    private TextField typeFld;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<SportEquipment, Integer> quantityCol;

    @FXML
    private TextField quantityFld;

    @FXML
    private Button updateBtn;

    @FXML
    private TableView<SportEquipment> equipmentTable;

    @FXML
    private Text errorMsg;

    private final SportEquipmentStore equipmentStore = new SportEquipmentStore();

    @FXML
    public void initialize() {
        equipmentNameCol.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ObservableList<SportEquipment> equipmentList = equipmentStore.getEquipmentList();
        equipmentTable.setItems(equipmentList);

        equipmentTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            SportEquipment selectedEquipment = equipmentTable.getSelectionModel().getSelectedItem();
            if(selectedEquipment != null) {
                equipmentNameFld.setText(selectedEquipment.getEquipmentName());
                typeFld.setText(selectedEquipment.getType());
                quantityFld.setText(Integer.toString(selectedEquipment.getQuantity()));
            }
        });
    }

    @FXML
    void addEquipment(ActionEvent event) {
        String error = "";
        boolean isValid = true;

        String equipmentName = equipmentNameFld.getText();
        if(equipmentName.isEmpty()) {
            error += "Error: Equipment Name is required\n";
            isValid = false;
        }

        String type = typeFld.getText();
        if(type.isEmpty()) {
            error += "Error: Type is required\n";
            isValid = false;
        }

        Integer quantity = null;
        if(quantityFld.getText().isEmpty()) {
            error += "Error: Quantity is required\n";
            isValid = false;
        }
        else try {
            quantity = Integer.parseInt(quantityFld.getText());
        } catch(NumberFormatException ex) {
            error += "Error: Invalid quantity value!\n";
            isValid = false;
        }

        if(isValid) {
            equipmentStore.addEquipment(new SportEquipment(equipmentName, type, quantity));

            equipmentNameFld.setText("");
            typeFld.setText("");
            quantityFld.setText("");
            errorMsg.setText("");
        } else {
            errorMsg.setText(error);
        }
    }

    @FXML
    void deleteEquipment(ActionEvent event) {
        SportEquipment selectedEquipment = equipmentTable.getSelectionModel().getSelectedItem();

        if(selectedEquipment != null) {
            equipmentStore.deleteEquipment(selectedEquipment);

            // Clear fields after deletion
            equipmentNameFld.setText("");
            typeFld.setText("");
            quantityFld.setText("");
            errorMsg.setText("");
        }
    }

    @FXML
    void updateEquipment(ActionEvent event) {
        SportEquipment selectedEquipment = equipmentTable.getSelectionModel().getSelectedItem();

        if(selectedEquipment != null) {
            String error = "";
            boolean isValid = true;

            String equipmentName = equipmentNameFld.getText();
            if(equipmentName.isEmpty()) {
                error += "Error: Equipment Name is required!\n";
                isValid = false;
            }

            String type = typeFld.getText();
            if(type.isEmpty()) {
                error += "Error: Type is required!\n";
                isValid = false;
            }

            Integer quantity = null;
            if(quantityFld.getText().isEmpty()) {
                error += "Error: Quantity is required!\n";
                isValid = false;
            }
            else try {
                quantity = Integer.parseInt(quantityFld.getText());
            } catch(NumberFormatException ex) {
                error += "Error: Invalid quantity value!\n";
                isValid = false;
            }

            if(isValid) {
                equipmentStore.updateEquipment(selectedEquipment, equipmentName, type, quantity);
                errorMsg.setText("");
            } else {
                errorMsg.setText(error);
            }
        }
    }
}
