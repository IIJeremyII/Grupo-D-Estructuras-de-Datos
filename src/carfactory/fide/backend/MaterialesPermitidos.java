/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide.backend;

/**
 *
 * @author jerse
 */
public class MaterialesPermitidos {

    private MaterialesPermitidos() {
    }

    public static ListaMateriales fabrica1() {
        ListaMateriales inv = new ListaMateriales();
        inv.add(new Material("Motor de lujo", 500));
        inv.add(new Material("Carrocería", 500));
        return inv;
    }

    public static ListaMateriales fabrica2() {
        ListaMateriales inv = fabrica1();
        inv.add(new Material("Motor especial deportivo", 2000));
        inv.add(new Material("Carrocería especial", 800));
        inv.add(new Material("Llantas únicas de trabajo", 800));
        return inv;
    }

    public static ListaMateriales fabrica3() {
        ListaMateriales inv = fabrica2();
        inv.add(new Material("Llantas deportivas", 2000));
        return inv;
    }
}
