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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

public class FRMMantenimientoMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCMP;
	private JTextField txtEspecialidad;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTable tableMedico;
	private JTextField txtBuscar;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;

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

		setTitle("Mantenimiento Medicos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoMedico.class.getResource("/IMG/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 723);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TITULO
		JLabel lblTitulo = new JLabel("Mantenimiento Medicos");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblTitulo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/logo.png")));
		lblTitulo.setBounds(0, 5, 331, 60);
		contentPane.add(lblTitulo);

		// BOTONES
		JButton btnAgregar = new JButton("Nuevo");
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/agregar.png")));
		btnAgregar.setBackground(new Color(0, 128, 128));
		btnAgregar.setBounds(20, 645, 100, 30);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar.setBackground(new Color(0, 128, 128));
		btnModificar.setBounds(130, 645, 109, 30);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(535, 645, 100, 30);
		contentPane.add(btnEliminar);

		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar.setBackground(new Color(0, 128, 128));
		btnGrabar.setBounds(249, 645, 100, 30);
		contentPane.add(btnGrabar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/escoba.png")));
		btnLimpiar.setBackground(new Color(128, 128, 0));
		btnLimpiar.setBounds(425, 645, 100, 30);
		contentPane.add(btnLimpiar);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setForeground(new Color(128, 0, 0));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(new Color(220, 220, 220));
		btnSalir.setBounds(500, 21, 167, 35);
		contentPane.add(btnSalir);
										
										JPanel panelModificacion = new JPanel();
										panelModificacion.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new LineBorder(new Color(0, 255, 255))));
										panelModificacion.setBackground(new Color(220, 220, 220));
										panelModificacion.setBounds(10, 70, 625, 335);
										contentPane.add(panelModificacion);
										panelModificacion.setLayout(null);
										
										JPanel panel = new JPanel();
										panel.setBounds(25, 253, 555, 60);
										panelModificacion.add(panel);
										panel.setBorder(new LineBorder(new Color(47, 79, 79), 3));
										panel.setLayout(null);
										
												// CONSULTA
												JRadioButton rdbtnCMP = new JRadioButton("CMP");
												rdbtnCMP.setFont(new Font("Segoe UI", Font.BOLD, 12));
												rdbtnCMP.setBounds(6, 18, 60, 23);
												panel.add(rdbtnCMP);
												buttonGroup.add(rdbtnCMP);
												
														JRadioButton rdbtnApellidos = new JRadioButton("Apellidos");
														rdbtnApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
														rdbtnApellidos.setBounds(68, 18, 90, 23);
														panel.add(rdbtnApellidos);
														buttonGroup.add(rdbtnApellidos);
														
																txtBuscar = new JTextField();
																txtBuscar.setBounds(164, 18, 260, 30);
																panel.add(txtBuscar);
																txtBuscar.setBackground(SystemColor.scrollbar);
																
																		JButton btnBuscar = new JButton("Buscar");
																		btnBuscar.setForeground(new Color(128, 128, 0));
																		btnBuscar.setBounds(434, 18, 100, 30);
																		panel.add(btnBuscar);
																		btnBuscar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/IMG/busqueda.png")));
																		btnBuscar.setBackground(SystemColor.info);
																		
																				txtCorreo = new JTextField();
																				txtCorreo.setBounds(220, 125, 200, 25);
																				panelModificacion.add(txtCorreo);
																				
																						JLabel lblConsulta = new JLabel("Consulta Medico");
																						lblConsulta.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/consulta.png")));
																						lblConsulta.setForeground(new Color(0, 128, 128));
																						lblConsulta.setFont(new Font("Segoe UI", Font.BOLD, 14));
																						lblConsulta.setBounds(10, 213, 156, 29);
																						panelModificacion.add(lblConsulta);
																						
																								txtTelefono = new JTextField();
																								txtTelefono.setBounds(430, 125, 150, 25);
																								panelModificacion.add(txtTelefono);
																								
																										JLabel lblTelefono = new JLabel("Telefono");
																										lblTelefono.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/Telefono.png")));
																										lblTelefono.setBounds(430, 104, 80, 23);
																										panelModificacion.add(lblTelefono);
																										lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																										
																												JLabel lblCorreo = new JLabel("Correo");
																												lblCorreo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/Correo.png")));
																												lblCorreo.setBounds(220, 102, 80, 27);
																												panelModificacion.add(lblCorreo);
																												lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																												
																														JLabel lblCMP = new JLabel("CMP");
																														lblCMP.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/identificacionMedico.png")));
																														lblCMP.setBounds(430, 51, 70, 19);
																														panelModificacion.add(lblCMP);
																														lblCMP.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																														
																																txtCMP = new JTextField();
																																txtCMP.setBounds(430, 70, 150, 25);
																																panelModificacion.add(txtCMP);
																																
																																		JLabel lblEspecialidad = new JLabel("Especialidad");
																																		lblEspecialidad.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/EspecialidadMedico.png")));
																																		lblEspecialidad.setBounds(10, 105, 119, 20);
																																		panelModificacion.add(lblEspecialidad);
																																		lblEspecialidad.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																																		
																																				txtEspecialidad = new JTextField();
																																				txtEspecialidad.setBounds(10, 125, 200, 25);
																																				panelModificacion.add(txtEspecialidad);
																																				
																																						txtApellidos = new JTextField();
																																						txtApellidos.setBounds(220, 70, 200, 25);
																																						panelModificacion.add(txtApellidos);
																																						
																																								txtNombre = new JTextField();
																																								txtNombre.setBounds(10, 70, 200, 25);
																																								panelModificacion.add(txtNombre);
																																								
																																										// CAMPOS
																																										JLabel lblNombre = new JLabel("Nombre Medico");
																																										lblNombre.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/NombreApellidos.png")));
																																										lblNombre.setBounds(10, 50, 119, 20);
																																										panelModificacion.add(lblNombre);
																																										lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																																										
																																												JLabel lblApellidos = new JLabel("Apellidos");
																																												lblApellidos.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/NombreApellidos.png")));
																																												lblApellidos.setBounds(220, 51, 84, 17);
																																												panelModificacion.add(lblApellidos);
																																												lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																																												
																																														JLabel lblSubTitulo = new JLabel("Modificacion / Agregar");
																																														lblSubTitulo.setHorizontalAlignment(SwingConstants.LEFT);
																																														lblSubTitulo.setForeground(new Color(0, 128, 128));
																																														lblSubTitulo.setBounds(10, 11, 222, 27);
																																														panelModificacion.add(lblSubTitulo);
																																														lblSubTitulo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/mantenimiento.png")));
																																														lblSubTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
																																														
																																																JSeparator separator = new JSeparator();
																																																separator.setBounds(25, 37, 214, 2);
																																																panelModificacion.add(separator);
																																																
																																																		JLabel lblCodigo = new JLabel("Codigo");
																																																		lblCodigo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/Codigo.png")));
																																																		lblCodigo.setBounds(12, 158, 91, 17);
																																																		panelModificacion.add(lblCodigo);
																																																		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																																																		
																																																		textField = new JTextField();
																																																		textField.setBounds(12, 177, 100, 25);
																																																		panelModificacion.add(textField);
																																																		textField.setColumns(10);
																																																		
																																																		JPanel panelListaMedicos = new JPanel();
																																																		panelListaMedicos.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new LineBorder(new Color(0, 128, 128))));
																																																		panelListaMedicos.setBackground(new Color(220, 220, 220));
																																																		panelListaMedicos.setBounds(10, 416, 625, 218);
																																																		contentPane.add(panelListaMedicos);
																																																				panelListaMedicos.setLayout(null);
																																																		
																																																				// TABLA
																																																				JScrollPane scrollPane = new JScrollPane();
																																																				scrollPane.setBounds(10, 37, 599, 170);
																																																				panelListaMedicos.add(scrollPane);
																																																				
																																																						table = new JTable();
																																																						table.setModel(new DefaultTableModel(
																																																							new Object[][] {
																																																							},
																																																							new String[] {
																																																								"Nombres", "Apellidos", "CMP", "Especialidad", "Telefono", "Correo"
																																																							}
																																																						));
																																																						scrollPane.setViewportView(table);
																																																						
																																																						JLabel lblListaDeMedicos = new JLabel("Lista de Medicos");
																																																						lblListaDeMedicos.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/agregar (1).png")));
																																																						lblListaDeMedicos.setForeground(new Color(0, 128, 128));
																																																						lblListaDeMedicos.setFont(new Font("Segoe UI", Font.BOLD, 14));
																																																						lblListaDeMedicos.setBounds(10, 8, 156, 29);
																																																						panelListaMedicos.add(lblListaDeMedicos);
	}
}
