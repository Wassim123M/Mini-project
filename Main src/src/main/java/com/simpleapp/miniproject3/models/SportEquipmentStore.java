package com.simpleapp.miniproject3.models;

import com.simpleapp.miniproject3.db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SportEquipmentStore {

    private final ObservableList<SportEquipment> equipments = FXCollections.observableArrayList();

    public SportEquipmentStore() {
        loadFromDatabase();
    }

    public ObservableList<SportEquipment> getEquipments() {
        return equipments;
    }

    // LOAD
    public void loadFromDatabase() {
        equipments.clear();

        String sql = "SELECT * FROM sport_equipment";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SportEquipment se = new SportEquipment(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                equipments.add(se);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ADD
    public void addEquipment(SportEquipment se) {
        String sql = "INSERT INTO sport_equipment (name, type, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, se.getName());
            ps.setString(2, se.getType());
            ps.setInt(3, se.getQuantity());
            ps.setDouble(4, se.getPrice());
            ps.executeUpdate();

            equipments.add(se);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // REMOVE
    public void removeEquipment(SportEquipment se) {
        String sql = "DELETE FROM sport_equipment WHERE name = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, se.getName());
            ps.executeUpdate();

            equipments.remove(se);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
