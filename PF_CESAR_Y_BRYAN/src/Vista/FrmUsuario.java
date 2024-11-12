package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrmUsuario extends JFrame {
    Color AzulOscuro = new Color(41, 128, 185);
    Color AzulClaro = new Color(52, 152, 219);
    
    public FrmUsuario() {
        setTitle("Bienvenido al Sistema de Empleos");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(AzulClaro);

        // Creación y estilización de componentes
        JLabel lblPregunta = new JLabel("¿Es usted Cliente o Funcionario?");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 18));
        lblPregunta.setForeground(Color.WHITE);

        JButton btnCliente = new JButton("Cliente");
        JButton btnFuncionario = new JButton("Funcionario");

        // Estilos de botones
        estilizarBoton(btnCliente);
        estilizarBoton(btnFuncionario);

        // Configuración de la posición de los elementos
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblPregunta, gbc);

        gbc.gridy = 1;
        add(btnCliente, gbc);

        gbc.gridy = 2;
        add(btnFuncionario, gbc);

        // Listeners para los botones
        btnCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FrmAutenticacion(false); // Cliente
                dispose();
            }
        });

        btnFuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // new (true); // Funcionario
                dispose();
            }
        });

        setVisible(true);
    }

    // Método para estilizar botones
    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(AzulOscuro);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(150, 40));
    }
    public static void main(String[] args) {
        new FrmUsuario();
    }
}