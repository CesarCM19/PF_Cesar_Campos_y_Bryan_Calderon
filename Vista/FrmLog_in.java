package Vista;
import javax.swing.*;
import java.awt.*;

public class FrmLog_in extends JFrame {
    public FrmLog_in (){
        String Usertxt;
        String Contraseñatxt;

        //*Configuración de la ventana */
        JFrame Ventana = new JFrame("Log in");
        Ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Ventana.setSize(1190,720);
        Ventana.setLocationRelativeTo(null);
        Ventana.setResizable(false);

        //* Configuración del fondo */
        ImageIcon Back = new ImageIcon("C:\\Users\\Usuario\\Documents\\GitHub\\PF_Cesar_Campos_y_Bryan_Calderon\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());
        JPanel cuadro = new JPanel ();
        cuadro.setBackground(new Color(245, 225, 206));
        cuadro.setBounds(340,80,500,500);

        //*Mensajes */
        JLabel Bienvenida = new JLabel("Bienvenido al sistema de restaurantes");
        Bienvenida.setLocation(0,0);
        JLabel user = new JLabel("Usuario:");
        JLabel contraseña = new JLabel("Contraseña:");

        //* Campos */
        JPasswordField contraseñaField = new JPasswordField();
        Contraseñatxt = String.valueOf(contraseñaField.getPassword());
        JTextField usuarioField = new JTextField();
        Usertxt = usuarioField.getText();

        //*Botones */
        JButton Ingresarbtn = new JButton("Iniciar sesión");
        Ingresarbtn.setBounds(0,0,100,100);
        JButton Salirbtn = new JButton("Salir");
        Salirbtn.setBounds(0,0,100,100);
        //*Agregar Elementos */
        Ventana.add(Ingresarbtn);
        Ventana.add(Salirbtn);
        Ventana.add(cuadro);
        Ventana.add(Imagen);
        Ventana.setVisible(true);
        
        }//Fin del constructor
    }//Fin de la clase
