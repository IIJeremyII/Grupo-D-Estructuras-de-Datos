/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

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
     * Autorellenaba desde inventario si quedan ≤3, hasta la capacidad. sin
     * embargo nos daba un bug que logramos solucionar en la fabrica 3
     */
    public void autorellenar(ListaMateriales inventario, PRNG rng) {
        while (materiales.tamano() < capacidad) {
            int idx = rng.nextInt(inventario.tamano());
            materiales.add(inventario.get(idx));
        }
    }
}//FIN DE LA CLASE

