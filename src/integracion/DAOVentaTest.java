package integracion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.TAdministrador;
import negocio.TCarrito;
import negocio.TCliente;
import negocio.TVenta;

public class DAOVentaTest {

    private static int idClienteTest;
    private static int idEmpleadoTest;

    private DAOVenta dao;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
        idClienteTest = DAOFactory.getInstance().createCliente()
            .create(new TCliente(-1, "VentaTestCliente", "Test", "TEST_NIF_VCL01", "v@test.com", "600"));
        idEmpleadoTest = DAOFactory.getInstance().createTrabajador()
            .create(new TAdministrador(-1, "VentaTestAdmin", "Test", "TEST_NIF_VEM01", "Completa", 2000.0, ""));
    }

    @AfterClass
    public static void cleanUp() {
        if (idClienteTest > 0) DAOFactory.getInstance().createCliente().delete(idClienteTest);
        if (idEmpleadoTest > 0) DAOFactory.getInstance().createTrabajador().delete(idEmpleadoTest);
    }

    @Before
    public void setUp() {
        dao = DAOFactory.getInstance().createVenta();
        idCreado = 0;
    }

    @After
    public void tearDown() {
        if (idCreado > 0) {
            dao.delete(idCreado);
        }
    }

    private TCarrito buildCarrito() {
        return new TCarrito(idEmpleadoTest, idClienteTest);
    }

    @Test
    public void createExito() {
        TVenta tVenta = new TVenta(-1, "2026-01-01", 50.0, idClienteTest, idEmpleadoTest);
        idCreado = dao.create(tVenta, buildCarrito());
        assertTrue(idCreado > 0);
    }

    @Test
    public void readExito() {
        TVenta tVenta = new TVenta(-1, "2026-02-01", 75.0, idClienteTest, idEmpleadoTest);
        idCreado = dao.create(tVenta, buildCarrito());
        TVenta leida = dao.read(idCreado);
        assertNotNull(leida);
        assertEquals("2026-02-01", leida.getFecha());
        assertEquals(75.0, leida.getImporte(), 0.001);
    }

    @Test
    public void readNoExiste() {
        TVenta leida = dao.read(-999);
        assertNull(leida);
    }

    @Test
    public void updateExito() {
        idCreado = dao.create(new TVenta(-1, "2026-03-01", 100.0, idClienteTest, idEmpleadoTest), buildCarrito());
        TVenta modificada = new TVenta(idCreado, "2026-03-15", 120.0, idClienteTest, idEmpleadoTest);
        int resultado = dao.update(modificada);
        assertEquals(idCreado, resultado);
        TVenta leida = dao.read(idCreado);
        assertEquals("2026-03-15", leida.getFecha());
        assertEquals(120.0, leida.getImporte(), 0.001);
    }

    @Test
    public void updateNoExiste() {
        int resultado = dao.update(new TVenta(-999, "2026-01-01", 0, idClienteTest, idEmpleadoTest));
        assertEquals(0, resultado);
    }

    @Test
    public void deleteExito() {
        idCreado = dao.create(new TVenta(-1, "2026-04-01", 30.0, idClienteTest, idEmpleadoTest), buildCarrito());
        int idABorrar = idCreado;
        int resultado = dao.delete(idABorrar);
        idCreado = 0;
        assertEquals(idABorrar, resultado);
        assertNull(dao.read(idABorrar));
    }

    @Test
    public void deleteNoExiste() {
        int resultado = dao.delete(-999);
        assertEquals(0, resultado);
    }

    @Test
    public void readByFecha() {
        idCreado = dao.create(new TVenta(-1, "2026-05-20", 60.0, idClienteTest, idEmpleadoTest), buildCarrito());
        assertFalse(dao.readByFecha("2026-05-20").isEmpty());
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(dao.readAll());
    }
}
