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

    //Agregue getter and setters
    material material;
    NodoMaterial siguiente;

    public NodoMaterial(material material) {
        this.material = material;
        this.siguiente = null;
    }

    public material getMaterial() {
        return material;
    }

    public void setMaterial(material material) {
        this.material = material;
    }

    public NodoMaterial getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMaterial siguiente) {
        this.siguiente = siguiente;
    }

}
