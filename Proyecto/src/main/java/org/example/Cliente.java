package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author Izan López Mora 1DAM
 * @version 1.0
 * Clase que simula un cliente que realiza pedidos en una ap de un supermercado.
 */
public class Cliente {

    private String usuario;
    private String contraseña;
    private String direccion;
    private Pedido pedido;
    private boolean promociones;

    public Cliente (String usuario, String contraseña){
        this.usuario = usuario;
        this.contraseña = contraseña;
        pedido = null;
        promociones = false;
        direccion = "Calle false, 123";
    }

    /**
     * Función que inicializa el atributo pedido en un cliente.
     */
    public void crearPedido(){

        pedido = new Pedido();
    }

    /**Función que, al pasarle el nombre de un producto, busca en el HashMap de pedido si ese producto existe o no.
     * Si existe, le suma en 1 el valor y si no, le asigna 1 por primera vez.
     * @param producto a añadir al HashMap de productos del pedido.
     */
    public void insertarProducto(String producto){

        pedido.getPedidos().put(Producto.valueOf(producto), pedido.getPedidos().getOrDefault(Producto.valueOf(producto), 0)+1);
    }

    /**
     * Bucle for-each que recorre el HashMap de pedido y va asignando el precio por unidades a cada producto, además de
     * mostrarlos.
     * @return total del pedido para poder utilizarlo en el programa principal.
     */
    public double mostrarProductos(){
        double total = 0;

        for (Map.Entry<Producto, Integer> mapa : pedido.getPedidos().entrySet()){
            System.out.println(mapa.getValue() + " " + mapa.getKey() + " " + (mapa.getKey().getPrecio()*mapa.getValue()));
            total += mapa.getKey().getPrecio()*mapa.getValue();
        }

        System.out.println("IMPORTE TOTAL: " + total + "€");
        return total;
    }

    /**
     * Función que utiliza la interfaz comparator sobre una lista que contiene un mapa para ordenarlo en orden inverso por
     * su valor, para después mostrarlo por pantalla.
     */
    public void mostrarProductosPorUds(){
        List<Map.Entry<Producto, Integer>> listaPedidosOrden = new ArrayList<>(pedido.getPedidos().entrySet());
        listaPedidosOrden.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<Producto, Integer> lista : listaPedidosOrden){
            System.out.println(lista.getValue() + " " + lista.getKey() + " " + lista.getKey().getPrecio()*lista.getValue());
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public boolean getPromociones() {
        return promociones;
    }

    public void setPromociones(boolean promociones) {
        this.promociones = promociones;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", direccion='" + direccion + '\'' +
                ", pedido=" + pedido +
                ", promociones=" + promociones +
                '}';
    }
}
