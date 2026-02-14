package gui;

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
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FRMConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblCitas;
	private JTextField txtFecha;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FRMConsulta frame = new FRMConsulta();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FRMConsulta() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMConsulta.class.getResource("/IMG/icon.png")));
		setTitle("Consulta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Hospital Hermilio");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		lblTitulo.setIcon(new ImageIcon(FRMConsulta.class.getResource("/IMG/icon.png")));
		lblTitulo.setBounds(0, 0, 153, 35);
		contentPane.add(lblTitulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 33, 244, 2);
		contentPane.add(separator);

		JLabel lblConsulta = new JLabel("Consulta:");
		lblConsulta.setFont(new Font("SimSun", Font.BOLD, 14));
		lblConsulta.setBounds(10, 40, 68, 14);
		contentPane.add(lblConsulta);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setIcon(new ImageIcon(FRMConsulta.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(SystemColor.inactiveCaptionText);
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setBounds(488, 11, 135, 23);
		contentPane.add(btnSalir);

		JLabel lblFiltros = new JLabel("Filtros:");
		lblFiltros.setFont(new Font("SimSun", Font.BOLD, 14));
		lblFiltros.setBounds(10, 116, 94, 14);
		contentPane.add(lblFiltros);

		JLabel lblListadodeCitas = new JLabel("Listado de Citas:");
		lblListadodeCitas.setFont(new Font("SimSun", Font.BOLD, 14));
		lblListadodeCitas.setBounds(10, 248, 153, 14);
		contentPane.add(lblListadodeCitas);

		JScrollPane scrollCitas = new JScrollPane();
		scrollCitas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCitas.setBounds(10, 262, 614, 203);
		contentPane.add(scrollCitas);

		tblCitas = new JTable();
		tblCitas.setEnabled(false);
		tblCitas.setRowSelectionAllowed(false);
		tblCitas.setModel(new DefaultTableModel(
			new Object[17][7],
			new String[] {"N° Cita", "Paciente", "Medico", "Consultorio", "Fecha", "Hora", "Estado"}
		));

		scrollCitas.setViewportView(tblCitas);
		tblCitas.setBorder(new LineBorder(Color.BLACK));

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(new ImageIcon(FRMConsulta.class.getResource("/IMG/busqueda.png")));
		btnConsultar.setBackground(SystemColor.inactiveCaptionText);
		btnConsultar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnConsultar.setBounds(10, 486, 110, 25);
		contentPane.add(btnConsultar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMConsulta.class.getResource("/IMG/escoba.png")));
		btnLimpiar.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnLimpiar.setBounds(130, 486, 110, 25);
		contentPane.add(btnLimpiar);

		JPanel panelConsulta = new JPanel();
		panelConsulta.setBorder(new LineBorder(Color.BLACK, 1, true));
		panelConsulta.setBounds(10, 60, 614, 35);
		panelConsulta.setLayout(null);
		contentPane.add(panelConsulta);

		JRadioButton rdbtnPorPaciente = new JRadioButton("Por Paciente");
		rdbtnPorPaciente.setBounds(6, 7, 109, 23);
		panelConsulta.add(rdbtnPorPaciente);

		JRadioButton rdbtnPorMedico = new JRadioButton("Por Medico");
		rdbtnPorMedico.setBounds(129, 7, 109, 23);
		panelConsulta.add(rdbtnPorMedico);

		JRadioButton rdbtnPorConsultorio = new JRadioButton("Por Consultorio");
		rdbtnPorConsultorio.setBounds(254, 7, 109, 23);
		panelConsulta.add(rdbtnPorConsultorio);

		JRadioButton rdbtnPorFecha = new JRadioButton("Por Fecha");
		rdbtnPorFecha.setBounds(380, 7, 109, 23);
		panelConsulta.add(rdbtnPorFecha);

		JPanel panelFiltros = new JPanel();
		panelFiltros.setLayout(null);
		panelFiltros.setBorder(new LineBorder(Color.BLACK, 1, true));
		panelFiltros.setBounds(10, 136, 614, 83);
		contentPane.add(panelFiltros);

		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Serif", Font.BOLD, 12));
		lblPaciente.setBounds(10, 11, 58, 14);
		panelFiltros.add(lblPaciente);

		JComboBox cmbxPaciente = new JComboBox();
		cmbxPaciente.setBounds(108, 7, 185, 25);
		panelFiltros.add(cmbxPaciente);

		JComboBox cmbxConsultorio = new JComboBox();
		cmbxConsultorio.setBounds(108, 47, 185, 25);
		panelFiltros.add(cmbxConsultorio);

		JLabel lblConsultorio = new JLabel("Consultorio:");
		lblConsultorio.setFont(new Font("Serif", Font.BOLD, 12));
		lblConsultorio.setBounds(10, 51, 67, 14);
		panelFiltros.add(lblConsultorio);

		JComboBox cmbxMedico = new JComboBox();
		cmbxMedico.setBounds(404, 7, 185, 25);
		panelFiltros.add(cmbxMedico);

		JLabel lblMedico = new JLabel("Medico:");
		lblMedico.setFont(new Font("Serif", Font.BOLD, 12));
		lblMedico.setBounds(327, 11, 58, 14);
		panelFiltros.add(lblMedico);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Serif", Font.BOLD, 12));
		lblFecha.setBounds(327, 51, 58, 14);
		panelFiltros.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(404, 49, 185, 25);
		panelFiltros.add(txtFecha);
		txtFecha.setColumns(10);
	}
}
