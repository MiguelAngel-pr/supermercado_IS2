package negocio;

import java.time.LocalDate;
import java.util.Set;

import integracion.DAOCliente;
import integracion.DAOFactory;
import integracion.DAOProducto;
import integracion.DAOTrabajador;
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
        //validación estructural básica
        if (tCarrito == null || tCarrito.getItems().isEmpty()) {
            return -1; // Error: Carrito vacío
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
       
        //validar existencia del EMPLEADO
        DAOTrabajador daoEmpleado = daoFactory.createTrabajador();
        if (daoEmpleado.read(tCarrito.getIdEmpleado()) == null) {
            return -3; // Código de error: "Empleado no existe"
        }

        //validar existencia del CLIENTE
        DAOCliente daoCliente = daoFactory.createCliente();
        if (daoCliente.read(tCarrito.getIdCliente()) == null) {
            return -4; // Código de error: "Cliente no existe"
        }

        //validar STOCK de todos los productos
        DAOProducto daoProducto = daoFactory.createProducto();
        for (TVentaProducto item : tCarrito.getItems()) {
            TProducto producto = daoProducto.read(item.getIdProducto());
            if (producto == null || producto.getCantidad() < item.getCantidad()) {
                return -2; // Código de error: "Stock insuficiente o producto inexistente"
            }
        }


        DAOVenta daoVenta = daoFactory.createVenta();
        DAOVentaProducto daoVentaProducto = daoFactory.createVentaProducto();

        String fecha = LocalDate.now().toString();
        double importe = tCarrito.calcularImporte(null);
        TVenta tVenta = new TVenta(-1, fecha, importe, tCarrito.getIdCliente(), tCarrito.getIdEmpleado());

        // 5. Crear la venta (Cabecera)
        int idVenta = daoVenta.create(tVenta, tCarrito);
       
        if (idVenta > 0) {
            for (TVentaProducto item : tCarrito.getItems()) {
                // Registrar línea de venta
                TVentaProducto vp = new TVentaProducto(idVenta, item.getIdProducto(), item.getCantidad());
                daoVentaProducto.create(vp);

                // Actualizar stock
                TProducto p = daoProducto.read(item.getIdProducto());
                TProducto actualizado = new TProducto(
                    p.getId(), p.getNombre(), p.getPrecio(),
                    p.getFechaCaducidad(), p.getCantidad() - item.getCantidad(),
                    p.getIdMarca()
                );
                daoProducto.update(actualizado);
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
        DAOVentaProducto daoVentaProducto = daoFactory.createVentaProducto();
        DAOProducto daoProducto = daoFactory.createProducto();

        TVenta existing = daoVenta.read(tVenta.getId());
        if (existing != null) {
            // Recuperar los productos de la venta antes de borrarla
            Set<TVentaProducto> items = daoVentaProducto.readByVenta(tVenta.getId());

            result = daoVenta.delete(tVenta.getId());

            // Si la venta se borró con éxito, devolver el stock
            if (result > 0) {
                for (TVentaProducto item : items) {
                    TProducto producto = daoProducto.read(item.getIdProducto());
                    if (producto != null) {
                        TProducto actualizado = new TProducto(
                            producto.getId(),
                            producto.getNombre(),
                            producto.getPrecio(),
                            producto.getFechaCaducidad(),
                            producto.getCantidad() + item.getCantidad(),
                            producto.getIdMarca()
                        );
                        daoProducto.update(actualizado);
                    }
                }
            }
        }
        return result;
    }
}