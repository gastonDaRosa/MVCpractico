/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.da.mvc.facturas.Servicios;

import java.util.ArrayList;

import ort.da.mvc.facturas.modelo.Cliente;
import ort.da.mvc.facturas.modelo.Producto;

/**
 *
 * @author magda
 */
public class SistemaClientes {

    private static SistemaClientes instancia;

    private ArrayList<Cliente> clientes = new ArrayList();

    public static SistemaClientes getInstancia() {

        if (instancia == null) {
            instancia = new SistemaClientes();
        }
        return instancia;
    }

    private SistemaClientes() {

    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Cliente> clientesNoCompraronProductoMenorPrecio() {
        return clientesCompraronOnoProductoMenorPrecio(false);
    }
    public ArrayList<Cliente> clientesCompraronProductoMenorPrecio() {
        return clientesCompraronOnoProductoMenorPrecio(true);
    }

    private ArrayList<Cliente> clientesCompraronOnoProductoMenorPrecio(boolean compraron) {
        Producto menor = SistemaStock.getInstancia().getProductoMenorPrecio();
        ArrayList<Cliente> retorno = new ArrayList();

        if (menor == null) return retorno;
        for (Cliente c : clientes) {
            if (SistemaFacturas.getInstancia().clienteComproProducto(c, menor) == compraron) {
                retorno.add(c);
            }

        }
        return retorno;

    }

    public boolean existeCliente(String unaCedula) {
        return buscarCliente(unaCedula) != null;
    }

    public Cliente buscarCliente(String unaCedula) {
        for (Cliente c : clientes) {
            if (c.getCedula().equals(unaCedula)) {
                return c;
            }
        }
        return null;
    }

    public boolean agregar(Cliente c) {
        if (c == null)
            return false;
        boolean ok = false;

        if (c.validar() && !this.existeCliente(c.getCedula())) {
            clientes.add(c);
            ok = true;

        }

        return ok;
    }

}
