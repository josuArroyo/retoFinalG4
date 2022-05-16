package modelo;

import java.util.ArrayList;
import java.util.Map;

import Excepciones.ExceptionManager;
import clases.*;

public interface ControladorDatos {

	//Todo de Usuarios
	
	public void altaUsuario(Usuario usu) ;
	
	public void modificarUsuario(Usuario usu);
	
	public void eliminarUsuario(String dni,String nombre);
	
	public Usuario buscarUsuario(String dni, String contrasenia);
	
	public boolean buscarUsuarioDni(String dni);
	
	public boolean esAdmin(String dni);
	

	public  ArrayList<Usuario> listarUsuarios();

	
	//Todo de Hardware
	
	public void aniadirHardware(Hardware har);
	
	public Hardware buscarHardware(Hardware har);
	
	public void modificarHardware(Hardware har);
	
	public void comprarHardware(Factura fac);
	

	public  ArrayList<Hardware> listarHardware();
	
	public  ArrayList<Hardware>  listarDatosHardware(String  tipo);
	
	public ArrayList<Hardware> listarTipoHardware();

	
	// Todo Torneo 
	
	public void aniadirTorneo(Torneo tor, String opcion, String opcionNoOfficial);
	
	public void buscarTorneo(Torneo tor);
	
	public void inscribirse(Usuario usu,Torneo tor);

	public ArrayList<Torneo> listarTorneos();
	
	public ArrayList<Torneo> listarDatosTorneos(String juego);
	
	public ArrayList<Torneo> listarJuegoTorneo();

	
	//Todo Plazas 
	
	public void reservarPlaza(Plaza plaz);
	
	
}
