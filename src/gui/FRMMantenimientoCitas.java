package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.CitaDao;
import controlador.ConsultorioDao;
import controlador.MedicoDao;
import controlador.PacienteDAO;
import entidad.Cita;
import entidad.Consultorio;
import entidad.Medico;
import entidad.Paciente;

public class FRMMantenimientoCitas extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtCita;
	private JTextField txtMotivo;
	private JTextField txtFecha;
	private JTextField txtHora;

	private JTable tblListaCitas;
	private JTextField txtBuscar;

	private JComboBox<String> cbPaciente;
	private JComboBox<String> cbMedico;
	private JComboBox<String> cbConsultorio;

	private final ButtonGroup bgBusqueda = new ButtonGroup();
	private JRadioButton rdbtnDniPaciente;
	private JRadioButton rdbtnApellidosPaciente;

	private final CitaDao citaDao = new CitaDao();
	private final PacienteDAO pacienteDao = new PacienteDAO();
	private final MedicoDao medicoDao = new MedicoDao();
	private final ConsultorioDao consultorioDao = new ConsultorioDao();

	private final ArrayList<Integer> idsPaciente = new ArrayList<>();
	private final ArrayList<Integer> idsMedico = new ArrayList<>();
	private final ArrayList<Integer> idsConsultorio = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FRMMantenimientoCitas frame = new FRMMantenimientoCitas();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

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
		btnSalir.addActionListener(e -> {
			FRMPrincipal p = new FRMPrincipal();
			p.setVisible(true);
			dispose();
		});
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

		JLabel lblCita_1 = new JLabel("N Cita");
		lblCita_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCita_1.setBounds(12, 49, 62, 14);
		panelRegistrarCitas.add(lblCita_1);

		txtCita = new JTextField();
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

		cbPaciente = new JComboBox<>();
		cbPaciente.setEnabled(false);
		cbPaciente.setEditable(false);
		cbPaciente.setBounds(142, 69, 250, 25);
		panelRegistrarCitas.add(cbPaciente);

		JLabel lblMedico_1 = new JLabel("Medico");
		lblMedico_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/EspecialidadMedico.png")));
		lblMedico_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMedico_1.setBounds(424, 48, 90, 16);
		panelRegistrarCitas.add(lblMedico_1);

		cbMedico = new JComboBox<>();
		cbMedico.setEnabled(false);
		cbMedico.setEditable(false);
		cbMedico.setBounds(422, 68, 250, 25);
		panelRegistrarCitas.add(cbMedico);

		JLabel lblConsultorio_1 = new JLabel("Consultorio");
		lblConsultorio_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/consultorio-medico.png")));
		lblConsultorio_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblConsultorio_1.setBounds(12, 114, 120, 24);
		panelRegistrarCitas.add(lblConsultorio_1);

		cbConsultorio = new JComboBox<>();
		cbConsultorio.setEnabled(false);
		cbConsultorio.setEditable(false);
		cbConsultorio.setBackground(new Color(245, 255, 250));
		cbConsultorio.setBounds(10, 137, 200, 25);
		panelRegistrarCitas.add(cbConsultorio);

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

		JLabel lblHorario_1 = new JLabel("Horario");
		lblHorario_1.setForeground(new Color(0, 128, 128));
		lblHorario_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHorario_1.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/calendar.png")));
		lblHorario_1.setBounds(10, 192, 110, 20);
		panelRegistrarCitas.add(lblHorario_1);

		JSeparator separator2_1 = new JSeparator();
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

		JLabel lblConsultaCitas = new JLabel("Consulta Citas");
		lblConsultaCitas.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/img/consulta.png")));
		lblConsultaCitas.setForeground(new Color(0, 128, 128));
		lblConsultaCitas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblConsultaCitas.setBounds(10, 297, 148, 31);
		panelRegistrarCitas.add(lblConsultaCitas);

		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setLayout(null);
		panelBusqueda.setBorder(new LineBorder(new Color(0, 128, 128), 3));
		panelBusqueda.setBounds(31, 328, 612, 60);
		panelRegistrarCitas.add(panelBusqueda);

		rdbtnDniPaciente = new JRadioButton("DNI Paciente");
		rdbtnDniPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnDniPaciente.setBounds(6, 20, 110, 23);
		panelBusqueda.add(rdbtnDniPaciente);
		bgBusqueda.add(rdbtnDniPaciente);

		rdbtnApellidosPaciente = new JRadioButton("Apellidos Paciente");
		rdbtnApellidosPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnApellidosPaciente.setBounds(119, 20, 150, 23);
		panelBusqueda.add(rdbtnApellidosPaciente);
		bgBusqueda.add(rdbtnApellidosPaciente);

		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBackground(SystemColor.scrollbar);
		txtBuscar.setBounds(280, 20, 195, 25);
		panelBusqueda.add(txtBuscar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/busqueda.png")));
		btnBuscar.setForeground(new Color(128, 128, 0));
		btnBuscar.setBackground(SystemColor.info);
		btnBuscar.setBounds(502, 17, 100, 30);
		panelBusqueda.add(btnBuscar);

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
				new Object[][] {},
				new String[] { "N Cita", "Paciente", "Medico", "Consultorio", "Fecha", "Hora", "Motivo", "Estado" }
		) {
			private static final long serialVersionUID = 1L;
			@Override public boolean isCellEditable(int row, int column) { return false; }
		});
		scrollPane.setViewportView(tblListaCitas);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar.setForeground(new Color(0, 0, 128));
		btnModificar.setBackground(new Color(0, 128, 128));
		btnModificar.setBounds(20, 735, 113, 30);
		contentPane.add(btnModificar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGuardar.setForeground(new Color(0, 0, 128));
		btnGuardar.setBackground(new Color(0, 128, 128));
		btnGuardar.setBounds(143, 735, 100, 30);
		contentPane.add(btnGuardar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/escoba.png")));
		btnLimpiar.setForeground(new Color(0, 0, 128));
		btnLimpiar.setBackground(new Color(128, 128, 0));
		btnLimpiar.setBounds(484, 735, 100, 30);
		contentPane.add(btnLimpiar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FRMMantenimientoCitas.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar.setForeground(new Color(0, 0, 128));
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(594, 735, 100, 30);
		contentPane.add(btnEliminar);

		// tabla -> arriba
		tblListaCitas.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int fila = tblListaCitas.getSelectedRow();
				if (fila < 0) return;

				txtCita.setText(String.valueOf(tblListaCitas.getValueAt(fila, 0)));
				txtFecha.setText(String.valueOf(tblListaCitas.getValueAt(fila, 4)));
				txtHora.setText(String.valueOf(tblListaCitas.getValueAt(fila, 5)));
				txtMotivo.setText(String.valueOf(tblListaCitas.getValueAt(fila, 6)));

				// seleccionar por texto (rápido)
				String pac = String.valueOf(tblListaCitas.getValueAt(fila, 1));
				for (int i = 0; i < cbPaciente.getItemCount(); i++) {
					if (cbPaciente.getItemAt(i).equalsIgnoreCase(pac)) { cbPaciente.setSelectedIndex(i); break; }
				}
				String med = String.valueOf(tblListaCitas.getValueAt(fila, 2));
				for (int i = 0; i < cbMedico.getItemCount(); i++) {
					if (cbMedico.getItemAt(i).equalsIgnoreCase(med)) { cbMedico.setSelectedIndex(i); break; }
				}
				String con = String.valueOf(tblListaCitas.getValueAt(fila, 3));
				for (int i = 0; i < cbConsultorio.getItemCount(); i++) {
					if (cbConsultorio.getItemAt(i).equalsIgnoreCase(con)) { cbConsultorio.setSelectedIndex(i); break; }
				}
			}
		});

		// eventos botones
		btnModificar.addActionListener(e -> {
			if (txtCita.getText().trim().isEmpty()) {
				msg("Seleccione una cita");
				return;
			}
			cbPaciente.setEnabled(true);
			cbMedico.setEnabled(true);
			cbConsultorio.setEnabled(true);
			txtMotivo.setEnabled(true);
			txtFecha.setEnabled(true);
			txtHora.setEnabled(true);
		});

		btnGuardar.addActionListener(e -> {

			if (txtCita.getText().trim().isEmpty()) { msg("Seleccione una cita"); return; }
			if (cbPaciente.getSelectedIndex() < 0 || cbMedico.getSelectedIndex() < 0 || cbConsultorio.getSelectedIndex() < 0) {
				msg("Faltan datos");
				return;
			}

			String motivo = txtMotivo.getText().trim();
			String fecha = txtFecha.getText().trim();
			String hora = txtHora.getText().trim();

			if (motivo.isEmpty()) { msg("Ingrese motivo"); return; }
			if (fecha.length() < 8) { msg("Fecha invalida"); return; }
			if (hora.length() < 4) { msg("Hora invalida"); return; }

			int codPaciente = idsPaciente.get(cbPaciente.getSelectedIndex());
			int codMedico = idsMedico.get(cbMedico.getSelectedIndex());
			int codConsultorio = idsConsultorio.get(cbConsultorio.getSelectedIndex());

			Cita c = new Cita();
			c.setNumCita(Integer.parseInt(txtCita.getText().trim()));
			c.setCodPaciente(codPaciente);
			c.setCodMedico(codMedico);
			c.setCodConsultorio(codConsultorio);
			c.setMotivo(motivo);
			c.setFecha(fecha);
			c.setHora(formatearHora(hora));
			c.setEstado(1);

			boolean ok = citaDao.actualizar(c);

			if (!ok) {
				msg("No se pudo guardar");
				return;
			}

			msg("Listo");
			cargarCitasActivas();

			cbPaciente.setEnabled(false);
			cbMedico.setEnabled(false);
			cbConsultorio.setEnabled(false);
			txtMotivo.setEnabled(false);
			txtFecha.setEnabled(false);
			txtHora.setEnabled(false);

			txtCita.setText("");
			txtMotivo.setText("");
			txtFecha.setText("");
			txtHora.setText("");
			tblListaCitas.clearSelection();
		});

		btnEliminar.addActionListener(e -> {
			int fila = tblListaCitas.getSelectedRow();
			if (fila < 0) { msg("Seleccione una cita"); return; }

			int numCita = Integer.parseInt(String.valueOf(tblListaCitas.getValueAt(fila, 0)));

			int r = JOptionPane.showConfirmDialog(this, "Desea cancelar esta cita", "Confirmar", JOptionPane.YES_NO_OPTION);
			if (r != JOptionPane.YES_OPTION) return;

			boolean ok = citaDao.cancelar(numCita);

			if (ok) {
				msg("Cita cancelada");
				cargarCitasActivas();
				tblListaCitas.clearSelection();
				txtCita.setText("");
				txtMotivo.setText("");
				txtFecha.setText("");
				txtHora.setText("");
			} else {
				msg("No se pudo cancelar");
			}
		});

		btnLimpiar.addActionListener(e -> {
			txtBuscar.setText("");
			bgBusqueda.clearSelection();
			txtCita.setText("");
			txtMotivo.setText("");
			txtFecha.setText("");
			txtHora.setText("");

			cbPaciente.setEnabled(false);
			cbMedico.setEnabled(false);
			cbConsultorio.setEnabled(false);
			txtMotivo.setEnabled(false);
			txtFecha.setEnabled(false);
			txtHora.setEnabled(false);

			cargarCitasActivas();
			tblListaCitas.clearSelection();
		});

		btnBuscar.addActionListener(e -> {

			String q = txtBuscar.getText().trim();
			if (q.isEmpty()) {
				cargarCitasActivas();
				return;
			}

			DefaultTableModel model = (DefaultTableModel) tblListaCitas.getModel();
			model.setRowCount(0);

			if (rdbtnDniPaciente.isSelected()) {

				Paciente p = pacienteDao.buscarPorDni(q);
				if (p == null) { msg("DNI no existe"); return; }

				List<Object[]> rows = citaDao.consultarParaTabla(p.getCodPaciente(), null, null, null);
				for (Object[] r : rows) model.addRow(r);

				if (rows.isEmpty()) msg("No se encontraron citas");
				return;
			}

			if (rdbtnApellidosPaciente.isSelected()) {

				ArrayList<Paciente> pacientes = pacienteDao.buscarPorApellido(q);
				if (pacientes.isEmpty()) { msg("Apellido no existe"); return; }

				int total = 0;
				for (Paciente p : pacientes) {
					List<Object[]> rows = citaDao.consultarParaTabla(p.getCodPaciente(), null, null, null);
					for (Object[] r : rows) {
						model.addRow(r);
						total++;
					}
				}

				if (total == 0) msg("No se encontraron citas");
				return;
			}

			msg("Seleccione DNI o Apellidos");
		});

		// carga combos
		cbPaciente.removeAllItems();
		cbMedico.removeAllItems();
		cbConsultorio.removeAllItems();
		idsPaciente.clear();
		idsMedico.clear();
		idsConsultorio.clear();

		for (Paciente p : pacienteDao.listarActivos()) {
			idsPaciente.add(p.getCodPaciente());
			cbPaciente.addItem(p.getNombres() + " " + p.getApellidos());
		}

		for (Medico m : medicoDao.listarActivos()) {
			idsMedico.add(m.getCodMedico());
			cbMedico.addItem(m.getNombres() + " " + m.getApellidos());
		}

		for (Consultorio c : consultorioDao.listarActivos()) {
			idsConsultorio.add(c.getCodConsultorio());
			cbConsultorio.addItem(c.getNombre());
		}

		cargarCitasActivas();

		cbPaciente.setEnabled(false);
		cbMedico.setEnabled(false);
		cbConsultorio.setEnabled(false);
		txtMotivo.setEnabled(false);
		txtFecha.setEnabled(false);
		txtHora.setEnabled(false);
	}

	private void cargarCitasActivas() {
		DefaultTableModel model = (DefaultTableModel) tblListaCitas.getModel();
		model.setRowCount(0);

		List<Object[]> lista = citaDao.listarActivasParaTabla();
		for (Object[] r : lista) model.addRow(r);
	}

	private String formatearHora(String h) {
		if (h != null && h.length() == 5) return h + ":00";
		return h;
	}

	private void msg(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
}