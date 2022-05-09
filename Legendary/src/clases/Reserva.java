package clases;

import java.time.LocalDate;

public class Reserva {

	//Atributos
		private int id_Plaza;
		private String dni;
		private LocalDate fecha_ini;
		private LocalDate fecha_fin;
		private float coste;
		
		
		//Constructor
		public Reserva(int id_Plaza, String dni, LocalDate fecha_ini, LocalDate fecha_fin, float coste) {
			super();
			this.id_Plaza = id_Plaza;
			this.dni = dni;
			this.fecha_ini = fecha_ini;
			this.fecha_fin = fecha_fin;
			this.coste = coste;
		}		
		
		public Reserva() {
			super();
		}


		//Setters && Getters
		public int getId_Plaza() {
			return id_Plaza;
		}

		public void setId_Plaza(int id_Plaza) {
			this.id_Plaza = id_Plaza;
		}

		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
		}

		public LocalDate getFecha_ini() {
			return fecha_ini;
		}

		public void setFecha_ini(LocalDate fecha_ini) {
			this.fecha_ini = fecha_ini;
		}

		public LocalDate getFecha_fin() {
			return fecha_fin;
		}

		public void setFecha_fin(LocalDate fecha_fin) {
			this.fecha_fin = fecha_fin;
		}

		public float getCoste() {
			return coste;
		}

		public void setCoste(float coste) {
			this.coste = coste;
		}
	}
