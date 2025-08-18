/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class ListaMateriales {
    
    private NodoMaterial cabeza;
    private int tamano;

    public void add(Material material) {
        NodoMaterial n = new NodoMaterial(material);
        if (cabeza == null) {
            cabeza = n;
        } else {
            NodoMaterial a = cabeza;
            while (a.siguiente != null) {
                a = a.siguiente;
            }
            a.siguiente = n;
        }
        tamano++;
    }

    public Material get(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("indice");
        }
        NodoMaterial a = cabeza;
        for (int i = 0; i < indice; i++) {
            a = a.siguiente;
        }
        return a.dato;
    }


    public Material remove(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("indice");
        }
        NodoMaterial a = cabeza, prev = null;
        for (int i = 0; i < indice; i++) {
            prev = a;
            a = a.siguiente;
        }
        if (prev == null) {
            cabeza = a.siguiente;
        } else {
            prev.siguiente = a.siguiente;
        }
        tamano--;
        return a.dato;
    }

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public void limpiar() {
        cabeza = null;
        tamano = 0;
    }

    public int contarPorNombre(String nombre) {
        int c = 0;
        NodoMaterial a = cabeza;
        while (a != null) {
            if (a.dato.getNombre().equals(nombre)) {
                c++;
            }
            a = a.siguiente;
        }
        return c;
    }
    
}//Fin de la clase
