package integracion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.TMarca;
import negocio.TProducto;

public class DAOProductoTest {

    private static int idMarcaTest;
    private DAOProducto dao;
    private int idCreado;

    @BeforeClass
    public static void initDB() {
        DBSetup.initializeDatabase();
        DAOMarca daoMarca = DAOFactory.getInstance().createMarca();
        idMarcaTest = daoMarca.create(new TMarca(-1, "TEST_MarcaProducto"));
    }

    @AfterClass
    public static void cleanUpMarca() {
        if (idMarcaTest > 0) {
            DAOFactory.getInstance().createMarca().delete(idMarcaTest);
        }
    }

    @Before
    public void setUp() {
        dao = DAOFactory.getInstance().createProducto();
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
        TProducto tProducto = new TProducto(-1, "TEST_Producto", 1.5, "2026-12-31", 10, idMarcaTest);
        idCreado = dao.create(tProducto);
        assertTrue(idCreado > 0);
    }

    @Test
    public void readExito() {
        TProducto tProducto = new TProducto(-1, "TEST_ProductoRead", 2.0, "2026-12-31", 5, idMarcaTest);
        idCreado = dao.create(tProducto);
        TProducto leido = dao.read(idCreado);
        assertNotNull(leido);
        TProducto esperado = new TProducto(idCreado, "TEST_ProductoRead", 2.0, "2026-12-31", 5, idMarcaTest);
        assertEquals(esperado.toString(), leido.toString());
    }

    @Test
    public void readNoExiste() {
        TProducto leido = dao.read(-999);
        assertNull(leido);
    }

    @Test
    public void updateExito() {
        idCreado = dao.create(new TProducto(-1, "TEST_ProdOriginal", 1.0, "2026-01-01", 3, idMarcaTest));
        TProducto modificado = new TProducto(idCreado, "TEST_ProdModificado", 9.99, "2027-06-30", 20, idMarcaTest);
        int resultado = dao.update(modificado);
        assertEquals(idCreado, resultado);
        assertEquals(modificado.toString(), dao.read(idCreado).toString());
    }

    @Test
    public void updateNoExiste() {
        int resultado = dao.update(new TProducto(-999, "X", 0, "", 0, idMarcaTest));
        assertEquals(0, resultado);
    }

    @Test
    public void deleteExito() {
        idCreado = dao.create(new TProducto(-1, "TEST_ProdBorrar", 0.5, "2026-01-01", 1, idMarcaTest));
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
    public void readByMarca() {
        idCreado = dao.create(new TProducto(-1, "TEST_ProdMarca", 3.0, "2026-06-01", 8, idMarcaTest));
        assertFalse(dao.readByMarca(idMarcaTest).isEmpty());
    }

    @Test
    public void readAllNoNulo() {
        assertNotNull(dao.readAll());
    }
}
