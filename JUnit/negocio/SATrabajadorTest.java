package negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.DBSetup;

public class SATrabajadorTest {

    private SATrabajador sa;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
    }

    @Before
    public void setUp() {
        sa = SAFactory.getInstance().createTrabajador();
        idCreado = 0;
    }

    @After
    public void tearDown() {
        if (idCreado > 0) {
            sa.delete(idCreado);
        }
    }

    @Test
    public void createAdminExito() {
        TAdministrador admin = new TAdministrador(-1, "Carlos", "SA", "TEST_SA_ANIF001", "Completa", 3000.0, "");
        idCreado = sa.create(admin);
        assertTrue(idCreado > 0);
    }

    @Test
    public void createNIFDuplicadoFalla() {
        idCreado = sa.create(new TAdministrador(-1, "Admin", "SA", "TEST_SA_ANIF002", "Completa", 2000.0, ""));
        int resultado = sa.create(new TAdministrador(-1, "Admin Dup", "SA", "TEST_SA_ANIF002", "Completa", 2000.0, ""));
        assertEquals(0, resultado);
    }

    @Test
    public void createEmpleadoExito() {
        int idAdmin = sa.create(new TAdministrador(-1, "JefeEmp", "SA", "TEST_SA_ANIF003", "Completa", 3000.0, ""));
        try {
            TEmpleado emp = new TEmpleado(-1, "Emp", "SA", "TEST_SA_ENIF001", "Parcial", 1500.0, idAdmin);
            int idEmp = sa.create(emp);
            assertTrue(idEmp > 0);
            sa.delete(idEmp);
        } finally {
            sa.delete(idAdmin);
        }
    }

    @Test
    public void readAdminExito() {
        idCreado = sa.create(new TAdministrador(-1, "Elena", "SA", "TEST_SA_ANIF004", "Completa", 2500.0, ""));
        TTrabajador leido = sa.read(idCreado);
        assertNotNull(leido);
        assertTrue(leido instanceof TAdministrador);
        assertEquals("Elena", leido.getNombre());
    }

    @Test
    public void readNoExiste() {
        TTrabajador leido = sa.read(-999);
        assertNull(leido);
    }

    @Test
    public void updateAdminExito() {
        idCreado = sa.create(new TAdministrador(-1, "Miguel", "SA", "TEST_SA_ANIF005", "Completa", 2000.0, ""));
        TAdministrador modificado = new TAdministrador(idCreado, "Miguel Updated", "SA", "TEST_SA_ANIF005", "Parcial", 2200.0, "");
        int resultado = sa.update(modificado);
        assertEquals(idCreado, resultado);
        assertEquals("Miguel Updated", sa.read(idCreado).getNombre());
    }

    @Test
    public void deleteAdminExito() {
        idCreado = sa.create(new TAdministrador(-1, "Borrar", "SA", "TEST_SA_ANIF006", "Completa", 1000.0, ""));
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
        idCreado = sa.create(new TAdministrador(-1, "Sofia", "SA", "TEST_SA_ANIF007", "Completa", 2800.0, ""));
        TTrabajador leido = sa.readByNIF("TEST_SA_ANIF007");
        assertNotNull(leido);
        assertEquals("TEST_SA_ANIF007", leido.getNIF());
    }

    @Test
    public void readByNIFNoExiste() {
        TTrabajador leido = sa.readByNIF("TEST_SA_NIF_NOEXISTE99999");
        assertNull(leido);
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(sa.readAll());
    }
}
