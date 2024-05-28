package negocio;

public abstract class Venta {
	
	private int codigo;
	private String cliente;
	private String provincia;
	private String localidad;
	private int telefono;
	//pedido(? creo q no es una variable de aca, ya esta la clase
	//hay q relacionarlas nomas, mismo capaz pedido puede ser un extend xq es un
	//agregado 'opcional' de ciertos casos
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
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

	public abstract void ReservarProductos() {	
		
	}
}
