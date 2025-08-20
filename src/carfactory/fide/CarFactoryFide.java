/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carfactory.fide;
import javax.swing.SwingUtilities;
import carfactory.fide.gui.JuegoFrame;

/**
 *
 * @author jerse
 */

public class CarFactoryFide {

    public static void main(String[] args) {//INICIO DEL MAIN
        SwingUtilities.invokeLater(()
                -> {
            JuegoFrame frame = new JuegoFrame();
            frame.setVisible(true);
        }
        );

    }//FIN DEL MAIN

}
