package org.example;

import java.util.HashSet;
import java.util.Random;

public class Mercadam {
    static Random rdm = new Random();

    private HashSet<Cliente> listaClientes;

    public Mercadam(){
        listaClientes = new HashSet<>();
    }

    public void generarClientes(){
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

        System.out.println(listaClientes);

    }

}
