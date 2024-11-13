package PF_CesarCM_BryanCE.src.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class FrmEliminarReserva extends JFrame {
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;

    public FrmEliminarReserva() {
        setTitle("Eliminar Reserva");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        //* Panel principal con transparencia */
        JPanel cuadro = new JPanel();
        cuadro.setBackground(new Color(245, 225, 206, 180)); // Transparencia suave
        cuadro.setLayout(null);
        cuadro.setBounds(50, 50, 700, 400);

        //* Configuración del fondo */
        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());

        //* Título */
        JLabel titleLabel = new JLabel("Eliminar Reserva");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(300, 20, 250, 40);

        //* Configuración de la tabla */
        String[] columnas = {"ID Reserva", "Fecha", "Cliente", "Eliminar"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaReservas = new JTable(modeloTabla);
        tablaReservas.setBounds(50, 80, 600, 250);
        tablaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        scrollPane.setBounds(50, 80, 600, 250);
        cuadro.add(scrollPane);

        //* Botón de Actualizar tabla y Eliminar */
        JButton eliminarBtn = crearBoton("Eliminar Seleccionada", 250, 350);
        JButton cancelarBtn = crearBoton("Cancelar", 450, 350);

        cuadro.add(titleLabel);
        cuadro.add(eliminarBtn);
        cuadro.add(cancelarBtn);

        //* Añadir al contenedor principal */
        add(cuadro);
        add(Imagen);
        setVisible(true);

        // Llenar la tabla con las reservas
        cargarReservas();

        //* Acción del botón Eliminar */
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaReservas.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una reserva.");
                } else {
                    // Obtener el ID de la reserva seleccionada
                    int idReserva = (int) modeloTabla.getValueAt(selectedRow, 0);
                    eliminarReserva(idReserva); // Llamada al método para eliminar la reserva
                }
            }
        });

        //* Acción del botón Cancelar */
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  // Cerrar el frame actual
                dispose();          // Liberar los recursos
            }
        });
    }

    //* Método para cargar todas las reservas en la tabla */
    private void cargarReservas() {
    String url = "jdbc:mysql://localhost:3306/bd_proyecto_final"; // Cambia el nombre de la base de datos
        String usuario = "root";
        String contrasena = "12345"; // La contraseña de la base de datos si la tienes configurada

        // Consulta SQL para obtener todas las reservas
        String query = "SELECT id, fecha, cliente FROM reservas";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Limpiar la tabla antes de agregar nuevos datos
            modeloTabla.setRowCount(0);

            // Recorrer los resultados y agregar filas a la tabla
            while (rs.next()) {
                int idReserva = rs.getInt("id");
                String fecha = rs.getString("fecha");
                String cliente = rs.getString("cliente");
                modeloTabla.addRow(new Object[]{idReserva, fecha, cliente, "Eliminar"});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las reservas: " + ex.getMessage());
        }
    }

    //* Método para eliminar reserva desde la base de datos */
    private void eliminarReserva(int idReserva) {
        String url = "jdbc:mysql://localhost:3306/nombre_base_datos"; // Cambia el nombre de la base de datos
        String usuario = "root";
        String contraseña = "12345"; // La contraseña de la base de datos si la tienes configurada

        // Consulta SQL para ejecutar el procedimiento almacenado
        String query = "{call DeleteReserva(?)}";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             CallableStatement stmt = conn.prepareCall(query)) {

            // Establecer el parámetro para el procedimiento almacenado
            stmt.setInt(1, idReserva);

            // Ejecutar la llamada al procedimiento
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Reserva eliminada exitosamente.");
                cargarReservas(); // Recargar las reservas después de eliminar una
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una reserva con ese ID.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la reserva: " + ex.getMessage());
        }
    }

    //* Método para crear botones con bordes redondeados */
    private JButton crearBoton(String texto, int x, int y) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(x, y, 200, 40);
        boton.setBackground(new Color(186, 140, 99)); // Color tierra suave
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(186, 140, 99), 1, true)); // Borde redondeado
        boton.setContentAreaFilled(true);
        boton.setOpaque(true);
        return boton;
    }

    public static void main(String[] args) {
        new FrmEliminarReserva();
    }
}
