package org.example;

import java.util.HashMap;

public class Pedido {

    private HashMap<Producto, Integer> pedido;
    private double importe_total;

    public Pedido(){
        pedido = new HashMap<>();
    }
}
