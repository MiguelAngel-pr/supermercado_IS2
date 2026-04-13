package integracion;

public abstract class DAOFactory {

    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null) instance = new DAOFactoryImp();
        return instance;
    }

    public abstract DAOCliente createCliente();
    public abstract DAOMarca createMarca();
    public abstract DAOProducto createProducto();
    public abstract DAOTrabajador createTrabajador();
    public abstract DAOVenta createVenta();
    public abstract DAOVentaProducto createVentaProducto();
}
