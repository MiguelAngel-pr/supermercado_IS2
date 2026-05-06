package integracion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.TAdministrador;
import negocio.TEmpleado;
import negocio.TTrabajador;

public class DAOTrabajadorTest {

    private DAOTrabajador dao;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
    }

    @Before
    public void setUp() {
        dao = DAOFactory.getInstance().createTrabajador();
        idCreado = 0;
    }

    @After
    public void tearDown() {
        if (idCreado > 0) {
            dao.delete(idCreado);
        }
    }

    @Test
    public void createAdminExito() {
        TAdministrador admin = new TAdministrador(-1, "Carlos", "Perez", "TEST_NIF_ADM01", "Completa", 3000.0, "");
        idCreado = dao.create(admin);
        assertTrue(idCreado > 0);
    }

    @Test
    public void readAdminExito() {
        TAdministrador admin = new TAdministrador(-1, "Elena", "Gomez", "TEST_NIF_ADM02", "Completa", 2500.0, "");
        idCreado = dao.create(admin);
        TTrabajador leido = dao.read(idCreado);
        assertNotNull(leido);
        assertTrue(leido instanceof TAdministrador);
        assertEquals(leido.getNombre(), "Elena");
        assertEquals(leido.getNIF(), "TEST_NIF_ADM02");
    }

    @Test
    public void createEmpleadoExito() {
        int idAdmin = dao.create(new TAdministrador(-1, "AdminEmp", "Test", "TEST_NIF_ADM03", "Completa", 3000.0, ""));
        try {
            TEmpleado emp = new TEmpleado(-1, "Rosa", "Diaz", "TEST_NIF_EMP01", "Parcial", 1500.0, idAdmin);
            idCreado = dao.create(emp);
            assertTrue(idCreado > 0);
            TTrabajador leido = dao.read(idCreado);
            assertNotNull(leido);
            assertTrue(leido instanceof TEmpleado);
            assertEquals("Rosa", leido.getNombre());
        } finally {
            dao.delete(idAdmin);
        }
    }

    @Test
    public void readNoExiste() {
        TTrabajador leido = dao.read(-999);
        assertNull(leido);
    }

    @Test
    public void updateAdminExito() {
        idCreado = dao.create(new TAdministrador(-1, "Miguel", "Ramos", "TEST_NIF_ADM04", "Completa", 2000.0, ""));
        TAdministrador modificado = new TAdministrador(idCreado, "Miguel", "Ramos Updated", "TEST_NIF_ADM04", "Parcial", 2200.0, "");
        int resultado = dao.update(modificado);
        assertEquals(idCreado, resultado);
        TTrabajador leido = dao.read(idCreado);
        assertEquals("Ramos Updated", leido.getApellidos());
    }

    @Test
    public void deleteExito() {
        idCreado = dao.create(new TAdministrador(-1, "Borrar", "Test", "TEST_NIF_ADM05", "Completa", 1000.0, ""));
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
        idCreado = dao.create(new TAdministrador(-1, "Sofia", "Navarro", "TEST_NIF_ADM06", "Completa", 2800.0, ""));
        TTrabajador leido = dao.readByNIF("TEST_NIF_ADM06");
        assertNotNull(leido);
        assertEquals("TEST_NIF_ADM06", leido.getNIF());
    }

    @Test
    public void readByNIFNoExiste() {
        TTrabajador leido = dao.readByNIF("TEST_NIF_NOEXISTE99999");
        assertNull(leido);
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(dao.readAll());
    }
}
