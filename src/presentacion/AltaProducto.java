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
//                String nombre = nombreField.getText().trim();
//                double precio = Double.parseDouble(precioField.getText().trim());
//                String fecha = fechaField.getText().trim();
//                int cantidad = Integer.parseInt(cantidadField.getText().trim());
//                int idMarca = Integer.parseInt(idMarcaField.getText().trim());
//                if (!nombre.isEmpty()) {
//                    TProducto tProducto = new TProducto(-1, nombre, precio, fecha, cantidad, idMarca);
//                    Controller.getInstance().handleAction(Events.ALTA_PRODUCTO, tProducto);
//                    dispose();
            	
            	String nombre = nombreField.getText().trim();
                String precioStr = precioField.getText().trim();
                String fecha = fechaField.getText().trim();
                String cantidadStr = cantidadField.getText().trim();
                String idMarcaStr = idMarcaField.getText().trim();
                
                if (nombre.isEmpty() || precioStr.isEmpty() || fecha.isEmpty() || cantidadStr.isEmpty() || idMarcaStr.isEmpty()) {
                    throw new IllegalArgumentException("Todos los campos son obligatorios.");
                }
                
                double precio = Double.parseDouble(precioStr);
                int cantidad = Integer.parseInt(cantidadStr);
                int idMarca = Integer.parseInt(idMarcaStr);

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
                
                TProducto tProducto = new TProducto(-1, nombre, precio, fecha, cantidad, idMarca);
                Controller.getInstance().handleAction(Events.ALTA_PRODUCTO, tProducto);
                dispose();
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Precio, Cantidad e ID Marca deben ser valores numéricos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
	        }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
