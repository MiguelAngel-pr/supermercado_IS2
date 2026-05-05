package presentacion;

import javax.swing.*;

public class ResponseActualizarCliente extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ACTUALIZAR_CLIENTE_OK)
            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente.");
    }
}
