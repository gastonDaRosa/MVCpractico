package ort.da.mvc.facturas.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ort.da.mvc.facturas.Servicios.SistemaStock;
import ort.da.mvc.facturas.dto.ProductoDto;
import ort.da.mvc.facturas.modelo.Producto;
import ort.da.mvc.facturas.modelo.Respuesta;

@RestController
@RequestMapping("/productos")
public class ControladorProductos {
    
    private Producto producto = null;

    @PostMapping("/vistaConectada")
    public List<Respuesta> vistaConectada() {
       return Respuesta.lista(productos(), 
                              new Respuesta("habilitarIngreso",false));
    }

    @PostMapping("/ingresarNombre")
    public List<Respuesta> ingresarNombre(@RequestParam String nombre) {
       producto = new Producto();
       if(!producto.setNombre(nombre)){
           return Respuesta.lista(mensaje("Cedula incorrecta"));
       }
       Producto tmp = SistemaStock.getInstancia().buscarProducto(nombre);
       if(tmp!=null){
           return Respuesta.lista(mensaje("Ya existe el producto"),
                                  producto(tmp));
       }
       return Respuesta.lista(new Respuesta("habilitarIngreso",true));
    }
    @PostMapping("/guardarProducto")
    public List<Respuesta> guardarProducto(@RequestParam String nombre,@RequestParam int precio, @RequestParam int unidades) {
        if(producto == null)  return Respuesta.lista(mensaje("No se ha ingresado un nombre"));
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setUnidades(unidades);
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
    private Respuesta mensaje(String texto) {
        return new Respuesta("mensaje",texto);
        
    }
    private Respuesta producto(Producto p) {
        
        return new Respuesta("producto",
                      new ProductoDto(p));
        
    }
}
