package ort.da.mvc.facturas.modelo;

public class Producto {
    // Contador estático de IDs
    private static int ultimoId = 0;

    private int codUni;
    private String nombre;
    private int precio;
    private int unidades;
    private Proveedor proveedor;

    // Constructor vacío
    public Producto() {
        this.codUni = ++ultimoId; // se autoincrementa
    }

    // Constructor con datos
    public Producto(String nombre, int precio, int stock, Proveedor proveedor) {
        this.codUni = ++ultimoId; // se autoincrementa
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = stock;
        this.proveedor = proveedor;
    }

    // Getter solo lectura
    public int getCodUni() {
        return codUni;
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
        if (verificarNombre(unNombre)) {
            this.nombre = unNombre;
            return true;
        }
        return false;
    }

    private boolean verificarNombre(String nombre) {
        return nombre != null && nombre.length() < 50;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codUni=" + codUni +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", unidades=" + unidades +
                ", proveedor=" + (proveedor != null ? proveedor.getNombre() : "-") +
                '}';
    }
}
