/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jeremy.segura
 */
public class NodoLinea {

    LineaProduccion dato;
    NodoLinea siguiente;

    NodoLinea(LineaProduccion d) {
        this.dato = d;
    }
}
