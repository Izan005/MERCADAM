package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Izan López Mora 1DAM
 * @version 1.0
 * Clase que contiene una lista de pedidos en formato HashMap con el producto (enum Producto) como clave y la cantidad
 * (Integer) como valor, además del importe total de todo el pedido.
 */
public class Pedido {

    private HashMap<Producto, Integer> pedidos;

    public Pedido(){
        pedidos = new HashMap<>();
    }

    /**
     * Función que recalcula el total del pedido, haciendo que los productos de los cuales hayamos compardo más de 3
     * unidades "sufran" un descuento 3x2, restando 1 a las unidades totales por cada 3 unidades compradas.
     * @return el total del pedido recalculado con el descuento.
     */
    public double aplicarPromo3x2(){

        double total = 0;
        for (Map.Entry<Producto, Integer> mapa : pedidos.entrySet()){
            if (mapa.getValue() % 3 == 0 || (mapa.getValue() > 3 && mapa.getValue() % 3 != 0)){
                pedidos.put(mapa.getKey(),  mapa.getValue()-mapa.getValue()/3);
            }
            total += mapa.getKey().getPrecio()*mapa.getValue();

        }

        return total;
    }

    /**
     * Función que recibe el total del pedido con el descuento 3x2 aplicado y le aplica un 10% de descuento, multiplicando
     * por 0,9 el total recibido.
     * @param total con el descuento 3x2 aplicado.
     * @return el total con el descuento del 10% aplicado.
     */
    public double aplicarPromo10(double total){
        total = total * 0.9;
        return total;
    }

    public HashMap<Producto, Integer> getPedidos() {
        return pedidos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedido=" + pedidos +
                '}';
    }
}
