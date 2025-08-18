/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class ListaLineas {
    private NodoLinea cabeza;
    private int tamano;

    public void add(LineaProduccion lp) {
        NodoLinea n = new NodoLinea(lp);
        if (cabeza == null) {
            cabeza = n;
        } else {
            NodoLinea a = cabeza;
            while (a.siguiente != null) {
                a = a.siguiente;
            }
            a.siguiente = n;
        }
        tamano++;
    }

    public LineaProduccion get(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("indice");
        }
        NodoLinea a = cabeza;
        for (int i = 0; i < indice; i++) {
            a = a.siguiente;
        }
        return a.dato;
    }

    public int tamano() {
        return tamano;
    }

}
