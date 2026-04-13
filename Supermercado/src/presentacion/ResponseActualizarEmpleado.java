package presentacion;

import javax.swing.*;

public class ResponseActualizarEmpleado extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.ACTUALIZAR_EMPLEADO_OK)
            JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el empleado.");
    }
}
