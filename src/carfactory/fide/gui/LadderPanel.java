/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 *
 * @author jerse
 */
public class LadderPanel extends JPanel {
    
    private int indiceActivo;      // 0..2 para fabricas; 3 cuando victoria
    private boolean victoria;
    private String[] etiquetas = new String[] {
            "Empleado",
            "Gerente regional",
            "Gerente de produccion",
            "Gerente general"
    };

    public LadderPanel() {
        setBorder(titulo("Ascenso en la empresa"));
        setBackground(new Color(250, 250, 250));
        setPreferredSize(new Dimension(230, 0));
        setIndiceDesdeNivel(1, false);
    }

    private TitledBorder titulo(String t) {
        TitledBorder b = BorderFactory.createTitledBorder(
                new LineBorder(Color.DARK_GRAY, 1), t);
        b.setTitleFont(new Font("SansSerif", Font.BOLD, 16));
        return b;
    }

    /** nivel: 1..3; victoria true -> activa el 4to peldaño (Gerente general). */
    public void setIndiceDesdeNivel(int nivel, boolean victoria) {
        if (victoria) {
            this.indiceActivo = 3;
            this.victoria = true;
        } else {
            int idx = (nivel <= 1) ? 0 : (nivel == 2 ? 1 : 2);
            this.indiceActivo = idx;
            this.victoria = false;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int margen = 12;
        int altoCaja = 68;
        int espacio = 24;

        int total = etiquetas.length;
        int altoTotal = total * altoCaja + (total - 1) * espacio;
        int y0 = Math.max(margen, (h - altoTotal) / 2);

        int xCaja = margen + 10;
        int anchoCaja = w - xCaja - margen - 10;

        // columna/llave vertical
        int xLlave = xCaja - 10;
        g2.setStroke(new BasicStroke(3f));
        g2.setColor(new Color(120, 120, 120));
        g2.drawLine(xLlave, y0 - 14, xLlave, y0 + altoTotal + 14);

        for (int i = 0; i < total; i++) {
            int y = y0 + i * (altoCaja + espacio);

            // “bracito” a la caja (llave)
            g2.drawLine(xLlave, y + altoCaja / 2, xCaja - 4, y + altoCaja / 2);

            // caja
            boolean activo = (i == indiceActivo);
            Color borde = activo ? new Color(46, 139, 87) : new Color(150, 150, 150);
            Color fondo = activo ? new Color(225, 245, 231) : new Color(245, 245, 245);

            g2.setColor(fondo);
            g2.fillRoundRect(xCaja, y, anchoCaja, altoCaja, 16, 16);
            g2.setColor(borde);
            g2.setStroke(new BasicStroke(2f));
            g2.drawRoundRect(xCaja, y, anchoCaja, altoCaja, 16, 16);

            // titulo/etiqueta
            g2.setFont(new Font("SansSerif", activo ? Font.BOLD : Font.PLAIN, 14));
            g2.setColor(new Color(40, 40, 40));
            String texto = etiquetas[i];
            g2.drawString(texto, xCaja + 12, y + 26);

            // subtitulo
            String sub = (i == 0) ? "Fabrica 1"
                        : (i == 1) ? "Fabrica 2"
                        : (i == 2) ? "Fabrica 3"
                        : "Victoria";
            g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g2.setColor(new Color(90, 90, 90));
            g2.drawString(sub, xCaja + 12, y + 46);

            // insignia de activo
            if (activo) {
                g2.setFont(new Font("SansSerif", Font.BOLD, 12));
                g2.setColor(new Color(46, 139, 87));
                g2.drawString("✓", xCaja + anchoCaja - 18, y + 22);
            }
        }

        g2.dispose();
    }
    
}
