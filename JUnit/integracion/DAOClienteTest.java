package integracion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.TCliente;

public class DAOClienteTest {

    private DAOCliente dao;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
    }

    @Before
    public void setUp() {
        dao = DAOFactory.getInstance().createCliente();
        idCreado = 0;
    }

    @After
    public void tearDown() {
        if (idCreado > 0) {
            dao.delete(idCreado);
        }
    }

    @Test
    public void createExito() {
        TCliente tCliente = new TCliente(-1, "Juan", "Garcia", "TEST_NIF001", "j@test.com", "600000001");
        idCreado = dao.create(tCliente);
        assertTrue(idCreado > 0);
    }

    @Test
    public void readExito() {
        TCliente tCliente = new TCliente(-1, "Ana", "Lopez", "TEST_NIF002", "a@test.com", "600000002");
        idCreado = dao.create(tCliente);
        TCliente leido = dao.read(idCreado);
        assertNotNull(leido);
        TCliente esperado = new TCliente(idCreado, "Ana", "Lopez", "TEST_NIF002", "a@test.com", "600000002");
        assertEquals(esperado.toString(), leido.toString());
    }

    @Test
    public void readNoExiste() {
        TCliente leido = dao.read(-999);
        assertNull(leido);
    }

    @Test
    public void updateExito() {
        idCreado = dao.create(new TCliente(-1, "Pedro", "Ruiz", "TEST_NIF003", "p@test.com", "600000003"));
        TCliente modificado = new TCliente(idCreado, "Pedro", "Ruiz Nuevo", "TEST_NIF003", "p2@test.com", "600000099");
        int resultado = dao.update(modificado);
        assertEquals(idCreado, resultado);
        assertEquals(modificado.toString(), dao.read(idCreado).toString());
    }

    @Test
    public void updateNoExiste() {
        int resultado = dao.update(new TCliente(-999, "X", "X", "TEST_NIF_X", "", ""));
        assertEquals(0, resultado);
    }

    @Test
    public void deleteExito() {
        idCreado = dao.create(new TCliente(-1, "Maria", "Sanchez", "TEST_NIF004", "m@test.com", "600000004"));
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
    public void readByNIFExito() {
        idCreado = dao.create(new TCliente(-1, "Luis", "Moreno", "TEST_NIF005", "l@test.com", "600000005"));
        TCliente leido = dao.readByNIF("TEST_NIF005");
        assertNotNull(leido);
        assertEquals("TEST_NIF005", leido.getNIF());
    }

    @Test
    public void readByNIFNoExiste() {
        TCliente leido = dao.readByNIF("TEST_NIF_NOEXISTE99999");
        assertNull(leido);
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(dao.readAll());
    }
}
