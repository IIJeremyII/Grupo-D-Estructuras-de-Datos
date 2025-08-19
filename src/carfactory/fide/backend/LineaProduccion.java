/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jerse
 */
public class LineaProduccion {//Inicio de clase
    private Vehiculo vehiculoEnProceso;
    private ListaMateriales materialesColocados;

    public LineaProduccion() {
        this.vehiculoEnProceso = null;
        this.materialesColocados = new ListaMateriales();
    }

    public boolean tieneVehiculo() {
        return vehiculoEnProceso != null;
    }

    public Vehiculo getVehiculoEnProceso() {
        return vehiculoEnProceso;
    }

    public ListaMateriales getMaterialesColocados() {
        return materialesColocados;
    }

    public void iniciarPedido(Vehiculo v) {
        if (tieneVehiculo()) {
            throw new IllegalStateException("Linea ocupada");
        }
        this.vehiculoEnProceso = v;
        this.materialesColocados.limpiar();
    }

    
    public boolean puedeAceptarMaterial(Material m) {
        if (!tieneVehiculo()) {
            return false;
        }
        String nombre = m.getNombre();
        int requeridos = vehiculoEnProceso.getMaterialesRequeridos().contarPorNombre(nombre);
        if (requeridos == 0) {
            return false; // no es parte del vehiculo
        }
        int yaColocados = materialesColocados.contarPorNombre(nombre);
        return yaColocados < requeridos;
    }

   
    public void colocarMaterial(Material m) {
        if (!puedeAceptarMaterial(m)) {
            throw new IllegalArgumentException("Material incorrecto o excedido");
        }
        materialesColocados.add(m);
    }

  
    public boolean estaCompleto() {
        if (!tieneVehiculo()) {
            return false;
        }
        ListaMateriales req = vehiculoEnProceso.getMaterialesRequeridos();
        // Para cada tipo requerido, verificar conteos
        for (int i = 0; i < req.tamano(); i++) {
            String nombre = req.get(i).getNombre();
            int reqCnt = req.contarPorNombre(nombre);
            int colCnt = materialesColocados.contarPorNombre(nombre);
            if (colCnt < reqCnt) {
                return false;
            }
        }
        return true;
    }


    public Vehiculo finalizar() {
        if (!estaCompleto()) {
            throw new IllegalStateException("Materiales incompletos");
        }
        Vehiculo v = vehiculoEnProceso;
        vehiculoEnProceso = null;
        materialesColocados.limpiar();
        return v;
    }
} //Fin de la clase

