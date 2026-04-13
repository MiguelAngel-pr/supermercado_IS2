package presentacion;

import javax.swing.*;

public class ResponseActualizarProducto extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ACTUALIZAR_PRODUCTO_OK)
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto.");
    }
}
