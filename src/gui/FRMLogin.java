package gui;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.*;
import conexion.ConexionBD;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class FRMLogin extends JFrame {

 private JPanel contentPane;
 private JTextField txtUsuario;
 private JPasswordField passwordcontra;

 public static void main(String[] args) {
    
     EventQueue.invokeLater(() -> {
         try {
             FRMLogin frame = new FRMLogin();
             frame.setVisible(true);
         } catch (Exception e) {
             e.printStackTrace();
         }
     });
 }

 public FRMLogin() {

     
     setTitle("Inicio de Sesión");
     setSize(750, 450);
     setLocationRelativeTo(null);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setResizable(false);
     setUndecorated(true);

     // bordes redondeados
     setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 750, 450, 40, 40));

     contentPane = new JPanel();
     contentPane.setLayout(null);
     setContentPane(contentPane);

     JPanel panelIzquierdo = new JPanel();
     panelIzquierdo.setLayout(null);
     panelIzquierdo.setBackground(Color.WHITE);
     panelIzquierdo.setBounds(0, 0, 400, 450);
     contentPane.add(panelIzquierdo);

     
     JLabel lblTitulo = new JLabel("INICIAR SESION");
     lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
     lblTitulo.setForeground(new Color(25, 118, 210));
     lblTitulo.setBounds(100, 60, 300, 30);
     panelIzquierdo.add(lblTitulo);

     
     JLabel lblUsuario = new JLabel("Usuario");
     lblUsuario.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/user-md.png")));
     lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
     lblUsuario.setBounds(75, 130, 200, 20);
     panelIzquierdo.add(lblUsuario);

     
     txtUsuario = new JTextField();
     txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
     txtUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25,118,210)));
     txtUsuario.setBounds(75, 155, 250, 30);
     panelIzquierdo.add(txtUsuario);

     
     JLabel lblPassword = new JLabel("Contrasena");
     lblPassword.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/password-protection (1).png")));
     lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
     lblPassword.setBounds(75, 210, 200, 20);
     panelIzquierdo.add(lblPassword);

     
     passwordcontra = new JPasswordField();
     passwordcontra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
     passwordcontra.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25,118,210)));
     passwordcontra.setBounds(75, 235, 250, 30);
     panelIzquierdo.add(passwordcontra);
     
     

     
     JButton btnIngresar = new JButton("INGRESAR");
     btnIngresar.setBounds(75, 310, 250, 40);
     btnIngresar.setBackground(new Color(25,118,210));
     btnIngresar.setForeground(Color.WHITE);
     btnIngresar.setFocusPainted(false);
     btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
     btnIngresar.setBorder(BorderFactory.createEmptyBorder());
     panelIzquierdo.add(btnIngresar);

     //efecto del boton
     
     btnIngresar.addMouseListener(new MouseAdapter() {
    	 public void mouseEntered(MouseEvent evt) {
    		 btnIngresar.setBackground(new Color(13,71,161));
    	 }
    	 public void mouseExited(MouseEvent evt) {
    		 btnIngresar.setBackground(new Color(25,118,210));
    	 }
     });
     
     //Evento del boton Ingresar xd
     btnIngresar.addActionListener(e -> {
    	//Obtener datos
    	 String usuario = txtUsuario.getText();
    	 String password = String.valueOf(passwordcontra.getPassword());
    	 
    	 //metodo autenticar
    	 String rol = autenticar(usuario, password);
    	 
    	 if(rol != null) {
    		 //Alerta de bienvenida si los datos son correctos
    		 JOptionPane.showMessageDialog(this, "Bienvenido al hospital xd "  + usuario + " con el rol " + rol);
    		 
    		 // TE MANDA A LA VENTANA PRINCIPAL
    		 FRMPrincipal principal = new FRMPrincipal();
    		 principal.setVisible(true);
    		 dispose();    		 
    		 } 
    	 //SI LOS DATOS INGRESADOS SON INCORRECTOS
    	 else {
    		 //Alerta datos incorrectos
    		 JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña son incorrectos");
    		 }
     });
    
     JPanel panelDerecho = new JPanel();
     panelDerecho.setBounds(400, 0, 350, 450);
     panelDerecho.setLayout(null);
     contentPane.add(panelDerecho);

     JLabel fondo = new JLabel();
     fondo.setBounds(0, 0, 350, 450);
     panelDerecho.add(fondo);

     // cargar imagen original
     ImageIcon iconoOriginal = new ImageIcon(
             FRMLogin.class.getResource("/img/doctor-login.jpg"));

     // para que la escalada de la imagen concuerde
     Image imagen = iconoOriginal.getImage().getScaledInstance(
             350, 450,
             Image.SCALE_SMOOTH);

     fondo.setIcon(new ImageIcon(imagen));

     // boton cerrar personalizado
     JButton btnCerrar = new JButton("X");
     btnCerrar.setBounds(710, 10, 30, 30);
     btnCerrar.setBackground(Color.RED);
     btnCerrar.setForeground(Color.WHITE);
     btnCerrar.setFocusPainted(false);
     btnCerrar.setBorder(BorderFactory.createEmptyBorder());
     contentPane.add(btnCerrar);

     // cerrar aplicacion
     btnCerrar.addActionListener(e -> System.exit(0));
 }

 // metodo que valida usuario y contraseña en azurebd
    private String autenticar (String usuario , String password) {
    	String sql = "SELECT rol_usuario FROM usuario WHERE nombre_usuario = ? AND contrasena_usuario = ?";
    	
    	
    	try (Connection con = ConexionBD.conectar();
    			PreparedStatement ps = con.prepareStatement(sql)) {
    		//ENVIAR PARAMETROS A LA CONSULTA
    		
    		ps.setString(1, usuario);
    		ps.setString(2, password);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		//SI ENCUENTRA REGISTRO DEVUELVE EL ROL
    		
    		if (rs.next()) {
    			return rs.getString("rol_usuario");    			    			
    		}    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(this, "Error en la conexion con azurebd");
    	}
    	return null;
    }
}