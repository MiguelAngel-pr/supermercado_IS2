package presentacion;

import javax.swing.*;

public class BajaEmpleado extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public BajaEmpleado() {
        setTitle("Baja de Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID del empleado:"));
        JTextField idField = new JTextField(10); panel.add(idField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Controller.getInstance().handleAction(Events.BAJA_EMPLEADO, id);
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
