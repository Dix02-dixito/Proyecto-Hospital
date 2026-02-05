package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel bg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 429);
		bg = new JPanel();
		bg.setBackground(SystemColor.scrollbar);
		bg.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg);
		bg.setLayout(null);
		
		JButton btnMantenimiento = new JButton("Mantenimiento");
		btnMantenimiento.setIcon(new ImageIcon(Principal.class.getResource("/IMG/settings (2).png")));
		btnMantenimiento.setBackground(SystemColor.inactiveCaptionText);
		btnMantenimiento.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnMantenimiento.setBounds(30, 116, 140, 40);
		bg.add(btnMantenimiento);
		
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.setIcon(new ImageIcon(Principal.class.getResource("/IMG/eye.png")));
		btnConsulta.setBackground(SystemColor.inactiveCaptionText);
		btnConsulta.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsulta.setBounds(30, 167, 140, 40);
		bg.add(btnConsulta);
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.setBackground(SystemColor.inactiveCaptionText);
		btnRegistro.setIcon(new ImageIcon(Principal.class.getResource("/IMG/user-add.png")));
		btnRegistro.setSelectedIcon(new ImageIcon(Principal.class.getResource("/IMG/icon.png")));
		btnRegistro.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnRegistro.setBounds(30, 63, 140, 40);
		bg.add(btnRegistro);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.setIcon(new ImageIcon(Principal.class.getResource("/IMG/exclamation.png")));
		btnReporte.setBackground(SystemColor.inactiveCaptionText);
		btnReporte.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnReporte.setBounds(30, 218, 140, 40);
		bg.add(btnReporte);
		
		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.setIcon(new ImageIcon(Principal.class.getResource("/IMG/headset.png")));
		btnAyuda.setBackground(SystemColor.inactiveCaptionText);
		btnAyuda.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnAyuda.setBounds(30, 269, 140, 40);
		bg.add(btnAyuda);
		
		JLabel lblimgbg = new JLabel("New label");
		lblimgbg.setIcon(new ImageIcon(Principal.class.getResource("/IMG/hpsss.png")));
		lblimgbg.setBounds(255, 0, 357, 441);
		bg.add(lblimgbg);
		
		JLabel lblTitulo = new JLabel("MENU PRINCIPAL DEL SISTEMA ");
		lblTitulo.setForeground(SystemColor.infoText);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitulo.setBounds(10, 29, 255, 23);
		bg.add(lblTitulo);

	}
}
