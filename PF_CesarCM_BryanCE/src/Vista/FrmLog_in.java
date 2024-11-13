package PF_CesarCM_BryanCE.src.Vista;
import javax.swing.*;

import PF_CesarCM_BryanCE.src.Modelo.conexion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmLog_in extends JFrame {
    public FrmLog_in (){

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
        ImageIcon Acceder = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\icons8-usuario-hombre-verificado-50.png");
        ImageIcon LogOut= new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\icons8-cambiar-usuario-masculino-50.png");
        JLabel Restaurante = new JLabel(RestauranteImg);
        JLabel Restaurante2 = new JLabel(RestauranteImg2);
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());
        
        
          //* Panel principal con efecto de transparencia */
        JPanel cuadro = new JPanel();
          cuadro.setBackground(new Color(245, 225, 206, 180)); // Transparencia suave
        cuadro.setBounds(340, 80, 500, 500);
        cuadro.setLayout(null);
        

        //*Mensajes */
        JLabel Bienvenida = new JLabel("Bienvenido al sistema de gestión");
        JLabel Bienvenida2 = new JLabel("De Reservas");
        Bienvenida.setFont(new Font("Arial",Font.BOLD,20));
        Bienvenida2.setFont(new Font("Arial",Font.BOLD,20));
        JLabel user = new JLabel("Usuario:");
        JLabel contraseña = new JLabel("Contraseña:");

        //* Campos */
        JPasswordField contraseñaField = new JPasswordField();
        JTextField usuarioField = new JTextField();
        

        //*Botones */
        JButton Ingresarbtn = crearBoton("Iniciar sesión", 613, 500, Acceder);

        JButton Salirbtn = crearBoton("Salir", 400, 500, LogOut);



        
        

        //*Ubicaciones */
        cuadro.setBounds(340,80,500,500);
        Bienvenida.setBounds(440,90,350,80);
        Bienvenida2.setBounds(530,130,300,100);
        Ingresarbtn.setBounds(580,500,198,50);
        Salirbtn.setBounds(400,500,130,50);
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
        


        Ingresarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String contraseña = new String(contraseñaField.getPassword());
        
                boolean autenticado = conexion.autenticacion(usuario, contraseña);
        
                if (autenticado) {
                    // abrir la ventana del menú principal
                    FrmMenuPrincipal menu = new FrmMenuPrincipal(); 
                    menu.setVisible(true);
                    Ventana.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            }
        });
        
        Salirbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }//Fin del constructor


    private JButton crearBoton(String texto, int x, int y, Icon Icono) {
        JButton boton = new JButton(texto,Icono);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(x, y, 300, 50);
        
        // Colores amigables y neutros
        boton.setBackground(new Color(208, 199, 197)); 
        boton.setForeground(new Color(63, 40, 31));    
        
        // Borde redondeado con color tierra
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Bordes redondeados visualmente
        boton.setContentAreaFilled(true);
        
        return boton;
}
    }//Fin de la clase
