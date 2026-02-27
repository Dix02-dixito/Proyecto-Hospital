package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.CitaDao;
import controlador.ConsultorioDao;
import controlador.MedicoDao;
import controlador.PacienteDAO;
import entidad.Cita;

public class FRMRegistroCitas extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtNcita;
    private JTextField txtfecha;
    private JTextField txtHora;
    private JTextField txtMotivo;

    private JComboBox<String> cbPaciente;
    private JComboBox<String> cbMedico;
    private JComboBox<String> cbConsultorio;

    private JTable tbldatosModificados;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    
    //DAOS
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDao medicoDao = new MedicoDao();
    private final ConsultorioDao consultorioDao = new ConsultorioDao();
    private final CitaDao citaDao = new CitaDao();
    
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FRMRegistroCitas frame = new FRMRegistroCitas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }
    

    public FRMRegistroCitas() {
        setTitle("Registro de Citas");
        setIconImage(Toolkit.getDefaultToolkit().getImage(FRMRegistroCitas.class.getResource("/IMG/icon.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 762, 700);

        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSalir = new JButton("Salir / Volver");
        btnSalir.setVerticalAlignment(SwingConstants.TOP);
        btnSalir.setBounds(582, 23, 150, 30);
        btnSalir.setForeground(new Color(128, 0, 0));
        btnSalir.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/salida.png")));
        btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        btnSalir.setBackground(new Color(192, 192, 192));
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
                FRMPrincipal principal = new FRMPrincipal();
                principal.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnSalir);

        JLabel lblTitulo = new JLabel("Registro de Citas");
        lblTitulo.setBounds(0, 5, 268, 59);
        lblTitulo.setForeground(new Color(0, 128, 128));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/img/logo.png")));
        contentPane.add(lblTitulo);

        // ===================== PANEL REGISTRAR =====================
        JPanel panelRegistrarCitas = new JPanel();
        panelRegistrarCitas.setBorder(new CompoundBorder(
                new LineBorder(new Color(128, 128, 128)),
                new LineBorder(new Color(0, 206, 209))
        ));
        panelRegistrarCitas.setBackground(new Color(220, 220, 220));
        panelRegistrarCitas.setBounds(20, 73, 686, 296);
        contentPane.add(panelRegistrarCitas);
        panelRegistrarCitas.setLayout(null);

        JLabel lblRegistrar = new JLabel("Registrar Citas");
        lblRegistrar.setBounds(10, 11, 153, 27);
        lblRegistrar.setForeground(new Color(0, 128, 128));
        lblRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblRegistrar.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/img/imglogo33.png")));
        panelRegistrarCitas.add(lblRegistrar);

        JLabel lblCita = new JLabel("N° Cita");
        lblCita.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblCita.setBounds(12, 49, 62, 14);
        panelRegistrarCitas.add(lblCita);

        txtNcita = new JTextField();
        txtNcita.setBounds(10, 68, 100, 25);
        txtNcita.setBackground(new Color(245, 255, 250));
        txtNcita.setColumns(10);
        txtNcita.setEditable(false);
        panelRegistrarCitas.add(txtNcita);

        JLabel lblPaciente = new JLabel("Paciente");
        lblPaciente.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblPaciente.setBounds(144, 48, 90, 17);
        lblPaciente.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/img/NombreApellidos.png")));
        panelRegistrarCitas.add(lblPaciente);

        cbPaciente = new JComboBox<>();
        cbPaciente.setBounds(142, 69, 250, 25);
        panelRegistrarCitas.add(cbPaciente);

        JLabel lblMedico = new JLabel("Medico");
        lblMedico.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblMedico.setBounds(424, 48, 90, 16);
        lblMedico.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/img/EspecialidadMedico.png")));
        panelRegistrarCitas.add(lblMedico);

        cbMedico = new JComboBox<>();
        cbMedico.setBounds(422, 68, 250, 25);
        panelRegistrarCitas.add(cbMedico);

        JLabel lblConsultorio = new JLabel("Consultorio");
        lblConsultorio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblConsultorio.setBounds(12, 114, 120, 24);
        lblConsultorio.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/img/consultorio-medico.png")));
        panelRegistrarCitas.add(lblConsultorio);

        cbConsultorio = new JComboBox<>();
        cbConsultorio.setBounds(10, 137, 200, 25);
        cbConsultorio.setBackground(new Color(245, 255, 250));
        panelRegistrarCitas.add(cbConsultorio);

        JLabel lblMotivo = new JLabel("Motivo");
        lblMotivo.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/img/Motivo.png")));
        lblMotivo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblMotivo.setBounds(246, 114, 90, 24);
        panelRegistrarCitas.add(lblMotivo);

        txtMotivo = new JTextField();
        txtMotivo.setColumns(10);
        txtMotivo.setBackground(new Color(245, 255, 250));
        txtMotivo.setBounds(244, 137, 428, 25);
        panelRegistrarCitas.add(txtMotivo);

        JLabel lblHorario = new JLabel(" Horario");
        lblHorario.setForeground(new Color(0, 128, 128));
        lblHorario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblHorario.setBounds(10, 192, 80, 20);
        lblHorario.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/calendar.png")));
        panelRegistrarCitas.add(lblHorario);

        JSeparator separator2 = new JSeparator();
        separator2.setForeground(new Color(105, 105, 105));
        separator2.setBackground(new Color(105, 105, 105));
        separator2.setBounds(10, 212, 226, 16);
        panelRegistrarCitas.add(separator2);

        JLabel lblFecha = new JLabel("Fecha YYYY-MM-DD");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFecha.setBounds(12, 225, 120, 25);
        lblFecha.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/calendario.png")));
        panelRegistrarCitas.add(lblFecha);

        txtfecha = new JTextField();
        txtfecha.setBounds(10, 250, 200, 25);
        txtfecha.setBackground(new Color(245, 255, 250));
        txtfecha.setColumns(10);
        panelRegistrarCitas.add(txtfecha);

        JLabel lblHora = new JLabel("Hora HH:MM:SS");
        lblHora.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblHora.setBounds(246, 225, 112, 25);
        lblHora.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/reloj.png")));
        panelRegistrarCitas.add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(244, 250, 200, 25);
        txtHora.setBackground(new Color(245, 255, 250));
        txtHora.setColumns(10);
        panelRegistrarCitas.add(txtHora);

       
        cbPaciente.setEditable(false);
        cbMedico.setEditable(false);
        cbConsultorio.setEditable(false);

        // ===================== PANEL TABLA =====================
        JPanel panelTabla = new JPanel();
        panelTabla.setBorder(new CompoundBorder(
                new LineBorder(new Color(128, 128, 128)),
                new LineBorder(new Color(0, 128, 128))
        ));
        panelTabla.setBackground(new Color(220, 220, 220));
        panelTabla.setBounds(20, 383, 686, 221);
        contentPane.add(panelTabla);
        panelTabla.setLayout(null);

        JLabel lblCitasRegistradas = new JLabel("Citas Registradas (Nuevas)");
        lblCitasRegistradas.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/img/agregar (1).png")));
        lblCitasRegistradas.setForeground(new Color(0, 128, 128));
        lblCitasRegistradas.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCitasRegistradas.setBounds(12, 7, 250, 24);
        panelTabla.add(lblCitasRegistradas);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 33, 664, 180);
        panelTabla.add(scrollPane);

        tbldatosModificados = new JTable();
        tbldatosModificados.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"N° Cita", "Paciente", "Medico", "Consultorio", "Fecha", "Hora", "Motivo"}
        ));
        scrollPane.setViewportView(tbldatosModificados);

        // ===================== BOTONES =====================
        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(20, 615, 125, 25);
        btnNuevo.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/agregar.png")));
        btnNuevo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnNuevo.setBackground(new Color(0, 128, 128));
        contentPane.add(btnNuevo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(155, 615, 125, 25);
        btnGuardar.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/flecha-de-circulo-de-disquete-a-la-derecha.png")));
        btnGuardar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnGuardar.setBackground(new Color(0, 128, 128));
        contentPane.add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(582, 615, 125, 25);
        btnLimpiar.setIcon(new ImageIcon(FRMRegistroCitas.class.getResource("/IMG/escoba.png")));
        btnLimpiar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnLimpiar.setBackground(new Color(128, 128, 0));
        contentPane.add(btnLimpiar);

        // ===================== EVENTOS=====================

        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	limpiarFormulario();
                habilitarFormulario(true);
                cbPaciente.requestFocus();

            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cbPaciente.setSelectedItem(null);
                cbMedico.setSelectedItem(null);
                cbConsultorio.setSelectedItem(null);

                txtfecha.setText("");
                txtHora.setText("");
                txtMotivo.setText("");
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!validar()) return;

                String pacienteTxt = cbPaciente.getEditor().getItem().toString().trim();
                String medicoTxt = cbMedico.getEditor().getItem().toString().trim();
                String consultorioTxt = cbConsultorio.getEditor().getItem().toString().trim();

                Integer codPaciente = pacienteDAO.obtenerCodPorNombreCompleto(pacienteTxt);
                Integer codMedico = medicoDao.obtenerCodPorNombreCompleto(medicoTxt);
                Integer codConsultorio = consultorioDao.obtenerCodPorNombre(consultorioTxt);

                if (codPaciente == null || codMedico == null || codConsultorio == null) {
                    msg("Selecciona un Paciente/Médico/Consultorio valido ");
                    return;
                }

                Cita c = new Cita(
                        0,
                        codPaciente,
                        codMedico,
                        codConsultorio,
                        txtfecha.getText().trim(), // año-Mes-dia
                        txtHora.getText().trim(),  // Hora:minuto:segundos
                        1,
                        txtMotivo.getText().trim()
                );

                Integer numGenerado = citaDao.insertarYRetornarNumCita(c);

                if (numGenerado != null) {
                    msg("Cita registrada CORRECTAMENTE");

                    String fecha = txtfecha.getText().trim();
                    String hora = txtHora.getText().trim();
                    String motivo = txtMotivo.getText().trim();

                    agregarFilaTabla(numGenerado, pacienteTxt, medicoTxt, consultorioTxt, fecha, hora, motivo);

                    
                    
                    habilitarFormulario(false);
                } else {
                    msg("No se pudo guardar");
                }
            }

            
        });
        
        
        cargarCombos();
        habilitarFormulario(false);
        limpiarFormulario();

        
    }
    
    
    //LIMPIA LOS CAMPOS DE LA PARTE DE ARRIBA
    private void habilitarFormulario(boolean estado) {
        cbPaciente.setEnabled(estado);
        cbMedico.setEnabled(estado);
        cbConsultorio.setEnabled(estado);

        txtfecha.setEnabled(estado);
        txtHora.setEnabled(estado);
        txtMotivo.setEnabled(estado);

        btnGuardar.setEnabled(estado);

        // numCita lo genera la BD
        txtNcita.setEnabled(true);
        txtNcita.setEditable(false);
    }
    
    //LIMPIA LOS CAMPOS
    private void limpiarFormulario() {
        cbPaciente.setSelectedItem(null);
        cbMedico.setSelectedItem(null);
        cbConsultorio.setSelectedItem(null);

        txtfecha.setText("");
        txtHora.setText("");
        txtMotivo.setText("");
        txtNcita.setText("");
    }

    //CARGO LOS DATOS PACIENTE MEDICO CONSULTORIO EN LOS COMBOBOX
    private void cargarCombos() {
        cbPaciente.removeAllItems();
        for (var p : pacienteDAO.listarActivos()) {
            cbPaciente.addItem(p.getNombres() + " " + p.getApellidos());
        }

        cbMedico.removeAllItems();
        for (var m : medicoDao.listarActivos()) {
            cbMedico.addItem(m.getNombres() + " " + m.getApellidos());
        }

        cbConsultorio.removeAllItems();
        for (var c : consultorioDao.listarActivos()) {
            cbConsultorio.addItem(c.getNombre());
        }
    }
    
    //ALERTA DE MSG PARA LOS CAMPOS NO RELLENADOS CORRECTAMENTE
    private void msg(String texto) {
        javax.swing.JOptionPane.showMessageDialog(this, texto);
    }


    //VALIDA SI LOS CAMPOS ESTAN RELLENADOS CORRECTAMENTE SI NO ESTAN RELLENADOS DEVUELVE LA ALERTA CREADA
    private boolean validar() {

        if (cbPaciente.getSelectedItem() == null) { msg("Seleccione un paciente"); return false; }
        if (cbMedico.getSelectedItem() == null) { msg("Seleccione un medico"); return false; }
        if (cbConsultorio.getSelectedItem() == null) { msg("Seleccione un consultorio"); return false; }

        String f = txtfecha.getText().trim();
        String h = txtHora.getText().trim();

        if (txtMotivo.getText().trim().isEmpty()) { msg("ingrese un motivo"); return false; }

        // validar formato fecha año-mes-dia
        if (!f.matches("\\d{4}-\\d{2}-\\d{2}")) {
            msg("Fecha inválida. Use: yyyy-MM-dd (Ej: 2026-02-21)");
            return false;
        }

        // validar formato hora hora:minuto:segundo
        if (!h.matches("\\d{2}:\\d{2}:\\d{2}")) {
            msg("Hora inválida. Use: hora:minuto:segundo (Ej: 09:00:00)");
            return false;
        }

        // validar que Date.valueOf y Time.valueOf no fallen
        try {
            java.sql.Date.valueOf(f);
        } catch (Exception e) {
            msg("Fecha no valida (revise el dia/mes). Ej: 2026-02-21");
            return false;
        }

        try {
            java.sql.Time.valueOf(h);
        } catch (Exception e) {
            msg("Hora ingresada no valida. Ej: 09:00:00");
            return false;
        }

        return true;
    }
    
    //AGREGA LA CITA REGISTRADA A LA JTABLE PARA VISUALIZAR LOS DATOS DE LA CITA
    private void agregarFilaTabla(int numCita, String paciente, String medico, String consultorio, String fecha, String hora, String motivo) {
        DefaultTableModel model = (DefaultTableModel) tbldatosModificados.getModel();
        model.addRow(new Object[] { numCita, paciente, medico, consultorio, fecha, hora, motivo });
    }
}