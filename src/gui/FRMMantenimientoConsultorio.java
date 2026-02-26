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

public class FRMMantenimientoConsultorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtUbicacion;
	private JTextField txtPiso;
	private JTextField txtEstado;
	private JTextField txtCodigo;
	private JTable table;
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
		setBounds(100, 100, 631, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TITULO
		JLabel lblTitulo = new JLabel("Hospital Hermilio");
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		lblTitulo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/icon.png")));
		lblTitulo.setBounds(10, 11, 160, 35);
		contentPane.add(lblTitulo);

		JLabel lblSubTitulo = new JLabel("Mantenimiento Consultorios");
		lblSubTitulo.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/settings (2).png")));
		lblSubTitulo.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblSubTitulo.setBounds(20, 60, 260, 27);
		contentPane.add(lblSubTitulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 82, 260, 2);
		contentPane.add(separator);

		// CAMPOS
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblNombre.setBounds(10, 98, 60, 17);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(70, 98, 150, 20);
		contentPane.add(txtNombre);

		JLabel lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblUbicacion.setBounds(249, 98, 70, 17);
		contentPane.add(lblUbicacion);

		txtUbicacion = new JTextField();
		txtUbicacion.setBounds(319, 95, 150, 20);
		contentPane.add(txtUbicacion);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblPiso.setBounds(10, 136, 46, 27);
		contentPane.add(lblPiso);

		txtPiso = new JTextField();
		txtPiso.setBounds(70, 136, 100, 20);
		contentPane.add(txtPiso);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEstado.setBounds(208, 136, 60, 27);
		contentPane.add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(268, 136, 120, 20);
		contentPane.add(txtEstado);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCodigo.setBounds(419, 141, 50, 17);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(488, 136, 100, 20);
		contentPane.add(txtCodigo);

		// CONSULTA
		JLabel lblConsulta = new JLabel("Consulta");
		lblConsulta.setBounds(33, 216, 60, 14);
		contentPane.add(lblConsulta);

		// BOTONES
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/agregar.png")));
		btnAgregar.setBackground(new Color(0, 128, 128));
		btnAgregar.setBounds(35, 309, 100, 23);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar.setBackground(new Color(0, 128, 128));
		btnModificar.setBounds(154, 309, 100, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar.setBackground(new Color(0, 128, 128));
		btnEliminar.setBounds(264, 309, 100, 23);
		contentPane.add(btnEliminar);

		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar.setBackground(new Color(0, 128, 128));
		btnGrabar.setBounds(379, 309, 100, 23);
		contentPane.add(btnGrabar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/escoba.png")));
		btnLimpiar.setBackground(new Color(0, 128, 128));
		btnLimpiar.setBounds(488, 309, 100, 23);
		contentPane.add(btnLimpiar);

		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setForeground(new Color(128, 0, 0));
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(new Color(128, 128, 128));
		btnSalir.setBounds(457, 30, 145, 35);
		contentPane.add(btnSalir);

		// TABLA
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 362, 570, 137);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Nombre", "Ubicacion", "Piso", "Estado", "Codigo"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(43, 233, 545, 51);
		contentPane.add(panel);
				panel.setLayout(null);
		
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.setForeground(new Color(128, 128, 0));
				btnBuscar.setBounds(435, 10, 85, 25);
				panel.add(btnBuscar);
				btnBuscar.setIcon(new ImageIcon(FRMMantenimientoConsultorio.class.getResource("/IMG/busqueda.png")));
				btnBuscar.setBackground(SystemColor.info);
				
						txtBuscar = new JTextField();
						txtBuscar.setBounds(147, 10, 254, 20);
						panel.add(txtBuscar);
						txtBuscar.setBackground(SystemColor.scrollbar);
						
						JRadioButton rdbtnNewRadioButton = new JRadioButton("Codigo");
						rdbtnNewRadioButton.setBounds(72, 10, 69, 23);
						panel.add(rdbtnNewRadioButton);
						
								JRadioButton rdbtnNombre = new JRadioButton("Nombre");
								rdbtnNombre.setBounds(6, 10, 80, 23);
								panel.add(rdbtnNombre);
								buttonGroup.add(rdbtnNombre);
	}
}
