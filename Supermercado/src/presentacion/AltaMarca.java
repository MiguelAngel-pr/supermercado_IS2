package presentacion;

import javax.swing.*;
import negocio.TMarca;

public class AltaMarca extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public AltaMarca() {
        setTitle("Alta de Marca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nombre:"));
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
            if (!nombreField.getText().trim().isEmpty()) {
                TMarca tMarca = new TMarca(-1, nombreField.getText().trim(), true);
                Controller.getInstance().handleAction(Events.ALTA_MARCA, tMarca);
                dispose();
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
