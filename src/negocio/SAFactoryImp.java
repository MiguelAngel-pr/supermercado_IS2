package negocio;

public class SAFactoryImp extends SAFactory {

    @Override
    public SACliente createCliente() { return new SAClienteImp(); }

    @Override
    public SAMarca createMarca() { return new SAMarcaImp(); }

    @Override
    public SAProducto createProducto() { return new SAProductoImp(); }

    @Override
    public SATrabajador createTrabajador() { return new SATrabajadorImp(); }

    @Override
    public SAVenta createVenta() { return new SAVentaImp(); }
}
