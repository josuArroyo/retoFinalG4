package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import clases.*;

public class BDAImplementacion implements ControladorDatos {

	// declaramos variables de conexion con BD
	private Connection con;
	private PreparedStatement stmt;

	// Sentencias SQL

	final String ObtenerUsu = "select * from usuario where dni=? and contrasenia=?";
	final String ObtenerDniUsu = "select * from usuario";
	final String ObtenerIdPlaza = "select p.id_plaza from plaza p, reserva r where p.id_plaza not in(select id_plaza from reserva) limit 1";
	final String OBTENERhardw = "Select distinct * from hardware group by tipo";
	final String ObtenerDatosHardw = "Select * from hardware where tipo = ?";
	final String ObtenerJuego = "Select * from torneo";

	// El procedimiento recibira por pantalla los paremetro que se introduciran en
	// la BD para añiadir un usuario.
	final String AltaUsuario = "CALL `AltaUsuario`(?, ?, ?, ?, ?, ?, ?, ?)";
	final String ObtenerUsuario = "Select distinct nombre, dni from Usuario group by nombre";
	final String ReservarPlaza = "CALL `ReservarPlaza`(?, ?, ?, ?)";
	

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
			// TODO Auto-generated catch block
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
	public void comprarHardware(Hardware har) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
	public ArrayList<Torneo> listarDatosTorneos() {
		// TODO Auto-generated method stub
		return null;
	}

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

		}
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

	public ArrayList<Torneo> listarTipoTorneo() {
		ResultSet rs = null;
		Torneo tor;
		Map<String, Torneo> tipoJuego = new TreeMap<>();

		this.openConnection();

		try {
			stmt = con.prepareStatement(ObtenerJuego);

			rs = stmt.executeQuery();

			while (rs.next()) {

				tor = new Torneo();
				tor.setJuego(rs.getString(""));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
		}

		return id_plaza;

	}

}
