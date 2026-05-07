package presentacion;

import javax.swing.*;
import negocio.TOAVenta;

public class ResponseAbrirVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.CERRAR_VENTA_OK) {
            TOAVenta toa = (TOAVenta) data;
            int id        = toa.getTVenta().getId();
            double importe = toa.getTVenta().getImporte();
            JOptionPane.showMessageDialog(null,
                    "Venta cerrada con exito.\nID: " + id +
                    "\nImporte total: " + importe);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo cerrar la venta. Verifique que los IDs de empleado, cliente y productos existan.");
        }
    }
}
