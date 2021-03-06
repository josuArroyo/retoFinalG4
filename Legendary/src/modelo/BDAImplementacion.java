package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import Excepciones.ExceptionManager;
import clases.*;

/**
 * Esta clase sirve para conectar la base de datos con la aplicacion permitiendonos asi realizar sentencias sql
 * @author 1dam
 *
 */
public class BDAImplementacion implements ControladorDatos {

	// declaramos variables de conexion con BD
	private Connection con;
	private PreparedStatement stmt;

	// Los siguientes atributos se utilizan para recoger los valores del fich de
	// configuración
	/**  **/
	private ResourceBundle configFile;
	private String driverBD;
	/** En esta variable se almacena la url de la base de datos **/
	private String urlBD;
	/** En esta variable se almacena el usuario de la base de datos **/
	private String userBD;
	/** En esta variable se almacena la contraseña que corresponde el usuario que guardas en la variable anterior **/
	private String passwordBD;

	// Sentencias SQL

	// Usuarios
	
	final String ObtenerUsu = "select * from usuario where dni=? and contrasenia=?";
	final String ObtenerDniUsu = "select * from usuario";
	final String ObtenerUsuario = "Select distinct nombre, dni from Usuario group by nombre";
	final String EliminarUsuario = "delete from usuario where dni = ?";
	final String AltaUsuario = "CALL `AltaUsuario`(?, ?, ?, ?, ?, ?, ?, ?)";
	final String ModificarUsuario = "{CALL `ModificarUsuario`(?,?,?,?,?,?,?,?)}";

	// Hardware
	final String OBTENERhardw = "Select distinct * from hardware group by tipo";
	final String ObtenerDatosHardw = "Select * from hardware where tipo = ?";
	final String updateHardware = "UPDATE hardware SET hardware.precio= ?,hardware.stock = ? WHERE hardware.id_hardware =?";
	final String altaHardware = "INSERT INTO hardware (id_hardware,nombre,precio,marca,tipo,stock,precio_coste) VALUES (?, ?, ?, ?, ?, ?, ?)";
	final String ComprarProducto = "{CALL `ComprarProducto`(?,?,?,?)}";

	// Torneo
	final String ObtenerJuego = "Select distinct * from torneo group by juego";
	final String ObtenerDatosTorneos = "select * from torneo where juego = ?";
	final String ObtenerDatosTorneoOficial = "select t.*, tof.* from torneo t, torneo_oficial tof where t.id_torneo = tof.id_torneo and juego = ?";
	final String ObtenerDatosTorneoNoOficial = "select t.*, tor.* from torneo t, torneo_regla tor where t.id_torneo = tor.id_torneo and juego =? ";
	final String AltaTorneo = "CALL `AñadirTorneo`(?, ?, ?, ?, ?, ?, ?)";
	final String Inscribirse = "CALL `Insribir`(?,?)";

	// Reservas
	final String ObtenerIdPlaza = "select p.id_plaza from plaza p, reserva r where p.id_plaza not in(select id_plaza from reserva) limit 1";
	final String ReservarPlaza = "CALL `ReservarPlaza`(?, ?, ?, ?)";

	// Para la conexión utilizamos un fichero de configuaración, config que
	// guardamos en el paquete control:
	
