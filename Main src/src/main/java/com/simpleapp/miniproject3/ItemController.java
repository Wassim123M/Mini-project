package com.simpleapp.miniproject3;

import com.simpleapp.miniproject3.models.Item;
import com.simpleapp.miniproject3.models.ItemStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ItemController {

    @FXML
    private TextField nameFld, colorFld, weightFld, priceFld;

    @FXML
    private DatePicker dateFld;

    @FXML
    private CheckBox fruitCheck, vegCheck;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, String> nameColumn, typeColumn;

    @FXML
    private TableColumn<Item, Double> weightColumn, priceColumn;

    @FXML
    private TableColumn<Item, LocalDate> dateColumn;

    @FXML
    private Text errorMsg;

    private final ObservableList<Item> items = FXCollections.observableArrayList();
    private final ItemStore store = new ItemStore();

    // Initialize Table Columns
    @FXML
    public void initialize() {
        // Set up table columns
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        typeColumn.setCellValueFactory(data -> data.getValue().typeProperty());
        weightColumn.setCellValueFactory(data -> data.getValue().weightProperty().asObject());
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty().asObject());
        dateColumn.setCellValueFactory(data -> data.getValue().dateProperty());

        // Load items from store
        items.addAll(store.getItemsList());
        itemTable.setItems(items);

        // Select first item automatically
        if (!items.isEmpty()) {
            itemTable.getSelectionModel().selectFirst();
            populateFields(items.get(0));
        }

        // Update form when selection changes
        itemTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) populateFields(newSel);
        });
    }

    // Populate form fields with item data
    private void populateFields(Item item) {
        nameFld.setText(item.getName());
        weightFld.setText(String.valueOf(item.getWeight()));
        priceFld.setText(String.valueOf(item.getPrice()));
        dateFld.setValue(item.getDate());
        fruitCheck.setSelected("Fruit".equals(item.getType()));
        vegCheck.setSelected("Vegetable".equals(item.getType()));
        errorMsg.setText("");
    }

    // Handle Fruit Check
    @FXML
    void onFruitCheck() { if (fruitCheck.isSelected()) vegCheck.setSelected(false); }

    // Handle Vegetable Check
    @FXML
    void onVegCheck() { if (vegCheck.isSelected()) fruitCheck.setSelected(false); }

    // Add new Item
    @FXML
    void addItem(ActionEvent event) {
        String name = nameFld.getText().trim();
        String type = fruitCheck.isSelected() ? "Fruit" : vegCheck.isSelected() ? "Vegetable" : "";
        LocalDate date = dateFld.getValue();

        double weight, price;

        if (name.isEmpty() || type.isEmpty() || date == null) {
            showError("Please fill all fields correctly.");
            return;
        }

        try {
            weight = Double.parseDouble(weightFld.getText());
            price = Double.parseDouble(priceFld.getText());
        } catch (NumberFormatException e) {
            showError("Weight and Price must be numbers.");
            return;
        }

        Item newItem = new Item(name, type, weight, price, date);
        items.add(newItem);
        store.addItem(newItem);
        clearFields();
        itemTable.getSelectionModel().select(newItem);
    }

    // Update existing item
    @FXML
    void updateItem(ActionEvent event) {
        Item selected = itemTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select an item to update.");
            return;
        }

        try {
            selected.setName(nameFld.getText());
            selected.setType(fruitCheck.isSelected() ? "Fruit" : "Vegetable");
            selected.setWeight(Double.parseDouble(weightFld.getText()));
            selected.setPrice(Double.parseDouble(priceFld.getText()));
            selected.setDate(dateFld.getValue());
        } catch (NumberFormatException e) {
            showError("Weight and Price must be numbers.");
            return;
        }

        itemTable.refresh();
        clearFields();
        itemTable.getSelectionModel().select(selected);
    }

    // Delete item
    @FXML
    void deleteItem(ActionEvent event) {
        Item selected = itemTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            items.remove(selected);
            store.deleteItem(selected);
            clearFields();
        } else {
            showError("Select an item to delete.");
        }
    }

    // Back to home page
    @FXML
    void onBackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/simpleapp/miniproject3/home-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Show error messages
    private void showError(String message) {
        errorMsg.setText(message);
    }

    // Clear form fields
    private void clearFields() {
        nameFld.clear();
        weightFld.clear();
        priceFld.clear();
        dateFld.setValue(null);
        fruitCheck.setSelected(false);
        vegCheck.setSelected(false);
        errorMsg.setText("");
    }
}
