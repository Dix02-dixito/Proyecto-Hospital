package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entidad.Paciente;
import conexion.ConexionBD;

public class PacienteDAO {
	

    public List<Paciente> listarPacientes() {

        List<Paciente> lista = new ArrayList<>();

        String sql = "SELECT * FROM Paciente";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente();

                p.setCodPaciente(rs.getInt("codPaciente"));
                p.setNombres(rs.getString("nombres_paciente"));
                p.setApellidos(rs.getString("apellidos_paciente"));
                p.setEdad(rs.getInt("edad_paciente"));
                p.setDni(rs.getString("dni_paciente"));
                p.setEstado(rs.getInt("estado_paciente"));
                p.setCelular(rs.getString("celular_paciente"));
                p.setCorreo(rs.getString("correo_paciente"));

                lista.add(p);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean insertarPaciente(Paciente p) {

        String sql = "INSERT INTO Paciente " +
        		"(nombres_paciente, apellidos_paciente, edad_paciente, dni_paciente, correo_paciente, celular_paciente, estado_paciente) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombres());
            ps.setString(2, p.getApellidos());
            ps.setString(3, p.getDni());
            ps.setInt(4, p.getEdad());
            ps.setString(5, p.getCelular());
            ps.setString(6, p.getCorreo());
            ps.setInt(7, p.getEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizarPaciente(Paciente p) {

        String sql = "UPDATE paciente SET " +
                     "nombres_paciente=?, apellidos_paciente=?, dni_paciente=?, edad_paciente=?, " +
                     "celular_paciente=?, correo_paciente=?, estado_paciente=? " +
                     "WHERE codPaciente=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombres());
            ps.setString(2, p.getApellidos());
            ps.setString(3, p.getDni());
            ps.setInt(4, p.getEdad());
            ps.setString(5, p.getCelular());
            ps.setString(6, p.getCorreo());
            ps.setInt(7, p.getEstado());
            ps.setInt(8, p.getCodPaciente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Paciente buscarPorDni(String dni) {
    	System.out.println("Buscando DNI: " + dni);

        Paciente p = null;

        String sql = "SELECT * FROM paciente WHERE dni_paciente = ? AND estado_paciente = 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Paciente();

                p.setCodPaciente(rs.getInt("codPaciente"));
                p.setNombres(rs.getString("nombres_paciente"));
                p.setApellidos(rs.getString("apellidos_paciente"));
                p.setEdad(rs.getInt("edad_paciente"));
                p.setDni(rs.getString("dni_paciente"));
                p.setEstado(rs.getInt("estado_paciente"));
                p.setCelular(rs.getString("celular_paciente"));
                p.setCorreo(rs.getString("correo_paciente"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Consulta ejecutada");
        }

        return p;
        
    }
    
    public ArrayList<Paciente> buscarPorApellido(String apellido) {

        ArrayList<Paciente> lista = new ArrayList<>();

        String sql = "SELECT * FROM paciente WHERE apellidos_paciente LIKE ? AND estado_paciente = 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + apellido + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();

                p.setCodPaciente(rs.getInt("codPaciente"));
                p.setNombres(rs.getString("nombres_paciente"));
                p.setApellidos(rs.getString("apellidos_paciente"));
                p.setEdad(rs.getInt("edad_paciente"));
                p.setDni(rs.getString("dni_paciente"));
                p.setEstado(rs.getInt("estado_paciente"));
                p.setCelular(rs.getString("celular_paciente"));
                p.setCorreo(rs.getString("correo_paciente"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean eliminarPaciente(int codigo) {

        String sql = "UPDATE paciente SET estado_paciente = 0 WHERE codPaciente = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}