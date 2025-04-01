package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AppZonaClientes {

    static Scanner in = new Scanner(System.in);
    static Cliente cliente;
    public static void main(String[] args) {

        List<Cliente> listaClientes = Mercadam.generarClientes();
        System.out.println(Mercadam.getListaClientes()); //Imprimir listaClientes
        autenticacion(listaClientes);
    }

    public static void autenticacion(List<Cliente> listaClientes){

        String usuario = "";
        String contra = "";
        boolean salir = false; //Variable de control para salir del bucle cuando el user y pass sean correctos.
        System.out.println("*** COMPRA ONLINE MERCADAM ***");
        for (int i = 0; i < 3; i++) {

            System.out.println("Usuario: ");
            usuario = in.next();
            System.out.println("Contraseña: ");
            contra = in.next();

            for (Cliente c : listaClientes){
                if (c.getUsuario().equals(usuario) && c.getContraseña().equals(contra)){
                    System.out.println("BIENVENID@, " + usuario);
                    cliente = c;
                    salir = true;
                    iniciarCompra();
                }
            }

            if (salir) { //Si el user y pass son correctos se sale del bucle
                break;
            }
            if (i == 2){ //Cuando los intentos de autenticación lleguen a 3 salta error.
                System.out.println("ERROR DE AUTENTICACIÓN");
            } else { //Si los intentos no llegan a 3 salta este mensaje:
                System.out.println("Datos incorrectos. Vuelve a introducirlos");

            }
        }
    }

    public static void iniciarCompra(){
        String producto = "";
        System.out.println("Añade productos a tu lista de la compra... \n");
        imprimirProductos();
        producto = in.next();
        

    }

    public static void imprimirProductos(){
        for (Producto p : Producto.values()){
            System.out.println("\t -" + p + " precio (" + p.getPrecio() + "€), \n");
            System.out.println("=============================================");
            System.out.println("Elige un producto:");
        }
    }
}