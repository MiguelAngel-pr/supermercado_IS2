package negocio;

import java.io.Serializable;

public abstract class TTrabajador implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String apellidos;
    private String NIF;
    private String jornada;
    private boolean isAdministrador;

    public TTrabajador(int id, String nombre, String apellidos, String NIF, String jornada, boolean isAdministrador) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.NIF = NIF;
        this.jornada = jornada;
        this.isAdministrador = isAdministrador;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getNIF() { return NIF; }
    public String getJornada() { return jornada; }
    public boolean getIsAdministrador() { return isAdministrador; }

    public abstract String getIdsEmpleados();
    public abstract int getIdAdmin();

    @Override
    public String toString() {
        return "Id: " + id + ", Nombre: " + nombre + ", Apellidos: " + apellidos +
               ", NIF: " + NIF + ", Jornada: " + jornada +
               ", Es administrador: " + isAdministrador + "\n";
    }
}
