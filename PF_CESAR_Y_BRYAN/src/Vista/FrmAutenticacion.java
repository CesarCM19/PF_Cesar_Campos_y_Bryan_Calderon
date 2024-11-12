package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Modelo.Conexion;

public class FrmAutenticacion extends JFrame {
    Color AzulM = new Color(51, 139, 133);

    public FrmAutenticacion(boolean esFuncionario) {
        //* Configuración de ventana */
        JFrame Ventana = new JFrame("Login Colaborador");
        JPanel Panel = new JPanel();
        JPanel franja = new JPanel();
        franja.setBackground(AzulM);
        JPanel franja2 = new JPanel();
        franja2.setBackground(AzulM);

        JLabel login = new JLabel("Ingrese su usuario:");
        JLabel contraseña = new JLabel("Ingrese su Contraseña:");
        JTextField logintxt = new JTextField();
        JPasswordField contraseñatxt = new JPasswordField();
        JButton Ingresarbtn = new JButton("Ingresar");
        ImageIcon exit = new ImageIcon("ProyectoProgramado1\\src\\Images\\salir.png");
        JButton Salirbtn = new JButton("Salir", exit);
        
        // Estilos
        Ingresarbtn.setContentAreaFilled(false);
        Ingresarbtn.setBorder(null);
        Salirbtn.setContentAreaFilled(false);
        Salirbtn.setBorder(null);

        // Mensajes
        login.setFont(new Font("Arial", Font.PLAIN, 14));
        login.setForeground(Color.BLACK);
        contraseña.setFont(new Font("Arial", Font.PLAIN, 14));
        contraseña.setForeground(Color.BLACK);

        // Panel
        Panel.setBackground(Color.WHITE);
        login.setBounds(150, 100, 200, 30);
        logintxt.setBounds(150, 130, 200, 20);
        contraseña.setBounds(150, 160, 200, 30);
        contraseñatxt.setBounds(150, 190, 200, 20);
        Ingresarbtn.setBounds(250, 220, 100, 50);
        Salirbtn.setBounds(130, 220, 100, 50);
        franja.setBounds(0, 0, 60, 500);
        franja2.setBounds(425, 0, 60, 500);

        // Añadir componentes
        Ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Ventana.setSize(500, 300);
        Ventana.setLocationRelativeTo(null);
        Panel.setLayout(null);
        Panel.add(login);
        Panel.add(contraseña);
        Panel.add(logintxt);
        Panel.add(contraseñatxt);
        Panel.add(Ingresarbtn);
        Panel.add(Salirbtn);
        Panel.add(franja);
        Panel.add(franja2);
        Ventana.add(Panel);
        Ventana.setVisible(true);

        // Botón Salir
        Salirbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema de empleos de Costa Rica!");
                System.exit(0);
            }
        });

        // Botón Ingresar
        Ingresarbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = logintxt.getText();
                String contraseña = new String(contraseñatxt.getPassword());

                Conexion.conectar();
                if (Conexion.autenticacion(contraseña, usuario)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema " + usuario + "!");
                    new FrmMenuColaborador();  // Redirige al frame de colaborador
                    Ventana.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
    }
}
