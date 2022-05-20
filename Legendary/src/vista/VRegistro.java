package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.ExceptionManager;
import clases.Hardware;
import clases.Usuario;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

/**
 * Esta es la clase para registrarse
 * 
 * @author 1dam
 *
 */
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
	 * Este es el constructor de la ventana cuando se viene de la ventana login
	 * 
	 * @param ventanaPadre
	 * @param modal
	 * @param usuario
	 */
	public VRegistro(VLogin ventanaPadre, boolean modal, Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);
		cargarVentana(true, usuario);
	}

	/**
	 * Este es el constructor de la ventana cuando se viene de la ventana Menu
	 * 
	 * @param ventanaPadre
	 * @param modal
	 * @param cargando
	 */
	public VRegistro(Menu ventanaPadre, boolean modal, Usuario cargando) {
		super(ventanaPadre);
		this.setModal(modal);
		cargarVentana(false, cargando);

		textDniReg.setEnabled(false);
		textFechNacReg.setEnabled(false);

		if (cargando != null) {
			textDniReg.setText(String.valueOf(cargando.getDni()));
			textNombreReg.setText(String.valueOf(cargando.getNombre()));
			textContraReg.setText(String.valueOf(cargando.getContrasenia()));
			textTelReg.setText(String.valueOf(cargando.getTelefono()));
			textCorreoReg.setText(String.valueOf(cargando.getCorreo()));
			textFechNacReg.setText(String.valueOf(cargando.getFechaNac()));
			Enumeration<AbstractButton> radios = grupo1.getElements();
			while (radios.hasMoreElements()) {
				JRadioButton radio = (JRadioButton) radios.nextElement();
				if (radio.getText().equalsIgnoreCase(cargando.getSexo())) {
					radio.setSelected(true);
				}
			}

		}
	}

	/**
	 * Este metodo sirve para mostrar la ventana
	 * 
	 * @param opc
	 * @param usu
	 */
	private void cargarVentana(boolean opc, Usuario usu) {

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
				alta();

			}
		});

		btnAlta.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnAlta.setBounds(47, 528, 119, 46);
		contentPanel.add(btnAlta);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatos(usu);
			}
		});
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
		} else if (!opc) {
			btnAlta.setVisible(false);

		}
	}

	/**
	 * El metodo se usa para modicar los datos de los usuarios
	 * 
	 * @param usuario
	 */
	protected void modificarDatos(Usuario usuario) {

		try {
			Usuario usu = ModiPantallaUsu();
			if (comprobarTelefono(String.valueOf(usu.getTelefono()))) {
				datos.modificarUsuario(usu);
			}

		} catch (ExceptionManager e) {

			JOptionPane.showMessageDialog(this, e.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Este metodo nos devuelve el objeto usuario con los nuevos datos para
	 * modificar
	 * 
	 * @return Usuario
	 */
	private Usuario ModiPantallaUsu() {

		Usuario usu = new Usuario();

		usu.setDni(textDniReg.getText());
		usu.setContrasenia(textContraReg.getText());
		usu.setNombre(textNombreReg.getText());
		usu.setTelefono(Integer.valueOf(textTelReg.getText()));
		usu.setCorreo(textCorreoReg.getText());
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		usu.setFechaNac(LocalDate.parse(textFechNacReg.getText(), formateador));
		usu.setSexo(grupo1.getSelection().getActionCommand());

		return usu;

	}

	/**
	 * El metodo sirve para cerrar la ventana actual
	 */
	protected void cancelar() {
		this.dispose();
	}

	/**
	 * El metodo se usa para dar de alta a un usuario
	 */
	protected void alta() {

		Usuario us = new Usuario();
		LocalDate fecha;
		boolean found = false;
		boolean correcto = false;

		try {
			found = datos.buscarUsuarioDni(textDniReg.getText());
		} catch (ExceptionManager e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
		}
		try {
			if (textDniReg.getText().isEmpty() || textNombreReg.getText().isEmpty() || textContraReg.getText().isEmpty()
					|| textFechNacReg.getText().isEmpty() || textCorreoReg.getText().isEmpty()
					|| textTelReg.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mete los datos en su sitio ");
			} else {

				if (!found) {

					if (ComprobarDni(textDniReg.getText())) {

						us.setDni(textDniReg.getText());
						us.setNombre(textNombreReg.getText());
						us.setContrasenia(textContraReg.getText());
						us.setCorreo(textCorreoReg.getText());

						DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						fecha = LocalDate.parse(textFechNacReg.getText(), formateador);
						us.setFechaNac(fecha);

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

							JOptionPane.showMessageDialog(null, "Registro realizado con exito.");
						} else {

							JOptionPane.showMessageDialog(null, "Introduzca el tipo de datos correctos en el campo.",
									"Datos incorrectos", JOptionPane.ERROR_MESSAGE);

						}

					} else {

						JOptionPane.showMessageDialog(null, "El dni esta mal introduzca el dni correcto ",
								"Datos incorrectos", JOptionPane.ERROR_MESSAGE);

					}

				} else {
					JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese DNI.", "Datos incorrectos",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
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
				if (!Character.isDigit(text.charAt(8))) {
					if (letra[numero] == text.charAt(8)) {
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

	/**
	 * El metodo sirve para comprobar que el Telefono sea de 9 digitos
	 * 
	 * @param text
	 * @return
	 */
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
