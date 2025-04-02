package org.example;

import java.util.*;

public class Mercadam {
    static Random rdm = new Random();

    private static List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(new Cliente("key", "key")));


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
        return Collections.unmodifiableList(listaClientes);
    }
}
