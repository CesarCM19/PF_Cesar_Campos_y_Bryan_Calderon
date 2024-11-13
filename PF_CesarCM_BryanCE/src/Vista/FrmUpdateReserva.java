package PF_CesarCM_BryanCE.src.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import PF_CesarCM_BryanCE.src.Modelo.ActualizarDAO;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FrmUpdateReserva extends JFrame {
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;

    // Campos de texto
    private JTextField IDtxt;
    private JTextField nMesa;
    private JTextField nCedula;
    private JTextField nIDRest;
    private JTextField nFecha;
    private JTextField nDescripcion;

    public FrmUpdateReserva(){
        setTitle("Ver Reservas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1190, 720);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false);

        JPanel cuadro = new JPanel();
        JPanel Linea = new JPanel();
        Linea.setBackground(Color.black);
        Linea.setBounds(680,10,3,650);
        cuadro.setBackground(new Color(245, 225, 206, 180)); 
        cuadro.setLayout(null);  
        cuadro.setBounds(10, 10, 1150, 650);

        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());

        JLabel titleLabel = new JLabel("Reservas Actuales");
        JLabel Nuevos = new JLabel("Datos Nuevos");
        JLabel ID = new JLabel("ID de la reserva a actualizar:");
        IDtxt = new JTextField();
        IDtxt.setBounds(815,95,200,30);
        JLabel Mesa = new JLabel("Nuevo Número de Mesa:");
        Mesa.setBounds(720,130,400,30);
        nMesa = new JTextField();
        nMesa.setBounds(720,160,400,30);
        JLabel Cedula = new JLabel("Nueva cédula del cliente:");
        Cedula.setBounds(720,190,400,30);
        nCedula = new JTextField();
        nCedula.setBounds(720,220,400,30);
        JLabel IDRest = new JLabel("Nuevo ID del Restaurante:");
        IDRest.setBounds(720,250,400,30);
        nIDRest = new JTextField();
        nIDRest.setBounds(720,280,400,30);
        JLabel Fecha = new JLabel("Nueva Fecha de Reserva:");
        Fecha.setBounds(720,310,400,30);
        nFecha = new JTextField(); 
        nFecha.setBounds(720,340,400,30);
        JLabel Descripcion = new JLabel("Nueva Descripción de la reserva:");
        Descripcion.setBounds(720,370,400,30);
        nDescripcion = new JTextField(); 
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

        JButton regresarBtn = crearBoton("Regresar al Menú", 250, 470);
        JButton ActualizarBtn = crearBoton("Actualizar", 815, 470);
        cuadro.add(regresarBtn);
        cuadro.add(ActualizarBtn);

        add(Linea);
        add(cuadro);
        add(Imagen);
        
        setVisible(true);

        cargarReservas();

        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new FrmMenuPrincipal();  // Reemplaza con tu clase de menú
            }
        });
        ActualizarBtn.addActionListener(new ActionListener (){
            public void actionPerformed (ActionEvent e){
                String ID = IDtxt.getText();
                String Mesa = nMesa.getText();
                String Cedula = nCedula.getText();
                String IDR = nIDRest.getText();
                String Fecha = nFecha.getText();
                String Descrip = nDescripcion.getText();
                ActualizarDAO.ActualizarReserva(ID, Mesa, Cedula, IDR, Fecha, Descrip);
                cargarReservas();
            }
        });

        // Agregar MouseListener para seleccionar fila y llenar campos de texto
        tablaReservas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tablaReservas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    IDtxt.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                    nMesa.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                    nCedula.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                    nIDRest.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
                    nFecha.setText(modeloTabla.getValueAt(filaSeleccionada, 4).toString());
                    nDescripcion.setText(modeloTabla.getValueAt(filaSeleccionada, 5).toString());
                }
            }
        });
    }

    private void cargarReservas() {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final";
        String usuario = "root";
        String contrasena = "12345";
        String query = "{call SelectReservas()}";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
            CallableStatement stmt = conn.prepareCall(query);
            ResultSet rs = stmt.executeQuery()) {

            modeloTabla.setRowCount(0); 

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
}

