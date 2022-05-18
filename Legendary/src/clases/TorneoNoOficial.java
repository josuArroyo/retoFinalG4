package clases;

public class TorneoNoOficial extends Torneo{

	/**
	 * Esta clase se utiliza para trabajar con datos de Torneo no Oficial
	 */
	
	//Atributos
	private String regla;

	
	
	//Constructor
	
	public TorneoNoOficial() {
		super();
	}
	
	/**
	 * 
	 * @param regla
	 */
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
