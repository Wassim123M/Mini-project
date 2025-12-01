package com.simpleapp.miniproject3.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Item {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final DoubleProperty weight = new SimpleDoubleProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    // Constructor used when loading from DB
    public Item(int id, String name, String type, double weight, double price, LocalDate date) {
        this.id.set(id);
        this.name.set(name);
        this.type.set(type);
        this.weight.set(weight);
        this.price.set(price);
        this.date.set(date);
    }

    // Constructor used for new items before DB assigns an ID
    public Item(String name, String type, double weight, double price, LocalDate date) {
        this.name.set(name);
        this.type.set(type);
        this.weight.set(weight);
        this.price.set(price);
        this.date.set(date);
    }

    // Getters and setters
    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public String getType() { return type.get(); }
    public void setType(String value) { type.set(value); }
    public StringProperty typeProperty() { return type; }

    public double getWeight() { return weight.get(); }
    public void setWeight(double value) { weight.set(value); }
    public DoubleProperty weightProperty() { return weight; }

    public double getPrice() { return price.get(); }
    public void setPrice(double value) { price.set(value); }
    public DoubleProperty priceProperty() { return price; }

    public LocalDate getDate() { return date.get(); }
    public void setDate(LocalDate value) { date.set(value); }
    public ObjectProperty<LocalDate> dateProperty() { return date; }
}
