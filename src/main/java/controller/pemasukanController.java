/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.pemasukan;
import view.pemasukanView;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author aisya
 */
public class pemasukanController {

    private final pemasukanView view;

    public pemasukanController(pemasukanView view) {
        this.view = view;

        loadTable();

        view.addSimpanListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String namaBarang = view.getTextNamaBarang().getText().trim();
                    String jumlahText = view.getTextJumlah().getText().trim();
                    String hargaText = view.getTextHarga().getText().trim();
                    String metodePembayaran = view.getTextMetodePembayaran().getText().trim();

                    if (namaBarang.isEmpty()
                            || jumlahText.isEmpty()
                            || hargaText.isEmpty()
                            || metodePembayaran.isEmpty()) {

                        JOptionPane.showMessageDialog(view, "Semua data harus diisi!");
                        return;
                    }

                    int jumlah = Integer.parseInt(jumlahText);
                    int harga = Integer.parseInt(hargaText);

                    if (jumlah <= 0 || harga < 0) {
                        JOptionPane.showMessageDialog(view,
                                "Jumlah harus lebih dari 0 dan harga tidak boleh negatif!"
                        );
                        return;
                    }

                    pemasukan p = new pemasukan(namaBarang, jumlah, harga, metodePembayaran);
                    if (p.save()) {

                        JOptionPane.showMessageDialog(view, "Data pemasukan berhasil disimpan!");

                        loadTable();

                        view.getTextNamaBarang().setText("");
                        view.getTextJumlah().setText("");
                        view.getTextHarga().setText("");
                        view.getTextMetodePembayaran().setText("");

                    } else {
                        JOptionPane.showMessageDialog(view, "Gagal menyimpan data pemasukan!");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Jumlah dan harga harus berupa angka!");
                }
            }
        });

        view.addHapusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow
                        = view.getTable().getSelectedRow();

                if (selectedRow != -1) {

                    int idPemasukan = (int) view.getTableModel().getValueAt(
                            selectedRow, 0
                    );

                    int konfirmasi = JOptionPane.showConfirmDialog(view,
                            "Apakah kamu yakin ingin menghapus data ini?",
                            "Konfirmasi Hapus",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (konfirmasi == JOptionPane.YES_OPTION) {

                        pemasukan.delete(idPemasukan);

                        loadTable();

                        JOptionPane.showMessageDialog(view, "Permintaan penghapusan telah diproses.");
                    }

                } else {
                    JOptionPane.showMessageDialog(view, "Pilih data yang ingin dihapus!");
                }
            }
        });
    }

    private void loadTable() {
        DefaultTableModel tableModel
                = view.getTableModel();

        tableModel.setRowCount(0);

        for (pemasukan p : pemasukan.getAllPemasukan()) {

            tableModel.addRow(new Object[]{
                p.getIdPemasukan(),
                p.getNamaBarang(),
                p.getJumlah(),
                p.getHarga(),
                p.getMetodePembayaran()
            });
        }
    }
}
