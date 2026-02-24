package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FRMPrincipal extends JFrame {

    private JPanel contentpane;

    public FRMPrincipal() {
        // configuracion de la ventana principal
        setTitle("sistema hospitalario");
        setSize(769, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        


        contentpane = new JPanel();
        contentpane.setLayout(null);
        setContentPane(contentpane);

        // menu lateral
        JPanel panelmenu = new JPanel();
        panelmenu.setBackground(new Color(33, 33, 33)); // color gris oscuro
        panelmenu.setBounds(0, 0, 220, 550);
        panelmenu.setLayout(null);
        contentpane.add(panelmenu);

        // boton para registrar citas
        JButton btnregistrocitas = new JButton("registrar citas");
        btnregistrocitas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FRMRegistroCitas registrocitas = new FRMRegistroCitas();
                registrocitas.setVisible(true);
                dispose();    
            }
        });
        btnregistrocitas.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/agregar.png")));
        btnregistrocitas.setBounds(10, 85, 200, 40);
        panelmenu.add(btnregistrocitas);

        // boton para mantenimiento de paciente
        JButton btnpaciente = new JButton("mantenimiento paciente");
        btnpaciente.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/settings (2).png")));
        btnpaciente.setBounds(10, 136, 200, 40);
        panelmenu.add(btnpaciente);

        // boton para mantenimiento de medico
        JButton btnmedico = new JButton("mantenimiento medico");
        btnmedico.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/settings (2).png")));
        btnmedico.setBounds(10, 187, 200, 40);
        panelmenu.add(btnmedico);

        // boton para mantenimiento de consultorio
        JButton btnconsultorio = new JButton("mantenimiento consultorio");
        btnconsultorio.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/settings (2).png")));
        btnconsultorio.setBounds(10, 238, 200, 40);
        panelmenu.add(btnconsultorio);

        // boton para consulta
        JButton btnconsulta = new JButton("consulta");
        btnconsulta.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/eye.png")));
        btnconsulta.setBounds(10, 289, 200, 40);
        panelmenu.add(btnconsulta);

        // boton para reporte
        JButton btnreporte = new JButton("reporte");
        btnreporte.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/exclamation.png")));
        btnreporte.setBounds(10, 340, 200, 40);
        panelmenu.add(btnreporte);

        // boton para ayuda
        JButton btnayuda = new JButton("ayuda");
        btnayuda.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/headset.png")));
        btnayuda.setBounds(10, 391, 200, 40);
        panelmenu.add(btnayuda);
        

        // titulo del menu lateral
        JLabel lbltitulo_1 = new JLabel("ventana principal");
        lbltitulo_1.setBounds(10, 31, 210, 30);
        lbltitulo_1.setForeground(new Color(30, 144, 255)); // color azul dodger
        lbltitulo_1.setFont(new Font("segoe ui", Font.BOLD, 18));
        panelmenu.add(lbltitulo_1);

        // separador visual debajo del titulo
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(255, 255, 255)); // blanco
        separator.setBounds(10, 65, 200, 9);
        panelmenu.add(separator);
        
                // boton para cerrar sesion
                JButton btnsalir = new JButton("cerrar sesion");
                btnsalir.setBounds(10, 441, 200, 40);
                panelmenu.add(btnsalir);
                btnsalir.setBackground(new Color(0, 128, 128)); // teal
                btnsalir.setForeground(new Color(0, 0, 0)); // negro texto
                btnsalir.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // accion para cerrar sesion aqui
                    }
                });
                btnsalir.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/salida.png")));

        // header
        JPanel panelheader = new JPanel();
        panelheader.setBackground(new Color(8, 185, 203)); // color turquesa
        panelheader.setBounds(0, 0, 900, 60);
        panelheader.setLayout(null);
        contentpane.add(panelheader);

        // panel contenido
        JPanel panelcontenido = new JPanel();
        panelcontenido.setBackground(Color.WHITE);
        panelcontenido.setBounds(220, 60, 533, 451);
        panelcontenido.setLayout(null);
        contentpane.add(panelcontenido);
        
        ImageIcon icono = new ImageIcon(
                FRMPrincipal.class.getResource("/img/VentanaPrincipal.jpg"));

        Image imagen = icono.getImage().getScaledInstance(
                533, 451,
                Image.SCALE_SMOOTH);

        JLabel lblNewLabel = new JLabel(new ImageIcon(imagen));
        lblNewLabel.setBounds(0, 0, 533, 451);

        panelcontenido.add(lblNewLabel);
        
        
        
        
    }
    
    
}