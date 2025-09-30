/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.da.mvc.facturas.Servicios;

import java.util.ArrayList;

import ort.da.mvc.facturas.modelo.Cliente;
import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.Producto;

/**
 *
 * @author magda
 */
public class SistemaFacturas {

    private static SistemaFacturas instancia;
    private ArrayList<Factura> facturas = new ArrayList();
    private int totalFacturado = 0;
    private Factura facturaEnCurso = null;

    public static SistemaFacturas getInstancia() {

        if (instancia == null) {
            instancia = new SistemaFacturas();
        }
        return instancia;
    }

    private SistemaFacturas() {

    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public boolean agregar(Factura unaFactura) {
        facturas.add(unaFactura);
        return true;
    }

    public boolean clienteComproProducto(Cliente c, Producto p) {
        boolean ret = false;
        for (Factura f : facturas) {
            if (f.getCliente().equals(c) && f.tieneProducto(p)) {
                ret = true;
            }
        }

        return ret;
    }

    public int getTotalFacturado() {
        return totalFacturado;
    }

    public boolean iniciarFactura(Cliente cliente) {
        if (facturaEnCurso != null)
            return false;
        facturaEnCurso = new Factura(cliente);
        return true;
    }

    public boolean agregarProductoAFactura(Producto p, int cantidad) {
        if (facturaEnCurso == null)
            return false;
        boolean ok = facturaEnCurso.agregarProducto(p, cantidad);
        return ok;
    }

    public boolean confirmarFactura() {
        if (facturaEnCurso == null || !facturaEnCurso.tieneLineas())
            return false;
        facturaEnCurso.descontarStock();
        facturas.add(facturaEnCurso);
        totalFacturado += facturaEnCurso.total();
        facturaEnCurso = null;
        return true;
    }

    public void descartarFactura() {
        facturaEnCurso = null;
    }

    public Factura getFacturaEnCurso() {
        return facturaEnCurso;
    }

    public int getTotalFacturado(Cliente cliente) {
        int total = 0;
        for (Factura f : facturas) {
            if (f.getCliente().equals(cliente)) {
                total += f.total();
            }
        }
        return total;
    }
}
