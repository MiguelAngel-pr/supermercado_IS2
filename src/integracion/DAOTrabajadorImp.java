package integracion;

import java.sql.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import negocio.TAdministrador;
import negocio.TEmpleado;
import negocio.TTrabajador;

public class DAOTrabajadorImp implements DAOTrabajador {

    private static final String DB_URL = "jdbc:sqlite:supermercado_bd.db";

    @Override
    public int create(TTrabajador tTrabajador) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Trabajador (nombre, apellidos, nif, jornada, isAdministrador) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tTrabajador.getNombre());
            ps.setString(2, tTrabajador.getApellidos());
            ps.setString(3, tTrabajador.getNIF());
            ps.setString(4, tTrabajador.getJornada());
            ps.setBoolean(5, tTrabajador.getIsAdministrador());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
            rs.close(); ps.close();

            if (id > 0) {
                if (tTrabajador instanceof TAdministrador) {
                    TAdministrador admin = (TAdministrador) tTrabajador;
                    PreparedStatement ps2 = conn.prepareStatement(
                        "INSERT INTO Administrador (id, salario, idsEmpleados) VALUES (?, ?, ?)");
                    ps2.setInt(1, id);
                    ps2.setDouble(2, admin.getSalario());
                    ps2.setString(3, admin.getIdsEmpleados() != null ? admin.getIdsEmpleados() : "");
                    ps2.executeUpdate(); ps2.close();
                } else if (tTrabajador instanceof TEmpleado) {
                    TEmpleado emp = (TEmpleado) tTrabajador;
                    PreparedStatement ps2 = conn.prepareStatement(
                        "INSERT INTO Empleado (id, salario, idAdmin) VALUES (?, ?, ?)");
                    ps2.setInt(1, id);
                    ps2.setDouble(2, emp.getSalario());
                    ps2.setInt(3, emp.getIdAdmin());
                    ps2.executeUpdate(); ps2.close();
                }
            }
            conn.commit();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public TTrabajador read(int id) {
        TTrabajador tTrabajador = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Trabajador WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tTrabajador = buildTrabajador(conn, rs);
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tTrabajador;
    }

    @Override
    public TTrabajador readByNIF(String NIF) {
        TTrabajador tTrabajador = null;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Trabajador WHERE nif = ?");
            ps.setString(1, NIF);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tTrabajador = buildTrabajador(conn, rs);
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return tTrabajador;
    }

    private TTrabajador buildTrabajador(Connection conn, ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String nif = rs.getString("nif");
        String jornada = rs.getString("jornada");
        boolean isAdmin = rs.getBoolean("isAdministrador");

        if (isAdmin) {
            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Administrador WHERE id = ?");
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            double salario = 0; String idsEmpleados = "";
            if (rs2.next()) { salario = rs2.getDouble("salario"); idsEmpleados = rs2.getString("idsEmpleados"); }
            rs2.close(); ps2.close();
            return new TAdministrador(id, nombre, apellidos, nif, jornada, salario, idsEmpleados);
        } else {
            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Empleado WHERE id = ?");
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            double salario = 0; int idAdmin = -1;
            if (rs2.next()) { salario = rs2.getDouble("salario"); idAdmin = rs2.getInt("idAdmin"); }
            rs2.close(); ps2.close();
            return new TEmpleado(id, nombre, apellidos, nif, jornada, salario, idAdmin);
        }
    }

    @Override
    public Set<TTrabajador> readAll() {
        Set<TTrabajador> set = new TreeSet<>(Comparator.comparingInt(TTrabajador::getId));
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Trabajador");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(buildTrabajador(conn, rs));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }

    @Override
    public int update(TTrabajador tTrabajador) {
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE Trabajador SET nombre=?, apellidos=?, nif=?, jornada=? WHERE id=?");
            ps.setString(1, tTrabajador.getNombre());
            ps.setString(2, tTrabajador.getApellidos());
            ps.setString(3, tTrabajador.getNIF());
            ps.setString(4, tTrabajador.getJornada());
            ps.setInt(5, tTrabajador.getId());
            if (ps.executeUpdate() == 1) id = tTrabajador.getId();
            ps.close();

            if (id > 0) {
                if (tTrabajador instanceof TAdministrador) {
                    TAdministrador admin = (TAdministrador) tTrabajador;
                    PreparedStatement ps2 = conn.prepareStatement(
                        "UPDATE Administrador SET salario=?, idsEmpleados=? WHERE id=?");
                    ps2.setDouble(1, admin.getSalario());
                    ps2.setString(2, admin.getIdsEmpleados() != null ? admin.getIdsEmpleados() : "");
                    ps2.setInt(3, id);
                    ps2.executeUpdate(); ps2.close();
                } else if (tTrabajador instanceof TEmpleado) {
                    TEmpleado emp = (TEmpleado) tTrabajador;
                    PreparedStatement ps2 = conn.prepareStatement(
                        "UPDATE Empleado SET salario=?, idAdmin=? WHERE id=?");
                    ps2.setDouble(1, emp.getSalario());
                    ps2.setInt(2, emp.getIdAdmin());
                    ps2.setInt(3, id);
                    ps2.executeUpdate(); ps2.close();
                }
            }
            conn.commit();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return id;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM Administrador WHERE id=?");
            ps1.setInt(1, id); ps1.executeUpdate(); ps1.close();
            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM Empleado WHERE id=?");
            ps2.setInt(1, id); ps2.executeUpdate(); ps2.close();
            PreparedStatement ps3 = conn.prepareStatement("DELETE FROM Trabajador WHERE id=?");
            ps3.setInt(1, id);
            if (ps3.executeUpdate() == 1) result = id;
            ps3.close();
            conn.commit();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return result;
    }
}
