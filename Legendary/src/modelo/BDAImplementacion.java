package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Excepciones.ExceptionManager;
import clases.*;

public class BDAImplementacion implements ControladorDatos {

	// declaramos variables de conexion con BD
	private Connection con;
	private PreparedStatement stmt;

	// Sentencias SQL

	// Usuarios
	final String ObtenerUsu = "select * from usuario where dni=? and contrasenia=?";
	final String ObtenerDniUsu = "select * from usuario";
	final String ObtenerAdmin = "select * from usuario where dni = ?";

	// Hardware
	final String OBTENERhardw = "Select distinct * from hardware group by tipo";
	final String ObtenerDatosHardw = "Select * from hardware where tipo = ?";

	// Torneos
	final String ObtenerJuego = "Select distinct * from torneo group by juego";
	final String ObtenerDatosTorneos = "select * from torneo where juego = ?";
	final String ObtenerDatosTorneoOficial = "select t.*, tof.* from torneo t, torneo_oficial tof where t.id_torneo = tof.id_torneo and juego = ?";
	final String ObtenerDatosTorneoNoOficial = "select t.*, tor.* from torneo t, torneo_regla tor where t.id_torneo = tor.id_torneo and juego =? ";

	// El procedimiento recibira por pantalla los paremetro que se introduciran en
	// la BD para añiadir un usuario.
	final String AltaUsuario = "CALL `AltaUsuario`(?, ?, ?, ?, ?, ?, ?, ?)";
	final String ComprarProducto = "{CALL `ComprarProducto`(?,?,?,?)}";

	// Metodo para conectarse a la base de datos.
	public void openConnection() {
		try {
			// Variables para entablar conexion con la BD.
			String url = "jdbc:mysql://localhost:3306/legendary?serverTimezone=Europe/Madrid&useSSL=false";
			String user = "root";
			String password = "abcd*1234";

			// La siguiente linea sirve para conectar a la BD
			con = DriverManager.getConnection(url, user, password);
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

	// Metodo para añadir Usuarios.
	@Override
	public void altaUsuario(Usuario usu) {
		// TODO Auto-generated method stub

		// Abrimos la conexion con la BD.
		this.openConnection();

		try {
			// Usamos la variable de conexion para usar la variable de ejecucion de
			// sentencias.
			stmt = con.prepareStatement(AltaUsuario);

			// recogemos los valores por pantalle para enviarlos a la BD.
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
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarUsuario(String dni, String nombre) {
		// TODO Auto-generated method stub

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

				// Añadimos los valores de la variable rs a la variable usua
				usua.setDni(rs.getString("dni"));
				usua.setContrasenia(rs.getString("contrasenia"));

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

				// Añadimos los valores de la variable rs a la variable usua
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aniadirHardware(Hardware har) {
		// TODO Auto-generated method stub

	}

	@Override
	public Hardware buscarHardware(Hardware har) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarHardware(Hardware har) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comprarHardware(Factura fac) {

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
	public ArrayList<Hardware> listarHardware() {
		// TODO Auto-generated method stub
		return null;
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
	public void aniadirTorneo(Torneo tor) {
		
		
		this.openConnection();
		
		//stmt = con.prepareStatement(AltaUsuario)

	}

	@Override
	public void buscarTorneo(Torneo tor) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Torneo> listarTorneos() {
		// TODO Auto-generated method stub
		return null;
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
				((TorneoNoOficial) tor).setReglas(rs2.getString("regla"));
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

	@Override
	public void reservarPlaza(Plaza plaz) {
		// TODO Auto-generated method stub

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
	public boolean esAdmin(String dni) {

		Usuario usu = null;
		ResultSet rs = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerAdmin);

			stmt.setString(1, dni);
			rs = stmt.executeQuery();

			while (rs.next()) {
				usu.setEsAdmin(rs.getBoolean(""));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
