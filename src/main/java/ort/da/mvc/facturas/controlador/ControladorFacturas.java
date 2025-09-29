package ort.da.mvc.facturas.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ort.da.mvc.facturas.Servicios.SistemaClientes;
import ort.da.mvc.facturas.Servicios.SistemaFacturas;
import ort.da.mvc.facturas.Servicios.SistemaStock;
import ort.da.mvc.facturas.dto.FacturaDto;
import ort.da.mvc.facturas.modelo.Cliente;
import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.Producto;
import ort.da.mvc.facturas.modelo.Respuesta;

@RestController
@RequestMapping("/facturas")
public class ControladorFacturas {
    private Factura factura = null;
    
    @PostMapping("/vistaConectada")
public List<Respuesta> vistaConectada() {
    return Respuesta.lista(
        new Respuesta("totalFacturado", "$" + SistemaFacturas.getInstancia().getTotalFacturado()),
        new Respuesta("habilitarIngreso", false)
    );
}

@PostMapping("/iniciarFactura")
public List<Respuesta> iniciarFactura(@RequestParam String cedula) {
    Cliente cliente = SistemaClientes.getInstancia().buscarCliente(cedula);
    if (cliente == null) return Respuesta.lista(mensaje( "No se pudo iniciar la factura, verifique la cédula"));

    if (!SistemaFacturas.getInstancia().iniciarFactura(cliente))
        return Respuesta.lista(mensaje( "Debe finalizar la factura actual antes de iniciar otra"));

    Factura f = SistemaFacturas.getInstancia().getFacturaEnCurso();
    return Respuesta.lista(
        new Respuesta("habilitarIngreso", true),
        new Respuesta("factura", new FacturaDto(f))
    );
}

@PostMapping("/agregarProducto")
public List<Respuesta> agregarProducto(@RequestParam String nombreProducto, @RequestParam int cantidad) {
    Producto p = SistemaStock.getInstancia().buscarProducto(nombreProducto);
    if (p == null || cantidad < 1 || p.getUnidades() < cantidad)
        return Respuesta.lista(mensaje( "No se pudo agregar el producto"));

    boolean ok = SistemaFacturas.getInstancia().agregarProductoAFactura(p, cantidad);
    if (!ok) return Respuesta.lista(mensaje( "Debe iniciar la factura antes de agregar productos"));

    Factura f = SistemaFacturas.getInstancia().getFacturaEnCurso();
    return Respuesta.lista(new Respuesta("factura", new FacturaDto(f)));
}

@PostMapping("/confirmarFactura")
public List<Respuesta> confirmarFactura() {
    boolean ok = SistemaFacturas.getInstancia().confirmarFactura();
    if (!ok) return Respuesta.lista(mensaje( "No se pudo confirmar la factura, verifique si hay productos"));

    return Respuesta.lista(
        mensaje("Se guardó correctamente"),
        new Respuesta("totalFacturado", "$" + SistemaFacturas.getInstancia().getTotalFacturado()),
        new Respuesta("limpiarEntradas", true),
        new Respuesta("habilitarIngreso", false)
    );
}

@PostMapping("/descartarFactura")
public List<Respuesta> descartarFactura() {
    SistemaFacturas.getInstancia().descartarFactura();
    return Respuesta.lista(
        mensaje("Factura descartada"),
        new Respuesta("limpiarEntradas", true),
        new Respuesta("habilitarIngreso", false)
    );
}


    private Respuesta facturas() {
        
        return new Respuesta("facturas",
                      FacturaDto.listaDtos(SistemaFacturas.getInstancia().getFacturas()));
        
    }
    private Respuesta mensaje(String texto) {
        return new Respuesta("mensaje",texto);
        
    }
    private Respuesta factura(Factura c) {
        
        return new Respuesta("factura",
                      new FacturaDto(c));
        
    }
}
