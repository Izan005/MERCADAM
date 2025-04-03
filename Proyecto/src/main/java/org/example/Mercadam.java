package org.example;

import java.util.*;

/**
 * @author Izan López Mora 1DAM
 * @version 1.0
 * Clase encargada de generar una lista de usuarios y contraseñas de 8 caracteres de forma aleatoria
 */
public class Mercadam {
    static Random rdm = new Random();

    //Lista con un usuario por defecto para agilizar el proceso de acceso.
    private static List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(new Cliente("key", "key")));


    /**
     * Método que genera de forma aleatoria un número de clientes y, para cada uno, una contraseña y usuario aleatorios
     * de 8 caracteres.
     * @return
     */
    public static List<Cliente> generarClientes(){
       String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

       int clientesTotales = rdm.nextInt(10)+1;

       int tamaño = 8;

        for (int i = 0; i < clientesTotales; i++) {
            String contraseña = "";
            String usuario = "";

            for (int j = 0; j < tamaño; j++) {
                contraseña += caracteres.charAt(rdm.nextInt(caracteres.length()));
                usuario += caracteres.charAt(rdm.nextInt(caracteres.length()));
            }

            listaClientes.add(new Cliente(usuario, contraseña));
        }
        return listaClientes;
    }

    public static List<Cliente> getListaClientes(){
        return Collections.unmodifiableList(listaClientes); //Hace que la lista de clientes (user y pass) sea inmutable.
    }
}
