package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.CitaDao;
import controlador.PacienteDAO;
import entidad.Paciente;

public class FRMMantenimientoPaciente extends JFrame {

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

	private JTextField txtBuscar1;
	private JTable table;

	private boolean modoNuevo = false;

	private final PacienteDAO ap = new PacienteDAO();

	private void msg(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	// llena la tabla con pacientes activos
	private void cargarPacientesActivos() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);

		for (Paciente p : ap.listarActivos()) {
			modelo.addRow(new Object[] {
					p.getNombres(),
					p.getApellidos(),
					p.getEdad(),
					p.getDni(),
					p.getEstado(),
					p.getCelular(),
					p.getCorreo(),
					p.getCodPaciente()
			});
		}
	}

	// habilita o bloquea campos del formulario
	private void habilitarCampos(boolean estado) {
		txtNombre1.setEnabled(estado);
		txtApellidos1.setEnabled(estado);
		txtDNI1.setEnabled(estado);
		txtEdad1.setEnabled(estado);
		txtTelefono1.setEnabled(estado);
		txtCorreo1.setEnabled(estado);

		txtCodigo.setEnabled(false);
		txtEstado1.setEnabled(false);
	}

	// limpia el formulario
	private void limpiarCamposArriba() {
		txtCodigo.setText("");
		txtNombre1.setText("");
		txtApellidos1.setText("");
		txtDNI1.setText("");
		txtEdad1.setText("");
		txtTelefono1.setText("");
		txtCorreo1.setText("");
		txtEstado1.setText("1");
	}

	// valida antes de guardar
	private boolean validarCampos() {
		String dni = txtDNI1.getText().trim();
		String nombres = txtNombre1.getText().trim();
		String apellidos = txtApellidos1.getText().trim();
		String edadTxt = txtEdad1.getText().trim();

		if (dni.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || edadTxt.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe rellenar los campos obligatorios");
			return false;
		}

		if (!dni.matches("\\d{8}")) {
			JOptionPane.showMessageDialog(this, "El DNI debe tener 8 digitos numericos");
			return false;
		}

		if (!nombres.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
			JOptionPane.showMessageDialog(this, "El nombre solo puede contener letras y espacios");
			return false;
		}

		if (!apellidos.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
			JOptionPane.showMessageDialog(this, "Los apellidos solo puede contener letras y espacios");
			return false;
		}

		try {
			int edad = Integer.parseInt(edadTxt);
			if (edad <= 0 || edad > 120) {
				JOptionPane.showMessageDialog(this, "Edad no valida");
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Edad debe ser numerica");
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FRMMantenimientoPaciente frame = new FRMMantenimientoPaciente();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FRMMantenimientoPaciente() {

		setTitle("Mantenimiento Pacientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoPaciente.class.getResource("/img/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 782);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo1 = new JLabel("Mantenimiento Pacientes");
		lblTitulo1.setForeground(new Color(0, 128, 128));
		lblTitulo1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitulo1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/logo.png")));
		lblTitulo1.setBounds(0, 5, 326, 60);
		contentPane.add(lblTitulo1);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setForeground(new Color(220, 20, 60));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/salida.png")));
		btnSalir.setBackground(new Color(220, 220, 220));
		btnSalir.setBounds(536, 21, 150, 35);
		contentPane.add(btnSalir);

		JButton btnAgregar = new JButton("Nuevo");
		btnAgregar.setForeground(new Color(0, 0, 128));
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/agregar.png")));
		btnAgregar.setBackground(new Color(0, 128, 128));
		btnAgregar.setBounds(20, 685, 100, 30);
		contentPane.add(btnAgregar);

		JButton btnModificar1 = new JButton("Modificar");
		btnModificar1.setForeground(new Color(0, 0, 128));
		btnModificar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/cuadrado-de-la-pluma.png")));
		btnModificar1.setBackground(new Color(0, 128, 128));
		btnModificar1.setBounds(130, 685, 113, 30);
		contentPane.add(btnModificar1);

		JButton btnGrabar1 = new JButton("Guardar");
		btnGrabar1.setForeground(new Color(0, 0, 128));
		btnGrabar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar1.setBackground(new Color(0, 128, 128));
		btnGrabar1.setBounds(260, 685, 100, 30);
		contentPane.add(btnGrabar1);

		JButton btnLimpiar1 = new JButton("Limpiar");
		btnLimpiar1.setForeground(new Color(0, 0, 128));
		btnLimpiar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/escoba.png")));
		btnLimpiar1.setBackground(new Color(128, 128, 0));
		btnLimpiar1.setBounds(429, 685, 100, 30);
		contentPane.add(btnLimpiar1);

		JButton btnEliminar1 = new JButton("Eliminar");
		btnEliminar1.setForeground(new Color(0, 0, 128));
		btnEliminar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/cruz-pequena.png")));
		btnEliminar1.setBackground(new Color(128, 0, 0));
		btnEliminar1.setBounds(536, 685, 100, 30);
		contentPane.add(btnEliminar1);

		JPanel panelModificacion = new JPanel();
		panelModificacion.setBorder(new CompoundBorder(
				new LineBorder(new Color(128, 128, 128)),
				new LineBorder(new Color(0, 255, 255))
		));
		panelModificacion.setBackground(new Color(220, 220, 220));
		panelModificacion.setBounds(20, 73, 621, 358);
		contentPane.add(panelModificacion);
		panelModificacion.setLayout(null);

		JLabel lblsubTitulo1 = new JLabel("Modificacion / Agregar");
		lblsubTitulo1.setForeground(new Color(0, 128, 128));
		lblsubTitulo1.setBounds(10, 11, 222, 27);
		lblsubTitulo1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/mantenimiento.png")));
		lblsubTitulo1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelModificacion.add(lblsubTitulo1);

		JSeparator separator = new JSeparator();
		separator.setBounds(28, 36, 214, 2);
		panelModificacion.add(separator);

		JLabel lblDNI1 = new JLabel("DNI");
		lblDNI1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/DNI.png")));
		lblDNI1.setBounds(12, 58, 60, 20);
		lblDNI1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblDNI1);

		txtDNI1 = new JTextField();
		txtDNI1.setBounds(10, 78, 150, 25);
		panelModificacion.add(txtDNI1);
		txtDNI1.setColumns(10);

		JLabel lblNombre1 = new JLabel("Nombres");
		lblNombre1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/NombreApellidos.png")));
		lblNombre1.setBounds(172, 60, 100, 17);
		lblNombre1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblNombre1);

		txtNombre1 = new JTextField();
		txtNombre1.setBounds(170, 78, 200, 25);
		panelModificacion.add(txtNombre1);
		txtNombre1.setColumns(10);

		JLabel lblApellidos1 = new JLabel("Apellidos");
		lblApellidos1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/NombreApellidos.png")));
		lblApellidos1.setBounds(382, 58, 100, 21);
		lblApellidos1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblApellidos1);

		txtApellidos1 = new JTextField();
		txtApellidos1.setBounds(380, 80, 200, 25);
		panelModificacion.add(txtApellidos1);
		txtApellidos1.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Correo.png")));
		lblCorreo.setBounds(12, 120, 80, 20);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblCorreo);

		txtCorreo1 = new JTextField();
		txtCorreo1.setBounds(10, 139, 200, 25);
		panelModificacion.add(txtCorreo1);
		txtCorreo1.setColumns(10);

		JLabel lblTelefono1 = new JLabel("Telefono");
		lblTelefono1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Telefono.png")));
		lblTelefono1.setBounds(222, 121, 100, 19);
		lblTelefono1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblTelefono1);

		txtTelefono1 = new JTextField();
		txtTelefono1.setBounds(220, 141, 200, 25);
		panelModificacion.add(txtTelefono1);
		txtTelefono1.setColumns(10);

		JLabel lblEdad1 = new JLabel("Edad");
		lblEdad1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Edades.png")));
		lblEdad1.setBounds(431, 124, 80, 14);
		lblEdad1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblEdad1);

		txtEdad1 = new JTextField();
		txtEdad1.setBounds(430, 141, 100, 25);
		panelModificacion.add(txtEdad1);
		txtEdad1.setColumns(10);

		JLabel lblEstado1 = new JLabel("Estado");
		lblEstado1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/estado.png")));
		lblEstado1.setBounds(12, 169, 80, 20);
		lblEstado1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblEstado1);

		txtEstado1 = new JTextField();
		txtEstado1.setBounds(10, 190, 100, 25);
		panelModificacion.add(txtEstado1);
		txtEstado1.setColumns(10);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Codigo.png")));
		lblCodigo.setBounds(122, 172, 80, 17);
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(120, 190, 100, 25);
		panelModificacion.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblConsulta1 = new JLabel("Consulta Paciente");
		lblConsulta1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/consulta.png")));
		lblConsulta1.setForeground(new Color(0, 128, 128));
		lblConsulta1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblConsulta1.setBounds(12, 236, 148, 31);
		panelModificacion.add(lblConsulta1);

		ButtonGroup bg = new ButtonGroup();

		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBounds(22, 268, 558, 60);
		panelBusqueda.setBorder(new LineBorder(new Color(0, 128, 128), 3));
		panelBusqueda.setLayout(null);
		panelModificacion.add(panelBusqueda);

		JRadioButton rdbtnDNI = new JRadioButton("DNI");
		rdbtnDNI.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnDNI.setBounds(23, 20, 57, 23);
		panelBusqueda.add(rdbtnDNI);

		JRadioButton rdbtnApellidos1 = new JRadioButton("Apellidos");
		rdbtnApellidos1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnApellidos1.setBounds(82, 20, 86, 23);
		panelBusqueda.add(rdbtnApellidos1);

		bg.add(rdbtnDNI);
		bg.add(rdbtnApellidos1);

		txtBuscar1 = new JTextField();
		txtBuscar1.setBounds(193, 20, 200, 25);
		txtBuscar1.setBackground(SystemColor.scrollbar);
		txtBuscar1.setColumns(10);
		panelBusqueda.add(txtBuscar1);

		JButton btnBuscar1 = new JButton("Buscar");
		btnBuscar1.setForeground(new Color(128, 128, 0));
		btnBuscar1.setBounds(443, 20, 100, 30);
		btnBuscar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/busqueda.png")));
		btnBuscar1.setBackground(SystemColor.info);
		panelBusqueda.add(btnBuscar1);

		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new CompoundBorder(
				new LineBorder(new Color(128, 128, 128)),
				new LineBorder(new Color(0, 128, 128))
		));
		panelTabla.setBounds(21, 454, 620, 220);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);

		JLabel lbllistaPacientes = new JLabel("Lista de Pacientes");
		lbllistaPacientes.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/agregar (1).png")));
		lbllistaPacientes.setForeground(new Color(0, 128, 128));
		lbllistaPacientes.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbllistaPacientes.setBounds(12, 5, 191, 31);
		panelTabla.add(lbllistaPacientes);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 600, 175);
		panelTabla.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "Nombres", "Apellidos", "Edad", "Dni", "Estado", "Telefono", "Correo", "Codigo" }
		) {
			private static final long serialVersionUID = 1L;
			@Override public boolean isCellEditable(int row, int column) { return false; }
		});
		scrollPane.setViewportView(table);

		// eventos

		btnAgregar.addActionListener(e -> {
			modoNuevo = true;
			limpiarCamposArriba();
			habilitarCampos(true);
			table.clearSelection();
			txtNombre1.requestFocus();
		});

		btnModificar1.addActionListener(e -> {

			int fila = table.getSelectedRow();
			if (fila == -1) {
				msg("Seleccione un paciente");
				return;
			}

			int codigo = Integer.parseInt(table.getValueAt(fila, 7).toString());
			Paciente p = ap.buscarPorCodigo(codigo);

			if (p == null) {
				msg("No se encontro el paciente");
				return;
			}

			modoNuevo = false;

			txtCodigo.setText(String.valueOf(p.getCodPaciente()));
			txtNombre1.setText(p.getNombres());
			txtApellidos1.setText(p.getApellidos());
			txtDNI1.setText(p.getDni());
			txtEdad1.setText(String.valueOf(p.getEdad()));
			txtTelefono1.setText(p.getCelular());
			txtCorreo1.setText(p.getCorreo());
			txtEstado1.setText(String.valueOf(p.getEstado()));

			habilitarCampos(true);
			txtNombre1.requestFocus();
		});

		btnGrabar1.addActionListener(e -> {

			if (!validarCampos()) return;

			try {
				String dni = txtDNI1.getText().trim();

				Paciente p = new Paciente();
				p.setNombres(txtNombre1.getText().trim());
				p.setApellidos(txtApellidos1.getText().trim());
				p.setDni(dni);
				p.setEdad(Integer.parseInt(txtEdad1.getText().trim()));
				p.setCelular(txtTelefono1.getText().trim());
				p.setCorreo(txtCorreo1.getText().trim());
				p.setEstado(1);

				boolean ok;

				if (modoNuevo) {

					if (ap.existeDniNuevo(dni)) {
						JOptionPane.showMessageDialog(this, "El DNI ya esta registrado", "Validacion",
								JOptionPane.WARNING_MESSAGE);
						txtDNI1.requestFocus();
						return;
					}

					ok = ap.insertarPaciente(p);
					if (ok) msg("Paciente registrado correctamente");

				} else {

					if (txtCodigo.getText().trim().isEmpty()) {
						msg("Seleccione un paciente para modificar");
						return;
					}

					int codigo = Integer.parseInt(txtCodigo.getText().trim());

					if (ap.existeDni(dni, codigo)) {
						JOptionPane.showMessageDialog(this, "El DNI ya esta registrado en otro paciente", "Validacion",
								JOptionPane.WARNING_MESSAGE);
						txtDNI1.requestFocus();
						return;
					}

					p.setCodPaciente(codigo);
					ok = ap.actualizarPaciente(p);
					if (ok) msg("Paciente actualizado correctamente");
				}

				if (!ok) {
					JOptionPane.showMessageDialog(this, "No se pudo guardar el paciente", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				cargarPacientesActivos();
				habilitarCampos(false);
				limpiarCamposArriba();
				modoNuevo = false;
				table.clearSelection();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Edad invalida", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnEliminar1.addActionListener(e -> {

			CitaDao citaDao = new CitaDao();

			int fila = table.getSelectedRow();
			if (fila == -1) {
				msg("Seleccione un paciente");
				return;
			}

			int codPaciente = Integer.parseInt(table.getValueAt(fila, 7).toString());

			if (citaDao.pacienteTieneCitasFuturas(codPaciente)) {
				msg("No se puede eliminar. Tiene citas pendientes");
				return;
			}

			int r = JOptionPane.showConfirmDialog(this, "Desea eliminar este paciente", "Confirmar",
					JOptionPane.YES_NO_OPTION);
			if (r != JOptionPane.YES_OPTION) return;

			boolean eliminado = ap.eliminarPaciente(codPaciente);

			if (eliminado) {
				msg("Paciente eliminado correctamente");
				cargarPacientesActivos();
				limpiarCamposArriba();
				habilitarCampos(false);
				modoNuevo = false;
				table.clearSelection();
			} else {
				msg("Error al eliminar");
			}
		});

		btnLimpiar1.addActionListener(e -> {
			limpiarCamposArriba();
			habilitarCampos(false);
			modoNuevo = false;

			txtBuscar1.setText("");
			bg.clearSelection();

			cargarPacientesActivos();
			table.clearSelection();
		});

		btnBuscar1.addActionListener(e -> {

			String valor = txtBuscar1.getText().trim();

			if (valor.isEmpty()) {
				msg("Ingrese dato para buscar");
				txtBuscar1.requestFocus();
				return;
			}

			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.setRowCount(0);

			try {

				if (rdbtnDNI.isSelected()) {

					Paciente p = ap.buscarPorDni(valor);

					if (p == null) {
						msg("DNI no existente");
						return;
					}

					modelo.addRow(new Object[] {
							p.getNombres(),
							p.getApellidos(),
							p.getEdad(),
							p.getDni(),
							p.getEstado(),
							p.getCelular(),
							p.getCorreo(),
							p.getCodPaciente()
					});
					return;
				}

				if (rdbtnApellidos1.isSelected()) {

					ArrayList<Paciente> lista = ap.buscarPorApellido(valor);

					if (lista.isEmpty()) {
						msg("Apellido no existente");
						return;
					}

					for (Paciente p : lista) {
						modelo.addRow(new Object[] {
								p.getNombres(),
								p.getApellidos(),
								p.getEdad(),
								p.getDni(),
								p.getEstado(),
								p.getCelular(),
								p.getCorreo(),
								p.getCodPaciente()
						});
					}
					return;
				}

				msg("Seleccione DNI o Apellidos");

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
			}
		});

		btnSalir.addActionListener(e -> {
			FRMPrincipal principal = new FRMPrincipal();
			principal.setVisible(true);
			dispose();
		});

		// estado inicial
		txtEstado1.setText("1");
		habilitarCampos(false);
		limpiarCamposArriba();
		cargarPacientesActivos();
	}
}