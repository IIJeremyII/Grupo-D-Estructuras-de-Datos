/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jerse
 */
public class ReglasFabrica {

    private ReglasFabrica() {
    }

    public static int slotsCinta(int numeroFabrica) {
        if (numeroFabrica == 1) {
            return 3;
        }
        if (numeroFabrica == 2) {
            return 4;
        }
        return 5; // F3
    }

    public static double objetivoDinero(int numeroFabrica) {
        if (numeroFabrica == 1) {
            return 90000;
        }
        if (numeroFabrica == 2) {
            return 125000;
        }
        return 275000; // F3
    }
    
    public static int pedidosIniciales(int numeroFabrica) {
        if (numeroFabrica == 1) {
            return 18;  // 90k / 5k
        }
        if (numeroFabrica == 2) {
            return 25;  // 125k / 5k
        }
        return 23;
    }
}
