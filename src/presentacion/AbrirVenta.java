package presentacion;

import javax.swing.*;
import java.awt.*;
import negocio.TCarrito;
import negocio.TVentaProducto;

public class AbrirVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    private static final int MAX_PRODUCTOS = 5;

    public AbrirVenta(TCarrito tCarrito) {
        setTitle("Abrir Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Datos generales ---
        JPanel datosPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        datosPanel.setBorder(BorderFactory.createTitledBorder("Datos de la venta"));

        JTextField idEmpleadoField = new JTextField(8);
        JTextField idClienteField  = new JTextField(8);

        datosPanel.add(new JLabel("ID Empleado:")); datosPanel.add(idEmpleadoField);
        datosPanel.add(new JLabel("ID Cliente:"));  datosPanel.add(idClienteField);

        // --- Productos ---
        JPanel productosPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        productosPanel.setBorder(BorderFactory.createTitledBorder(
                "Productos  (el importe se calcula automáticamente)"));

        productosPanel.add(new JLabel("#",           SwingConstants.CENTER));
        productosPanel.add(new JLabel("ID Producto", SwingConstants.CENTER));
        productosPanel.add(new JLabel("Cantidad",    SwingConstants.CENTER));

        JTextField[] idProductoFields = new JTextField[MAX_PRODUCTOS];
        JTextField[] cantidadFields   = new JTextField[MAX_PRODUCTOS];
        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            idProductoFields[i] = new JTextField(6);
            cantidadFields[i]   = new JTextField(4);
            productosPanel.add(new JLabel("  " + (i + 1) + "."));
            productosPanel.add(idProductoFields[i]);
            productosPanel.add(cantidadFields[i]);
        }

        // --- Botones ---
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar   = new JButton("Cerrar venta");
        JButton btnCancelar = new JButton("Cancelar");
        botonesPanel.add(btnCerrar);
        botonesPanel.add(btnCancelar);

        mainPanel.add(datosPanel,     BorderLayout.NORTH);
        mainPanel.add(productosPanel, BorderLayout.CENTER);
        mainPanel.add(botonesPanel,   BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

        btnCerrar.addActionListener(e -> {
            try {
                String empText = idEmpleadoField.getText().trim();
                String cliText = idClienteField.getText().trim();

                if (empText.isEmpty() || cliText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Rellene ID Empleado e ID Cliente.");
                    return;
                }

                int idEmpleado = Integer.parseInt(empText);
                int idCliente  = Integer.parseInt(cliText);

                if (idEmpleado <= 0 || idCliente <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "ID Empleado e ID Cliente deben ser mayores que 0.");
                    return;
                }

                TCarrito carrito = new TCarrito(idEmpleado, idCliente);

                for (int i = 0; i < MAX_PRODUCTOS; i++) {
                    String idProdText = idProductoFields[i].getText().trim();
                    String cantText   = cantidadFields[i].getText().trim();
                    if (idProdText.isEmpty() || cantText.isEmpty()) continue;
                    int idProducto = Integer.parseInt(idProdText);
                    int cantidad   = Integer.parseInt(cantText);
                    if (idProducto > 0 && cantidad > 0) {
                        carrito.addItem(new TVentaProducto(0, idProducto, cantidad));
                    }
                }

                if (carrito.getItems().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Añada al menos un producto con ID y cantidad mayores que 0.");
                    return;
                }

                Controller.getInstance().handleAction(Events.CERRAR_VENTA, carrito);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Datos invalidos. Verifique que los IDs sean números enteros.");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
