/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jerse
 */
public class PRNG {
    
    //Explicacion PRNG se utiliza para generar numeros aleatorios, se le conoce como un generador pseudoaleatorio
    //En este proyecto sirve para generar los pedidos iniciales de cada fabrica es decir elige los vehiculos aleatorios del catalogo valido.
    //Tambien nos sirve para rellenar la cinta transportadora eligiendo materiales aleatorios del inventario permitido para esa fabrica
    //Al no poder usar java.util.random optamos por esta opcion para randomizar estas zonas necesarias

    private long estado;

    public PRNG(long semilla) { // Arranmca con una semilla base
        if (semilla == 0) {
            semilla = 0x9E3779B97F4A7C15L; //Partiendo de esta semilla, va sacando una secuencia de numeros que parecen aleatorios (aunque no lo son)
        }
        this.estado = semilla;
    }

    private long nextLong() { //Avanza a esta parte para generar un numero aleatorio mas grande
        estado = (estado * 6364136223846793005L + 1) & 0xFFFFFFFFFFFFFFFFL;
        return estado;
    }

    public int nextInt(int limite) { // te da un numero entero entre 0 y el limite que seria -1
        if (limite <= 0) {
            throw new IllegalArgumentException("limite <= 0");
        }
        long r = nextLong() >>> 1;
        return (int) (r % limite);
    }
}
