package integracion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.TMarca;

public class DAOMarcaTest {

    private DAOMarca dao;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
    }

    @Before
    public void setUp() {
        dao = DAOFactory.getInstance().createMarca();
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
        TMarca tMarca = new TMarca(-1, "TEST_Marca");
        idCreado = dao.create(tMarca);
        assertTrue(idCreado > 0);
    }

    @Test
    public void readExito() {
        TMarca tMarca = new TMarca(-1, "TEST_MarcaRead");
        idCreado = dao.create(tMarca);
        TMarca leida = dao.read(idCreado);
        assertNotNull(leida);
        TMarca esperada = new TMarca(idCreado, "TEST_MarcaRead");
        assertEquals(esperada.toString(), leida.toString());
    }

    @Test
    public void readNoExiste() {
        TMarca leida = dao.read(-999);
        assertNull(leida);
    }

    @Test
    public void updateExito() {
        idCreado = dao.create(new TMarca(-1, "TEST_MarcaOriginal"));
        TMarca modificada = new TMarca(idCreado, "TEST_MarcaModificada");
        int resultado = dao.update(modificada);
        assertEquals(idCreado, resultado);
        assertEquals(modificada.toString(), dao.read(idCreado).toString());
    }

    @Test
    public void updateNoExiste() {
        int resultado = dao.update(new TMarca(-999, "X"));
        assertEquals(0, resultado);
    }

    @Test
    public void deleteExito() {
        idCreado = dao.create(new TMarca(-1, "TEST_MarcaBorrar"));
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
    public void readByNameExito() {
        idCreado = dao.create(new TMarca(-1, "TEST_MarcaByName"));
        TMarca leida = dao.readByName("TEST_MarcaByName");
        assertNotNull(leida);
        assertEquals("TEST_MarcaByName", leida.getNombre());
    }

    @Test
    public void readByNameNoExiste() {
        TMarca leida = dao.readByName("TEST_MarcaQueNoExiste99999");
        assertNull(leida);
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(dao.readAll());
    }
}
