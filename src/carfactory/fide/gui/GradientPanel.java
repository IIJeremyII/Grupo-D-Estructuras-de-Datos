/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.gui;

import javax.swing.JPanel;
import java.awt.*;
/**
 *
 * @author jerse
 */
public class GradientPanel extends JPanel {
    private final Color a, b;
    public GradientPanel(Color a, Color b) { this.a = a; this.b = b; setOpaque(false); }
    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(0, 0, a, 0, getHeight(), b));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }
}
