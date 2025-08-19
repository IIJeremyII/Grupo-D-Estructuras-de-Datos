/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.gui;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author jerse
 */
public class Temas {
    private Temas() {}
    // Paleta (ajústala a tu gusto)
    public static final Color BG        = new Color(245, 247, 250);
    public static final Color PANEL     = new Color(252, 253, 255);
    public static final Color TEXT      = new Color(35, 38, 47);
    public static final Color SUBTEXT   = new Color(92, 101, 117);

    public static final Color ACCENT    = new Color(86, 124, 255);   // azul
    public static final Color ACCENT_2  = new Color(46, 139, 87);    // verde (éxito)
    public static final Color ACCENT_3  = new Color(255, 159, 67);   // naranja (alertas)
    public static final Color HOVER     = new Color(240, 244, 255);

    public static final Color CARD_BORDER      = new Color(210, 218, 235);
    public static final Color CARD_BG          = Color.WHITE;
    public static final Color CARD_BORDER_ACC  = new Color(100, 149, 237); // pedidos
    public static final Color CARD_BORDER_MAT  = new Color(60, 179, 113);  // materiales
    public static final Color BIN_BORDER       = new Color(220, 80, 80);
    public static final Color BIN_BG           = new Color(255, 240, 240);

    // Gradientes (opcionales)
    public static final Color GRAD_TOP_A = new Color(240, 245, 255);
    public static final Color GRAD_TOP_B = new Color(255, 255, 255);

    // Tipografías
    public static final Font TITLE  = new Font("SansSerif", Font.BOLD, 16);
    public static final Font BODY   = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font SMALL  = new Font("SansSerif", Font.PLAIN, 12);
}
