package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.TextField;
import javax.swing.JSeparator;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.DropMode;
import javax.swing.JButton;
import java.awt.Toolkit;

public class FRMLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordcontra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMLogin frame = new FRMLogin();
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
	public FRMLogin() {
		setTitle("Inicio de Sesion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMLogin.class.getResource("/IMG/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 430);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo1 = new JLabel("Hospital Hermilio");
		lblTitulo1.setForeground(new Color(255, 255, 255));
		lblTitulo1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitulo1.setBounds(336, 173, 248, 36);
		contentPane.add(lblTitulo1);
		
		JLabel lblLogo = new JLabel("New label");
		lblLogo.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/logo (1).png")));
		lblLogo.setBounds(370, 11, 151, 143);
		contentPane.add(lblLogo);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/city.png")));
		lblBackground.setBounds(314, 0, 270, 394);
		contentPane.add(lblBackground);
		
		JLabel lblIcono = new JLabel("New label");
		lblIcono.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/icon.png")));
		lblIcono.setBounds(0, 0, 42, 36);
		contentPane.add(lblIcono);
		
		JLabel lblTitulo2 = new JLabel("Hospital Hermilio");
		lblTitulo2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitulo2.setBounds(47, 11, 172, 14);
		contentPane.add(lblTitulo2);
		
		JLabel lblTitulo3 = new JLabel("INICIAR SESION");
		lblTitulo3.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblTitulo3.setBounds(10, 56, 144, 14);
		contentPane.add(lblTitulo3);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(new ImageIcon(FRMLogin.class.getResource("/IMG/user.png")));
		lblUsuario.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblUsuario.setBounds(10, 103, 101, 20);
		contentPane.add(lblUsuario);
		
		JTextField txtIngreseSuUsuario = new JTextField();
		txtIngreseSuUsuario.setBackground(SystemColor.scrollbar);
		txtIngreseSuUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtIngreseSuUsuario.setForeground(SystemColor.infoText);
		txtIngreseSuUsuario.setText("Ingrese su usuario");
		txtIngreseSuUsuario.setBounds(10, 134, 231, 20);
		contentPane.add(txtIngreseSuUsuario);
		txtIngreseSuUsuario.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setIcon(new ImageIcon(FRMLogin.class.getResource("/IMG/password-protection (1).png")));
		lblContraseña.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblContraseña.setBounds(10, 186, 131, 26);
		contentPane.add(lblContraseña);
		
		passwordcontra = new JPasswordField();
		passwordcontra.setBackground(SystemColor.scrollbar);
		passwordcontra.setForeground(new Color(180, 180, 180));
		passwordcontra.setToolTipText("");
		passwordcontra.setHorizontalAlignment(SwingConstants.CENTER);
		passwordcontra.setBounds(10, 210, 231, 20);
		contentPane.add(passwordcontra);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 257, 294, 2);
		contentPane.add(separator);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setIcon(new ImageIcon(FRMLogin.class.getResource("/IMG/shield-check (1).png")));
		btnIngresar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		btnIngresar.setBackground(SystemColor.inactiveCaptionText);
		btnIngresar.setBounds(10, 281, 119, 36);
		contentPane.add(btnIngresar);

	}
}
