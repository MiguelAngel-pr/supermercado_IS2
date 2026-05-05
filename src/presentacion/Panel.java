package presentacion;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Panel extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;
    private final Controller controller = Controller.getInstance();

    public Panel() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        setTitle("SuperMarket IS2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        // ---- Marca ----
        JMenu marcaMenu = new JMenu("Marca");
        JMenuItem mAlta = new JMenuItem("Dar de alta");
        JMenuItem mBaja = new JMenuItem("Dar de baja");
        JMenuItem mActualizar = new JMenuItem("Actualizar");
        JMenuItem mMostrar = new JMenuItem("Mostrar");
        marcaMenu.add(mAlta); marcaMenu.add(mBaja);
        marcaMenu.add(mActualizar); marcaMenu.add(mMostrar);

        // ---- Cliente ----
        JMenu clienteMenu = new JMenu("Cliente");
        JMenuItem cAlta = new JMenuItem("Dar de alta");
        JMenuItem cBaja = new JMenuItem("Dar de baja");
        JMenuItem cActualizar = new JMenuItem("Actualizar");
        JMenuItem cMostrar = new JMenuItem("Mostrar");
        clienteMenu.add(cAlta); clienteMenu.add(cBaja);
        clienteMenu.add(cActualizar); clienteMenu.add(cMostrar);

        // ---- Producto ----
        JMenu productoMenu = new JMenu("Producto");
        JMenuItem pAlta = new JMenuItem("Dar de alta");
        JMenuItem pBaja = new JMenuItem("Dar de baja");
        JMenuItem pActualizar = new JMenuItem("Actualizar");
        JMenuItem pMostrar = new JMenuItem("Mostrar");
        productoMenu.add(pAlta); productoMenu.add(pBaja);
        productoMenu.add(pActualizar); productoMenu.add(pMostrar);

        // ---- Empleado ----
        JMenu empleadoMenu = new JMenu("Empleado");
        JMenuItem eAlta = new JMenuItem("Dar de alta");
        JMenuItem eBaja = new JMenuItem("Dar de baja");
        JMenuItem eActualizar = new JMenuItem("Actualizar");
        JMenuItem eMostrar = new JMenuItem("Mostrar");
        empleadoMenu.add(eAlta); empleadoMenu.add(eBaja);
        empleadoMenu.add(eActualizar); empleadoMenu.add(eMostrar);

        // ---- Venta ----
        JMenu ventaMenu = new JMenu("Venta");
        JMenuItem vAbrir = new JMenuItem("Abrir venta");
        JMenuItem vDevolucion = new JMenuItem("Devolucion");
        JMenuItem vActualizar = new JMenuItem("Actualizar");
        JMenuItem vMostrar = new JMenuItem("Mostrar");
        ventaMenu.add(vAbrir); ventaMenu.add(vDevolucion);
        ventaMenu.add(vActualizar); ventaMenu.add(vMostrar);

        menuBar.add(marcaMenu);
        menuBar.add(clienteMenu);
        menuBar.add(productoMenu);
        menuBar.add(empleadoMenu);
        menuBar.add(ventaMenu);
        setJMenuBar(menuBar);

        // ---- Action Listeners: Marca ----
        mAlta.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ALTA_MARCA, null));
        mBaja.addActionListener(e -> controller.handleAction(Events.MOSTRAR_BAJA_MARCA, null));
        mActualizar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ACTUALIZAR_MARCA, null));
        mMostrar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_MOSTRAR_MARCA, null));

        // ---- Action Listeners: Cliente ----
        cAlta.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ALTA_CLIENTE, null));
        cBaja.addActionListener(e -> controller.handleAction(Events.MOSTRAR_BAJA_CLIENTE, null));
        cActualizar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ACTUALIZAR_CLIENTE, null));
        cMostrar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_MOSTRAR_CLIENTE, null));

        // ---- Action Listeners: Producto ----
        pAlta.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ALTA_PRODUCTO, null));
        pBaja.addActionListener(e -> controller.handleAction(Events.MOSTRAR_BAJA_PRODUCTO, null));
        pActualizar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ACTUALIZAR_PRODUCTO, null));
        pMostrar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_MOSTRAR_PRODUCTO, null));

        // ---- Action Listeners: Empleado ----
        eAlta.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ALTA_EMPLEADO, null));
        eBaja.addActionListener(e -> controller.handleAction(Events.MOSTRAR_BAJA_EMPLEADO, null));
        eActualizar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ACTUALIZAR_EMPLEADO, null));
        eMostrar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_MOSTRAR_EMPLEADO, null));

        // ---- Action Listeners: Venta ----
        vAbrir.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ABRIR_VENTA, null));
        vDevolucion.addActionListener(e -> controller.handleAction(Events.MOSTRAR_DEVOLUCION_VENTA, null));
        vActualizar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_ACTUALIZAR_VENTA, null));
        vMostrar.addActionListener(e -> controller.handleAction(Events.MOSTRAR_MOSTRAR_VENTA, null));
    }

    @Override
    public void update(GUIEvents event, Object data) {}
}
