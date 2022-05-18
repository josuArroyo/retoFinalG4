package clases;

import java.sql.Clob;
import java.sql.Date;
import java.time.LocalDate;

public class Reserva {

	/**
	 * Esta clase se utiliza para trabajar con datos de reserva
	 */
	//Atributos
		private int id_Plaza;
		private String dni;
		private Date fecha_ini;
		private Date fecha_fin;
		private float coste;
		
		
		//Constructor
		/**
		 * 
		 * @param id_Plaza
		 * @param dni
		 * @param fecha_ini
		 * @param fecha_fin
		 * @param coste
		 */
		public Reserva(int id_Plaza, String dni, Date fecha_ini, Date fecha_fin, float coste) {
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

		public Date getFecha_ini() {
			return fecha_ini;
		}

		public void setFecha_ini(Date fecha_ini) {
			this.fecha_ini = fecha_ini;
		}

		public Date getFecha_fin() {
			return fecha_fin;
		}

		public void setFecha_fin(Date fecha_fin) {
			this.fecha_fin = fecha_fin;
		}

		public float getCoste() {
			return coste;
		}

		public void setCoste(float coste) {
			this.coste = coste;
		}
	}
