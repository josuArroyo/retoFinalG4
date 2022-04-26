package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import clases.*;

public class BDAImplementacion implements ControladorDatos {

	private Connection con;
	private PreparedStatement stmt;

	public void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/Seliculas?serverTimezone=Europe/Madrid&useSSL=false";
			String user = "root";
			String password = "abcd*1234";

			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
		}
	}
	
	public void closeConnection() throws SQLException {
		if(stmt != null) {
			stmt.close();
		}
		if(con != null) {
			con.close();
		}
	}

	@Override
	public void altaUsuario(Usuario usu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarUsuario(Usuario usu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarUsuario(Usuario usu) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario buscarUsuario(Usuario usu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Usuario> listarUsuarios() {
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
	public void comprarHardware(Hardware har) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Hardware> listarHardware() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Hardware> listarDatosHardware() {
		// TODO Auto-generated method stub
		return null;
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
	public Map<String, Torneo> listarTorneos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Torneo> listarDatosTorneos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reservarPlaza(Plaza plaz) {
		// TODO Auto-generated method stub

	}

}
