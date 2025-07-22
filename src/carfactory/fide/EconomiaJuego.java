/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

public class EconomiaJuego {

    private int ganancias;
    private int perdidas;

    public EconomiaJuego() {
        this.ganancias = 0;
        this.perdidas = 0;
    }

    public void sumarGanancia(int cantidad) {
        ganancias += cantidad;
    }

    public void sumarPerdida(int cantidad) {
        perdidas += cantidad;
    }

    public int getGanancias() {
        return ganancias;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public int getSaldo() {
        return ganancias - perdidas;
    }

    public String obtenerResumen() {
        return "Ganancias: $" + ganancias +
               " | Pérdidas: $" + perdidas +
               " | Saldo: $" + getSaldo();
    }
}