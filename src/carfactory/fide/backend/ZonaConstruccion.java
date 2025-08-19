/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jerse
 */
public class ZonaConstruccion {
    private ListaLineas lineas;

    public ZonaConstruccion() {
        lineas = new ListaLineas();
        lineas.add(new LineaProduccion());
        lineas.add(new LineaProduccion());
        lineas.add(new LineaProduccion());
    }

    public ListaLineas getLineas() {
        return lineas;
    }

    /**
     * Devuelve índice de una línea libre o -1 si todas están ocupadas.
     */
    public int indiceLineaLibre() {
        for (int i = 0; i < lineas.tamano(); i++) {
            if (!lineas.get(i).tieneVehiculo()) {
                return i;
            }
        }
        return -1;
    }
}
