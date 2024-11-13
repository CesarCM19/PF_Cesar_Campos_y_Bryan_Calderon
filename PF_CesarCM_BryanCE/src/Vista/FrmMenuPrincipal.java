package PF_CesarCM_BryanCE.src.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMenuPrincipal extends JFrame {
    public FrmMenuPrincipal() {
        //* Configuración de la ventana */
        setTitle("Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null);
        setResizable(false);
    
        //* Crear un contenedor de capas */
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, getWidth(), getHeight());
        setContentPane(layeredPane);
    
        //* Configuración del fondo */
        ImageIcon backImage = new ImageIcon("PF_CesarCM_BryanCE/src/Images/RestauranteFondo.png");
        JLabel backgroundLabel = new JLabel(backImage);
    
        // Ajustar la imagen al tamaño de la ventana
        backgroundLabel.setLayout(new BorderLayout());  // Aseguramos que ocupe todo el espacio
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());  // Asigna el tamaño de la ventana al JLabel
    
        // Ajusta la imagen para que cubra todo el espacio
        ImageIcon scaledImage = new ImageIcon(backImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(scaledImage);
    
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER); // Agrega la imagen de fondo en la capa inferior
    
        //* Panel principal con efecto de transparencia */
        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBounds(100, 50, 990, 600);
    
        //* Panel izquierdo - Reservas */
        JPanel panelReservas = new JPanel(null);
        panelReservas.setBackground(new Color(245, 225, 206, 80)); // Color semitransparente 
        panelReservas.setBounds(0, 0, 495, 600);
    
        //* Panel derecho - Mesas */
        JPanel panelMesas = new JPanel(null);
        panelMesas.setBackground(new Color(245, 225, 206, 80)); // Color semitransparente
        panelMesas.setBounds(495, 0, 495, 600);
    
        //* Títulos para cada sección */
        JLabel lblReservas = new JLabel("Gestión de Reservas");
        lblReservas.setFont(new Font("Arial", Font.BOLD, 20));
        lblReservas.setBounds(150, 20, 200, 40);
    
        JLabel lblMesas = new JLabel("Gestión de Mesas");
        lblMesas.setFont(new Font("Arial", Font.BOLD, 20));
        lblMesas.setBounds(150, 20, 200, 40);
    
        //* Línea divisoria debajo de los títulos */
        JSeparator separadorReservas = new JSeparator();
        separadorReservas.setBounds(50, 70, 400, 40);
        separadorReservas.setBackground(Color.white); 
    
        JSeparator separadorMesas = new JSeparator();
        separadorMesas.setBounds(50, 70, 400, 40);
        separadorMesas.setBackground(Color.white);
    
        //* Botones para operaciones de Reservas */
        JButton btnInsertarReserva = crearBoton("Insertar Reserva", 150, 100);
        JButton btnActualizarReserva = crearBoton("Actualizar Reserva", 150, 200);
        JButton btnMostrarReserva = crearBoton("Mostrar Reservas", 150, 300);
        JButton btnEliminarReserva = crearBoton("Eliminar Reserva", 150, 400);
    
        //* Botones para operaciones de Mesas */
        JButton btnInsertarMesa = crearBoton("Insertar Mesa", 150, 100);
        JButton btnActualizarMesa = crearBoton("Actualizar Mesa", 150, 200);
        JButton btnMostrarMesa = crearBoton("Mostrar Mesas", 150, 300);
        JButton btnEliminarMesa = crearBoton("Eliminar Mesa", 150, 400);
    
        //* Agregar elementos a cada panel */
        panelReservas.add(lblReservas);
        panelReservas.add(separadorReservas);
        panelReservas.add(btnInsertarReserva);
        panelReservas.add(btnActualizarReserva);
        panelReservas.add(btnMostrarReserva);
        panelReservas.add(btnEliminarReserva);
    
        panelMesas.add(lblMesas);
        panelMesas.add(separadorMesas);
        panelMesas.add(btnInsertarMesa);
        panelMesas.add(btnActualizarMesa);
        panelMesas.add(btnMostrarMesa);
        panelMesas.add(btnEliminarMesa);
    
        //* Añadir paneles al panel principal */
        panelPrincipal.add(panelReservas);
        panelPrincipal.add(panelMesas);
    
        //* Añadir panel principal a la capa superior */
        layeredPane.add(panelPrincipal, JLayeredPane.PALETTE_LAYER);
    
        //* ActionListeners de ejemplo para los botones */
        btnInsertarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmInsertReserva();
                dispose();
            }
        });
        
        btnInsertarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmInsertMesa();
                dispose();
            }
        });
    
        setVisible(true);

        btnEliminarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmEliminarReserva();
                dispose();
            }
        });
    
        setVisible(true);


        btnEliminarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmEliminarMesa();
                dispose();
            }
        });
    
        setVisible(true);

        btnMostrarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmMostrarReservas();
                dispose();
            }
        });
    
        setVisible(true);
        btnMostrarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmMostrarMesas();
                dispose();
            }
        });
    
        setVisible(true);
        btnActualizarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmActualizarMesa();
                dispose();
            }
        });
    
        setVisible(true);

    }
    

    private JButton crearBoton(String texto, int x, int y) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(x, y, 200, 40);
        
        // Colores amigables y neutros
        boton.setBackground(new Color(208, 199, 197)); 
        boton.setForeground(new Color(63, 40, 31));    
        
        // Borde redondeado con color tierra
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Bordes redondeados visualmente
        boton.setContentAreaFilled(true);
        
        return boton;
    }
    
    
    

}
