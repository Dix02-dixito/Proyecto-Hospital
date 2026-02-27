package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import entidad.Medico;

public class MedicoDao {
	 // LISTAR MÉDICOS ACTIVOS (para llenar el combo)
	public List<Medico> listarActivos() {
		List<Medico> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM medico WHERE estado_medico = 1";
		
		try (Connection con = ConexionBD.conectar();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Medico m = new Medico(
						rs.getInt("codMedico"),
						rs.getString("nombres_medico"),
						rs.getString("apellidos_medico"),
						rs.getString("especialidad_medico"),
						rs.getString("CMP_medico"),
						rs.getInt("estado_medico")
						);
				lista.add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	//OBTENER ID POR NOMBRE COMPLETO (si el usuario escribe y no selecciona)
	public Integer obtenerCodPorNombreCompleto(String nombreCompleto) {
		 String sql = "SELECT codMedico FROM medico " +
                 "WHERE (nombres_medico + ' ' + apellidos_medico) = ? " +
                 "AND estado_medico = 1";
		
		try (Connection con = ConexionBD.conectar();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, nombreCompleto);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) return rs.getInt("codMedico");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return  null;
	}

}
