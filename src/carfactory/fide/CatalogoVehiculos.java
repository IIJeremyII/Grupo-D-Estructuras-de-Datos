/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carfactory.fide;

/**
 *
 * @author jerse
 */
public class CatalogoVehiculos {
    private CatalogoVehiculos() {}

    public static ListaVehiculos fabrica1() {
        ListaVehiculos cat = new ListaVehiculos();

        ListaMateriales m1 = new ListaMateriales();
        m1.add(new Material("Motor de lujo", 500));
        m1.add(new Material("Carrocería", 500));
        cat.add(new Vehiculo("Sedan de Lujo", m1, 5000));

        ListaMateriales m2 = new ListaMateriales();
        m2.add(new Material("Motor de lujo", 500));
        m2.add(new Material("Carrocería", 500));
        cat.add(new Vehiculo("Pickup de alta gama", m2, 12000));

        return cat;
    }

    public static ListaVehiculos fabrica2() {
        ListaVehiculos cat = fabrica1();

        ListaMateriales m3 = new ListaMateriales();
        m3.add(new Material("Motor especial deportivo", 2000));
        m3.add(new Material("Carrocería especial", 800));
        m3.add(new Material("Llantas únicas de trabajo", 800));
        cat.add(new Vehiculo("Maquinaria de alta gama para trabajos pesados", m3, 17500));

        return cat;
    }

    public static ListaVehiculos fabrica3() {
        ListaVehiculos cat = new ListaVehiculos();

        ListaMateriales m2 = new ListaMateriales();
        m2.add(new Material("Motor de lujo", 500));
        m2.add(new Material("Carrocería", 500));
        cat.add(new Vehiculo("Pickup de alta gama", m2, 12000));

        ListaMateriales m3 = new ListaMateriales();
        m3.add(new Material("Motor especial deportivo", 2000));
        m3.add(new Material("Carrocería especial", 800));
        m3.add(new Material("Llantas únicas de trabajo", 800));
        cat.add(new Vehiculo("Maquinaria de alta gama para trabajos pesados", m3, 17500));

        ListaMateriales m4 = new ListaMateriales();
        m4.add(new Material("Motor especial deportivo", 2000));
        m4.add(new Material("Carrocería", 500));
        m4.add(new Material("Llantas deportivas", 2000));
        cat.add(new Vehiculo("Superauto Deportivo", m4, 20000));

        return cat;
    }
}
