package PF_CesarCM_BryanCE.src.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FrmMostrarReservas extends JFrame {
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;

    public FrmMostrarReservas() {
        setTitle("Ver Reservas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false);

        // Panel principal con transparencia
        JPanel cuadro = new JPanel();
        cuadro.setBackground(new Color(245, 225, 206, 180)); // Transparencia suave
        cuadro.setLayout(null);  // Usamos null layout para posicionar manualmente
        cuadro.setBounds(250, 110, 700, 400);

        // Configuración del fondo
        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());

        // Título centrado
        JLabel titleLabel = new JLabel("Ver Reservas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(350, 20, 250, 40);  // Centramos el título en el JFrame

        // Configuración de la tabla
        String[] columnas = {"ID", "Número de Mesa", "Cédula", "ID Restaurante", "Fecha", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaReservas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        scrollPane.setBounds(50, 80, 600, 250);
        cuadro.add(scrollPane);

        // Botón "Regresar al Menú"
        JButton regresarBtn = crearBoton("Regresar al Menú", 250, 340);
        cuadro.add(regresarBtn);

        // Añadir el cuadro y fondo al contenedor principal
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

    // Método para crear botones con bordes redondeados
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
}
