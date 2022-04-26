package vista;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VErrorUsuExist extends JPanel {

	/**
	 * Create the panel.
	 */
	public VErrorUsuExist() {
		JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese DNI.","Datos incorrectos", JOptionPane.ERROR_MESSAGE);
	}

}
