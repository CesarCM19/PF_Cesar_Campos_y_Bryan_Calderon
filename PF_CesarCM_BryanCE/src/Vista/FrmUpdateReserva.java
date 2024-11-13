package PF_CesarCM_BryanCE.src.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FrmUpdateReserva extends JFrame {
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    public FrmUpdateReserva(){
        setTitle("Ver Reservas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false);

        // Panel principal con transparencia
        JPanel cuadro = new JPanel();
        JPanel Linea = new JPanel();
        Linea.setBackground(Color.black);
        Linea.setBounds(680,10,3,650);
        cuadro.setBackground(new Color(245, 225, 206, 180)); // Transparencia suave
        cuadro.setLayout(null);  // Usamos null layout para posicionar manualmente
        cuadro.setBounds(10, 10, 1150, 650);

        // Configuración del fondo
        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());

        // Título centrado
        JLabel titleLabel = new JLabel("Reservas Actuales");
        JLabel Nuevos = new JLabel("Datos Nuevos");
        JLabel ID = new JLabel("ID de la reserva a actualizar:");
        JTextField IDtxt = new JTextField();
        IDtxt.setBounds(815,95,200,30);
        JLabel Mesa = new JLabel("Nuevo Número de Mesa:");
        Mesa.setBounds(720,130,400,30);
        JTextField nMesa = new JTextField();
        nMesa.setBounds(720,160,400,30);
        JLabel Cedula = new JLabel("Nueva cédula del cliente:");
        Cedula.setBounds(720,190,400,30);
        JTextField nCedula = new JTextField();
        nCedula.setBounds(720,220,400,30);
        JLabel IDRest = new JLabel("Nuevo ID del Restaurante:");
        IDRest.setBounds(720,250,400,30);
        JTextField nIDRest = new JTextField();
        nIDRest.setBounds(720,280,400,30);
        JLabel Fecha = new JLabel("Nueva Fecha de Reserva:");
        Fecha.setBounds(720,310,400,30);
        JTextField nFecha = new JTextField(); 
        nFecha.setBounds(720,340,400,30);
        JLabel Descripcion = new JLabel("Nueva Descripción de la reserva:");
        Descripcion.setBounds(720,370,400,30);
        JTextField nDescripcion = new JTextField(); 
        nDescripcion.setBounds(720,400,400,30);
        ID.setBounds(820,55,300,50);
        ID.setFont(new Font("Arial", Font.BOLD, 15));
        Nuevos.setBounds(850,-35,150,150);
        Nuevos.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(265, 140, 250, 40);
        cuadro.add(titleLabel);
        cuadro.add(Nuevos);
        cuadro.add(ID);
        cuadro.add(IDtxt);
        cuadro.add(Mesa);
        cuadro.add(nMesa);
        cuadro.add(Cedula);
        cuadro.add(nCedula);
        cuadro.add(IDRest);
        cuadro.add(nIDRest);
        cuadro.add(Fecha);
        cuadro.add(nFecha);
        cuadro.add(Descripcion);
        cuadro.add(nDescripcion);



        // Configuración de la tabla
        String[] columnas = {"ID", "Número de Mesa", "Cédula", "ID Restaurante", "Fecha", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaReservas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        scrollPane.setBounds(50, 190, 600, 250);
        cuadro.add(scrollPane);

        // Botón "Regresar al Menú"
        JButton regresarBtn = crearBoton("Regresar al Menú", 250, 500);
        cuadro.add(regresarBtn);

        // Añadir el cuadro y fondo al contenedor principal
        add(Linea);
        add(cuadro);
        add(Imagen);
        
        setVisible(true);

        // Llenar la tabla con las reservas
        cargarReservas();

        // Acción del botón "Regresar al Menú"
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new FrmMenuPrincipal();  // Reemplaza con tu clase de menú
            }
        });
    }

    // Método para cargar todas las reservas en la tabla usando el procedimiento almacenado
    private void cargarReservas() {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final";
        String usuario = "root";
        String contrasena = "12345";

        // Llamada al procedimiento almacenado
        String query = "{call SelectReservas()}";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
            CallableStatement stmt = conn.prepareCall(query);
            ResultSet rs = stmt.executeQuery()) {

            modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

            // Recorrer los resultados y agregar las filas a la tabla
            while (rs.next()) {
                int idReserva = rs.getInt("id");
                int numeroMesa = rs.getInt("numeroMesa");
                String cedula = rs.getString("Cedula");
                int idRestaurante = rs.getInt("IDRestaurante");
                String fecha = rs.getString("Fecha");
                String descripcion = rs.getString("Descripcion");

                modeloTabla.addRow(new Object[]{idReserva, numeroMesa, cedula, idRestaurante, fecha, descripcion});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las reservas: " + ex.getMessage());
        }
    }
    private JButton crearBoton(String texto, int x, int y) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(x, y, 200, 40);
        boton.setBackground(new Color(186, 140, 99));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(186, 140, 99), 1, true));
        boton.setContentAreaFilled(true);
        boton.setOpaque(true);
        return boton;
    }
    public static void main(String[] args) {
        new FrmUpdateReserva();
    }
}
