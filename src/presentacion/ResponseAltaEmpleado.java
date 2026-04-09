package presentacion;

import javax.swing.*;

public class ResponseAltaEmpleado extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ALTA_EMPLEADO_OK)
            JOptionPane.showMessageDialog(null, "Empleado dado de alta con id: " + (int) data);
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de alta el empleado. El NIF puede ya existir.");
    }
}
