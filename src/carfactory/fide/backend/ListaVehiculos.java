/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author AMD
 */


public class ListaVehiculos {
    private NodoVehiculo cabeza;
    private int tamano;

    public void add(Vehiculo vehiculo) {
        NodoVehiculo n = new NodoVehiculo(vehiculo);
        if (cabeza == null) {
            cabeza = n;
        } else {
            NodoVehiculo a = cabeza;
            while (a.siguiente != null) {
                a = a.siguiente;
            }
            a.siguiente = n;
        }
        tamano++;
    }

    public Vehiculo get(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("indice");
        }
        NodoVehiculo a = cabeza;
        for (int i = 0; i < indice; i++) {
            a = a.siguiente;
        }
        return a.dato;
    }

 
    public Vehiculo remove(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("indice");
        }
        NodoVehiculo a = cabeza, prev = null;
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

}//FIN DE LA CLASE

