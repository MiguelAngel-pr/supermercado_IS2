package presentacion;

import javax.swing.*;
import negocio.TCliente;

public class ActualizarCliente extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public ActualizarCliente() {
        setTitle("Actualizar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:")); JTextField idField = new JTextField(8); panel.add(idField);
        panel.add(new JLabel("Nombre:")); JTextField nombreField = new JTextField(15); panel.add(nombreField);
        panel.add(new JLabel("Apellidos:")); JTextField apellidosField = new JTextField(20); panel.add(apellidosField);
        panel.add(new JLabel("NIF:")); JTextField nifField = new JTextField(10); panel.add(nifField);
        panel.add(new JLabel("Email:")); JTextField emailField = new JTextField(20); panel.add(emailField);
        panel.add(new JLabel("Telefono:")); JTextField telefonoField = new JTextField(12); panel.add(telefonoField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
            	int id = Integer.parseInt(idField.getText().trim());
                String telefono = telefonoField.getText().trim();

                if (!telefono.isEmpty() && !telefono.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "El teléfono solo puede contener números.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TCliente tCliente = new TCliente(id, nombreField.getText().trim(),
                    apellidosField.getText().trim(), nifField.getText().trim(),
                    emailField.getText().trim(), telefono);

                Controller.getInstance().handleAction(Events.ACTUALIZAR_CLIENTE, tCliente);
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
