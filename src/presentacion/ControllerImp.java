package presentacion;

import java.util.Set;

import javax.swing.SwingUtilities;

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
        SwingUtilities.invokeLater(() -> {
            GUIFactory guiFactory = GUIFactory.getInstance();
            SAFactory saFactory = SAFactory.getInstance();

            int result;
            SAMarca saMarca;
            TMarca tMarca;
            IGUI responseMostrarMarca;
            SACliente saCliente;
            TCliente tCliente;
            IGUI responseMostrarCliente;
            SAProducto saProducto;
            TProducto tProducto;
            IGUI responseMostrarProducto;
            SATrabajador saTrabajador;
            TTrabajador tTrabajador;
            IGUI responseMostrarEmpleado;
            SAVenta saVenta;
            Set<TVenta> tVentas;
            IGUI responseMostrarVenta;

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
                IGUI responseAltaMarca = guiFactory.createResponseAltaMarca();
                if (result > 0) responseAltaMarca.update(GUIEvents.ALTA_MARCA_OK, result);
                else responseAltaMarca.update(GUIEvents.ALTA_MARCA_KO, result);
                break;
            case MOSTRAR_BAJA_MARCA:
                guiFactory.createBajaMarca();
                break;
            case BAJA_MARCA:
                saMarca = saFactory.createMarca();
                result = saMarca.delete(((TMarca) data).getId());
                IGUI responseBajaMarca = guiFactory.createResponseBajaMarca();
                if (result > 0) responseBajaMarca.update(GUIEvents.BAJA_MARCA_OK, result);
                else responseBajaMarca.update(GUIEvents.BAJA_MARCA_KO, result);
                break;
            case MOSTRAR_ACTUALIZAR_MARCA:
                guiFactory.createActualizarMarca();
                break;
            case ACTUALIZAR_MARCA:
                saMarca = saFactory.createMarca();
                result = saMarca.update((TMarca) data);
                IGUI responseActualizarMarca = guiFactory.createResponseActualizarMarca();
                if (result > 0) responseActualizarMarca.update(GUIEvents.ACTUALIZAR_MARCA_OK, result);
                else responseActualizarMarca.update(GUIEvents.ACTUALIZAR_MARCA_KO, result);
                break;
            case MOSTRAR_MOSTRAR_MARCA:
                guiFactory.createMostrarMarca();
                break;
            case MOSTRAR_UNO_ID_MARCA:
                saMarca = saFactory.createMarca();
                tMarca = saMarca.read((int) data);
                responseMostrarMarca = guiFactory.createResponseMostrarMarca();
                if (tMarca != null) responseMostrarMarca.update(GUIEvents.MOSTRAR_MARCA_OK, tMarca);
                else responseMostrarMarca.update(GUIEvents.MOSTRAR_MARCA_KO, null);
                break;
            case MOSTRAR_UNO_NOMBRE_MARCA:
                saMarca = saFactory.createMarca();
                tMarca = saMarca.readByName((String) data);
                responseMostrarMarca = guiFactory.createResponseMostrarMarca();
                if (tMarca != null) responseMostrarMarca.update(GUIEvents.MOSTRAR_MARCA_OK, tMarca);
                else responseMostrarMarca.update(GUIEvents.MOSTRAR_MARCA_KO, null);
                break;
            case MOSTRAR_TODO_MARCA:
                saMarca = saFactory.createMarca();
                Set<TMarca> tMarcas = saMarca.readAll();
                responseMostrarMarca = guiFactory.createResponseMostrarMarca();
                if (!tMarcas.isEmpty()) responseMostrarMarca.update(GUIEvents.MOSTRAR_MARCA_OK, tMarcas);
                else responseMostrarMarca.update(GUIEvents.MOSTRAR_MARCA_KO, null);
                break;

            // ==================== CLIENTE ====================
            case MOSTRAR_ALTA_CLIENTE:
                guiFactory.createAltaCliente();
                break;
            case ALTA_CLIENTE:
                saCliente = saFactory.createCliente();
                result = saCliente.create((TCliente) data);
                IGUI responseAltaCliente = guiFactory.createResponseAltaCliente();
                if (result > 0) responseAltaCliente.update(GUIEvents.ALTA_CLIENTE_OK, result);
                else responseAltaCliente.update(GUIEvents.ALTA_CLIENTE_KO, result);
                break;
            case MOSTRAR_BAJA_CLIENTE:
                guiFactory.createBajaCliente();
                break;
            case BAJA_CLIENTE:
                saCliente = saFactory.createCliente();
                result = saCliente.delete(((TCliente) data).getId());
                IGUI responseBajaCliente = guiFactory.createResponseBajaCliente();
                if (result > 0) responseBajaCliente.update(GUIEvents.BAJA_CLIENTE_OK, result);
                else responseBajaCliente.update(GUIEvents.BAJA_CLIENTE_KO, result);
                break;
            case MOSTRAR_ACTUALIZAR_CLIENTE:
                guiFactory.createActualizarCliente();
                break;
            case ACTUALIZAR_CLIENTE:
                saCliente = saFactory.createCliente();
                result = saCliente.update((TCliente) data);
                IGUI responseActualizarCliente = guiFactory.createResponseActualizarCliente();
                if (result > 0) responseActualizarCliente.update(GUIEvents.ACTUALIZAR_CLIENTE_OK, result);
                else responseActualizarCliente.update(GUIEvents.ACTUALIZAR_CLIENTE_KO, result);
                break;
            case MOSTRAR_MOSTRAR_CLIENTE:
                guiFactory.createMostrarCliente();
                break;
            case MOSTRAR_UNO_ID_CLIENTE:
                saCliente = saFactory.createCliente();
                tCliente = saCliente.read((int) data);
                responseMostrarCliente = guiFactory.createResponseMostrarCliente();
                if (tCliente != null) responseMostrarCliente.update(GUIEvents.MOSTRAR_CLIENTE_OK, tCliente);
                else responseMostrarCliente.update(GUIEvents.MOSTRAR_CLIENTE_KO, null);
                break;
            case MOSTRAR_UNO_NIF_CLIENTE:
                saCliente = saFactory.createCliente();
                tCliente = saCliente.readByNIF((String) data);
                responseMostrarCliente = guiFactory.createResponseMostrarCliente();
                if (tCliente != null) responseMostrarCliente.update(GUIEvents.MOSTRAR_CLIENTE_OK, tCliente);
                else responseMostrarCliente.update(GUIEvents.MOSTRAR_CLIENTE_KO, null);
                break;
            case MOSTRAR_TODO_CLIENTE:
                saCliente = saFactory.createCliente();
                Set<TCliente> tClientes = saCliente.readAll();
                responseMostrarCliente = guiFactory.createResponseMostrarCliente();
                if (!tClientes.isEmpty()) responseMostrarCliente.update(GUIEvents.MOSTRAR_CLIENTE_OK, tClientes);
                else responseMostrarCliente.update(GUIEvents.MOSTRAR_CLIENTE_KO, null);
                break;

            // ==================== PRODUCTO ====================
            case MOSTRAR_ALTA_PRODUCTO:
                guiFactory.createAltaProducto();
                break;
            case ALTA_PRODUCTO:
                saProducto = saFactory.createProducto();
                result = saProducto.create((TProducto) data);
                IGUI responseAltaProducto = guiFactory.createResponseAltaProducto();
                if (result > 0) responseAltaProducto.update(GUIEvents.ALTA_PRODUCTO_OK, result);
                else responseAltaProducto.update(GUIEvents.ALTA_PRODUCTO_KO, result);
                break;
            case MOSTRAR_BAJA_PRODUCTO:
                guiFactory.createBajaProducto();
                break;
            case BAJA_PRODUCTO:
                saProducto = saFactory.createProducto();
                result = saProducto.delete(((TProducto) data).getId());
                IGUI responseBajaProducto = guiFactory.createResponseBajaProducto();
                if (result > 0) responseBajaProducto.update(GUIEvents.BAJA_PRODUCTO_OK, result);
                else responseBajaProducto.update(GUIEvents.BAJA_PRODUCTO_KO, result);
                break;
            case MOSTRAR_ACTUALIZAR_PRODUCTO:
                guiFactory.createActualizarProducto();
                break;
            case ACTUALIZAR_PRODUCTO:
                saProducto = saFactory.createProducto();
                result = saProducto.update((TProducto) data);
                IGUI responseActualizarProducto = guiFactory.createResponseActualizarProducto();
                if (result > 0) responseActualizarProducto.update(GUIEvents.ACTUALIZAR_PRODUCTO_OK, result);
                else responseActualizarProducto.update(GUIEvents.ACTUALIZAR_PRODUCTO_KO, result);
                break;
            case MOSTRAR_MOSTRAR_PRODUCTO:
                guiFactory.createMostrarProducto();
                break;
            case MOSTRAR_UNO_ID_PRODUCTO:
                saProducto = saFactory.createProducto();
                tProducto = saProducto.read((int) data);
                responseMostrarProducto = guiFactory.createResponseMostrarProducto();
                if (tProducto != null) responseMostrarProducto.update(GUIEvents.MOSTRAR_PRODUCTO_OK, tProducto);
                else responseMostrarProducto.update(GUIEvents.MOSTRAR_PRODUCTO_KO, null);
                break;
            case MOSTRAR_TODO_MARCA_PRODUCTO:
                saProducto = saFactory.createProducto();
                Set<TProducto> tProductosMarca = saProducto.readByMarca((int) data);
                responseMostrarProducto = guiFactory.createResponseMostrarProducto();
                if (!tProductosMarca.isEmpty()) responseMostrarProducto.update(GUIEvents.MOSTRAR_PRODUCTO_OK, tProductosMarca);
                else responseMostrarProducto.update(GUIEvents.MOSTRAR_PRODUCTO_KO, null);
                break;
            case MOSTRAR_TODO_PRODUCTO:
                saProducto = saFactory.createProducto();
                Set<TProducto> tProductos = saProducto.readAll();
                responseMostrarProducto = guiFactory.createResponseMostrarProducto();
                if (!tProductos.isEmpty()) responseMostrarProducto.update(GUIEvents.MOSTRAR_PRODUCTO_OK, tProductos);
                else responseMostrarProducto.update(GUIEvents.MOSTRAR_PRODUCTO_KO, null);
                break;

            // ==================== EMPLEADO ====================
            case MOSTRAR_ALTA_EMPLEADO:
                guiFactory.createAltaEmpleado();
                break;
            case ALTA_EMPLEADO:
                saTrabajador = saFactory.createTrabajador();
                result = saTrabajador.create((TTrabajador) data);
                IGUI responseAltaEmpleado = guiFactory.createResponseAltaEmpleado();
                if (result > 0) responseAltaEmpleado.update(GUIEvents.ALTA_EMPLEADO_OK, result);
                else responseAltaEmpleado.update(GUIEvents.ALTA_EMPLEADO_KO, result);
                break;
            case MOSTRAR_BAJA_EMPLEADO:
                guiFactory.createBajaEmpleado();
                break;
            case BAJA_EMPLEADO:
                saTrabajador = saFactory.createTrabajador();
                result = saTrabajador.delete((int) data);
                IGUI responseBajaEmpleado = guiFactory.createResponseBajaEmpleado();
                if (result > 0) responseBajaEmpleado.update(GUIEvents.BAJA_EMPLEADO_OK, result);
                else responseBajaEmpleado.update(GUIEvents.BAJA_EMPLEADO_KO, result);
                break;
            case MOSTRAR_ACTUALIZAR_EMPLEADO:
                guiFactory.createActualizarEmpleado();
                break;
            case ACTUALIZAR_EMPLEADO:
                saTrabajador = saFactory.createTrabajador();
                result = saTrabajador.update((TTrabajador) data);
                IGUI responseActualizarEmpleado = guiFactory.createResponseActualizarEmpleado();
                if (result > 0) responseActualizarEmpleado.update(GUIEvents.ACTUALIZAR_EMPLEADO_OK, result);
                else responseActualizarEmpleado.update(GUIEvents.ACTUALIZAR_EMPLEADO_KO, result);
                break;
            case MOSTRAR_MOSTRAR_EMPLEADO:
                guiFactory.createMostrarEmpleado();
                break;
            case MOSTRAR_UNO_ID_EMPLEADO:
                saTrabajador = saFactory.createTrabajador();
                tTrabajador = saTrabajador.read((int) data);
                responseMostrarEmpleado = guiFactory.createResponseMostrarEmpleado();
                if (tTrabajador != null) responseMostrarEmpleado.update(GUIEvents.MOSTRAR_EMPLEADO_OK, tTrabajador);
                else responseMostrarEmpleado.update(GUIEvents.MOSTRAR_EMPLEADO_KO, null);
                break;
            case MOSTRAR_UNO_NIF_EMPLEADO:
                saTrabajador = saFactory.createTrabajador();
                tTrabajador = saTrabajador.readByNIF((String) data);
                responseMostrarEmpleado = guiFactory.createResponseMostrarEmpleado();
                if (tTrabajador != null) responseMostrarEmpleado.update(GUIEvents.MOSTRAR_EMPLEADO_OK, tTrabajador);
                else responseMostrarEmpleado.update(GUIEvents.MOSTRAR_EMPLEADO_KO, null);
                break;
            case MOSTRAR_TODO_EMPLEADO:
                saTrabajador = saFactory.createTrabajador();
                Set<TTrabajador> tTrabajadores = saTrabajador.readAll();
                responseMostrarEmpleado = guiFactory.createResponseMostrarEmpleado();
                if (!tTrabajadores.isEmpty()) responseMostrarEmpleado.update(GUIEvents.MOSTRAR_EMPLEADO_OK, tTrabajadores);
                else responseMostrarEmpleado.update(GUIEvents.MOSTRAR_EMPLEADO_KO, null);
                break;

            // ==================== VENTA ====================
            case MOSTRAR_ABRIR_VENTA:
                saVenta = saFactory.createVenta();
                guiFactory.createAbrirVenta(saVenta.abrir());
                break;
            case CERRAR_VENTA:
                saVenta = saFactory.createVenta();
                result = saVenta.cerrar((TCarrito) data);
                IGUI responseAbrirVenta = guiFactory.createResponseAbrirVenta();
                if (result > 0) responseAbrirVenta.update(GUIEvents.CERRAR_VENTA_OK, result);
                else responseAbrirVenta.update(GUIEvents.CERRAR_VENTA_KO, null);
                break;
            case MOSTRAR_DEVOLUCION_VENTA:
                guiFactory.createDevolucionVenta();
                break;
            case DEVOLUCION_VENTA:
                saVenta = saFactory.createVenta();
                result = saVenta.devolver((TVenta) data);
                IGUI responseDevolucionVenta = guiFactory.createResponseDevolucionVenta();
                if (result > 0) responseDevolucionVenta.update(GUIEvents.DEVOLUCION_VENTA_OK, result);
                else responseDevolucionVenta.update(GUIEvents.DEVOLUCION_VENTA_KO, result);
                break;
            case MOSTRAR_ACTUALIZAR_VENTA:
                guiFactory.createActualizarVenta();
                break;
            case ACTUALIZAR_VENTA:
                saVenta = saFactory.createVenta();
                result = saVenta.update((TVenta) data);
                IGUI responseActualizarVenta = guiFactory.createResponseActualizarVenta();
                if (result > 0) responseActualizarVenta.update(GUIEvents.ACTUALIZAR_VENTA_OK, result);
                else responseActualizarVenta.update(GUIEvents.ACTUALIZAR_VENTA_KO, result);
                break;
            case MOSTRAR_MOSTRAR_VENTA:
                guiFactory.createMostrarVenta();
                break;
            case MOSTRAR_UNO_ID_VENTA:
                saVenta = saFactory.createVenta();
                TOAVenta toaVenta = saVenta.read((int) data);
                responseMostrarVenta = guiFactory.createResponseMostrarVenta();
                if (toaVenta != null) responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_OK, toaVenta);
                else responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_KO, null);
                break;
            case MOSTRAR_VENTA_ID_CLIENTE:
                saVenta = saFactory.createVenta();
                tVentas = saVenta.readByIdCliente((int) data);
                responseMostrarVenta = guiFactory.createResponseMostrarVenta();
                if (!tVentas.isEmpty()) responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
                else responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_KO, null);
                break;
            case MOSTRAR_VENTA_ID_EMPLEADO:
                saVenta = saFactory.createVenta();
                tVentas = saVenta.readByIdEmpleado((int) data);
                responseMostrarVenta = guiFactory.createResponseMostrarVenta();
                if (!tVentas.isEmpty()) responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
                else responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_KO, null);
                break;
            case MOSTRAR_VENTA_FECHA:
                saVenta = saFactory.createVenta();
                tVentas = saVenta.readByFecha((String) data);
                responseMostrarVenta = guiFactory.createResponseMostrarVenta();
                if (!tVentas.isEmpty()) responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
                else responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_KO, null);
                break;
            case MOSTRAR_TODO_VENTA:
                saVenta = saFactory.createVenta();
                tVentas = saVenta.readAll();
                responseMostrarVenta = guiFactory.createResponseMostrarVenta();
                if (!tVentas.isEmpty()) responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_OK, tVentas);
                else responseMostrarVenta.update(GUIEvents.MOSTRAR_VENTA_KO, null);
                break;

            default:
                break;
            }
        });
    }
}
