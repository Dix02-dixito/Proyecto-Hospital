package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class FRMMantenimientoCitas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCita;
	private JTextField txtMotivo;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTable tblListaCitas;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMMantenimientoCitas frame = new FRMMantenimientoCitas();
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
	public FRMMantenimientoCitas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoCitas.class.getResource("/img/icon.png")));
		setTitle("Mantenimiento Citas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 813);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Mantenimiento Citas");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/logo.png")));
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitulo.setBounds(0, 5, 288, 57);
		contentPane.add(lblTitulo);
		
		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setVerticalAlignment(SwingConstants.TOP);
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/salida.png")));
		btnSalir.setForeground(new Color(128, 0, 0));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setBackground(new Color(220, 220, 220));
		btnSalir.setBounds(586, 27, 150, 30);
		contentPane.add(btnSalir);
		
		JPanel panelRegistrarCitas = new JPanel();
		panelRegistrarCitas.setLayout(null);
		panelRegistrarCitas.setBorder(new CompoundBorder(
		                new LineBorder(new Color(128, 128, 128)),
		                new LineBorder(new Color(0, 206, 209))
		        ));
		panelRegistrarCitas.setBackground(new Color(220, 220, 220));
		panelRegistrarCitas.setBounds(20, 73, 686, 406);
		contentPane.add(panelRegistrarCitas);
		
		JLabel lblModificacion = new JLabel("Modificacion");
		lblModificacion.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/mantenimiento.png")));
		lblModificacion.setForeground(new Color(0, 128, 128));
		lblModificacion.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblModificacion.setBounds(10, 11, 153, 27);
		panelRegistrarCitas.add(lblModificacion);
		
		JLabel lblCita_1 = new JLabel("N° Cita");
		lblCita_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCita_1.setBounds(12, 49, 62, 14);
		panelRegistrarCitas.add(lblCita_1);
		
		txtCita = new JTextField();
		txtCita.setEnabled(true);
		txtCita.setEditable(false);
		txtCita.setColumns(10);
		txtCita.setBackground(new Color(245, 255, 250));
		txtCita.setBounds(10, 68, 100, 25);
		panelRegistrarCitas.add(txtCita);
		
		JLabel lblPaciente_1 = new JLabel("Paciente");
		lblPaciente_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/NombreApellidos.png")));
		lblPaciente_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPaciente_1.setBounds(144, 48, 90, 17);
		panelRegistrarCitas.add(lblPaciente_1);
		
		JComboBox<String> cbPaciente_1 = new JComboBox<String>();
		cbPaciente_1.setEnabled(false);
		cbPaciente_1.setEditable(true);
		cbPaciente_1.setBounds(142, 69, 250, 25);
		panelRegistrarCitas.add(cbPaciente_1);
		
		JLabel lblMedico_1 = new JLabel("Medico");
		lblMedico_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/EspecialidadMedico.png")));
		lblMedico_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMedico_1.setBounds(424, 48, 90, 16);
		panelRegistrarCitas.add(lblMedico_1);
		
		JComboBox<String> cbMedico_1 = new JComboBox<String>();
		cbMedico_1.setEnabled(false);
		cbMedico_1.setEditable(true);
		cbMedico_1.setBounds(422, 68, 250, 25);
		panelRegistrarCitas.add(cbMedico_1);
		
		JLabel lblConsultorio_1 = new JLabel("Consultorio");
		lblConsultorio_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/consultorio-medico.png")));
		lblConsultorio_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblConsultorio_1.setBounds(12, 114, 120, 24);
		panelRegistrarCitas.add(lblConsultorio_1);
		
		JComboBox<String> cbConsultorio_1 = new JComboBox<String>();
		cbConsultorio_1.setEnabled(false);
		cbConsultorio_1.setEditable(true);
		cbConsultorio_1.setBackground(new Color(245, 255, 250));
		cbConsultorio_1.setBounds(10, 137, 200, 25);
		panelRegistrarCitas.add(cbConsultorio_1);
		
		JLabel lblMotivo_1 = new JLabel("Motivo");
		lblMotivo_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/Motivo.png")));
		lblMotivo_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMotivo_1.setBounds(246, 114, 90, 24);
		panelRegistrarCitas.add(lblMotivo_1);
		
		txtMotivo = new JTextField();
		txtMotivo.setEnabled(false);
		txtMotivo.setColumns(10);
		txtMotivo.setBackground(new Color(245, 255, 250));
		txtMotivo.setBounds(244, 137, 428, 25);
		panelRegistrarCitas.add(txtMotivo);
		
		JLabel lblHorario_1 = new JLabel(" Horario");
		lblHorario_1.setForeground(new Color(0, 128, 128));
		lblHorario_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHorario_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/calendar.png")));
		lblHorario_1.setBounds(10, 192, 80, 20);
		panelRegistrarCitas.add(lblHorario_1);
		
		JSeparator separator2_1 = new JSeparator();
		separator2_1.setForeground(UIManager.getColor("Button.darkShadow"));
		separator2_1.setBackground(UIManager.getColor("Button.darkShadow"));
		separator2_1.setBounds(10, 212, 226, 16);
		panelRegistrarCitas.add(separator2_1);
		
		JLabel lblFecha_1 = new JLabel("Fecha");
		lblFecha_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/calendario.png")));
		lblFecha_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFecha_1.setBounds(12, 225, 81, 25);
		panelRegistrarCitas.add(lblFecha_1);
		
		txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setColumns(10);
		txtFecha.setBackground(new Color(245, 255, 250));
		txtFecha.setBounds(10, 250, 200, 25);
		panelRegistrarCitas.add(txtFecha);
		
		JLabel lblHora_1 = new JLabel("Hora");
		lblHora_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/reloj.png")));
		lblHora_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblHora_1.setBounds(246, 225, 58, 25);
		panelRegistrarCitas.add(lblHora_1);
		
		txtHora = new JTextField();
		txtHora.setEnabled(false);
		txtHora.setColumns(10);
		txtHora.setBackground(new Color(245, 255, 250));
		txtHora.setBounds(244, 250, 200, 25);
		panelRegistrarCitas.add(txtHora);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 128, 128), 3));
		panel.setBounds(31, 328, 612, 60);
		panelRegistrarCitas.add(panel);
		
		JRadioButton rdbtnDniPaciente = new JRadioButton("DNI Paciente");
		rdbtnDniPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnDniPaciente.setBounds(6, 20, 100, 23);
		panel.add(rdbtnDniPaciente);
		
		JRadioButton rdbtnApellidosPaciente = new JRadioButton("Apellidos Paciente");
		rdbtnApellidosPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnApellidosPaciente.setBounds(119, 20, 134, 23);
		panel.add(rdbtnApellidosPaciente);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(SystemColor.scrollbar);
		textField.setBounds(259, 20, 216, 25);
		panel.add(textField);
		
		JButton btnBuscar1 = new JButton("Buscar");
		btnBuscar1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/busqueda.png")));
		btnBuscar1.setForeground(new Color(128, 128, 0));
		btnBuscar1.setBackground(SystemColor.info);
		btnBuscar1.setBounds(502, 17, 100, 30);
		panel.add(btnBuscar1);
		
		JLabel lblConsultaCitas = new JLabel("Consulta Citas");
		lblConsultaCitas.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/consulta.png")));
		lblConsultaCitas.setForeground(new Color(0, 128, 128));
		lblConsultaCitas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblConsultaCitas.setBounds(10, 297, 148, 31);
		panelRegistrarCitas.add(lblConsultaCitas);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBorder(new CompoundBorder(
		                new LineBorder(new Color(128, 128, 128)),
		                new LineBorder(new Color(0, 128, 128))
		        ));
		panelTabla.setBackground(new Color(220, 220, 220));
		panelTabla.setBounds(20, 502, 686, 221);
		contentPane.add(panelTabla);
		
		JLabel lblListaDeCitas = new JLabel("Lista de Citas");
		lblListaDeCitas.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/agregar (1).png")));
		lblListaDeCitas.setForeground(new Color(0, 128, 128));
		lblListaDeCitas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblListaDeCitas.setBounds(12, 7, 250, 24);
		panelTabla.add(lblListaDeCitas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 35, 664, 175);
		panelTabla.add(scrollPane);
		
		tblListaCitas = new JTable();
		tblListaCitas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N Cita", "Paciente", "Medico", "Consultorio", "Motivo", "Fecha", "Hora"
			}
		));
		tblListaCitas.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		scrollPane.setViewportView(tblListaCitas);
		
		JButton btnModificar1 = new JButton("Modificar");
		btnModificar1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar1.setForeground(new Color(0, 0, 128));
		btnModificar1.setBackground(new Color(0, 128, 128));
		btnModificar1.setBounds(20, 735, 113, 30);
		contentPane.add(btnModificar1);
		
		JButton btnGrabar1 = new JButton("Guardar");
		btnGrabar1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar1.setForeground(new Color(0, 0, 128));
		btnGrabar1.setBackground(new Color(0, 128, 128));
		btnGrabar1.setBounds(143, 735, 100, 30);
		contentPane.add(btnGrabar1);
		
		JButton btnLimpiar1 = new JButton("Limpiar");
		btnLimpiar1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/escoba.png")));
		btnLimpiar1.setForeground(new Color(0, 0, 128));
		btnLimpiar1.setBackground(new Color(128, 128, 0));
		btnLimpiar1.setBounds(484, 735, 100, 30);
		contentPane.add(btnLimpiar1);
		
		JButton btnEliminar1 = new JButton("Eliminar");
		btnEliminar1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar1.setForeground(new Color(0, 0, 128));
		btnEliminar1.setBackground(new Color(128, 0, 0));
		btnEliminar1.setBounds(594, 735, 100, 30);
		contentPane.add(btnEliminar1);

	}
}
