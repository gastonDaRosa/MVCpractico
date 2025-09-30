package ort.da.mvc.facturas.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.LineaFactura;

public class FacturaLineaDto {
    private String clienteNombre;
    private String fecha;
    private int total;
    //private List<LineaFacturaDto> lineas = new ArrayList<>();
    private String lineasTexto;

    public FacturaLineaDto(Factura f) {
        this.clienteNombre = f.getCliente().getNombre();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.fecha = sdf.format(f.getFecha());
        this.total = f.total();
        StringBuilder sb = new StringBuilder();
        for (LineaFactura lf : f.getLineas()) {
            sb.append(lf.getCantidad())
                    .append(" x ")
                    .append(lf.getProducto().getNombre())
                    .append(" = ")
                    .append(lf.total())
                    .append("<br>");
        }
        this.lineasTexto = sb.toString();
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public int getTotal() {
        return total;
    }

    //public List<LineaFacturaDto> getLineas() { return lineas; }
    public String getLineasTexto() { return lineasTexto; }

    // Método estático para listas
    public static List<FacturaLineaDto> listaDtos(List<Factura> facturas) {
        List<FacturaLineaDto> dtos = new ArrayList<>();
        for (Factura f : facturas) {
            dtos.add(new FacturaLineaDto(f));
        }
        return dtos;
    }
}
