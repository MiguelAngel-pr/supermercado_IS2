package presentacion;

import javax.swing.*;

public class ResponseBajaCliente extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.BAJA_CLIENTE_OK)
            JOptionPane.showMessageDialog(null, "Cliente dado de baja correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el cliente. Verifique el ID.");
    }
}
