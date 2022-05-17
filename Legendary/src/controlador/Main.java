package controlador;

import modelo.BDAImplementacion;
import modelo.ControladorDatos;
import vista.VLogin;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ControladorDatos datos = new BDAImplementacion();
		
		VLogin principal = new VLogin();
		principal.setVisible(true);
	}
<<<<<<< HEAD

}
=======
}
>>>>>>> 45cbd6c424e1baf33e3b4e1011320ec704041541
