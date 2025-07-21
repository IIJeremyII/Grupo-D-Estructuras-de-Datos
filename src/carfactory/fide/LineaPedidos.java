/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author AMD
 */
public class LineaPedidos {

    private ListaVehiculos lista;
    //15 porque los pedidos son 15
    private int capacidad = 15;

    public LineaPedidos() {
        generarPedidos();
    }

    //Similar a cinta transportadora crear materiales
    public Vehiculo generarPedido() {
        int aleatorio = (int) (Math.random() * 4);
        switch (aleatorio) {
            case 0:
                return new Vehiculo("Sedan de Lujo", 5000);
            case 1:
                return new Vehiculo("Pick-up de alta gama", 12000);
            case 2:
                return new Vehiculo("Maquinaria de alta gama para trabajos pesados", 17500);
            case 3:
                return new Vehiculo("Superauto Deportivo", 20000);
            default:
                return new Vehiculo("Error", 0);
        }
    }

    public void generarPedidos() {
        lista = new ListaVehiculos();

        for (int i = 0; i < capacidad; i++) {
            Vehiculo pedido = generarPedido();
            lista.agregar(pedido);
        }
    }

    public Vehiculo obtenerSiguientePedido() {
        if (lista.getCabeza() == null) {
            return null;
        }

        Vehiculo pedido = lista.getCabeza().vehiculo;
        lista.setCabeza(lista.getCabeza().siguiente);
        return pedido;
    }

    public boolean hayPedidos() {
        return lista.getCabeza() != null;
    }

    public ListaVehiculos getLista() {
        return lista;
    }

}
