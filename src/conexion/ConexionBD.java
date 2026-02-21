package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
        "jdbc:sqlserver://proyectodixma-sql.database.windows.net:1433;"
      + "database=db-proyecto;"
      + "encrypt=true;"
      + "trustServerCertificate=false;"
      + "hostNameInCertificate=*.database.windows.net;"
      + "loginTimeout=30;";

    private static final String USER = "dixma@proyectodixma-sql";
    private static final String PASSWORD = "Jdhhfj_12"; // tu password real

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(">>> Conectado a Azure db-proyecto 🚀");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}