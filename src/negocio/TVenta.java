package negocio;

import java.io.Serializable;

public class TVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String fecha;
    private double importe;
    private TCliente cliente;
    private int idEmpleado;

    public TVenta(int id, String fecha, double importe, TCliente cliente, int idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.importe = importe;
        this.cliente = cliente;
        this.idEmpleado = idEmpleado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFecha() { return fecha; }
    public double getImporte() { return importe; }
    public TCliente getCliente() { return cliente; }
    public int getIdEmpleado() { return idEmpleado; }

    @Override
    public String toString() {
        return "Id: " + id + ", Fecha: " + fecha + ", Importe: " + importe +
               ", ClienteID: " + (cliente != null ? cliente.getId() : "null") + ", IdEmpleado: " + idEmpleado + "\n";
    }
}