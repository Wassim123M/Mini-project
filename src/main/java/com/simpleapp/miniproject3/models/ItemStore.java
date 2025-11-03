package com.simpleapp.miniproject3.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class ItemStore {

    private final ObservableList<Item> items = FXCollections.observableArrayList();

    public ItemStore() {
        items.addAll(
                new Item("Apple", "Fruit", 0.2, 1.5, LocalDate.now()),
                new Item("Carrot", "Vegetable", 0.1, 0.8, LocalDate.now()),
                new Item("Banana", "Fruit", 0.3, 1.2, LocalDate.now())
        );
    }

    public ObservableList<Item> getItemsList() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(Item oldItem, String name, String type, double weight, double price, LocalDate date) {
        oldItem.setName(name);
        oldItem.setType(type);
        oldItem.setWeight(weight);
        oldItem.setPrice(price);
        oldItem.setDate(date);
    }

    public void deleteItem(Item item) {
        items.remove(item);
    }
}
