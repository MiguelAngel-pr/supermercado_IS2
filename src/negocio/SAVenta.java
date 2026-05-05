package negocio;

import java.util.Set;

public interface SAVenta {
    TCarrito abrir();
    int cerrar(TCarrito tCarrito);
    TOAVenta read(int id);
    Set<TVenta> readAll();
    Set<TVenta> readByFecha(String fecha);
    Set<TVenta> readByImporte(double importe);
    Set<TVenta> readByIdCliente(int idCliente);
    Set<TVenta> readByIdEmpleado(int idEmpleado);
    int update(TVenta tVenta);
    int devolver(TVenta tVenta);
}
