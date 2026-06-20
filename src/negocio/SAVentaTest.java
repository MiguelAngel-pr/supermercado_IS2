package negocio;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import integracion.DBSetup;

public class SAVentaTest {

    private static int idClienteTest;
    private static int idEmpleadoTest;
    private static int idMarcaTest;
    private static int idProductoTest;

    private SAVenta sa;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();

        idClienteTest = SAFactory.getInstance().createCliente()
            .create(new TCliente(-1, "VentaSACliente", "Test", "TEST_SA_VCL01", "vsa@test.com", "611"));

        idEmpleadoTest = SAFactory.getInstance().createTrabajador()
            .create(new TAdministrador(-1, "VentaSAAdmin", "Test", "TEST_SA_VEM01", "Completa", 2000.0, ""));

        idMarcaTest = SAFactory.getInstance().createMarca()
            .create(new TMarca(-1, "TEST_SAMarcaVenta"));

        // Producto con stock 100 para que ningún test lo agote
        idProductoTest = SAFactory.getInstance().createProducto()
            .create(new TProducto(-1, "TEST_SAProductoVenta", 5.0, "2030-01-01", 100, idMarcaTest));
    }

    @AfterClass
    public static void cleanUp() {
        if (idProductoTest > 0) SAFactory.getInstance().createProducto().delete(idProductoTest);
        if (idMarcaTest > 0)    SAFactory.getInstance().createMarca().delete(idMarcaTest);
        if (idClienteTest > 0)  SAFactory.getInstance().createCliente().delete(idClienteTest);
        if (idEmpleadoTest > 0) SAFactory.getInstance().createTrabajador().delete(idEmpleadoTest);
    }

    @Before
    public void setUp() {
        sa = SAFactory.getInstance().createVenta();
        idCreado = 0;
    }

    @After
    public void tearDown() {
        if (idCreado > 0) {
            // CORREGIDO: Se pasa directamente el id entero sin simular el DTO
            sa.devolver(idCreado);
            idCreado = 0;
        }
    }

    private TCarrito buildCarrito() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, idClienteTest);
        carrito.addItem(new TVentaProducto(0, idProductoTest, 1));
        return carrito;
    }

    // ── abrir ────────────────────────────────────────────────────────────────

    @Test
    public void abrirDevuelveCarritoVacio() {
        TCarrito carrito = sa.abrir();
        assertNotNull(carrito);
        assertTrue(carrito.getItems().isEmpty());
    }

    // ── cerrar ───────────────────────────────────────────────────────────────

    @Test
    public void cerrarExito() {
        TCarrito carrito = buildCarrito();
        idCreado = sa.cerrar(carrito);
        assertTrue(idCreado > 0);
    }

    @Test
    public void cerrarConCarritoVacioFalla() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, idClienteTest);
        int resultado = sa.cerrar(carrito);
        assertEquals(-1, resultado); // -1: carrito vacío
    }

    @Test
    public void cerrarSinEmpleadoFalla() {
        TCarrito carrito = new TCarrito(-999, idClienteTest);
        carrito.addItem(new TVentaProducto(0, idProductoTest, 1));
        int resultado = sa.cerrar(carrito);
        assertEquals(-3, resultado); // -3: empleado no existe
    }

    @Test
    public void cerrarSinClienteFalla() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, -999);
        carrito.addItem(new TVentaProducto(0, idProductoTest, 1));
        int resultado = sa.cerrar(carrito);
        assertEquals(-4, resultado); // -4: cliente no existe
    }

    @Test
    public void cerrarSinStockFalla() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, idClienteTest);
        carrito.addItem(new TVentaProducto(0, idProductoTest, 9999)); // cantidad imposible
        int resultado = sa.cerrar(carrito);
        assertEquals(-2, resultado); // -2: stock insuficiente
    }

    @Test
    public void cerrarConProductoInexistenteFalla() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, idClienteTest);
        carrito.addItem(new TVentaProducto(0, -999, 1)); // producto que no existe
        int resultado = sa.cerrar(carrito);
        assertEquals(-2, resultado); // -2: producto inexistente
    }

    // ── read ─────────────────────────────────────────────────────────────────

    @Test
    public void readExito() {
        idCreado = sa.cerrar(buildCarrito());
        assertTrue(idCreado > 0);
        TOAVenta leida = sa.read(idCreado);
        assertNotNull(leida);
        assertEquals(idCreado, leida.getTVenta().getId());
    }

    @Test
    public void readNoExiste() {
        TOAVenta leida = sa.read(-999);
        assertNull(leida);
    }

    // ── readAll / readBy ──────────────────────────────────────────────────────

    @Test
    public void readAllNoNulo() {
        assertNotNull(sa.readAll());
    }

    // ── update ───────────────────────────────────────────────────────────────

    @Test
    public void updateExito() {
        idCreado = sa.cerrar(buildCarrito());
        assertTrue(idCreado > 0);
        TVenta modificada = new TVenta(idCreado, "2026-06-01", 200.0, idClienteTest, idEmpleadoTest);
        int resultado = sa.update(modificada);
        assertEquals(idCreado, resultado);
    }

    // ── devolver ─────────────────────────────────────────────────────────────

    @Test
    public void devolverExito() {
        idCreado = sa.cerrar(buildCarrito());
        assertTrue(idCreado > 0);
        //CORREGIDO: Se envía directamente el valor primitivo idCreado
        int resultado = sa.devolver(idCreado);
        idCreado = 0; // ya borrada, tearDown no debe intentarlo de nuevo
        assertTrue(resultado > 0);
    }

    @Test
    public void devolverNoExisteDevuelveCero() {
    	//CORREGIDO: Se envía directamente el identificador ficticio -999 sin envolverlo en objeto
        int resultado = sa.devolver(-999);
        assertEquals(0, resultado);
    }
}