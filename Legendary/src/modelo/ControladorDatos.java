package modelo;

import java.util.ArrayList;
import java.util.Map;

import Excepciones.ExceptionManager;
import clases.*;

public interface ControladorDatos {

	//Todo de Usuarios
	
	public void altaUsuario(Usuario usu) throws ExceptionManager ;
	
	public void modificarUsuario(Usuario usu) throws ExceptionManager;
	
	public void eliminarUsuario(String dni) throws ExceptionManager;
	
	public Usuario buscarUsuario(String dni, String contrasenia) throws ExceptionManager;
	
	public boolean buscarUsuarioDni(String dni) throws ExceptionManager;
	
	public  ArrayList<Usuario> listarUsuarios() throws ExceptionManager;

	
	//Todo de Hardware
	
	public void aniadirHardware(Hardware har) throws ExceptionManager;
	
	public void modificarHardware(Hardware har) throws ExceptionManager;
	
	public void comprarHardware(Factura fac, String Dni) throws ExceptionManager;
	
	public  ArrayList<Hardware>  listarDatosHardware(String  tipo) throws ExceptionManager;
	
	public ArrayList<Hardware> listarTipoHardware() throws ExceptionManager;

	
	// Todo Torneo 
	
	public void aniadirTorneo(Torneo tor) throws ExceptionManager;
	
	public ArrayList<Torneo> listarDatosTorneos(String juego) throws ExceptionManager;
	
	public ArrayList<Torneo> listarJuegoTorneo() throws ExceptionManager;
	
	public void inscribirse(Usuario usu, Torneo tor) throws ExceptionManager;

	
	//Todo Plazas 
	
	public void reservarPlaza(Reserva rev) throws ExceptionManager;
	
	public int traerIDPlaza() throws ExceptionManager;
	
}
