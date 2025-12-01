package com.simpleapp.miniproject3.db;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("✅ Connected successfully: " + (conn != null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
