package PF_CesarCM_BryanCE.src.Modelo;

import java.sql.*;

public class conexion {
        // Datos de conexión a la base de datos

    private static final String URL = "jdbc:mysql://localhost:3306/bd_proyecto_final";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "12345";

        // Método para establecer la conexión a la base de datos

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

    // Método para autenticar al usuario llamando al procedimiento almacenado
    public static boolean autenticacion(String usuario, String contraseña) {
        String sql = "{CALL AutenticarUsuario(?, ?, ?)}";
        try (Connection conn = conectar();
            CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, usuario); // Usuario ingresado
            stmt.setString(2, contraseña); // Contraseña ingresada
            stmt.registerOutParameter(3, Types.BOOLEAN); // Valor devuelto por el procedimiento

            stmt.execute();
            return stmt.getBoolean(3); // Retorna true si autenticación es exitosa

        } catch (SQLException e) {
            System.err.println("Error en autenticación: " + e.getMessage());
            return false;
        }
    }
}
