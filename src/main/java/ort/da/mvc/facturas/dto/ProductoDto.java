package ort.da.mvc.facturas.dto;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.facturas.modelo.Producto;

public class ProductoDto {
    private String nombre;
    private int precio;
    private int unidades;
    private String proveedor;
    private int proveedorId;


    public ProductoDto(Producto p) {
        this.nombre = p.getNombre();
        this.precio = p.getPrecio();
        this.unidades = p.getUnidades();
        this.proveedor = p.getProveedor() != null ? p.getProveedor().getNombre() : null;
        this.proveedorId = p.getProveedor() != null ? p.getProveedor().getId() : null;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getPrecio() {
        return precio;
    }


    public void setPrecio(int precio) {
        this.precio = precio;
    }


    public int getUnidades() {
        return unidades;
    }


    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }


    public int getProveedorId() {
        return proveedorId;
    }


    public void setProveedorId(int proveedor) {
        this.proveedorId = proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }


    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public static List<ProductoDto> listaDtos(List<Producto> lista){
        List<ProductoDto> dtos = new ArrayList<>();
        for(Producto p: lista){
           dtos.add(new ProductoDto(p));
        }
        return dtos;
    }

    
}
