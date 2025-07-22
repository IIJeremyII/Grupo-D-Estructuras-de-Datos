/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class lineaProduccion {//Inicio de clase

    private Vehiculo vehiculo;

    public lineaProduccion() {
        this.vehiculo = null;
    }

    public boolean estaLibre() {
        return vehiculo == null;
    }

    public boolean iniciarConstruccion(String tipo) {
        if (!estaLibre()) {
            return false;
        }

        switch (tipo) {
            case "Sedan de Lujo":
                vehiculo = new Vehiculo("Sedan de Lujo", 5000);
                break;
            case "Pick-up de alta gama":
                vehiculo = new Vehiculo("Pick-up de alta gama", 12000);
                break;
            case "Maquinaria de alta gama para trabajos pesados":
                vehiculo = new Vehiculo("Maquinaria de alta gama para trabajos pesados", 17500);
                break;
            case "Superauto Deportivo":
                vehiculo = new Vehiculo("Superauto Deportivo", 20000);
                break;    
            default:
                return false;
        }
        return true;
    }

    public boolean agregarMaterial(material m) {
        if (vehiculo == null) {
            return false;
        }

        boolean usado = vehiculo.recibirMaterial(m);
        if (vehiculo.estaCompleto()) {
            System.out.println("Vehiculo " + vehiculo.getTipo() + " completado. Ganancia: $" + vehiculo.getGanancia());
            vehiculo = null;
        }
        return usado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
} //Fin de la clase
