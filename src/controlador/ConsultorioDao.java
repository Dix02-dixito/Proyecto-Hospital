package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import entidad.Consultorio;

public class ConsultorioDao {
	// 1) LISTAR ACTIVOS (para combobox)
    public List<Consultorio> listarActivos() {
        List<Consultorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM consultorio WHERE estado_consultorio = 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Consultorio c = new Consultorio(
                        rs.getInt("codConsultorio"),
                        rs.getString("nombre_consultorio"),
                        rs.getInt("piso_consultorio"),
                        rs.getString("ubicacion_consultorio"),
                        rs.getInt("capacidad_consultorio"),
                        rs.getInt("estado_consultorio")
                );
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 2) OBTENER ID POR NOMBRE (si escriben)
    public Integer obtenerCodPorNombre(String nombre) {

        String sql = "SELECT codConsultorio FROM consultorio " +
                     "WHERE nombre_consultorio = ? " +
                     "AND estado_consultorio = 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("codConsultorio");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	

}
