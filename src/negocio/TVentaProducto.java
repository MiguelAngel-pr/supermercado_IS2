package negocio;

import java.io.Serializable;

public class TVentaProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idVenta;
    private int idProducto;
    private int cantidad;

    public TVentaProducto(int idVenta, int idProducto, int cantidad) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdVenta() { return idVenta; }
    public int getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return "IdVenta: " + idVenta + ", IdProducto: " + idProducto + ", Cantidad: " + cantidad + "\n";
    }
}
