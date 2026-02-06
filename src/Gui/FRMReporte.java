package Gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class FRMReporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblReporte;
	private JTextField txtFecha;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FRMReporte frame = new FRMReporte();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FRMReporte() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMReporte.class.getResource("/IMG/icon.png")));
		setTitle("Reporte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Hospital Hermilio");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		lblTitulo.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/icon.png")));
		lblTitulo.setBounds(0, 0, 170, 35);
		contentPane.add(lblTitulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 33, 260, 2);
		contentPane.add(separator);

		JLabel lblReporte = new JLabel("Reporte:");
		lblReporte.setFont(new Font("SimSun", Font.BOLD, 14));
		lblReporte.setBounds(10, 40, 80, 14);
		contentPane.add(lblReporte);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(SystemColor.inactiveCaptionText);
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setBounds(525, 11, 145, 23);
		contentPane.add(btnSalir);

		// Panel tipo de reporte
		JPanel panelTipoReporte = new JPanel();
		panelTipoReporte.setBorder(new LineBorder(Color.BLACK, 1, true));
		panelTipoReporte.setBounds(10, 60, 660, 60);
		panelTipoReporte.setLayout(null);
		contentPane.add(panelTipoReporte);

		JRadioButton rdbtnPorPaciente = new JRadioButton("Por Paciente");
		rdbtnPorPaciente.setBounds(6, 7, 120, 23);
		panelTipoReporte.add(rdbtnPorPaciente);

		JRadioButton rdbtnPorMedico = new JRadioButton("Por Médico");
		rdbtnPorMedico.setBounds(140, 7, 120, 23);
		panelTipoReporte.add(rdbtnPorMedico);

		JRadioButton rdbtnPorConsultorio = new JRadioButton("Por Consultorio");
		rdbtnPorConsultorio.setBounds(280, 7, 140, 23);
		panelTipoReporte.add(rdbtnPorConsultorio);

		JRadioButton rdbtnPorFecha = new JRadioButton("Por Fecha");
		rdbtnPorFecha.setBounds(440, 7, 120, 23);
		panelTipoReporte.add(rdbtnPorFecha);

		JRadioButton rdbtnPendientes = new JRadioButton("Pacientes con citas pendientes");
		rdbtnPendientes.setBounds(6, 33, 230, 23);
		panelTipoReporte.add(rdbtnPendientes);

		JRadioButton rdbtnAgendaDia = new JRadioButton("Agenda del día");
		rdbtnAgendaDia.setBounds(280, 33, 140, 23);
		panelTipoReporte.add(rdbtnAgendaDia);

		// Panel filtros
		JPanel panelFiltros = new JPanel();
		panelFiltros.setBorder(new LineBorder(Color.BLACK, 1, true));
		panelFiltros.setBounds(10, 130, 660, 80);
		panelFiltros.setLayout(null);
		contentPane.add(panelFiltros);

		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Serif", Font.BOLD, 12));
		lblPaciente.setBounds(10, 12, 70, 14);
		panelFiltros.add(lblPaciente);

		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(90, 8, 180, 25);
		panelFiltros.add(cmbPaciente);

		JLabel lblMedico = new JLabel("Médico:");
		lblMedico.setFont(new Font("Serif", Font.BOLD, 12));
		lblMedico.setBounds(300, 12, 70, 14);
		panelFiltros.add(lblMedico);

		JComboBox cmbMedico = new JComboBox();
		cmbMedico.setBounds(380, 8, 180, 25);
		panelFiltros.add(cmbMedico);

		JLabel lblConsultorio = new JLabel("Consultorio:");
		lblConsultorio.setFont(new Font("Serif", Font.BOLD, 12));
		lblConsultorio.setBounds(10, 47, 80, 14);
		panelFiltros.add(lblConsultorio);

		JComboBox cmbConsultorio = new JComboBox();
		cmbConsultorio.setBounds(90, 43, 180, 25);
		panelFiltros.add(cmbConsultorio);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Serif", Font.BOLD, 12));
		lblFecha.setBounds(300, 47, 70, 14);
		panelFiltros.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(380, 43, 180, 25);
		panelFiltros.add(txtFecha);

		// Tabla reporte
		JLabel lblListado = new JLabel("Resultado del Reporte:");
		lblListado.setFont(new Font("SimSun", Font.BOLD, 14));
		lblListado.setBounds(10, 220, 200, 14);
		contentPane.add(lblListado);

		JScrollPane scrollReporte = new JScrollPane();
		scrollReporte.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollReporte.setBounds(10, 235, 660, 230);
		contentPane.add(scrollReporte);

		tblReporte = new JTable();
		tblReporte.setEnabled(false);
		tblReporte.setRowSelectionAllowed(false);
		tblReporte.setModel(new DefaultTableModel(
			new Object[15][7],
			new String[] {"Paciente", "Médico", "Consultorio", "Fecha", "Hora", "Estado", "Observación"}
		));
		tblReporte.getTableHeader().setReorderingAllowed(false);
		tblReporte.setBorder(new LineBorder(Color.BLACK));

		scrollReporte.setViewportView(tblReporte);

		JButton btnGenerar = new JButton("Generar");
		btnGenerar.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/busqueda.png")));
		btnGenerar.setBackground(SystemColor.inactiveCaptionText);
		btnGenerar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnGenerar.setBounds(10, 485, 110, 25);
		contentPane.add(btnGenerar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/escoba.png")));
		btnLimpiar.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnLimpiar.setBounds(130, 485, 110, 25);
		contentPane.add(btnLimpiar);
	}
}
