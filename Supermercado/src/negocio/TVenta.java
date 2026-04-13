package negocio;

import java.io.Serializable;

public class TVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String fecha;
    private double importe;
    private int idCliente;
    private int idEmpleado;
    private boolean activo;

    public TVenta(int id, String fecha, double importe, int idCliente, int idEmpleado, boolean activo) {
        this.id = id;
        this.fecha = fecha;
        this.importe = importe;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFecha() { return fecha; }
    public double getImporte() { return importe; }
    public int getIdCliente() { return idCliente; }
    public int getIdEmpleado() { return idEmpleado; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Id: " + id + ", Fecha: " + fecha + ", Importe: " + importe +
               ", IdCliente: " + idCliente + ", IdEmpleado: " + idEmpleado +
               ", Activo: " + activo + "\n";
    }
}
