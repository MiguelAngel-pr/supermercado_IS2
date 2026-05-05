package presentacion;

import java.util.List;
import negocio.TCarrito;
import negocio.TProducto;

public class TCarritoConImporte extends TCarrito {

    private static final long serialVersionUID = 1L;
    private final double importeTotal;

    public TCarritoConImporte(int idEmpleado, int idCliente, double importeTotal) {
        super(idEmpleado, idCliente);
        this.importeTotal = importeTotal;
    }

    @Override
    public double calcularImporte(List<TProducto> productos) {
        return importeTotal;
    }
}
