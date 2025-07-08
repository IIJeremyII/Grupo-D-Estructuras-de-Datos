/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jeremy.segura
 */
public class ZonaBasurero {
    
    private cintaTransportadora cinta;

    public ZonaBasurero(cintaTransportadora cinta) {
        this.cinta = cinta;
    }

    public void desecharMaterial(material m) {
        cinta.eliminarMaterial(m);
        // Aquí se podrían aplicar las pérdidas luego, por ahora no es necesario
        System.out.println("Material desechado: " + m.getNombre());
    }
}

