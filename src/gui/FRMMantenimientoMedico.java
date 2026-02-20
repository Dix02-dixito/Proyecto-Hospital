package gui;

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
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;

public class FRMMantenimientoMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCMP;
	private JTextField txtEspecialidad;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtCodigo;
	private JTable tableMedico;
	private JTextField txtBuscar;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMMantenimientoMedico frame = new FRMMantenimientoMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FRMMantenimientoMedico() {

		setTitle("Mantenimiento Médicos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoMedico.class.getResource("/IMG/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TITULO
		JLabel lblTitulo = new JLabel("Hospital Hermilio");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		lblTitulo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/icon.png")));
		lblTitulo.setBounds(10, 11, 160, 35);
		contentPane.add(lblTitulo);

		JLabel lblSubTitulo = new JLabel("Mantenimiento Médicos");
		lblSubTitulo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/settings (2).png")));
		lblSubTitulo.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblSubTitulo.setBounds(20, 60, 222, 27);
		contentPane.add(lblSubTitulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 82, 214, 2);
		contentPane.add(separator);

		// CAMPOS
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblNombre.setBounds(10, 98, 60, 17);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(70, 98, 150, 20);
		contentPane.add(txtNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblApellidos.setBounds(249, 98, 60, 14);
		contentPane.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(319, 95, 150, 20);
		contentPane.add(txtApellidos);

		JLabel lblCMP = new JLabel("CMP");
		lblCMP.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCMP.setBounds(10, 136, 46, 27);
		contentPane.add(lblCMP);

		txtCMP = new JTextField();
		txtCMP.setBounds(70, 136, 100, 20);
		contentPane.add(txtCMP);

		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEspecialidad.setBounds(208, 136, 80, 27);
		contentPane.add(lblEspecialidad);

		txtEspecialidad = new JTextField();
		txtEspecialidad.setBounds(290, 136, 150, 20);
		contentPane.add(txtEspecialidad);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblTelefono.setBounds(369, 174, 60, 27);
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(434, 174, 100, 20);
		contentPane.add(txtTelefono);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCorreo.setBounds(10, 174, 46, 27);
		contentPane.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(70, 174, 289, 20);
		contentPane.add(txtCorreo);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCodigo.setBounds(379, 98, 50, 17);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(434, 98, 100, 20);
		contentPane.add(txtCodigo);

		JLabel lblConsulta = new JLabel("Consulta");
		lblConsulta.setBounds(33, 216, 60, 14);
		contentPane.add(lblConsulta);

		// BOTONES
		JButton btnAgregar = new JButton("Nuevo");
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/agregar.png")));
		btnAgregar.setBackground(SystemColor.inactiveCaptionText);
		btnAgregar.setBounds(35, 309, 100, 23);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar.setBackground(SystemColor.inactiveCaptionText);
		btnModificar.setBounds(154, 309, 100, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar.setBackground(SystemColor.inactiveCaptionText);
		btnEliminar.setBounds(264, 309, 100, 23);
		contentPane.add(btnEliminar);

		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar.setBackground(SystemColor.inactiveCaptionText);
		btnGrabar.setBounds(379, 309, 100, 23);
		contentPane.add(btnGrabar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/escoba.png")));
		btnLimpiar.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar.setBounds(488, 309, 100, 23);
		contentPane.add(btnLimpiar);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(SystemColor.inactiveCaptionText);
		btnSalir.setBounds(467, 30, 135, 35);
		contentPane.add(btnSalir);

		// TABLA
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 362, 570, 137);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombres", "Apellidos", "CMP", "Especialidad", "Telefono", "Correo"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(42, 231, 492, 47);
		contentPane.add(panel);
		panel.setLayout(null);
		
				// CONSULTA
				JRadioButton rdbtnCMP = new JRadioButton("CMP");
				rdbtnCMP.setBounds(6, 7, 60, 23);
				panel.add(rdbtnCMP);
				buttonGroup.add(rdbtnCMP);
				
						JRadioButton rdbtnApellidos = new JRadioButton("Apellidos");
						rdbtnApellidos.setBounds(68, 7, 90, 23);
						panel.add(rdbtnApellidos);
						buttonGroup.add(rdbtnApellidos);
						
								txtBuscar = new JTextField();
								txtBuscar.setBounds(164, 8, 184, 20);
								panel.add(txtBuscar);
								txtBuscar.setBackground(SystemColor.scrollbar);
								
										JButton btnBuscar = new JButton("Buscar");
										btnBuscar.setBounds(374, 7, 89, 23);
										panel.add(btnBuscar);
										btnBuscar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/busqueda.png")));
										btnBuscar.setBackground(SystemColor.inactiveCaptionText);
	}
}
