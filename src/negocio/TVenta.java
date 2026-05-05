package negocio;

import java.io.Serializable;

public class TVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String fecha;
    private double importe;
    private int idCliente;
    private int idEmpleado;

    public TVenta(int id, String fecha, double importe, int idCliente, int idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.importe = importe;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFecha() { return fecha; }
    public double getImporte() { return importe; }
    public int getIdCliente() { return idCliente; }
    public int getIdEmpleado() { return idEmpleado; }

    @Override
    public String toString() {
        return "Id: " + id + ", Fecha: " + fecha + ", Importe: " + importe +
               ", IdCliente: " + idCliente + ", IdEmpleado: " + idEmpleado + "\n";
    }
}
