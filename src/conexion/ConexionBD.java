package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
        "jdbc:sqlserver://26.110.90.173:1433;"
      + "databaseName=BDHospitalCibertec;"
      + "encrypt=true;"
      + "trustServerCertificate=true;"
      + "loginTimeout=5;";

    private static final String USER = "sa";
    private static final String PASSWORD = "Jdhhfj_12";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(">>> Conectado a BDHospitalCibertec");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}