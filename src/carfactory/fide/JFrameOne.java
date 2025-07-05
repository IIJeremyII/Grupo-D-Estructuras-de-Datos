/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.dnd.DropTarget;

//Importes para el drag and drop del raton
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jeremy.segura
 */
public class JFrameOne extends JFrame {

    //Lista de materiales
    private cintaTransportadora cintaTransportadora1 = new cintaTransportadora(10);

    //Obtenemos lista de cintraTransportadora 
    ListaMateriales lista = cintaTransportadora1.getMateriales();
    NodoMaterial actual = lista.getcabeza();

    //Contador para la lista, de esta manera reemplazamos los materiales con el siguente de la lista
    //Debemos desarollar para que contador se utilize para hacer el recorrido de la lista.
    //Ejemplo tras escoger el material 1 y colocarlo en la linea de produccion o basura que esta sea ahora el espacio 3 
    //(pasamos del 0 al 1 al 2, en las primeros materiales ahora pasamos al 3)
    //
    //Quiza se pueda hacer con un for
    // 
    //
    //
    //
    private int contador = 0;

    //Hacemos variable global para el drag
    private material materialArrastrar = null;

    public JFrameOne() {
        //initialize();

        //Titulo, tamaño, cerrar, y hacer visible
        setTitle("CarFactory-Fide");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Cambio de 1200,700 a 1800,1300
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setVisible(true);

        //Poder ver hub(pedidos) usamos un gridBag constrains acomodamos parte superior
        JPanel panelPedidos = new JPanel(new GridBagLayout());

        //Creamos una fuente para que las letras no se vean tan pequeñas 
        Font fuente = new Font("Arial", Font.BOLD, 20);

        JLabel pedido1 = new JLabel("Aqui iria el primer pedido (editamos variable cuando tengamos el codigo para generarlo aleatoriamente)");
        pedido1.setFont(fuente);

        JLabel pedido2 = new JLabel("Aqui iria el segundo pedido (editamos variable cuando tengamos el codigo para generarlo aleatoriamente)");
        pedido2.setFont(fuente);

        JLabel pedido3 = new JLabel("Aqui iria el tercer pedido (editamos variable cuando tengamos el codigo para generarlo aleatoriamente)");
        pedido3.setFont(fuente);

        JLabel pedido4 = new JLabel("Aqui iria el cuarto pedido (editamos variable cuando tengamos el codigo para generarlo aleatoriamente)");
        pedido4.setFont(fuente);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        //Estamos espacio 0,0 movemos el X ya que este se mueve horizontal, Y arriba hacia abajo
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPedidos.add(pedido1, gbc);

        gbc.gridx = 1;
        panelPedidos.add(pedido2, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panelPedidos.add(pedido3, gbc);

        gbc.gridx = 1;
        panelPedidos.add(pedido4, gbc);

        add(panelPedidos, BorderLayout.NORTH);

        //Linea de construccion donde vamos a arrastar los materiales
        JPanel panelLineaConstruccion = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        //Insets son los pixeles de espacio top,left,botom,right
        gbc2.insets = new Insets(10, 10, 10, 10);
        gbc2.fill = GridBagConstraints.BOTH;

        JLabel linea1 = new JLabel("Arrastramos aqui el material");
        //Preferred size damos la dimesion 200 por 200 pixeles
        linea1.setPreferredSize(new Dimension(200, 200));
        //Hacemos que sea opaco y colocamos un color de fondo
        linea1.setOpaque(true);
        linea1.setBackground(Color.LIGHT_GRAY);

        JLabel linea2 = new JLabel("Arrastramos aqui el material");
        linea2.setPreferredSize(new Dimension(200, 200));
        //Hacemos que sea opaco y colocamos un color de fondo
        linea2.setOpaque(true);
        linea2.setBackground(Color.LIGHT_GRAY);

        JLabel linea3 = new JLabel("Arrastramos aqui el material");
        linea3.setPreferredSize(new Dimension(200, 200));
        //Hacemos que sea opaco y colocamos un color de fondo
        linea3.setOpaque(true);
        linea3.setBackground(Color.LIGHT_GRAY);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        panelLineaConstruccion.add(linea1, gbc2);

        gbc2.gridx = 2;
        panelLineaConstruccion.add(linea2, gbc2);

        gbc2.gridx = 4;
        panelLineaConstruccion.add(linea3, gbc2);

        add(panelLineaConstruccion, BorderLayout.CENTER);

        //Area de materiales.
        //Cantidad de materiales cambia en fabrica 2 y 3 aumentan de 3 a 4, y de 4 a 5 respectivamente. (Usaremos 3 por ahora, debemos de hacer logica futuros avances)
        JPanel panelMateriales = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(30, 30, 30, 30);
        gbc3.fill = GridBagConstraints.BOTH;

        JLabel material1 = new JLabel();
        JLabel material2 = new JLabel();
        JLabel material3 = new JLabel();

        gbc3.gridx = 0;
        gbc3.gridy = 0;
        panelMateriales.add(material1, gbc3);

        gbc3.gridx = 1;
        panelMateriales.add(material2, gbc3);

        gbc3.gridx = 2;
        panelMateriales.add(material3, gbc3);

        //Conseguimos los nombres de la lista
        if (actual != null) {
            material1.setText(actual.getMaterial().getNombre());
        }
        if (actual != null && actual.siguiente != null) {
            material2.setText(actual.siguiente.getMaterial().getNombre());
        }
        if (actual != null && actual.siguiente != null && actual.siguiente.siguiente != null) {
            material3.setText(actual.siguiente.siguiente.getMaterial().getNombre());
        }

        add(panelMateriales, BorderLayout.SOUTH);

        //Acciones para el drag and drop usando variable global
        material1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (actual != null) {
                    materialArrastrar = actual.getMaterial();
                }
            }
        });

        //Lo hacemos para cada uno de los materiales
        material2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (actual != null) {
                    //Se siguente lo hacemos por el espacio 2, tomamos el 2nd espacio en nustra lista
                    materialArrastrar = actual.siguiente.getMaterial();
                }
            }
        });

        material3.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (actual != null) {
                    //Los mismo que el cometario anterio pero con el 3ro
                    materialArrastrar = actual.siguiente.siguiente.getMaterial();;
                }
            }
        });

        //Acciones para el drop
        linea1.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (materialArrastrar != null) {
                    linea1.setText(materialArrastrar.getNombre());
                    materialArrastrar = null;
                }
            }
        });
        
        //Igual lo hacemos para las 3 lineas
        linea2.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (materialArrastrar != null) {
                    linea2.setText(materialArrastrar.getNombre());
                    materialArrastrar = null;
                }
            }
        });
        
        linea3.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (materialArrastrar != null) {
                    linea3.setText(materialArrastrar.getNombre());
                    materialArrastrar = null;
                }
            }
        });

    }

    /*
    private void initialize() {
        setTitle("CarFactory-Fide");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,700);
        setLocationRelativeTo(null);
        setVisible(true); 
    } 
     */
}
