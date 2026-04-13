package presentacion;

import java.util.Set;
import javax.swing.*;
import negocio.TTrabajador;

public class ResponseMostrarEmpleado extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.MOSTRAR_EMPLEADO_OK) {
            StringBuilder sb = new StringBuilder();
            if (data instanceof TTrabajador) {
                sb.append(data.toString());
            } else if (data instanceof Set) {
                for (TTrabajador t : (Set<TTrabajador>) data) sb.append(t.toString());
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Empleados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron empleados.");
        }
    }
}
