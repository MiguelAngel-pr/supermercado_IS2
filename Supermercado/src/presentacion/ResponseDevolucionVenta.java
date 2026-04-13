package presentacion;

import javax.swing.*;

public class ResponseDevolucionVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.DEVOLUCION_VENTA_OK)
            JOptionPane.showMessageDialog(null, "Devolucion realizada correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo realizar la devolucion. Verifique el ID.");
    }
}
