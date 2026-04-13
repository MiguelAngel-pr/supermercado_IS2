package presentacion;

import negocio.TCarrito;

public abstract class GUIFactory {

    private static GUIFactory instance;

    public static GUIFactory getInstance() {
        if (instance == null) instance = new GUIFactoryImp();
        return instance;
    }

    public abstract IGUI createPanel();

    // Marca
    public abstract IGUI createAltaMarca();
    public abstract IGUI createResponseAltaMarca();
    public abstract IGUI createBajaMarca();
    public abstract IGUI createResponseBajaMarca();
    public abstract IGUI createActualizarMarca();
    public abstract IGUI createResponseActualizarMarca();
    public abstract IGUI createMostrarMarca();
    public abstract IGUI createResponseMostrarMarca();

    // Cliente
    public abstract IGUI createAltaCliente();
    public abstract IGUI createResponseAltaCliente();
    public abstract IGUI createBajaCliente();
    public abstract IGUI createResponseBajaCliente();
    public abstract IGUI createActualizarCliente();
    public abstract IGUI createResponseActualizarCliente();
    public abstract IGUI createMostrarCliente();
    public abstract IGUI createResponseMostrarCliente();

    // Producto
    public abstract IGUI createAltaProducto();
    public abstract IGUI createResponseAltaProducto();
    public abstract IGUI createBajaProducto();
    public abstract IGUI createResponseBajaProducto();
    public abstract IGUI createActualizarProducto();
    public abstract IGUI createResponseActualizarProducto();
    public abstract IGUI createMostrarProducto();
    public abstract IGUI createResponseMostrarProducto();

    // Empleado
    public abstract IGUI createAltaEmpleado();
    public abstract IGUI createResponseAltaEmpleado();
    public abstract IGUI createBajaEmpleado();
    public abstract IGUI createResponseBajaEmpleado();
    public abstract IGUI createActualizarEmpleado();
    public abstract IGUI createResponseActualizarEmpleado();
    public abstract IGUI createMostrarEmpleado();
    public abstract IGUI createResponseMostrarEmpleado();

    // Venta
    public abstract IGUI createAbrirVenta(TCarrito tCarrito);
    public abstract IGUI createResponseAbrirVenta();
    public abstract IGUI createDevolucionVenta();
    public abstract IGUI createResponseDevolucionVenta();
    public abstract IGUI createActualizarVenta();
    public abstract IGUI createResponseActualizarVenta();
    public abstract IGUI createMostrarVenta();
    public abstract IGUI createResponseMostrarVenta();
}
