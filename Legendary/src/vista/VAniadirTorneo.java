package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Torneo;
import clases.TorneoNoOficial;
import clases.TorneoOficial;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class VAniadirTorneo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textAforo;
	private JTextField textJuego;
	private JTextField textFecha;
	private JTextField textDir;
	private JRadioButton rdbtnOficial;
	private JRadioButton rdbtnNoOficial;
	private ButtonGroup grupo1;
	ControladorDatos datos = new BDAImplementacion();
	private JTextField textPremio;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VAniadirTorneo(VTorneo ventanaPadre, boolean modal) {
		super(ventanaPadre);
		this.setModal(modal);
		System.out.println("cosas");
		setBounds(100, 100, 679, 573);
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

		JLabel lblJuego = new JLabel("Juego");
		lblJuego.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblJuego.setBounds(378, 177, 94, 26);
		contentPanel.add(lblJuego);

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

		rdbtnOficial = new JRadioButton("OFICIAL");
		rdbtnOficial.setBounds(378, 389, 77, 23);
		contentPanel.add(rdbtnOficial);

		rdbtnNoOficial = new JRadioButton("NO-OFICIAL");
		rdbtnNoOficial.setBounds(463, 389, 99, 23);
		contentPanel.add(rdbtnNoOficial);

		grupo1 = new ButtonGroup();
		grupo1.add(rdbtnNoOficial);
		grupo1.add(rdbtnOficial);

		JButton btnCrearTor = new JButton("Crear");
		btnCrearTor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearTorneo();
			}
		});
		btnCrearTor.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCrearTor.setBounds(383, 457, 192, 38);
		contentPanel.add(btnCrearTor);

		JLabel lblPremioRegla = new JLabel("Premio/Regla");
		lblPremioRegla.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblPremioRegla.setBounds(57, 393, 180, 26);
		contentPanel.add(lblPremioRegla);

		textPremio = new JTextField();
		textPremio.setColumns(10);
		textPremio.setBounds(57, 430, 197, 38);
		contentPanel.add(textPremio);

	}

	protected void crearTorneo() {

		Torneo tor = AniadirPantallaTorneo();
		// String opcion = grupo1.getSelection().getActionCommand();
		// String texto =
		// String opcionOficial = rdbtnOficial.getText().toString();
		// String opcionNoOfficial = rdbtnNoOficial.getText().toString();

		datos.aniadirTorneo(tor);
		JOptionPane.showMessageDialog(this, "Torneo dado de alta");

		textId.setText("");
		textNombre.setText("");
		textAforo.setText("");
		textJuego.setText("");
		textFecha.setText("");
		textDir.setText("");
		textPremio.setText("");

	}

	private Torneo AniadirPantallaTorneo() {
		
		Torneo tor = new Torneo();
		
		LocalDate fecha;
		
		if(rdbtnOficial.isSelected()) {
			tor = new TorneoOficial();
			tor.setTipo(rdbtnOficial.getText());
			((TorneoOficial) tor).setPremio(textPremio.getText());
		}else {
			tor = new TorneoNoOficial();
			tor.setTipo(rdbtnNoOficial.getText());
			((TorneoNoOficial) tor).setRegla(textPremio.getText());
			
		}
		
		tor.setIdTorneo(Integer.parseInt(textId.getText()));
		tor.setNombre(textNombre.getText());
		tor.setAforo(Integer.parseInt(textAforo.getText()));
		tor.setJuego(textJuego.getText());
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		fecha = LocalDate.parse(textFecha.getText(), formateador);
		tor.setFecha(fecha);
		tor.setDir(textDir.getText());
		
	
		
		return tor;
	}
}
