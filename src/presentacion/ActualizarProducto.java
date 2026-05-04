package presentacion;

import javax.swing.*;
import negocio.TProducto;

public class ActualizarProducto extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public ActualizarProducto() {
        setTitle("Actualizar Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:")); JTextField idField = new JTextField(8); panel.add(idField);
        panel.add(new JLabel("Nombre:")); JTextField nombreField = new JTextField(20); panel.add(nombreField);
        panel.add(new JLabel("Precio:")); JTextField precioField = new JTextField(8); panel.add(precioField);
        panel.add(new JLabel("Fecha caducidad:")); JTextField fechaField = new JTextField(12); panel.add(fechaField);
        panel.add(new JLabel("Cantidad:")); JTextField cantidadField = new JTextField(6); panel.add(cantidadField);
        panel.add(new JLabel("ID Marca:")); JTextField idMarcaField = new JTextField(6); panel.add(idMarcaField);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnAceptar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                double precio = Double.parseDouble(precioField.getText().trim());
                int cantidad = Integer.parseInt(cantidadField.getText().trim());
                int idMarca = Integer.parseInt(idMarcaField.getText().trim());
                String fecha = fechaField.getText().trim();
                
                if (precio <= 0)
                	throw new IllegalArgumentException("El precio debe ser mayor que 0.");
                if (cantidad <= 0)
                	throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
                if (idMarca <= 0)
                	throw new IllegalArgumentException("El ID de marca debe ser un número positivo.");

                try {
                    java.time.LocalDate.parse(fecha); 
                } catch (java.time.format.DateTimeParseException ex) {
                    throw new IllegalArgumentException("La fecha debe tener el formato YYYY-MM-DD y ser válida.");
                }
                
                TProducto tProducto = new TProducto(id, nombreField.getText().trim(), precio,
                    fechaField.getText().trim(), cantidad, idMarca);
                Controller.getInstance().handleAction(Events.ACTUALIZAR_PRODUCTO, tProducto);
                dispose();
            } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Precio, Cantidad e ID Marca deben ser valores numéricos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
	        }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
