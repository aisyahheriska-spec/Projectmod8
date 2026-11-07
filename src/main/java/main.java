/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aisya
 */
import controller.pemasukanController;
import model.PemasukanDAO;
import model.PemasukanRepository;
import view.pemasukanView;

import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            pemasukanView view = new pemasukanView();

            PemasukanRepository repository = new PemasukanDAO();

            new pemasukanController(view, repository);

            view.setVisible(true);
        });
    }
}
