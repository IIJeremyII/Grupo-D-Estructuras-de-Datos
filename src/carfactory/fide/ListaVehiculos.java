/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author AMD
 */

// NOTAS
//Similar a material creamos un nodo y una lista para los vehiculos que tambien vamos a usar para crear la lista de pedidos.
public class ListaVehiculos {

    private NodoVehiculo cabeza;
    private int tamano;

    public ListaVehiculos() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void agregar(Vehiculo v) {
        NodoVehiculo nuevo = new NodoVehiculo(v);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoVehiculo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    public int getTamano() {
        return tamano;
    }

    public NodoVehiculo getCabeza() {
        return cabeza;
    }

    public void limpiar() {
        cabeza = null;
        tamano = 0;
    }

    public void setCabeza(NodoVehiculo cabeza) {
        this.cabeza = cabeza;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    

}
