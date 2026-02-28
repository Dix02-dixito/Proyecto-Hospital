package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import conexion.ConexionBD;

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
	private JRadioButton rdbtnCodigo;
	private JRadioButton rdbtnNombre;

	private boolean modoNuevo = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FRMMantenimientoConsultorio frame = new FRMMantenimientoConsultorio();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void msg(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	private void habilitarCampos(boolean estado) {
		txtNombre.setEnabled(estado);
		txtUbicacion.setEnabled(estado);
		txtPiso.setEnabled(estado);

		txtCodigo.setEnabled(false);
		txtEstado.setEnabled(false);
	}

	private void limpiarArriba() {
		txtNombre.setText("");
		txtUbicacion.setText("");
		txtPiso.setText("");
		txtCodigo.setText("");
		txtEstado.setText("1");
	}

	private void cargarSeleccionTabla() {
		int fila = tableConsultorios.getSelectedRow();
		if (fila < 0) return;

		txtNombre.setText(String.valueOf(tableConsultorios.getValueAt(fila, 0)));
		txtUbicacion.setText(String.valueOf(tableConsultorios.getValueAt(fila, 1)));
		txtPiso.setText(String.valueOf(tableConsultorios.getValueAt(fila, 2)));
		txtEstado.setText(String.valueOf(tableConsultorios.getValueAt(fila, 3)));
		txtCodigo.setText(String.valueOf(tableConsultorios.getValueAt(fila, 4)));
	}

	private boolean validar() {

		String nombre = txtNombre.getText().trim();
		String ubic = txtUbicacion.getText().trim();
		String pisoTxt = txtPiso.getText().trim();

		if (nombre.isEmpty()) { msg("Ingrese nombre"); return false; }
		if (ubic.isEmpty()) { msg("Ingrese ubicacion"); return false; }
		if (pisoTxt.isEmpty()) { msg("Ingrese piso"); return false; }

		try {
			int piso = Integer.parseInt(pisoTxt);
			if (piso <= 0 || piso > 50) {
				msg("Piso invalido");
				return false;
			}
		} catch (Exception e) {
			msg("Piso debe ser numerico");
			return false;
		}

		return true;
	}

	private void listarTabla() {

		DefaultTableModel model = (DefaultTableModel) tableConsultorios.getModel();
		model.setRowCount(0);

		String sql = "SELECT nombre_consultorio, ubicacion_consultorio, piso_consultorio, estado_consultorio, codConsultorio "
				   + "FROM consultorio WHERE estado_consultorio = 1 ORDER BY codConsultorio";

		try (Connection con = ConexionBD.conectar();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				model.addRow(new Object[] {
					rs.getString("nombre_consultorio"),
					rs.getString("ubicacion_consultorio"),
					rs.getInt("piso_consultorio"),
					rs.getInt("estado_consultorio"),
					rs.getInt("codConsultorio")
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg("Error al listar");
		}
	}

	private void buscar() {

		String q = txtBuscar.getText().trim();

		if (q.isEmpty()) {
			listarTabla();
			return;
		}

		if (!rdbtnCodigo.isSelected() && !rdbtnNombre.isSelected()) {
			msg("Seleccione Codigo o Nombre");
			return;
		}

		DefaultTableModel model = (DefaultTableModel) tableConsultorios.getModel();
		model.setRowCount(0);

		String sql;

		if (rdbtnCodigo.isSelected()) {
			sql = "SELECT nombre_consultorio, ubicacion_consultorio, piso_consultorio, estado_consultorio, codConsultorio "
				+ "FROM consultorio WHERE estado_consultorio = 1 AND codConsultorio = ?";
		} else {
			sql = "SELECT nombre_consultorio, ubicacion_consultorio, piso_consultorio, estado_consultorio, codConsultorio "
				+ "FROM consultorio WHERE estado_consultorio = 1 AND nombre_consultorio LIKE ?";
		}

		try (Connection con = ConexionBD.conectar();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			if (rdbtnCodigo.isSelected()) {
				ps.setInt(1, Integer.parseInt(q));
			} else {
				ps.setString(1, "%" + q + "%");
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					model.addRow(new Object[] {
						rs.getString("nombre_consultorio"),
						rs.getString("ubicacion_consultorio"),
						rs.getInt("piso_consultorio"),
						rs.getInt("estado_consultorio"),
						rs.getInt("codConsultorio")
					});
				}
			}

		} catch (NumberFormatException ex) {
			msg("Codigo debe ser numerico");
		} catch (Exception ex) {
			ex.printStackTrace();
			msg("Error al buscar");
		}

		if (model.getRowCount() == 0) msg("No se encontraron resultados");
	}

	public FRMMantenimientoConsultorio() {

		setTitle("Mantenimiento Consultorios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoConsultorio.class.getResource("/img/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 674);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Mantenimiento Consultorios");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitulo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/logo.png")));
		lblTitulo.setBounds(0, 5, 378, 57);
		contentPane.add(lblTitulo);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setForeground(new Color(128, 0, 0));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/salida.png")));
		btnSalir.setBackground(new Color(220, 220, 220));
		btnSalir.setBounds(549, 14, 145, 35);
		btnSalir.addActionListener(e -> {
			FRMPrincipal p = new FRMPrincipal();
			p.setVisible(true);
			dispose();
		});
		contentPane.add(btnSalir);

		JPanel panelMantenimientoConsultorio = new JPanel();
		panelMantenimientoConsultorio.setBorder(new CompoundBorder(
				new LineBorder(new Color(128, 128, 128)),
				new LineBorder(new Color(0, 255, 255))
		));
		panelMantenimientoConsultorio.setBackground(new Color(220, 220, 220));
		panelMantenimientoConsultorio.setBounds(10, 69, 659, 282);
		contentPane.add(panelMantenimientoConsultorio);
		panelMantenimientoConsultorio.setLayout(null);

		JLabel lblSubTitulo = new JLabel("Modificacion / Agregar");
		lblSubTitulo.setForeground(new Color(0, 128, 128));
		lblSubTitulo.setBounds(10, 11, 260, 27);
		lblSubTitulo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/mantenimiento.png")));
		lblSubTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelMantenimientoConsultorio.add(lblSubTitulo);

		JLabel lblNombre = new JLabel("Nombre del Consultorio");
		lblNombre.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/consultorio-medico.png")));
		lblNombre.setBounds(12, 52, 174, 17);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelMantenimientoConsultorio.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(10, 71, 200, 25);
		panelMantenimientoConsultorio.add(txtNombre);

		JLabel lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/ubicacion.png")));
		lblUbicacion.setBounds(224, 52, 100, 17);
		lblUbicacion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelMantenimientoConsultorio.add(lblUbicacion);

		txtUbicacion = new JTextField();
		txtUbicacion.setBounds(222, 71, 200, 25);
		panelMantenimientoConsultorio.add(txtUbicacion);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/NumeroPiso.png")));
		lblPiso.setBounds(434, 49, 46, 27);
		lblPiso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelMantenimientoConsultorio.add(lblPiso);

		txtPiso = new JTextField();
		txtPiso.setBounds(432, 73, 200, 25);
		panelMantenimientoConsultorio.add(txtPiso);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/estado.png")));
		lblEstado.setBounds(10, 107, 60, 27);
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelMantenimientoConsultorio.add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(10, 129, 100, 25);
		panelMantenimientoConsultorio.add(txtEstado);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/Codigo.png")));
		lblCodigo.setBounds(136, 112, 76, 17);
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelMantenimientoConsultorio.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(134, 129, 100, 25);
		panelMantenimientoConsultorio.add(txtCodigo);

		JSeparator separator = new JSeparator();
		separator.setBounds(35, 36, 260, 2);
		panelMantenimientoConsultorio.add(separator);

		JLabel lblConsulta = new JLabel("Consulta Consultorio");
		lblConsulta.setForeground(new Color(0, 128, 128));
		lblConsulta.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/consulta.png")));
		lblConsulta.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblConsulta.setBounds(10, 178, 186, 27);
		panelMantenimientoConsultorio.add(lblConsulta);

		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBounds(20, 209, 612, 62);
		panelBusqueda.setBorder(new LineBorder(new Color(0, 128, 128), 3));
		panelBusqueda.setLayout(null);
		panelMantenimientoConsultorio.add(panelBusqueda);

		rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnNombre.setBounds(6, 20, 80, 23);
		panelBusqueda.add(rdbtnNombre);
		buttonGroup.add(rdbtnNombre);

		rdbtnCodigo = new JRadioButton("Codigo");
		rdbtnCodigo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnCodigo.setBounds(90, 20, 69, 23);
		panelBusqueda.add(rdbtnCodigo);
		buttonGroup.add(rdbtnCodigo);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(195, 20, 254, 25);
		txtBuscar.setBackground(SystemColor.scrollbar);
		panelBusqueda.add(txtBuscar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(128, 128, 0));
		btnBuscar.setBounds(489, 17, 100, 30);
		btnBuscar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/busqueda.png")));
		btnBuscar.setBackground(SystemColor.info);
		btnBuscar.addActionListener(e -> buscar());
		panelBusqueda.add(btnBuscar);

		JPanel panelJtable = new JPanel();
		panelJtable.setBorder(new CompoundBorder(
				new LineBorder(new Color(128, 128, 128)),
				new LineBorder(new Color(0, 128, 128))
		));
		panelJtable.setBackground(new Color(220, 220, 220));
		panelJtable.setBounds(10, 371, 659, 192);
		contentPane.add(panelJtable);
		panelJtable.setLayout(null);

		JLabel lblLista = new JLabel("Lista de Consultorios");
		lblLista.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/agregar (1).png")));
		lblLista.setForeground(new Color(0, 128, 128));
		lblLista.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblLista.setBounds(10, 8, 190, 24);
		panelJtable.add(lblLista);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 636, 148);
		panelJtable.add(scrollPane);

		tableConsultorios = new JTable();
		tableConsultorios.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "Nombre", "Ubicacion", "Piso", "Estado", "Codigo" }
		) {
			private static final long serialVersionUID = 1L;
			@Override public boolean isCellEditable(int row, int column) { return false; }
		});
		scrollPane.setViewportView(tableConsultorios);

		tableConsultorios.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && !modoNuevo) cargarSeleccionTabla();
		});

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/agregar.png")));
		btnAgregar.setBackground(new Color(0, 128, 128));
		btnAgregar.setBounds(10, 590, 100, 30);
		btnAgregar.addActionListener(e -> {
			modoNuevo = true;
			limpiarArriba();
			habilitarCampos(true);
			tableConsultorios.clearSelection();
			txtNombre.requestFocus();
		});
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/cuadrado-de-la-pluma.png")));
		btnModificar.setBackground(new Color(0, 128, 128));
		btnModificar.setBounds(120, 590, 100, 30);
		btnModificar.addActionListener(e -> {
			int fila = tableConsultorios.getSelectedRow();
			if (fila == -1) { msg("Seleccione un consultorio"); return; }

			modoNuevo = false;
			cargarSeleccionTabla();
			habilitarCampos(true);
			txtNombre.requestFocus();
		});
		contentPane.add(btnModificar);

		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar.setBackground(new Color(0, 128, 128));
		btnGrabar.setBounds(230, 590, 100, 30);
		btnGrabar.addActionListener(e -> {

			if (!validar()) return;

			String nombre = txtNombre.getText().trim();
			String ubic = txtUbicacion.getText().trim();
			int piso = Integer.parseInt(txtPiso.getText().trim());

			boolean ok = false;

			if (modoNuevo) {
				String sql = "INSERT INTO consultorio (nombre_consultorio, ubicacion_consultorio, piso_consultorio, capacidad_consultorio, estado_consultorio) "
						   + "VALUES (?, ?, ?, 1, 1)";

				try (Connection con = ConexionBD.conectar();
					 PreparedStatement ps = con.prepareStatement(sql)) {

					ps.setString(1, nombre);
					ps.setString(2, ubic);
					ps.setInt(3, piso);

					ok = ps.executeUpdate() > 0;

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			} else {

				if (txtCodigo.getText().trim().isEmpty()) { msg("Seleccione un consultorio"); return; }

				int cod = Integer.parseInt(txtCodigo.getText().trim());

				String sql = "UPDATE consultorio SET nombre_consultorio = ?, ubicacion_consultorio = ?, piso_consultorio = ? "
						   + "WHERE codConsultorio = ?";

				try (Connection con = ConexionBD.conectar();
					 PreparedStatement ps = con.prepareStatement(sql)) {

					ps.setString(1, nombre);
					ps.setString(2, ubic);
					ps.setInt(3, piso);
					ps.setInt(4, cod);

					ok = ps.executeUpdate() > 0;

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (!ok) {
				msg("No se pudo guardar");
				return;
			}

			msg("Guardado con exito");
			listarTabla();
			habilitarCampos(false);
			limpiarArriba();
			modoNuevo = false;
			tableConsultorios.clearSelection();

		});
		contentPane.add(btnGrabar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/escoba.png")));
		btnLimpiar.setBackground(new Color(128, 128, 0));
		btnLimpiar.setBounds(459, 590, 100, 30);
		btnLimpiar.addActionListener(e -> {
			limpiarArriba();
			habilitarCampos(false);
			modoNuevo = false;
			txtBuscar.setText("");
			buttonGroup.clearSelection();
			listarTabla();
			tableConsultorios.clearSelection();
		});
		contentPane.add(btnLimpiar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/img/cruz-pequena.png")));
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(569, 590, 100, 30);
		btnEliminar.addActionListener(e -> {

			int fila = tableConsultorios.getSelectedRow();
			if (fila == -1) { msg("Seleccione un consultorio"); return; }

			int cod = Integer.parseInt(tableConsultorios.getValueAt(fila, 4).toString());

			int r = JOptionPane.showConfirmDialog(
					this,
					"Desea eliminar este consultorio",
					"Confirmar",
					JOptionPane.YES_NO_OPTION
			);

			if (r != JOptionPane.YES_OPTION) return;

			String sql = "UPDATE consultorio SET estado_consultorio = 0 WHERE codConsultorio = ?";

			boolean ok = false;

			try (Connection con = ConexionBD.conectar();
				 PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, cod);
				ok = ps.executeUpdate() > 0;

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (ok) {
				msg("Eliminado");
				listarTabla();
				limpiarArriba();
				habilitarCampos(false);
				modoNuevo = false;
				tableConsultorios.clearSelection();
			} else {
				msg("No se pudo eliminar");
			}
		});
		contentPane.add(btnEliminar);

		txtEstado.setText("1");
		habilitarCampos(false);
		limpiarArriba();
		listarTabla();
	}
}