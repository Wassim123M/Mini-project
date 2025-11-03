package com.simpleapp.miniproject3.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Item {
    private final StringProperty name;
    private final StringProperty type;
    private final DoubleProperty weight;
    private final DoubleProperty price;
    private final ObjectProperty<LocalDate> date;

    public Item(String name, String type, double weight, double price, LocalDate date) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.weight = new SimpleDoubleProperty(weight);
        this.price = new SimpleDoubleProperty(price);
        this.date = new SimpleObjectProperty<>(date);
    }

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
