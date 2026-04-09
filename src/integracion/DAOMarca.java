package integracion;

import java.util.Set;
import negocio.TMarca;

public interface DAOMarca {
    int create(TMarca tMarca);
    TMarca read(int id);
    TMarca readByName(String nombre);
    Set<TMarca> readAll();
    int update(TMarca tMarca);
    int delete(int id);
}
