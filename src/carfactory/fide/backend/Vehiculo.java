/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jerse
 */
public class Vehiculo {//Inicio

    private String nombre;
    private ListaMateriales materialesRequeridos;
    private double gananciaUnidad;

    public Vehiculo(String nombre, ListaMateriales materialesRequeridos, double gananciaUnidad) {
        this.nombre = nombre;
        this.materialesRequeridos = materialesRequeridos;
        this.gananciaUnidad = gananciaUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public ListaMateriales getMaterialesRequeridos() {
        return materialesRequeridos;
    }

    public double getGananciaUnidad() {
        return gananciaUnidad;
    }

    public double getCostoMateriales() {
        double total = 0;
        for (int i = 0; i < materialesRequeridos.tamano(); i++) {
            total += materialesRequeridos.get(i).getValor();
        }
        return total;
    }
    
}//Fin 
