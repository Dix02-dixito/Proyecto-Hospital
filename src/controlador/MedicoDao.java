package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import entidad.Medico;

public class MedicoDao {

    // ESTE METODO SIRVE PARA LISTAR LOS MEDICOS ACTIVOS (PARA LOS COMBOS)
    public List<Medico> listarActivos() {
        List<Medico> lista = new ArrayList<>();

        String sql = "SELECT codMedico, nombres_medico, apellidos_medico, especialidad_medico, CMP_medico, telefono_medico, correo_medico, estado_medico "
                   + "FROM medico WHERE estado_medico = 1";

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

    // ESTE METODO SIRVE PARA OBTENER EL CODIGO DEL MEDICO SEGUN SU NOMBRE COMPLETO
    public Integer obtenerCodPorNombreCompleto(String nombreCompleto) {
        String sql = "SELECT codMedico FROM medico "
                   + "WHERE (nombres_medico + ' ' + apellidos_medico) = ? "
                   + "AND estado_medico = 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreCompleto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt("codMedico");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ESTE METODO SIRVE PARA INSERTAR UN NUEVO MEDICO
    public boolean insertar(String nombres, String apellidos, String cmp, String especialidad, String telefono, String correo) {
        String sql = "INSERT INTO medico (nombres_medico, apellidos_medico, CMP_medico, especialidad_medico, telefono_medico, correo_medico, estado_medico) "
                   + "VALUES (?, ?, ?, ?, ?, ?, 1)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, cmp);
            ps.setString(4, especialidad);
            ps.setString(5, telefono);
            ps.setString(6, correo);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ESTE METODO SIRVE PARA ACTUALIZAR LOS DATOS DE UN MEDICO
    public boolean actualizar(int codMedico, String nombres, String apellidos, String cmp, String especialidad, String telefono, String correo) {
        String sql = "UPDATE medico SET nombres_medico = ?, apellidos_medico = ?, CMP_medico = ?, especialidad_medico = ?, "
                   + "telefono_medico = ?, correo_medico = ? "
                   + "WHERE codMedico = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, cmp);
            ps.setString(4, especialidad);
            ps.setString(5, telefono);
            ps.setString(6, correo);
            ps.setInt(7, codMedico);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ESTE METODO SIRVE PARA INACTIVAR (ELIMINAR LOGICAMENTE) UN MEDICO
    public boolean eliminar(int codMedico) {
        String sql = "UPDATE medico SET estado_medico = 0 WHERE codMedico = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codMedico);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ESTE METODO SIRVE PARA LISTAR MEDICOS ACTIVOS EN FORMATO PARA JTable
    public List<Object[]> listarActivosParaTabla() {
        List<Object[]> lista = new ArrayList<>();

        String sql = "SELECT codMedico, nombres_medico, apellidos_medico, CMP_medico, especialidad_medico, telefono_medico, correo_medico "
                   + "FROM medico WHERE estado_medico = 1 "
                   + "ORDER BY apellidos_medico ASC, nombres_medico ASC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[] {
                        rs.getInt("codMedico"),
                        rs.getString("nombres_medico"),
                        rs.getString("apellidos_medico"),
                        rs.getString("CMP_medico"),
                        rs.getString("especialidad_medico"),
                        rs.getString("telefono_medico"),
                        rs.getString("correo_medico")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ESTE METODO SIRVE PARA BUSCAR MEDICOS POR CMP
    public List<Object[]> buscarPorCMPParaTabla(String cmp) {
        List<Object[]> lista = new ArrayList<>();

        String sql = "SELECT codMedico, nombres_medico, apellidos_medico, CMP_medico, especialidad_medico, telefono_medico, correo_medico "
                   + "FROM medico "
                   + "WHERE estado_medico = 1 AND CMP_medico LIKE ? "
                   + "ORDER BY apellidos_medico ASC, nombres_medico ASC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + cmp + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[] {
                            rs.getInt("codMedico"),
                            rs.getString("nombres_medico"),
                            rs.getString("apellidos_medico"),
                            rs.getString("CMP_medico"),
                            rs.getString("especialidad_medico"),
                            rs.getString("telefono_medico"),
                            rs.getString("correo_medico")
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ESTE METODO SIRVE PARA BUSCAR MEDICOS POR APELLIDOS
    public List<Object[]> buscarPorApellidosParaTabla(String apellidos) {
        List<Object[]> lista = new ArrayList<>();

        String sql = "SELECT codMedico, nombres_medico, apellidos_medico, CMP_medico, especialidad_medico, telefono_medico, correo_medico "
                   + "FROM medico "
                   + "WHERE estado_medico = 1 AND apellidos_medico LIKE ? "
                   + "ORDER BY apellidos_medico ASC, nombres_medico ASC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + apellidos + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[] {
                            rs.getInt("codMedico"),
                            rs.getString("nombres_medico"),
                            rs.getString("apellidos_medico"),
                            rs.getString("CMP_medico"),
                            rs.getString("especialidad_medico"),
                            rs.getString("telefono_medico"),
                            rs.getString("correo_medico")
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}