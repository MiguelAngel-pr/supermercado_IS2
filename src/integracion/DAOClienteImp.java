package integracion;

import java.sql.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import negocio.TCliente;

public class DAOClienteImp implements DAOCliente {

    private static final String DB_URL = "jdbc:sqlite:supermercado_bd.db";

    @Override
    public int create(TCliente tCliente) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Cliente (nombre, apellidos, nif, email, telefono) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tCliente.getNombre());
            ps.setString(2, tCliente.getApellidos());
            ps.setString(3, tCliente.getNIF());
            ps.setString(4, tCliente.getEmail());
            ps.setString(5, tCliente.getTelefono());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
            rs.close();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public TCliente read(int id) {
        TCliente tCliente = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cliente WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tCliente = new TCliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
                    rs.getString("nif"), rs.getString("email"), rs.getString("telefono"));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tCliente;
    }

    @Override
    public Set<TCliente> readAll() {
        Set<TCliente> set = new TreeSet<>(Comparator.comparingInt(TCliente::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cliente");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(new TCliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
                    rs.getString("nif"), rs.getString("email"), rs.getString("telefono")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public int update(TCliente tCliente) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE Cliente SET nombre=?, apellidos=?, nif=?, email=?, telefono=? WHERE id=?");
            ps.setString(1, tCliente.getNombre());
            ps.setString(2, tCliente.getApellidos());
            ps.setString(3, tCliente.getNIF());
            ps.setString(4, tCliente.getEmail());
            ps.setString(5, tCliente.getTelefono());
            ps.setInt(6, tCliente.getId());
            if (ps.executeUpdate() == 1) id = tCliente.getId();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Cliente WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) result = id;
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return result;
    }

    @Override
    public TCliente readByNIF(String NIF) {
        TCliente tCliente = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cliente WHERE nif = ?");
            ps.setString(1, NIF);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tCliente = new TCliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
                    rs.getString("nif"), rs.getString("email"), rs.getString("telefono"));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tCliente;
    }
}
