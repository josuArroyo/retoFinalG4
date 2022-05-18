package controlador;

import modelo.BDAImplementacion;
import modelo.ControladorDatos;
import vista.VLogin;

public class Main {

	/**
	 * Esta clase la usamos para ejecutar el codigo
	 * 
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ControladorDatos datos = new BDAImplementacion();
		
		VLogin principal = new VLogin();
		principal.setVisible(true);
	}
}
