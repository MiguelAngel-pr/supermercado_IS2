package presentacion;

import javax.swing.*;

public class ResponseBajaEmpleado extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.BAJA_EMPLEADO_OK)
            JOptionPane.showMessageDialog(null, "Empleado dado de baja correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el empleado. Verifique el ID.");
    }
}
