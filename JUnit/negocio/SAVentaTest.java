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
            .create(new TMarca(-1, "TEST_SA_MarcaVenta"));
        idProductoTest = SAFactory.getInstance().createProducto()
            .create(new TProducto(-1, "ProductoVentaTest", 2.50, "2030-01-01", 100, idMarcaTest));
    }

    @AfterClass
    public static void cleanUp() {
        if (idProductoTest > 0) SAFactory.getInstance().createProducto().delete(idProductoTest);
        if (idMarcaTest    > 0) SAFactory.getInstance().createMarca().delete(idMarcaTest);
        if (idClienteTest  > 0) SAFactory.getInstance().createCliente().delete(idClienteTest);
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
            sa.devolver(new TVenta(idCreado, "", 0, 0, 0));
        }
    }

    /** Carrito con producto inexistente (idProducto=0 → importe queda en 0). */
    private TCarrito buildCarrito() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, idClienteTest);
        carrito.addItem(new TVentaProducto(0, 0, 1));
        return carrito;
    }

    /** Carrito con el producto de prueba real (precio 2.50 €). */
    private TCarrito buildCarritoConProductoReal(int cantidad) {
        TCarrito carrito = new TCarrito(idEmpleadoTest, idClienteTest);
        carrito.addItem(new TVentaProducto(0, idProductoTest, cantidad));
        return carrito;
    }

    @Test
    public void abrirDevuelveCarritoVacio() {
        TCarrito carrito = sa.abrir();
        assertNotNull(carrito);
        assertTrue(carrito.getItems().isEmpty());
    }

    @Test
    public void cerrarConCarritoVacioFalla() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, idClienteTest);
        int resultado = sa.cerrar(carrito);
        assertEquals(0, resultado);
    }

    @Test
    public void cerrarSinEmpleadoFalla() {
        TCarrito carrito = new TCarrito(-1, idClienteTest);
        carrito.addItem(new TVentaProducto(0, 1, 1));
        int resultado = sa.cerrar(carrito);
        assertEquals(0, resultado);
    }

    @Test
    public void cerrarSinClienteFalla() {
        TCarrito carrito = new TCarrito(idEmpleadoTest, -1);
        carrito.addItem(new TVentaProducto(0, 1, 1));
        int resultado = sa.cerrar(carrito);
        assertEquals(0, resultado);
    }

    @Test
    public void cerrarCalculaImporteCorrectamente() {
        // 3 unidades x 2.50 € = 7.50 €
        TCarrito carrito = buildCarritoConProductoReal(3);
        idCreado = sa.cerrar(carrito);
        assertTrue("La venta debe crearse con exito", idCreado > 0);

        TOAVenta toa = sa.read(idCreado);
        assertNotNull(toa);
        assertEquals(7.50, toa.getTVenta().getImporte(), 0.001);
    }

    @Test
    public void readNoExiste() {
        TOAVenta leida = sa.read(-999);
        assertNull(leida);
    }

    @Test
    public void updateExito() {
        idCreado = sa.cerrar(buildCarrito());
        assertTrue(idCreado > 0);
        TVenta modificada = new TVenta(idCreado, "2026-06-01", 200.0, idClienteTest, idEmpleadoTest);
        int resultado = sa.update(modificada);
        assertEquals(idCreado, resultado);
    }

    @Test
    public void devolverExito() {
        idCreado = sa.cerrar(buildCarrito());
        assertTrue(idCreado > 0);
        int resultado = sa.devolver(new TVenta(idCreado, "", 0, 0, 0));
        idCreado = 0;
        assertTrue(resultado > 0);
    }

    @Test
    public void devolverNoExisteDevuelveCero() {
        int resultado = sa.devolver(new TVenta(-999, "", 0, 0, 0));
        assertEquals(0, resultado);
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(sa.readAll());
    }
}
