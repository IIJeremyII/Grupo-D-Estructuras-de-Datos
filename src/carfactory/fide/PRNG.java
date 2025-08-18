/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class PRNG {

    private long estado;

    public PRNG(long semilla) {
        if (semilla == 0) {
            semilla = 0x9E3779B97F4A7C15L;
        }
        this.estado = semilla;
    }

    private long nextLong() {
        estado = (estado * 6364136223846793005L + 1) & 0xFFFFFFFFFFFFFFFFL;
        return estado;
    }

    public int nextInt(int limite) {
        if (limite <= 0) {
            throw new IllegalArgumentException("limite <= 0");
        }
        long r = nextLong() >>> 1;
        return (int) (r % limite);
    }
}
