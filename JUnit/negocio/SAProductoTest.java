package negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.DBSetup;

public class SAProductoTest {

    private static int idMarcaTest;
    private SAProducto sa;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
        idMarcaTest = SAFactory.getInstance().createMarca().create(new TMarca(-1, "TEST_SAMarcaProd"));
    }

    @AfterClass
    public static void cleanUpMarca() {
        if (idMarcaTest > 0) {
            SAFactory.getInstance().createMarca().delete(idMarcaTest);
        }
    }

    @Before
    public void setUp() {
        sa = SAFactory.getInstance().createProducto();
        idCreado = 0;
    }

    @After
    public void tearDown() {
        if (idCreado > 0) {
            sa.delete(idCreado);
        }
    }

    @Test
    public void createExito() {
        TProducto tProducto = new TProducto(-1, "TEST_SAProducto", 2.5, "2026-12-31", 10, idMarcaTest);
        idCreado = sa.create(tProducto);
        assertTrue(idCreado > 0);
    }

    @Test
    public void readExito() {
        idCreado = sa.create(new TProducto(-1, "TEST_SAProdRead", 3.0, "2026-12-31", 5, idMarcaTest));
        TProducto leido = sa.read(idCreado);
        assertNotNull(leido);
        assertEquals("TEST_SAProdRead", leido.getNombre());
        assertEquals(3.0, leido.getPrecio(), 0.001);
    }

    @Test
    public void readNoExiste() {
        TProducto leido = sa.read(-999);
        assertNull(leido);
    }

    @Test
    public void updateExito() {
        idCreado = sa.create(new TProducto(-1, "TEST_SAProdOrig", 1.0, "2026-01-01", 2, idMarcaTest));
        TProducto modificado = new TProducto(idCreado, "TEST_SAProdMod", 5.0, "2027-06-30", 20, idMarcaTest);
        int resultado = sa.update(modificado);
        assertEquals(idCreado, resultado);
        TProducto leido = sa.read(idCreado);
        assertEquals("TEST_SAProdMod", leido.getNombre());
        assertEquals(5.0, leido.getPrecio(), 0.001);
    }

    @Test
    public void deleteExito() {
        idCreado = sa.create(new TProducto(-1, "TEST_SAProdDel", 0.5, "2026-01-01", 1, idMarcaTest));
        int idABorrar = idCreado;
        int resultado = sa.delete(idABorrar);
        idCreado = 0;
        assertEquals(idABorrar, resultado);
        assertNull(sa.read(idABorrar));
    }

    @Test
    public void deleteNoExisteDevuelveCero() {
        int resultado = sa.delete(-999);
        assertEquals(0, resultado);
    }

    @Test
    public void readByMarcaExito() {
        idCreado = sa.create(new TProducto(-1, "TEST_SAProdByMarca", 1.0, "2026-01-01", 1, idMarcaTest));
        assertFalse(sa.readByMarca(idMarcaTest).isEmpty());
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(sa.readAll());
    }
}
