package integracion;

import java.util.Set;
import negocio.TProducto;

public interface DAOProducto {
    int create(TProducto tProducto);
    TProducto read(int id);
    Set<TProducto> readAll();
    Set<TProducto> readByMarca(int idMarca);
    Set<TProducto> readByVenta(int idVenta);
    int update(TProducto tProducto);
    int delete(int id);
}
