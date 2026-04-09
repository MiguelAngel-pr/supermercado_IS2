package integracion;

import java.sql.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import negocio.TProducto;

public class DAOProductoImp implements DAOProducto {

    private static final String DB_URL = "jdbc:sqlite:supermercado_bd.db";

    @Override
    public int create(TProducto tProducto) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Producto (nombre, precio, fechaCaducidad, cantidad, idMarca, activo) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tProducto.getNombre());
            ps.setDouble(2, tProducto.getPrecio());
            ps.setString(3, tProducto.getFechaCaducidad());
            ps.setInt(4, tProducto.getCantidad());
            ps.setInt(5, tProducto.getIdMarca());
            ps.setBoolean(6, true);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public TProducto read(int id) {
        TProducto tProducto = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Producto WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tProducto = new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
                    rs.getString("fechaCaducidad"), rs.getInt("cantidad"), rs.getInt("idMarca"), rs.getBoolean("activo"));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tProducto;
    }

    @Override
    public Set<TProducto> readAll() {
        Set<TProducto> set = new TreeSet<>(Comparator.comparingInt(TProducto::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Producto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
                    rs.getString("fechaCaducidad"), rs.getInt("cantidad"), rs.getInt("idMarca"), rs.getBoolean("activo")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public Set<TProducto> readByMarca(int idMarca) {
        Set<TProducto> set = new TreeSet<>(Comparator.comparingInt(TProducto::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Producto WHERE idMarca = ?");
            ps.setInt(1, idMarca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
                    rs.getString("fechaCaducidad"), rs.getInt("cantidad"), rs.getInt("idMarca"), rs.getBoolean("activo")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public Set<TProducto> readByVenta(int idVenta) {
        Set<TProducto> set = new TreeSet<>(Comparator.comparingInt(TProducto::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT p.* FROM Producto p JOIN VentaProducto vp ON p.id = vp.idProducto WHERE vp.idVenta = ?");
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
                    rs.getString("fechaCaducidad"), rs.getInt("cantidad"), rs.getInt("idMarca"), rs.getBoolean("activo")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public int update(TProducto tProducto) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE Producto SET nombre=?, precio=?, fechaCaducidad=?, cantidad=?, idMarca=?, activo=? WHERE id=?");
            ps.setString(1, tProducto.getNombre());
            ps.setDouble(2, tProducto.getPrecio());
            ps.setString(3, tProducto.getFechaCaducidad());
            ps.setInt(4, tProducto.getCantidad());
            ps.setInt(5, tProducto.getIdMarca());
            ps.setBoolean(6, tProducto.getActivo());
            ps.setInt(7, tProducto.getId());
            if (ps.executeUpdate() == 1) id = tProducto.getId();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Producto SET activo=0 WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) result = id;
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return result;
    }
}
