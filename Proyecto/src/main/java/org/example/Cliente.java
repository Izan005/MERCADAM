package org.example;

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

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
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
