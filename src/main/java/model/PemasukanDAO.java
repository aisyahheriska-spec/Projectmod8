/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aisya
 */
public class PemasukanDAO implements PemasukanRepository {

    private final Database database;

    public PemasukanDAO() {
        this.database = new Database();
    }

    @Override
    public List<Pemasukan> getAll() throws SQLException {

        List<Pemasukan> daftarPemasukan = new ArrayList<>();

        String sql = "SELECT * FROM tabel_pemasukan "
                + "ORDER BY id_pemasukan ASC";

        try (Connection conn = database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pemasukan p = new Pemasukan(
                        rs.getInt("id_pemasukan"),
                        rs.getString("nama_barang"),
                        rs.getInt("jumlah"),
                        rs.getInt("harga"),
                        rs.getString("metode_pembayaran")
                );

                daftarPemasukan.add(p);
            }
        }

        return daftarPemasukan;
    }

    @Override
    public boolean save(Pemasukan Pemasukan) throws SQLException {

        String sql = "INSERT INTO tabel_pemasukan "
                + "(nama_barang, jumlah, harga, metode_pembayaran) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Pemasukan.getNamaBarang());
            ps.setInt(2, Pemasukan.getJumlah());
            ps.setInt(3, Pemasukan.getHarga());
            ps.setString(4, Pemasukan.getMetodePembayaran());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int idPemasukan) throws SQLException {

        String sql = "DELETE FROM tabel_pemasukan "
                + "WHERE id_pemasukan = ?";

        try (Connection conn = database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPemasukan);

            return ps.executeUpdate() > 0;
        }
    }
}
