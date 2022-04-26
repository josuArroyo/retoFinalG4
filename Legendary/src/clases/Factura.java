package clases;

import java.time.LocalDate;

public class Factura {

	//Atributos
	private int idFactura;
	private LocalDate fechaFactura;
	private float precio;
	private int Cantidad;
	
	
	//Contructor
	
	public Factura() {
		super();
		
	}
	
	public Factura(int idFactura, LocalDate fechaFactura, float precio, int cantidad) {
		super();
		this.idFactura = idFactura;
		this.fechaFactura = fechaFactura;
		this.precio = precio;
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
	
	
}
