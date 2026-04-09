package presentacion;

import javax.swing.*;

import negocio.TCarrito;
import negocio.TVentaProducto;

public class AbrirVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    public AbrirVenta(TCarrito tCarrito) {
        setTitle("Abrir Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID Empleado:")); JTextField idEmpleadoField = new JTextField(8); panel.add(idEmpleadoField);
        panel.add(new JLabel("ID Cliente:")); JTextField idClienteField = new JTextField(8); panel.add(idClienteField);
        panel.add(new JLabel("--- Productos (ID:Cantidad, separados por coma) ---"));
        JTextField productosField = new JTextField(30); panel.add(productosField);
        panel.add(new JLabel("Importe total:")); JTextField importeField = new JTextField(10); panel.add(importeField);

        JButton btnCerrar = new JButton("Cerrar venta");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnCerrar); panel.add(btnCancelar);

        add(panel); pack(); setLocationRelativeTo(null);

        btnCerrar.addActionListener(e -> {
            try {
                int idEmpleado = Integer.parseInt(idEmpleadoField.getText().trim());
                int idCliente = Integer.parseInt(idClienteField.getText().trim());
                double importe = Double.parseDouble(importeField.getText().trim());

                TCarritoConImporte carritoConImporte = new TCarritoConImporte(idEmpleado, idCliente, importe);
                // Parse products: format "idProducto:cantidad,idProducto:cantidad"
                String[] items = productosField.getText().trim().split(",");
                for (String item : items) {
                    String[] parts = item.trim().split(":");
                    if (parts.length == 2) {
                        int idProducto = Integer.parseInt(parts[0].trim());
                        int cantidad = Integer.parseInt(parts[1].trim());
                        carritoConImporte.addItem(new TVentaProducto(0, idProducto, cantidad));
                    }
                }

                Controller.getInstance().handleAction(Events.CERRAR_VENTA, carritoConImporte);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Datos invalidos. Revise los campos.");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
