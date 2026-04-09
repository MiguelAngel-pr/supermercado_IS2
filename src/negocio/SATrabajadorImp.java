package negocio;

import java.util.Set;

import integracion.DAOFactory;
import integracion.DAOTrabajador;

public class SATrabajadorImp implements SATrabajador {

    @Override
    public int create(TTrabajador tTrabajador) {
        int id = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOTrabajador daoTrabajador = daoFactory.createTrabajador();
        TTrabajador existing = daoTrabajador.readByNIF(tTrabajador.getNIF());

        if (existing == null) {
            id = daoTrabajador.create(tTrabajador);
        } else if (!existing.getActivo()) {
            tTrabajador.setId(existing.getId());
            tTrabajador.setActivo(true);
            id = daoTrabajador.update(tTrabajador);
        }
        return id;
    }

    @Override
    public TTrabajador read(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOTrabajador daoTrabajador = daoFactory.createTrabajador();
        return daoTrabajador.read(id);
    }

    @Override
    public TTrabajador readByNIF(String NIF) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOTrabajador daoTrabajador = daoFactory.createTrabajador();
        return daoTrabajador.readByNIF(NIF);
    }

    @Override
    public Set<TTrabajador> readAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOTrabajador daoTrabajador = daoFactory.createTrabajador();
        return daoTrabajador.readAll();
    }

    @Override
    public int update(TTrabajador tTrabajador) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOTrabajador daoTrabajador = daoFactory.createTrabajador();
        return daoTrabajador.update(tTrabajador);
    }

    @Override
    public int delete(int id) {
        int result = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOTrabajador daoTrabajador = daoFactory.createTrabajador();
        TTrabajador tTrabajador = daoTrabajador.read(id);
        if (tTrabajador != null && tTrabajador.getActivo()) {
            result = daoTrabajador.delete(id);
        }
        return result;
    }
}
