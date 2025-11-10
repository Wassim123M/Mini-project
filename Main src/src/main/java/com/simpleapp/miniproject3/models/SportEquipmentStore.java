package com.simpleapp.miniproject3.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SportEquipmentStore {

    private static final ObservableList<SportEquipment> equipmentList = FXCollections.observableArrayList();

    static {
        // Preset sample equipment
        equipmentList.addAll(
                new SportEquipment("Soccer Ball", "Ball", 10, 29.99),
                new SportEquipment("Tennis Racket", "Racket", 5, 79.99),
                new SportEquipment("Volleyball Net", "Net", 2, 120.00),
                new SportEquipment("Basketball", "Ball", 8, 35.50),
                new SportEquipment("Badminton Shuttlecock", "Shuttlecock", 50, 15.00)
        );
    }

    // Get the observable list
    public static ObservableList<SportEquipment> getEquipmentList() {
        return equipmentList;
    }

    // Add new equipment
    public static void addEquipment(SportEquipment equipment) {
        equipmentList.add(equipment);
    }

    // Remove equipment
    public static void removeEquipment(SportEquipment equipment) {
        equipmentList.remove(equipment);
    }
}
