package presentacion;

import javax.swing.*;

public class MostrarEmpleado extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public MostrarEmpleado() {
        setTitle("Mostrar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        String[] opciones = {"Por ID", "Por NIF", "Todos"};
        JComboBox<String> combo = new JComboBox<>(opciones);
        panel.add(new JLabel("Buscar:")); panel.add(combo);
        JTextField valorField = new JTextField(15); panel.add(valorField);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnBuscar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnBuscar.addActionListener(e -> {
            String valor = valorField.getText().trim();
            int selected = combo.getSelectedIndex();
            try {
                if (selected == 0) {
                    Controller.getInstance().handleAction(Events.MOSTRAR_UNO_ID_EMPLEADO, Integer.parseInt(valor));
                } else if (selected == 1) {
                    Controller.getInstance().handleAction(Events.MOSTRAR_UNO_NIF_EMPLEADO, valor);
                } else {
                    Controller.getInstance().handleAction(Events.MOSTRAR_TODO_EMPLEADO, null);
                }
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor invalido");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
