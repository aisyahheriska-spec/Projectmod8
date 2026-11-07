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
public class Pemasukan {

    private int idPemasukan;
    private String namaBarang;
    private int jumlah;
    private int harga;
    private String metodePembayaran;

    public Pemasukan(String namaBarang, int jumlah, int harga, String metodePembayaran) {
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.harga = harga;
        this.metodePembayaran = metodePembayaran;
    }

    public Pemasukan(int idPemasukan, String namaBarang, int jumlah, int harga, String metodePembayaran) {
        this.idPemasukan = idPemasukan;
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
}
