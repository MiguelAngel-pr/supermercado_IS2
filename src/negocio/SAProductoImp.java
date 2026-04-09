package negocio;

import java.util.Set;

import integracion.DAOFactory;
import integracion.DAOProducto;

public class SAProductoImp implements SAProducto {

    @Override
    public int create(TProducto tProducto) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProducto daoProducto = daoFactory.createProducto();
        return daoProducto.create(tProducto);
    }

    @Override
    public TProducto read(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProducto daoProducto = daoFactory.createProducto();
        return daoProducto.read(id);
    }

    @Override
    public Set<TProducto> readAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProducto daoProducto = daoFactory.createProducto();
        return daoProducto.readAll();
    }

    @Override
    public Set<TProducto> readByMarca(int idMarca) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProducto daoProducto = daoFactory.createProducto();
        return daoProducto.readByMarca(idMarca);
    }

    @Override
    public Set<TProducto> readByVenta(int idVenta) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProducto daoProducto = daoFactory.createProducto();
        return daoProducto.readByVenta(idVenta);
    }

    @Override
    public int update(TProducto tProducto) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProducto daoProducto = daoFactory.createProducto();
        return daoProducto.update(tProducto);
    }

    @Override
    public int delete(int id) {
        int result = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProducto daoProducto = daoFactory.createProducto();
        TProducto tProducto = daoProducto.read(id);
        if (tProducto != null && tProducto.getActivo()) {
            result = daoProducto.delete(id);
        }
        return result;
    }
}
