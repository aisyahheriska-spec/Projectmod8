/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Pemasukan;
import model.PemasukanRepository;
import view.pemasukanView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author aisya
 */
public class pemasukanController {

    private final pemasukanView view;
    private final PemasukanRepository repository;

    public pemasukanController(
            pemasukanView view,
            PemasukanRepository repository) {

        this.view = view;
        this.repository = repository;

        loadTable();

        view.addSimpanListener(e -> simpanPemasukan());
        view.addHapusListener(e -> hapusPemasukan());
    }

    private void simpanPemasukan() {

        try {
            String namaBarang
                    = view.getTextNamaBarang().getText().trim();

            String jumlahText
                    = view.getTextJumlah().getText().trim();

            String hargaText
                    = view.getTextHarga().getText().trim();

            String metodePembayaran
                    = view.getTextMetodePembayaran().getText().trim();

            // Validasi input kosong
            if (namaBarang.isEmpty()
                    || jumlahText.isEmpty()
                    || hargaText.isEmpty()
                    || metodePembayaran.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "Semua data harus diisi!"
                );
                return;
            }

            int jumlah = Integer.parseInt(jumlahText);
            int harga = Integer.parseInt(hargaText);

            if (jumlah <= 0 || harga < 0) {

                JOptionPane.showMessageDialog(
                        view,
                        "Jumlah harus lebih dari 0 "
                        + "dan harga tidak boleh negatif!"
                );
                return;
            }

            // Membuat objek Pemasukan
            Pemasukan pemasukan = new Pemasukan(
                    namaBarang,
                    jumlah,
                    harga,
                    metodePembayaran
            );

            // Menyimpan melalui Repository
            boolean berhasil = repository.save(pemasukan);
            if (berhasil) {
                JOptionPane.showMessageDialog(view, "Data pemasukan berhasil disimpan!");

                kosongkanForm();
                loadTable();

            } else {
                JOptionPane.showMessageDialog(view, "Data pemasukan gagal disimpan!");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(view, "Jumlah dan harga harus berupa angka!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, "Kesalahan database: " + e.getMessage());
        }
    }

    private void hapusPemasukan() {

        int selectedRow = view.getTable().getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(view, "Pilih data yang ingin dihapus!");
            return;
        }

        int modelRow = view.getTable().convertRowIndexToModel(selectedRow);

        int idPemasukan = Integer.parseInt(
                view.getTableModel()
                        .getValueAt(modelRow, 0).toString()
        );

        int konfirmasi = JOptionPane.showConfirmDialog(
                view,
                "Apakah kamu yakin ingin menghapus data ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean berhasil = repository.delete(idPemasukan);

            if (berhasil) {

                JOptionPane.showMessageDialog(view, "Data pemasukan berhasil dihapus!");

                loadTable();

            } else {

                JOptionPane.showMessageDialog(view, "Data gagal dihapus atau tidak ditemukan!");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, "Kesalahan database: " + e.getMessage());
        }
    }

    private void loadTable() {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);

        try {
            for (Pemasukan p : repository.getAll()) {
                tableModel.addRow(new Object[]{
                    p.getIdPemasukan(),
                    p.getNamaBarang(),
                    p.getJumlah(),
                    p.getHarga(),
                    p.getMetodePembayaran()
                });
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void kosongkanForm() {
        view.getTextNamaBarang().setText("");
        view.getTextJumlah().setText("");
        view.getTextHarga().setText("");
        view.getTextMetodePembayaran().setText("");
    }
}
