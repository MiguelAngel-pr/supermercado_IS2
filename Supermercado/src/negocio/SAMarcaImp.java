package negocio;

import java.util.Set;

import integracion.DAOFactory;
import integracion.DAOMarca;

public class SAMarcaImp implements SAMarca {

    @Override
    public int create(TMarca tMarca) {
        int id = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOMarca daoMarca = daoFactory.createMarca();
        TMarca existing = daoMarca.readByName(tMarca.getNombre());

        if (existing == null) {
            id = daoMarca.create(tMarca);
        } else if (!existing.getActivo()) {
            tMarca.setId(existing.getId());
            tMarca.setActivo(true);
            id = daoMarca.update(tMarca);
        }
        return id;
    }

    @Override
    public TMarca read(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOMarca daoMarca = daoFactory.createMarca();
        return daoMarca.read(id);
    }

    @Override
    public TMarca readByName(String nombre) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOMarca daoMarca = daoFactory.createMarca();
        return daoMarca.readByName(nombre);
    }

    @Override
    public Set<TMarca> readAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOMarca daoMarca = daoFactory.createMarca();
        return daoMarca.readAll();
    }

    @Override
    public int update(TMarca tMarca) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOMarca daoMarca = daoFactory.createMarca();
        return daoMarca.update(tMarca);
    }

    @Override
    public int delete(int id) {
        int result = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOMarca daoMarca = daoFactory.createMarca();
        TMarca tMarca = daoMarca.read(id);
        if (tMarca != null && tMarca.getActivo()) {
            result = daoMarca.delete(id);
        }
        return result;
    }
}
