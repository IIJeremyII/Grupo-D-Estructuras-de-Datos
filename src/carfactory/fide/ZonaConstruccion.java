/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jeremy.segura
 */
public class ZonaConstruccion {
    private NodoLinea cabeza;
    private int cantidad;

    public ZonaConstruccion() {
        cabeza = null;
        cantidad = 0;

        for (int i = 0; i < 3; i++) {
            agregarLinea();
        }
    }

    private void agregarLinea() {
        NodoLinea nueva = new NodoLinea(new lineaProduccion());
        if (cabeza == null) {
            cabeza = nueva;
        } else {
            NodoLinea actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nueva;
        }
        cantidad++;
    }

    public boolean iniciarVehiculo(String tipo, int index) {
        lineaProduccion linea = getLinea(index);
        if (linea != null && linea.estaLibre()) {
            return linea.iniciarConstruccion(tipo);
        }
        return false;
    }

    public boolean usarMaterialEnLinea(int index, material m) {
        lineaProduccion linea = getLinea(index);
        if (linea != null) {
            return linea.agregarMaterial(m);
        }
        return false;
    }

    public lineaProduccion getLinea(int index) {
        NodoLinea actual = cabeza;
        int contador = 0;
        while (actual != null) {
            if (contador == index) {
                return actual.linea;
            }
            actual = actual.siguiente;
            contador++;
        }
        return null;
    }
    
}
    