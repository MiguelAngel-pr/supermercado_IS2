package integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import negocio.TCarrito;
import negocio.TVenta;
import negocio.TCliente;

public class DAOVentaImp implements DAOVenta {

    private static final String DB_URL = "jdbc:sqlite:supermercado_bd.db";

    @Override
    public int create(TVenta tVenta, TCarrito tCarrito) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Venta (idEmpleado, idCliente, fecha, importe) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            
            int idEmpleado = tCarrito.getIdEmpleado();
            int idCliente = tCarrito.getIdCliente();
            String fecha = tVenta.getFecha();
            double importe = tVenta.getImporte();

            ps.setInt(1, idEmpleado);
            ps.setInt(2, idCliente);
            ps.setString(3, fecha);
            ps.setDouble(4, importe);
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public TVenta read(int id) {
        TVenta tVenta = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Venta WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TCliente c = new TCliente(); c.setId(rs.getInt("idCliente"));
                tVenta = new TVenta(rs.getInt("id"), rs.getString("fecha"), rs.getDouble("importe"),
                		rs.getInt("idCliente"), rs.getInt("idEmpleado"));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tVenta;
    }

    @Override
    public Set<TVenta> readAll() {
        Set<TVenta> set = new TreeSet<>(Comparator.comparingInt(TVenta::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Venta");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TCliente c = new TCliente(); c.setId(rs.getInt("idCliente"));
                set.add(new TVenta(rs.getInt("id"), rs.getString("fecha"), rs.getDouble("importe"),
                		rs.getInt("idCliente"), rs.getInt("idEmpleado")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public Set<TVenta> readByFecha(String fecha) {
        Set<TVenta> set = new TreeSet<>(Comparator.comparingInt(TVenta::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Venta WHERE fecha = ?");
            ps.setString(1, fecha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TCliente c = new TCliente(); c.setId(rs.getInt("idCliente"));
                set.add(new TVenta(rs.getInt("id"), rs.getString("fecha"), rs.getDouble("importe"),
                		rs.getInt("idCliente"), rs.getInt("idEmpleado")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public Set<TVenta> readByImporte(double importe) {
        Set<TVenta> set = new TreeSet<>(Comparator.comparingInt(TVenta::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Venta WHERE importe = ?");
            ps.setDouble(1, importe);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TCliente c = new TCliente(); c.setId(rs.getInt("idCliente"));
                set.add(new TVenta(rs.getInt("id"), rs.getString("fecha"), rs.getDouble("importe"),
                		rs.getInt("idCliente"), rs.getInt("idEmpleado")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public Set<TVenta> readByIdCliente(int idCliente) {
        Set<TVenta> set = new TreeSet<>(Comparator.comparingInt(TVenta::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Venta WHERE idCliente = ?");
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TCliente c = new TCliente(); c.setId(rs.getInt("idCliente"));
                set.add(new TVenta(rs.getInt("id"), rs.getString("fecha"), rs.getDouble("importe"),
                		rs.getInt("idCliente"), rs.getInt("idEmpleado")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public Set<TVenta> readByIdEmpleado(int idEmpleado) {
        Set<TVenta> set = new TreeSet<>(Comparator.comparingInt(TVenta::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Venta WHERE idEmpleado = ?");
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(new TVenta(rs.getInt("id"), rs.getString("fecha"), rs.getDouble("importe"),
                	rs.getInt("idCliente"), rs.getInt("idEmpleado")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public int update(TVenta tVenta) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE Venta SET idEmpleado=?, idCliente=?, fecha=?, importe=? WHERE id=?");
            
          
            int idEmpleado = tVenta.getIdEmpleado();
            int idCliente = tVenta.getIdCliente();
            String fecha = tVenta.getFecha();
            double importe = tVenta.getImporte();
            int idVenta = tVenta.getId();

            ps.setInt(1, idEmpleado);
            ps.setInt(2, idCliente);
            ps.setString(3, fecha);
            ps.setDouble(4, importe);
            ps.setInt(5, idVenta);

            if (ps.executeUpdate() == 1) id = tVenta.getId();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM VentaProducto WHERE idVenta=?");
            ps1.setInt(1, id); ps1.executeUpdate(); ps1.close();
            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM Venta WHERE id=?");
            ps2.setInt(1, id);
            if (ps2.executeUpdate() == 1) result = id;
            ps2.close();
            conn.commit();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return result;
    }
}