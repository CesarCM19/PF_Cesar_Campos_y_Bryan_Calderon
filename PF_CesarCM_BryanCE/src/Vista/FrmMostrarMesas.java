package PF_CesarCM_BryanCE.src.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FrmMostrarMesas extends JFrame {
    private JTable tablaMesas;
    private DefaultTableModel modeloTabla;

    public FrmMostrarMesas() {
        setTitle("Ver Mesas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false);

        //* Panel principal con transparencia */
        JPanel cuadro = new JPanel();
        cuadro.setBackground(new Color(245, 225, 206, 180)); // Transparencia suave
        cuadro.setLayout(null);
        cuadro.setBounds(250, 110, 700, 400);
        setLocationRelativeTo(null);

        //* Configuración del fondo */
        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());

        //* Título */
        JLabel titleLabel = new JLabel("Ver Mesas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(300, 20, 250, 40);

        //* Configuración de la tabla */
        String[] columnas = {"Número de Mesa", "ID Restaurante", "Ubicación", "Tamaño"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaMesas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMesas);
        scrollPane.setBounds(50, 80, 600, 250);
        cuadro.add(scrollPane);

        //* Botón de Regresar */
        JButton regresarBtn = crearBoton("Regresar al Menú", 250, 350);
        cuadro.add(regresarBtn);

        //* Añadir al contenedor principal */
        add(cuadro);
        add(Imagen);
        setVisible(true);

        // Llenar la tabla con las mesas
        cargarMesas();

        //* Acción del botón Regresar */
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new FrmMenuPrincipal();  // Reemplaza con tu clase de menú
            }
        });
    }

    //* Método para cargar todas las mesas en la tabla usando el procedimiento almacenado */
    private void cargarMesas() {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final";
        String usuario = "root";
        String contrasena = "12345";

        // Llamada al procedimiento almacenado
        String query = "{call SelectMesas()}";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             CallableStatement stmt = conn.prepareCall(query);
             ResultSet rs = stmt.executeQuery()) {

            modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

            // Recorrer los resultados y agregar las filas a la tabla
            while (rs.next()) {
                String numeroMesa = rs.getString("NumeroMesa");
                int idRestaurante = rs.getInt("idRestaurante");
                String ubicacion = rs.getString("Ubicacion");
                String tamano = rs.getString("Tamano");

                modeloTabla.addRow(new Object[]{numeroMesa, idRestaurante, ubicacion, tamano});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las mesas: " + ex.getMessage());
        }
    }

    //* Método para crear botones con bordes redondeados */
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
