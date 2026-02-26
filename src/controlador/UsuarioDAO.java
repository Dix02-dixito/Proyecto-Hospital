package controlador;

import java.sql.*;
import javax.swing.JOptionPane;
import conexion.ConexionBD;

public class UsuarioDAO {
	
	//METODO PARA VALIDAR EL INICIO DE SESION

    public static String validarLogin(String usuario, String contraseña) {
    	String rol = null;
    	try {
    		Connection cn = ConexionBD.conectar();
    		String sql = "SELECT rol_usuario FROM usuario " +
    		             "WHERE nombre_usuario = ?" +
    				     "AND contrasena_usuario = ?" +
    		             "AND estado_usuario = 1";
    		
    		PreparedStatement ps = cn.prepareStatement(sql);
    		ps.setString(1, usuario);
    		ps.setString(2, contraseña);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			rol = rs.getString("rol_usuario");
    			
    		}
    		cn.close();
    		
    	}catch (Exception e){
    		JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

    	}
    	return rol;
    }
}