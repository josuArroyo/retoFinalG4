package clases;

import java.time.LocalDate;

public class Usuario {
	
	//Atributos
	
	private String dni;
	private String nombre;
	private LocalDate fechaNac;
	private boolean esAdmin;
	private int telefono;
	private String sexo;
	
	
	//Constructor
	public Usuario() {
		super();
	}
	
	public Usuario(String dni, String nombre, LocalDate fechaNac, boolean esAdmin, int telefono, String sexo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.esAdmin = esAdmin;
		this.telefono = telefono;
		this.sexo = sexo;
	}

	
	
	//Setters && Getters
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	
}