package presentacion;

import negocio.TCarrito;

public abstract class GUIFactory {

    private static GUIFactory instance;

    public static GUIFactory getInstance() {
        if (instance == null) instance = new GUIFactoryImp();
        return instance;
    }

    public abstract IGUI createPanel();
    
    //NUEVO MÉTODO CENTRALIZADO PARA DIÁLOGOS
    public abstract void handleResponse(GUIEvents event, Object data);

    // Marca
    public abstract IGUI createAltaMarca();
    public abstract IGUI createBajaMarca();
    public abstract IGUI createActualizarMarca();
    public abstract IGUI createMostrarMarca();
    public abstract IGUI createResponseMostrarMarca(); //este se queda pq muestra tablas

    // Cliente
    public abstract IGUI createAltaCliente();
    public abstract IGUI createBajaCliente();
    public abstract IGUI createActualizarCliente();
    public abstract IGUI createMostrarCliente();
    public abstract IGUI createResponseMostrarCliente();

    // Producto
    public abstract IGUI createAltaProducto();
    public abstract IGUI createBajaProducto();
    public abstract IGUI createActualizarProducto();
    public abstract IGUI createMostrarProducto();
    public abstract IGUI createResponseMostrarProducto();

    // Empleado
    public abstract IGUI createAltaEmpleado();
    public abstract IGUI createBajaEmpleado();
    public abstract IGUI createActualizarEmpleado();
    public abstract IGUI createMostrarEmpleado();
    public abstract IGUI createResponseMostrarEmpleado();

    // Venta
    public abstract IGUI createAbrirVenta(TCarrito tCarrito);
    public abstract IGUI createDevolucionVenta();
    public abstract IGUI createActualizarVenta();
    public abstract IGUI createMostrarVenta();
    public abstract IGUI createResponseMostrarVenta();
}
