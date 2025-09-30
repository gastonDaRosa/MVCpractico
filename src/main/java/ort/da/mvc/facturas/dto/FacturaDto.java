package ort.da.mvc.facturas.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.LineaFactura;

public class FacturaDto {
    private String codigo;
    private String clienteNombre;
    private String fecha;
    private int total;
    private List<LineaFacturaDto> lineas = new ArrayList<>();

    public FacturaDto(Factura f) {
        this.codigo = f.getCodigo();
        this.clienteNombre = f.getCliente().getNombre();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.fecha = sdf.format(f.getFecha());
        this.total = f.total();
        for (LineaFactura lf : f.getLineas()) {
            lineas.add(new LineaFacturaDto(lf));
        }
    }

    public String getCodigo() {
        return codigo;
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

    public List<LineaFacturaDto> getLineas() {
        return lineas;
    }

    public static List<FacturaDto> listaDtos(List<Factura> facturas) {
        List<FacturaDto> dtos = new ArrayList<>();
        for (Factura f : facturas) {
            dtos.add(new FacturaDto(f));
        }
        return dtos;
    }
}
