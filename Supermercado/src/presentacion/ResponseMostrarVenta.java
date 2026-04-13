package presentacion;

import java.util.Set;
import javax.swing.*;
import negocio.TOAVenta;
import negocio.TVenta;

public class ResponseMostrarVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.MOSTRAR_VENTA_OK) {
            StringBuilder sb = new StringBuilder();
            if (data instanceof TOAVenta) {
                sb.append(data.toString());
            } else if (data instanceof TVenta) {
                sb.append(data.toString());
            } else if (data instanceof Set) {
                for (TVenta v : (Set<TVenta>) data) sb.append(v.toString());
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Ventas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron ventas.");
        }
    }
}
