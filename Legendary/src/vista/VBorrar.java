package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VBorrar extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VBorrar dialog = new VBorrar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VBorrar() {
		setBounds(100, 100, 589, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("En esta ventana podras borrar usuarios");
			lblNewLabel.setBounds(33, 50, 414, 24);
			contentPanel.add(lblNewLabel);
		}
		
		JComboBox comboNombreDni = new JComboBox();
		comboNombreDni.setBounds(33, 107, 299, 37);
		contentPanel.add(comboNombreDni);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres eliminar a este usuario?");
				
				if(JOptionPane.OK_OPTION == confirmar) {
					JOptionPane.showMessageDialog(null, "El usuario se ha borrado.");
				}else {
					JOptionPane.showMessageDialog(null, "El usuario no se ha borrado.");
				}
			}
		});
		btnBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnBorrar.setBounds(33, 190, 180, 59);
		contentPanel.add(btnBorrar);
		
		JButton btnCancelBorrar = new JButton("Cancelar");
		btnCancelBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCancelBorrar.setBounds(301, 190, 196, 59);
		contentPanel.add(btnCancelBorrar);
	}
}
