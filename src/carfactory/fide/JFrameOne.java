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
import java.awt.FlowLayout;
import java.awt.LayoutManager;


//Importes para el drag and drop del raton
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
/**
 *
 * @author jeremy.segura
 */
public class JFrameOne extends JFrame {

    //Lista de materiales
    private cintaTransportadora cintaTransportadora1 = new cintaTransportadora(10);
    private ZonaConstruccion zonaConstruccion  = new ZonaConstruccion();
    private ZonaBasurero zonaBasurero;
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
    private JLabel linea1;
    private JLabel linea2;
    private JLabel linea3;
    private JLabel basurero;
    private JLabel material1;
    private JLabel material2;
    private JLabel material3;
    
    //Hacemos variable global para el drag
    private material materialArrastrar = null;
    private cintaTransportadora cinta;
    
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

        

        linea1 = new JLabel("Sedan de Lujo");
        linea1.setPreferredSize(new Dimension(200, 200));
        linea1.setOpaque(true);
        linea1.setBackground(Color.LIGHT_GRAY);
        linea1.setForeground(Color.BLACK);
        linea1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        linea1.setHorizontalAlignment(SwingConstants.CENTER);
        
        linea2 = new JLabel("Pick-up de alta gama");
        linea2.setPreferredSize(new Dimension(200, 200));
        linea2.setOpaque(true);
        linea2.setBackground(Color.LIGHT_GRAY);
        linea2.setForeground(Color.BLACK);
        linea2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        linea2.setHorizontalAlignment(SwingConstants.CENTER);

        linea3 = new JLabel("Sedan de Lujo");
        linea3.setPreferredSize(new Dimension(200, 200));
        linea3.setOpaque(true);
        linea3.setBackground(Color.LIGHT_GRAY);
        linea3.setForeground(Color.BLACK);
        linea3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        linea3.setHorizontalAlignment(SwingConstants.CENTER);

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

        material1 = new JLabel();
        material1.setPreferredSize(new Dimension(250,50));
        material1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        material1.setHorizontalAlignment(SwingConstants.CENTER);
        
        material2 = new JLabel();
        material2.setPreferredSize(new Dimension(250, 50));
        material2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        material2.setHorizontalAlignment(SwingConstants.CENTER);

        material3 = new JLabel();
        material3.setPreferredSize(new Dimension(250, 50));
        material3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        material3.setHorizontalAlignment(SwingConstants.CENTER);

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

