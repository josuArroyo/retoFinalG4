package controlador;

import modelo.BDAImplementacion;
import modelo.ControladorDatos;
import vista.VLogin;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ControladorDatos datos = new BDAImplementacion();
		
		VLogin principal = new VLogin(datos);
		principal.setVisible(true);
		
		
	}

}
