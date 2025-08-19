/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.gui;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;

import carfactory.fide.backend.*;

/**
 *
 * @author jerse
 */
public class JuegoFrame extends JFrame {
    // Estado del juego
    private Fabrica fabrica;
    private int nivelActual;

    // UI: paneles
    private JPanel panelTop;          // Contiene HUB (izq) + panel dinero (der)
    private JPanel panelHubPedidos;   // Pedidos visibles (máx 3)
    private JPanel panelDinero;       // Dinero y objetivo
    private JPanel panelCentro;       // Zona construcción (3 líneas)
    private JPanel panelBasurero;     // A la derecha
    private JPanel panelCinta;        // Abajo
    private JLabel barraEstado;       // Mensajes

    // UI: líneas individuales (3 fijos, sin arreglos ni listas externas)
    private LineaPanel linea0;
    private LineaPanel linea1;
    private LineaPanel linea2;
    
    //Zona de ascensos de la empresa
    private LadderPanel panelAscenso;

  
    private Font fontTitulo = new Font("SansSerif", Font.BOLD, 16);
    private Font fontNormal = new Font("SansSerif", Font.PLAIN, 14);

    public JuegoFrame() {
        super("CarFactory-Fide");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Nivel inicial
        nivelActual = 1;
        iniciarFabrica(nivelActual);

        construirUI();
        refrescarTodo();
    }

    private void iniciarFabrica(int numero) {
        // Semilla cualquiera; no usamos java.util
        long semilla = System.nanoTime();
        this.fabrica = new Fabrica(numero, semilla);
    }

    private void construirUI() {
        // --- TOP (HUB + dinero/objetivo)
        panelTop = new JPanel(new BorderLayout());
        panelTop.setBorder(new EmptyBorder(8, 8, 4, 8));

        panelHubPedidos = new JPanel();
        panelHubPedidos.setLayout(new GridLayout(1, 3, 8, 0));
        panelHubPedidos.setBorder(titulo("HUB de pedidos"));
        panelTop.add(panelHubPedidos, BorderLayout.WEST);

        panelDinero = new JPanel();
        panelDinero.setLayout(new GridLayout(2, 1, 4, 4));
        panelDinero.setBorder(titulo("Estado económico"));
        panelTop.add(panelDinero, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);

        // --- CENTRO (Zona de construcción: 3 líneas)
        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(3, 1, 8, 8));
        panelCentro.setBorder(new EmptyBorder(8, 8, 8, 8));

        linea0 = new LineaPanel(0);
        linea1 = new LineaPanel(1);
        linea2 = new LineaPanel(2);

        panelCentro.add(linea0);
        panelCentro.add(linea1);
        panelCentro.add(linea2);

        add(panelCentro, BorderLayout.CENTER);

        // --- BASURERO (derecha)
        panelBasurero = new JPanel(new BorderLayout());
        panelBasurero.setPreferredSize(new Dimension(220, 0));
        panelBasurero.setBorder(titulo("Basurero (arrastrar materiales aquí)"));
        JLabel basurero = crearEtiquetaGrande("🗑️ Basurero");
        basurero.setHorizontalAlignment(SwingConstants.CENTER);
        basurero.setBorder(new DashedBorder(Color.GRAY));
        basurero.setTransferHandler(new BasureroDropHandler());
        basurero.addMouseListener(new DropCueMouse());
        panelBasurero.add(basurero, BorderLayout.CENTER);

        add(panelBasurero, BorderLayout.EAST);

        panelCinta = new JPanel();
        panelCinta.setBorder(titulo("Cinta transportadora"));

