package vista;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VErrorLogin extends JPanel {

	/**
	 * Create the panel.
	 */
	public VErrorLogin() {
		JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectas.","Datos incorrectos", JOptionPane.ERROR_MESSAGE);
	}

}
