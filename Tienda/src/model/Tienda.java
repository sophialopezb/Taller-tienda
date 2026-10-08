package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Tienda {

    private final String nombre;
    private final String nit;
    private final String telefono;

    // Se recomienda declarar con el tipo de la interfaz (List)
    private final List<Cliente> listaClientes = new ArrayList<>();
    private final List<Factura> listaFacturas = new LinkedList<>();
    private Map<String, Producto> listaProductos = new HashMap<>();

    public Tienda(String nombre, String nit, String telefono) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public Map<String, Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(Map<String, Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }


    // CRUD: REGISTROS (CREATE)


    public String registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDocumentoIdentidad()).isPresent()) {
            return "El cliente ya se encuentra registrado";
        }
        listaClientes.add(cliente);
        return "Cliente registrado con exito";
    }

    public String registrarProducto(Producto producto) {
        if (buscarProducto(producto.getCodigo()).isPresent()) {
            return "Producto ya registrado";
        }
        // Se guarda usando el código como clave
        listaProductos.put(producto.getCodigo(), producto);
        return "Producto registrado con exito";
    }

    public String registrarFactura(Factura factura) {
        if (buscarFactura(factura.codigo()).isPresent()) {
            return "La factura ya fue registrada anteriormente";
        }
        listaFacturas.add(factura);
        return "Factura generada con exito";
    }


    // CRUD: ELIMINACIONES (DELETE)


    public String eliminarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDocumentoIdentidad()).isPresent()) {
            listaClientes.remove(cliente);
            return "Cliente eliminado con exito";
        }
        return "El cliente no se encuentra registrado";
    }

    public String eliminarProducto(Producto producto) {
        // eliminar de un HashMap se pasa la clave (el código String)
        if (buscarProducto(producto.getCodigo()).isPresent()) {
            listaProductos.remove(producto.getCodigo());
            return "Producto eliminado con exito";
        }
        return "El producto no esta registrado";
    }

    public String eliminarFactura(Factura factura) {
        if (buscarFactura(factura.codigo()).isPresent()) {
            listaFacturas.remove(factura);
            return "Factura eliminada con exito";
        }
        return "La factura que se desea eliminar no ha sido registrada";
    }

    // CRUD: ACTUALIZACIONES (UPDATE)


    public String actualizarProducto(Producto producto, int cantidad) {
        Optional<Producto> productoEncontrado = buscarProducto(producto.getCodigo());
        if (productoEncontrado.isPresent()) {
            productoEncontrado.get().setCantidadDisponible(cantidad);
            return "Actualizado con exito";
        }
        return "El producto no se encuentra registrado";
    }


    // CRUD: LECTURA Y BÚSQUEDA (READ)


    public Optional<Cliente> buscarCliente(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumentoIdentidad().equals(documento)) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    public Optional<Producto> buscarProducto(String codigo) {
        // OPTIMIZACIÓN: Búsqueda directa O(1) usando la clave del HashMap
        return Optional.ofNullable(listaProductos.get(codigo));
    }

    public Optional<Factura> buscarFactura(String codigo) {
        for (Factura factura : listaFacturas) {
            if (factura.codigo().equals(codigo)) {
                return Optional.of(factura);
            }
        }
        return Optional.empty();
    }

    // 1. Obtener productos en cantidad mayores  o iguales a 10
    public List<Producto> obtenerMayoresDiez() {
        List<Producto> productosAdecuado = new ArrayList<>();

        for (Producto productosBuenos : hashMaplistaProductos.values()) {
            if (productosBuenos.getCantidadDisponible() >= 10) {
                productosAdecuado.add(productosBuenos);
            }
        }
        return productosAdecuado;
    }
    //2. Obtener la lista de codigos de los productos con una cantidad disponible mayor igual a 10 y menor que 50

    public ArrayList<String> ObtenerCodigoProductosAgotados(int limiteInferior, int limite Superior) {
        ArrayList<String> resultado = new ArrayList<>();
        for (String codigo : hashMaplistaProductos.keySet()) {
            Producto producto = hashMaplistaProductos.get(codigo);
            if (producto.getCantidadDisponible() >= 10 && producto.getCantidadDisponible() < 50) {
                resultado.add(codigo);
            }
        }
        return resultado;
    }

    //3. Obtener la lista de clientes que hayan comprado el 7 de octubre de 2026
    public Arraylist<Cliente> ObtenerClientesCompras(LocalDate fechaConsulta) {
        ArraList<Cliente> listaClientes = new Arraylist<>();

        for (Factura factura : listaFacturas) {
            if (factura.fecha().isEqual(fechaConsulta)) {
                listaClientes.add(factura.cliente());
            }
        }
        return listaClientes;
    }

    //4. obtener las facturas que tenga un cliente donde su nombre empiece con R
    public ArrayList<Factura> obtenerFacturasClienteConR(){
        ArrayList<Factura> resultado = new ArrayList<>();

        for (Factura factura : listaFacturas){
            if(factura.tieneClienteConR()){
                resultado.add(factura);
            }
        }
        return resultado;

    }
//punto 5:   Obtener las facturas donde se haya comprado un celular de marca Iphone 16 pro max

//punto 6:   Obtener las facturas que tenga un cliente
// donde su nombre sea juan y haya comprado un celular de marca Iphone 16 pro max


// Punto 7: Implementar un método que reciba una categoría
//  y retorne todos los productos registrados que pertenezcan a ella.

//punto 8 : Implementar un método que reciba un precio mínimo y un precio máximo, y retorne
// los productos cuyo precio se encuentre dentro de ese rango, incluyendo ambos límites.

//punto 9: Implementar un método que retorne todos los productos
// registrados en la tienda, ordenados de menor a mayor según su precio.

//punto 10: Implementar un método que identifique el producto con el precio más alto de la tienda.
// Si no existen productos registrados, el método debe retornar un Optional vacío.

//Punto 11: Implementar un método que reciba el nombre de una ciudad y retorne
// todos los clientes que residan en ella.
// La búsqueda debe realizarse sin diferenciar entre mayúsculas y minúsculas.


