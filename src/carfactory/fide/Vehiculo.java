/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class Vehiculo {//Inicio

    private String tipo;
    private int ganancia;
    private ListaMateriales materialesNecesarios;

    public Vehiculo(String tipo, int ganancia) {
        this.tipo = tipo;
        this.ganancia = ganancia;
        this.materialesNecesarios = new ListaMateriales();
        
        
        switch (tipo) {
            case "Sedan de Lujo":
                materialesNecesarios.agregar(new material("Motor de lujo", 500));
                materialesNecesarios.agregar(new material("Carroceria", 500));
                break;
            case "Pick-up de alta gama":
                materialesNecesarios.agregar(new material("Motor de lujo", 500));
                materialesNecesarios.agregar(new material("Carroceria", 500));
                break;
            case "Maquinaria de alta gama para trabajos pesados":
                materialesNecesarios.agregar(new material("Motor especial deportivo", 2000));
                materialesNecesarios.agregar(new material("Carroceria especial", 800));
                materialesNecesarios.agregar(new material("Llantas unicas de trabajo", 800));
                break;
            case "Superauto Deportivo":
                materialesNecesarios.agregar(new material("Motor especial deportivo", 2000));
                materialesNecesarios.agregar(new material("Carroceria", 500));
                materialesNecesarios.agregar(new material("Llantas deportivas", 2000));
                break;
        }
    }

    public boolean recibirMaterial(material m) {
        NodoMaterial actual = materialesNecesarios.getcabeza();
        NodoMaterial anterior = null;

        while (actual != null) {
            if (actual.material.getNombre().equals(m.getNombre())) {
                if (anterior == null) {
                    materialesNecesarios.eliminarPrimero();
                } else {
                    anterior.siguiente = actual.siguiente;
       //             materialesNecesarios.getTamano--;
                }
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean estaCompleto() {
        return materialesNecesarios.getTamano() == 0;
    }

    public int getGanancia() {
        return ganancia;
    }

    public String getTipo() {
        return tipo;
    }

}//Fin 
