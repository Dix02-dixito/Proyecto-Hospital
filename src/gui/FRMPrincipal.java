package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FRMPrincipal extends JFrame {

    private JPanel contentpane;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FRMPrincipal().setVisible(true);
            }
        });
    }

    public FRMPrincipal() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(FRMPrincipal.class.getResource("/img/icon.png")));
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
        btnregistrocitas.setBackground(new Color(119, 136, 153));
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
        btnpaciente.setBackground(new Color(32, 178, 170));
        btnpaciente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 FRMMantenimientoPaciente mantenimientopaciente = new FRMMantenimientoPaciente();
        		 mantenimientopaciente.setVisible(true);
                 dispose();  
        	}
        });
        btnpaciente.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/settings (2).png")));
        btnpaciente.setBounds(10, 136, 200, 40);
        panelmenu.add(btnpaciente);

        // boton para mantenimiento de medico
        JButton btnmedico = new JButton("mantenimiento medico");
        btnmedico.setBackground(new Color(32, 178, 170));
        btnmedico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FRMMantenimientoMedico mantenimientomedico = new FRMMantenimientoMedico();
       		 mantenimientomedico.setVisible(true);
                dispose();  
        	}
        });
        btnmedico.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/settings (2).png")));
        btnmedico.setBounds(10, 187, 200, 40);
        panelmenu.add(btnmedico);

        // boton para mantenimiento de consultorio
        JButton btnconsultorio = new JButton("mantenimiento consultorio");
        btnconsultorio.setBackground(new Color(32, 178, 170));
        btnconsultorio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FRMMantenimientoConsultorio mantenimientoconsultorio = new FRMMantenimientoConsultorio();
        		mantenimientoconsultorio.setVisible(true);
                   dispose();  
        	}
        });
        btnconsultorio.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/settings (2).png")));
        btnconsultorio.setBounds(10, 238, 200, 40);
        panelmenu.add(btnconsultorio);

        // boton para consulta
        JButton btnconsulta = new JButton("consulta");
        btnconsulta.setBackground(new Color(30, 144, 255));
        btnconsulta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FRMConsulta consulta = new FRMConsulta();
        		consulta.setVisible(true);
                   dispose();  
        	}
        });
        btnconsulta.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/eye.png")));
        btnconsulta.setBounds(10, 391, 200, 40);
        panelmenu.add(btnconsulta);

        // boton para reporte
        JButton btnreporte = new JButton("reporte");
        btnreporte.setBackground(new Color(128, 128, 0));
        btnreporte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FRMReporte reporte = new FRMReporte();
        		reporte.setVisible(true);
                   dispose();  
        	}
        });
        btnreporte.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/exclamation.png")));
        btnreporte.setBounds(10, 340, 200, 40);
        panelmenu.add(btnreporte);

        // boton para ayuda
        JButton btnayuda = new JButton("ayuda");
        btnayuda.setBackground(new Color(85, 107, 47));
        btnayuda.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FRMAyuda ayuda = new FRMAyuda();
        		ayuda.setVisible(true);
                   dispose();  
        		
        	}
        });
        btnayuda.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/headset.png")));
        btnayuda.setBounds(10, 442, 200, 40);
        panelmenu.add(btnayuda);
        

        // titulo del menu lateral
        JLabel lbltitulo_1 = new JLabel("VENTANA PRINCIPAL");
        lbltitulo_1.setBounds(10, 31, 210, 30);
        lbltitulo_1.setForeground(new Color(30, 144, 255)); // color azul dodger
        lbltitulo_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelmenu.add(lbltitulo_1);

        // separador visual debajo del titulo
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(255, 255, 255)); // blanco
        separator.setBounds(10, 65, 200, 9);
        panelmenu.add(separator);
        
                // boton para cerrar sesion
                JButton btnMantenimientoCitas = new JButton("Mantenimiento Cita");
                btnMantenimientoCitas.setBounds(10, 289, 200, 40);
                panelmenu.add(btnMantenimientoCitas);
                btnMantenimientoCitas.setBackground(new Color(32, 178, 170)); // teal
                btnMantenimientoCitas.setForeground(new Color(0, 0, 0)); // negro texto
                btnMantenimientoCitas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // accion para cerrar sesion aqui
                    	FRMMantenimientoCitas mantenimientocitas = new FRMMantenimientoCitas();
                    	mantenimientocitas.setVisible(true);
                           dispose(); 
                    }
                });
                btnMantenimientoCitas.setIcon(new ImageIcon(FRMPrincipal.class.getResource("/img/settings (2).png")));

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