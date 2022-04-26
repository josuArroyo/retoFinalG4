package vista;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VFechaIncorrecta extends JPanel {

	/**
	 * Create the panel.
	 */
	public VFechaIncorrecta() {
		JOptionPane.showMessageDialog(null, "Fecha Incorrecta o fecha reservada.","Fecha Incorrecta", JOptionPane.ERROR_MESSAGE);
	}

}
