package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexion {

    
	private static final String URL =
		    "jdbc:sqlserver://localhost:1433;"
		  + "databaseName=BDHospitalCibertec;"
		  + "encrypt=true;"
		  + "trustServerCertificate=true;"
		  + "loginTimeout=5;";

    private static final String USER = "sa";
    private static final String PASSWORD = "Jdhhfj_12";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            if (conn != null && !conn.isClosed()) {
                System.out.println(" Conectado correctamente");
                System.out.println("Servidor: " + conn.getMetaData().getURL());
            }

        } catch (SQLException e) {
            System.out.println(" No se pudo conectar");
            e.printStackTrace();
        }
    }
}