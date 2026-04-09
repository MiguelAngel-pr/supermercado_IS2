package integracion;

public class DAOFactoryImp extends DAOFactory {

    @Override
    public DAOCliente createCliente() { return new DAOClienteImp(); }

    @Override
    public DAOMarca createMarca() { return new DAOMarcaImp(); }

    @Override
    public DAOProducto createProducto() { return new DAOProductoImp(); }

    @Override
    public DAOTrabajador createTrabajador() { return new DAOTrabajadorImp(); }

    @Override
    public DAOVenta createVenta() { return new DAOVentaImp(); }

    @Override
    public DAOVentaProducto createVentaProducto() { return new DAOVentaProductoImp(); }
}
