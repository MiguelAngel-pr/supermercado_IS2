package integracion;

import java.util.Set;
import negocio.TTrabajador;

public interface DAOTrabajador {
    int create(TTrabajador tTrabajador);
    TTrabajador read(int id);
    TTrabajador readByNIF(String NIF);
    Set<TTrabajador> readAll();
    int update(TTrabajador tTrabajador);
    int delete(int id);
}
