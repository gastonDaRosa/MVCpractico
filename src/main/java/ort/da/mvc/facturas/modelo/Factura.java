package ort.da.mvc.facturas.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Factura {

    private static int ultimoId = 0;

    private String codigo;
    private Cliente cliente;
    private ArrayList<LineaFactura> lineas = new ArrayList<>();
    private Date fecha = new Date();

    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.codigo = "Fac-" + (++ultimoId);
    }

    public String getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<LineaFactura> getLineas() {
        return lineas;
    }

    public boolean agregar(int cantidad, Producto p) {
        LineaFactura nuevaLinea = new LineaFactura(p, cantidad);
        lineas.add(nuevaLinea);
        return true;
    }

    public boolean tieneProducto(Producto unP) {
        for (LineaFactura l : lineas) {
            if (l.getProducto() == unP) {
                return true;
            }
        }
        return false;
    }

    public int total() {
        int total = 0;
        for (LineaFactura lf : lineas) {
            total += lf.total();
        }
        return total;
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (cantidad < 1 || producto.getUnidades() < cantidad) return false;

        for (LineaFactura linea : lineas) {
            if (linea.getProducto().equals(producto)) {
                linea.setCantidad(linea.getCantidad() + cantidad);
                return true;
            }
        }

        lineas.add(new LineaFactura(producto, cantidad));
        return true;
    }

    public void descontarStock() {
        for (LineaFactura linea : lineas) {
            Producto p = linea.getProducto();
            p.setUnidades(p.getUnidades() - linea.getCantidad());
        }
    }

    public boolean tieneLineas() {
        return !lineas.isEmpty();
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo='" + codigo + '\'' +
                ", cliente=" + cliente +
                ", lineas=" + lineas +
                '}';
    }
}
