package modelo;

import java.util.ArrayList;
import java.util.Map;

import Excepciones.ExceptionManager;
import clases.*;

/**
 * 
 * @author 1dam
 *
 * Esta interfaz es la que enlaza la base de datos con el programa.
 */

public interface ControladorDatos {

	// Todo de Usuarios

	/**
	 * Este metodo lo usaremos para a?adir usuarios a nuestra base de datos
	 * 
	 * @param usu
	 * @throws ExceptionManager
	 */
	public void altaUsuario(Usuario usu) throws ExceptionManager;

	/**
	 * Este metodo se usa para modificar los usuarios de nuestra base de datos
	 * 
	 * @param usu
	 * @throws ExceptionManager
	 */
	public void modificarUsuario(Usuario usu) throws ExceptionManager;

	/**
	 * Este metodo se usa para eliminar usuarios de nuestra base de datos
	 * 
	 * @param dni
	 * @throws ExceptionManager
	 */
	public void eliminarUsuario(String dni) throws ExceptionManager;

	/**
	 * Este metodo lo usamos para buscar un usuario en nuestra base de datos
	 * 
	 * @param dni
	 * @param contrasenia
	 * @throws ExceptionManager
	 * @return Usuario
	 */
	public Usuario buscarUsuario(String dni, String contrasenia) throws ExceptionManager;

	/**
	 * Este metodo lo usamos para buscar el dni de un usuario en la base de datos
	 * 
	 * @param dni
	 * @return boolean
	 * @throws ExceptionManager
	 */
	public boolean buscarUsuarioDni(String dni) throws ExceptionManager;

	/**
	 * Este metodo lo usamos para conseguir una lista de los usuarios de la base de
	 * datos
	 * 
	 * @return {@code ArrayList<Usuario>}
	 * @throws ExceptionManager
	 */
	public ArrayList<Usuario> listarUsuarios() throws ExceptionManager;

	// Todo de Hardware

	/**
	 * Este metodo lo usamos para a?adir un hardware a la base de datos
	 * 
	 * @param har
	 * @throws ExceptionManager
	 */
	public void aniadirHardware(Hardware har) throws ExceptionManager;

	/**
	 * Este metodo lo usamos para modificar los datos de el hardware de nuestra base
	 * de datos
	 * 
	 * @param har
	 * @throws ExceptionManager
	 */
	public void modificarHardware(Hardware har) throws ExceptionManager;

	/**
	 * Este metodo se usa para comprar hardware de nuestra aplicacion
	 * 
	 * @param fac
	 * @param Dni
	 * @throws ExceptionManager
	 */
	public void comprarHardware(Factura fac, String Dni) throws ExceptionManager;

	/**
	 * 
	 * Este metodo lo usamos para listar los datos de Hardware de nuestra base de
	 * datos
	 * 
	 * @param tipo
	 * @return {@code ArrayList<Hardware>}
	 * @throws ExceptionManager
	 */
	public ArrayList<Hardware> listarDatosHardware(String tipo) throws ExceptionManager;

	/**
	 * Este metodo se usa para listar el tipo de hardware de la base de datos
	 * 
	 * @return {@code ArrayList<Hardware>}
	 * @throws ExceptionManager
	 */
	public ArrayList<Hardware> listarTipoHardware() throws ExceptionManager;

	// Todo Torneo

	/**
	 * Este metodo lo usamos para a?adir torneos a la base de datos
	 * 
	 * @param tor
	 * @throws ExceptionManager
	 */
	public void aniadirTorneo(Torneo tor) throws ExceptionManager;

	/**
	 * Este metodo se usa para listar los datos de torneo de la base de datos
	 * 
	 * @param juego
	 * @return {@code ArrayList<Torneo>}
	 * @throws ExceptionManager
	 */
	public ArrayList<Torneo> listarDatosTorneos(String juego) throws ExceptionManager;

	/**
	 * Este metodo lo usamos para lista el tipo de juego de los torneos de la base
	 * de datos
	 * 
	 * @return {@code ArrayList<Torneo>}
	 * @throws ExceptionManager
	 */
	public ArrayList<Torneo> listarJuegoTorneo() throws ExceptionManager;

	/**
	 * Este metodo se usa para inscribir personas en torneo
	 * 
	 * @param usu
	 * @param tor
	 * @throws ExceptionManager
	 */
	public void inscribirse(Usuario usu, Torneo tor) throws ExceptionManager;

	// Todo Plazas

	/**
	 * Este metodo lo usamos para reservar plazas de nuestro local
	 * 
	 * @param rev
	 * @throws ExceptionManager
	 */
	public void reservarPlaza(Reserva rev) throws ExceptionManager;

	/**
	 * Este metodo nos devuelve el id de las plazas disponibles
	 * 
	 * @return int
	 * @throws ExceptionManager
	 */
	public int traerIDPlaza() throws ExceptionManager;

}
