package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ButtonGroup;
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

public class FRMReporte extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTable tblReporte;
    private JTextField txtFecha;

    // radios (1 solo)
    private JRadioButton rdbtnPorPaciente;
    private JRadioButton rdbtnPorMedico;
    private JRadioButton rdbtnPorConsultorio;
    private JRadioButton rdbtnPorFecha;
    private JRadioButton rdbtnPendientes;
    private JRadioButton rdbtnAgendaDia;

    // filtros como atributos
    private JComboBox<String> cmbPaciente;
    private JComboBox<String> cmbMedico;
    private JComboBox<String> cmbConsultorio;

    // botones
    private JButton btnGenerar;
    private JButton btnLimpiar;
    private JButton btnLimpiarResultado;
    private JButton btnSalir;

    // DAOs
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDao medicoDao = new MedicoDao();
    private final ConsultorioDao consultorioDao = new ConsultorioDao();
    private final CitaDao citaDao = new CitaDao();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FRMReporte frame = new FRMReporte();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FRMReporte() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(FRMReporte.class.getResource("/IMG/icon.png")));
        setTitle("Reporte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 799);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Reporte General");
        lblTitulo.setForeground(new Color(0, 128, 128));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/logo.png")));
        lblTitulo.setBounds(0, 5, 263, 57);
        contentPane.add(lblTitulo);

        btnSalir = new JButton("Salir / Volver");
        btnSalir.setForeground(new Color(128, 0, 0));
        btnSalir.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/salida.png")));
        btnSalir.setBackground(new Color(220, 220, 220));
        btnSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        btnSalir.setBounds(508, 11, 162, 37);
        contentPane.add(btnSalir);

        // grupo de radios (solo 1)
        ButtonGroup bg = new ButtonGroup();

        JPanel panel = new JPanel();
        panel.setBorder(new CompoundBorder(
                new LineBorder(new Color(128, 128, 128), 3, true),
                new LineBorder(new Color(0, 255, 255), 3, true)
        ));
        panel.setBackground(new Color(220, 220, 220));
        panel.setBounds(10, 63, 664, 348);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblReporte = new JLabel("Reporte ");
        lblReporte.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/Reporte.png")));
        lblReporte.setForeground(new Color(0, 128, 128));
        lblReporte.setBounds(10, 18, 161, 39);
        panel.add(lblReporte);
        lblReporte.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Panel tipo de reporte
        JPanel panelReporte = new JPanel();
        panelReporte.setBounds(10, 56, 644, 77);
        panel.add(panelReporte);
        panelReporte.setBorder(new LineBorder(new Color(0, 128, 0), 3, true));
        panelReporte.setLayout(null);

        rdbtnPorPaciente = new JRadioButton("Por Paciente");
        rdbtnPorPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorPaciente.setBounds(18, 7, 120, 23);
        panelReporte.add(rdbtnPorPaciente);

        rdbtnPorMedico = new JRadioButton("Por Medico");
        rdbtnPorMedico.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorMedico.setBounds(176, 7, 120, 23);
        panelReporte.add(rdbtnPorMedico);

        rdbtnPorConsultorio = new JRadioButton("Por Consultorio");
        rdbtnPorConsultorio.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorConsultorio.setBounds(336, 7, 140, 23);
        panelReporte.add(rdbtnPorConsultorio);

        rdbtnPorFecha = new JRadioButton("Por Fecha");
        rdbtnPorFecha.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPorFecha.setBounds(505, 7, 120, 23);
        panelReporte.add(rdbtnPorFecha);

        rdbtnPendientes = new JRadioButton("Pacientes con citas pendientes");
        rdbtnPendientes.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnPendientes.setBounds(18, 40, 230, 23);
        panelReporte.add(rdbtnPendientes);

        rdbtnAgendaDia = new JRadioButton("Agenda del día");
        rdbtnAgendaDia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnAgendaDia.setBounds(336, 40, 140, 23);
        panelReporte.add(rdbtnAgendaDia);

        // meter a grupo
        bg.add(rdbtnPorPaciente);
        bg.add(rdbtnPorMedico);
        bg.add(rdbtnPorConsultorio);
        bg.add(rdbtnPorFecha);
        bg.add(rdbtnPendientes);
        bg.add(rdbtnAgendaDia);

        JLabel lblFiltros = new JLabel("Filtros");
        lblFiltros.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/filtrar.png")));
        lblFiltros.setForeground(new Color(0, 128, 128));
        lblFiltros.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblFiltros.setBounds(10, 156, 111, 24);
        panel.add(lblFiltros);

        // Panel filtros
        JPanel panelFiltros = new JPanel();
        panelFiltros.setBounds(10, 191, 644, 80);
        panel.add(panelFiltros);
        panelFiltros.setBorder(new LineBorder(new Color(0, 128, 0), 3, true));
        panelFiltros.setLayout(null);

        JLabel lblPaciente = new JLabel("Paciente");
        lblPaciente.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/NombreApellidos.png")));
        lblPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPaciente.setBounds(10, 11, 90, 15);
        panelFiltros.add(lblPaciente);

        cmbPaciente = new JComboBox<>();
        cmbPaciente.setBounds(115, 7, 200, 25);
        panelFiltros.add(cmbPaciente);

        JLabel lblMedico = new JLabel("Medico");
        lblMedico.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/EspecialidadMedico.png")));
        lblMedico.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMedico.setBounds(337, 9, 80, 18);
        panelFiltros.add(lblMedico);

        cmbMedico = new JComboBox<>();
        cmbMedico.setBounds(417, 7, 200, 25);
        panelFiltros.add(cmbMedico);

        JLabel lblConsultorio = new JLabel("Consultorio");
        lblConsultorio.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/consultorio-medico.png")));
        lblConsultorio.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblConsultorio.setBounds(10, 47, 100, 14);
        panelFiltros.add(lblConsultorio);

        cmbConsultorio = new JComboBox<>();
        cmbConsultorio.setBounds(115, 44, 200, 25);
        panelFiltros.add(cmbConsultorio);

        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/calendario.png")));
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFecha.setBounds(337, 47, 70, 14);
        panelFiltros.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(417, 43, 200, 25);
        panelFiltros.add(txtFecha);

        btnGenerar = new JButton("Generar");
        btnGenerar.setForeground(new Color(0, 255, 0));
        btnGenerar.setBounds(10, 297, 125, 30);
        panel.add(btnGenerar);
        btnGenerar.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/busqueda.png")));
        btnGenerar.setBackground(new Color(0, 128, 128));
        btnGenerar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setForeground(new Color(240, 230, 140));
        btnLimpiar.setBounds(160, 297, 125, 30);
        panel.add(btnLimpiar);
        btnLimpiar.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/escoba.png")));
        btnLimpiar.setBackground(new Color(128, 128, 0));
        btnLimpiar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));

        // =================== PANEL LISTA REPORTE ===================
        JPanel panelListaReporte = new JPanel();
        panelListaReporte.setBorder(new CompoundBorder(
                new LineBorder(new Color(128, 128, 128), 3, true),
                new LineBorder(new Color(0, 255, 255), 3, true)
        ));
        panelListaReporte.setBackground(new Color(220, 220, 220));
        panelListaReporte.setBounds(10, 423, 660, 326);
        contentPane.add(panelListaReporte);
        panelListaReporte.setLayout(null);

        JLabel lblListado = new JLabel("Resultado Reporte");
        lblListado.setForeground(new Color(0, 128, 128));
        lblListado.setIcon(new ImageIcon(FRMReporte.class.getResource("/img/agregar (1).png")));
        lblListado.setBounds(10, 11, 200, 24);
        panelListaReporte.add(lblListado);
        lblListado.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollReporte = new JScrollPane();
        scrollReporte.setBounds(10, 38, 640, 237);
        panelListaReporte.add(scrollReporte);
        scrollReporte.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tblReporte = new JTable();
        tblReporte.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Paciente", "Medico", "Consultorio", "Fecha", "Hora", "Estado", "Motivo"}
        ));
        tblReporte.getTableHeader().setReorderingAllowed(false);
        tblReporte.setBorder(new LineBorder(new Color(0, 128, 0)));
        scrollReporte.setViewportView(tblReporte);

        btnLimpiarResultado = new JButton("Limpiar");
        btnLimpiarResultado.setIcon(new ImageIcon(FRMReporte.class.getResource("/IMG/escoba.png")));
        btnLimpiarResultado.setForeground(new Color(240, 230, 140));
        btnLimpiarResultado.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnLimpiarResultado.setBackground(new Color(128, 128, 0));
        btnLimpiarResultado.setBounds(10, 286, 125, 30);
        panelListaReporte.add(btnLimpiarResultado);

        // =================== ARRANQUE ===================
        cargarCombos();
        deshabilitarFiltros();

        // =================== EVENTOS ===================
        rdbtnPorPaciente.addActionListener(e -> actualizarFiltros());
        rdbtnPorMedico.addActionListener(e -> actualizarFiltros());
        rdbtnPorConsultorio.addActionListener(e -> actualizarFiltros());
        rdbtnPorFecha.addActionListener(e -> actualizarFiltros());
        rdbtnPendientes.addActionListener(e -> actualizarFiltros());
        rdbtnAgendaDia.addActionListener(e -> actualizarFiltros());

        btnGenerar.addActionListener(e -> generarReporte());
        btnLimpiar.addActionListener(e -> limpiarFiltrosYRadio());
        btnLimpiarResultado.addActionListener(e -> limpiarTabla());

        btnSalir.addActionListener(e -> {
            FRMPrincipal p = new FRMPrincipal();
            p.setVisible(true);
            dispose();
        });
    }

    // =================== METODOS ===================

    private void deshabilitarFiltros() {
        cmbPaciente.setEnabled(false);
        cmbMedico.setEnabled(false);
        cmbConsultorio.setEnabled(false);
        txtFecha.setEnabled(false);
    }

    // según el radio elegido, habilita filtros
    private void actualizarFiltros() {
        deshabilitarFiltros();

        if (rdbtnPorPaciente.isSelected()) {
            cmbPaciente.setEnabled(true);

        } else if (rdbtnPorMedico.isSelected()) {
            cmbMedico.setEnabled(true);
            txtFecha.setEnabled(true); // agenda del médico por fecha

        } else if (rdbtnPorConsultorio.isSelected()) {
            cmbConsultorio.setEnabled(true);
            txtFecha.setEnabled(true); // ocupación por día (simple)

        } else if (rdbtnPorFecha.isSelected()) {
            txtFecha.setEnabled(true);

        } else if (rdbtnPendientes.isSelected()) {
            // sin filtros

        } else if (rdbtnAgendaDia.isSelected()) {
            txtFecha.setEnabled(true);
        }

        cmbPaciente.setSelectedItem(null);
        cmbMedico.setSelectedItem(null);
        cmbConsultorio.setSelectedItem(null);
        txtFecha.setText("");
    }

    private void cargarCombos() {
        cmbPaciente.removeAllItems();
        for (var p : pacienteDAO.listarActivos()) {
            cmbPaciente.addItem(p.getNombres() + " " + p.getApellidos());
        }

        cmbMedico.removeAllItems();
        for (var m : medicoDao.listarActivos()) {
            cmbMedico.addItem(m.getNombres() + " " + m.getApellidos());
        }

        cmbConsultorio.removeAllItems();
        for (var c : consultorioDao.listarActivos()) {
            cmbConsultorio.addItem(c.getNombre());
        }

        cmbPaciente.setSelectedItem(null);
        cmbMedico.setSelectedItem(null);
        cmbConsultorio.setSelectedItem(null);
        txtFecha.setText("");
    }

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) tblReporte.getModel();
        model.setRowCount(0);
    }

    private void limpiarFiltrosYRadio() {
        rdbtnPorPaciente.setSelected(false);
        rdbtnPorMedico.setSelected(false);
        rdbtnPorConsultorio.setSelected(false);
        rdbtnPorFecha.setSelected(false);
        rdbtnPendientes.setSelected(false);
        rdbtnAgendaDia.setSelected(false);

        cmbPaciente.setSelectedItem(null);
        cmbMedico.setSelectedItem(null);
        cmbConsultorio.setSelectedItem(null);
        txtFecha.setText("");

        deshabilitarFiltros();
    }

    private void generarReporte() {
        if (!hayRadioSeleccionado()) {
            msg("Seleccione un tipo de reporte.");
            return;
        }

        Integer codPaciente = null;
        Integer codMedico = null;
        Integer codConsultorio = null;
        String fecha = null;

        // POR PACIENTE
        if (rdbtnPorPaciente.isSelected()) {
            if (cmbPaciente.getSelectedItem() == null) { msg("Seleccione un paciente"); return; }
            String pacTxt = cmbPaciente.getSelectedItem().toString().trim();
            codPaciente = pacienteDAO.obtenerCodPorNombreCompleto(pacTxt);
            if (codPaciente == null) { msg("Paciente inválido"); return; }
        }

        // POR MEDICO (agenda por fecha)
        if (rdbtnPorMedico.isSelected()) {
            if (cmbMedico.getSelectedItem() == null) { msg("Seleccione un médico"); return; }
            String medTxt = cmbMedico.getSelectedItem().toString().trim();
            codMedico = medicoDao.obtenerCodPorNombreCompleto(medTxt);
            if (codMedico == null) { msg("Médico inválido"); return; }

            fecha = leerFecha(true);
            if (fecha == null) return;
        }

        // POR CONSULTORIO (ocupación por día)
        if (rdbtnPorConsultorio.isSelected()) {
            if (cmbConsultorio.getSelectedItem() == null) { msg("Seleccione un consultorio"); return; }
            String conTxt = cmbConsultorio.getSelectedItem().toString().trim();
            codConsultorio = consultorioDao.obtenerCodPorNombre(conTxt);
            if (codConsultorio == null) { msg("Consultorio inválido"); return; }

            fecha = leerFecha(true);
            if (fecha == null) return;
        }

        // POR FECHA
        if (rdbtnPorFecha.isSelected()) {
            fecha = leerFecha(true);
            if (fecha == null) return;
        }

        // AGENDA DEL DÍA
        if (rdbtnAgendaDia.isSelected()) {
            fecha = leerFecha(true);
            if (fecha == null) return;
        }

        List<Object[]> filas;

        // PENDIENTES
        if (rdbtnPendientes.isSelected()) {
            filas = citaDao.reportePacientesConPendientes();

        } else if (rdbtnAgendaDia.isSelected()) {
            filas = citaDao.reporteAgendaDelDia(fecha);

        } else {
            // general (por paciente / medico+fecha / consultorio+fecha / fecha)
            filas = citaDao.reporteGeneral(codPaciente, codMedico, codConsultorio, fecha);
        }

        DefaultTableModel model = (DefaultTableModel) tblReporte.getModel();
        model.setRowCount(0);

        for (Object[] f : filas) {
            model.addRow(f);
        }

        if (filas.isEmpty()) msg("No hay datos para ese reporte.");
    }

    private boolean hayRadioSeleccionado() {
        return rdbtnPorPaciente.isSelected()
                || rdbtnPorMedico.isSelected()
                || rdbtnPorConsultorio.isSelected()
                || rdbtnPorFecha.isSelected()
                || rdbtnPendientes.isSelected()
                || rdbtnAgendaDia.isSelected();
    }

    // lee fecha yyyy-MM-dd
    private String leerFecha(boolean obligatorio) {
        String f = txtFecha.getText().trim();

        if (obligatorio && f.isEmpty()) {
            msg("Ingrese fecha (yyyy-MM-dd)");
            return null;
        }

        if (!f.isEmpty()) {
            if (!f.matches("\\d{4}-\\d{2}-\\d{2}")) {
                msg("Fecha inválida. Use yyyy-MM-dd. Ej: 2026-02-21");
                return null;
            }
            try {
                java.sql.Date.valueOf(f);
            } catch (Exception e) {
                msg("Fecha inválida (revise día/mes). Ej: 2026-02-21");
                return null;
            }
        }

        return f.isEmpty() ? null : f;
    }

    private void msg(String s) {
        javax.swing.JOptionPane.showMessageDialog(this, s);
    }
}