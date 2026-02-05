package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Mantenimiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre1;
	private JTextField txtApellidos1;
	private JTextField txtDNI1;
	private JTextField txtEstado1;
	private JTextField txtCorreo1;
	private JTextField txtEdad1;
	private JTextField txtTelefono1;
	private JTextField txtCodigo;
	private JTable tablePaciente;
	private JTextField txtBuscar1;
	private JTextField txtNombre2;
	private JTextField txtApellidos2;
	private JTextField txtEdad2;
	private JTextField txtCMP;
	private JTextField txtEspecialidad;
	private JTextField txtTelefono;
	private JTable tableMedico;
	private JTextField txtBuscar2;
	private JTextField txtNombreConsultorio;
	private JTextField txtUbicacion;
	private JTextField txtEstado2;
	private JTextField txtNumeroConsulta;
	private JTable tableConsultorio;
	private JTextField txtBuscar3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mantenimiento frame = new Mantenimiento();
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
	public Mantenimiento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 962);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo1 = new JLabel("Hospital Hermilio");
		lblTitulo1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		lblTitulo1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/icon.png")));
		lblTitulo1.setBounds(10, 11, 160, 35);
		contentPane.add(lblTitulo1);
		
		JLabel lblsubTitulo1 = new JLabel("Mantenimiento Pacientes");
		lblsubTitulo1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/settings (2).png")));
		lblsubTitulo1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblsubTitulo1.setBounds(20, 60, 222, 27);
		contentPane.add(lblsubTitulo1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 82, 214, 2);
		contentPane.add(separator);
		
		JLabel lblNombre1 = new JLabel("Nombres");
		lblNombre1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblNombre1.setBounds(10, 98, 53, 17);
		contentPane.add(lblNombre1);
		
		txtNombre1 = new JTextField();
		txtNombre1.setBounds(70, 98, 150, 20);
		contentPane.add(txtNombre1);
		txtNombre1.setColumns(10);
		
		JLabel lblApellidos1 = new JLabel("Apellidos");
		lblApellidos1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblApellidos1.setBounds(249, 98, 60, 14);
		contentPane.add(lblApellidos1);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCorreo.setBounds(10, 174, 46, 27);
		contentPane.add(lblCorreo);
		
		txtApellidos1 = new JTextField();
		txtApellidos1.setColumns(10);
		txtApellidos1.setBounds(319, 95, 150, 20);
		contentPane.add(txtApellidos1);
		
		JLabel lblTelefono1 = new JLabel("Telefono");
		lblTelefono1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblTelefono1.setBounds(369, 138, 60, 27);
		contentPane.add(lblTelefono1);
		
		JLabel lblDNI1 = new JLabel("DNI");
		lblDNI1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblDNI1.setBounds(10, 136, 30, 30);
		contentPane.add(lblDNI1);
		
		txtDNI1 = new JTextField();
		txtDNI1.setBounds(70, 136, 100, 20);
		contentPane.add(txtDNI1);
		txtDNI1.setColumns(10);
		
		JLabel lblEstado1 = new JLabel("Estado");
		lblEstado1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEstado1.setBounds(208, 136, 46, 27);
		contentPane.add(lblEstado1);
		
		JLabel lblEdad1 = new JLabel("Edad");
		lblEdad1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEdad1.setBounds(488, 90, 46, 30);
		contentPane.add(lblEdad1);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCodigo.setBounds(379, 174, 50, 17);
		contentPane.add(lblCodigo);
		
		txtEstado1 = new JTextField();
		txtEstado1.setColumns(10);
		txtEstado1.setBounds(259, 136, 100, 20);
		contentPane.add(txtEstado1);
		
		txtCorreo1 = new JTextField();
		txtCorreo1.setColumns(10);
		txtCorreo1.setBounds(70, 174, 289, 20);
		contentPane.add(txtCorreo1);
		
		txtEdad1 = new JTextField();
		txtEdad1.setColumns(10);
		txtEdad1.setBounds(535, 95, 46, 20);
		contentPane.add(txtEdad1);
		
		txtTelefono1 = new JTextField();
		txtTelefono1.setColumns(10);
		txtTelefono1.setBounds(434, 136, 100, 20);
		contentPane.add(txtTelefono1);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(434, 174, 100, 20);
		contentPane.add(txtCodigo);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(766, 0, 17, 560);
		contentPane.add(scrollBar);
		
		JRadioButton rdbtnDNI = new JRadioButton("DNI");
		rdbtnDNI.setBounds(47, 244, 51, 23);
		contentPane.add(rdbtnDNI);
		
		JRadioButton rdbtnApellidos1 = new JRadioButton("Apellidos");
		rdbtnApellidos1.setBounds(100, 244, 67, 23);
		contentPane.add(rdbtnApellidos1);
		
		txtBuscar1 = new JTextField();
		txtBuscar1.setBackground(SystemColor.scrollbar);
		txtBuscar1.setBounds(172, 245, 222, 20);
		contentPane.add(txtBuscar1);
		txtBuscar1.setColumns(10);
		
		JButton btnBuscar1 = new JButton("Buscar");
		btnBuscar1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/busqueda.png")));
		btnBuscar1.setBackground(SystemColor.inactiveCaptionText);
		btnBuscar1.setBounds(417, 244, 89, 23);
		contentPane.add(btnBuscar1);
		
		tablePaciente = new JTable();
		tablePaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePaciente.setBounds(35, 234, 509, 52);
		contentPane.add(tablePaciente);
		
		JLabel lblConsulta1 = new JLabel("Consulta");
		lblConsulta1.setBounds(33, 216, 46, 14);
		contentPane.add(lblConsulta1);
		
		JLabel lblsubTitulo2 = new JLabel("Mantenimiento Medico");
		lblsubTitulo2.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/settings (2).png")));
		lblsubTitulo2.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblsubTitulo2.setBounds(10, 381, 197, 27);
		contentPane.add(lblsubTitulo2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 406, 214, 2);
		contentPane.add(separator_1);
		
		JButton btnAgregar1 = new JButton("Agregar");
		btnAgregar1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/agregar.png")));
		btnAgregar1.setBackground(SystemColor.inactiveCaptionText);
		btnAgregar1.setBounds(35, 309, 100, 23);
		contentPane.add(btnAgregar1);
		
		JButton btnModificar1 = new JButton("Modificar");
		btnModificar1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar1.setBackground(SystemColor.inactiveCaptionText);
		btnModificar1.setBounds(154, 309, 100, 23);
		contentPane.add(btnModificar1);
		
		JButton btnEliminar1 = new JButton("Eliminar");
		btnEliminar1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar1.setBackground(SystemColor.inactiveCaptionText);
		btnEliminar1.setBounds(264, 309, 100, 23);
		contentPane.add(btnEliminar1);
		
		JButton btnGrabar1 = new JButton("Grabar");
		btnGrabar1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar1.setBackground(SystemColor.inactiveCaptionText);
		btnGrabar1.setBounds(379, 309, 100, 23);
		contentPane.add(btnGrabar1);
		
		JLabel lblNombre2 = new JLabel("Nombres");
		lblNombre2.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblNombre2.setBounds(10, 429, 53, 17);
		contentPane.add(lblNombre2);
		
		txtNombre2 = new JTextField();
		txtNombre2.setColumns(10);
		txtNombre2.setBounds(70, 424, 150, 20);
		contentPane.add(txtNombre2);
		
		JLabel lblApellidos2 = new JLabel("Apellidos");
		lblApellidos2.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblApellidos2.setBounds(249, 427, 60, 14);
		contentPane.add(lblApellidos2);
		
		txtApellidos2 = new JTextField();
		txtApellidos2.setColumns(10);
		txtApellidos2.setBounds(319, 424, 150, 20);
		contentPane.add(txtApellidos2);
		
		JLabel lblEdad2 = new JLabel("Edad");
		lblEdad2.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEdad2.setBounds(488, 424, 46, 30);
		contentPane.add(lblEdad2);
		
		txtEdad2 = new JTextField();
		txtEdad2.setColumns(10);
		txtEdad2.setBounds(535, 424, 46, 20);
		contentPane.add(txtEdad2);
		
		JLabel lblCMP = new JLabel("CMP");
		lblCMP.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCMP.setBounds(10, 468, 30, 30);
		contentPane.add(lblCMP);
		
		txtCMP = new JTextField();
		txtCMP.setColumns(10);
		txtCMP.setBounds(70, 470, 100, 20);
		contentPane.add(txtCMP);
		
		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEspecialidad.setBounds(180, 470, 74, 27);
		contentPane.add(lblEspecialidad);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(259, 470, 150, 20);
		contentPane.add(txtEspecialidad);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblTelefono.setBounds(417, 473, 60, 27);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(481, 470, 100, 20);
		contentPane.add(txtTelefono);
		
		JLabel lblConsulta2 = new JLabel("Consulta");
		lblConsulta2.setBounds(35, 535, 46, 14);
		contentPane.add(lblConsulta2);
		
		JRadioButton rdbtnCmp = new JRadioButton("CMP");
		rdbtnCmp.setBounds(47, 570, 51, 23);
		contentPane.add(rdbtnCmp);
		
		txtBuscar2 = new JTextField();
		txtBuscar2.setColumns(10);
		txtBuscar2.setBackground(SystemColor.scrollbar);
		txtBuscar2.setBounds(172, 571, 222, 20);
		contentPane.add(txtBuscar2);
		
		JButton btnBuscar2 = new JButton("Buscar");
		btnBuscar2.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/busqueda.png")));
		btnBuscar2.setBackground(SystemColor.inactiveCaptionText);
		btnBuscar2.setBounds(417, 570, 89, 23);
		contentPane.add(btnBuscar2);
		
		JRadioButton rdbtnApellidos = new JRadioButton("Apellidos");
		rdbtnApellidos.setBounds(100, 570, 67, 23);
		contentPane.add(rdbtnApellidos);
		
		tableMedico = new JTable();
		tableMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableMedico.setBounds(35, 554, 509, 52);
		contentPane.add(tableMedico);
		
		JButton btnAgregar2 = new JButton("Agregar");
		btnAgregar2.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/agregar.png")));
		btnAgregar2.setBackground(SystemColor.inactiveCaptionText);
		btnAgregar2.setBounds(35, 627, 100, 23);
		contentPane.add(btnAgregar2);
		
		JButton btnModificar2 = new JButton("Modificar");
		btnModificar2.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar2.setBackground(SystemColor.inactiveCaptionText);
		btnModificar2.setBounds(154, 627, 100, 23);
		contentPane.add(btnModificar2);
		
		JButton btnEliminar2 = new JButton("Eliminar");
		btnEliminar2.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar2.setBackground(SystemColor.inactiveCaptionText);
		btnEliminar2.setBounds(264, 627, 100, 23);
		contentPane.add(btnEliminar2);
		
		JButton btnGrabar2 = new JButton("Grabar");
		btnGrabar2.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar2.setBackground(SystemColor.inactiveCaptionText);
		btnGrabar2.setBounds(379, 627, 100, 23);
		contentPane.add(btnGrabar2);
		
		JLabel lblsubTitulo3 = new JLabel("Mantenimiento Consultorio");
		lblsubTitulo3.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/settings (2).png")));
		lblsubTitulo3.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblsubTitulo3.setBounds(10, 686, 232, 27);
		contentPane.add(lblsubTitulo3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 708, 214, 2);
		contentPane.add(separator_1_1);
		
		JLabel lblNombreConsultorio = new JLabel("Nombre del Consultorio");
		lblNombreConsultorio.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblNombreConsultorio.setBounds(10, 738, 139, 17);
		contentPane.add(lblNombreConsultorio);
		
		txtNombreConsultorio = new JTextField();
		txtNombreConsultorio.setColumns(10);
		txtNombreConsultorio.setBounds(159, 733, 200, 20);
		contentPane.add(txtNombreConsultorio);
		
		JLabel lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblUbicacion.setBounds(10, 766, 60, 28);
		contentPane.add(lblUbicacion);
		
		txtUbicacion = new JTextField();
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(79, 767, 280, 20);
		contentPane.add(txtUbicacion);
		
		JLabel lblEstado2 = new JLabel("Estado");
		lblEstado2.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEstado2.setBounds(369, 736, 60, 27);
		contentPane.add(lblEstado2);
		
		txtEstado2 = new JTextField();
		txtEstado2.setColumns(10);
		txtEstado2.setBounds(417, 733, 150, 20);
		contentPane.add(txtEstado2);
		
		JLabel lblNumeroConsulta = new JLabel("Numero de Consultorio");
		lblNumeroConsulta.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblNumeroConsulta.setBounds(369, 770, 137, 27);
		contentPane.add(lblNumeroConsulta);
		
		txtNumeroConsulta = new JTextField();
		txtNumeroConsulta.setColumns(10);
		txtNumeroConsulta.setBounds(503, 767, 64, 20);
		contentPane.add(txtNumeroConsulta);
		
		JRadioButton rdbtnNombreDelConsultorio = new JRadioButton("Nombre del Consultorio");
		rdbtnNombreDelConsultorio.setBounds(47, 829, 139, 23);
		contentPane.add(rdbtnNombreDelConsultorio);
		
		txtBuscar3 = new JTextField();
		txtBuscar3.setColumns(10);
		txtBuscar3.setBackground(SystemColor.scrollbar);
		txtBuscar3.setBounds(187, 830, 242, 20);
		contentPane.add(txtBuscar3);
		
		JButton btnBuscar3 = new JButton("Buscar");
		btnBuscar3.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/busqueda.png")));
		btnBuscar3.setBackground(SystemColor.inactiveCaptionText);
		btnBuscar3.setBounds(445, 829, 89, 23);
		contentPane.add(btnBuscar3);
		
		tableConsultorio = new JTable();
		tableConsultorio.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableConsultorio.setBounds(35, 815, 509, 52);
		contentPane.add(tableConsultorio);
		
		JButton btnAgregar3 = new JButton("Agregar");
		btnAgregar3.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/agregar.png")));
		btnAgregar3.setBackground(SystemColor.inactiveCaptionText);
		btnAgregar3.setBounds(35, 883, 100, 23);
		contentPane.add(btnAgregar3);
		
		JButton btnModificar3 = new JButton("Modificar");
		btnModificar3.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar3.setBackground(SystemColor.inactiveCaptionText);
		btnModificar3.setBounds(154, 883, 100, 23);
		contentPane.add(btnModificar3);
		
		JButton btnEliminar3 = new JButton("Eliminar");
		btnEliminar3.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar3.setBackground(SystemColor.inactiveCaptionText);
		btnEliminar3.setBounds(264, 883, 100, 23);
		contentPane.add(btnEliminar3);
		
		JButton btnGrabar3 = new JButton("Grabar");
		btnGrabar3.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar3.setBackground(SystemColor.inactiveCaptionText);
		btnGrabar3.setBounds(379, 883, 100, 23);
		contentPane.add(btnGrabar3);
		
		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(SystemColor.inactiveCaptionText);
		btnSalir.setBounds(467, 30, 135, 35);
		contentPane.add(btnSalir);
		
		JButton btnLimpiar1 = new JButton("Limpiar");
		btnLimpiar1.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/escoba.png")));
		btnLimpiar1.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar1.setBounds(488, 309, 100, 23);
		contentPane.add(btnLimpiar1);
		
		JButton btnLimpiar3 = new JButton("Limpiar");
		btnLimpiar3.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/escoba.png")));
		btnLimpiar3.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar3.setBounds(488, 883, 100, 23);
		contentPane.add(btnLimpiar3);
		
		JButton btnLimpiar2 = new JButton("Limpiar");
		btnLimpiar2.setIcon(new ImageIcon(Mantenimiento.class.getResource("/IMG/escoba.png")));
		btnLimpiar2.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar2.setBounds(488, 627, 100, 23);
		contentPane.add(btnLimpiar2);

	}
}
