package clases;

public class TorneoOficial extends Torneo{

	//Atributos
	
	private String premio;

	
	
	//Constructor
	public TorneoOficial() {
		super();
	}
	
	public TorneoOficial(String premio) {
		super();
		this.premio = premio;
	}

	
	
	//Setters && Getters
	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}
	
	
	
	
}
