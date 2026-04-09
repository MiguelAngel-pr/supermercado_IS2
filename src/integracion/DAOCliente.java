package integracion;

import java.util.Set;
import negocio.TCliente;

public interface DAOCliente {
    int create(TCliente tCliente);
    TCliente read(int id);
    Set<TCliente> readAll();
    int update(TCliente tCliente);
    int delete(int id);
    TCliente readByNIF(String NIF);
}
