package org.example;

import java.util.*;

/**
 * @author Izan López Mora 1DAM
 * @version 1.0
 * Aplicación que simula la compra online de productos de un supermercado.
 */
public class AppZonaClientes {

    static Scanner in = new Scanner(System.in);
    static Cliente cliente; //Cliente que iniciaremos con el pedido.
    static double total = 0; //Total a pagar sobre el pedido.
    public static void main(String[] args){

        List<Cliente> listaClientes = Mercadam.generarClientes(); //Lista de clientes (user y pass aleatorios)
        System.out.println(Mercadam.getListaClientes()); //Imprimir listaClientes
        autenticacion(listaClientes);
    }

    /**
     * Método que recive la lista de users y passwords generada anteriormente y solicita un usuario y contraseñas
     * válidos presentes en la lista para poder iniciar sesión. Si fallamos 3 veces la autenticación saltará un error
     * y saldremos del programa.
     * @param listaClientes que le hemos pasado del main.
     */
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
                    cliente = c; //Asignamos las credenciales correctas al usuario.
                    cliente.crearPedido(); //Inicializamos el atributo pedido en el cliente estático.
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

    /**
     * Método que se encarga de llamar al método imprimirProductos() que imprime la lista de productos y devuelve
     * el producto seleccionado para asignarlo a la variable local producto, la cual la utilizaremos en el método
     * comprobarProducto(String producto) que busca en el enum que el producto exista.
     */
    public static void iniciarCompra(){
        String producto = imprimirProductos();
        comprobarProducto(producto);

    }

    /**
     * Método que imprime la lista de productos y devuelve el producto seleccionado para asignarlo a la variable
     * producto del método iniciarCompra().
     * @return el producto seleccionado.
     */
    public static String imprimirProductos(){
        System.out.println("Añade productos a tu lista de la compra... \n");
        for (Producto p : Producto.values()){
            System.out.println("\t -" + p + " precio (" + p.getPrecio() + "€), \n");

        }
        System.out.println("=============================================");
        System.out.println("Elige un producto:");

        return in.next().toUpperCase();
    }

    /**
     * Método encargado de buscar en el enum Producto que el producto exista y si no existe, salta error y nos redirige
     * al método iniciarCompra(). Si el producto existe, se inserta en el cliente mediante el método insertarProducto y
     * se llama al método mostrarCarrito().
     * @param producto que recibe del método iniciarCompra().
     */
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

    /**
     * Método que muestra el carrito actual y el importe total de nustro pedido. Además, se nos muestra un menú para
     * seleccionar si queremos añadir un producto más o no. Si es que sí, se nos redirege al método iniciarCompra() y si
     * es que no, nos redirige al método menuFinal().
     */
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

    /**
     * Método encargado de mostrar un menú que nos permite:
     * 1. Aplicar los descuentos 3x2 y del 10%.
     * 2. Mostrar los productos ordenados de mayor a menor por sus unidades.
     * 3. Terminar el pedido (salir del programa).
     * Este método solo sirve para mostrar el menú y no para seleccionar algo realmente ya que de eso se encarga el
     * método seleccionMenuFinal().
     */
    public static void menuFinal(){
        System.out.println("=============================\n");
        System.out.println("¿QUÉ DESEA HACER?\n\t[1]. Aplicar promo.\n\t[2]. Mostrar resumen ordenado por uds." +
                "\n\t[3]. Terminar pedido.");
        System.out.println("=============================\n\tElije una opción:");
        seleccionMenuFinal();
    }

    /**
     * Método que guarda una opción del método menuFinal() y la utiliza en un switch-case para seleccionar la opción
     * elegida. Según la opción el programa hará lo siguiente:
     * 1. Si el cliente no ha consumido las promociones (atributo booleano promociones = false) se le aplicará al total
     * del pedido el 3x2 sobre los productos los cuales hayamos pedido más de 3 unidades. Seguidamente a ese total le
     * aplicaremos el 10% de descuento para por último hacer que el atributo promociones sea true y regresamos de nuevo
     * a menuFinal().
     * 2. Llamamos al método mostrarProductosPorUds() que muestra ordenado por unidades de mayor a menor los productos
     * del carrito y regresamos de nuevo a menuFinal()
     * 3. Terminamos el pedido y salimos del programa.
     */
    public static void seleccionMenuFinal(){
        String opc = in.next();
        switch (opc) {
            case "1":
                if (!cliente.getPromociones()){
                    total = cliente.getPedido().aplicarPromo3x2();
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