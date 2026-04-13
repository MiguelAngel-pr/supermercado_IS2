package negocio;

import java.util.Set;

public interface SACliente {
    int create(TCliente tCliente);
    TCliente read(int id);
    Set<TCliente> readAll();
    int update(TCliente tCliente);
    int delete(int id);
    TCliente readByNIF(String NIF);
}
