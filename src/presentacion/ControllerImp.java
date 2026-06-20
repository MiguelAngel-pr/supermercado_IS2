package presentacion;

import java.util.Set;

import negocio.SACliente;
import negocio.SAFactory;
import negocio.SAMarca;
import negocio.SAProducto;
import negocio.SATrabajador;
import negocio.SAVenta;
import negocio.TCarrito;
import negocio.TCliente;
import negocio.TMarca;
import negocio.TOAVenta;
import negocio.TProducto;
import negocio.TTrabajador;
import negocio.TVenta;

public class ControllerImp extends Controller {

    @Override
    public void handleAction(Events event, Object data) {
        GUIFactory guiFactory = GUIFactory.getInstance();
        SAFactory saFactory = SAFactory.getInstance();

        int result;
        SAMarca saMarca;
        TMarca tMarca;
        SACliente saCliente;
        TCliente tCliente;
        SAProducto saProducto;
        TProducto tProducto;
        SATrabajador saTrabajador;
        TTrabajador tTrabajador;
        SAVenta saVenta;
        Set<TVenta> tVentas;

        switch (event) {
        // ==================== PANEL ====================
        case MOSTRAR_PANEL:
            guiFactory.createPanel();
            break;

        // ==================== MARCA ====================
        case MOSTRAR_ALTA_MARCA:
            guiFactory.createAltaMarca();
            break;
        case ALTA_MARCA:
            saMarca = saFactory.createMarca();
            result = saMarca.create((TMarca) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ALTA_MARCA_OK, result);
            else guiFactory.handleResponse(GUIEvents.ALTA_MARCA_KO, result);
            break;
        case MOSTRAR_BAJA_MARCA:
            guiFactory.createBajaMarca();
            break;
        case BAJA_MARCA:
            saMarca = saFactory.createMarca();
            result = saMarca.delete(((TMarca) data).getId());
            if (result > 0) guiFactory.handleResponse(GUIEvents.BAJA_MARCA_OK, result);
            else guiFactory.handleResponse(GUIEvents.BAJA_MARCA_KO, result);
            break;
        case MOSTRAR_ACTUALIZAR_MARCA:
            guiFactory.createActualizarMarca();
            break;
        case ACTUALIZAR_MARCA:
            saMarca = saFactory.createMarca();
            result = saMarca.update((TMarca) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ACTUALIZAR_MARCA_OK, result);
            else guiFactory.handleResponse(GUIEvents.ACTUALIZAR_MARCA_KO, result);
            break;
        case MOSTRAR_MOSTRAR_MARCA:
            guiFactory.createMostrarMarca();
            break;
        case MOSTRAR_UNO_ID_MARCA:
            saMarca = saFactory.createMarca();
            tMarca = saMarca.read((int) data);
            if (tMarca != null) guiFactory.createResponseMostrarMarca().update(GUIEvents.MOSTRAR_MARCA_OK, tMarca);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_MARCA_KO, null);
            break;
        case MOSTRAR_UNO_NOMBRE_MARCA:
            saMarca = saFactory.createMarca();
            tMarca = saMarca.readByName((String) data);
            if (tMarca != null) guiFactory.createResponseMostrarMarca().update(GUIEvents.MOSTRAR_MARCA_OK, tMarca);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_MARCA_KO, null);
            break;
        case MOSTRAR_TODO_MARCA:
            saMarca = saFactory.createMarca();
            Set<TMarca> tMarcas = saMarca.readAll();
            if (!tMarcas.isEmpty()) guiFactory.createResponseMostrarMarca().update(GUIEvents.MOSTRAR_MARCA_OK, tMarcas);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_MARCA_KO, null);
            break;

        // ==================== CLIENTE ====================
        case MOSTRAR_ALTA_CLIENTE:
            guiFactory.createAltaCliente();
            break;
        case ALTA_CLIENTE:
            saCliente = saFactory.createCliente();
            result = saCliente.create((TCliente) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ALTA_CLIENTE_OK, result);
            else guiFactory.handleResponse(GUIEvents.ALTA_CLIENTE_KO, result);
            break;
        case MOSTRAR_BAJA_CLIENTE:
            guiFactory.createBajaCliente();
            break;
        case BAJA_CLIENTE:
            saCliente = saFactory.createCliente();
            result = saCliente.delete(((TCliente) data).getId());
            if (result > 0) guiFactory.handleResponse(GUIEvents.BAJA_CLIENTE_OK, result);
            else guiFactory.handleResponse(GUIEvents.BAJA_CLIENTE_KO, result);
            break;
        case MOSTRAR_ACTUALIZAR_CLIENTE:
            guiFactory.createActualizarCliente();
            break;
        case ACTUALIZAR_CLIENTE:
            saCliente = saFactory.createCliente();
            result = saCliente.update((TCliente) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ACTUALIZAR_CLIENTE_OK, result);
            else guiFactory.handleResponse(GUIEvents.ACTUALIZAR_CLIENTE_KO, result);
            break;
        case MOSTRAR_MOSTRAR_CLIENTE:
            guiFactory.createMostrarCliente();
            break;
        case MOSTRAR_UNO_ID_CLIENTE:
            saCliente = saFactory.createCliente();
            tCliente = saCliente.read((int) data);
            if (tCliente != null) guiFactory.createResponseMostrarCliente().update(GUIEvents.MOSTRAR_CLIENTE_OK, tCliente);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_CLIENTE_KO, null);
            break;
        case MOSTRAR_UNO_NIF_CLIENTE:
            saCliente = saFactory.createCliente();
            tCliente = saCliente.readByNIF((String) data);
            if (tCliente != null) guiFactory.createResponseMostrarCliente().update(GUIEvents.MOSTRAR_CLIENTE_OK, tCliente);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_CLIENTE_KO, null);
            break;
        case MOSTRAR_TODO_CLIENTE:
            saCliente = saFactory.createCliente();
            Set<TCliente> tClientes = saCliente.readAll();
            if (!tClientes.isEmpty()) guiFactory.createResponseMostrarCliente().update(GUIEvents.MOSTRAR_CLIENTE_OK, tClientes);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_CLIENTE_KO, null);
            break;

        // ==================== PRODUCTO ====================
        case MOSTRAR_ALTA_PRODUCTO:
            guiFactory.createAltaProducto();
            break;
        case ALTA_PRODUCTO:
            saProducto = saFactory.createProducto();
            result = saProducto.create((TProducto) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ALTA_PRODUCTO_OK, result);
            else guiFactory.handleResponse(GUIEvents.ALTA_PRODUCTO_KO, result);
            break;
        case MOSTRAR_BAJA_PRODUCTO:
            guiFactory.createBajaProducto();
            break;
        case BAJA_PRODUCTO:
            saProducto = saFactory.createProducto();
            result = saProducto.delete(((TProducto) data).getId());
            if (result > 0) guiFactory.handleResponse(GUIEvents.BAJA_PRODUCTO_OK, result);
            else guiFactory.handleResponse(GUIEvents.BAJA_PRODUCTO_KO, result);
            break;
        case MOSTRAR_ACTUALIZAR_PRODUCTO:
            guiFactory.createActualizarProducto();
            break;
        case ACTUALIZAR_PRODUCTO:
            saProducto = saFactory.createProducto();
            result = saProducto.update((TProducto) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ACTUALIZAR_PRODUCTO_OK, result);
            else guiFactory.handleResponse(GUIEvents.ACTUALIZAR_PRODUCTO_KO, result);
            break;
        case MOSTRAR_MOSTRAR_PRODUCTO:
            guiFactory.createMostrarProducto();
            break;
        case MOSTRAR_UNO_ID_PRODUCTO:
            saProducto = saFactory.createProducto();
            tProducto = saProducto.read((int) data);
            if (tProducto != null) guiFactory.createResponseMostrarProducto().update(GUIEvents.MOSTRAR_PRODUCTO_OK, tProducto);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_PRODUCTO_KO, null);
            break;
        case MOSTRAR_TODO_MARCA_PRODUCTO:
            saProducto = saFactory.createProducto();
            Set<TProducto> tProductosMarca = saProducto.readByMarca((int) data);
            if (!tProductosMarca.isEmpty()) guiFactory.createResponseMostrarProducto().update(GUIEvents.MOSTRAR_PRODUCTO_OK, tProductosMarca);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_PRODUCTO_KO, null);
            break;
        case MOSTRAR_TODO_PRODUCTO:
            saProducto = saFactory.createProducto();
            Set<TProducto> tProductos = saProducto.readAll();
            if (!tProductos.isEmpty()) guiFactory.createResponseMostrarProducto().update(GUIEvents.MOSTRAR_PRODUCTO_OK, tProductos);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_PRODUCTO_KO, null);
            break;

        // ==================== EMPLEADO ====================
        case MOSTRAR_ALTA_EMPLEADO:
            guiFactory.createAltaEmpleado();
            break;
        case ALTA_EMPLEADO:
            saTrabajador = saFactory.createTrabajador();
            result = saTrabajador.create((TTrabajador) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ALTA_EMPLEADO_OK, result);
            else guiFactory.handleResponse(GUIEvents.ALTA_EMPLEADO_KO, result);
            break;
        case MOSTRAR_BAJA_EMPLEADO:
            guiFactory.createBajaEmpleado();
            break;
        case BAJA_EMPLEADO:
            saTrabajador = saFactory.createTrabajador();
            result = saTrabajador.delete((int) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.BAJA_EMPLEADO_OK, result);
            else guiFactory.handleResponse(GUIEvents.BAJA_EMPLEADO_KO, result);
            break;
        case MOSTRAR_ACTUALIZAR_EMPLEADO:
            guiFactory.createActualizarEmpleado();
            break;
        case ACTUALIZAR_EMPLEADO:
            saTrabajador = saFactory.createTrabajador();
            result = saTrabajador.update((TTrabajador) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ACTUALIZAR_EMPLEADO_OK, result);
            else guiFactory.handleResponse(GUIEvents.ACTUALIZAR_EMPLEADO_KO, result);
            break;
        case MOSTRAR_MOSTRAR_EMPLEADO:
            guiFactory.createMostrarEmpleado();
            break;
        case MOSTRAR_UNO_ID_EMPLEADO:
            saTrabajador = saFactory.createTrabajador();
            tTrabajador = saTrabajador.read((int) data);
            if (tTrabajador != null) guiFactory.createResponseMostrarEmpleado().update(GUIEvents.MOSTRAR_EMPLEADO_OK, tTrabajador);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_EMPLEADO_KO, null);
            break;
        case MOSTRAR_UNO_NIF_EMPLEADO:
            saTrabajador = saFactory.createTrabajador();
            tTrabajador = saTrabajador.readByNIF((String) data);
            if (tTrabajador != null) guiFactory.createResponseMostrarEmpleado().update(GUIEvents.MOSTRAR_EMPLEADO_OK, tTrabajador);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_EMPLEADO_KO, null);
            break;
        case MOSTRAR_TODO_EMPLEADO:
            saTrabajador = saFactory.createTrabajador();
            Set<TTrabajador> tTrabajadores = saTrabajador.readAll();
            if (!tTrabajadores.isEmpty()) guiFactory.createResponseMostrarEmpleado().update(GUIEvents.MOSTRAR_EMPLEADO_OK, tTrabajadores);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_EMPLEADO_KO, null);
            break;

        // ==================== VENTA ====================
        case MOSTRAR_ABRIR_VENTA:
            saVenta = saFactory.createVenta();
            guiFactory.createAbrirVenta(saVenta.abrir());
            break;
        case CERRAR_VENTA:
            saVenta = saFactory.createVenta();
            result = saVenta.cerrar((TCarrito) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.CERRAR_VENTA_OK, saVenta.read(result));
            else guiFactory.handleResponse(GUIEvents.CERRAR_VENTA_KO, null);
            break;
        case MOSTRAR_DEVOLUCION_VENTA:
            guiFactory.createDevolucionVenta();
            break;
        case DEVOLUCION_VENTA:
            saVenta = saFactory.createVenta();
            result = saVenta.devolver((int) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.DEVOLUCION_VENTA_OK, result);
            else guiFactory.handleResponse(GUIEvents.DEVOLUCION_VENTA_KO, result);
            break;
        case MOSTRAR_ACTUALIZAR_VENTA:
            guiFactory.createActualizarVenta();
            break;
        case ACTUALIZAR_VENTA:
            saVenta = saFactory.createVenta();
            result = saVenta.update((TVenta) data);
            if (result > 0) guiFactory.handleResponse(GUIEvents.ACTUALIZAR_VENTA_OK, result);
            else guiFactory.handleResponse(GUIEvents.ACTUALIZAR_VENTA_KO, result);
            break;
        case MOSTRAR_MOSTRAR_VENTA:
            guiFactory.createMostrarVenta();
            break;
        case MOSTRAR_UNO_ID_VENTA:
            saVenta = saFactory.createVenta();
            TOAVenta toaVenta = saVenta.read((int) data);
            if (toaVenta != null) guiFactory.createResponseMostrarVenta().update(GUIEvents.MOSTRAR_VENTA_OK, toaVenta);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_VENTA_KO, null);
            break;
        case MOSTRAR_VENTA_ID_CLIENTE:
            saVenta = saFactory.createVenta();
            tVentas = saVenta.readByIdCliente((int) data);
            if (!tVentas.isEmpty()) guiFactory.createResponseMostrarVenta().update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_VENTA_KO, null);
            break;
        case MOSTRAR_VENTA_ID_EMPLEADO:
            saVenta = saFactory.createVenta();
            tVentas = saVenta.readByIdEmpleado((int) data);
            if (!tVentas.isEmpty()) guiFactory.createResponseMostrarVenta().update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_VENTA_KO, null);
            break;
        case MOSTRAR_VENTA_FECHA:
            saVenta = saFactory.createVenta();
            tVentas = saVenta.readByFecha((String) data);
            if (!tVentas.isEmpty()) guiFactory.createResponseMostrarVenta().update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_VENTA_KO, null);
            break;
        case MOSTRAR_TODO_VENTA:
            saVenta = saFactory.createVenta();
            tVentas = saVenta.readAll();
            if (!tVentas.isEmpty()) guiFactory.createResponseMostrarVenta().update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
            else guiFactory.handleResponse(GUIEvents.MOSTRAR_VENTA_KO, null);
            break;

        default:
            break;
        }
    }
}