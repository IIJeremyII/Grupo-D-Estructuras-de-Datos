/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carfactory.fide;

import javax.swing.SwingUtilities;

/**
 *
 * @author jerse
 */
public class CarFactoryFide {

    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrameOne frame1 = new JFrameOne();
            }
        });
    }//FIN DEL MAIN

}
