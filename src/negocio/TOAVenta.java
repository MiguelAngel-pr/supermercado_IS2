package negocio;

import java.util.Set;

public class TOAVenta {

    private TVenta tVenta;
    private Set<TVentaProducto> tVentaProductos;

    public TOAVenta(TVenta tVenta, Set<TVentaProducto> tVentaProductos) {
        this.tVenta = tVenta;
        this.tVentaProductos = tVentaProductos;
    }

    public TVenta getTVenta() { return tVenta; }
    public Set<TVentaProducto> getTVentaProductos() { return tVentaProductos; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta: ").append(tVenta);
        sb.append("Productos: ");
        for (TVentaProducto p : tVentaProductos) {
            sb.append("[IdProducto: ").append(p.getIdProducto())
              .append(", Cantidad: ").append(p.getCantidad()).append("] ");
        }
        return sb.toString();
    }
}
