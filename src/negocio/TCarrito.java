package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TCarrito implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idEmpleado;
    private TCliente cliente;
    private List<TVentaProducto> items;

    public TCarrito(int idEmpleado, TCliente cliente) {
        this.idEmpleado = idEmpleado;
        this.cliente = cliente;
        this.items = new ArrayList<>();
    }

    public int getIdEmpleado() { return idEmpleado; }
    public TCliente getCliente() { return cliente; }
    public List<TVentaProducto> getItems() { return items; }

    public void addItem(TVentaProducto item) {
        items.add(item);
    }

    public double calcularImporte(List<TProducto> productos) {
        if (productos == null) return 0;
        double total = 0;
        for (TVentaProducto item : items) {
            for (TProducto p : productos) {
                if (p.getId() == item.getIdProducto()) {
                    total += p.getPrecio() * item.getCantidad();
                    break;
                }
            }
        }
        return total;
    }
}