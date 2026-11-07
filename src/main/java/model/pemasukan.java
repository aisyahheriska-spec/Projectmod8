/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aisya
 */
public class pemasukan {

    private int idPemasukan;
    private String namaBarang;
    private int jumlah;
    private int harga;
    private String metodePembayaran;

    public pemasukan(String namaBarang, int jumlah, int harga, String metodePembayaran) {
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.harga = harga;
        this.metodePembayaran = metodePembayaran;
    }

    public int getIdPemasukan() {
        return idPemasukan;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public static List<pemasukan> getAllPemasukan() {
        List<pemasukan> daftarPemasukan = new ArrayList<>();
        Database db = new Database();
        db.connect();

        try {
            ResultSet rs = db.view("SELECT * FROM tabel_pemasukan ORDER BY id_pemasukan ASC");
            if (rs != null) {
                while (rs.next()) {

                    pemasukan p = new pemasukan(rs.getString("nama_barang"), rs.getInt("jumlah"),
                            rs.getInt("harga"), rs.getString("metode_pembayaran")
                    );

                    p.idPemasukan = rs.getInt("id_pemasukan");
                    daftarPemasukan.add(p);
                }

                rs.close();
            }

        } catch (SQLException e) {
            System.out.println("Kesalahan membaca data: " + e.getMessage());

        } finally {
            db.disconnect();
        }

        return daftarPemasukan;
    }

    public boolean save() {
        Database db = new Database();
        db.connect();

        try {
            return db.query(
                    "INSERT INTO tabel_pemasukan "
                    + "(nama_barang, jumlah, harga, metode_pembayaran) "
                    + "VALUES (?, ?, ?, ?)",
                    namaBarang, jumlah, harga, metodePembayaran
            );

        } finally {
            db.disconnect();
        }
    }

    public static void delete(int idPemasukan) {
        Database db = new Database();
        db.connect();

        try {
            db.query("DELETE FROM tabel_pemasukan WHERE id_pemasukan = ?", idPemasukan);

            System.out.println("Transaksi berhasil dihapus!");

        } finally {
            db.disconnect();
        }
    }
}
