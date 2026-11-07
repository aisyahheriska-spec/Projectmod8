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

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
