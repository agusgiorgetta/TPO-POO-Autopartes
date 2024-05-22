package negocio;

public class Pedido {
	
	private int codigo;
	private String fecha;
	private double montoTotal;
	private String usuario;
	private int cantidad;
	private String detalles;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	//lo mismo, creo q las pondria en admin
	public void CargarPedido(Pedido p) { //le pondria mejor este parametroy pediria los datos en el ejecutable
		
	}
	
	public void CancelarPedido(int codigo) { //mejor pedir el cod y buscarlo en los pedidos
		
	}
	
	public void ReservarProductos(Autoparte a) { //esta creo q seria una de los metodos q agrega pedido si es extend de venta
		
	}
}