        barraEstado = new JLabel("Listo.");
        barraEstado.setBorder(new EmptyBorder(6, 8, 8, 8));

// contenedor que aloja ambos
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelCinta, BorderLayout.CENTER);
        panelInferior.add(barraEstado, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
        
        
        //ASCENSO A LA IZQUIERDA DEL TODO
        panelAscenso = new LadderPanel();
        add(panelAscenso, BorderLayout.WEST);
    }

    private TitledBorder titulo(String t) {
        TitledBorder b = BorderFactory.createTitledBorder(
                new LineBorder(Color.DARK_GRAY, 1), t);
        b.setTitleFont(fontTitulo);
        return b;
    }

    private JLabel crearEtiquetaGrande(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(fontTitulo);
        l.setOpaque(true);
        l.setBackground(new Color(245, 245, 245));
        l.setBorder(new LineBorder(Color.LIGHT_GRAY));
        return l;
    }

    // -------------------- REFRESCOS --------------------

    private void refrescarTodo() {
        refrescarPedidos();
        refrescarDinero();
        refrescarCinta();
        refrescarLineas();
        panelAscenso.setIndiceDesdeNivel(nivelActual, false);
    }

    private void refrescarPedidos() {
        panelHubPedidos.removeAll();

        ListaVehiculos visibles = fabrica.getLineaPedidos().getVisibles();
        // Máx 3 columnas ya está en layout
        int total = visibles.tamano();
        int columnas = 3;
        int i = 0;
        while (i < columnas) {
            if (i < total) {
                Vehiculo v = visibles.get(i);
                JPanel tarjeta = crearTarjetaPedido(v.getNombre(), i);
                panelHubPedidos.add(tarjeta);
            } else {
                JPanel vacio = new JPanel();
                vacio.setBorder(new LineBorder(new Color(230,230,230)));
                panelHubPedidos.add(vacio);
            }
            i++;
        }
        panelHubPedidos.revalidate();
        panelHubPedidos.repaint();
    }

    private JPanel crearTarjetaPedido(String nombreVehiculo, int indiceVisible) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(100, 149, 237)),
                new EmptyBorder(8, 8, 8, 8)
        ));
        JLabel titulo = new JLabel("Pedido");
        titulo.setFont(fontNormal);
        JLabel nombre = new JLabel(nombreVehiculo);
        nombre.setFont(fontTitulo);

        card.add(titulo, BorderLayout.NORTH);
        card.add(nombre, BorderLayout.CENTER);

        // Hacer draggable como "PEDIDO:indice"
        String payload = "PEDIDO:" + indiceVisible;
        DraggableSupport.hacerArrastrable(card, payload);

        return card;
    }

    private void refrescarDinero() {
        panelDinero.removeAll();

        JLabel lblNivel = new JLabel("Fábrica: " + nivelActual);
        lblNivel.setFont(fontNormal);

        JLabel lblDinero = new JLabel("Dinero: $" + (long)fabrica.getDineroActual());
        lblDinero.setFont(fontNormal);

        JLabel lblObjetivo = new JLabel("Objetivo: $" + (long)fabrica.getObjetivoDinero());
        lblObjetivo.setFont(fontNormal);

        JPanel arriba = new JPanel(new GridLayout(1, 2, 6, 6));
        arriba.add(lblNivel);
        arriba.add(lblObjetivo);

        panelDinero.add(arriba);
        panelDinero.add(lblDinero);

        panelDinero.revalidate();
        panelDinero.repaint();
    }

    private void refrescarCinta() {
        panelCinta.removeAll();

        ListaMateriales mats = fabrica.getCinta().getMateriales();
        int cap = fabrica.getCinta().getCapacidad();

        int i = 0;
        while (i < cap) {
            if (i < mats.tamano()) {
                Material m = mats.get(i);
                JPanel tarjeta = crearTarjetaMaterial(m, i);
                panelCinta.add(tarjeta);
            } else {
                JPanel vacio = new JPanel();
                vacio.setBorder(new LineBorder(new Color(230,230,230)));
                panelCinta.add(vacio);
            }
            i++;
        }

        panelCinta.revalidate();
        panelCinta.repaint();
    }

    private JPanel crearTarjetaMaterial(Material m, int indiceEnCinta) {
        JPanel card = new JPanel(new GridLayout(2,1));
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(60, 179, 113)),
                new EmptyBorder(6, 6, 6, 6)
        ));
        JLabel t = new JLabel(m.getNombre());
        t.setFont(fontNormal);
        JLabel v = new JLabel("$" + (long)m.getValor());
        v.setFont(fontNormal);
        card.add(t);
        card.add(v);

        // Draggable como "MATERIAL:indice"
        String payload = "MATERIAL:" + indiceEnCinta;
        DraggableSupport.hacerArrastrable(card, payload);

        return card;
    }

    private void refrescarLineas() {
        linea0.refrescar();
        linea1.refrescar();
        linea2.refrescar();
    }

    // -------------------- Clases de soporte DnD --------------------

    /** Soporte genérico para hacer un componente arrastrable con un payload String. */
    private static final class DraggableSupport {
        static void hacerArrastrable(final JComponent comp, final String payload) {
            comp.setTransferHandler(new TransferHandler("text") {
                @Override
                protected Transferable createTransferable(JComponent c) {
                    return new StringSelection(payload);
                }
                @Override public int getSourceActions(JComponent c) {
                    return COPY_OR_MOVE;
                }
            });
            comp.addMouseListener(new MouseAdapter() {
                @Override public void mousePressed(MouseEvent e) {
                    JComponent c = (JComponent)e.getSource();
                    TransferHandler th = c.getTransferHandler();
                    th.exportAsDrag(c, e, TransferHandler.MOVE);
                }
            });
        }
    }

    /** Bordes punteados para áreas de drop. */
    private static final class DashedBorder extends LineBorder {
        public DashedBorder(Color color) { super(color, 2, true); }
    }

    /** Pista visual al entrar/salir. */
    private static final class DropCueMouse extends MouseAdapter {
        @Override public void mouseEntered(MouseEvent e) {
            JComponent c = (JComponent)e.getSource();
            c.setBackground(new Color(255, 250, 240));
        }
        @Override public void mouseExited(MouseEvent e) {
            JComponent c = (JComponent)e.getSource();
            c.setBackground(new Color(245, 245, 245));
        }
    }

    // -------------------- Drop Handlers --------------------

    /** Drop en el basurero: solo acepta MATERIAL. */
    private class BasureroDropHandler extends TransferHandler {
        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }
        @Override
        public boolean importData(TransferSupport support) {
            try {
                String data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                if (data.startsWith("MATERIAL:")) {
                    int idx = parseIndice(data);
                    double perdida = fabrica.desecharMaterialDeCinta(idx);
                    mostrarMensaje("Material desechado: -$" + (long)perdida);
                    refrescarDinero();
                    refrescarCinta();
                    return true;
                } else {
                    mostrarMensaje("Solo se aceptan materiales en el basurero.");
                    return false;
                }
            } catch (Exception ex) {
                mostrarMensaje("Error al soltar en basurero.");
                return false;
            }
        }
    }

    /** Drop en línea de producción: acepta PEDIDO (si vacía) o MATERIAL (si con vehículo). */
    private class LineaDropHandler extends TransferHandler {
        private int indiceLinea;
        LineaDropHandler(int indiceLinea) {
            this.indiceLinea = indiceLinea;
        }
        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }
        @Override
        public boolean importData(TransferSupport support) {
            try {
                String data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                if (data.startsWith("PEDIDO:")) {
                    boolean ok = fabrica.colocarPedidoEnLinea(parseIndice(data), indiceLinea);
                    if (ok) {
                        mostrarMensaje("Pedido colocado en línea " + (indiceLinea+1));
                        refrescarPedidos();
                        refrescarLineas();
                        return true;
                    } else {
                        mostrarMensaje("La línea " + (indiceLinea+1) + " ya está ocupada.");
                        return false;
                    }
                } else if (data.startsWith("MATERIAL:")) {
                    boolean ok = fabrica.intentarColocarMaterial(parseIndice(data), indiceLinea);
                    if (ok) {
                        mostrarMensaje("Material correcto.");
                        refrescarCinta();
                        refrescarLineas();

                        // Intentar finalizar
                        double g = fabrica.intentarFinalizarLinea(indiceLinea);
                        if (g > 0) {
                            mostrarMensaje("Vehículo terminado: +$" + (long)g);
                            refrescarLineas();
                            refrescarDinero();

                            // ¿Objetivo cumplido?
                            if (fabrica.objetivoAlcanzado()) {
                                avanzarDeFabrica();
                            }
                        }
                        return true;
                    } else {
                        mostrarMensaje("Material incorrecto para esa línea.");
                        return false;
                    }
                }
                return false;
            } catch (Exception ex) {
                mostrarMensaje("Error al soltar en línea.");
                return false;
            }
        }
    }

    // -------------------- Línea visual --------------------

    private final class LineaPanel extends JPanel {
        private int indice; // 0,1,2
        private JLabel lblTitulo;
        private JLabel lblContenido;

        LineaPanel(int indice) {
            super(new BorderLayout());
            this.indice = indice;
            setBorder(titulo("Línea " + (indice+1)));

            lblTitulo = new JLabel("");
            lblTitulo.setFont(fontTitulo);
            lblContenido = new JLabel("");
            lblContenido.setFont(fontNormal);

            JPanel centro = new JPanel(new GridLayout(2,1));
            centro.add(lblTitulo);
            centro.add(lblContenido);

            add(centro, BorderLayout.CENTER);

            setBackground(new Color(245,245,245));
            setBorder(new CompoundBorder(
                    titulo("Línea " + (indice+1)),
                    new EmptyBorder(10,10,10,10)
            ));

            // Drop handler para esta línea
            setTransferHandler(new LineaDropHandler(indice));
            addMouseListener(new DropCueMouse());
        }

        void refrescar() {
            LineaProduccion lp = fabrica.getZonaConstruccion().getLineas().get(indice);
            if (!lp.tieneVehiculo()) {
                lblTitulo.setText("(vacía)");
                lblContenido.setText("Arrastra aquí un pedido.");
            } else {
                Vehiculo v = lp.getVehiculoEnProceso();
                int req = v.getMaterialesRequeridos().tamano();
                int col = lp.getMaterialesColocados().tamano();
                lblTitulo.setText(v.getNombre());
                lblContenido.setText("Progreso: " + col + " / " + req + " (arrastra materiales)");
            }
            revalidate();
            repaint();
        }
    }

    // -------------------- Utilidades --------------------

    private int parseIndice(String payload) {
        // payload = "TIPO:numero"
        int pos = payload.indexOf(':');
        if (pos < 0) return -1;
        String num = payload.substring(pos+1);
        // No usamos try/catch con librerías externas; parsing simple
        int r = 0;
        int i = 0;
        boolean neg = false;
        if (num.length() > 0 && num.charAt(0) == '-') { neg = true; i = 1; }
        while (i < num.length()) {
            char c = num.charAt(i);
            if (c < '0' || c > '9') break;
            r = r*10 + (c - '0');
            i++;
        }
        return neg ? -r : r;
    }

    private void mostrarMensaje(String msg) {
        barraEstado.setText(msg);
    }

    private void avanzarDeFabrica() {
        if (nivelActual < 3) {
            nivelActual++;
            mostrarMensaje("Felicidades, lograste avanzar a la fábrica " + nivelActual);
            iniciarFabrica(nivelActual);
            //ASCENSO VISUAL
            panelAscenso.setIndiceDesdeNivel(nivelActual, false);
            refrescarTodo();
        } else {
            mostrarMensaje("¡Victoria! Has completado la Fábrica 3.");
            panelAscenso.setIndiceDesdeNivel(nivelActual, true);
        }
        refrescarDinero();
    }
}
