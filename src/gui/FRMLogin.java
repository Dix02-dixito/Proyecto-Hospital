package gui;

import java.awt.*;
import controlador.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.*;
import conexion.ConexionBD;
import javax.swing.*;

public class FRMLogin extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField passwordcontra;

    // Metodo main para ejecutar el login
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

    // Constructor de la ventana Login
    public FRMLogin() {

        setIconImage(Toolkit.getDefaultToolkit().getImage(FRMLogin.class.getResource("/img/icon.png")));

        setTitle("Inicio de sesion");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        // Bordes redondeados
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 750, 450, 40, 40));

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Panel izquierdo donde va el formulario
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(null);
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setBounds(0, 0, 400, 450);
        contentPane.add(panelIzquierdo);

        JLabel lblTitulo = new JLabel("INICIAR SESION");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 128, 128));
        lblTitulo.setBounds(100, 60, 300, 30);
        panelIzquierdo.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/Usuario1.png")));
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsuario.setBounds(75, 120, 200, 30);
        panelIzquierdo.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25,118,210)));
        txtUsuario.setBounds(75, 155, 250, 30);
        panelIzquierdo.add(txtUsuario);

        JLabel lblPassword = new JLabel("Contrasena");
        lblPassword.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/Contra.png")));
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPassword.setBounds(75, 196, 200, 34);
        panelIzquierdo.add(lblPassword);

        passwordcontra = new JPasswordField();
        passwordcontra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordcontra.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25,118,210)));
        passwordcontra.setBounds(75, 235, 250, 30);
        panelIzquierdo.add(passwordcontra);

        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setIcon(new ImageIcon(FRMLogin.class.getResource("/img/shield-check (1).png")));
        btnIngresar.setBounds(75, 310, 250, 40);
        btnIngresar.setBackground(new Color(25,118,210));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder());
        panelIzquierdo.add(btnIngresar);

        // Cambia color cuando el mouse pasa por el boton
        btnIngresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnIngresar.setBackground(new Color(13,71,161));
            }
            public void mouseExited(MouseEvent evt) {
                btnIngresar.setBackground(new Color(25,118,210));
            }
        });

        // Evento del boton ingresar
        btnIngresar.addActionListener(e -> {

            // Obtener datos ingresados
            String usuario = txtUsuario.getText();
            String password = String.valueOf(passwordcontra.getPassword());

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Valida usuario en la base de datos
            String rol = usuarioDAO.validarLogin(usuario, password);

            if (rol != null) {

                JOptionPane.showMessageDialog(this,
                        "Bienvenido " + usuario + " Rol: " + rol);

                // Abre la ventana principal
                FRMPrincipal principal = new FRMPrincipal();
                principal.setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Usuario o contrasena incorrectos");
            }
        });

        // Panel derecho con imagen
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBounds(400, 0, 350, 450);
        panelDerecho.setLayout(null);
        contentPane.add(panelDerecho);

        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 350, 450);
        panelDerecho.add(fondo);

        ImageIcon iconoOriginal = new ImageIcon(
                FRMLogin.class.getResource("/img/doctor-login.jpg"));

        Image imagen = iconoOriginal.getImage().getScaledInstance(
                350, 450,
                Image.SCALE_SMOOTH);

        fondo.setIcon(new ImageIcon(imagen));

        // Boton cerrar personalizado
        JButton btnCerrar = new JButton("X");
        btnCerrar.setBounds(710, 10, 30, 30);
        btnCerrar.setBackground(Color.RED);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(btnCerrar);

        // Cierra toda la aplicacion
        btnCerrar.addActionListener(e -> System.exit(0));
    }

    // Metodo alternativo para validar directamente en BD
    private String autenticar(String usuario, String password) {

        String sql = "SELECT rol_usuario FROM usuario WHERE nombre_usuario = ? AND contrasena_usuario = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            // Si encuentra el usuario devuelve el rol
            if (rs.next()) {
                return rs.getString("rol_usuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la conexion con la base de datos");
        }

        return null;
    }
}