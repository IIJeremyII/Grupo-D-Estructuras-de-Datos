/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class ListaMateriales {//Find

    private NodoMaterial cabeza;
    private int tamano;

    public ListaMateriales() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void agregar(material m) {
        NodoMaterial nuevo = new NodoMaterial(m);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoMaterial actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    public void eliminarPrimero() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
            tamano--;
        }
    }

    public int getTamano() {
        return tamano;
    }

    public NodoMaterial getcabeza() {
        return cabeza;
    }

    public void limpiar() {
        cabeza = null;
        tamano = 0;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

}//Fin de la clase