	/**
	 * Este metodo se usa para guardar la conexion con la base de datos
	 */
	public BDAImplementacion() {

		this.configFile = ResourceBundle.getBundle("modelo.config");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

	// Metodo para conectarse a la base de datos.
	
	/**
	 * Este metodo se usa para establecer la conexion con la base de datos
	 */
	public void openConnection() {
		try {
			con = DriverManager.getConnection(urlBD, userBD, passwordBD);
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");

			e.printStackTrace();
		}
	}

	// Metodo para desconectarse de la BD.
	/**
	 * Esta metodo se usa para cerrar la conexion con la base de datos
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null) {
			con.close();
		}
	}

	// Usuarios
	// Metodo para aniadir Usuarios.
	
	/**
	 * Este metodo sirve para dar de alta a un usuario en la base de datos
	 * @param usu
	 * @throws ExceptionManager
	 */
	@Override
	public void altaUsuario(Usuario usu) throws ExceptionManager {
		// TODO Auto-generated method stub

		// Abrimos la conexion con la BD.
		this.openConnection();

		try {
			// Usamos la variable de conexion para usar la variable de ejecucion de
			// sentencias.
			stmt = con.prepareStatement(AltaUsuario);

			// recogemos los valores por pantalla para enviarlos a la BD.
			stmt.setString(1, usu.getDni());
			stmt.setString(2, usu.getNombre());
			stmt.setString(3, usu.getContrasenia());
			stmt.setString(4, usu.getCorreo());
			stmt.setDate(5, Date.valueOf(usu.getFechaNac()));
			stmt.setInt(6, usu.getTelefono());
			stmt.setString(7, usu.getSexo());
			stmt.setBoolean(8, false);

			// Ejecutamos la sentecia de actualizacion.
			stmt.executeUpdate();

		} catch (SQLException e1) {
			String error = "Error al dar de alta";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				String error = "Error al dar de alta";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

	}

	/**
	 * Este metodo sirve para modificar a un usuario en la base de datos
	 * @param usu
	 * @throws ExceptionManager
	 */
	@Override
	public void modificarUsuario(Usuario usu) throws ExceptionManager {

		this.openConnection();

		try {

			stmt = con.prepareStatement(ModificarUsuario);

			stmt.setString(1, usu.getDni());
			stmt.setString(2, usu.getNombre());
			stmt.setString(3, usu.getContrasenia());
			stmt.setString(4, usu.getCorreo());
			stmt.setDate(5, Date.valueOf(usu.getFechaNac()));
			stmt.setInt(6, usu.getTelefono());
			stmt.setString(7, usu.getSexo());
			stmt.setBoolean(8, usu.isEsAdmin());

			stmt.executeUpdate();

		} catch (SQLException e) {
			String error = "Error al modificar el usuario";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al modificar el usuario";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

	}

	/**
	 * Este metodo sirve para borrar a un usuario en la base de datos
	 * @param dni
	 * @throws ExceptionManager
	 */
	@Override
	public void eliminarUsuario(String dni) throws ExceptionManager {

		this.openConnection();

		try {

			stmt = con.prepareStatement(EliminarUsuario);

			stmt.setString(1, dni);

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String error = "Error al eliminar el usuario";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				String error = "Error al eliminar el usuario";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

	}

	/**
	 * Este metodo sirve para buscar un usuario en la base de datos
	 * @param dni
	 * @param contrasenia
	 * @throws ExceptionManager
	 * @return Usuario
	 */
	@Override
	public Usuario buscarUsuario(String dni, String contrasenia) throws ExceptionManager {

		// Variables
		ResultSet rs = null;
		Usuario usua = null;

		// Abrimos la conexion con la BD.
		this.openConnection();

		try {
			// Usamos la variable de conexion para usar la variable de ejecucion de
			// sentencias.
			stmt = con.prepareStatement(ObtenerUsu);

			// recogemos los valores por pantalle para enviarlos a la BD.
			stmt.setString(1, dni);
			stmt.setString(2, contrasenia);

			// Guardamos el resultado devuelto por la sentencia SQL en una variable
			rs = stmt.executeQuery();

			// Recorremos la variable hasta que nos de null
			if (rs.next()) {
				// Instanciamos la variable usua
				usua = new Usuario();

				// AÃ±adimos los valores de la variable rs a la variable usua
				usua.setDni(rs.getString("dni"));
				usua.setNombre(rs.getString("nombre"));
				usua.setContrasenia(rs.getString("contrasenia"));
				usua.setCorreo(rs.getString("Correo"));
				usua.setFechaNac(rs.getDate("fecha_nac").toLocalDate());
				usua.setTelefono(rs.getInt("telefono"));
				usua.setSexo(rs.getString("sexo"));
				usua.setEsAdmin(rs.getBoolean("es_admin"));

			} else {
				// Instaciamos la variable usua a null
				usua = null;
			}

		} catch (SQLException e) {
			String error = "Error al buscar el usuario";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					String error = "Error al buscar el usuario";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al buscar el usuario";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

		return usua;
	}

	/**
	 * Este metodo sirve para buscar un usuario mediante su dni en la base de datos
	 * @param dni
	 * @throws ExceptionManager
	 * @return boolean
	 */
	@Override
	public boolean buscarUsuarioDni(String dni) throws ExceptionManager {
		// Variables
		ResultSet rs = null;
		Usuario usua = null;
		boolean found = false;

		// Abrimos la conexion con la BD.
		this.openConnection();

		try {
			// Usamos la variable de conexion para usar la variable de ejecucion de
			// sentencias.
			stmt = con.prepareStatement(ObtenerDniUsu);

			// recogemos los valores por pantalle para enviarlos a la BD.
//			stmt.setString(1, dni);

			// Guardamos el resultado devuelto por la sentencia SQL en una variable
			rs = stmt.executeQuery();

			while (rs.next() && !found) {
				// Instanciamos la variable usua
				usua = new Usuario();

				// AÃ±adimos los valores de la variable rs a la variable usua
				usua.setDni(rs.getString("dni"));
				if (usua.getDni().equalsIgnoreCase(dni)) {
					found = true;
				} else {
					found = false;
				}
			}

		} catch (SQLException e) {
			String error = "Error al buscar el usuario";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					String error = "Error al buscar el usuario";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al buscar el usuario";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

		return found;
	}

	/**
	 * Este metodo sirve para listar los usuarios de la base de datos
	 * @param usu
	 * @return {@code ArrayList<Usuario>}
	 * @throws ExceptionManager
	 * 
	 */
	@Override
	public ArrayList<Usuario> listarUsuarios() throws ExceptionManager {
		ResultSet rs = null;
		Usuario usu;
		ArrayList<Usuario> sus = new ArrayList<>();

		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerDniUsu);

			rs = stmt.executeQuery();

			while (rs.next()) {

				usu = new Usuario();
				usu.setDni(rs.getString("dni"));
				usu.setNombre(rs.getString("Nombre"));
				usu.setContrasenia(rs.getString("contrasenia"));
				usu.setCorreo(rs.getString("correo"));
				usu.setFechaNac(rs.getDate("fecha_nac").toLocalDate());
				usu.setTelefono(rs.getInt("telefono"));
				usu.setSexo(rs.getString("sexo"));
				usu.setEsAdmin(rs.getBoolean("es_admin"));

				sus.add(usu);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					String error = "Error al buscar el usuario";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				} catch (Exception ex) {
					String error = "Error al buscar el usuario";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				}
			}

			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al buscar el usuario";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}

		}

		return sus;
	}

	// HARDWARE
	/**
	 * Este metodo sirve para añadir un hardware en la base de datos
	 * @param har
	 * @throws ExceptionManager
	 */
	@Override
	public void aniadirHardware(Hardware har) throws ExceptionManager {
		// Te quiero atti

		this.openConnection();

		try {
			stmt = con.prepareStatement(altaHardware);

			stmt.setInt(1, har.getIdHW());
			stmt.setString(2, har.getNombreHW());
			stmt.setFloat(3, har.getPrecioHW());
			stmt.setString(4, har.getMarcaHW());
			stmt.setString(5, har.getTipoHW());
			stmt.setInt(6, har.getStockHW());
			stmt.setFloat(7, har.getPrecioCosteHW());

			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error al añadir hardware";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al añadir hardware";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

	}

	/**
	 * Este metodo sirve para modificar un hardware en la base de datos
	 * @param har
	 * @throws ExceptionManager
	 */
	@Override
	public void modificarHardware(Hardware har) throws ExceptionManager {

		this.openConnection();

		try {
			stmt = con.prepareStatement(updateHardware);

			stmt.setFloat(1, har.getPrecioHW());
			stmt.setInt(2, har.getStockHW());
			stmt.setInt(3, har.getIdHW());

			stmt.executeUpdate();

		} catch (SQLException e1) {
			String error = "Error al añadir hardware";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al añadir hardware";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

	}

	/**
	 * Este metodo sirve para comprar hardware en la aplicacion
	 * @param fac
	 * @param dni
	 * @throws ExceptionManager
	 */
	@Override
	public void comprarHardware(Factura fac, String Dni) throws ExceptionManager {

		this.openConnection();

		try {

			stmt = con.prepareCall(ComprarProducto);

			stmt.setString(1, fac.getNombre());
			stmt.setInt(2, fac.getCantidad());
			stmt.setString(3, fac.getDni());
			stmt.setDate(4, Date.valueOf(fac.getFechaFactura()));

			stmt.executeUpdate();

		} catch (SQLException e1) {
			String error = "Error al añadir hardware";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;

		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al añadir hardware";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

	}

	/**
	 * Este metodo sirve para listar los datos de el hardware de la base de datos
	 * @param tipo
	 * @return {@code ArrayList<Hardware>}
	 * @throws ExceptionManager
	 */
	@Override
	public ArrayList<Hardware> listarDatosHardware(String tipo) throws ExceptionManager {

		// Variables
		ArrayList<Hardware> datosHardware = new ArrayList<>();
		ResultSet rs = null;
		Hardware hardw = null;
		// boolean found = false;

		// Abrimos la conexion con la BD.
		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerDatosHardw);

			stmt.setString(1, tipo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				hardw = new Hardware();
				hardw.setIdHW(rs.getInt("id_hardware"));
				hardw.setNombreHW(rs.getString("nombre"));
				hardw.setPrecioHW(rs.getFloat("precio"));
				hardw.setMarcaHW(rs.getString("marca"));
				hardw.setTipoHW(rs.getString("tipo"));
				hardw.setStockHW(rs.getInt("stock"));
				hardw.setPrecioCosteHW(rs.getFloat("precio_coste"));
				datosHardware.add(hardw);

			}

		} catch (SQLException e) {
			String error = "Error al añadir hardware";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					String error = "Error al añadir hardware";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al añadir hardware";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

		return datosHardware;

	}

	/**
	 * Este metodo sirve para listar el tipo de hardware en la base de datos
	 * @return {@code ArrayList<Hardware>}
	 * @throws ExceptionManager
	 */
	@Override
	public ArrayList<Hardware> listarTipoHardware() throws ExceptionManager {
		ResultSet rs = null;
		Hardware hardw;
		ArrayList<Hardware> tipohw = new ArrayList<>();

		this.openConnection();

		try {
			stmt = con.prepareStatement(OBTENERhardw);

			rs = stmt.executeQuery();

			while (rs.next()) {

				hardw = new Hardware();
				hardw.setTipoHW(rs.getString("Tipo").toString());
				tipohw.add(hardw);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("Error en el cierre del ResultSet");
				} catch (Exception ex) {
					System.out.println("Error consulta props");
				}
			}

			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				String error = "Error al añadir hardware";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}

		}

		return tipohw;
	}

	// TORNEO
	/**
	 * Este metodo sirve para añadir un torneo en la base de datos
	 * @param tor
	 * @throws ExceptionManager
	 */
	@Override
	public void aniadirTorneo(Torneo tor) throws ExceptionManager {

		this.openConnection();

		try {
			stmt = con.prepareCall(AltaTorneo);

			stmt.setInt(1, tor.getIdTorneo());
			stmt.setString(2, tor.getNombre());
			stmt.setInt(3, tor.getAforo());
			stmt.setString(4, tor.getJuego());
			stmt.setDate(5, Date.valueOf(tor.getFecha()));
			stmt.setString(6, tor.getTipo());
			if (tor.getTipo().equalsIgnoreCase("oficial")) {
				stmt.setString(7, ((TorneoOficial) tor).getPremio());
			} else {
				stmt.setString(7, ((TorneoNoOficial) tor).getRegla());
			}

			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error al añadir torneo";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al añadir torneo";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}
	}

	/**
	 * Este metodo sirve para listar los datos de los torneos de la base de datos en base a su juego
	 * @param juego
	 * @throws ExceptionManager
	 */
	@Override
	public ArrayList<Torneo> listarDatosTorneos(String juego) throws ExceptionManager {
		ArrayList<Torneo> listaTorneos = new ArrayList<>();
		ResultSet rs = null;
		ResultSet rs2 = null;
		Torneo tor = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerDatosTorneoOficial);

			stmt.setString(1, juego);
			rs = stmt.executeQuery();

			while (rs.next()) {
				tor = new TorneoOficial();

				tor.setIdTorneo(rs.getInt("id_torneo"));
				tor.setNombre(rs.getString("nombre"));
				tor.setAforo(rs.getInt("aforo"));
				tor.setJuego(rs.getString("juego"));
				tor.setDir(rs.getString("direccion"));
				tor.setFecha(rs.getDate("fecha").toLocalDate());
				tor.setTipo(rs.getString("tipo"));
				((TorneoOficial) tor).setPremio(rs.getString("premio"));
				listaTorneos.add(tor);

			}

		} catch (SQLException e) {
			String error = "Error al listar torneo";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		}
		try {
			stmt = con.prepareStatement(ObtenerDatosTorneoNoOficial);

			stmt.setString(1, juego);
			rs2 = stmt.executeQuery();

			while (rs2.next()) {
				tor = new TorneoNoOficial();

				tor.setIdTorneo(rs2.getInt("id_torneo"));
				tor.setNombre(rs2.getString("nombre"));
				tor.setAforo(rs2.getInt("aforo"));
				tor.setJuego(rs2.getString("juego"));
				tor.setDir(rs2.getString("direccion"));
				tor.setFecha(rs2.getDate("fecha").toLocalDate());
				tor.setTipo(rs2.getString("tipo"));
				((TorneoNoOficial) tor).setRegla(rs2.getString("regla"));
				listaTorneos.add(tor);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					String error = "Error al listar torneo";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				}
			}
			if (rs2 != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					String error = "Error al listar torneo";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al listar torneo";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

		return listaTorneos;
	}

	/**
	 * Este metodo sirve para listar el tipo de juego de los torneos de la base de datos
	 * @return {@code ArrayList<Torneo>}
	 * @throws ExceptionManager
	 */
	public ArrayList<Torneo> listarJuegoTorneo() throws ExceptionManager {
		ResultSet rs = null;
		Torneo tor;
		ArrayList<Torneo> tipoJuego = new ArrayList<>();

		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerJuego);

			rs = stmt.executeQuery();

			while (rs.next()) {

				tor = new Torneo();

				tor.setJuego(rs.getString("juego").toString());
				tipoJuego.add(tor);

			}
		} catch (SQLException e) {
			String error = "Error al listar torneo";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			// cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					String error = "Error al listar tipo torneo";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				} catch (Exception ex) {
					String error = "Error al listar tipo torneo";
					ExceptionManager uwu = new ExceptionManager(error);
					throw uwu;
				}
			}

			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al listar tipo torneo";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}

		}

		return tipoJuego;

	}

	/**
	 * Este metodo sirve para que los usuarios se inscriban a los torneos
	 * @param usu
	 * @param tor
	 * @throws ExceptionManager
	 */
	@Override
	public void inscribirse(Usuario usu, Torneo tor) throws ExceptionManager {

		this.openConnection();

		try {
			stmt = con.prepareStatement(Inscribirse);

			stmt.setString(1, usu.getDni());
			stmt.setInt(2, tor.getIdTorneo());

			stmt.executeUpdate();

		} catch (SQLException e) {
			String error = "Error al inscribirse al torneo";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al inscribirse al torneo";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

	}

	// RESERVA

	/**
	 * Este metodo sirve para reservar una plaza en el local
	 * @param rev
	 * @throws ExceptionManager
	 */
	@Override
	public void reservarPlaza(Reserva rev) throws ExceptionManager {

		this.openConnection();

		try {
			// Usamos la variable de conexion para usar la variable de ejecucion de
			// sentencias.
			stmt = con.prepareStatement(ReservarPlaza);

			// recogemos los valores por pantalla para enviarlos a la BD.
			stmt.setInt(1, rev.getId_Plaza());
			stmt.setString(2, rev.getDni());
			stmt.setDate(3, rev.getFecha_ini());
			stmt.setDate(4, rev.getFecha_fin());
			// Ejecutamos la sentecia de actualizacion.
			stmt.executeUpdate();
		} catch (SQLException e) {
			String error = "Error al Error al reservar plaza";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;

		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al reservar plaza";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}
	}

	/**
	 * Este metodo sirve traed el id de las plazas disponibles
	 * @return int
	 * @throws ExceptionManager
	 */
	@Override
	public int traerIDPlaza() throws ExceptionManager {
		int id_plaza = 1;
		ResultSet rs = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerIdPlaza);
			rs = stmt.executeQuery();

			if (rs.next()) {
				id_plaza = rs.getInt("id_plaza");
			}

		} catch (SQLException e) {
			String error = "Error al traer id plaza";
			ExceptionManager uwu = new ExceptionManager(error);
			throw uwu;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al traer id plaza";
				ExceptionManager uwu = new ExceptionManager(error);
				throw uwu;
			}
		}

		return id_plaza;
	}
}
