package model;

public class DetalleFactura {

    private final int cantidadComprada;
    private final double subTotal;
    private final Producto producto;
    private final Factura owndByFactura;

    public DetalleFactura(int cantidadComprada, double subTotal, Producto producto, Factura owndByFactura) {
        this.cantidadComprada = cantidadComprada;
        this.subTotal = subTotal;
        this.producto = producto;
        this.owndByFactura = owndByFactura;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public Factura getOwndByFactura() {
        return owndByFactura;
    }
}