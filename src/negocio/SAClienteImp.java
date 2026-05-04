package negocio;

import java.util.Set;

import integracion.DAOCliente;
import integracion.DAOFactory;

public class SAClienteImp implements SACliente {

    @Override
    public int create(TCliente tCliente) {
        int id = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOCliente daoCliente = daoFactory.createCliente();
        TCliente existing = daoCliente.readByNIF(tCliente.getNIF());

        if (existing == null) {
            id = daoCliente.create(tCliente);
        }
        return id;
    }

    @Override
    public TCliente read(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOCliente daoCliente = daoFactory.createCliente();
        return daoCliente.read(id);
    }

    @Override
    public Set<TCliente> readAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOCliente daoCliente = daoFactory.createCliente();
        return daoCliente.readAll();
    }

    @Override
    public int update(TCliente tCliente) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOCliente daoCliente = daoFactory.createCliente();
        //aquí creo q falta comrpobar si está, y si no, crea uno nuevo con los datos proporcionados
        
        return daoCliente.update(tCliente);
    }

    @Override
    public int delete(int id) {
        int result = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOCliente daoCliente = daoFactory.createCliente();
        TCliente tCliente = daoCliente.read(id);
        if (tCliente != null) {
            result = daoCliente.delete(id);
        }
        return result;
    }

    @Override
    public TCliente readByNIF(String NIF) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOCliente daoCliente = daoFactory.createCliente();
        return daoCliente.readByNIF(NIF);
    }
}
