package presentacion;

import javax.swing.*;
import negocio.TProducto;

public class AltaProducto extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public AltaProducto() {
        setTitle("Alta de Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nombre:")); JTextField nombreField = new JTextField(20); panel.add(nombreField);
        panel.add(new JLabel("Precio:")); JTextField precioField = new JTextField(8); panel.add(precioField);
        panel.add(new JLabel("Fecha caducidad (YYYY-MM-DD):")); JTextField fechaField = new JTextField(12); panel.add(fechaField);
        panel.add(new JLabel("Cantidad:")); JTextField cantidadField = new JTextField(6); panel.add(cantidadField);
        panel.add(new JLabel("ID Marca:")); JTextField idMarcaField = new JTextField(6); panel.add(idMarcaField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                String nombre = nombreField.getText().trim();
                double precio = Double.parseDouble(precioField.getText().trim());
                String fecha = fechaField.getText().trim();
                int cantidad = Integer.parseInt(cantidadField.getText().trim());
                int idMarca = Integer.parseInt(idMarcaField.getText().trim());
                if (!nombre.isEmpty()) {
                    TProducto tProducto = new TProducto(-1, nombre, precio, fecha, cantidad, idMarca);
                    Controller.getInstance().handleAction(Events.ALTA_PRODUCTO, tProducto);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Datos invalidos. Verifique precio, cantidad e ID de marca.");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
