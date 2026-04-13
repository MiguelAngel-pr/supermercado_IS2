package negocio;

import java.io.Serializable;

public class TProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private double precio;
    private String fechaCaducidad;
    private int cantidad;
    private int idMarca;
    private boolean activo;

    public TProducto(int id, String nombre, double precio, String fechaCaducidad, int cantidad, int idMarca, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
        this.idMarca = idMarca;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getFechaCaducidad() { return fechaCaducidad; }
    public int getCantidad() { return cantidad; }
    public int getIdMarca() { return idMarca; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Id: " + id + ", Nombre: " + nombre + ", Precio: " + precio +
               ", FechaCaducidad: " + fechaCaducidad + ", Cantidad: " + cantidad +
               ", IdMarca: " + idMarca + ", Activo: " + activo + "\n";
    }
}
