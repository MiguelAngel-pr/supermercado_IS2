package presentacion;

import javax.swing.*;

public class ResponseAltaProducto extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ALTA_PRODUCTO_OK)
            JOptionPane.showMessageDialog(null, "Producto dado de alta con id: " + (int) data);
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de alta el producto.");
    }
}
