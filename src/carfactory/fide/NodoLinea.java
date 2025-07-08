/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jeremy.segura
 */
public class NodoLinea {

    lineaProduccion linea;
    NodoLinea siguiente;

    public NodoLinea(lineaProduccion linea) {
        this.linea = linea;
        this.siguiente = null;
    }
}
