package integracion;

import java.util.Set;
import negocio.TVentaProducto;

public interface DAOVentaProducto {
    int create(TVentaProducto tVentaProducto);
    Set<TVentaProducto> readByVenta(int idVenta);
}
