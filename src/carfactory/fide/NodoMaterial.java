/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class NodoMaterial {

    material material;
    NodoMaterial siguiente;

    public NodoMaterial(material material) {
        this.material = material;
        this.siguiente = null;
    }

}
