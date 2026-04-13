package presentacion;

import javax.swing.*;

public class ResponseAltaMarca extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ALTA_MARCA_OK)
            JOptionPane.showMessageDialog(null, "Marca dada de alta con id: " + (int) data);
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de alta la marca. Puede que ya exista.");
    }
}
