package presentacion;

import javax.swing.*;

public class ResponseAbrirVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.CERRAR_VENTA_OK)
            JOptionPane.showMessageDialog(null, "Venta cerrada con exito. ID: " + (int) data);
        else
            JOptionPane.showMessageDialog(null, "No se pudo cerrar la venta. Verifique los datos.");
    }
}
