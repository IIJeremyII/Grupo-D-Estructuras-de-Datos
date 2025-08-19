/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

import carfactory.fide.backend.Basurero;

/**
 *
 * @author jerse
 */
public class Fabrica {

    private int numeroFabrica;
    private double objetivoDinero;
    private double dineroActual;

    private CintaTransportadora cinta;
    private LineaPedidos lineaPedidos;
    private ZonaConstruccion zonaConstruccion;

    private ListaMateriales inventario; // materiales permitidos de esta fábrica
    private PRNG rng;

    public Fabrica(int numeroFabrica, long semilla) {
        this.numeroFabrica = numeroFabrica;
        this.objetivoDinero = ReglasFabrica.objetivoDinero(numeroFabrica);
        this.dineroActual = 0;
        this.rng = new PRNG(semilla);

        // Catálogo y pedidos (15) válidos para esta fábrica
        ListaVehiculos catalogo = (numeroFabrica == 1) ? CatalogoVehiculos.fabrica1()
                : (numeroFabrica == 2) ? CatalogoVehiculos.fabrica2()
                        : CatalogoVehiculos.fabrica3();

        int nPedidos = ReglasFabrica.pedidosIniciales(numeroFabrica);
        ListaVehiculos pedidosGenerados = new ListaVehiculos();
        for (int i = 0; i < nPedidos; i++) {
            int idx = rng.nextInt(catalogo.tamano());
            pedidosGenerados.add(catalogo.get(idx));
        }
        this.lineaPedidos = new LineaPedidos(pedidosGenerados);

        // Inventario permitido según fábrica
        this.inventario = (numeroFabrica == 1) ? MaterialesPermitidos.fabrica1()
                : (numeroFabrica == 2) ? MaterialesPermitidos.fabrica2()
                        : MaterialesPermitidos.fabrica3();

        // Cinta con slots según fábrica + autorellenar inicial
        int capacidad = ReglasFabrica.slotsCinta(numeroFabrica);
        this.cinta = new CintaTransportadora(capacidad);
        this.cinta.autorellenar(inventario, rng);

        // 3 líneas fijas
        this.zonaConstruccion = new ZonaConstruccion();
    }

    // --------- Interacción de la GUI (acciones típicas) ---------
    /**
     * Toma un pedido visible y lo coloca en la línea indicada (si está libre).
     */
    public boolean colocarPedidoEnLinea(int indicePedidoVisible, int indiceLinea) {
        LineaProduccion lp = zonaConstruccion.getLineas().get(indiceLinea);
        if (lp.tieneVehiculo()) {
            return false;
        }
        Vehiculo v = lineaPedidos.tomarPedidoVisible(indicePedidoVisible);
        lp.iniciarPedido(v);
        return true;
    }

    /**
     * Intenta colocar un material de la cinta en la línea. Retorna true si el
     * material es correcto y se colocó.
     */
    public boolean intentarColocarMaterial(int indiceMaterialCinta, int indiceLinea) {
        LineaProduccion lp = zonaConstruccion.getLineas().get(indiceLinea);
        if (!lp.tieneVehiculo()) {
            return false;
        }

        Material candidato = cinta.getMateriales().get(indiceMaterialCinta);
        if (!lp.puedeAceptarMaterial(candidato)) {
            return false; // "Material incorrecto"
        }
        // Ahora sí removemos de la cinta y colocamos
        Material tomado = cinta.tomarMaterial(indiceMaterialCinta);
        lp.colocarMaterial(tomado);

        // Autorellenar si corresponde
        cinta.autorellenar(inventario, rng);
        return true;
    }

    /**
     * Si la línea completó el vehículo, lo finaliza y suma la ganancia.
     * Devuelve la ganancia sumada (0 si no estaba completo).
     */
    public double intentarFinalizarLinea(int indiceLinea) {
        LineaProduccion lp = zonaConstruccion.getLineas().get(indiceLinea);
        if (!lp.estaCompleto()) {
            return 0;
        }
        Vehiculo terminado = lp.finalizar();
        double g = terminado.getGananciaUnidad();
        agregarDinero(g);
        return g;
    }

    /**
     * Desecha un material de la cinta (aplica pérdida) y autorellena si hace
     * falta.
     */
    public double desecharMaterialDeCinta(int indiceMaterialCinta) {
        Material m = cinta.tomarMaterial(indiceMaterialCinta);
        double perdida = Basurero.desechar(m, this);
        cinta.autorellenar(inventario, rng);
        return perdida;
    }

    // --------- Utilidades / estado ---------
    public boolean objetivoAlcanzado() {
        return dineroActual >= objetivoDinero;
    }

    public int getNumeroFabrica() {
        return numeroFabrica;
    }

    public double getObjetivoDinero() {
        return objetivoDinero;
    }

    public double getDineroActual() {
        return dineroActual;
    }

    public void agregarDinero(double delta) {
        dineroActual += delta;
    }

    public CintaTransportadora getCinta() {
        return cinta;
    }

    public LineaPedidos getLineaPedidos() {
        return lineaPedidos;
    }

    public ZonaConstruccion getZonaConstruccion() {
        return zonaConstruccion;
    }

    public ListaMateriales getInventario() {
        return inventario;
    }

    public PRNG getRng() {
        return rng;
    }
}//Fin de la clase

