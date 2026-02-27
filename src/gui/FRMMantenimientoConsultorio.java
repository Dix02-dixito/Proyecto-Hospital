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
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FRMMantenimientoConsultorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtUbicacion;
	private JTextField txtPiso;
	private JTextField txtEstado;
	private JTextField txtCodigo;
	private JTable tableConsultorios;
	private JTextField txtBuscar;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMMantenimientoConsultorio frame = new FRMMantenimientoConsultorio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FRMMantenimientoConsultorio() {

		setTitle("Mantenimiento Consultorios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoConsultorio.class.getResource("/IMG/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TITULO
		JLabel lblTitulo = new JLabel("Mantenimiento Consultorios");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitulo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/logo.png")));
		lblTitulo.setBounds(0, 5, 378, 57);
		contentPane.add(lblTitulo);

		// BOTONES
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/agregar.png")));
		btnAgregar.setBackground(new Color(0, 128, 128));
		btnAgregar.setBounds(10, 590, 100, 30);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar.setBackground(new Color(0, 128, 128));
		btnModificar.setBounds(120, 590, 100, 30);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(569, 590, 100, 30);
		contentPane.add(btnEliminar);

		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar.setBackground(new Color(0, 128, 128));
		btnGrabar.setBounds(230, 590, 100, 30);
		contentPane.add(btnGrabar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/escoba.png")));
		btnLimpiar.setBackground(new Color(128, 128, 0));
		btnLimpiar.setBounds(459, 590, 100, 30);
		contentPane.add(btnLimpiar);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setForeground(new Color(128, 0, 0));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(new Color(220, 220, 220));
		btnSalir.setBounds(549, 14, 145, 35);
		contentPane.add(btnSalir);

		// TABLA
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 406, 636, 137);
		contentPane.add(scrollPane);

		tableConsultorios = new JTable();
		tableConsultorios.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Nombre", "Ubicacion", "Piso", "Estado", "Codigo"
			}
		));
		scrollPane.setViewportView(tableConsultorios);
								
								JPanel panelMantenimientoConsultorio = new JPanel();
								panelMantenimientoConsultorio.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new LineBorder(new Color(0, 255, 255))));
								panelMantenimientoConsultorio.setBackground(new Color(220, 220, 220));
								panelMantenimientoConsultorio.setBounds(10, 69, 659, 282);
								contentPane.add(panelMantenimientoConsultorio);
								panelMantenimientoConsultorio.setLayout(null);
								
										JLabel lblSubTitulo = new JLabel("Modificacion / Agregar");
										lblSubTitulo.setForeground(new Color(0, 128, 128));
										lblSubTitulo.setBackground(new Color(0, 128, 128));
										lblSubTitulo.setBounds(10, 11, 260, 27);
										panelMantenimientoConsultorio.add(lblSubTitulo);
										lblSubTitulo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/mantenimiento.png")));
										lblSubTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
										
												// CAMPOS
												JLabel lblNombre = new JLabel("Nombre del Consultorio");
												lblNombre.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/consultorio-medico.png")));
												lblNombre.setBounds(12, 52, 174, 17);
												panelMantenimientoConsultorio.add(lblNombre);
												lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
												
														txtNombre = new JTextField();
														txtNombre.setBounds(10, 71, 200, 25);
														panelMantenimientoConsultorio.add(txtNombre);
														
																txtUbicacion = new JTextField();
																txtUbicacion.setBounds(222, 71, 200, 25);
																panelMantenimientoConsultorio.add(txtUbicacion);
																
																		JLabel lblUbicacion = new JLabel("Ubicacion");
																		lblUbicacion.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/ubicacion.png")));
																		lblUbicacion.setBounds(224, 52, 100, 17);
																		panelMantenimientoConsultorio.add(lblUbicacion);
																		lblUbicacion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																		
																				JLabel lblEstado = new JLabel("Estado");
																				lblEstado.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/estado.png")));
																				lblEstado.setBounds(10, 107, 60, 27);
																				panelMantenimientoConsultorio.add(lblEstado);
																				lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																				
																						txtPiso = new JTextField();
																						txtPiso.setBounds(432, 73, 200, 25);
																						panelMantenimientoConsultorio.add(txtPiso);
																						
																								JLabel lblPiso = new JLabel("Piso");
																								lblPiso.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/NumeroPiso.png")));
																								lblPiso.setBounds(434, 49, 46, 27);
																								panelMantenimientoConsultorio.add(lblPiso);
																								lblPiso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																								
																										txtEstado = new JTextField();
																										txtEstado.setBounds(10, 129, 100, 25);
																										panelMantenimientoConsultorio.add(txtEstado);
																										
																												JLabel lblCodigo = new JLabel("Codigo");
																												lblCodigo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/Codigo.png")));
																												lblCodigo.setBounds(136, 112, 76, 17);
																												panelMantenimientoConsultorio.add(lblCodigo);
																												lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
																												
																														txtCodigo = new JTextField();
																														txtCodigo.setBounds(134, 129, 100, 25);
																														panelMantenimientoConsultorio.add(txtCodigo);
																														
																														JPanel panel = new JPanel();
																														panel.setBounds(20, 209, 612, 62);
																														panelMantenimientoConsultorio.add(panel);
																														panel.setBorder(new LineBorder(new Color(0, 128, 128), 3));
																														panel.setLayout(null);
																														
																																JButton btnBuscar = new JButton("Buscar");
																																btnBuscar.addActionListener(new ActionListener() {
																																	public void actionPerformed(ActionEvent e) {
																																	}
																																});
																																btnBuscar.setForeground(new Color(128, 128, 0));
																																btnBuscar.setBounds(489, 17, 100, 30);
																																panel.add(btnBuscar);
																																btnBuscar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/busqueda.png")));
																																btnBuscar.setBackground(SystemColor.info);
																																
																																		txtBuscar = new JTextField();
																																		txtBuscar.setBounds(195, 20, 254, 25);
																																		panel.add(txtBuscar);
																																		txtBuscar.setBackground(SystemColor.scrollbar);
																																		
																																		JRadioButton rdbtnNewRadioButton = new JRadioButton("Codigo");
																																		rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
																																		rdbtnNewRadioButton.setBounds(90, 20, 69, 23);
																																		panel.add(rdbtnNewRadioButton);
																																		
																																				JRadioButton rdbtnNombre = new JRadioButton("Nombre");
																																				rdbtnNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
																																				rdbtnNombre.setBounds(6, 20, 80, 23);
																																				panel.add(rdbtnNombre);
																																				buttonGroup.add(rdbtnNombre);
																																				
																																						JSeparator separator = new JSeparator();
																																						separator.setBounds(35, 36, 260, 2);
																																						panelMantenimientoConsultorio.add(separator);
																																						
																																								// CONSULTA
																																								JLabel lblConsulta = new JLabel("Consulta Consultorio");
																																								lblConsulta.setForeground(new Color(0, 128, 128));
																																								lblConsulta.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/consulta.png")));
																																								lblConsulta.setFont(new Font("Segoe UI", Font.BOLD, 14));
																																								lblConsulta.setBounds(10, 178, 186, 27);
																																								panelMantenimientoConsultorio.add(lblConsulta);
																																								
																																								JPanel panelJtable = new JPanel();
																																								panelJtable.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new LineBorder(new Color(0, 128, 128))));
																																								panelJtable.setBackground(new Color(220, 220, 220));
																																								panelJtable.setBounds(10, 371, 659, 192);
																																								contentPane.add(panelJtable);
																																								panelJtable.setLayout(null);
																																								
																																								JLabel lblListaCoNSULTORIOS = new JLabel("Lista de Consultorios");
																																								lblListaCoNSULTORIOS.setBounds(10, 8, 166, 24);
																																								lblListaCoNSULTORIOS.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/agregar (1).png")));
																																								lblListaCoNSULTORIOS.setForeground(new Color(0, 128, 128));
																																								lblListaCoNSULTORIOS.setFont(new Font("Segoe UI", Font.BOLD, 14));
																																								panelJtable.add(lblListaCoNSULTORIOS);
	}
}
