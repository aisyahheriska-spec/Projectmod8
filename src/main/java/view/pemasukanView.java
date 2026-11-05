/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author aisya
 */
public class pemasukanView extends JFrame {

    private final JTextField textnamaBarang, textjumlah, textHarga, textmetodePembayaran;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JButton btnSimpan, btnHapus;

    public pemasukanView() {
        setTitle("Pencatatan Pemasukan Transaksi Penjualan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama Barang:"));
        textnamaBarang = new JTextField();
        panelForm.add(textnamaBarang);

        panelForm.add(new JLabel("Jumlah:"));
        textjumlah = new JTextField();
        panelForm.add(textjumlah);

        panelForm.add(new JLabel("Harga:"));
        textHarga = new JTextField();
        panelForm.add(textHarga);

        panelForm.add(new JLabel("Metode Pembayaran:"));
        textmetodePembayaran = new JTextField();
        panelForm.add(textmetodePembayaran);

        btnSimpan = new JButton("Simpan");
        btnHapus = new JButton("Hapus");

        panelForm.add(btnSimpan);
        panelForm.add(btnHapus);

        tableModel = new DefaultTableModel(new String[]{"Nama Barang", "Jumlah", "Harga", "Metode Pembayaran"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public JTextField getTextNamaBarang() {
        return textnamaBarang;
    }

    public JTextField getTextJumlah() {
        return textjumlah;
    }

    public JTextField getTextHarga() {
        return textHarga;
    }

    public JTextField getTextMetodePembayaran() {
        return textmetodePembayaran;
    }

    public JButton getBtnSimpan() {
        return btnSimpan;
    }

    public JButton getBtnHapus() {
        return btnHapus;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void addSimpanListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void addHapusListener(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }

}
