/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

import javax.swing.JFrame;

/**
 *
 * @author jeremy.segura
 */
public class JFrameOne extends JFrame {
    
    public JFrameOne(){
        initialize();
    }
    
    private void initialize() {
        setTitle("CarFactory-Fide");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,700);
        setLocationRelativeTo(null);
        setVisible(true); 
    } 
    
}
