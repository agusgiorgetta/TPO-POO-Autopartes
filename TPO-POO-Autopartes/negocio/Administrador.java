package negocio;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrador {
	
	private int codigo;
	private String nombre;
	private int contrasenia;
	private String correo;
	private String perfil;
	
	private ArrayList<Autoparte> catalogo;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Venta> cantVentas;
	
	public Administrador() {
		catalogo = new ArrayList<Autoparte>(); //lista que contiene todas las autopartes
		pedidos = new ArrayList<Pedido>(); //lista que contiene todos los pedidos
		cantVentas = new ArrayList<Venta>(); //lista que contiene todas las ventas --> guarda numero factura
	}
	
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

	//Valida los datos introducidos por el Administrador
	public boolean IniciarSesion(String correo, String contra) {
		if(correo.equals("admin") && contra.equals("admin")) {
			return true;
		}else {
			return false;
		}
	}
	//Finaliza la sesión actual
	public boolean CerrarSesion() {
		return true;
	}
	//Muestra por pantalla el catalogo con todas las autopartes
	public void ListarCatalogo() {
		if(catalogoVacio() == false) {
			System.out.println("-------------- CATÁLOGO --------------");
			for(int i = 0; i < catalogo.size(); i++) {
				System.out.println("AUTOPARTE: " + i);
				System.out.println("Código: "+ catalogo.get(i).getCodigo());
				System.out.println("Denominación: " + catalogo.get(i).getDenominacion());
				System.out.println("Descripción: " + catalogo.get(i).getDescripcion());
				System.out.println("Categoría: " + catalogo.get(i).getCategoria());
				System.out.println("Marca: " + catalogo.get(i).getMarca());
				System.out.println("Modelo: " + catalogo.get(i).getModelo());
				System.out.println("Precio: " + catalogo.get(i).getPrecio());
				System.out.println("Stock: " + catalogo.get(i).getCantStock());
				System.out.println("Stock Mínimo: " + catalogo.get(i).getStockMinimo());
				System.out.println("Enlace: " + catalogo.get(i).getEnlace() + "\n");
			}
		}else {
			return;
		}
	}
	
	//Utilicé el scanner acá también ya que no puede recibir otro dato debido que hay opciones
	//que son Strings y otras Int, por ende, recibe la opción elegida por el usuario y se recorre
	//el catalogo y se modifica la autoparte de acuerdo a lo seleccionado
	public void ModificarCatalogo(int codigo, int opcion) {
		
		Scanner leer = new Scanner(System.in);
		
		for(int i = 0; i < catalogo.size(); i++) {
			if(catalogo.get(i).getCodigo() == codigo) {
				if(opcion == 1) {
					System.out.print("Introduzca el nuevo código: ");
					int codigoNuevo = leer.nextInt();
					catalogo.get(i).setCodigo(codigoNuevo);
					System.out.println("Se ha modificado exitosamente el código del autoparte!");
				}else if(opcion == 2) {
					System.out.print("Introduzca la nueva denominación: ");
					String denominacion = leer.next();
					catalogo.get(i).setDenominacion(denominacion);
					System.out.println("Se ha modificado exitosamente la denominacion del autoparte!");
				}else if(opcion == 3) {
					System.out.print("Introduzca la nueva descripción: ");
					String descripcion = leer.next();
					catalogo.get(i).setDescripcion(descripcion);
					System.out.println("Se ha modificado exitosamente la descripción del autoparte!");
				}else if(opcion == 4) {
					System.out.print("Introduzca la nueva categoría: ");
					String categoria = leer.next();
					catalogo.get(i).setCategoria(categoria);
					System.out.println("Se ha modificado exitosamente la categoría del autoparte!");
				}else if(opcion == 5) {
					System.out.print("Introduzca la marca: ");
					String marca = leer.next();
					catalogo.get(i).setMarca(marca);
					System.out.println("Se ha modificado exitosamente la marca del autoparte!");
				}else if(opcion == 6) {
					System.out.print("Introduzca el modelo: ");
					String modelo = leer.next();
					catalogo.get(i).setModelo(modelo);
					System.out.println("Se ha modificado exitosamente el modelo del autoparte!");
				}else if(opcion == 7) {
					System.out.print("Introduzca el nuevo precio: ");
					double precio = leer.nextDouble();
					catalogo.get(i).setPrecio(precio);
					System.out.println("Se ha modificado exitosamente el precio del autoparte!");
				}else if(opcion == 8) {
					System.out.print("Introduzca el nuevo stock mínimo: ");
					int stockMinimo = leer.nextInt();
					catalogo.get(i).setStockMinimo(stockMinimo);
					System.out.println("Se ha modificado exitosamente el stock mínimo del autoparte!");
				}else if(opcion == 9) {
					System.out.println("Introduzca el nuevo enlace: ");
					String enlace = leer.next();
					catalogo.get(i).setEnlace(enlace);
					System.out.println("Se ha modificado exitosamente el enlace del autoparte!");
				}else if(opcion == 10) {
					System.out.println("Introduzca el nuevo stock: ");
					int stock = leer.nextInt();
					catalogo.get(i).setCantStock(stock);
					System.out.println("Se ha modificado exitosamente el stock del autoparte!");
				}else {
					return;
				}
			}
		}
	}
	
	//carga la autoparte al catalogo
	public void CargarAutoparte(Autoparte a) {
		catalogo.add(a);
	}
	
	//elimina la autoparte mediante el código de la misma
	public void EliminarDelCatalogo(int codigo) {
		if(catalogoVacio() == false) {
			for(int i = 0; i < catalogo.size(); i++) {
				if(catalogo.get(i).getCodigo() == codigo) {
					catalogo.remove(i);
				}
			}
			System.out.println("Autoparte eliminada exitosamente!");
		}else {
			return;
		}
	}
	
	//modifica el stock de una autoparte mediante su codigo y se lo guarda en el catalogo
	public void ModificarStock(int codigo, int nuevoStock, int opcion) {
		if(catalogoVacio() == false) {
			if(existeAutoparte(codigo) == true) {
				for(int i = 0; i < catalogo.size(); i++) {
					if(catalogo.get(i).getCodigo() == codigo) {
						
						int stockAntiguo = catalogo.get(i).getCantStock();
						int stockMinimo = catalogo.get(i).getStockMinimo();
						int stockFinal = 0;
						
						if(opcion == 1){ //sumar el stock
							stockFinal = stockAntiguo + nuevoStock;
							catalogo.get(i).setCantStock(stockFinal);
						}else if(opcion == 2){ //modificar stock	
							stockFinal = nuevoStock;
							catalogo.get(i).setCantStock(stockFinal);
						}else{ //restar, en caso de reservas
							stockFinal = stockAntiguo - nuevoStock;
							catalogo.get(i).setCantStock(stockFinal);
						}
						StockMinimo(stockFinal,stockMinimo);
					}
				}	
			}else {
				return;
			}
		}else {
			return;
		}
	}
	
	// Carga un pedido al contenedor de pedidos
	public void CargarPedido(Pedido p) { 
		
		//buscamos la autoparte para definir el 'detalle' del pedido (denominacion + precio*cantidad)
		if(catalogoVacio() == false) {
			for(int i = 0; i < catalogo.size(); i++) {
				if(catalogo.get(i).getCodigo() == p.getCodigo()) {
					Autoparte a = catalogo.get(i);
					p.setDetalles("Artículo: " + a.getDenominacion());	//guarda en detalle el nombre de la autoparte
					p.setMontoTotal(a.getPrecio()*p.getCantidad());		//guarda el monto total -> precio * cantidad pedida
				}
			}
		}
		pedidos.add(p);
	}
	
	// Verifica que exista el pedido, lo cancela en base al numero de pedido y devuelve el stock
	public void CancelarPedido(int codigo) { 
		if(existePedido(codigo) == true) {
			for(int i = 0; i < pedidos.size(); i++) {
				if(pedidos.get(i).getCodigo() == codigo) {
					pedidos.remove(i);
					System.out.println("Pedido cancelado exitosamente!");
					return;
				}
			}
		}
	}
	
	// Realiza una venta de un autoparte CON pedido para un cliente
	public void RegistrarVentaConPedido(int numPedido, Venta v) {
		Pedido p = null;
		if(existePedido(numPedido) == true) {
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getCodigo() == numPedido) {
					p = pedidos.get(i);
				}
			}
			//se añade el detalle del producto a la venta
			v.setDetalleVenta(p);
			
			//se añaden los datos faltantes a la venta
			v.setProvincia("Buenos Aires"); //autodefino xq son de la sucursal
			v.setLocalidad("Monserrat");	//autodefino xq son de la sucursal
			v.setTelefono(1122334455);		//autodefino xq son de la sucursal
			
			//se añade la venta al registro
			cantVentas.add(v);
			
			System.out.println("Operación exitosa!");
		}
	}
	
	// Realiza una venta de un autoparte para un cliente SIN un pedido previo
	public void RegistrarVentaSinPedido(Pedido detalleVenta, Venta v) {
		//buscamos la autoparte
		if(catalogoVacio() == false) {
			for(int i = 0; i < catalogo.size(); i++) {
				if(catalogo.get(i).getCodigo() == detalleVenta.getCodigo()) {
					Autoparte a = catalogo.get(i);
					detalleVenta.setDetalles("Artículo: " + a.getDenominacion());				//guarda en detalle el nombre de la autoparte
					detalleVenta.setMontoTotal(a.getPrecio()*detalleVenta.getCantidad());		//guarda el monto total -> precio * cantidad pedida
				}
			}
		}
		
		//se añaden los datos faltantes a la venta
		v.setDetalleVenta(detalleVenta);
		v.setProvincia("Buenos Aires"); //autodefino xq son de la sucursal
		v.setLocalidad("Monserrat");	//autodefino xq son de la sucursal
		v.setTelefono(1122334455);		//autodefino xq son de la sucursal
				
		//se añade la venta al registro
		cantVentas.add(v);
				
		System.out.println("Operación exitosa!");
	}
	
	// Verifica la disponibilidad y la cantidad de stock de un autoparte y devuelve el stock disponible
	public int DisponibilidadStock(int codigo) {
		int stock;
		if(catalogoVacio() == false) {
			for(int i = 0; i < catalogo.size(); i++) {
				if(catalogo.get(i).getCodigo() == codigo) {
					stock = catalogo.get(i).getCantStock();
					if(stock == 1) {
						System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidad");
						return stock;
					}else if(stock > 1) {
						System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidades");
						return stock;
					}else {
						System.out.println("La autoparte " + codigo + " no dispone de stock disponible");
					}
				}else {
					existeAutoparte(codigo);
				}	
			}
		}
		return 0;
	}
	
	// Verifica la opcion y los datos introducidos por el administrador
	public void RegistrarMedioDePago(int medio) {
		
	}
	
	// Genera la factura de la venta de un autoparte relacionada con un cliente
	public void GenerarFactura(Venta v) {

	}
	
	public void StockMinimo(int stock, int minimo) {
		if (stock < minimo) {
			System.out.println("\nAlerta! El stock de esta autoparte se encuentra por debajo el mínimo --> "+ minimo +". Contacte con proveedores.\n");
			return;
		}
		else {
			return;
		}
	}
	
	// Verifica si existe "x" autoparte dentro del catálogo. Si no existe devuelve false, caso contrario true
	public boolean existeAutoparte(int codigo) {
		if(catalogoVacio() == false) {
			for(int i = 0; i < catalogo.size(); i++) {
				if(catalogo.get(i).getCodigo() == codigo) {
					return true;
				}
			}
			System.out.println("La autoparte con código " + codigo + " no existe, intente nuevamente");
			return false;
		}else {
			return false;
		}
	}
	
	//Verifica si el catálogo no dispone de autopartes y devuelve true en caso de serlo. Caso contrario devuelve false
	public boolean catalogoVacio() {
		if(catalogo.isEmpty()) {
			System.out.println("El catálogo está vacío, se necesita al menos 1 autoparte");
			return true;
		}return false;
	}
	
	//Verifica si existe un pedido mediante el ingreso del número del mismo.
	public boolean existePedido(int numero) {
		for(int i = 0; i < pedidos.size(); i++) {
			if(pedidos.get(i).getCodigo() == codigo) {
				return true;
			}
		}System.out.println("No existe un pedido con ID: " + numero + ", intente nuevamente!");
		return false;
	}
}
