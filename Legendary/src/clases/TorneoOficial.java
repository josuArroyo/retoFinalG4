package clases;

public class TorneoOficial extends Torneo{

	/**
	 * Esta clase se utiliza para trabajar con datos de torneo Oficial
	 */
	//Atributos
	
	private String premio;

	
	
	//Constructor
	public TorneoOficial() {
		super();
	}
	
	/**
	 * 
	 * @param premio
	 */
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
