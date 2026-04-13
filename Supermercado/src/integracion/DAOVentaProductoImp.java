package integracion;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import negocio.TVentaProducto;

public class DAOVentaProductoImp implements DAOVentaProducto {

    private static final String DB_URL = "jdbc:sqlite:supermercado_bd.db";

    @Override
    public int create(TVentaProducto tVentaProducto) {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO VentaProducto (idVenta, idProducto, cantidad) VALUES (?, ?, ?)");
            ps.setInt(1, tVentaProducto.getIdVenta());
            ps.setInt(2, tVentaProducto.getIdProducto());
            ps.setInt(3, tVentaProducto.getCantidad());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return result;
    }

    @Override
    public Set<TVentaProducto> readByVenta(int idVenta) {
        Set<TVentaProducto> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VentaProducto WHERE idVenta = ?");
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(new TVentaProducto(rs.getInt("idVenta"), rs.getInt("idProducto"), rs.getInt("cantidad")));
            }
            rs.close(); ps.close();
        } catch (SQLException e) { e.printStackTrace(System.err); }
        return set;
    }
}
