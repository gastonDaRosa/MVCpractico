package ort.da.mvc.facturas.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ort.da.mvc.facturas.Servicios.SistemaStock;
import ort.da.mvc.facturas.dto.ProductoDto;
import ort.da.mvc.facturas.dto.ProveedorDto;
import ort.da.mvc.facturas.modelo.Producto;
import ort.da.mvc.facturas.modelo.Proveedor;
import ort.da.mvc.facturas.modelo.Respuesta;

@RestController
@RequestMapping("/productos")
public class ControladorProductos {
    
    private Producto producto = null;

    @PostMapping("/vistaConectada")
    public List<Respuesta> vistaConectada() {
       return Respuesta.lista(productos(),
                              new Respuesta("habilitarIngreso",false),
                              proveedores());
    }

    @PostMapping("/ingresarNombre")
    public List<Respuesta> ingresarNombre(@RequestParam String nombreBsc) {
       producto = new Producto();
       if(!producto.setNombre(nombreBsc)){
           return Respuesta.lista(mensaje("Nombre incorrecto"));
       }
       Producto tmp = SistemaStock.getInstancia().buscarProducto(nombreBsc);
       if(tmp!=null){
           return Respuesta.lista(mensaje("Ya existe el producto"),
                                  producto(tmp));
       }
       return Respuesta.lista(new Respuesta("habilitarIngreso",true));
    }
    
    @PostMapping("/guardarProducto")
    public List<Respuesta> guardarProducto(@RequestParam int precio, @RequestParam int unidades, @RequestParam String proveedoresSelect) {
        if(producto == null)  return Respuesta.lista(mensaje("No se ha ingresado un nombre"));
        if (precio < 1) {
            return Respuesta.lista(mensaje("No se pudo agregar el producto"),
            mensaje("El precio no puede ser menor a 1"));
        }
        producto.setPrecio(precio) ;
        if (unidades < 1) {
            return Respuesta.lista(mensaje("No se pudo agregar el producto"),
            mensaje("Las unidades no pueden ser menor a 1"));
        }
        producto.setUnidades(unidades);
        Proveedor prov = SistemaStock.getInstancia().buscarProveedor(proveedoresSelect);
        if (prov == null) {
            return Respuesta.lista(mensaje("No se pudo agregar el producto"),
            mensaje("No se pudo encontrar el proveedor"));
        }
        producto.setProveedor(prov);
        if(SistemaStock.getInstancia().altaProducto(producto)){
            producto = null;
            return Respuesta.lista(productos(),
                                   new Respuesta("limpiarEntradas",true),
                                   new Respuesta("habilitarIngreso",false));
        }else{
            return Respuesta.lista(mensaje("No se pudo agregar el producto"));
        }
    }


    private Respuesta productos() {
        
        return new Respuesta("productos",
                      ProductoDto.listaDtos(SistemaStock.getInstancia().getProductos()));
        
    }
    private Respuesta proveedores() {
        
        return new Respuesta("proveedores",
                      ProveedorDto.listaDtos(SistemaStock.getInstancia().getProveedores()));
        
    }
    private Respuesta mensaje(String texto) {
        return new Respuesta("mensaje",texto);
        
    }
    private Respuesta producto(Producto p) {
        
        return new Respuesta("producto",
                      new ProductoDto(p));
        
    }
}
