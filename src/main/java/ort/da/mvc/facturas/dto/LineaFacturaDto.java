package ort.da.mvc.facturas.dto;

import ort.da.mvc.facturas.modelo.LineaFactura;

public class LineaFacturaDto {
    private String productoNombre;
    private int cantidad;
    private int total;

    public LineaFacturaDto(LineaFactura lf) {
        this.productoNombre = lf.getProducto().getNombre();
        this.cantidad = lf.getCantidad();
        this.total = lf.total();
    }

    public String getProductoNombre() { return productoNombre; }
    public int getCantidad() { return cantidad; }
    public int getTotal() { return total; }
}

