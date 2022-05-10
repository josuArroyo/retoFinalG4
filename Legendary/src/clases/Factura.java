package clases;

import java.time.LocalDate;

public class Factura {

	//Atributos
	private int idFactura;
	private LocalDate fechaFactura;
	private float precio;
	private int Cantidad;
	private String nombre;
	private String Dni;
	
	//Contructor
	
	public Factura() {
		super();
		
	}
	
	public Factura(int idFactura, LocalDate fechaFactura, float precio, int cantidad,String nombre,String Dni) {
		super();
		this.idFactura = idFactura;
		this.fechaFactura = fechaFactura;
		this.precio = precio;
		this.nombre = nombre;
		this.Dni= Dni;
		Cantidad = cantidad;
	}

	
	
	//Setters && Getters
	
	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public LocalDate getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(LocalDate fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}
	
	
}
