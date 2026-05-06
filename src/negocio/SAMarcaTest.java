package negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.DBSetup;

public class SAMarcaTest {

    private SAMarca sa;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
    }

    @Before
    public void setUp() {
        sa = SAFactory.getInstance().createMarca();
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
        idCreado = sa.create(new TMarca(-1, "TEST_SAMarca"));
        assertTrue(idCreado > 0);
    }

    @Test
    public void createDuplicadoFalla() {
        idCreado = sa.create(new TMarca(-1, "TEST_SAMarcaDup"));
        int resultado = sa.create(new TMarca(-1, "TEST_SAMarcaDup"));
        assertEquals(0, resultado);
    }

    @Test
    public void readExito() {
        idCreado = sa.create(new TMarca(-1, "TEST_SAMarcaRead"));
        TMarca leida = sa.read(idCreado);
        assertNotNull(leida);
        assertEquals("TEST_SAMarcaRead", leida.getNombre());
    }

    @Test
    public void readNoExiste() {
        TMarca leida = sa.read(-999);
        assertNull(leida);
    }

    @Test
    public void updateExito() {
        idCreado = sa.create(new TMarca(-1, "TEST_SAMarcaOrig"));
        TMarca modificada = new TMarca(idCreado, "TEST_SAMarcaMod");
        int resultado = sa.update(modificada);
        assertEquals(idCreado, resultado);
        assertEquals("TEST_SAMarcaMod", sa.read(idCreado).getNombre());
    }

    @Test
    public void deleteExito() {
        idCreado = sa.create(new TMarca(-1, "TEST_SAMarcaDel"));
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
    public void readByNameExito() {
        idCreado = sa.create(new TMarca(-1, "TEST_SAMarcaByName"));
        TMarca leida = sa.readByName("TEST_SAMarcaByName");
        assertNotNull(leida);
        assertEquals("TEST_SAMarcaByName", leida.getNombre());
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(sa.readAll());
    }
}
