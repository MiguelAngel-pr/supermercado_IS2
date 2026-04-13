package negocio;

import java.util.Set;

public interface SAProducto {
    int create(TProducto tProducto);
    TProducto read(int id);
    Set<TProducto> readAll();
    Set<TProducto> readByMarca(int idMarca);
    Set<TProducto> readByVenta(int idVenta);
    int update(TProducto tProducto);
    int delete(int id);
}
