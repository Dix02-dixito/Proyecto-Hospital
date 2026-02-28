package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.MedicoDao;

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

	private JTextField txtBuscar;

	private JTable table;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnCMP;
	private JRadioButton rdbtnApellidos;

	// true si estas creando nuevo, false si estas editando
	private boolean modoNuevo = false;

	private final MedicoDao medicoDao = new MedicoDao();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FRMMantenimientoMedico frame = new FRMMantenimientoMedico();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	// llena la tabla con medicos activos
	private void listarTabla() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		List<Object[]> lista = medicoDao.listarActivosParaTabla();
		for (Object[] fila : lista) {
			model.addRow(fila);
		}
	}

	// activa o bloquea los campos para escribir
	private void habilitarCampos(boolean estado) {
		txtNombre.setEnabled(estado);
		txtApellidos.setEnabled(estado);
		txtCMP.setEnabled(estado);
		txtEspecialidad.setEnabled(estado);
		txtTelefono.setEnabled(estado);
		txtCorreo.setEnabled(estado);

		// este no se edita
		txtCodigo.setEnabled(false);
	}

	// limpia el formulario de arriba
	private void limpiarCamposArriba() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtCMP.setText("");
		txtEspecialidad.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
	}

	// valida antes de grabar
	private boolean validarCampos() {

		if (txtNombre.getText().trim().isEmpty()) { msg("Ingrese nombres"); return false; }
		if (txtApellidos.getText().trim().isEmpty()) { msg("Ingrese apellidos"); return false; }
		if (txtCMP.getText().trim().isEmpty()) { msg("Ingrese CMP"); return false; }
		if (txtEspecialidad.getText().trim().isEmpty()) { msg("Ingrese especialidad"); return false; }

		String tel = txtTelefono.getText().trim();
		if (!tel.isEmpty() && !tel.matches("\\d{7,15}")) {
			msg("Telefono invalido (solo numeros, 7 a 15 digitos)");
			return false;
		}

		String correo = txtCorreo.getText().trim();
		if (!correo.isEmpty() && !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			msg("Correo invalido");
			return false;
		}

		return true;
	}

	// cuando eliges una fila, rellena los campos de arriba
	private void cargarSeleccionTabla() {
		int fila = table.getSelectedRow();
		if (fila < 0) return;

		txtCodigo.setText(table.getValueAt(fila, 0).toString());
		txtNombre.setText(table.getValueAt(fila, 1).toString());
		txtApellidos.setText(table.getValueAt(fila, 2).toString());
		txtCMP.setText(table.getValueAt(fila, 3).toString());
		txtEspecialidad.setText(table.getValueAt(fila, 4).toString());
		txtTelefono.setText(table.getValueAt(fila, 5).toString());
		txtCorreo.setText(table.getValueAt(fila, 6).toString());
	}

	public FRMMantenimientoMedico() {

		setTitle("Mantenimiento Medicos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoMedico.class.getResource("/img/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 723);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Mantenimiento Medicos");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblTitulo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/logo.png")));
		lblTitulo.setBounds(0, 5, 331, 60);
		contentPane.add(lblTitulo);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setForeground(new Color(128, 0, 0));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/salida.png")));
		btnSalir.setBackground(new Color(220, 220, 220));
		btnSalir.setBounds(500, 21, 167, 35);
		btnSalir.addActionListener(e -> {
			FRMPrincipal p = new FRMPrincipal();
			p.setVisible(true);
			dispose();
		});
		contentPane.add(btnSalir);

		JPanel panelModificacion = new JPanel();
		panelModificacion.setBorder(new CompoundBorder(
				new LineBorder(new Color(128, 128, 128)),
				new LineBorder(new Color(0, 255, 255))
		));
		panelModificacion.setBackground(new Color(220, 220, 220));
		panelModificacion.setBounds(10, 70, 625, 335);
		contentPane.add(panelModificacion);
		panelModificacion.setLayout(null);

		JLabel lblSubTitulo = new JLabel("Modificacion / Agregar");
		lblSubTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubTitulo.setForeground(new Color(0, 128, 128));
		lblSubTitulo.setBounds(10, 11, 222, 27);
		lblSubTitulo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/mantenimiento.png")));
		lblSubTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelModificacion.add(lblSubTitulo);

		JLabel lblNombre = new JLabel("Nombre Medico");
		lblNombre.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/NombreApellidos.png")));
		lblNombre.setBounds(10, 50, 160, 20);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(10, 70, 200, 25);
		panelModificacion.add(txtNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/NombreApellidos.png")));
		lblApellidos.setBounds(220, 51, 120, 17);
		lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(220, 70, 200, 25);
		panelModificacion.add(txtApellidos);

		JLabel lblCMP = new JLabel("CMP");
		lblCMP.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/identificacionMedico.png")));
		lblCMP.setBounds(430, 51, 70, 19);
		lblCMP.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblCMP);

		txtCMP = new JTextField();
		txtCMP.setBounds(430, 70, 150, 25);
		panelModificacion.add(txtCMP);

		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/EspecialidadMedico.png")));
		lblEspecialidad.setBounds(10, 105, 140, 20);
		lblEspecialidad.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblEspecialidad);

		txtEspecialidad = new JTextField();
		txtEspecialidad.setBounds(10, 125, 200, 25);
		panelModificacion.add(txtEspecialidad);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/Correo.png")));
		lblCorreo.setBounds(220, 102, 80, 27);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(220, 125, 200, 25);
		panelModificacion.add(txtCorreo);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/Telefono.png")));
		lblTelefono.setBounds(430, 104, 80, 23);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(430, 125, 150, 25);
		panelModificacion.add(txtTelefono);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/Codigo.png")));
		lblCodigo.setBounds(12, 158, 91, 17);
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelModificacion.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(12, 177, 100, 25);
		txtCodigo.setEditable(false);
		panelModificacion.add(txtCodigo);

		JLabel lblConsulta = new JLabel("Consulta Medico");
		lblConsulta.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/consulta.png")));
		lblConsulta.setForeground(new Color(0, 128, 128));
		lblConsulta.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblConsulta.setBounds(10, 213, 156, 29);
		panelModificacion.add(lblConsulta);

		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBounds(25, 253, 555, 60);
		panelBusqueda.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		panelBusqueda.setLayout(null);
		panelModificacion.add(panelBusqueda);

		rdbtnCMP = new JRadioButton("CMP");
		rdbtnCMP.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnCMP.setBounds(6, 18, 60, 23);
		panelBusqueda.add(rdbtnCMP);
		buttonGroup.add(rdbtnCMP);

		rdbtnApellidos = new JRadioButton("Apellidos");
		rdbtnApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnApellidos.setBounds(68, 18, 90, 23);
		panelBusqueda.add(rdbtnApellidos);
		buttonGroup.add(rdbtnApellidos);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(164, 18, 260, 30);
		panelBusqueda.add(txtBuscar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(128, 128, 0));
		btnBuscar.setBounds(434, 18, 100, 30);
		btnBuscar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/busqueda.png")));
		btnBuscar.setBackground(new Color(255, 255, 224));
		btnBuscar.addActionListener(e -> {

			String q = txtBuscar.getText().trim();

			if (q.isEmpty()) {
				msg("Ingrese un valor para buscar");
				return;
			}

			List<Object[]> lista;

			if (rdbtnCMP.isSelected()) {
				lista = medicoDao.buscarPorCMPParaTabla(q);
			} else if (rdbtnApellidos.isSelected()) {
				lista = medicoDao.buscarPorApellidosParaTabla(q);
			} else {
				msg("Seleccione CMP o Apellidos");
				return;
			}

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);

			for (Object[] fila : lista) {
				model.addRow(fila);
			}

			if (lista.isEmpty()) msg("No se encontraron resultados");
		});
		panelBusqueda.add(btnBuscar);

		JPanel panelListaMedicos = new JPanel();
		panelListaMedicos.setBorder(new CompoundBorder(
				new LineBorder(new Color(128, 128, 128)),
				new LineBorder(new Color(0, 128, 128))
		));
		panelListaMedicos.setBackground(new Color(220, 220, 220));
		panelListaMedicos.setBounds(10, 416, 625, 218);
		contentPane.add(panelListaMedicos);
		panelListaMedicos.setLayout(null);

		JLabel lblListaDeMedicos = new JLabel("Lista de Medicos");
		lblListaDeMedicos.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/agregar (1).png")));
		lblListaDeMedicos.setForeground(new Color(0, 128, 128));
		lblListaDeMedicos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblListaDeMedicos.setBounds(10, 8, 156, 29);
		panelListaMedicos.add(lblListaDeMedicos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 599, 170);
		panelListaMedicos.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] { "Codigo", "Nombres", "Apellidos", "CMP", "Especialidad", "Telefono", "Correo" }
		) {
			private static final long serialVersionUID = 1L;
			@Override public boolean isCellEditable(int row, int column) { return false; }
		});
		scrollPane.setViewportView(table);

		// cuando seleccionas una fila, rellena arriba
		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && !modoNuevo) cargarSeleccionTabla();
		});

		JButton btnAgregar = new JButton("Nuevo");
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/agregar.png")));
		btnAgregar.setBackground(new Color(0, 128, 128));
		btnAgregar.setBounds(20, 645, 100, 30);
		btnAgregar.addActionListener(e -> {
			modoNuevo = true;
			limpiarCamposArriba();
			habilitarCampos(true);
			table.clearSelection();
			txtNombre.requestFocus();
		});
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/cuadrado-de-la-pluma.png")));
		btnModificar.setBackground(new Color(0, 128, 128));
		btnModificar.setBounds(130, 645, 109, 30);
		btnModificar.addActionListener(e -> {

			int fila = table.getSelectedRow();
			if (fila == -1) {
				msg("Seleccione un medico");
				return;
			}

			modoNuevo = false;
			cargarSeleccionTabla();
			habilitarCampos(true);
			txtNombre.requestFocus();
		});
		contentPane.add(btnModificar);

		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar.setBackground(new Color(0, 128, 128));
		btnGrabar.setBounds(249, 645, 100, 30);
		btnGrabar.addActionListener(e -> {

			if (!validarCampos()) return;

			String nombres = txtNombre.getText().trim();
			String apellidos = txtApellidos.getText().trim();
			String cmp = txtCMP.getText().trim();
			String especialidad = txtEspecialidad.getText().trim();
			String telefono = txtTelefono.getText().trim();
			String correo = txtCorreo.getText().trim();

			boolean ok = false;

			// guardar nuevo
			if (modoNuevo) {
				ok = medicoDao.insertar(nombres, apellidos, cmp, especialidad, telefono, correo);
				if (ok) msg("Medico registrado");
			}
			// actualizar
			else {

				if (txtCodigo.getText().trim().isEmpty()) {
					msg("Seleccione un medico para modificar");
					return;
				}

				int codMedico = Integer.parseInt(txtCodigo.getText().trim());
				ok = medicoDao.actualizar(codMedico, nombres, apellidos, cmp, especialidad, telefono, correo);
				if (ok) msg("Medico actualizado");
			}

			if (!ok) {
				msg("No se pudo guardar");
				return;
			}

			listarTabla();
			habilitarCampos(false);
			limpiarCamposArriba();
			modoNuevo = false;

		});
		contentPane.add(btnGrabar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/escoba.png")));
		btnLimpiar.setBackground(new Color(128, 128, 0));
		btnLimpiar.setBounds(425, 645, 100, 30);
		btnLimpiar.addActionListener(e -> {
			limpiarCamposArriba();
			habilitarCampos(false);
			modoNuevo = false;
			txtBuscar.setText("");
			buttonGroup.clearSelection();
			listarTabla();
			table.clearSelection();
		});
		contentPane.add(btnLimpiar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FRMMantenimientoMedico.class.getResource("/img/cruz-pequena.png")));
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(535, 645, 100, 30);
		btnEliminar.addActionListener(e -> {

			int fila = table.getSelectedRow();
			if (fila == -1) {
				msg("Seleccione un medico");
				return;
			}

			int codMedico = Integer.parseInt(table.getValueAt(fila, 0).toString());

			int r = javax.swing.JOptionPane.showConfirmDialog(
					this,
					"Desea eliminar este medico",
					"Confirmar",
					javax.swing.JOptionPane.YES_NO_OPTION
			);

			if (r != javax.swing.JOptionPane.YES_OPTION) return;

			boolean ok = medicoDao.eliminar(codMedico);

			if (ok) {
				msg("Eliminado correctamente");
				listarTabla();
				limpiarCamposArriba();
				habilitarCampos(false);
				modoNuevo = false;
				table.clearSelection();
			} else {
				msg("No se pudo eliminar");
			}
		});
		contentPane.add(btnEliminar);

		// estado inicial
		habilitarCampos(false);
		limpiarCamposArriba();
		listarTabla();
	}

	private void msg(String s) {
		javax.swing.JOptionPane.showMessageDialog(this, s);
	}
}