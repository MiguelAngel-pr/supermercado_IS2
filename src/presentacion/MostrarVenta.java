package presentacion;

import javax.swing.*;

public class MostrarVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public MostrarVenta() {
        setTitle("Mostrar Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        String[] opciones = {"Por ID", "Por ID Cliente", "Por ID Empleado", "Por Fecha", "Todas"};
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
                switch (selected) {
                    case 0: Controller.getInstance().handleAction(Events.MOSTRAR_UNO_ID_VENTA, Integer.parseInt(valor)); break;
                    case 1: Controller.getInstance().handleAction(Events.MOSTRAR_VENTA_ID_CLIENTE, Integer.parseInt(valor)); break;
                    case 2: Controller.getInstance().handleAction(Events.MOSTRAR_VENTA_ID_EMPLEADO, Integer.parseInt(valor)); break;
                    case 3: Controller.getInstance().handleAction(Events.MOSTRAR_VENTA_FECHA, valor); break;
                    default: Controller.getInstance().handleAction(Events.MOSTRAR_TODO_VENTA, null); break;
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
