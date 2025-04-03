package org.example;

/**
 * @author Izan López Mora 1DAM
 * @version 1.0
 * Enum que almacena los productos con sus precios.
 */
public enum Producto {
    MANZANAS(2.30), PAN(1.00), ARROZ(3.50), POLLO(3.50), LECHE(1.30),
    ACEITE(8.30), HUEVOS(3.30), TOMATES(4.00), PASTA(0.89);

    private double precio;

    Producto(double precio){
        this.precio=precio;
    }

    public double getPrecio(){
        return precio;
    }

}
