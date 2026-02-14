package gui;

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
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JOptionPane;

public class FRMLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordcontra;

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

		// ====== TITULOS Y LOGOS ======

		JLabel lblTitulo1 = new JLabel("Hospital Hermilio");
		lblTitulo1.setForeground(new Color(255, 255, 255));
		lblTitulo1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitulo1.setBounds(336, 173, 248, 36);
		contentPane.add(lblTitulo1);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/logo (1).png")));
		lblLogo.setBounds(370, 11, 151, 143);
		contentPane.add(lblLogo);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/city.png")));
		lblBackground.setBounds(314, 0, 270, 394);
		contentPane.add(lblBackground);

		JLabel lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/icon.png")));
		lblIcono.setBounds(0, 0, 42, 36);
		contentPane.add(lblIcono);

		JLabel lblTitulo2 = new JLabel("Hospital Hermilio");
		lblTitulo2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitulo2.setBounds(47, 11, 172, 14);
		contentPane.add(lblTitulo2);

		JLabel lblTitulo3 = new JLabel("INICIAR SESION");
		lblTitulo3.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblTitulo3.setBounds(10, 56, 200, 20);
		contentPane.add(lblTitulo3);

		// ====== USUARIO ======

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(new ImageIcon(FRMLogin.class.getResource("/IMG/user.png")));
		lblUsuario.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblUsuario.setBounds(10, 103, 120, 20);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBackground(SystemColor.scrollbar);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setText("Ingrese su usuario");
		txtUsuario.setBounds(10, 134, 231, 25);
		contentPane.add(txtUsuario);

		// ====== CONTRASEÑA ======

		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setIcon(new ImageIcon(FRMLogin.class.getResource("/IMG/password-protection (1).png")));
		lblContraseña.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblContraseña.setBounds(10, 186, 150, 26);
		contentPane.add(lblContraseña);

		passwordcontra = new JPasswordField();
		passwordcontra.setBackground(SystemColor.scrollbar);
		passwordcontra.setHorizontalAlignment(SwingConstants.CENTER);
		passwordcontra.setBounds(10, 210, 231, 25);
		contentPane.add(passwordcontra);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 257, 294, 2);
		contentPane.add(separator);

		// ====== BOTON INGRESAR ======

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setIcon(new ImageIcon(FRMLogin.class.getResource("/IMG/shield-check (1).png")));
		btnIngresar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		btnIngresar.setBackground(SystemColor.inactiveCaptionText);
		btnIngresar.setForeground(SystemColor.desktop);
		btnIngresar.setBounds(10, 281, 150, 36);
		contentPane.add(btnIngresar);

		// EVENTO BOTON
		btnIngresar.addActionListener(e -> {

			if (!validarCampos()) {
				return;
			}

			String usuario = txtUsuario.getText();
			String password = String.valueOf(passwordcontra.getPassword());

			if (autenticar(usuario, password)) {

				JOptionPane.showMessageDialog(this, 
						"Bienvenido");

				

			} else {

				JOptionPane.showMessageDialog(this, 
						"Usuario o contraseña incorrectos");
				limpiarCampos();
			}
		});
	}

	// ====== METODOS ======

	private boolean validarCampos() {

		if (txtUsuario.getText().trim().isEmpty() 
				|| txtUsuario.getText().equals("Ingrese su usuario")) {

			JOptionPane.showMessageDialog(this, 
					"Ingrese el usuario");
			txtUsuario.requestFocus();
			return false;
		}

		if (String.valueOf(passwordcontra.getPassword()).trim().isEmpty()) {

			JOptionPane.showMessageDialog(this, 
					"Ingrese la contraseña");
			passwordcontra.requestFocus();
			return false;
		}

		return true;
	}

	private boolean autenticar(String usuario, String password) {

		// demo
		String userDemo = "admin";
		String passDemo = "1234";

		return usuario.equals(userDemo) && password.equals(passDemo);
	}

	private void limpiarCampos() {
		txtUsuario.setText("");
		passwordcontra.setText("");
		txtUsuario.requestFocus();
	}
}
