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
    public String getNombre() { return nombre; }
    public String getNit() { return nit; }
    public String getTelefono() { return telefono; }

    public List<Cliente> getListaClientes() { return listaClientes; }
    public List<Factura> getListaFacturas() { return listaFacturas; }
    public Map<String, Producto> getListaProductos() { return listaProductos; }

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
    public List<Producto> obtenerProductosConCantidadDisponibleMayorIgualA10() {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : listaProductos.values()) {
            if (producto.getCantidadDisponible() >= 10) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }
    //2. Obtener la lista de codigos de 
}