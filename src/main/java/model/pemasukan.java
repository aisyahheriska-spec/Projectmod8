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
        ResultSet rs = db.view("SELECT * FROM tabel_pemasukan");
        try {
            while (rs.next()) {
                daftarPemasukan.add(new pemasukan(rs.getString("Nama Barang"),
                        rs.getInt("jumlah"), rs.getInt("harga"), rs.getString("Metode Pembayaran")));
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan saat membaca data: " + e.getMessage());
        }
        db.disconnect();
        return daftarPemasukan;
    }

    public void save() {
        Database db = new Database();
        db.connect();
        ResultSet rs = db.view("SELECT * FROM tabel_pemasukan WHERE nama_barang='" + namaBarang + "'");
        try {
            if (rs.next()) {
                db.query("UPDATE tabel_pemasukan SET jumlah=" + jumlah + ", harga="
                        + harga + ", metode_pembayaran='" + metodePembayaran + "' WHERE nama_barang='" + namaBarang + "'");
            } else {
                db.query("INSERT INTO tabel_pemasukan (nama_Barang, jumlah, harga, metode_pembayaran, metode_pembayaran) VALUES('"
                        + namaBarang + "'  , '" + jumlah + "'  , " + harga + "  , '" + metodePembayaran + ")");
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
        db.disconnect();
    }

    public static void delete(String namaBarang) {
        Database db = new Database();
        db.connect();
        db.query("DELETE FROM tabel_pemasukan WHERE namaBarang='" + namaBarang + "'");
        db.disconnect();
    }
}
