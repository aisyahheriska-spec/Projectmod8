/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aisya
 */
import controller.pemasukanController;
import view.pemasukanView;

public class main {

    public static void main(String[] args) {
        pemasukanView view = new pemasukanView();
        new pemasukanController(view);
    }

}
