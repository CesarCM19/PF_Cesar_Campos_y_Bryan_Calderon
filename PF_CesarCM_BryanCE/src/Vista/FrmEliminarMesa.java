package PF_CesarCM_BryanCE.src.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FrmEliminarMesa extends JFrame {
    private JTable tablaMesas;
    private DefaultTableModel modeloTabla;

    public FrmEliminarMesa() {
        setTitle("Eliminar Mesa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null);
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
        JLabel titleLabel = new JLabel("Eliminar Mesa");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(280, 20, 250, 40);

        //* Configuración de la tabla */
        String[] columnas = {"Número de Mesa", "ID Restaurante", "Ubicación", "Tamaño", "Eliminar"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaMesas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMesas);
        scrollPane.setBounds(50, 80, 600, 250);
        cuadro.add(scrollPane);

        //* Botones de Eliminar y Cancelar */
        JButton eliminarBtn = crearBoton("Eliminar Seleccionada", 250, 350);
        eliminarBtn.setBounds (100, 340, 200, 40 );


        JButton cancelarBtn = crearBoton("Regresar", 450, 350);
        cancelarBtn.setBounds (400, 340, 200, 40 );


        cuadro.add(titleLabel);
        cuadro.add(eliminarBtn);
        cuadro.add(cancelarBtn);

        //* Añadir al contenedor principal */
        add(cuadro);
        add(Imagen);
        setVisible(true);

        // Llenar la tabla con las mesas
        cargarMesas();

        //* Acción del botón Eliminar */
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaMesas.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una mesa.");
                } else {
                    String numeroMesa = (String) modeloTabla.getValueAt(selectedRow, 0);
                    eliminarMesa(numeroMesa);
                }
            }
        });

        //* Acción del botón regresar */
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                setVisible(false);
                dispose();
                
                
                new FrmMenuPrincipal();  
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

            modeloTabla.setRowCount(0);

            // Recorrer los resultados y agregar las filas a la tabla
            while (rs.next()) {
                String numeroMesa = rs.getString("NumeroMesa");
                int idRestaurante = rs.getInt("idRestaurante");
                String ubicacion = rs.getString("Ubicacion");
                String tamano = rs.getString("Tamano");
                
                modeloTabla.addRow(new Object[]{numeroMesa, idRestaurante, ubicacion, tamano, "Eliminar"});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las mesas: " + ex.getMessage());
        }
    }

    //* Método para eliminar mesa desde la base de datos */
    private void eliminarMesa(String numeroMesa) {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final";
        String usuario = "root";
        String contrasena = "12345";

        // Llamada al procedimiento almacenado para eliminar
        String query = "{call DeleteMesas(?)}";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, numeroMesa);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Mesa eliminada exitosamente.");
                cargarMesas(); // Recargar las mesas después de eliminar una
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una mesa con ese número.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la mesa: " + ex.getMessage());
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
