package PF_CesarCM_BryanCE.src.Modelo;

import java.sql.*;
import javax.swing.*;

public class conexion {
    public static Connection conectar() {
            String URL = "jdbc:mysql://localhost:3306/bd_proyecto_final"; 
            String usuario = "root"; 
            String contraseña = "12345"; 
    
            try {
                Connection conexion = DriverManager.getConnection(URL, usuario, contraseña);
                return conexion;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
                //* Método de autenticación
        public static boolean autenticacion (String contraseña, String login){
            String sql = "SELECT * FROM autenticacion WHERE login = ? AND contraseña = ?;";
                Connection conexion = conectar();
                if (conexion==null){
                    JOptionPane.showMessageDialog(null,"No se ha podido realizar la conexión con la base de datos!");
                } 
                try(PreparedStatement Sentencia = conexion.prepareStatement(sql)){
                    Sentencia.setString(1, login);
                    Sentencia.setString(2,contraseña);
                    ResultSet rs = Sentencia.executeQuery();
                    return rs.next(); //*"Lee el siguiente" y retorna true cuando hay un resultado
                }catch(SQLException e){
                    e.printStackTrace();
                    return false;
                }
            
        };
}
