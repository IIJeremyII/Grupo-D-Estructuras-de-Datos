/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class cintaTransportadora {// Inicio de la clase

    private ListaMateriales material;
    private int capacidad;

    public cintaTransportadora(int capacidad) {
        this.capacidad = capacidad;
        this.material = new ListaMateriales();
        rellenarCinta();
    }

    public void rellenarCinta() {
        while (material.getTamano() < capacidad) {
            material.agregar(generarMaterial());
        }
    }

    public void actualizarCinta() {
        if (material.getTamano() < capacidad) {
            rellenarCinta();
        }
    }

    //Agregue los otros materiales
    private material generarMaterial() {
        int aleatorio = (int) (Math.random() * 6);
        switch (aleatorio) {
            case 0:
                return new material("Motor de lujo", 500);
            case 1:
                return new material("Motor especial deportivo", 2000);
            case 2:
                return new material("Carroceria", 500);
            case 3:
                return new material("Carroceria especial", 800);
            case 4:
                return new material("Llantas deportivas", 2000);
            case 5:
                return new material("Llantas unicas de trabajo", 800);
            default:
                return new material("Error", 0);
        }
    }

    public ListaMateriales getMateriales() {
        return material;
    }

    public void eliminarMaterial(material m) {
        material.eliminarPrimero();
        actualizarCinta();
    }

    public void reiniciar() {
        material.limpiar();
        rellenarCinta();
    }

}//Fin de la clase
