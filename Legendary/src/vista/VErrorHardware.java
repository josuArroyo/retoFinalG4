package vista;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VErrorHardware extends JPanel {

	/**
	 * Create the panel.
	 */
	public VErrorHardware() {
		JOptionPane.showMessageDialog(null, "No existe ningun hardware con esas caracteristicas.","Datos incorrectos", JOptionPane.ERROR_MESSAGE);
	}

}
