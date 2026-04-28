package presentacion;

import javax.swing.*;
import negocio.TVenta;

public class ActualizarVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public ActualizarVenta() {
        setTitle("Actualizar Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID Venta:")); JTextField idField = new JTextField(8); panel.add(idField);
        panel.add(new JLabel("ID Empleado:")); JTextField idEmpleadoField = new JTextField(8); panel.add(idEmpleadoField);
        panel.add(new JLabel("ID Cliente:")); JTextField idClienteField = new JTextField(8); panel.add(idClienteField);
        panel.add(new JLabel("Fecha (YYYY-MM-DD):")); JTextField fechaField = new JTextField(12); panel.add(fechaField);
        panel.add(new JLabel("Importe:")); JTextField importeField = new JTextField(10); panel.add(importeField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int idEmpleado = Integer.parseInt(idEmpleadoField.getText().trim());
                int idCliente = Integer.parseInt(idClienteField.getText().trim());
                double importe = Double.parseDouble(importeField.getText().trim());
                String fecha = fechaField.getText().trim();
                TVenta tVenta = new TVenta(id, fecha, importe, idCliente, idEmpleado);
                Controller.getInstance().handleAction(Events.ACTUALIZAR_VENTA, tVenta);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Datos invalidos");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
