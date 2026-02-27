package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entidad.Paciente;
import conexion.ConexionBD;

public class PacienteDAO {
	
//METODO PARA LISTAR PACIENTE
	public List<Paciente> listarActivos() {

	    List<Paciente> lista = new ArrayList<>();
	    String sql = "SELECT * FROM paciente WHERE estado_paciente = 1";

	    try (Connection con = ConexionBD.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

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
	
	
    ////METODO PARA AGREGAR un nuevo PACIENTE
    public boolean insertarPaciente(Paciente p) {

        String sql = "INSERT INTO paciente " +
                "(nombres_paciente, apellidos_paciente, edad_paciente, dni_paciente, correo_paciente, celular_paciente, estado_paciente) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombres());
            ps.setString(2, p.getApellidos());
            ps.setInt(3, p.getEdad());
            ps.setString(4, p.getDni());
            ps.setString(5, p.getCorreo());
            ps.setString(6, p.getCelular());
            ps.setInt(7, p.getEstado()); 

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    //METODO PARA ACTUALIZAR PACIENTE EN EL BOTON GUARDAR
    public boolean actualizarPaciente(Paciente p) {

    	String sql = "UPDATE paciente SET " +
                "nombres_paciente=?, apellidos_paciente=?, dni_paciente=?, edad_paciente=?, " +
                "celular_paciente=?, correo_paciente=? " +
                "WHERE codPaciente=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombres());
            ps.setString(2, p.getApellidos());
            ps.setString(3, p.getDni());
            ps.setInt(4, p.getEdad());
            ps.setString(5, p.getCelular());
            ps.setString(6, p.getCorreo());
            ps.setInt(7, p.getCodPaciente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }
    //METODO PARA BUSCAR POR DNI EN EL BOTON BUSCAR
    public Paciente buscarPorDni(String dni) {
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
        }

        return p;
        
    }
    //METODO PARA BUSCAR POR APELLIDO EN EL BOTON BUSCAR
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
    //METODO QUE BUSCA POR CODIGO EN LA LISTA PARA RELLENAR LOS CAMPOS EN EL BOTON MODIFICAR
    public Paciente buscarPorCodigo(int codigo) {
    	
    	String sql = "SELECT * FROM paciente WHERE codPaciente = ?";
    	Paciente p = null;
    	
    	try (Connection con = ConexionBD.conectar();
    		 PreparedStatement ps = con.prepareStatement(sql)){
    		
    		ps.setInt(1, codigo);
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
        }

        return p;
    		
    		
    	}
    //METODO PARA EL BOTON ELIMINAR
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
    
    //METODO PARA VALIDAR QUE NO HAYA DNI DUPLICADO EN NUEVO
    
    public boolean existeDni(String dni, int codigoActual) {

        String sql = "SELECT COUNT(*) FROM paciente WHERE dni_paciente = ? AND codPaciente <> ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.setInt(2, codigoActual);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
  //METODO PARA VALIDAR QUE NO HAYA DNI DUPLICADO EN ACTUALIZAR
    
    public boolean existeDniNuevo(String dni) {

        String sql = "SELECT COUNT(*) FROM paciente WHERE dni_paciente = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // true si ya existe
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    //OBTENER COD POR NOMBRE PARA EL COMBOBOX
    public Integer obtenerCodPorNombreCompleto(String nombreCompleto) {

        String sql = "SELECT codPaciente FROM paciente " +
                     "WHERE (nombres_paciente + ' ' + apellidos_paciente) = ? " +
                     "AND estado_paciente = 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreCompleto);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("codPaciente");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    
    

}