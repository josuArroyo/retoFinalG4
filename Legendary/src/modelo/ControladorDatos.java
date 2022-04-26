package modelo;

import java.util.Map;

import clases.*;

public interface ControladorDatos {

	//Todo de Usuarios
	
	public void altaUsuario(Usuario usu);
	
	public void modificarUsuario(Usuario usu);
	
	public void eliminarUsuario(Usuario usu);
	
	public Usuario buscarUsuario(Usuario usu);
	
	public Map<String, Usuario> listarUsuarios();
	
	//Todo de Hardware
	
	public void aniadirHardware(Hardware har);
	
	public Hardware buscarHardware(Hardware har);
	
	public void modificarHardware(Hardware har);
	
	public void comprarHardware(Hardware har);
	
	public Map<String, Hardware> listarHardware();
	
	public Map<String, Hardware> listarDatosHardware();
	
	// Todo Torneo 
	
	public void aniadirTorneo(Torneo tor);
	
	public void buscarTorneo(Torneo tor);
	
	public Map<String, Torneo> listarTorneos();
	
	public Map<String, Torneo> listarDatosTorneos();
	
	//Todo Plazas 
	
	public void reservarPlaza(Plaza plaz);
	
	
}
