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
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VAniadirTorneo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VAniadirTorneo() {
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
		
		JLabel lblPremio = new JLabel("PREMIO");
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(57, 214, 197, 38);
		contentPanel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(378, 214, 197, 38);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(57, 324, 197, 38);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(378, 331, 197, 38);
		contentPanel.add(textField_3);
		
		JRadioButton rdbtnOficial = new JRadioButton("OFICIAL");
		rdbtnOficial.setBounds(42, 430, 77, 23);
		contentPanel.add(rdbtnOficial);
		
		JRadioButton rdbtnNoOficial = new JRadioButton("NO-OFICIAL");
		rdbtnNoOficial.setBounds(127, 430, 99, 23);
		contentPanel.add(rdbtnNoOficial);
		
		JComboBox comboReglas = new JComboBox();
		comboReglas.setBounds(278, 430, 351, 23);
		contentPanel.add(comboReglas);
		
		JButton btnCrearTor = new JButton("Crear");
		btnCrearTor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCrearTor.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCrearTor.setBounds(383, 478, 192, 38);
		contentPanel.add(btnCrearTor);
	}
}
