package negocio;

import java.util.Set;

public interface SATrabajador {
    int create(TTrabajador tTrabajador);
    TTrabajador read(int id);
    TTrabajador readByNIF(String NIF);
    Set<TTrabajador> readAll();
    int update(TTrabajador tTrabajador);
    int delete(int id);
}
