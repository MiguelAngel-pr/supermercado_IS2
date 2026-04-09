package negocio;

import java.io.Serializable;

public class TCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String apellidos;
    private String NIF;
    private String email;
    private String telefono;
    private boolean activo;

    public TCliente(int id, String nombre, String apellidos, String NIF, String email, String telefono, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.NIF = NIF;
        this.email = email;
        this.telefono = telefono;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getNIF() { return NIF; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Id: " + id + ", Nombre: " + nombre + ", Apellidos: " + apellidos +
               ", NIF: " + NIF + ", Email: " + email + ", Telefono: " + telefono +
               ", Activo: " + activo + "\n";
    }
}
