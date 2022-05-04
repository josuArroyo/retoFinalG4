package clases;

public class TorneoNoOficial extends Torneo{

	//Atributos
	private String reglas;

	
	
	//Constructor
	
	public TorneoNoOficial() {
		super();
	}
	
	public TorneoNoOficial(String reglas) {
		super();
		this.reglas = reglas;
	}

	
	
	//Setters && Getters
	
	public String getReglas() {
		return reglas;
	}

	public void setReglas(String reglas) {
		this.reglas = reglas;
	}
	
}
