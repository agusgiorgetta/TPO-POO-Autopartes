package negocio;

public class Administrador {
	
	private int codigo;
	private String nombre;
	private int contrasenia;
	private String correo;
	private String perfil;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(int contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	//no me gusta esta dinamica de iniciar sesion que paja codear esto
	//se lo dejo... jsjsjsj
		//igual para mi estas estan mas demas, para mi toca poner las funcionalidades 
		//que estan en las otras clases como venta y eso xq es el admin el q hace esas
		//cosas, no la venta
	public void IniciarSesion(String correo, String contra) {
		
	}
	
	public boolean CerrarSesion() {
		
		return false;
	}
	
	//pongo los metodos de catalogo(q deberiamos hacerlo arraylist diria)
	public void ListarCatalogo() {
		
	}

	public void ModificarCatalogo(Autoparte a) {
		
	}
	
	public void CargarAutoparte(Autoparte a) {
		
	}
	
	public void EliminarDelCatalogo(Autoparte a) {
		
	}

	public void ModificarStock(Autoparte a, int nuevoStock) {
		//tenemos q decidir donde meter esta q esta REPETIDA en venta
	}

	public void CargarPedido(Pedido p) { //le pondria mejor este parametroy pediria los datos en el ejecutable
		
	}
	
	public void CancelarPedido(int codigo) { //mejor pedir el cod y buscarlo en los pedidos
		
	}

	public void RegistrarVentaConPedido(Pedido p) {
		
	}
	
	public void RegistrarVentaSinPedido(Autoparte a) {
		
	}
	
	public void DisponibilidadStock(Autoparte a) {
		
	}

	public void RegistrarMedioDePago(String medio) {
		
	}
	
	public void GenerarFactura(Venta v) {

	}
}
