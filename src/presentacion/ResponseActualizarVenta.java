package presentacion;

import javax.swing.*;

public class ResponseActualizarVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ACTUALIZAR_VENTA_OK)
            JOptionPane.showMessageDialog(null, "Venta actualizada correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la venta.");
    }
}
