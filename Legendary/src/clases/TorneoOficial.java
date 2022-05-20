package clases;

	/**
	 * 
	 * @author 1dam
	 *
	 * Esta clase se utiliza para trabajar con datos de torneo Oficial
	 */

public class TorneoOficial extends Torneo{

	
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
