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
                    String namaBarang = view.getTextNamaBarang().getText();
                    int jumlah = Integer.parseInt(view.getTextJumlah().getText());
                    int harga = Integer.parseInt(view.getTextHarga().getText());
                    String metodePembayaran = view.getTextMetodePembayaran().getText();

                    if (namaBarang.isEmpty() || metodePembayaran.isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Semua data harus diisi!");
                        return;
                    }

                    new pemasukan(namaBarang, jumlah, harga, metodePembayaran).save();

                    JOptionPane.showMessageDialog(view, "Data pemasukan berhasil disimpan!");

                    loadTable();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Jumlah dan harga harus berupa angka!");
                }
            }
        });
        view.addHapusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    int idPemasukan = (int) view.getTableModel().getValueAt(selectedRow, 0);
                    pemasukan.delete(idPemasukan);
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(view, "Pilih data yang ingin dihapus!");
                }
            }
        });
    }

    private void loadTable() {
        DefaultTableModel tableModel = view.getTableModel();
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
