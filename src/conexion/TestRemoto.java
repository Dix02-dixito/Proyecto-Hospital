package conexion;

import java.sql.Connection;

public class TestRemoto {
    public static void main(String[] args) {

        try (Connection con = ConexionBD.conectar()) {

            if (con != null && !con.isClosed()) {
                System.out.println("🔥 Conexión remota OK");
            } else {
                System.out.println("❌ No se conectó");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}