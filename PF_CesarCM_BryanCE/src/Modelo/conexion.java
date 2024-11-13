package PF_CesarCM_BryanCE.src.Modelo;

import java.sql.*;

public class conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_proyecto_final";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "12345";

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa a la base de datos.");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    // Método de autenticación
    public static boolean autenticacion(String usuario, String contraseña) {
        String sql = "{CALL AutenticarUsuario(?, ?, ?)}";
        try (Connection conn = conectar();
            CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            stmt.registerOutParameter(3, Types.BOOLEAN);

            stmt.execute();
            return stmt.getBoolean(3); // Retorna true si el usuario es autenticado, false si no

        } catch (SQLException e) {
            System.err.println("Error en autenticación: " + e.getMessage());
            return false;
        }
    }
}
