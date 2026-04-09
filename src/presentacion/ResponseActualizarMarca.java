package presentacion;

import javax.swing.*;

public class ResponseActualizarMarca extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ACTUALIZAR_MARCA_OK)
            JOptionPane.showMessageDialog(null, "Marca actualizada correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la marca.");
    }
}