        basurero = new JLabel("Basurero");
        basurero.setPreferredSize(new Dimension(200, 200));
        basurero.setOpaque(true);
        basurero.setBackground(Color.LIGHT_GRAY);
        basurero.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon basureroIcon = new ImageIcon(basura_icono.png);
        add(basurero, BorderLayout.EAST);
        
        
        
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
        linea1.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (materialArrastrar != null) {
                    boolean usado = zonaConstruccion.usarMaterialEnLinea(0, materialArrastrar);
                    if (usado) {
                        linea1.setText("✔ " + materialArrastrar.getNombre());
                        cintaTransportadora1.eliminarMaterial(materialArrastrar);
                        actualizarMateriales();
                        if (zonaConstruccion.getLinea(0).getVehiculo() == null) {
                            linea1.setText("Vehículo terminado + $ganancia");
                            new javax.swing.Timer(5000, ev -> {
                                linea1.setText("Sin vehículo");
                            }).start();
                        } else {
                            new javax.swing.Timer(5000, ev -> {
                                Vehiculo v = zonaConstruccion.getLinea(0).getVehiculo();
                                linea1.setText(v != null ? v.getTipo() : "Sin vehículo");
                            }).start();
                        }
                    } else {
                        linea1.setText("✖ " + materialArrastrar.getNombre());
                        new javax.swing.Timer(5000, ev -> {
                            Vehiculo v = zonaConstruccion.getLinea(0).getVehiculo();
                            linea1.setText(v != null ? v.getTipo() : "Sin vehículo");
                        }).start();
                    }
                    materialArrastrar = null;
                }
            }
        });

        linea2.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (materialArrastrar != null) {
                    boolean usado = zonaConstruccion.usarMaterialEnLinea(1, materialArrastrar);
                    if (usado) {
                        linea2.setText("✔ " + materialArrastrar.getNombre());
                        cintaTransportadora1.eliminarMaterial(materialArrastrar);
                        actualizarMateriales();
                        if (zonaConstruccion.getLinea(1).getVehiculo() == null) {
                            linea2.setText("Vehículo terminado + $ganancia");
                            new javax.swing.Timer(5000, ev -> {
                                linea2.setText("Sin vehículo");
                            }).start();
                        } else {
                            new javax.swing.Timer(5000, ev -> {
                                Vehiculo v = zonaConstruccion.getLinea(1).getVehiculo();
                                linea2.setText(v != null ? v.getTipo() : "Sin vehículo");
                            }).start();
                        }
                    } else {
                        linea2.setText("✖ " + materialArrastrar.getNombre());
                        new javax.swing.Timer(5000, ev -> {
                            Vehiculo v = zonaConstruccion.getLinea(1).getVehiculo();
                            linea2.setText(v != null ? v.getTipo() : "Sin vehículo");
                        }).start();
                    }
                    materialArrastrar = null;
                }
            }
        });

        linea3.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (materialArrastrar != null) {
                    boolean usado = zonaConstruccion.usarMaterialEnLinea(2, materialArrastrar);
                    if (usado) {
                        linea3.setText("✔ " + materialArrastrar.getNombre());
                        cintaTransportadora1.eliminarMaterial(materialArrastrar);
                        actualizarMateriales();
                        if (zonaConstruccion.getLinea(2).getVehiculo() == null) {
                            linea3.setText("Vehículo terminado + $ganancia");
                            new javax.swing.Timer(5000, ev -> {
                                linea3.setText("Sin vehículo");
                            }).start();
                        } else {
                            new javax.swing.Timer(5000, ev -> {
                                Vehiculo v = zonaConstruccion.getLinea(2).getVehiculo();
                                linea3.setText(v != null ? v.getTipo() : "Sin vehículo");
                            }).start();
                        }
                    } else {
                        linea3.setText("✖ " + materialArrastrar.getNombre());
                        new javax.swing.Timer(5000, ev -> {
                            Vehiculo v = zonaConstruccion.getLinea(2).getVehiculo();
                            linea3.setText(v != null ? v.getTipo() : "Sin vehículo");
                        }).start();
                    }
                    materialArrastrar = null;
                }
            }
        });
        
        //Acciones para el drop
        zonaBasurero = new ZonaBasurero(cintaTransportadora1);
        basurero.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (materialArrastrar != null) {
                    zonaBasurero.desecharMaterial(materialArrastrar);
                    materialArrastrar = null;
                    actualizarMateriales();
                }
            }
        });

        //ESTA ZONA ES SOLO DE PRUEBA PARA INICIAR 3 AUTOS AUTOMATICAMENTE SIN PEDIDOS
        zonaConstruccion.iniciarVehiculo("Sedan de Lujo", 0);
        zonaConstruccion.iniciarVehiculo("Pick-up de alta gama", 1);
        zonaConstruccion.iniciarVehiculo("Sedan de Lujo", 2);
    }

    private void actualizarMateriales() {
        actual = lista.getcabeza();

        if (actual != null) {
            material1.setText(actual.getMaterial().getNombre());
        } else {
            material1.setText("");
        }

        if (actual != null && actual.siguiente != null) {
            material2.setText(actual.siguiente.getMaterial().getNombre());
        } else {
            material2.setText("");
        }

        if (actual != null && actual.siguiente != null && actual.siguiente.siguiente != null) {
            material3.setText(actual.siguiente.siguiente.getMaterial().getNombre());
        } else {
            material3.setText("");
        }
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
