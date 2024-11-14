package PF_CesarCM_BryanCE.src.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PF_CesarCM_BryanCE.src.Modelo.InsertReservaDAO;


public class FrmInsertReserva extends JFrame {
    public FrmInsertReserva(){
        //*Configuración de la ventana */
        JFrame Ventana = new JFrame("Reservas");
        Ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Ventana.setSize(1190,720);
        Ventana.setLocationRelativeTo(null);
        Ventana.setResizable(false);

        //* Configuración del fondo */
        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());
        JPanel cuadro = new JPanel ();
        cuadro.setBackground(new Color(245, 225, 206,180));
        

        //*Mensajes */
        JLabel Bienvenida = new JLabel("Ingrese la información de la reserva");
        Bienvenida.setFont(new Font("Arial",Font.BOLD,20));
        JLabel NumMesa = new JLabel("Numero de Mesa:");
        JLabel Cedulalbl = new JLabel ("Ingrese la cédula del cliente que reserva:");
        JLabel IDRestaurante = new JLabel("ID del Restaurante:");
        JLabel Fecha = new JLabel("Ingrese la fecha de reserva:");
        JLabel Descripcion = new JLabel("Breve descripción de la reserva:");

        //* Campos */
        JTextField FechaField = new JTextField("YYYY-MM-DD HH:MM:SS");
        FechaField.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                FechaField.setText("");
            }
        });
        JTextField IDRestauranteField = new JTextField();
        JTextField NumMesaField = new JTextField();
        JTextField CedulaField = new JTextField();
        JTextField DescripcionField= new JTextField();

        


        //*Botones */
        JButton Insertbtn = crearBoton("Guardar Mesa", 620, 580);
        JButton Salirbtn = crearBoton("Atrás", 430, 580);
        
        Insertbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NumeroMesa = NumMesaField.getText();
                String Cedula = CedulaField.getText();
                String Fecha = FechaField.getText();
                String idRestaurante = IDRestauranteField.getText();
                String Descripcion = DescripcionField.getText();
        
                InsertReservaDAO.insertarReserva(NumeroMesa,Cedula,idRestaurante,Fecha,Descripcion);
            }
        });
        Salirbtn.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                new FrmMenuPrincipal();
                Ventana.dispose();
            }
        });

        //*Ubicaciones */
        cuadro.setBounds(340,40,500,600);
        Bienvenida.setBounds(420,70,350,80);
  
        Cedulalbl.setBounds(390,160,350,40);
        CedulaField.setBounds(390,200,400,40);
        Descripcion.setBounds(390,480,300,40);
        DescripcionField.setBounds(390,520,400,40);
        NumMesa.setBounds(390,320,150,40);
        NumMesaField.setBounds(390,360,400,40); 
        Fecha.setBounds(390,240,200,40);
        FechaField.setBounds(390,280,400,40);
        IDRestaurante.setBounds(390,400,150,40);
        IDRestauranteField.setBounds(390,440,400,40);
        
        
        
        //*Agregar Elementos */
        Ventana.add(CedulaField);
        Ventana.add(FechaField);
        Ventana.add(Cedulalbl);
        Ventana.add(Fecha);
        Ventana.add(Descripcion);
        Ventana.add(IDRestauranteField);
        Ventana.add(DescripcionField);
        Ventana.add(Bienvenida);
        Ventana.add(NumMesa);
        Ventana.add(IDRestaurante);
        Ventana.add(NumMesaField);
        Ventana.add(IDRestauranteField);
        Ventana.add(Insertbtn);
        Ventana.add(Salirbtn);
        Ventana.add(cuadro);
        Ventana.add(Imagen);
        Ventana.setVisible(true);
    }//Fin del constructor 


    // Método para crear botones uniformes
    private JButton crearBoton(String texto, int x, int y) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(x, y, 130, 30);
        boton.setBackground(new Color(186, 140, 99));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(186, 140, 99), 1, true));
        boton.setContentAreaFilled(true);
        boton.setOpaque(true);
        return boton;
    }

}//Fin de la clase

