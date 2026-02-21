package gui;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.util.ArrayList;
import entidad.Paciente;
import gui.FRMPrincipal;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
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
import java.util.List;
import dao.PacienteDAO;

import controlador.ArregloPaciente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	//Arraylist
	private PacienteDAO ap = new PacienteDAO();
	
	//metodo validacion de campos vacios
	private boolean validarCampos() {
	    String dni = txtDNI1.getText().trim();
	    String nombres = txtNombre1.getText().trim();
	    String apellidos = txtApellidos1.getText().trim();
	    String edadTxt = txtEdad1.getText().trim();
	    String estadoTxt = txtEstado1.getText().trim();

	    // 🔹 Campos vacíos
	    if (dni.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() 
	            || edadTxt.isEmpty() || estadoTxt.isEmpty()) {
	        JOptionPane.showMessageDialog(this,
	                "Debe rellenar todos los campos obligatorios",
	                "Campos vacíos",
	                JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    // 🔹 DNI: 8 dígitos numéricos
	    if (!dni.matches("\\d{8}")) {
	        JOptionPane.showMessageDialog(this,
	                "El DNI debe tener 8 dígitos numéricos",
	                "Error DNI",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // 🔹 Nombres y Apellidos: solo letras y espacios
	    if (!nombres.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
	        JOptionPane.showMessageDialog(this,
	                "El nombre solo puede contener letras y espacios",
	                "Error Nombre",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!apellidos.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
	        JOptionPane.showMessageDialog(this,
	                "Los apellidos solo pueden contener letras y espacios",
	                "Error Apellido",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // 🔹 Edad: número entre 1 y 120
	    try {
	        int edad = Integer.parseInt(edadTxt);
	        if (edad <= 0 || edad > 120) {
	            JOptionPane.showMessageDialog(this,
	                    "Edad no válida",
	                    "Error Edad",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this,
	                "Edad debe ser numérica",
	                "Error Edad",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // 🔹 Estado: solo número (ejemplo 0 o 1)
	    try {
	        int estado = Integer.parseInt(estadoTxt);
	        if (estado < 0 || estado > 1) { // Ajusta según tu lógica
	            JOptionPane.showMessageDialog(this,
	                    "Estado no válido",
	                    "Error Estado",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this,
	                "Estado debe ser numérico",
	                "Error Estado",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    return true; // Todo correcto
	}

	

	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMMantenimientoPaciente frame = new FRMMantenimientoPaciente();
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
	public FRMMantenimientoPaciente() {
		setTitle("Mantenimiento Pacientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMantenimientoPaciente.class.getResource("/IMG/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo1 = new JLabel("Hospital Hermilio");
		lblTitulo1.setForeground(new Color(0, 128, 128));
		lblTitulo1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		lblTitulo1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/icon.png")));
		lblTitulo1.setBounds(10, 11, 160, 35);
		contentPane.add(lblTitulo1);
		
		JLabel lblsubTitulo1 = new JLabel("Mantenimiento Pacientes");
		lblsubTitulo1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/settings (2).png")));
		lblsubTitulo1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblsubTitulo1.setBounds(20, 60, 222, 27);
		contentPane.add(lblsubTitulo1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 82, 214, 2);
		contentPane.add(separator);
		
		JLabel lblNombre1 = new JLabel("Nombres");
		lblNombre1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblNombre1.setBounds(10, 98, 53, 17);
		contentPane.add(lblNombre1);
		
		txtNombre1 = new JTextField();
		txtNombre1.setBounds(70, 98, 150, 20);
		contentPane.add(txtNombre1);
		txtNombre1.setColumns(10);
		
		JLabel lblApellidos1 = new JLabel("Apellidos");
		lblApellidos1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblApellidos1.setBounds(249, 98, 60, 14);
		contentPane.add(lblApellidos1);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCorreo.setBounds(10, 174, 46, 27);
		contentPane.add(lblCorreo);
		
		txtApellidos1 = new JTextField();
		txtApellidos1.setColumns(10);
		txtApellidos1.setBounds(319, 95, 150, 20);
		contentPane.add(txtApellidos1);
		
		JLabel lblTelefono1 = new JLabel("Telefono");
		lblTelefono1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblTelefono1.setBounds(369, 138, 60, 27);
		contentPane.add(lblTelefono1);
		
		JLabel lblDNI1 = new JLabel("DNI");
		lblDNI1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblDNI1.setBounds(10, 136, 30, 30);
		contentPane.add(lblDNI1);
		
		txtDNI1 = new JTextField();
		txtDNI1.setBounds(70, 136, 100, 20);
		contentPane.add(txtDNI1);
		txtDNI1.setColumns(10);
		
		JLabel lblEstado1 = new JLabel("Estado");
		lblEstado1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEstado1.setBounds(208, 136, 46, 27);
		contentPane.add(lblEstado1);
		
		JLabel lblEdad1 = new JLabel("Edad");
		lblEdad1.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblEdad1.setBounds(488, 90, 46, 30);
		contentPane.add(lblEdad1);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		lblCodigo.setBounds(379, 174, 50, 17);
		contentPane.add(lblCodigo);
		
		txtEstado1 = new JTextField();
		txtEstado1.setColumns(10);
		txtEstado1.setBounds(259, 136, 100, 20);
		contentPane.add(txtEstado1);
		
		txtCorreo1 = new JTextField();
		txtCorreo1.setColumns(10);
		txtCorreo1.setBounds(70, 174, 289, 20);
		contentPane.add(txtCorreo1);
		
		txtEdad1 = new JTextField();
		txtEdad1.setColumns(10);
		txtEdad1.setBounds(535, 95, 46, 20);
		contentPane.add(txtEdad1);
		
		txtTelefono1 = new JTextField();
		txtTelefono1.setColumns(10);
		txtTelefono1.setBounds(434, 136, 100, 20);
		contentPane.add(txtTelefono1);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(434, 174, 100, 20);
		contentPane.add(txtCodigo);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(766, 0, 17, 560);
		contentPane.add(scrollBar);
		
		JLabel lblConsulta1 = new JLabel("Consulta");
		lblConsulta1.setBounds(33, 216, 46, 14);
		contentPane.add(lblConsulta1);
		//boton modificar
		JButton btnModificar1 =  new JButton("Modificar");
		btnModificar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int fila = table.getSelectedRow();

		        if (fila == -1) {
		            JOptionPane.showMessageDialog(null, "Seleccione un paciente de la tabla");
		            return;
		        }

		        // Obtener código del paciente de la tabla
		        int codigo = Integer.parseInt(table.getValueAt(fila, 7).toString());

		        // Buscar paciente en el arreglo
		        Paciente p = ap.buscarPorCodigo(codigo);
		        if (p == null) {
		            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
		            return;
		        }

		        // Cargar datos en los campos de texto
		        txtCodigo.setText(String.valueOf(p.getCodPaciente()));
		        txtNombre1.setText(p.getNombres());
		        txtApellidos1.setText(p.getApellidos());
		        txtDNI1.setText(p.getDni());
		        txtEdad1.setText(String.valueOf(p.getEdad()));
		        txtTelefono1.setText(p.getCelular());
		        txtCorreo1.setText(p.getCorreo());
		        txtEstado1.setText(String.valueOf(p.getEstado()));

		        // Habilitar campos para editar
		        txtNombre1.setEnabled(true);
		        txtApellidos1.setEnabled(true);
		        txtDNI1.setEnabled(true);
		        txtEdad1.setEnabled(true);
		        txtTelefono1.setEnabled(true);
		        txtCorreo1.setEnabled(true);
		        txtEstado1.setEnabled(true);

		        JOptionPane.showMessageDialog(null, "Modifique los campos y presione 'Grabar' para guardar");
		    }
				 
			});
		
		
		
		btnModificar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar1.setBackground(SystemColor.inactiveCaptionText);
		btnModificar1.setBounds(143, 309, 100, 23);
		contentPane.add(btnModificar1);
		
		//boton eliminar
		JButton btnEliminar1 = new JButton("Eliminar");
		btnEliminar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo del boton eliminar
				 int fila = table.getSelectedRow();

			        if (fila == -1) {
			            JOptionPane.showMessageDialog(null, "Seleccione un paciente para eliminar");
			            return;
			        }

			        // Confirmación de eliminación
			        int opcion = JOptionPane.showConfirmDialog(null,
			                "¿Está seguro que desea eliminar este paciente?",
			                "Confirmar eliminación",
			                JOptionPane.YES_NO_OPTION);

			        if (opcion != JOptionPane.YES_OPTION) return;

			        // Obtener el código del paciente seleccionado
			        int codigo = Integer.parseInt(table.getValueAt(fila, 7).toString());

			        // Buscar paciente en el arreglo
			        Paciente p = ap.buscarPorCodigo(codigo);
			        if (p != null) {
			            ap.eliminar(p); // Cambia estado a 0
			            JOptionPane.showMessageDialog(null,
			                    "Paciente eliminado correctamente",
			                    "Éxito",
			                    JOptionPane.INFORMATION_MESSAGE);
			        } else {
			            JOptionPane.showMessageDialog(null,
			                    "Paciente no encontrado",
			                    "Error",
			                    JOptionPane.ERROR_MESSAGE);
			        }

			        // Actualizar tabla mostrando solo pacientes activos
			        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			        modelo.setRowCount(0);

			        for (int i = 0; i < ap.tamanio(); i++) {
			            Paciente px = ap.obtener(i);
			            if (px.getEstado() != 0) { // Mostrar solo activos
			                Object[] filaTabla = {
			                        px.getNombres(),
			                        px.getApellidos(),
			                        px.getEdad(),
			                        px.getDni(),
			                        px.getEstado(),
			                        px.getCelular(),
			                        px.getCorreo(),
			                        px.getCodPaciente()
			                };
			                modelo.addRow(filaTabla);
			            }
			        }
			    }
		});
		btnEliminar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar1.setBackground(SystemColor.inactiveCaptionText);
		btnEliminar1.setBounds(369, 309, 100, 23);
		contentPane.add(btnEliminar1);
		//boton grabar osea guardar cambios xd
		JButton btnGrabar1 = new JButton("Grabar");
		btnGrabar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        // Validación
		        if (!validarCampos()) return;

		        try {
		            int codigo = Integer.parseInt(txtCodigo.getText().trim());
		            int edad = Integer.parseInt(txtEdad1.getText().trim());
		            int estado = Integer.parseInt(txtEstado1.getText().trim());

		            // 🔹 Buscar paciente existente
		            Paciente p = ap.buscarPorCodigo(codigo);
		            if (p == null) {
		                JOptionPane.showMessageDialog(null,
		                        "Paciente no encontrado para actualizar",
		                        "Error",
		                        JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // 🔹 Actualizar campos del paciente
		            p.setNombres(txtNombre1.getText().trim());
		            p.setApellidos(txtApellidos1.getText().trim());
		            p.setDni(txtDNI1.getText().trim());
		            p.setEdad(edad);
		            p.setCelular(txtTelefono1.getText().trim());
		            p.setCorreo(txtCorreo1.getText().trim());
		            p.setEstado(estado);

		            JOptionPane.showMessageDialog(null,
		                    "Paciente actualizado correctamente",
		                    "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);

		            // 🔹 Actualizar tabla
		            DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		            modelo.setRowCount(0);
		            for (int i = 0; i < ap.tamanio(); i++) {
		                Paciente px = ap.obtener(i);
		                Object[] fila = {
		                        px.getNombres(),
		                        px.getApellidos(),
		                        px.getEdad(),
		                        px.getDni(),
		                        px.getEstado(),
		                        px.getCelular(),
		                        px.getCorreo(),
		                        px.getCodPaciente()
		                };
		                modelo.addRow(fila);
		            }

		            // 🔹 Bloquear campos nuevamente
		            txtNombre1.setEnabled(false);
		            txtApellidos1.setEnabled(false);
		            txtDNI1.setEnabled(false);
		            txtEdad1.setEnabled(false);
		            txtTelefono1.setEnabled(false);
		            txtCorreo1.setEnabled(false);
		            txtEstado1.setEnabled(false);

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null,
		                    "Edad y Estado deben ser números válidos",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
			});
		btnGrabar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar1.setBackground(SystemColor.inactiveCaptionText);
		btnGrabar1.setBounds(259, 309, 100, 23);
		contentPane.add(btnGrabar1);
		//Boton para volver a la ventana principal
		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo para volver a la ventana principal
				FRMPrincipal principal = new FRMPrincipal();
				principal.setVisible(true);

				dispose(); // Cierra el formulario actual
				
			}
		});
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(SystemColor.inactiveCaptionText);
		btnSalir.setBounds(467, 30, 135, 35);
		contentPane.add(btnSalir);
		//boton limpiar
		JButton btnLimpiar1 = new JButton("Limpiar");
		btnLimpiar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//codigo de el boton limpiar
				// Limpiar todos los campos de texto
		        txtCodigo.setText("");
		        txtNombre1.setText("");
		        txtApellidos1.setText("");
		        txtDNI1.setText("");
		        txtEdad1.setText("");
		        txtTelefono1.setText("");
		        txtCorreo1.setText("");
		        txtEstado1.setText("");
		        txtBuscar1.setText("");

		        // vuelve a deshabilitar los campos
		        txtNombre1.setEnabled(false);
		        txtApellidos1.setEnabled(false);
		        txtDNI1.setEnabled(false);
		        txtEdad1.setEnabled(false);
		        txtTelefono1.setEnabled(false);
		        txtCorreo1.setEnabled(false);
		        txtEstado1.setEnabled(false);
		    }
		});
		btnLimpiar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/escoba.png")));
		btnLimpiar1.setBackground(SystemColor.inactiveCaptionText);
		btnLimpiar1.setBounds(488, 309, 100, 23);
		contentPane.add(btnLimpiar1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 362, 570, 137);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombres", "Apellidos", "Edad", "Dni", "Estado", "Telefono", "Correo", "Codigo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(30, 231, 504, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		JRadioButton rdbtnDNI = new JRadioButton("DNI");
		rdbtnDNI.setBounds(6, 14, 57, 23);
		panel.add(rdbtnDNI);
		
		JRadioButton rdbtnApellidos1 = new JRadioButton("Apellidos");
		rdbtnApellidos1.setBounds(65, 14, 67, 23);
		panel.add(rdbtnApellidos1);
		
		//agrupar radio buttons para que no se puede marcar los 2 a la vez
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnDNI);
		bg.add(rdbtnApellidos1);


		
		txtBuscar1 = new JTextField();
		txtBuscar1.setBounds(141, 15, 222, 20);
		panel.add(txtBuscar1);
		txtBuscar1.setBackground(SystemColor.scrollbar);
		txtBuscar1.setColumns(10);
		
		//boton buscar con base de datos
		JButton btnBuscar1 = new JButton("Buscar");
		btnBuscar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			    String valor = txtBuscar1.getText().trim();

			    if (valor.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Ingrese dato para buscar");
			        txtBuscar1.requestFocus();
			        return;
			    }

			    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			    modelo.setRowCount(0);

			    try {

			        //  Buscar por DNI
			        if (rdbtnDNI.isSelected()) {

			            Paciente p = ap.buscarPorDni(valor);

			            if (p == null) {
			                JOptionPane.showMessageDialog(null, "DNI no existente");
			                return;
			            }

			            modelo.addRow(new Object[]{
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

			        //  Buscar por Apellido
			        else if (rdbtnApellidos1.isSelected()) {

			            ArrayList<Paciente> lista = ap.buscarPorApellido(valor);

			            if (lista.isEmpty()) {
			                JOptionPane.showMessageDialog(null, "Apellido no existente");
			                return;
			            }

			            for (Paciente p : lista) {
			                modelo.addRow(new Object[]{
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

			        else {
			            JOptionPane.showMessageDialog(null, "Seleccione DNI o Apellidos");
			        }

			    } catch (Exception ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage());
			    }
			}
		});


		btnBuscar1.setBounds(389, 14, 89, 23);
		panel.add(btnBuscar1);
		btnBuscar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/busqueda.png")));
		btnBuscar1.setBackground(SystemColor.inactiveCaptionText);
		
		//deshabilita los campos
		
		txtNombre1.setEnabled(false);
		txtApellidos1.setEnabled(false);
		txtDNI1.setEnabled(false);
		txtEdad1.setEnabled(false);
		txtTelefono1.setEnabled(false);
		txtCorreo1.setEnabled(false);
		txtEstado1.setEnabled(false);
		txtCodigo.setEnabled(false);
		
		
		//BOTON NUEVO
		JButton btnAgregar = new JButton("Nuevo");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/agregar.png")));
		btnAgregar.setBackground(SystemColor.inactiveCaptionText);
		btnAgregar.setBounds(33, 309, 100, 23);
		contentPane.add(btnAgregar);
		
		

		//conexion a la bd
		PacienteDAO ap = new PacienteDAO();
		
	}
}
