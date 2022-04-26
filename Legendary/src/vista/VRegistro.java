package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class VRegistro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDniReg;
	private JTextField textContraReg;
	private JTextField textNombreReg;
	private JTextField textTelReg;
	private JTextField textCorreoReg;
	private JTextField textFechNacReg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VRegistro dialog = new VRegistro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VRegistro() {
		setBounds(100, 100, 636, 636);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbltexto = new JLabel("Rellene los datos que aparecen a continuacion para registrarse en nuestra aplicacion.");
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
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(47, 484, 53, 23);
		contentPanel.add(rdbtnMujer);
		
		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(122, 484, 69, 23);
		contentPanel.add(rdbtnHombre);
		
		JRadioButton rdbtnOtro = new JRadioButton("Otro");
		rdbtnOtro.setBounds(207, 484, 53, 23);
		contentPanel.add(rdbtnOtro);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnAlta.setBounds(47, 528, 119, 46);
		contentPanel.add(btnAlta);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnModificar.setBounds(204, 528, 158, 46);
		contentPanel.add(btnModificar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCancelar.setBounds(401, 529, 144, 46);
		contentPanel.add(btnCancelar);
	}
}
