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
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class FRMRegistroCitas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNcita;
	private JTextField txtEstado;
	private JTextField txtfecha;
	private JTextField txtHora;
	private JTable tbldatosModificados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMRegistroCitas frame = new FRMRegistroCitas();
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
	public FRMRegistroCitas() {
		setTitle("Registro de Citas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMRegistroCitas.class.getResource("/IMG/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 586);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hospital Hermilio");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		lblNewLabel.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/icon.png")));
		lblNewLabel.setBounds(10, 11, 160, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblCita = new JLabel("N° Cita");
		lblCita.setBounds(20, 60, 34, 14);
		contentPane.add(lblCita);
		
		txtNcita = new JTextField();
		txtNcita.setBackground(SystemColor.scrollbar);
		txtNcita.setEnabled(false);
		txtNcita.setEditable(false);
		txtNcita.setBounds(67, 57, 100, 20);
		contentPane.add(txtNcita);
		txtNcita.setColumns(10);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/user.png")));
		lblPaciente.setBounds(203, 57, 64, 17);
		contentPane.add(lblPaciente);
		
		JLabel lblMedico = new JLabel("Medico");
		lblMedico.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/user-md.png")));
		lblMedico.setBounds(496, 58, 64, 18);
		contentPane.add(lblMedico);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(20, 121, 359, 2);
		contentPane.add(separator1);
		
		JLabel lblOtrosDatos = new JLabel("Otros Datos");
		lblOtrosDatos.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/menu-dots.png")));
		lblOtrosDatos.setBounds(20, 104, 100, 14);
		contentPane.add(lblOtrosDatos);
		
		JLabel lblConsultorio = new JLabel("Consultorio");
		lblConsultorio.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/building-user.png")));
		lblConsultorio.setBounds(20, 134, 100, 24);
		contentPane.add(lblConsultorio);
		
		JComboBox cbpaciente = new JComboBox();
		cbpaciente.setBounds(277, 56, 200, 20);
		contentPane.add(cbpaciente);
		
		JComboBox cbmedico = new JComboBox();
		cbmedico.setBounds(554, 56, 200, 20);
		contentPane.add(cbmedico);
		
		JComboBox cbconsultorio = new JComboBox();
		cbconsultorio.setBackground(SystemColor.scrollbar);
		cbconsultorio.setBounds(20, 169, 200, 20);
		contentPane.add(cbconsultorio);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/punto-pendiente.png")));
		lblEstado.setBounds(279, 134, 76, 24);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setBackground(SystemColor.scrollbar);
		txtEstado.setEditable(false);
		txtEstado.setBounds(277, 169, 150, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(20, 236, 359, 2);
		contentPane.add(separator2);
		
		JLabel lblHorario = new JLabel(" Horario");
		lblHorario.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/calendar.png")));
		lblHorario.setBounds(20, 211, 64, 20);
		contentPane.add(lblHorario);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/calendario.png")));
		lblFecha.setBounds(20, 249, 81, 25);
		contentPane.add(lblFecha);
		
		txtfecha = new JTextField();
		txtfecha.setBackground(SystemColor.scrollbar);
		txtfecha.setBounds(20, 280, 200, 20);
		contentPane.add(txtfecha);
		txtfecha.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/reloj.png")));
		lblHora.setBounds(257, 249, 58, 25);
		contentPane.add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setBackground(SystemColor.scrollbar);
		txtHora.setColumns(10);
		txtHora.setBounds(255, 280, 200, 20);
		contentPane.add(txtHora);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 342, 696, 152);
		contentPane.add(scrollPane);
		
		tbldatosModificados = new JTable();
		tbldatosModificados.setRowSelectionAllowed(false);
		tbldatosModificados.setEnabled(false);
		tbldatosModificados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"N\u00B0 Cita", "Paciente", "Medico", "Consultorio", "Fecha", "Hora", "Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Object.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tbldatosModificados);
		tbldatosModificados.setBorder(new LineBorder(SystemColor.desktop));
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/agregar.png")));
		btnNuevo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnNuevo.setBackground(SystemColor.inactiveCaptionText);
		btnNuevo.setBounds(30, 505, 125, 25);
		contentPane.add(btnNuevo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGuardar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnGuardar.setBackground(SystemColor.inactiveCaptionText);
		btnGuardar.setBounds(165, 505, 125, 25);
		contentPane.add(btnGuardar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBackground(SystemColor.inactiveCaptionText);
		btnModificar.setBounds(300, 505, 125, 25);
		contentPane.add(btnModificar);
		
		JButton btnCancelarCita = new JButton("Cancelar Cita");
		btnCancelarCita.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/cruz-pequena.png")));
		btnCancelarCita.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnCancelarCita.setBackground(SystemColor.inactiveCaptionText);
		btnCancelarCita.setBounds(435, 505, 146, 25);
		contentPane.add(btnCancelarCita);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/escoba.png")));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnLimpiar.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar.setBounds(591, 505, 125, 25);
		contentPane.add(btnLimpiar);
		
		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/salida.png")));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setBackground(SystemColor.inactiveCaptionText);
		btnSalir.setBounds(621, 14, 150, 30);
		contentPane.add(btnSalir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 44, 258, 2);
		contentPane.add(separator);
		
		JLabel lblDatosModificados = new JLabel("Datos Modificados:");
		lblDatosModificados.setBounds(30, 311, 110, 14);
		contentPane.add(lblDatosModificados);

	}
}
