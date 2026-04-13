package presentacion;

import javax.swing.*;

public class ResponseBajaProducto extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.BAJA_PRODUCTO_OK)
            JOptionPane.showMessageDialog(null, "Producto dado de baja correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el producto. Verifique el ID.");
    }
}
