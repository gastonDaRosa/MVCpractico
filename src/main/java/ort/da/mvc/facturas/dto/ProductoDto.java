package ort.da.mvc.facturas.dto;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.facturas.modelo.Producto;

public class ProductoDto {
    
    private String nombre;
    private int precio;
    private int unidades;
    private String proveedor;
    private int codigo;

    public ProductoDto(Producto p) {
        this.nombre = p.getNombre();
        this.precio = p.getPrecio();
        this.unidades = p.getUnidades();
        this.proveedor = p.getProveedor() != null ? p.getProveedor().getNombre() : null;
        this.codigo = p.getCodUni();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public String getProveedor() {
        return proveedor;
    }

    public static List<ProductoDto> listaDtos(List<Producto> lista) {
        List<ProductoDto> dtos = new ArrayList<>();
        for (Producto p : lista) {
            dtos.add(new ProductoDto(p));
        }
        return dtos;
    }
}
