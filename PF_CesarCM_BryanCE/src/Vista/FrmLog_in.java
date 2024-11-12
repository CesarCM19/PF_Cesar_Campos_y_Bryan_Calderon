package PF_CesarCM_BryanCE.src.Vista;
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
        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        ImageIcon RestauranteImg = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\icons8-restaurant-100.png");
        ImageIcon RestauranteImg2 = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\icons8-restaurant-100.png");
        JLabel Restaurante = new JLabel(RestauranteImg);
        JLabel Restaurante2 = new JLabel(RestauranteImg2);
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());
        JPanel cuadro = new JPanel ();
        cuadro.setBackground(new Color(245, 225, 206));
        

        //*Mensajes */
        JLabel Bienvenida = new JLabel("Bienvenido al sistema de gestión");
        JLabel Bienvenida2 = new JLabel("De Reservas");
        Bienvenida.setFont(new Font("Arial",Font.BOLD,20));
        Bienvenida2.setFont(new Font("Arial",Font.BOLD,20));
        JLabel user = new JLabel("Usuario:");
        JLabel contraseña = new JLabel("Contraseña:");

        //* Campos */
        JPasswordField contraseñaField = new JPasswordField();
        Contraseñatxt = String.valueOf(contraseñaField.getPassword());
        JTextField usuarioField = new JTextField();
        Usertxt = usuarioField.getText();

        //*Botones */
        JButton Ingresarbtn = new JButton("Iniciar sesión");
        Ingresarbtn.setFont(new Font("Arial",Font.BOLD,16));
        Ingresarbtn.setBorderPainted(false);
        Ingresarbtn.setContentAreaFilled(false);
        JButton Salirbtn = new JButton("Salir");
        Salirbtn.setFont(new Font("Arial",Font.BOLD,16));
        Salirbtn.setBorderPainted(false);
        Salirbtn.setContentAreaFilled(false);
        
        //*Ubicaciones */
        cuadro.setBounds(340,80,500,500);
        Bienvenida.setBounds(440,90,350,80);
        Bienvenida2.setBounds(530,130,300,100);
        Ingresarbtn.setBounds(580,500,150,50);
        Salirbtn.setBounds(430,500,150,50);
        user.setBounds(390,250,150,40);
        usuarioField.setBounds(390,300,400,40);    
        contraseña.setBounds(390,360,150,40);
        contraseñaField.setBounds(390,400,400,40);
        //Copas.setBounds(350,100,100,100);
        Restaurante.setBounds(380,150,100,100);
        Restaurante2.setBounds(700,150,100,100);
        
        
        //*Agregar Elementos */
        Ventana.add(Restaurante);
        Ventana.add(Restaurante2);
        Ventana.add(Bienvenida2);
        Ventana.add(Bienvenida);
        Ventana.add(user);
        Ventana.add(contraseña);
        Ventana.add(contraseñaField);
        Ventana.add(usuarioField);
        Ventana.add(Ingresarbtn);
        Ventana.add(Salirbtn);
        Ventana.add(cuadro);
        Ventana.add(Imagen);
        Ventana.setVisible(true);
        
        }//Fin del constructor
    }//Fin de la clase
