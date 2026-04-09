package presentacion;

import javax.swing.*;
import negocio.TAdministrador;
import negocio.TEmpleado;

public class AltaEmpleado extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public AltaEmpleado() {
        setTitle("Alta de Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nombre:")); JTextField nombreField = new JTextField(15); panel.add(nombreField);
        panel.add(new JLabel("Apellidos:")); JTextField apellidosField = new JTextField(20); panel.add(apellidosField);
        panel.add(new JLabel("NIF:")); JTextField nifField = new JTextField(10); panel.add(nifField);
        panel.add(new JLabel("Jornada:")); JTextField jornadaField = new JTextField(10); panel.add(jornadaField);
        panel.add(new JLabel("Salario:")); JTextField salarioField = new JTextField(8); panel.add(salarioField);
        panel.add(new JLabel("Tipo:"));
        String[] tipos = {"Empleado", "Administrador"};
        JComboBox<String> tipoCombo = new JComboBox<>(tipos); panel.add(tipoCombo);
        panel.add(new JLabel("ID Admin (si empleado, -1 si admin):")); JTextField idAdminField = new JTextField(6); panel.add(idAdminField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                String nombre = nombreField.getText().trim();
                String apellidos = apellidosField.getText().trim();
                String nif = nifField.getText().trim();
                String jornada = jornadaField.getText().trim();
                double salario = Double.parseDouble(salarioField.getText().trim());
                int idAdmin = Integer.parseInt(idAdminField.getText().trim());

                if (!nombre.isEmpty() && !nif.isEmpty()) {
                    Object trabajador;
                    if (tipoCombo.getSelectedIndex() == 1) {
                        trabajador = new TAdministrador(-1, nombre, apellidos, nif, jornada, true, salario, "");
                    } else {
                        trabajador = new TEmpleado(-1, nombre, apellidos, nif, jornada, true, salario, idAdmin);
                    }
                    Controller.getInstance().handleAction(Events.ALTA_EMPLEADO, trabajador);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Datos invalidos. Verifique salario e ID admin.");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
