package negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.DBSetup;

public class SAClienteTest {

    private SACliente sa;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
    }

    @Before
    public void setUp() {
        sa = SAFactory.getInstance().createCliente();
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
        TCliente tCliente = new TCliente(-1, "Juan", "SA", "TEST_SA_NIF001", "j@sa.com", "600001001");
        idCreado = sa.create(tCliente);
        assertTrue(idCreado > 0);
    }

    @Test
    public void createNIFDuplicadoFalla() {
        idCreado = sa.create(new TCliente(-1, "Ana", "SA", "TEST_SA_NIF002", "a@sa.com", "600001002"));
        int resultado = sa.create(new TCliente(-1, "Ana Duplicada", "SA", "TEST_SA_NIF002", "a2@sa.com", "600001099"));
        assertEquals(0, resultado);
    }

    @Test
    public void readExito() {
        idCreado = sa.create(new TCliente(-1, "Pedro", "SA", "TEST_SA_NIF003", "p@sa.com", "600001003"));
        TCliente leido = sa.read(idCreado);
        assertNotNull(leido);
        assertEquals("Pedro", leido.getNombre());
        assertEquals("TEST_SA_NIF003", leido.getNIF());
    }

    @Test
    public void readNoExiste() {
        TCliente leido = sa.read(-999);
        assertNull(leido);
    }

    @Test
    public void updateExito() {
        idCreado = sa.create(new TCliente(-1, "Maria", "SA", "TEST_SA_NIF004", "m@sa.com", "600001004"));
        TCliente modificado = new TCliente(idCreado, "Maria Updated", "SA", "TEST_SA_NIF004", "m2@sa.com", "600001099");
        int resultado = sa.update(modificado);
        assertEquals(idCreado, resultado);
        assertEquals("Maria Updated", sa.read(idCreado).getNombre());
    }

    @Test
    public void deleteExito() {
        idCreado = sa.create(new TCliente(-1, "Luis", "SA", "TEST_SA_NIF005", "l@sa.com", "600001005"));
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
    public void readByNIFExito() {
        idCreado = sa.create(new TCliente(-1, "Rosa", "SA", "TEST_SA_NIF006", "r@sa.com", "600001006"));
        TCliente leido = sa.readByNIF("TEST_SA_NIF006");
        assertNotNull(leido);
        assertEquals("TEST_SA_NIF006", leido.getNIF());
    }

    @Test
    public void readByNIFNoExiste() {
        TCliente leido = sa.readByNIF("TEST_SA_NIF_NOEXISTE99999");
        assertNull(leido);
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(sa.readAll());
    }
}
