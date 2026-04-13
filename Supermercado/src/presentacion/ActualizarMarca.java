package presentacion;

import javax.swing.*;
import negocio.TMarca;

public class ActualizarMarca extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public ActualizarMarca() {
        setTitle("Actualizar Marca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:"));
        JTextField idField = new JTextField(8);
        panel.add(idField);
        panel.add(new JLabel("Nuevo nombre:"));
        JTextField nombreField = new JTextField(20);
        panel.add(nombreField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar);
        panel.add(btnCancelar);

        add(panel);
        pack();
        setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String nombre = nombreField.getText().trim();
                if (!nombre.isEmpty()) {
                    TMarca tMarca = new TMarca(id, nombre, true);
                    Controller.getInstance().handleAction(Events.ACTUALIZAR_MARCA, tMarca);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalido");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
