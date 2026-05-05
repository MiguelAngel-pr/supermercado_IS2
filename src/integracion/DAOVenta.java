package integracion;

import java.util.Set;
import negocio.TCarrito;
import negocio.TVenta;

public interface DAOVenta {
    int create(TVenta tVenta, TCarrito tCarrito);
    TVenta read(int id);
    Set<TVenta> readAll();
    Set<TVenta> readByFecha(String fecha);
    Set<TVenta> readByImporte(double importe);
    Set<TVenta> readByIdCliente(int idCliente);
    Set<TVenta> readByIdEmpleado(int idEmpleado);
    int update(TVenta tVenta);
    int delete(int id);
}
