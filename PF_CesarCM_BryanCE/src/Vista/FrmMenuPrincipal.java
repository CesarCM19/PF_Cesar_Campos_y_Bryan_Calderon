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

        //* Configuración del fondo */
        ImageIcon Back = new ImageIcon("PF_CesarCM_BryanCE\\src\\Images\\RestauranteFondo.png");
        JLabel Imagen = new JLabel(Back);
        Imagen.setSize(Back.getIconWidth(), Back.getIconHeight());

        //* Panel principal con división izquierda y derecha */
        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(new Color(245, 225, 206));
        panelPrincipal.setBounds(100, 50, 990, 600);