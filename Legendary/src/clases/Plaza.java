package clases;

public class Plaza {

	/**
	 * Esta clase se utiliza para trabajar con datos de Plaza
	 */
	
	//Atributos
	private int idPlaza;
	private float PrecioPlaza;
	private String juegosPlaza;
	
	
	//Constructor
	
	public Plaza() {
		super();
	}
	
	public Plaza(int idPlaza, float precioPlaza, String juegosPlaza) {
		super();
		this.idPlaza = idPlaza;
		PrecioPlaza = precioPlaza;
		this.juegosPlaza = juegosPlaza;
	}

	
	
	//Setters && Getters
	
	public int getIdPlaza() {
		return idPlaza;
	}

	public void setIdPlaza(int idPlaza) {
		this.idPlaza = idPlaza;
	}

	public float getPrecioPlaza() {
		return PrecioPlaza;
	}

	public void setPrecioPlaza(float precioPlaza) {
		PrecioPlaza = precioPlaza;
	}

	public String getJuegosPlaza() {
		return juegosPlaza;
	}

	public void setJuegosPlaza(String juegosPlaza) {
		this.juegosPlaza = juegosPlaza;
	}
	
	
	
}
