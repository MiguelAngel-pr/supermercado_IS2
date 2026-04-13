package presentacion;

import javax.swing.*;
import negocio.TCliente;

public class AltaCliente extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public AltaCliente() {
        setTitle("Alta de Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
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
            if (!nifField.getText().trim().isEmpty() && !nombreField.getText().trim().isEmpty()
                    && !apellidosField.getText().trim().isEmpty()) {
                TCliente tCliente = new TCliente(-1, nombreField.getText().trim(),
                    apellidosField.getText().trim(), nifField.getText().trim(),
                    emailField.getText().trim(), telefonoField.getText().trim(), true);
                Controller.getInstance().handleAction(Events.ALTA_CLIENTE, tCliente);
                dispose();
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
