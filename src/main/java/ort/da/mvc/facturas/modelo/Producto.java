/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.da.mvc.facturas.modelo;

/**
 *
 * @author magda
 */
public class Producto {
    private String nombre;
    private int precio;
    private int unidades;
    private Proveedor proveedor;
    private int codUni;
  

    public Producto() {
    }
    public Producto(String nombre, int precio, int stock, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = stock;
        this.proveedor = proveedor;
    }

    public int getUnidades() {
        return unidades;
    }
   
    public void setUnidades(int unidades) {
        this.unidades = unidades;
        
    }
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String unNombre) {
       if(verificarNombre(unNombre)){
            nombre = unNombre;
            return true;
       }return false;
       
    }
    
    public boolean verificarNombre(String nombre){
        boolean ok = false;
        if (nombre !=null){
            int digitos = nombre.length();
            String blank = " ";
            boolean sinBlank = true;
            nombre = nombre.toLowerCase();

            for (int x=0;x<nombre.length()&&sinBlank;x++){
                String d = nombre.charAt(x)+"";
                if (!blank.contains(d)){
                    sinBlank = false;
                }
            }
           
            if (sinBlank && digitos<=8){
                ok = true;
            }    
        }
        return ok;        
    
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", unidades=" + unidades + ", proveedor=" + proveedor;
    }

    
    
}
