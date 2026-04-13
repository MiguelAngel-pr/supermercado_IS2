package presentacion;

import negocio.TCarrito;

public class GUIFactoryImp extends GUIFactory {

    @Override
    public IGUI createPanel() {
        Panel panel = new Panel();
        panel.setVisible(true);
        return panel;
    }

    // ==================== MARCA ====================
    @Override
    public IGUI createAltaMarca() {
        AltaMarca f = new AltaMarca(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseAltaMarca() { return new ResponseAltaMarca(); }
    @Override
    public IGUI createBajaMarca() {
        BajaMarca f = new BajaMarca(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseBajaMarca() { return new ResponseBajaMarca(); }
    @Override
    public IGUI createActualizarMarca() {
        ActualizarMarca f = new ActualizarMarca(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseActualizarMarca() { return new ResponseActualizarMarca(); }
    @Override
    public IGUI createMostrarMarca() {
        MostrarMarca f = new MostrarMarca(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseMostrarMarca() { return new ResponseMostrarMarca(); }

    // ==================== CLIENTE ====================
    @Override
    public IGUI createAltaCliente() {
        AltaCliente f = new AltaCliente(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseAltaCliente() { return new ResponseAltaCliente(); }
    @Override
    public IGUI createBajaCliente() {
        BajaCliente f = new BajaCliente(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseBajaCliente() { return new ResponseBajaCliente(); }
    @Override
    public IGUI createActualizarCliente() {
        ActualizarCliente f = new ActualizarCliente(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseActualizarCliente() { return new ResponseActualizarCliente(); }
    @Override
    public IGUI createMostrarCliente() {
        MostrarCliente f = new MostrarCliente(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseMostrarCliente() { return new ResponseMostrarCliente(); }

    // ==================== PRODUCTO ====================
    @Override
    public IGUI createAltaProducto() {
        AltaProducto f = new AltaProducto(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseAltaProducto() { return new ResponseAltaProducto(); }
    @Override
    public IGUI createBajaProducto() {
        BajaProducto f = new BajaProducto(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseBajaProducto() { return new ResponseBajaProducto(); }
    @Override
    public IGUI createActualizarProducto() {
        ActualizarProducto f = new ActualizarProducto(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseActualizarProducto() { return new ResponseActualizarProducto(); }
    @Override
    public IGUI createMostrarProducto() {
        MostrarProducto f = new MostrarProducto(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseMostrarProducto() { return new ResponseMostrarProducto(); }

    // ==================== EMPLEADO ====================
    @Override
    public IGUI createAltaEmpleado() {
        AltaEmpleado f = new AltaEmpleado(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseAltaEmpleado() { return new ResponseAltaEmpleado(); }
    @Override
    public IGUI createBajaEmpleado() {
        BajaEmpleado f = new BajaEmpleado(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseBajaEmpleado() { return new ResponseBajaEmpleado(); }
    @Override
    public IGUI createActualizarEmpleado() {
        ActualizarEmpleado f = new ActualizarEmpleado(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseActualizarEmpleado() { return new ResponseActualizarEmpleado(); }
    @Override
    public IGUI createMostrarEmpleado() {
        MostrarEmpleado f = new MostrarEmpleado(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseMostrarEmpleado() { return new ResponseMostrarEmpleado(); }

    // ==================== VENTA ====================
    @Override
    public IGUI createAbrirVenta(TCarrito tCarrito) {
        AbrirVenta f = new AbrirVenta(tCarrito); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseAbrirVenta() { return new ResponseAbrirVenta(); }
    @Override
    public IGUI createDevolucionVenta() {
        DevolucionVenta f = new DevolucionVenta(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseDevolucionVenta() { return new ResponseDevolucionVenta(); }
    @Override
    public IGUI createActualizarVenta() {
        ActualizarVenta f = new ActualizarVenta(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseActualizarVenta() { return new ResponseActualizarVenta(); }
    @Override
    public IGUI createMostrarVenta() {
        MostrarVenta f = new MostrarVenta(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseMostrarVenta() { return new ResponseMostrarVenta(); }
}
