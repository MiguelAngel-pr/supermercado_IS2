package integracion;

import java.sql.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import negocio.TMarca;

public class DAOMarcaImp implements DAOMarca {

    private static final String DB_URL = "jdbc:sqlite:supermercado_bd.db";

    @Override
    public int create(TMarca tMarca) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Marca (nombre, activo) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tMarca.getNombre());
            ps.setBoolean(2, true);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public TMarca read(int id) {
        TMarca tMarca = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Marca WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tMarca = new TMarca(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tMarca;
    }

    @Override
    public TMarca readByName(String nombre) {
        TMarca tMarca = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Marca WHERE nombre = ?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tMarca = new TMarca(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tMarca;
    }

    @Override
    public Set<TMarca> readAll() {
        Set<TMarca> set = new TreeSet<>(Comparator.comparingInt(TMarca::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Marca");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(new TMarca(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public int update(TMarca tMarca) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE Marca SET nombre=?, activo=? WHERE id=?");
            ps.setString(1, tMarca.getNombre());
            ps.setBoolean(2, tMarca.getActivo());
            ps.setInt(3, tMarca.getId());
            if (ps.executeUpdate() == 1) id = tMarca.getId();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Marca SET activo=0 WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) result = id;
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return result;
    }
}
