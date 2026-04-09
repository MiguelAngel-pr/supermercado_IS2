package negocio;

import java.io.Serializable;

public class TMarca implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private boolean activo;

    public TMarca(int id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Id: " + id + ", Nombre: " + nombre + ", Activo: " + activo + "\n";
    }
}
