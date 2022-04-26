package vista;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VErrorDatosIncorrectos extends JPanel {

	/**
	 * Create the panel.
	 */
	public VErrorDatosIncorrectos() {
		JOptionPane.showMessageDialog(null, "Introduzca el tipo de datos correctos en el campo.","Datos incorrectos", JOptionPane.ERROR_MESSAGE);
		
	}

}
