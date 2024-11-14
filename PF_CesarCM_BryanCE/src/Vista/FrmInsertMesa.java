package PF_CesarCM_BryanCE.src.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import PF_CesarCM_BryanCE.src.Modelo.InsertMesaDAO;

public class FrmInsertMesa extends JFrame {

    public FrmInsertMesa(){
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
        JPanel cuadro = new JPanel();
        cuadro.setBackground(new Color(245, 225, 206,180));
        
        //*Mensajes */
        JLabel Bienvenida = new JLabel("Agregar nueva mesa:");
        JLabel NumeroMesa = new JLabel("Ingrese el número de la mesa:");
        Bienvenida.setFont(new Font("Arial",Font.BOLD,23));
        JLabel IDRestaurante = new JLabel("Ingrese el ID del Restaurante:");
        JLabel Ubicacion = new JLabel ("Ingrese la ubicación de la mesa:");
        JLabel Tamaño = new JLabel("Ingrese el tamaño de la mesa:");

        //* Campos */
        JTextField UbicacionField = new JTextField("ubica");
        JTextField IdRest = new JTextField("ID");
        JTextField NumMesaField = new JTextField("Numero");
        JTextField TamañoField = new JTextField("A");

        //*Botones creados con el método crearBoton */
        JButton Insertbtn = crearBoton("Guardar Mesa", 620, 495);
        JButton Salirbtn = crearBoton("Atrás", 430, 495);
        

        Insertbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NumeroMesa = NumMesaField.getText();
                String IDRestaurante = IdRest.getText();
                String Ubicacion = UbicacionField.getText();
                String Tamaño = TamañoField.getText();
        
                InsertMesaDAO.insertMesa(NumeroMesa,IDRestaurante,Ubicacion,Tamaño);
            }
        });

        Salirbtn.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                new FrmMenuPrincipal();
                Ventana.dispose();
            }
        });

        //*Ubicaciones */
        cuadro.setBounds(340,40,500,500);
        Bienvenida.setBounds(480,70,350,80);
        NumeroMesa.setBounds(390,160,350,40);
        NumMesaField.setBounds(390,200,400,40);
        Ubicacion.setBounds(390,240,200,40);
        UbicacionField.setBounds(390,280,400,40);
        IDRestaurante.setBounds(390,400,150,40);
        IdRest.setBounds(390,440,400,40);
        TamañoField.setBounds(390,360,400,40);
        Tamaño.setBounds(390,320,400,40);

        //*Agregar Elementos */
        Ventana.add(TamañoField);
        Ventana.add(NumMesaField);
        Ventana.add(UbicacionField);
        Ventana.add(Ubicacion);
        Ventana.add(Tamaño);
        Ventana.add(IdRest);
        Ventana.add(Bienvenida);
        Ventana.add(NumeroMesa);
        Ventana.add(IDRestaurante);
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
}
