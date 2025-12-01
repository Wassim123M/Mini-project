package com.simpleapp.miniproject3.models;

import com.simpleapp.miniproject3.db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class ItemStore {

    private final ObservableList<Item> items = FXCollections.observableArrayList();

    public ItemStore() {
        loadItemsFromDatabase();
    }

    // ✅ Read (SELECT)
    public void loadItemsFromDatabase() {
        items.clear();
        String sql = "SELECT * FROM Items";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                double weight = rs.getDouble("weight");
                double price = rs.getDouble("price");
                LocalDate date = rs.getDate("date").toLocalDate();

                items.add(new Item(id, name, type, weight, price, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Create (INSERT)
    public void addItem(Item item) {
        String sql = "INSERT INTO Items (name, type, weight, price, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getType());
            ps.setDouble(3, item.getWeight());
            ps.setDouble(4, item.getPrice());
            ps.setDate(5, Date.valueOf(item.getDate()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        item.setId(keys.getInt(1)); // assign DB id
                    }
                }
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Update
    public void updateItem(Item item) {
        String sql = "UPDATE Items SET name=?, type=?, weight=?, price=?, date=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getType());
            ps.setDouble(3, item.getWeight());
            ps.setDouble(4, item.getPrice());
            ps.setDate(5, Date.valueOf(item.getDate()));
            ps.setInt(6, item.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete
    public void deleteItem(Item item) {
        String sql = "DELETE FROM Items WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getId());
            ps.executeUpdate();
            items.remove(item);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Item> getItemsList() {
        return items;
    }
}
