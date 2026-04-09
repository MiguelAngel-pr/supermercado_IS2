package presentacion;

import java.util.Set;
import javax.swing.*;
import negocio.TCliente;

public class ResponseMostrarCliente extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.MOSTRAR_CLIENTE_OK) {
            StringBuilder sb = new StringBuilder();
            if (data instanceof TCliente) {
                sb.append(data.toString());
            } else if (data instanceof Set) {
                for (TCliente c : (Set<TCliente>) data) sb.append(c.toString());
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Clientes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron clientes.");
        }
    }
}
