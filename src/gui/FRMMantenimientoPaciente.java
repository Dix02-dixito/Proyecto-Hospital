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

import controlador.*;
import controlador.PacienteDAO;
import controlador.CitaDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;

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
	//Arraylist
	private PacienteDAO ap = new PacienteDAO();
	
	private void cargarPacientesActivos() {

	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    modelo.setRowCount(0);

	    for (Paciente p : ap.listarActivos()) {

	        if (p.getEstado() == 1) {
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
	}
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
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(766, 0, 17, 560);
		contentPane.add(scrollBar);
		//boton modificar
		JButton btnModificar1 =  new JButton("Modificar");
		btnModificar1.setForeground(new Color(0, 0, 128));
		btnModificar1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int fila = table.getSelectedRow();

		        if (fila == -1) {
		            JOptionPane.showMessageDialog(null, "seleccione un paciente");
		            return;
		        }

		        // Obtiene el codigo (columna 7)
		        int codigo = Integer.parseInt(table.getValueAt(fila, 7).toString());

		        Paciente p = ap.buscarPorCodigo(codigo);

		        if (p == null) {
		            JOptionPane.showMessageDialog(null, "no se encontro el paciente");
		            return;
		        }

		        // Cargar datos en los campos
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
		        txtEstado1.setEnabled(false);

		        txtNombre1.requestFocus();
		    }
		});
		
		
		
		btnModificar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/cuadrado-de-la-pluma.png")));
		btnModificar1.setBackground(new Color(0, 128, 128));
		btnModificar1.setBounds(130, 685, 113, 30);
		contentPane.add(btnModificar1);
		
		//boton eliminar
		JButton btnEliminar1 = new JButton("Eliminar");
		btnEliminar1.setForeground(new Color(0, 0, 128));
		btnEliminar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo del boton eliminar
				CitaDao citaDao = new CitaDao();
				PacienteDAO pacienteDao = new PacienteDAO();

				int fila = table.getSelectedRow();

				if (fila == -1) {
				    JOptionPane.showMessageDialog(null, "Seleccione un paciente");
				    return;
				}

				int codPaciente = Integer.parseInt(
				        table.getValueAt(fila, 7).toString());

				// Validar citas pendientes
				if (citaDao.pacienteTieneCitasFuturas(codPaciente)) {
				    JOptionPane.showMessageDialog(null,
				        "No se puede eliminar. Tiene citas pendientes.");
				    return;
				}

				// Eliminación lógica
				boolean eliminado = pacienteDao.eliminarPaciente(codPaciente);

				if (eliminado) {
				    JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente");
				} else {
				    JOptionPane.showMessageDialog(null, "Error al eliminar");
				}
               
				 
			    }
		});
		btnEliminar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/cruz-pequena.png")));
		btnEliminar1.setBackground(new Color(128, 0, 0));
		btnEliminar1.setBounds(536, 685, 100, 30);
		contentPane.add(btnEliminar1);
		//boton grabar osea guardar cambios xd
		JButton btnGrabar1 = new JButton("Guardar");
		btnGrabar1.setForeground(new Color(0, 0, 128));
		btnGrabar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			

				        // validar campos
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
				            p.setEstado(1); // siempre activo al guardar

				            boolean ok = false;

				            // ========= NUEVO =========
				            if (modoNuevo) {

				                // validar DNI unico
				                if (ap.existeDniNuevo(dni)) {
				                    JOptionPane.showMessageDialog(null,
				                            "El DNI ya esta registrado",
				                            "Validacion",
				                            JOptionPane.WARNING_MESSAGE);
				                    txtDNI1.requestFocus();
				                    return;
				                }

				                ok = ap.insertarPaciente(p);

				                if (ok) {
				                    JOptionPane.showMessageDialog(null,
				                            "Paciente registrado correctamente");
				                }

				            }
				            // ========= MODIFICAR =========
				            else {

				                int codigo = Integer.parseInt(txtCodigo.getText());

				                // validar DNI unico excluyendo el actual
				                if (ap.existeDni(dni, codigo)) {
				                    JOptionPane.showMessageDialog(null,
				                            "El DNI ya esta registrado en otro paciente",
				                            "Validacion",
				                            JOptionPane.WARNING_MESSAGE);
				                    txtDNI1.requestFocus();
				                    return;
				                }

				                p.setCodPaciente(codigo);

				                ok = ap.actualizarPaciente(p);

				                if (ok) {
				                    JOptionPane.showMessageDialog(null,
				                            "Paciente actualizado correctamente");
				                }
				            }

				            if (!ok) {
				                JOptionPane.showMessageDialog(null,
				                        "No se pudo guardar el paciente",
				                        "Error",
				                        JOptionPane.ERROR_MESSAGE);
				                return;
				            }

				            // refrescar tabla
				            cargarPacientesActivos();

				            // bloquear campos y salir de modo nuevo
				            habilitarCampos(false);
				            limpiarCamposArriba();
				            modoNuevo = false;

				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null,
				                    "Edad invalida",
				                    "Error",
				                    JOptionPane.ERROR_MESSAGE);
				        } catch (Exception ex) {
				            ex.printStackTrace();
				            JOptionPane.showMessageDialog(null,
				                    "Error inesperado",
				                    "Error",
				                    JOptionPane.ERROR_MESSAGE);
				        }
				    }
				});
		
		btnGrabar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
		btnGrabar1.setBackground(new Color(0, 128, 128));
		btnGrabar1.setBounds(260, 685, 100, 30);
		contentPane.add(btnGrabar1);
		//Boton para volver a la ventana principal
		JButton btnSalir = new JButton("Salir / Volver");
		btnSalir.setForeground(new Color(220, 20, 60));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo para volver a la ventana principal
				FRMPrincipal principal = new FRMPrincipal();
				principal.setVisible(true);

				dispose(); // cierra la ventana actual
				
			}
		});
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnSalir.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/salida.png")));
		btnSalir.setBackground(new Color(220, 220, 220));
		btnSalir.setBounds(536, 21, 150, 35);
		contentPane.add(btnSalir);
		//boton limpiar
		JButton btnLimpiar1 = new JButton("Limpiar");
		btnLimpiar1.setForeground(new Color(0, 0, 128));
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
		btnLimpiar1.setBackground(new Color(128, 128, 0));
		btnLimpiar1.setBounds(429, 685, 100, 30);
		contentPane.add(btnLimpiar1);
		
		//agrupar radio buttons para que no se puede marcar los 2 a la vez
		ButtonGroup bg = new ButtonGroup();
		
		
		//BOTON NUEVO
		JButton btnAgregar = new JButton("Nuevo");
		btnAgregar.setForeground(new Color(0, 0, 128));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnAgregar.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {

				        modoNuevo = true;

				        // limpiar y habilitar
				        limpiarCamposArriba();
				        habilitarCampos(true);

				        // quitar seleccion de la tabla (por si habia una fila marcada)
				        table.clearSelection();

				        // foco al primer campo
				        txtNombre1.requestFocus();
				    }
				});
			}
		});
		btnAgregar.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/agregar.png")));
		btnAgregar.setBackground(new Color(0, 128, 128));
		btnAgregar.setBounds(20, 685, 100, 30);
		contentPane.add(btnAgregar);
		
		JPanel panelModificacion = new JPanel();
		panelModificacion.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new LineBorder(new Color(0, 255, 255))));
		panelModificacion.setBackground(new Color(220, 220, 220));
		panelModificacion.setBounds(20, 73, 621, 358);
		contentPane.add(panelModificacion);
		panelModificacion.setLayout(null);
		
		JLabel lblDNI1 = new JLabel("DNI");
		lblDNI1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/DNI.png")));
		lblDNI1.setBounds(12, 58, 60, 20);
		panelModificacion.add(lblDNI1);
		lblDNI1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Correo.png")));
		lblCorreo.setBounds(12, 120, 70, 20);
		panelModificacion.add(lblCorreo);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JLabel lblsubTitulo1 = new JLabel("Modificacion / Agregar");
		lblsubTitulo1.setForeground(new Color(0, 128, 128));
		lblsubTitulo1.setBounds(10, 11, 222, 27);
		panelModificacion.add(lblsubTitulo1);
		lblsubTitulo1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/mantenimiento.png")));
		lblsubTitulo1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 268, 558, 60);
		panelModificacion.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 128, 128), 3));
		panel.setLayout(null);
		
		
		
		JRadioButton rdbtnDNI = new JRadioButton("DNI");
		rdbtnDNI.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnDNI.setBounds(23, 20, 57, 23);
		panel.add(rdbtnDNI);
		
		JRadioButton rdbtnApellidos1 = new JRadioButton("Apellidos");
		rdbtnApellidos1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnApellidos1.setBounds(82, 20, 86, 23);
		panel.add(rdbtnApellidos1);
		bg.add(rdbtnDNI);
		bg.add(rdbtnApellidos1);
		
		
				
				txtBuscar1 = new JTextField();
				txtBuscar1.setBounds(193, 20, 200, 25);
				panel.add(txtBuscar1);
				txtBuscar1.setBackground(SystemColor.scrollbar);
				txtBuscar1.setColumns(10);
				
				//boton buscar con base de datos
				JButton btnBuscar1 = new JButton("Buscar");
				btnBuscar1.setForeground(new Color(128, 128, 0));
				btnBuscar1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					    String valor = txtBuscar1.getText().trim();
             //valida que el campo no este vacio y si esta vacio te manda la alerta
					    if (valor.isEmpty()) {
					        JOptionPane.showMessageDialog(null, "Ingrese dato para buscar");
					        txtBuscar1.requestFocus();
					        return;
					    }
            //
					    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					    modelo.setRowCount(0);

					    try {

					        //  Buscar por DNI
					        if (rdbtnDNI.isSelected()) {

					            Paciente p = ap.buscarPorDni(valor);
                              
					            if (p == null) {
					                JOptionPane.showMessageDialog(null, "DNI NO EXISTENTE");
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
                     //usamos lista por que puede aver varios pacientes con el mismo apellido
					            ArrayList<Paciente> lista = ap.buscarPorApellido(valor);
                     //Si no encuentro el apellido ingresado
					            if (lista.isEmpty()) {
					                JOptionPane.showMessageDialog(null, "Apellido no existente");
					                return;
					            }
             //Si hay resultados los recorre y los devuelve
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
                    //alerta si no ah seleccionado ni dni ni apellido
					        else {
					            JOptionPane.showMessageDialog(null, "Seleccione DNI o Apellidos");
					        }
               //si hubo un error al buscar
					    } catch (Exception ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage());
					    }
					}
				});
				
				
						btnBuscar1.setBounds(443, 20, 100, 30);
						panel.add(btnBuscar1);
						btnBuscar1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/IMG/busqueda.png")));
						btnBuscar1.setBackground(SystemColor.info);
						
						JLabel lblConsulta1 = new JLabel("Consulta Paciente");
						lblConsulta1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/consulta.png")));
						lblConsulta1.setForeground(new Color(0, 128, 128));
						lblConsulta1.setFont(new Font("Segoe UI", Font.BOLD, 14));
						lblConsulta1.setBounds(12, 236, 148, 31);
						panelModificacion.add(lblConsulta1);
						
						txtCorreo1 = new JTextField();
						txtCorreo1.setBounds(10, 139, 200, 25);
						panelModificacion.add(txtCorreo1);
						txtCorreo1.setColumns(10);
						txtCorreo1.setEnabled(false);
						
						txtDNI1 = new JTextField();
						txtDNI1.setBounds(10, 78, 150, 25);
						panelModificacion.add(txtDNI1);
						txtDNI1.setColumns(10);
						txtDNI1.setEnabled(false);
						
						JLabel lblEstado1 = new JLabel("Estado");
						lblEstado1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/estado.png")));
						lblEstado1.setBounds(12, 169, 70, 20);
						panelModificacion.add(lblEstado1);
						lblEstado1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
						
						txtEstado1 = new JTextField();
						txtEstado1.setBounds(10, 190, 100, 25);
						panelModificacion.add(txtEstado1);
						txtEstado1.setColumns(10);
						txtEstado1.setEnabled(false);
						
						txtCodigo = new JTextField();
						txtCodigo.setBounds(120, 190, 100, 25);
						panelModificacion.add(txtCodigo);
						txtCodigo.setColumns(10);
						txtCodigo.setEnabled(false);
						
						JLabel lblCodigo = new JLabel("Codigo");
						lblCodigo.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Codigo.png")));
						lblCodigo.setBounds(122, 172, 70, 17);
						panelModificacion.add(lblCodigo);
						lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
						
						txtTelefono1 = new JTextField();
						txtTelefono1.setBounds(220, 141, 200, 25);
						panelModificacion.add(txtTelefono1);
						txtTelefono1.setColumns(10);
						txtTelefono1.setEnabled(false);
						
						txtNombre1 = new JTextField();
						txtNombre1.setBounds(170, 78, 200, 25);
						panelModificacion.add(txtNombre1);
						txtNombre1.setColumns(10);
						
						//deshabilita los campos
						
						txtNombre1.setEnabled(false);
						
						JLabel lblNombre1 = new JLabel("Nombres");
						lblNombre1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/NombreApellidos.png")));
						lblNombre1.setBounds(172, 60, 83, 17);
						panelModificacion.add(lblNombre1);
						lblNombre1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
						
						JLabel lblApellidos1 = new JLabel("Apellidos");
						lblApellidos1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/NombreApellidos.png")));
						lblApellidos1.setBounds(382, 58, 83, 21);
						panelModificacion.add(lblApellidos1);
						lblApellidos1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
						
						txtApellidos1 = new JTextField();
						txtApellidos1.setBounds(380, 80, 200, 25);
						panelModificacion.add(txtApellidos1);
						txtApellidos1.setColumns(10);
						txtApellidos1.setEnabled(false);
						
						txtEdad1 = new JTextField();
						txtEdad1.setBounds(430, 141, 100, 25);
						panelModificacion.add(txtEdad1);
						txtEdad1.setColumns(10);
						txtEdad1.setEnabled(false);
						
						JLabel lblEdad1 = new JLabel("Edad");
						lblEdad1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Edades.png")));
						lblEdad1.setBounds(431, 124, 60, 14);
						panelModificacion.add(lblEdad1);
						lblEdad1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
						
						JSeparator separator = new JSeparator();
						separator.setBounds(28, 36, 214, 2);
						panelModificacion.add(separator);
						
						JLabel lblTelefono1 = new JLabel("Telefono");
						lblTelefono1.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/Telefono.png")));
						lblTelefono1.setBounds(222, 121, 83, 19);
						panelModificacion.add(lblTelefono1);
						lblTelefono1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
						
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new LineBorder(new Color(0, 128, 128))));
						panel_1.setBounds(21, 454, 620, 220);
						contentPane.add(panel_1);
						panel_1.setLayout(null);
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(10, 34, 600, 175);
						panel_1.add(scrollPane);
						
						table = new JTable();
						table.setBorder(new LineBorder(new Color(47, 79, 79), 2));
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
						
						JLabel lbllistaPacientes = new JLabel("Lista de Pacientes");
						lbllistaPacientes.setIcon(new ImageIcon(FRMMantenimientoPaciente.class.getResource("/img/agregar (1).png")));
						lbllistaPacientes.setForeground(new Color(0, 128, 128));
						lbllistaPacientes.setFont(new Font("Segoe UI", Font.BOLD, 14));
						lbllistaPacientes.setBounds(12, 5, 191, 31);
						panel_1.add(lbllistaPacientes);
		
			
	}
}
	
		
