package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TCarrito implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idEmpleado;
    private int idCliente;
    private List<TVentaProducto> items;

    public TCarrito(int idEmpleado, int idCliente) {
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.items = new ArrayList<>();
    }

    public int getIdEmpleado() { return idEmpleado; }
    public int getIdCliente() { return idCliente; }
    public List<TVentaProducto> getItems() { return items; }

    public void addItem(TVentaProducto item) {
        items.add(item);
    }
}