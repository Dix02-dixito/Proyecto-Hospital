package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import entidad.Cita;

public class CitaDao {

    // ===================== ESTADO A TEXTO =====================
    // 0 = cancelada, 1 = pendiente, 2 = atendida
    private String estadoTexto(int estado) {
        if (estado == 0) return "Cancelada";
        if (estado == 1) return "Pendiente";
        if (estado == 2) return "Atendida";
        return "Desconocido";
    }

    // ===================== CANCELAR CITAS PENDIENTES =====================
    public boolean cancelarCitasPendientesPaciente(int codPaciente) {

        String sql = "UPDATE cita SET estado_cita = 0 " + // 0 = cancelada
                     "WHERE codPaciente = ? " +
                     "AND estado_cita = 1"; // 1 = pendiente

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codPaciente);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ===================== COMPROBAR SI TIENE CITAS FUTURAS =====================
    public boolean pacienteTieneCitasFuturas(int codPaciente) {

        // OJO: te faltaban espacios en el SQL original, por eso es mejor así:
        String sql = "SELECT COUNT(*) FROM cita " +
                     "WHERE codPaciente = ? " +
                     "AND estado_cita = 1 " + // pendiente
                     "AND fecha_cita >= CAST(GETDATE() AS DATE)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codPaciente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ===================== INSERTAR CITA Y RETORNAR NUMCITA =====================
    public Integer insertarYRetornarNumCita(Cita c) {

        String sql = "INSERT INTO cita (codPaciente, codMedico, codConsultorio, motivo_cita, fecha_cita, hora_cita, estado_cita) "
                   + "OUTPUT INSERTED.numCita "
                   + "VALUES (?, ?, ?, ?, ?, ?, 1)"; // 1 = pendiente

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getCodPaciente());
            ps.setInt(2, c.getCodMedico());
            ps.setInt(3, c.getCodConsultorio());
            ps.setString(4, c.getMotivo());

            ps.setDate(5, java.sql.Date.valueOf(c.getFecha())); // yyyy-MM-dd
            ps.setTime(6, java.sql.Time.valueOf(c.getHora()));  // HH:mm:ss

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ===================== LISTAR ACTIVAS PARA TABLA =====================
    public List<Object[]> listarActivasParaTabla() {

        List<Object[]> rows = new ArrayList<>();

        String sql = "SELECT c.numCita, " +
                "CONCAT(p.nombres_paciente,' ',p.apellidos_paciente) AS paciente, " +
                "CONCAT(m.nombres_medico,' ',m.apellidos_medico) AS medico, " +
                "co.nombre_consultorio AS consultorio, " +
                "c.fecha_cita, c.hora_cita, c.motivo_cita, c.estado_cita " +
                "FROM cita c " +
                "JOIN paciente p ON p.codPaciente = c.codPaciente " +
                "JOIN medico m ON m.codMedico = c.codMedico " +
                "JOIN consultorio co ON co.codConsultorio = c.codConsultorio " +
                "WHERE c.estado_cita = 1 " + // solo pendientes
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
                        rs.getString("motivo_cita"),
                        estadoTexto(rs.getInt("estado_cita"))
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }

    // ===================== CANCELAR CITA POR NUMCITA =====================
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

    // ===================== CONSULTAR CITAS (FILTROS COMBINABLES) =====================
    public List<Object[]> consultarParaTabla(Integer codPaciente, Integer codMedico, Integer codConsultorio, String fecha) {

        List<Object[]> rows = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.numCita, ")
           .append("CONCAT(p.nombres_paciente,' ',p.apellidos_paciente) AS paciente, ")
           .append("CONCAT(m.nombres_medico,' ',m.apellidos_medico) AS medico, ")
           .append("co.nombre_consultorio AS consultorio, ")
           .append("c.fecha_cita, c.hora_cita, c.motivo_cita, c.estado_cita ")
           .append("FROM cita c ")
           .append("JOIN paciente p ON p.codPaciente = c.codPaciente ")
           .append("JOIN medico m ON m.codMedico = c.codMedico ")
           .append("JOIN consultorio co ON co.codConsultorio = c.codConsultorio ")
           .append("WHERE 1=1 ");

        if (codPaciente != null) sql.append("AND c.codPaciente = ? ");
        if (codMedico != null) sql.append("AND c.codMedico = ? ");
        if (codConsultorio != null) sql.append("AND c.codConsultorio = ? ");
        if (fecha != null && !fecha.trim().isEmpty()) sql.append("AND c.fecha_cita = ? ");

        sql.append("ORDER BY c.fecha_cita ASC, c.hora_cita ASC");

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int i = 1;

            if (codPaciente != null) ps.setInt(i++, codPaciente);
            if (codMedico != null) ps.setInt(i++, codMedico);
            if (codConsultorio != null) ps.setInt(i++, codConsultorio);
            if (fecha != null && !fecha.trim().isEmpty()) ps.setDate(i++, java.sql.Date.valueOf(fecha.trim()));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new Object[]{
                            rs.getInt("numCita"),
                            rs.getString("paciente"),
                            rs.getString("medico"),
                            rs.getString("consultorio"),
                            rs.getDate("fecha_cita").toString(),
                            rs.getTime("hora_cita").toString(),
                            rs.getString("motivo_cita"),
                            estadoTexto(rs.getInt("estado_cita")) // ✅ texto
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }

    // ===================== REPORTES =====================

    // Reporte general (por paciente / por medico+fecha / por consultorio+fecha / por fecha)
    public List<Object[]> reporteGeneral(Integer codPaciente, Integer codMedico, Integer codConsultorio, String fecha) {

        List<Object[]> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
           .append("CONCAT(p.nombres_paciente,' ',p.apellidos_paciente) AS paciente, ")
           .append("CONCAT(m.nombres_medico,' ',m.apellidos_medico) AS medico, ")
           .append("co.nombre_consultorio AS consultorio, ")
           .append("c.fecha_cita, c.hora_cita, c.estado_cita, c.motivo_cita ")
           .append("FROM cita c ")
           .append("JOIN paciente p ON p.codPaciente = c.codPaciente ")
           .append("JOIN medico m ON m.codMedico = c.codMedico ")
           .append("JOIN consultorio co ON co.codConsultorio = c.codConsultorio ")
           .append("WHERE 1=1 ");

        if (codPaciente != null) sql.append("AND c.codPaciente = ? ");
        if (codMedico != null) sql.append("AND c.codMedico = ? ");
        if (codConsultorio != null) sql.append("AND c.codConsultorio = ? ");
        if (fecha != null) sql.append("AND c.fecha_cita = ? ");

        sql.append("ORDER BY c.fecha_cita ASC, c.hora_cita ASC");

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int idx = 1;
            if (codPaciente != null) ps.setInt(idx++, codPaciente);
            if (codMedico != null) ps.setInt(idx++, codMedico);
            if (codConsultorio != null) ps.setInt(idx++, codConsultorio);
            if (fecha != null) ps.setDate(idx++, java.sql.Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[]{
                            rs.getString("paciente"),
                            rs.getString("medico"),
                            rs.getString("consultorio"),
                            rs.getDate("fecha_cita").toString(),
                            rs.getTime("hora_cita").toString(),
                            estadoTexto(rs.getInt("estado_cita")),
                            rs.getString("motivo_cita")
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Reporte: citas pendientes
    public List<Object[]> reportePacientesConPendientes() {

        List<Object[]> lista = new ArrayList<>();

        String sql = "SELECT "
                + "CONCAT(p.nombres_paciente,' ',p.apellidos_paciente) AS paciente, "
                + "CONCAT(m.nombres_medico,' ',m.apellidos_medico) AS medico, "
                + "co.nombre_consultorio AS consultorio, "
                + "c.fecha_cita, c.hora_cita, c.estado_cita, c.motivo_cita "
                + "FROM cita c "
                + "JOIN paciente p ON p.codPaciente = c.codPaciente "
                + "JOIN medico m ON m.codMedico = c.codMedico "
                + "JOIN consultorio co ON co.codConsultorio = c.codConsultorio "
                + "WHERE c.estado_cita = 1 "
                + "ORDER BY c.fecha_cita ASC, c.hora_cita ASC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getString("paciente"),
                        rs.getString("medico"),
                        rs.getString("consultorio"),
                        rs.getDate("fecha_cita").toString(),
                        rs.getTime("hora_cita").toString(),
                        estadoTexto(rs.getInt("estado_cita")),
                        rs.getString("motivo_cita")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Reporte: agenda del día por fecha
    public List<Object[]> reporteAgendaDelDia(String fecha) {
        return reporteGeneral(null, null, null, fecha);
    }
}