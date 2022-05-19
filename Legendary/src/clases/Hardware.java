package clases;

/**
 * 
 * @author 1dam
 *
 * Esta clase se us para trabajar con los datos de Hardware
 */
	public class Hardware {

	
	//Atributo
	private int idHW;
	private String nombreHW;
	private float precioHW;
	private String marcaHW;
	private String tipoHW;
	private float precioCosteHW;
	private int stockHW;
	
	//Constructor
	
	public Hardware() {
		super();
	}
	
	/**
	 * 
	 * @param idHW
	 * @param nombreHW
	 * @param precioHW
	 * @param marcaHW
	 * @param tipoHW
	 * @param precioCosteHW
	 * @param stockHW
	 */
	public Hardware(int idHW, String nombreHW, float precioHW, String marcaHW, String tipoHW, float precioCosteHW,
			int stockHW) {
		super();
		this.idHW = idHW;
		this.nombreHW = nombreHW;
		this.precioHW = precioHW;
		this.marcaHW = marcaHW;
		this.tipoHW = tipoHW;
		this.precioCosteHW = precioCosteHW;
		this.stockHW = stockHW;
	}
	
	
	//Setters && Getters
	
	public int getIdHW() {
		return idHW;
	}
	
	public void setIdHW(int idHW) {
		this.idHW = idHW;
	}
	public String getNombreHW() {
		return nombreHW;
	}
	public void setNombreHW(String nombreHW) {
		this.nombreHW = nombreHW;
	}
	public float getPrecioHW() {
		return precioHW;
	}
	public void setPrecioHW(float precioHW) {
		this.precioHW = precioHW;
	}
	public String getMarcaHW() {
		return marcaHW;
	}
	public void setMarcaHW(String marcaHW) {
		this.marcaHW = marcaHW;
	}
	public String getTipoHW() {
		return tipoHW;
	}
	public void setTipoHW(String tipoHW) {
		this.tipoHW = tipoHW;
	}
	public float getPrecioCosteHW() {
		return precioCosteHW;
	}
	public void setPrecioCosteHW(float precioCosteHW) {
		this.precioCosteHW = precioCosteHW;
	}
	public int getStockHW() {
		return stockHW;
	}
	public void setStockHW(int stockHW) {
		this.stockHW = stockHW;
	}
	
	
	
}
