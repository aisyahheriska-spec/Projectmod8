/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aisya
 */
public interface PemasukanRepository {

    List<Pemasukan> getAll() throws SQLException;

    boolean save(Pemasukan Pemasukan) throws SQLException;

    boolean delete(int idPemasukan) throws SQLException;

}
