package org.example;

import java.util.*;


public class AppZonaClientes {

    static Scanner in = new Scanner(System.in);
    static Cliente cliente;
    static double total = 0;
    public static void main(String[] args){

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
                    cliente.crearPedido();
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

        String producto = imprimirProductos();

        comprobarProducto(producto);


    }

    public static String imprimirProductos(){
        System.out.println("Añade productos a tu lista de la compra... \n");
        for (Producto p : Producto.values()){
            System.out.println("\t -" + p + " precio (" + p.getPrecio() + "€), \n");

        }
        System.out.println("=============================================");
        System.out.println("Elige un producto:");

        return in.next().toUpperCase();
    }

    public static void comprobarProducto(String producto){

        boolean existe = false;

        for (Producto p : Producto.values()){
            if (p.name().equals(producto)){
                existe = true;
            }
        }

        if (existe){
            cliente.insertarProducto(producto);
            mostrarCarrito();
        } else {
            System.out.println("ERROR. El producto seleccionado no existe.");
            iniciarCompra();
        }
    }

    public static void mostrarCarrito(){

        System.out.println("=== CARRITO === \n Productos: ");
        total = cliente.mostrarProductos();

        System.out.println("¿Añadir otro producto? [S/N]");
        switch (in.next().toUpperCase()){
            case "S":
                iniciarCompra();
                break;
            case "N":
                menuFinal();
                break;
            default:
                System.out.println("Opción no válida.");
                mostrarCarrito();
                break;
        }
    }

    public static void menuFinal(){
        System.out.println("=============================\n");
        System.out.println("¿QUÉ DESEA HACER?\n\t[1]. Aplicar promo.\n\t[2]. Mostrar resumen ordenado por uds." +
                "\n\t[3]. Terminar pedido.");
        System.out.println("=============================\n\tElije una opción:");
        seleccionMenuFinal();
    }

    public static void seleccionMenuFinal(){
        String opc = in.next();
        switch (opc) {
            case "1":
                if (!cliente.getPromociones()){
                    total = cliente.getPedido().aplicarPromo3x2(total);
                    total = cliente.getPedido().aplicarPromo10(total);
                    System.out.println("IMPORTE TOTAL TRAS LAS PROMOCIONES: " + total + "€.");
                    cliente.setPromociones(true);
                }else {
                    System.out.println("YA SE HAN APLICADO LAS PROMOCIONES");
                }
                menuFinal();
                break;
            case "2":
                cliente.mostrarProductosPorUds();
                menuFinal();
                break;
            case "3":
                System.out.println("Tu pedido de importe " + total + "€ será enviado a tu casa.");
                break;
            default:
                System.out.println("Opción no válida.");
                menuFinal();
                break;

        }
    }
}