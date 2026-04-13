package negocio;

import java.time.LocalDate;
import java.util.Set;

import integracion.DAOFactory;
import integracion.DAOVenta;
import integracion.DAOVentaProducto;

public class SAVentaImp implements SAVenta {

    @Override
    public TCarrito abrir() {
        // Returns an empty cart; the GUI will fill it with employee/client and products
        return new TCarrito(-1, -1);
    }

    @Override
    public int cerrar(TCarrito tCarrito) {
        if (tCarrito.getIdEmpleado() <= 0 || tCarrito.getIdCliente() <= 0 || tCarrito.getItems().isEmpty()) {
            return 0;
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        DAOVentaProducto daoVentaProducto = daoFactory.createVentaProducto();

        String fecha = LocalDate.now().toString();
        double importe = tCarrito.calcularImporte(null);
        TVenta tVenta = new TVenta(-1, fecha, importe, tCarrito.getIdCliente(), tCarrito.getIdEmpleado(), true);

        int idVenta = daoVenta.create(tVenta, tCarrito);
        if (idVenta > 0) {
            for (TVentaProducto item : tCarrito.getItems()) {
                TVentaProducto vp = new TVentaProducto(idVenta, item.getIdProducto(), item.getCantidad());
                daoVentaProducto.create(vp);
            }
        }
        return idVenta;
    }

    @Override
    public TOAVenta read(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        DAOVentaProducto daoVentaProducto = daoFactory.createVentaProducto();
        TVenta tVenta = daoVenta.read(id);
        if (tVenta == null) return null;
        Set<TVentaProducto> productos = daoVentaProducto.readByVenta(id);
        return new TOAVenta(tVenta, productos);
    }

    @Override
    public Set<TVenta> readAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        return daoVenta.readAll();
    }

    @Override
    public Set<TVenta> readByFecha(String fecha) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        return daoVenta.readByFecha(fecha);
    }

    @Override
    public Set<TVenta> readByImporte(double importe) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        return daoVenta.readByImporte(importe);
    }

    @Override
    public Set<TVenta> readByIdCliente(int idCliente) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        return daoVenta.readByIdCliente(idCliente);
    }

    @Override
    public Set<TVenta> readByIdEmpleado(int idEmpleado) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        return daoVenta.readByIdEmpleado(idEmpleado);
    }

    @Override
    public int update(TVenta tVenta) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        return daoVenta.update(tVenta);
    }

    @Override
    public int devolver(TVenta tVenta) {
        int result = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        TVenta existing = daoVenta.read(tVenta.getId());
        if (existing != null && existing.getActivo()) {
            result = daoVenta.delete(tVenta.getId());
        }
        return result;
    }
}
