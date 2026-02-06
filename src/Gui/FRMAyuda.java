package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class FRMAyuda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel bg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMAyuda frame = new FRMAyuda();
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
	public FRMAyuda() {
		setTitle("Ayuda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRMAyuda.class.getResource("/IMG/icon.png")));
		setBackground(new Color(128, 128, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 422);
		bg = new JPanel();
		bg.setBackground(new Color(0, 139, 139));
		bg.setBorder(null);
		setContentPane(bg);
		bg.setLayout(null);
		
		JLabel lblTitulo = new JLabel("GRUPO E (TRALALERITOS)");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBackground(SystemColor.inactiveCaptionText);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		lblTitulo.setIcon(new ImageIcon(FRMAyuda.class.getResource("/IMG/grupo-de-personas.png")));
		lblTitulo.setBounds(218, 0, 216, 47);
		bg.add(lblTitulo);
		
		JPanel bgsecundario = new JPanel();
		bgsecundario.setBackground(new Color(47, 79, 79));
		bgsecundario.setBounds(10, 50, 660, 322);
		bg.add(bgsecundario);
		bgsecundario.setLayout(null);
		
		JList listEstudiantes = new JList();
		listEstudiantes.setForeground(new Color(102, 205, 170));
		listEstudiantes.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		listEstudiantes.setModel(new AbstractListModel() {
			String[] values = new String[] {"", "", "", "", "", "", "", "", "                 Nombres y Apellidos                 ||          Apodos", " ==================================================================", "                • Jesus Angel Rivera Villanueva     (Dix tu carry)", "                • Daniel Alfredo Ostos Uchpa        (RompeTelitas)", "                • Nery Jessica Migo Salazar            ", "                • Nicole Angelina Guevara Buiza   (Niki)", "                • Joseph Bautista Marquez             (Buyu)"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listEstudiantes.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		listEstudiantes.setVisibleRowCount(10);
		listEstudiantes.setBackground(new Color(47, 79, 79));
		listEstudiantes.setToolTipText("");
		listEstudiantes.setBounds(31, 72, 580, 213);
		bgsecundario.add(listEstudiantes);
		
		JLabel lblSubtitulo = new JLabel("Integrantes :");
		lblSubtitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		lblSubtitulo.setBounds(10, 25, 106, 23);
		bgsecundario.add(lblSubtitulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 48, 124, 2);
		bgsecundario.add(separator);

	}
}
