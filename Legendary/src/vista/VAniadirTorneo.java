package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ControladorDatos;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VAniadirTorneo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textAforo;
	private JTextField textJuego;
	private JTextField textFecha;
	private JTextField textDir;
	private JTextField textOpc;
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @param datos 
	 * @param modal 
	 * @param ventanaPadre 
	 */
	public VAniadirTorneo(VTorneo ventanaPadre, boolean modal, ControladorDatos datos) {
		super(ventanaPadre);
		this.setModal(modal);
		setBounds(100, 100, 699, 589);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblId.setBounds(57, 72, 94, 26);
		contentPanel.add(lblId);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNombre.setBounds(378, 72, 94, 26);
		contentPanel.add(lblNombre);
		
		JLabel lblAforo = new JLabel("AFORO");
		lblAforo.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblAforo.setBounds(57, 177, 94, 26);
		contentPanel.add(lblAforo);
		
		JLabel lblPremio = new JLabel("Juego");
		lblPremio.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblPremio.setBounds(378, 177, 94, 26);
		contentPanel.add(lblPremio);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblFecha.setBounds(57, 287, 94, 26);
		contentPanel.add(lblFecha);
		
		JLabel lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblDireccion.setBounds(378, 294, 123, 26);
		contentPanel.add(lblDireccion);
		
		textId = new JTextField();
		textId.setBounds(57, 110, 197, 38);
		contentPanel.add(textId);
		textId.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(378, 109, 197, 38);
		contentPanel.add(textNombre);
		
		textAforo = new JTextField();
		textAforo.setColumns(10);
		textAforo.setBounds(57, 214, 197, 38);
		contentPanel.add(textAforo);
		
		textJuego = new JTextField();
		textJuego.setColumns(10);
		textJuego.setBounds(378, 214, 197, 38);
		contentPanel.add(textJuego);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(57, 324, 197, 38);
		contentPanel.add(textFecha);
		
		textDir = new JTextField();
		textDir.setColumns(10);
		textDir.setBounds(378, 331, 197, 38);
		contentPanel.add(textDir);
		
		JRadioButton rdbtnOficial = new JRadioButton("OFICIAL");
		rdbtnOficial.setBounds(378, 405, 77, 23);
		contentPanel.add(rdbtnOficial);
		
		JRadioButton rdbtnNoOficial = new JRadioButton("NO-OFICIAL");
		rdbtnNoOficial.setBounds(490, 405, 99, 23);
		contentPanel.add(rdbtnNoOficial);
		
		JButton btnCrearTor = new JButton("Crear");
		btnCrearTor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCrearTor.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCrearTor.setBounds(383, 457, 192, 38);
		contentPanel.add(btnCrearTor);
		
		textOpc = new JTextField();
		textOpc.setColumns(10);
		textOpc.setBounds(57, 458, 197, 38);
		contentPanel.add(textOpc);
		
		JLabel lblPremioregla = new JLabel("Premio/Regla");
		lblPremioregla.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblPremioregla.setBounds(57, 420, 197, 26);
		contentPanel.add(lblPremioregla);
	}
}
