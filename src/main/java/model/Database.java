/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

/**
 *
 * @author aisya
 */
public class Database {

    static final String url = "jdbc:mysql://localhost:3306/projectadpl";
    static final String user = "root";
    static final String pass = "Heriska123";

    static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    public void connect() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();

            System.out.println("Koneksi database berhasil!");
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public boolean query(String sql, Object... params) {
        if (conn == null) {
            System.out.println("Koneksi database belum tersedia!");
            return false;
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Kesalahan Query: " + ex.getMessage());
            return false;
        }
    }

    public ResultSet view(String sql) {
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Kesalahan View: " + ex.getMessage());
        }

        return rs;
    }

    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Kesalahan saat menutup koneksi: " + ex.getMessage());
        }
    }
}
