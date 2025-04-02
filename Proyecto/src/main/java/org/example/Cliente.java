package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void crearPedido(){

        pedido = new Pedido();
    }

    public void insertarProducto(String producto){

        pedido.getPedidos().put(Producto.valueOf(producto), pedido.getPedidos().getOrDefault(Producto.valueOf(producto), 0)+1);
    }

    public double mostrarProductos(){
        double total = 0;

        for (Map.Entry<Producto, Integer> mapa : pedido.getPedidos().entrySet()){
            System.out.println(mapa.getValue() + " " + mapa.getKey() + " " + (mapa.getKey().getPrecio()*mapa.getValue()));
            total += mapa.getKey().getPrecio()*mapa.getValue();
        }


        System.out.println("IMPORTE TOTAL: " + total + "€");
        return total;
    }

    public void mostrarProductosPorUds(){
        List<Map.Entry<Producto, Integer>> listaPedidosOrden = new ArrayList<>(pedido.getPedidos().entrySet());
        listaPedidosOrden.sort(Map.Entry.comparingByValue());

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
