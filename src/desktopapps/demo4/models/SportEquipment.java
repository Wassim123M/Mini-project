package desktopapps.demo4.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SportEquipment {
    private final SimpleStringProperty equipmentName;
    private final SimpleStringProperty type;
    private final SimpleIntegerProperty quantity;

    public SportEquipment(String equipmentName, String type, Integer quantity) {
        this.equipmentName = new SimpleStringProperty(equipmentName);
        this.type = new SimpleStringProperty(type);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public String getEquipmentName() {
        return equipmentName.get();
    }

    public void setEquipmentName(String name) {
        this.equipmentName.set(name);
    }

    public StringProperty equipmentNameProperty() {
        return this.equipmentName;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return this.type;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return this.quantity;
    }
}
