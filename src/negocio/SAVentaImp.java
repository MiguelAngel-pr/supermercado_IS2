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
        return new TCarrito(-1, -1);
    }

    @Override
    public int cerrar(TCarrito tCarrito) {
        //validaciï¿½n estructural bï¿½sica
        if (tCarrito == null || tCarrito.getItems().isEmpty()) {
            return -1; // Error: Carrito vacï¿½o
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
       
        //validar existencia del EMPLEADO
        DAOTrabajador daoEmpleado = daoFactory.createTrabajador();
        if (daoEmpleado.read(tCarrito.getIdEmpleado()) == null) {
            return -3; // Cï¿½digo de error: "Empleado no existe"
        }

        //validar existencia del CLIENTE
        DAOCliente daoCliente = daoFactory.createCliente();
        if (daoCliente.read(tCarrito.getIdCliente()) == null) {
            return -4; // Cï¿½digo de error: "Cliente no existe"
        }

        //validar STOCK de todos los productos
        DAOProducto daoProducto = daoFactory.createProducto();
        double importe = 0;
        for (TVentaProducto item : tCarrito.getItems()) {
            TProducto producto = daoProducto.read(item.getIdProducto());
            if (producto == null || producto.getCantidad() < item.getCantidad()) {
                return -2; // Cï¿½digo de error: "Stock insuficiente o producto inexistente"
            }
            else{
            	importe += producto.getPrecio() * item.getCantidad();
            }
        }

        DAOVenta daoVenta = daoFactory.createVenta();
        DAOVentaProducto daoVentaProducto = daoFactory.createVentaProducto();

        String fecha = LocalDate.now().toString();
        TVenta tVenta = new TVenta(-1, fecha, importe, tCarrito.getIdCliente(), tCarrito.getIdEmpleado());

        // 5. Crear la venta (Cabecera)
        int idVenta = daoVenta.create(tVenta, tCarrito);
       
        if (idVenta > 0) {
            for (TVentaProducto item : tCarrito.getItems()) {
                // Registrar lï¿½nea de venta
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
    public int devolver(int idVenta) {
        int result = 0;
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOVenta daoVenta = daoFactory.createVenta();
        DAOVentaProducto daoVentaProducto = daoFactory.createVentaProducto();
        DAOProducto daoProducto = daoFactory.createProducto();

        // Usamos directamente idVenta en lugar de tVenta.getId()
        TVenta existing = daoVenta.read(idVenta);
        if (existing != null) {
            // Recuperar los productos de la venta antes de borrarla
            Set<TVentaProducto> items = daoVentaProducto.readByVenta(idVenta);

            if (items == null || items.isEmpty()) {
                return -2; // Error: la venta no tiene líneas de producto
            }
            
            result = daoVenta.delete(idVenta);

            // Si la venta se borra con exito, devolver el stock al inventario
            if (result > 0) {
                for (TVentaProducto item : items) {
                    TProducto producto = daoProducto.read(item.getIdProducto());
                    if (producto != null) {
                        TProducto actualizado = new TProducto(
                            producto.getId(),
                            producto.getNombre(),
                            producto.getPrecio(),
                            producto.getFechaCaducidad(),
                            producto.getCantidad() + item.getCantidad(), // Suma stock devuelto
                            producto.getIdMarca()
                        );
                        daoProducto.update(actualizado);
                    }
                }
            }
        }
        else{
        	return -1; //Error: La venta no existe
        }
        return result; // resultado de la operación de anulación
    }
}