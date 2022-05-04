package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;

public class VComprar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VComprar dialog = new VComprar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VComprar() {
		setBounds(100, 100, 696, 513);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setBounds(50, 150, 591, 247);
		contentPanel.add(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(71, 70, 326, 46);
		contentPanel.add(comboBox);
		
		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnNewButton.setBounds(50, 408, 161, 46);
		contentPanel.add(btnNewButton);
	}
}
