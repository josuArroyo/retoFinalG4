package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class VRegistro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDniReg;
	private JTextField textContraReg;
	private JTextField textNombreReg;
	private JTextField textTelReg;
	private JTextField textCorreoReg;
	private JTextField textFechNacReg;
	private JButton btnAlta;
	private JRadioButton rdbtnMujer;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnOtro;
	private ButtonGroup grupo1;
	private ControladorDatos datos = new BDAImplementacion();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			VRegistro dialog = new VRegistro();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * 
	 * @param ventanaPadre
	 * 
	 * @param datos
	 * @param b
	 * @wbp.parser.constructor
	 */
	public VRegistro(VLogin ventanaPadre, boolean modal, ControladorDatos datos) {
		super(ventanaPadre);
		this.setModal(modal);
		cargarVentana(true);

	}

	public VRegistro(VGestionDatos ventanaPadre, boolean modal, ControladorDatos datos2) {
		super(ventanaPadre);
		this.setModal(modal);
		cargarVentana(false);
	}

	private void cargarVentana(boolean opc) {

		setBounds(100, 100, 636, 636);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbltexto = new JLabel(
					"Rellene los datos que aparecen a continuacion para registrarse en nuestra aplicacion.");
			lbltexto.setBounds(27, 77, 557, 21);
			contentPanel.add(lbltexto);
		}
		{
			JLabel lblDniReg = new JLabel("DNI");
			lblDniReg.setFont(new Font("Algerian", Font.PLAIN, 17));
			lblDniReg.setBounds(47, 129, 100, 39);
			contentPanel.add(lblDniReg);
		}
		{
			textDniReg = new JTextField();
			textDniReg.setBounds(47, 161, 184, 39);
			contentPanel.add(textDniReg);
			textDniReg.setColumns(10);
		}
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setFont(new Font("Algerian", Font.PLAIN, 17));
			lblContrasea.setBounds(335, 129, 128, 39);
			contentPanel.add(lblContrasea);
		}
		{
			textContraReg = new JTextField();
			textContraReg.setColumns(10);
			textContraReg.setBounds(335, 161, 184, 39);
			contentPanel.add(textContraReg);
		}
		{
			JLabel lblNombre = new JLabel("NOMBRE");
			lblNombre.setFont(new Font("Algerian", Font.PLAIN, 17));
			lblNombre.setBounds(47, 233, 100, 39);
			contentPanel.add(lblNombre);
		}
		{
			textNombreReg = new JTextField();
			textNombreReg.setColumns(10);
			textNombreReg.setBounds(47, 270, 184, 39);
			contentPanel.add(textNombreReg);
		}
		{
			JLabel lblTelefono = new JLabel("TELEFONO");
			lblTelefono.setFont(new Font("Algerian", Font.PLAIN, 17));
			lblTelefono.setBounds(335, 233, 100, 39);
			contentPanel.add(lblTelefono);
		}
		{
			textTelReg = new JTextField();
			textTelReg.setColumns(10);
			textTelReg.setBounds(335, 270, 184, 39);
			contentPanel.add(textTelReg);
		}
		{
			JLabel lblCorreo = new JLabel("CORREO");
			lblCorreo.setFont(new Font("Algerian", Font.PLAIN, 17));
			lblCorreo.setBounds(47, 346, 100, 39);
			contentPanel.add(lblCorreo);
		}
		{
			textCorreoReg = new JTextField();
			textCorreoReg.setColumns(10);
			textCorreoReg.setBounds(47, 382, 184, 39);
			contentPanel.add(textCorreoReg);
		}
		{
			JLabel lblFechaDeNacimiente = new JLabel("FECHA DE NACIMIENTO");
			lblFechaDeNacimiente.setFont(new Font("Algerian", Font.PLAIN, 17));
			lblFechaDeNacimiente.setBounds(335, 346, 184, 39);
			contentPanel.add(lblFechaDeNacimiente);
		}
		{
			textFechNacReg = new JTextField();
			textFechNacReg.setColumns(10);
			textFechNacReg.setBounds(335, 382, 184, 39);
			contentPanel.add(textFechNacReg);
		}
		{
			JLabel lblSexo = new JLabel("SEXO");
			lblSexo.setFont(new Font("Algerian", Font.PLAIN, 17));
			lblSexo.setBounds(47, 449, 100, 39);
			contentPanel.add(lblSexo);
		}

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(47, 484, 80, 23);
		contentPanel.add(rdbtnMujer);
		rdbtnMujer.setActionCommand("Mujer");

		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(129, 484, 88, 23);
		contentPanel.add(rdbtnHombre);
		rdbtnHombre.setActionCommand("Hombre");

		rdbtnOtro = new JRadioButton("Otro");
		rdbtnOtro.setBounds(219, 484, 77, 23);
		contentPanel.add(rdbtnOtro);
		rdbtnOtro.setActionCommand("Otro");

		grupo1 = new ButtonGroup();
		grupo1.add(rdbtnMujer);
		grupo1.add(rdbtnHombre);
		grupo1.add(rdbtnOtro);

		btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alta(datos);

			}
		});

		btnAlta.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnAlta.setBounds(47, 528, 119, 46);
		contentPanel.add(btnAlta);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnModificar.setBounds(204, 528, 158, 46);
		contentPanel.add(btnModificar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCancelar.setBounds(401, 529, 144, 46);
		contentPanel.add(btnCancelar);

		if (opc) {
			btnModificar.setVisible(false);
		} else if(!opc) {
			btnAlta.setVisible(false);
		}
	}

	protected void cancelar() {
		this.dispose();
	}

	protected void alta(ControladorDatos datos) {

		Usuario us = new Usuario();
		LocalDate fecha;
		boolean found;
		boolean correcto = false;

		found = datos.buscarUsuarioDni(textDniReg.getText());
		try {

			if (!found) {

				if (ComprobarDni(textDniReg.getText())) {

					us.setDni(textDniReg.getText());
					us.setNombre(textNombreReg.getText());
					us.setContrasenia(textContraReg.getText());
					us.setCorreo(textCorreoReg.getText());

					DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					fecha = LocalDate.parse(textFechNacReg.getText(), formateador);
					us.setFechaNac(fecha);

				}
				if (comprobarTelefono(textTelReg.getText())) {
					us.setTelefono(Integer.parseInt(textTelReg.getText()));
					us.setSexo(grupo1.getSelection().getActionCommand());

					datos.altaUsuario(us);
					textDniReg.setText("");
					textNombreReg.setText("");
					textContraReg.setText("");
					textCorreoReg.setText("");
					textFechNacReg.setText("");
					textTelReg.setText("");

					correcto = true;
				} else {
					VErrorDatosIncorrectos ventana = new VErrorDatosIncorrectos();
					ventana.setVisible(true);
				}

			} else {
				VErrorUsuExist ventana = new VErrorUsuExist();
				ventana.setVisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean ComprobarDni(String text) {
		boolean correcto = false;
		String letras;
		int numero;
		char letra[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };

		if (text.length() == 9) {
			letras = text.substring(0, 8);

			numero = Integer.valueOf(letras) % 23;

			for (int i = 0; i < text.length(); i++) {
				if (!Character.isDigit(text.charAt(9))) {
					if (letra[numero] == text.charAt(9)) {
						correcto = true;
					} else {
						correcto = false;
					}
				} else {
					correcto = false;
				}

			}
		}
		return correcto;
	}

	private boolean comprobarTelefono(String text) {

		boolean correcto = false;

		if (text.length() == 9) {

			for (int i = 0; i < text.length(); i++) {
				if (!Character.isDigit(text.charAt(i))) {
					correcto = false;
					i = text.length();
				} else {
					correcto = true;
				}

			}
		}
		return correcto;
	}
}
