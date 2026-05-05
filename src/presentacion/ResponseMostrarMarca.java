package presentacion;

import java.util.Set;
import javax.swing.*;
import negocio.TMarca;

public class ResponseMostrarMarca extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.MOSTRAR_MARCA_OK) {
            StringBuilder sb = new StringBuilder();
            if (data instanceof TMarca) {
                sb.append(data.toString());
            } else if (data instanceof Set) {
                for (TMarca m : (Set<TMarca>) data) sb.append(m.toString());
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Marcas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron marcas.");
        }
    }
}
