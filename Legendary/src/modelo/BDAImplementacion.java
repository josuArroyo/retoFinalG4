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

public class BDAImplementacion implements ControladorDatos {

	// declaramos variables de conexion con BD
	private Connection con;
	private PreparedStatement stmt;

	// Los siguientes atributos se utilizan para recoger los valores del fich de
	// configuración
	private ResourceBundle configFile;
	private String driverBD;
	private String urlBD;
	private String userBD;
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
	public BDAImplementacion() {

		this.configFile = ResourceBundle.getBundle("modelo.config");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

	// Metodo para conectarse a la base de datos.
	public void openConnection() {
		try {
			con = DriverManager.getConnection(urlBD, userBD, passwordBD);
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");

			e.printStackTrace();
		}
	}

	// Metodo para desconectarse de la BD.
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
	@Override
	public void altaUsuario(Usuario usu) {
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

		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarUsuario(Usuario usu) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void eliminarUsuario(String dni) {

		this.openConnection();

		try {

			stmt = con.prepareStatement(EliminarUsuario);

			stmt.setString(1, dni);

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public Usuario buscarUsuario(String dni, String contrasenia) {

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
			// TODO Auto-generated catch block
			System.out.println("Error en login");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return usua;
	}

	@Override
	public boolean buscarUsuarioDni(String dni) {
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
			// TODO Auto-generated catch block
			System.out.println("Error en login");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return found;
	}

	@Override
	public ArrayList<Usuario> listarUsuarios() {
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
					System.out.println("Error en el cierre del ResultSet");
				} catch (Exception ex) {
					System.out.println("Error consulta props");
				}
			}

			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return sus;
	}

	// HARDWARE
	@Override
	public void aniadirHardware(Hardware har) {
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
			System.out.println("Error en alta SQL");
			e1.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarHardware(Hardware har) {

		this.openConnection();

		try {
			stmt = con.prepareStatement(updateHardware);

			stmt.setFloat(1, har.getPrecioHW());
			stmt.setInt(2, har.getStockHW());
			stmt.setInt(3, har.getIdHW());

			stmt.executeUpdate();

		} catch (SQLException e1) {
			System.out.println("Error en alta SQL");
			e1.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void comprarHardware(Factura fac, String Dni) {

		this.openConnection();

		try {

			stmt = con.prepareCall(ComprarProducto);

			stmt.setString(1, fac.getNombre());
			stmt.setInt(2, fac.getCantidad());
			stmt.setString(3, fac.getDni());
			stmt.setDate(4, Date.valueOf(fac.getFechaFactura()));

			stmt.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();

		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public ArrayList<Hardware> listarDatosHardware(String tipo) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return datosHardware;

	}

	@Override

	public ArrayList<Hardware> listarTipoHardware() {
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
				e.printStackTrace();
			}

		}

		return tipohw;
	}

	// TORNEO
	@Override
	public void aniadirTorneo(Torneo tor) {

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
			System.out.println("Error en alta SQL");
			e1.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Torneo> listarDatosTorneos(String juego) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					e.printStackTrace();
				}
			}
			if (rs2 != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaTorneos;
	}

	public ArrayList<Torneo> listarJuegoTorneo() {
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
				e.printStackTrace();
			}

		}

		return tipoJuego;

	}

	@Override
	public void inscribirse(Usuario usu, Torneo tor) {

		this.openConnection();

		try {
			stmt = con.prepareStatement(Inscribirse);

			stmt.setString(1, usu.getDni());
			stmt.setInt(2, tor.getIdTorneo());

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// RESERVA

	@Override
	public void reservarPlaza(Reserva rev) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public int traerIDPlaza() {
		int id_plaza = 0;
		ResultSet rs = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerIdPlaza);
			rs = stmt.executeQuery();

			if (rs.next()) {
				id_plaza = rs.getInt("id_plaza");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return id_plaza;
	}
}
