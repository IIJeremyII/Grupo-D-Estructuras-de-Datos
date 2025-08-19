/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jerse
 */
public class Basurero {

    private Basurero() {
    }

    /**
     * Aplica pérdida al desechar un material. Devuelve la pérdida aplicada.
     */
    public static double desechar(Material m, Fabrica fabrica) {
        double perdida = m.getValor() * 0.6;
        fabrica.agregarDinero(-perdida);
        return perdida;
    }
}//Fin de la clase
