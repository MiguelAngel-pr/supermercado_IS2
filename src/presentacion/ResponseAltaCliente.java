package presentacion;

import javax.swing.*;

public class ResponseAltaCliente extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ALTA_CLIENTE_OK)
            JOptionPane.showMessageDialog(null, "Cliente dado de alta con id: " + (int) data);
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de alta el cliente. El NIF puede ya existir.");
    }
}
