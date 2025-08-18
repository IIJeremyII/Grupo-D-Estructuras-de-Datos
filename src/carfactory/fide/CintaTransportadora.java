/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class CintaTransportadora {
    
    private ListaMateriales materiales;
    private int capacidad;

    public CintaTransportadora(int capacidad) {
        this.materiales = new ListaMateriales();
        this.capacidad = capacidad;
    }

    public ListaMateriales getMateriales() {
        return materiales;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void agregarMaterial(Material m) {
        if (materiales.tamano() < capacidad) {
            materiales.add(m);
        }
    }

    /**
     * Quita y devuelve el material en índice dado.
     */
    public Material tomarMaterial(int indice) {
        return materiales.remove(indice);
    }

    /**
     * Autorellena desde inventario si quedan ≤3, hasta la capacidad.
     */
    public void autorellenar(ListaMateriales inventario, PRNG rng) {
        if (materiales.tamano() <= 3) {
            while (materiales.tamano() < capacidad) {
                int idx = rng.nextInt(inventario.tamano());
                materiales.add(inventario.get(idx));
            }
        }
    }
}//FIN DE LA CLASE

