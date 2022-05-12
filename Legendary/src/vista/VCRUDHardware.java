package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class VCRUDHardware extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VCRUDHardware dialog = new VCRUDHardware();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VCRUDHardware() {
		setBounds(100, 100, 613, 551);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "HARDWARE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel.setBounds(48, 67, 46, 14);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(48, 92, 207, 31);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("MARCA");
		lblNewLabel_1.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(338, 67, 86, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PRECIO");
		lblNewLabel_2.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(48, 182, 86, 14);
		contentPanel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(338, 92, 207, 31);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(48, 215, 207, 31);
		contentPanel.add(textField_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("COLOR");
		lblNewLabel_2_1.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(338, 182, 86, 14);
		contentPanel.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(338, 215, 207, 31);
		contentPanel.add(textField_3);
		
		JLabel lblNewLabel_2_2 = new JLabel("TIPO");
		lblNewLabel_2_2.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(48, 301, 86, 14);
		contentPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("sTOCK");
		lblNewLabel_2_2_1.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_2_1.setBounds(338, 301, 86, 14);
		contentPanel.add(lblNewLabel_2_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(48, 339, 207, 31);
		contentPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(338, 339, 207, 31);
		contentPanel.add(textField_5);
		
		JButton btnNewButton = new JButton("COMPRAR");
		btnNewButton.setBounds(13, 451, 160, 38);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("A\u00D1ADIR");
		btnNewButton_1.setBounds(218, 451, 160, 38);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("MODIFICAR");
		btnNewButton_2.setBounds(424, 451, 160, 38);
		contentPanel.add(btnNewButton_2);
	}	
}
