package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.CitaDao;
import controlador.ConsultorioDao;
import controlador.MedicoDao;
import controlador.PacienteDAO;

public class FRMConsulta extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTable tblCitas;
    private JTextField txtFecha;

    // COMPONENTES COMO ATRIBUTOS (para poder usarlos en métodos y eventos)
    private JRadioButton rdbtnPorPaciente;
    private JRadioButton rdbtnPorMedico;
    private JRadioButton rdbtnPorConsultorio;
    private JRadioButton rdbtnPorFecha;

    private JComboBox<String> cmbxPaciente;
    private JComboBox<String> cmbxMedico;
    private JComboBox<String> cmbxConsultorio;

    private JButton btnConsultar;
    private JButton btnLimpiarConsultar;
    private JButton btnLimpiarListaCitas;
    private JButton btnSalir;

    // DAOS
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDao medicoDao = new MedicoDao();
    private final ConsultorioDao consultorioDao = new ConsultorioDao();
    private final CitaDao citaDao = new CitaDao();

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
        setIconImage(Toolkit.getDefaultToolkit().getImage(FRMConsulta.class.getResource("/img/icon.png")));
        setTitle("Consulta de Citas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 746);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Consultar Citas");
        lblTitulo.setForeground(new Color(0, 128, 128));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/logo.png")));
        lblTitulo.setBounds(0, 5, 321, 57);
        contentPane.add(lblTitulo);

        btnSalir = new JButton("Salir / Volver");
        btnSalir.setForeground(new Color(128, 0, 0));
        btnSalir.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/salida.png")));
        btnSalir.setBackground(new Color(220, 220, 220));
        btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        btnSalir.setBounds(447, 11, 176, 34);
        contentPane.add(btnSalir);

        // =================== PANEL CONSULTAR ===================
        JPanel panelConsultar = new JPanel();
        panelConsultar.setBorder(new CompoundBorder(
                new LineBorder(new Color(105, 105, 105), 3, true),
                new LineBorder(new Color(0, 255, 255), 3, true)
        ));
        panelConsultar.setBackground(new Color(220, 220, 220));
        panelConsultar.setBounds(10, 73, 614, 301);
        contentPane.add(panelConsultar);
        panelConsultar.setLayout(null);

        JLabel lblConsulta = new JLabel("Consultar");
        lblConsulta.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/consulta.png")));
        lblConsulta.setForeground(new Color(0, 128, 128));
        lblConsulta.setBounds(10, 8, 162, 30);
        panelConsultar.add(lblConsulta);
        lblConsulta.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel panelConsulta = new JPanel();
        panelConsulta.setBounds(10, 38, 523, 50);
        panelConsultar.add(panelConsulta);
        panelConsulta.setBorder(new LineBorder(new Color(47, 79, 79), 3, true));
        panelConsulta.setLayout(null);

        rdbtnPorPaciente = new JRadioButton("Por Paciente");
        rdbtnPorPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorPaciente.setBounds(29, 12, 109, 25);
        panelConsulta.add(rdbtnPorPaciente);

        rdbtnPorMedico = new JRadioButton("Por Medico");
        rdbtnPorMedico.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorMedico.setBounds(154, 12, 109, 25);
        panelConsulta.add(rdbtnPorMedico);

        rdbtnPorConsultorio = new JRadioButton("Por Consultorio");
        rdbtnPorConsultorio.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorConsultorio.setBounds(265, 12, 128, 25);
        panelConsulta.add(rdbtnPorConsultorio);

        rdbtnPorFecha = new JRadioButton("Por Fecha");
        rdbtnPorFecha.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorFecha.setBounds(395, 12, 109, 25);
        panelConsulta.add(rdbtnPorFecha);

        JLabel lblFiltros = new JLabel("Filtros");
        lblFiltros.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/filtrar.png")));
        lblFiltros.setForeground(new Color(0, 128, 128));
        lblFiltros.setBounds(10, 117, 135, 24);
        panelConsultar.add(lblFiltros);
        lblFiltros.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel panelFiltros = new JPanel();
        panelFiltros.setBounds(10, 152, 594, 83);
        panelConsultar.add(panelFiltros);
        panelFiltros.setLayout(null);
        panelFiltros.setBorder(new LineBorder(new Color(47, 79, 79), 3, true));

        JLabel lblPaciente = new JLabel("Paciente");
        lblPaciente.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/NombreApellidos.png")));
        lblPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPaciente.setBounds(10, 8, 88, 17);
        panelFiltros.add(lblPaciente);

        JLabel lblConsultorio = new JLabel("Consultorio");
        lblConsultorio.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/consultorio-medico.png")));
        lblConsultorio.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblConsultorio.setBounds(10, 48, 100, 17);
        panelFiltros.add(lblConsultorio);

        JLabel lblMedico = new JLabel("Medico");
        lblMedico.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/EspecialidadMedico.png")));
        lblMedico.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMedico.setBounds(327, 8, 80, 17);
        panelFiltros.add(lblMedico);

        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/calendario.png")));
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFecha.setBounds(327, 51, 70, 21);
        panelFiltros.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(404, 49, 185, 25);
        panelFiltros.add(txtFecha);
        txtFecha.setColumns(10);

        cmbxPaciente = new JComboBox<>();
        cmbxPaciente.setBounds(108, 8, 185, 25);
        panelFiltros.add(cmbxPaciente);

        cmbxConsultorio = new JComboBox<>();
        cmbxConsultorio.setBounds(108, 48, 185, 25);
        panelFiltros.add(cmbxConsultorio);

        cmbxMedico = new JComboBox<>();
        cmbxMedico.setBounds(404, 8, 185, 25);
        panelFiltros.add(cmbxMedico);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setForeground(new Color(0, 255, 0));
        btnConsultar.setBounds(20, 246, 125, 30);
        panelConsultar.add(btnConsultar);
        btnConsultar.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/busqueda.png")));
        btnConsultar.setBackground(new Color(0, 128, 128));
        btnConsultar.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnLimpiarConsultar = new JButton("Limpiar");
        btnLimpiarConsultar.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/escoba.png")));
        btnLimpiarConsultar.setForeground(new Color(255, 215, 0));
        btnLimpiarConsultar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnLimpiarConsultar.setBackground(new Color(128, 128, 0));
        btnLimpiarConsultar.setBounds(169, 246, 125, 30);
        panelConsultar.add(btnLimpiarConsultar);

        // =================== PANEL LISTA ===================
        JPanel panelListaCitas = new JPanel();
        panelListaCitas.setBorder(new CompoundBorder(
                new LineBorder(new Color(0, 0, 128), 3, true),
                new LineBorder(new Color(102, 205, 170), 3, true)
        ));
        panelListaCitas.setBounds(9, 385, 614, 311);
        contentPane.add(panelListaCitas);
        panelListaCitas.setLayout(null);

        JLabel lblListadodeCitas = new JLabel("Lista de Citas");
        lblListadodeCitas.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/agregar (1).png")));
        lblListadodeCitas.setForeground(new Color(0, 128, 128));
        lblListadodeCitas.setBounds(10, 11, 143, 27);
        panelListaCitas.add(lblListadodeCitas);
        lblListadodeCitas.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollCitas = new JScrollPane();
        scrollCitas.setBounds(10, 38, 594, 214);
        panelListaCitas.add(scrollCitas);
        scrollCitas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tblCitas = new JTable();
        tblCitas.setSurrendersFocusOnKeystroke(true);
        tblCitas.setFillsViewportHeight(true);
        tblCitas.setColumnSelectionAllowed(true);
        tblCitas.setCellSelectionEnabled(true);
        tblCitas.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCitas.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"N° Cita", "Paciente", "Medico", "Consultorio", "Fecha", "Hora", "Motivo", "Estado"}
        ));
        scrollCitas.setViewportView(tblCitas);
        tblCitas.setBorder(new LineBorder(new Color(85, 107, 47)));

        btnLimpiarListaCitas = new JButton("Limpiar");
        btnLimpiarListaCitas.setForeground(new Color(255, 215, 0));
        btnLimpiarListaCitas.setBounds(20, 263, 125, 30);
        panelListaCitas.add(btnLimpiarListaCitas);
        btnLimpiarListaCitas.setIcon(new ImageIcon(FRMConsulta.class.getResource("/img/escoba.png")));
        btnLimpiarListaCitas.setBackground(new Color(128, 128, 0));
        btnLimpiarListaCitas.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));

        // =================== ARRANQUE ===================
        cargarCombos();
        deshabilitarTodosLosFiltros();

        // =================== EVENTOS ===================
        rdbtnPorPaciente.addActionListener(e -> actualizarFiltros());
        rdbtnPorMedico.addActionListener(e -> actualizarFiltros());
        rdbtnPorConsultorio.addActionListener(e -> actualizarFiltros());
        rdbtnPorFecha.addActionListener(e -> actualizarFiltros());

        btnConsultar.addActionListener(e -> consultar());

        btnLimpiarConsultar.addActionListener(e -> limpiarConsultar());

        btnLimpiarListaCitas.addActionListener(e -> limpiarTabla());

        btnSalir.addActionListener(e -> {
        	FRMPrincipal principal = new FRMPrincipal();
        	principal.setVisible(true);
               dispose(); 
        });
    }

    // ===================== METODOS =====================

    private void deshabilitarTodosLosFiltros() {
        cmbxPaciente.setEnabled(false);
        cmbxMedico.setEnabled(false);
        cmbxConsultorio.setEnabled(false);
        txtFecha.setEnabled(false);
    }

    // Habilita según lo que esté marcado (pueden ser varios)
    private void actualizarFiltros() {
        cmbxPaciente.setEnabled(rdbtnPorPaciente.isSelected());
        cmbxMedico.setEnabled(rdbtnPorMedico.isSelected());
        cmbxConsultorio.setEnabled(rdbtnPorConsultorio.isSelected());
        txtFecha.setEnabled(rdbtnPorFecha.isSelected());

        // Si desmarcan, limpiamos ese filtro para no usarlo sin querer
        if (!rdbtnPorPaciente.isSelected()) cmbxPaciente.setSelectedItem(null);
        if (!rdbtnPorMedico.isSelected()) cmbxMedico.setSelectedItem(null);
        if (!rdbtnPorConsultorio.isSelected()) cmbxConsultorio.setSelectedItem(null);
        if (!rdbtnPorFecha.isSelected()) txtFecha.setText("");
    }

    private void cargarCombos() {
        cmbxPaciente.removeAllItems();
        for (var p : pacienteDAO.listarActivos()) {
            cmbxPaciente.addItem(p.getNombres() + " " + p.getApellidos());
        }

        cmbxMedico.removeAllItems();
        for (var m : medicoDao.listarActivos()) {
            cmbxMedico.addItem(m.getNombres() + " " + m.getApellidos());
        }

        cmbxConsultorio.removeAllItems();
        for (var c : consultorioDao.listarActivos()) {
            cmbxConsultorio.addItem(c.getNombre());
        }

        cmbxPaciente.setSelectedItem(null);
        cmbxMedico.setSelectedItem(null);
        cmbxConsultorio.setSelectedItem(null);
        txtFecha.setText("");
    }

    private void limpiarConsultar() {
        rdbtnPorPaciente.setSelected(false);
        rdbtnPorMedico.setSelected(false);
        rdbtnPorConsultorio.setSelected(false);
        rdbtnPorFecha.setSelected(false);

        cmbxPaciente.setSelectedItem(null);
        cmbxMedico.setSelectedItem(null);
        cmbxConsultorio.setSelectedItem(null);
        txtFecha.setText("");

        deshabilitarTodosLosFiltros();
    }

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) tblCitas.getModel();
        model.setRowCount(0);
    }

    private void consultar() {
        // Debe haber por lo menos 1 radio marcado
        if (!rdbtnPorPaciente.isSelected() && !rdbtnPorMedico.isSelected()
                && !rdbtnPorConsultorio.isSelected() && !rdbtnPorFecha.isSelected()) {
            msg("Seleccione al menos un filtro (Paciente, Medico, Consultorio o Fecha).");
            return;
        }

        // Validaciones según radios marcados
        Integer codPaciente = null;
        Integer codMedico = null;
        Integer codConsultorio = null;
        String fecha = null;

        if (rdbtnPorPaciente.isSelected()) {
            if (cmbxPaciente.getSelectedItem() == null) { msg("Seleccione un paciente"); return; }
            String pacienteTxt = cmbxPaciente.getSelectedItem().toString().trim();
            codPaciente = pacienteDAO.obtenerCodPorNombreCompleto(pacienteTxt);
            if (codPaciente == null) { msg("Paciente inválido"); return; }
        }

        if (rdbtnPorMedico.isSelected()) {
            if (cmbxMedico.getSelectedItem() == null) { msg("Seleccione un médico"); return; }
            String medicoTxt = cmbxMedico.getSelectedItem().toString().trim();
            codMedico = medicoDao.obtenerCodPorNombreCompleto(medicoTxt);
            if (codMedico == null) { msg("Médico inválido"); return; }
        }

        if (rdbtnPorConsultorio.isSelected()) {
            if (cmbxConsultorio.getSelectedItem() == null) { msg("Seleccione un consultorio"); return; }
            String consultorioTxt = cmbxConsultorio.getSelectedItem().toString().trim();
            codConsultorio = consultorioDao.obtenerCodPorNombre(consultorioTxt);
            if (codConsultorio == null) { msg("Consultorio inválido"); return; }
        }

        if (rdbtnPorFecha.isSelected()) {
            fecha = txtFecha.getText().trim();
            if (fecha.isEmpty()) { msg("Ingrese la fecha (yyyy-MM-dd)"); return; }
            if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
                msg("Fecha inválida. Use: yyyy-MM-dd (Ej: 2026-02-21)");
                return;
            }
            try {
                java.sql.Date.valueOf(fecha);
            } catch (Exception ex) {
                msg("Fecha inválida (revise el día/mes). Ej: 2026-02-21");
                return;
            }
        }

        // Consultar en BD (este método lo agregaste en CitaDao)
        var filas = citaDao.consultarParaTabla(codPaciente, codMedico, codConsultorio, fecha);

        // Pintar en JTable
        DefaultTableModel model = (DefaultTableModel) tblCitas.getModel();
        model.setRowCount(0);

        for (Object[] fila : filas) {
            model.addRow(fila);
        }

        if (filas.isEmpty()) {
            msg("No se encontraron citas con esos filtros.");
        }
    }

    private void msg(String s) {
        javax.swing.JOptionPane.showMessageDialog(this, s);
    }
}