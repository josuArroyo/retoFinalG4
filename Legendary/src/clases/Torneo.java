package clases;

import java.time.LocalDate;

	/**
	 * 
	 * @author 1dam
	 *
	 * Esta clase se utiliza para trabajar con datos de Torneo
	 */

public class Torneo {

	
	//Atributos
	
	private int idTorneo;
	private String nombre;
	private int aforo;
	private String juego;
	private LocalDate fecha;
	private String dir;
	private String tipo;
	
	
	
	
	//Contructor
	
	public Torneo() {
		super();
	}
	
	/**
	 * 
	 * @param idTorneo
	 * @param nombre
	 * @param aforo
	 * @param juego
	 * @param fecha
	 * @param dir
	 * @param tipo
	 */
	public Torneo(int idTorneo, String nombre, int aforo, String juego, LocalDate fecha, String dir,String tipo) {
		super();
		this.idTorneo = idTorneo;
		this.nombre = nombre;
		this.aforo = aforo;
		this.juego = juego;
		this.fecha = fecha;
		this.dir = dir;
		this.tipo = tipo;
	}

	
	
	//Setters && Getters
	
	public int getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public String getJuego() {
		return juego;
	}

	public void setJuego(String juego) {
		this.juego = juego;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
