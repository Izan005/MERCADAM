# Práctica servicio de compra DAM
# Realizado por Izan López Mora
![MERCADAM.png](MERCADAM.png)

## ÍNDICE

1.  [Resumen](#resumen)
2. [Estructura de clases](#estructura-de-clases)
3. [Programa principal](#programa-principal)
4. [Pruebas](#pruebas)
5. [Entrega](#entrega)

## Resumen
> Este proyecto consiste en la realización de una aplicación de compra online para un supermercado llamado **MERCADAM**.

El programa se divide en 2 partes:
- **App de gestión** Crear clientes y productos.
- **App de zona de clientes** Autenticación y selección de productos para comprar.


## Estructura de clases
El programa sigue el paradigma de la **(POO)** y se basa en la siguiente imagen

### UML (código)
````Plant UML
@startuml

class AppZonaClientes {
    - Scanner in
    - Cliente cliente
    - double total
    + main(String[] args)
    + autenticacion(List<Cliente> listaClientes)
    + iniciarCompra()
    + imprimirProductos(): String
    + comprobarProducto(String producto)
    + mostrarCarrito()
    + menuFinal()
    + seleccionMenuFinal()
}

class Cliente {
    - String usuario
    - String contraseña
    - String direccion
    - Pedido pedido
    - boolean promociones
    + Cliente(String usuario, String contraseña)
    + crearPedido()
    + insertarProducto(String producto)
    + mostrarProductos(): double
    + mostrarProductosPorUds()
    + getUsuario(): String
    + getContraseña(): String
    + getPedido(): Pedido
    + getPromociones(): boolean
    + setPromociones(boolean promociones)
}

class Pedido {
    - HashMap<Producto, Integer> pedidos
    + Pedido()
    + aplicarPromo3x2(): double
    + aplicarPromo10(double total): double
    + getPedidos(): HashMap<Producto, Integer>
}

class Mercadam {
    - static Random rdm
    - static List<Cliente> listaClientes
    + static List<Cliente> generarClientes()
    + static List<Cliente> getListaClientes()
}

enum Producto {
    MANZANAS, PAN, ARROZ, POLLO, LECHE, ACEITE, HUEVOS, TOMATES, PASTA
    + getPrecio(): double
}

AppZonaClientes *-- Cliente
Cliente *-- Pedido

AppZonaClientes --o Mercadam

@enduml
````
### UML (imagen)
![DiagramaMercadam.png]()

## Programa principal
## Pruebas
## Entrega
-[ ] **Código fuente** [Enlace a GitHub]
