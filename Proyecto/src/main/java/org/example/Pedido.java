package org.example;

import java.util.HashMap;
import java.util.Map;

public class Pedido {

    private HashMap<Producto, Integer> pedidos;
    private double importe_total;

    public Pedido(){
        pedidos = new HashMap<>();
    }

    public HashMap<Producto, Integer> getPedidos() {
        return pedidos;
    }

    public double aplicarPromo3x2(double total){

        total = 0;
        for (Map.Entry<Producto, Integer> mapa : pedidos.entrySet()){
            if (mapa.getValue() % 3 == 0 || (mapa.getValue() > 3 && mapa.getValue() % 3 != 0)){
                pedidos.put(mapa.getKey(),  mapa.getValue()-mapa.getValue()/3);
            }
            total += mapa.getKey().getPrecio()*mapa.getValue();

        }

        return total;
    }

    public double aplicarPromo10(double total){
        total = total * 0.9;
        return total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedido=" + pedidos +
                ", importe_total=" + importe_total +
                '}';
    }
}
