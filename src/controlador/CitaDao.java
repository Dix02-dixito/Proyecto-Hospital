package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.ConexionBD;

public class CitaDao {
	
	//CANCELAR CITAS PENDIENTES
	public boolean cancelarCitasPendientesPaciente(int codPaciente) {

	    String sql = "UPDATE cita SET estado_cita = 2 " + // 2 = cancelada
	                 "WHERE codPaciente = ? " +
	                 "AND estado_cita = 1";

	    try (Connection con = ConexionBD.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, codPaciente);

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	//metodo para comprobar si el paciente tiene citas futuras
	
	public boolean pacienteTieneCitasFuturas(int codPaciente) {
		
		String sql = "SELECT COUNT(*) FROM cita " +
		             "WHERE codPaciente = ?" +
				     "AND estado_cita = 1" + //1 = pendiente
		             "AND fecha_cita >= CAST(GETDATE() AS DATE)";
		
		try (Connection con = ConexionBD.conectar();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setInt(1, codPaciente);

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            return rs.getInt(1) > 0;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return false;
		}
	}
