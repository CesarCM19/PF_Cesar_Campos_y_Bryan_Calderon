package PF_CesarCM_BryanCE.src.Modelo;

import java.sql.*;
import javax.swing.*;

public class InsertMesaDAO {
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


public static boolean insertMesa(String NumeroMesa,String IDRestaurante, String Ubicacion,String Tamaño) {
String sql = "{CALL InsertMesas(?, ?, ?, ?)}";
try (Connection conn = conectar();
    CallableStatement stmt = conn.prepareCall(sql)) {

    // Configurar los parámetros de entrada del procedimiento almacenado
    stmt.setString(1, NumeroMesa);
    stmt.setString(2, IDRestaurante);
    stmt.setString(3, Ubicacion);
    stmt.setString(4, Tamaño);
    
    int comprobante = stmt.executeUpdate();

    // Mostrar mensaje según el valor de comprobante
    if (comprobante>1) {
        JOptionPane.showMessageDialog(null, "Inserción exitosa de la mesa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo insertar la mesa.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

} catch (SQLException e) {
    // Manejar errores de SQL y mostrar un mensaje de error
    JOptionPane.showMessageDialog(null, "Error al insertar reserva: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    return false;
}
}
}
