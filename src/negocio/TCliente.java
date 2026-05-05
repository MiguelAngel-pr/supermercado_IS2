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

    public TCliente(int id, String nombre, String apellidos, String NIF, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.NIF = NIF;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getNIF() { return NIF; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return "Id: " + id + ", Nombre: " + nombre + ", Apellidos: " + apellidos +
               ", NIF: " + NIF + ", Email: " + email + ", Telefono: " + telefono + "\n";
    }
}
