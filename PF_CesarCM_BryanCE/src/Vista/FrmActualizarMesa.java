package PF_CesarCM_BryanCE.src.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FrmActualizarMesa extends JFrame {
    private JTable tablaMesas;
    private DefaultTableModel modeloTabla;
    private JTextField numMesaField, idRestField, ubicacionField, tamanoField;

    @SuppressWarnings("unused")
    public FrmActualizarMesa() {
        //* Configuración de la ventana */
        setTitle("Actualizar Mesa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        //* Configuración del fondo */
        ImageIcon back = new ImageIcon(new ImageIcon("PF_CesarCM_BryanCE/src/Images/RestauranteFondo.png")
        .getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
JLabel imagen = new JLabel(back);
        imagen.setBounds(0, 0, 1190, 720);


        

        //* Panel principal transparente */
        JPanel cuadro = new JPanel();
        cuadro.setBounds(50, 30, 1100, 650);
        cuadro.setBackground(new Color(245, 225, 206, 180));
        cuadro.setLayout(null);

        //* Título */
        JLabel titleLabel = new JLabel("Actualizar Mesa");
        JLabel Mesaslbl = new JLabel("Mesas:");
        Mesaslbl.setFont(new Font("Arial", Font.BOLD, 23));
        Mesaslbl.setBounds(400, 100, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 23));
        titleLabel.setBounds(480, 20, 300, 40);
        cuadro.add(titleLabel);
        cuadro.add(Mesaslbl);

        //* Tabla de Mesas */
        String[] columnas = {"Número Mesa", "ID Restaurante", "Ubicación", "Tamaño"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaMesas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMesas);
        scrollPane.setBounds(50, 150, 700, 250);
        cuadro.add(scrollPane);

        //* Campos de edición */
        JLabel numeroMesaLabel = new JLabel("Número de Mesa:");
        numeroMesaLabel.setBounds(800, 100, 200, 30);
        cuadro.add(numeroMesaLabel);

        numMesaField = new JTextField();
        numMesaField.setBounds(800, 130, 200, 30);
        cuadro.add(numMesaField);

        JLabel idRestauranteLabel = new JLabel("ID Restaurante:");
        idRestauranteLabel.setBounds(800, 170, 200, 30);
        cuadro.add(idRestauranteLabel);

        idRestField = new JTextField();
        idRestField.setBounds(800, 200, 200, 30);
        cuadro.add(idRestField);

        JLabel ubicacionLabel = new JLabel("Ubicación:");
        ubicacionLabel.setBounds(800, 240, 200, 30);
        cuadro.add(ubicacionLabel);

        ubicacionField = new JTextField();
        ubicacionField.setBounds(800, 270, 200, 30);
        cuadro.add(ubicacionField);

        JLabel tamanoLabel = new JLabel("Tamaño:");
        tamanoLabel.setBounds(800, 310, 200, 30);
        cuadro.add(tamanoLabel);

        tamanoField = new JTextField();
        tamanoField.setBounds(800, 340, 200, 30);
        cuadro.add(tamanoField);

        JButton actualizarBtn = crearBoton("Actualizar Mesa", 800, 400);
        cuadro.add(actualizarBtn);
    
        JButton volverBtn = crearBoton("Volver", 800, 450);
        cuadro.add(volverBtn);

        //* Agregar al contenedor principal */
        add(cuadro);
        add(imagen); // Añadir fondo después para que quede detrás de cuadro
        setVisible(true); // Ahora se muestra la ventana completa

        //* Llenar la tabla con las mesas actuales */
        cargarMesas();

        //* Acción del botón actualizar */
        actualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaMesas.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una mesa para actualizar.");
                } else {
                    String numeroMesa = numMesaField.getText();
                    String idRestaurante = idRestField.getText();
                    String ubicacion = ubicacionField.getText();
                    String tamano = tamanoField.getText();
                    actualizarMesa(numeroMesa, idRestaurante, ubicacion, tamano);
                }
            }
        });

        //* Acción del botón volver */
        volverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmMenuPrincipal();
                dispose();
            }
        });

        //* Acción de selección en la tabla */
        tablaMesas.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = tablaMesas.getSelectedRow();
            if (selectedRow != -1) {
                numMesaField.setText(modeloTabla.getValueAt(selectedRow, 0).toString());
                idRestField.setText(modeloTabla.getValueAt(selectedRow, 1).toString());
                ubicacionField.setText(modeloTabla.getValueAt(selectedRow, 2).toString());
                tamanoField.setText(modeloTabla.getValueAt(selectedRow, 3).toString());

                
            }
        });
    }

    //* Método para cargar las mesas en la tabla utilizando el procedimiento almacenado */
    private void cargarMesas() {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final?useSSL=false";
        String usuario = "root";
        String contrasena = "12345";
        String query = "{call SelectMesas()}";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             CallableStatement stmt = conn.prepareCall(query);
             ResultSet rs = stmt.executeQuery()) {

            modeloTabla.setRowCount(0);

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getString("NumeroMesa"),
                    rs.getString("idRestaurante"),
                    rs.getString("Ubicacion"),
                    rs.getString("Tamano")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las mesas: " + ex.getMessage());
        }
    }

    private void actualizarMesa(String numeroMesa, String idRestaurante, String ubicacion, String tamano) {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final?useSSL=false";
        String usuario = "root";
        String contrasena = "12345";
        String query = "{call UpdateMesas(?, ?, ?, ?)}";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, numeroMesa);
            stmt.setString(2, idRestaurante);
            stmt.setString(3, ubicacion);
            stmt.setString(4, tamano);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Mesa actualizada exitosamente.");
                cargarMesas();

            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una mesa con ese número.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la mesa: " + ex.getMessage());
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
//comentario
}
