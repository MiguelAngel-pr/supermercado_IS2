package negocio;

import java.util.Set;

public interface SAMarca {
    int create(TMarca tMarca);
    TMarca read(int id);
    TMarca readByName(String nombre);
    Set<TMarca> readAll();
    int update(TMarca tMarca);
    int delete(int id);
}
