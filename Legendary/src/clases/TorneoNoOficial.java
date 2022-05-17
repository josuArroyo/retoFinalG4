package clases;

public class TorneoNoOficial extends Torneo{

	//Atributos
	private String regla;

	
	
	//Constructor
	
	public TorneoNoOficial() {
		super();
	}
	
	public TorneoNoOficial(String regla) {
		super();
		this.regla = regla;
	}

	
	
	//Setters && Getters
	
	public String getRegla() {
		return regla;
	}

	public void setRegla(String regla) {
		this.regla = regla;
	}
	
}
