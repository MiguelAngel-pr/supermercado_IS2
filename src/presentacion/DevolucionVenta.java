package presentacion;

import javax.swing.*;

public class DevolucionVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public DevolucionVenta() {
        setTitle("Devolucion de Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID de la venta a devolver:"));
        JTextField idField = new JTextField(10); panel.add(idField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Controller.getInstance().handleAction(Events.DEVOLUCION_VENTA, id);
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
