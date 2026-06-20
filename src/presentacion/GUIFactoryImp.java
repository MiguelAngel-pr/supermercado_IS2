package presentacion;

import javax.swing.JOptionPane;
import negocio.TCarrito;
import negocio.TOAVenta;

public class GUIFactoryImp extends GUIFactory {

    @Override
    public IGUI createPanel() {
        Panel panel = new Panel();
        panel.setVisible(true);
        return panel;
    }

    // ==========================================================
    //  NUEVA IMPLEMENTACIÓN CENTRALIZADA PARA MENSAJES (MENSÁJERIA)
    // ==========================================================
    @Override
    public void handleResponse(GUIEvents event, Object data) {
        switch (event) {
            // ========== MARCA ==========
            case ALTA_MARCA_OK:
                JOptionPane.showMessageDialog(null, "Marca dada de alta con éxito. ID: " + data, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ALTA_MARCA_KO:
                JOptionPane.showMessageDialog(null, "Error al dar de alta la marca.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case BAJA_MARCA_OK:
                JOptionPane.showMessageDialog(null, "Marca dada de baja con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case BAJA_MARCA_KO:
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja la marca.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case ACTUALIZAR_MARCA_OK:
                JOptionPane.showMessageDialog(null, "Marca actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ACTUALIZAR_MARCA_KO:
                JOptionPane.showMessageDialog(null, "Error al actualizar la marca.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case MOSTRAR_MARCA_KO:
                JOptionPane.showMessageDialog(null, "No se encontró ninguna marca.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;

            // ========== CLIENTE ==========
            case ALTA_CLIENTE_OK:
                JOptionPane.showMessageDialog(null, "Cliente registrado con éxito. ID: " + data, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ALTA_CLIENTE_KO:
                JOptionPane.showMessageDialog(null, "Error al registrar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case BAJA_CLIENTE_OK:
                JOptionPane.showMessageDialog(null, "Cliente dado de baja con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case BAJA_CLIENTE_KO:
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja al cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case ACTUALIZAR_CLIENTE_OK:
                JOptionPane.showMessageDialog(null, "Datos del cliente actualizados.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ACTUALIZAR_CLIENTE_KO:
                JOptionPane.showMessageDialog(null, "Error al actualizar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case MOSTRAR_CLIENTE_KO:
                JOptionPane.showMessageDialog(null, "El cliente solicitado no existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;

            // ========== PRODUCTO ==========
            case ALTA_PRODUCTO_OK:
                JOptionPane.showMessageDialog(null, "Producto añadido con éxito. ID: " + data, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ALTA_PRODUCTO_KO:
                JOptionPane.showMessageDialog(null, "Error al crear el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case BAJA_PRODUCTO_OK:
                JOptionPane.showMessageDialog(null, "Producto dado de baja con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case BAJA_PRODUCTO_KO:
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case ACTUALIZAR_PRODUCTO_OK:
                JOptionPane.showMessageDialog(null, "Producto actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ACTUALIZAR_PRODUCTO_KO:
                JOptionPane.showMessageDialog(null, "Error al modificar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case MOSTRAR_PRODUCTO_KO:
                JOptionPane.showMessageDialog(null, "No se encontraron productos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;

            // ========== EMPLEADO ==========
            case ALTA_EMPLEADO_OK:
                JOptionPane.showMessageDialog(null, "Trabajador dado de alta. ID: " + data, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ALTA_EMPLEADO_KO:
                JOptionPane.showMessageDialog(null, "No se pudo dar de alta al empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case BAJA_EMPLEADO_OK:
                JOptionPane.showMessageDialog(null, "Empleado dado de baja con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case BAJA_EMPLEADO_KO:
                JOptionPane.showMessageDialog(null, "Error al procesar la baja del empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case ACTUALIZAR_EMPLEADO_OK:
                JOptionPane.showMessageDialog(null, "Información del empleado guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ACTUALIZAR_EMPLEADO_KO:
                JOptionPane.showMessageDialog(null, "Error al actualizar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case MOSTRAR_EMPLEADO_KO:
                JOptionPane.showMessageDialog(null, "Empleado no encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;

            // ========== VENTA ==========
            case CERRAR_VENTA_OK:
                TOAVenta toa = (TOAVenta) data;
                JOptionPane.showMessageDialog(null, "Venta cerrada con éxito.\nID: " + toa.getTVenta().getId() + "\nImporte total: " + toa.getTVenta().getImporte(), "Venta Completada", JOptionPane.INFORMATION_MESSAGE);
                break;
            case CERRAR_VENTA_KO:
                JOptionPane.showMessageDialog(null, "No se pudo cerrar la venta. Verifique stock o IDs.", "Error en Venta", JOptionPane.ERROR_MESSAGE);
                break;
            case DEVOLUCION_VENTA_OK:
                JOptionPane.showMessageDialog(null, "Devolución tramitada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case DEVOLUCION_VENTA_KO:
                JOptionPane.showMessageDialog(null, "No se pudo realizar la devolución.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case ACTUALIZAR_VENTA_OK:
                JOptionPane.showMessageDialog(null, "Venta modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ACTUALIZAR_VENTA_KO:
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case MOSTRAR_VENTA_KO:
                JOptionPane.showMessageDialog(null, "Venta no localizada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;

            default:
                break;
        }
    }

    // ==================== MARCA ====================
    @Override
    public IGUI createAltaMarca() {
        AltaMarca f = new AltaMarca(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createBajaMarca() {
        BajaMarca f = new BajaMarca(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createActualizarMarca() {
        ActualizarMarca f = new ActualizarMarca(); f.setVisible(true); return f;
    }
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
    public IGUI createBajaCliente() {
        BajaCliente f = new BajaCliente(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createActualizarCliente() {
        ActualizarCliente f = new ActualizarCliente(); f.setVisible(true); return f;
    }
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
    public IGUI createBajaProducto() {
        BajaProducto f = new BajaProducto(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createActualizarProducto() {
        ActualizarProducto f = new ActualizarProducto(); f.setVisible(true); return f;
    }
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
    public IGUI createBajaEmpleado() {
        BajaEmpleado f = new BajaEmpleado(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createActualizarEmpleado() {
        ActualizarEmpleado f = new ActualizarEmpleado(); f.setVisible(true); return f;
    }
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
    public IGUI createDevolucionVenta() {
        DevolucionVenta f = new DevolucionVenta(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createActualizarVenta() {
        ActualizarVenta f = new ActualizarVenta(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createMostrarVenta() {
        MostrarVenta f = new MostrarVenta(); f.setVisible(true); return f;
    }
    @Override
    public IGUI createResponseMostrarVenta() { return new ResponseMostrarVenta(); }
}