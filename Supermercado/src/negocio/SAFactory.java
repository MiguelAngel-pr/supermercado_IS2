package negocio;

public abstract class SAFactory {

    private static SAFactory instance;

    public static SAFactory getInstance() {
        if (instance == null) instance = new SAFactoryImp();
        return instance;
    }

    public abstract SACliente createCliente();
    public abstract SAMarca createMarca();
    public abstract SAProducto createProducto();
    public abstract SATrabajador createTrabajador();
    public abstract SAVenta createVenta();
}
