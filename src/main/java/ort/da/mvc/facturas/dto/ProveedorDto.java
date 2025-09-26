package ort.da.mvc.facturas.dto;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.facturas.modelo.Proveedor;

public class ProveedorDto {
    private int id;
    private String nombre;

    public ProveedorDto(Proveedor prov) {
        this.nombre = prov.getNombre();
        this.id = prov.getId();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static List<ProveedorDto> listaDtos(List<Proveedor> lista){
        List<ProveedorDto> dtos = new ArrayList<>();
        for(Proveedor p: lista){
           dtos.add(new ProveedorDto(p));
        }
        return dtos;
    }


    
}
