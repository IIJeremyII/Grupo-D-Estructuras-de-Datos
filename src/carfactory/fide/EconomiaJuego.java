package carfactory.fide;

public class EconomiaJuego {

    private int ganancia;
    private int perdida;

    public EconomiaJuego() {
        this.ganancia = 0;
        this.perdida = 0;
    }

    public void ganar(int monto) {
        ganancia += monto;
    }

    public void perder(int monto) {
        perdida += monto;
    }

    public int getGanancia() {
        return ganancia;
    }

    public int getPerdida() {
        return perdida;
    }

    public int getGananciaTotal() {
        return ganancia - perdida;
    }
}
