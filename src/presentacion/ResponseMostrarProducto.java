package presentacion;

import java.util.Set;
import javax.swing.*;
import negocio.TProducto;

public class ResponseMostrarProducto extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public void update(GUIEvents event, Object data) {
        if (event == GUIEvents.MOSTRAR_PRODUCTO_OK) {
            StringBuilder sb = new StringBuilder();
            if (data instanceof TProducto) {
                sb.append(data.toString());
            } else if (data instanceof Set) {
                for (TProducto p : (Set<TProducto>) data) sb.append(p.toString());
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Productos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron productos.");
        }
    }
}
