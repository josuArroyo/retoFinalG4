package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VGestionDatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControladorDatos datos = new BDAImplementacion();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @param b
	 * @param menu
	 */
	public VGestionDatos(Menu ventanaPadre, boolean modal) {
		super(ventanaPadre);
		this.setModal(modal);
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
				int confirmar = JOptionPane.showConfirmDialog(null,
						"Estas seguro de que quieres eliminar a este usuario?");

				if (JOptionPane.OK_OPTION == confirmar) {
					JOptionPane.showMessageDialog(null, "El usuario se ha borrado.");
				} else {
					JOptionPane.showMessageDialog(null, "El usuario no se ha borrado.");
				}
			}
		});
		btnBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnBorrar.setBounds(33, 212, 129, 37);
		contentPanel.add(btnBorrar);

		JButton btnCancelBorrar = new JButton("Cancelar");
		btnCancelBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCancelBorrar.setBounds(391, 212, 143, 37);
		contentPanel.add(btnCancelBorrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarRegistro();
			}
		});
		btnModificar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnModificar.setBounds(200, 212, 143, 37);
		contentPanel.add(btnModificar);
	}

	protected void cargarRegistro() {
		VRegistro ventanaRegistro = new VRegistro(this, true, datos);
		ventanaRegistro.setVisible(true);
	}
}
