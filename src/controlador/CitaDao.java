package controlador;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import entidad.Cita;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
	
	// AGREGAR NUEVAS CITAS
	public Integer insertarYRetornarNumCita(Cita c) {

        String sql = "INSERT INTO cita (codPaciente, codMedico, codConsultorio, motivo_cita, fecha_cita, hora_cita, estado_cita) "
                   + "OUTPUT INSERTED.numCita "
                   + "VALUES (?, ?, ?, ?, ?, ?, 1)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getCodPaciente());
            ps.setInt(2, c.getCodMedico());
            ps.setInt(3, c.getCodConsultorio());
            ps.setString(4, c.getMotivo());

            // IMPORTANTE: formato correcto
            ps.setDate(5, java.sql.Date.valueOf(c.getFecha())); // yyyy-MM-dd
            ps.setTime(6, java.sql.Time.valueOf(c.getHora()));  // HH:mm:ss

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // numCita generado
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // si falló
    }

    // LISTAR PARA JTABLE DE REGISTRO Y DE MANTENIMIENTO CITAS
    public List<Object[]> listarActivasParaTabla() {
        List<Object[]> rows = new ArrayList();

        String sql = "SELECT c.numCita, " +
                "CONCAT(p.nombres_paciente,' ',p.apellidos_paciente) AS paciente, " +
                "CONCAT(m.nombres_medico,' ',m.apellidos_medico) AS medico, " +
                "co.nombre_consultorio AS consultorio, " +
                "c.fecha_cita, c.hora_cita, c.motivo_cita " +
                "FROM cita c " +
                "JOIN paciente p ON p.codPaciente = c.codPaciente " +
                "JOIN medico m ON m.codMedico = c.codMedico " +
                "JOIN consultorio co ON co.codConsultorio = c.codConsultorio " +
                "WHERE c.estado_cita = 1 " +
                "ORDER BY c.fecha_cita DESC, c.hora_cita DESC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                rows.add(new Object[]{
                        rs.getInt("numCita"),
                        rs.getString("paciente"),
                        rs.getString("medico"),
                        rs.getString("consultorio"),
                        rs.getDate("fecha_cita").toString(),
                        rs.getTime("hora_cita").toString(),
                        rs.getString("motivo_cita")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    // CANCELAR CITAS (BIT A 0)
    public boolean cancelar(int numCita) {
        String sql = "UPDATE cita SET estado_cita = 0 WHERE numCita = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numCita);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	}
