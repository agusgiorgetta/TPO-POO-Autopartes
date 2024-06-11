package negocio;

public class Venta {
	
	private int codigo;
	private Cliente cliente;
	private String provincia;		//como son datos de la sucursal los predefinimos
	private String localidad;		//como son datos de la sucursal los predefinimos
	private int telefono;			//como son datos de la sucursal los predefinimos
	private Pedido detalleVenta;
	//o bien almacena el pedido reservado o uno que se hace inmediatamente ante una venta
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Pedido getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(Pedido detalleVenta) {
		this.detalleVenta = detalleVenta;
	}	
	
}
