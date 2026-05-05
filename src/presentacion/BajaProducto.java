package presentacion;

import javax.swing.*;
import negocio.TProducto;

public class BajaProducto extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public BajaProducto() {
        setTitle("Baja de Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID del producto:"));
        JTextField idField = new JTextField(10); panel.add(idField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                TProducto tProducto = new TProducto(id, "", 0, "", 0, 0);
                Controller.getInstance().handleAction(Events.BAJA_PRODUCTO, tProducto);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalido");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
