package presentacion;

import javax.swing.*;

public class ResponseBajaMarca extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.BAJA_MARCA_OK)
            JOptionPane.showMessageDialog(null, "Marca dada de baja correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja la marca. Verifique el ID.");
    }
}
