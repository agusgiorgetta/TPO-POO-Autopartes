package negocio;

import java.util.ArrayList;

public class Autoparte {
	
	private int codigo;
	private String denominacion;
	private String descripcion;
	private String categoria; 
	private String modelo; 
	private String marca;
	private int stockMinimo;
	private int cantStock;
	private String enlace;
	private double precio;	
	
	private ArrayList<Autoparte> autoparte;
	
	public Autoparte() {
		autoparte = new ArrayList<Autoparte>();
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public int getCantStock() {
		return cantStock;
	}
	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void StockMinimo(Autoparte a) {//esto se pregunta no? no iria en admin tmb?
		
	}
	// Avisa luego de realizar una venta en caso de que quede poco stock
	public void StockInsuficiente(Autoparte a) { 
		//este puede ser q se invoque aca cuando se modifica elstock y queda en nada
		//pero no se, capaz se puede poner en un if y obviarlo de java, convengamos q 
		//mucho de lo de los cuadros es representativo y no necesariamente literal
	}
}
