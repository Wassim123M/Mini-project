package desktopapps.demo4.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SportEquipmentStore {
    private final ObservableList<SportEquipment> equipmentList = FXCollections.observableArrayList();

    public SportEquipmentStore() {
        this.equipmentList.addAll(
            new SportEquipment("Soccer Ball", "Ball", 10),
            new SportEquipment("Tennis Racket", "Racket", 5),
            new SportEquipment("Volleyball Net", "Net", 2)
        );
    }

    public ObservableList<SportEquipment> getEquipmentList() {
        return equipmentList;
    }

    public void addEquipment(SportEquipment equipment) {
        if (equipment != null) {
            equipmentList.add(equipment);
        }
    }

    public void deleteEquipment(SportEquipment equipment) {
        if (equipment != null) {
            equipmentList.remove(equipment);
        }
    }

    public void updateEquipment(SportEquipment equipment, String name, String type, Integer quantity) {
        if (equipment != null) {
            equipment.setEquipmentName(name);
            equipment.setType(type);
            equipment.setQuantity(quantity);
        }
    }
}
