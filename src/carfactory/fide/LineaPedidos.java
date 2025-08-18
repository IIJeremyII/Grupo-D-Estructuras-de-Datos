/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class LineaPedidos {

    private ListaVehiculos visibles;   // máx 3
    private ListaVehiculos pendientes; // cola simple usando nuestra lista

    public LineaPedidos(ListaVehiculos pedidosGenerados) {
        visibles = new ListaVehiculos();
        pendientes = new ListaVehiculos();

        int n = pedidosGenerados.tamano();
        int toVis = (n >= 3) ? 3 : n;
        for (int i = 0; i < toVis; i++) {
            visibles.add(pedidosGenerados.get(i));
        }
        for (int i = 3; i < n; i++) {
            pendientes.add(pedidosGenerados.get(i));
        }
    }

    /**
     * Toma un pedido visible (lo remueve) y repone desde pendientes si hay.
     */
    public Vehiculo tomarPedidoVisible(int indiceVisible) {
        Vehiculo v = visibles.remove(indiceVisible);
        if (!pendientes.estaVacia()) {
            // traer "frente" de pendientes: posición 0
            Vehiculo siguiente = pendientes.remove(0);
            visibles.add(siguiente);
        }
        return v;
    }

    public ListaVehiculos getVisibles() {
        return visibles;
    }

    public ListaVehiculos getPendientes() {
        return pendientes;
    }
    
}
