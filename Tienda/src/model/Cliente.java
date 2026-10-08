package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {

    private final String documentoIdentidad;
    private final String nombreCompleto;
    private final Tienda ownedByTienda;
    private final String telefono;
    private final String correo;
    private final String ciudadResidencia;
    private final List<Factura> listaFacturas;

    public Cliente(String documentoIdentidad, String nombreCompleto,
                   Tienda ownedByTienda, String telefono,
                   String correo, String ciudadResidencia, List<Factura> listaFacturas) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.ownedByTienda = ownedByTienda;
        this.telefono = telefono;
        this.correo = correo;
        this.ciudadResidencia = ciudadResidencia;
        this.listaFacturas = listaFacturas;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public List<Factura> getListaFacturas() {
        return Collections.unmodifiableList(listaFacturas);
    }

    public Tienda getOwnedByTienda() {
        return ownedByTienda;
    }

}