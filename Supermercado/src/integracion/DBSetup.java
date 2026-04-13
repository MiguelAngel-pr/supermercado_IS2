package integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {

    private static final String DB_URL = "jdbc:sqlite:supermercado_bd.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Marca (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL UNIQUE, " +
                "activo BOOLEAN NOT NULL DEFAULT 1)");

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Cliente (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "apellidos TEXT NOT NULL, " +
                "nif TEXT UNIQUE NOT NULL, " +
                "email TEXT, " +
                "telefono TEXT, " +
                "activo BOOLEAN NOT NULL DEFAULT 1)");

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Trabajador (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "apellidos TEXT NOT NULL, " +
                "nif TEXT UNIQUE NOT NULL, " +
                "jornada TEXT, " +
                "isAdministrador BOOLEAN NOT NULL DEFAULT 0, " +
                "activo BOOLEAN NOT NULL DEFAULT 1)");

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Administrador (" +
                "id INTEGER PRIMARY KEY, " +
                "salario REAL NOT NULL DEFAULT 0, " +
                "idsEmpleados TEXT, " +
                "FOREIGN KEY (id) REFERENCES Trabajador(id))");

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Empleado (" +
                "id INTEGER PRIMARY KEY, " +
                "salario REAL NOT NULL DEFAULT 0, " +
                "idAdmin INTEGER, " +
                "FOREIGN KEY (id) REFERENCES Trabajador(id), " +
                "FOREIGN KEY (idAdmin) REFERENCES Administrador(id))");

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Producto (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "precio REAL NOT NULL, " +
                "fechaCaducidad TEXT, " +
                "cantidad INTEGER NOT NULL DEFAULT 0, " +
                "idMarca INTEGER NOT NULL, " +
                "activo BOOLEAN NOT NULL DEFAULT 1, " +
                "FOREIGN KEY (idMarca) REFERENCES Marca(id))");

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Venta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idEmpleado INTEGER NOT NULL, " +
                "idCliente INTEGER NOT NULL, " +
                "fecha TEXT NOT NULL, " +
                "importe REAL NOT NULL, " +
                "activo BOOLEAN NOT NULL DEFAULT 1, " +
                "FOREIGN KEY (idEmpleado) REFERENCES Trabajador(id), " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(id))");

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS VentaProducto (" +
                "idVenta INTEGER NOT NULL, " +
                "idProducto INTEGER NOT NULL, " +
                "cantidad INTEGER NOT NULL DEFAULT 1, " +
                "PRIMARY KEY (idVenta, idProducto), " +
                "FOREIGN KEY (idVenta) REFERENCES Venta(id), " +
                "FOREIGN KEY (idProducto) REFERENCES Producto(id))");

            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
