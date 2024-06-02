package empresa;
import negocio.*;
import java.util.Scanner;

public class Ejecutable {
	
	private static Administrador admin = new Administrador();
	
	static Scanner leer = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean verificador = true;
		boolean validar = true;
		int contador = 3;
			
		System.out.println("-------------- BIENVENIDO A TUTTA LA MACCHINA --------------");
		System.out.println("Ingrese por favor sus datos");
		
		while(validar) {
			
			System.out.print("USUARIO: ");
			String usuario = leer.next();
			System.out.print("CONTRASEÑA: ");
			String contrasena = leer.next();
			
			if(admin.IniciarSesion(usuario, contrasena) == true && contador != 0) {
				
				while(verificador) {
					
					System.out.println("\n--------------------------- MENÚ ---------------------------");
					System.out.print("[1] Agregar autoparte \n[2] Mostrar autopartes \n[3] Eliminar autoparte del catálogo \n[4] Modificar stock autoparte \n[5] Modificar catálogo \n[6] Disponibilidad stock de una autoparte \n[7] Cargar pedido \n[8] Cancelar pedido \n[9] Registrar venta con pedido \n[10] Registrar venta sin pedido \n[11] Generar factura \n[0] Cerrar Sesión \n\nOPCION: ");
					
					int opcion = leer.nextInt();
		
					if(opcion == 1) {
						agregarAutoparte();
					}else if(opcion == 2) {
						mostrarCatalogo();
					}else if(opcion == 3) {
						eliminarAutoparte();
					}else if(opcion == 4) {
						modificarStock();
					}else if(opcion == 5) {
						modificarCatalogo();
					}else if(opcion == 6) {
						stockDisponible();
					}else if(opcion == 7) {
						cargarPedido();
					}else if(opcion == 8) {
						cancelarPedido();
					}else if(opcion == 9) {
						registrarVentaConPedido();
					}else if(opcion == 10) {
						registrarVentaSinPedido();
					}else if(opcion == 11) {
						//generarFactura();
					}else if(opcion == 0) {
						if(admin.CerrarSesion() == true) {
							System.out.println("Se ha finalizado la sesión exitosamente!");
							System.out.println("-------------------- TUTTA LA MACCHINA ----------------------");
							validar = false;
							leer.close();
							break;
						}
					}
				}
			}else {
				contador = contador - 1;
				System.out.println("ERROR --> Datos erroneos, vuelva a intentar! Intentos restantes: " + contador + "\n");
				if(contador == 0) {
					break;
				}
			}
		}
	}
	
	public static void agregarAutoparte() {
		
		Autoparte autoparte = new Autoparte();
		
		System.out.print("Introduzca el código: ");
		int codigo = leer.nextInt();
		autoparte.setCodigo(codigo);

		
		System.out.print("Introduzca la denominación: ");
		String denominacion = leer.next();
		autoparte.setDenominacion(denominacion);
		
		leer.nextLine();
		
		System.out.print("Introduzca la descripción: ");
		String descripcion = leer.nextLine();
		autoparte.setDescripcion(descripcion);
		
		System.out.print("Introduzca la categoria: ");
		String categoria = leer.next();
		autoparte.setCategoria(categoria);
		
		leer.nextLine();
		
		System.out.print("Introduzca la marca: ");
		String marca = leer.next();
		autoparte.setMarca(marca);
		
		leer.nextLine();
		
		System.out.print("Introduzca el modelo: ");
		String modelo = leer.nextLine();
		autoparte.setModelo(modelo);
		
		System.out.print("Introduzca el precio: ");
		double precio = leer.nextDouble();
		autoparte.setPrecio(precio);
		
		System.out.print("Introduzca el stock: ");
		int stock = leer.nextInt();
		autoparte.setCantStock(stock);
		
		System.out.print("Introduzca el stock minimo: ");
		int stockMinimo = leer.nextInt();
		autoparte.setStockMinimo(stockMinimo);
		
		System.out.print("Introduzca el enlace: ");
		String enlace = leer.next();
		autoparte.setEnlace(enlace);
		
		admin.CargarAutoparte(autoparte);
		System.out.println("Autoparte cargada exitosamente!");
	}
	
	public static void mostrarCatalogo() {
		admin.ListarCatalogo();
	}
	
	public static void eliminarAutoparte() {
				
		System.out.print("Introduzca el código del autoparte: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			admin.EliminarDelCatalogo(codigo);
		}
	}
	
	public static void modificarStock() {
			
		System.out.print("Introduzca el código del autoparte para modificar su stock: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			System.out.print("OPCIONES: \n[1] Sumar unidades al stock actual \n[2] Reemplazar el valor del stock \nOPCION: ");
			int opcion = leer.nextInt();
			System.out.print("Introduzca el nuevo stock: ");
			int stock = leer.nextInt();
			admin.ModificarStock(codigo, stock, opcion);
		}
		System.out.println("Stock modificado exitosamente!");
	}
	
	public static void modificarCatalogo() {
		
		System.out.print("Introduzca el código del autoparte que desea modificar: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			System.out.println("Que desea modificar del autoparte?");
			System.out.print("OPCIONES: \n[1] Código \n[2] Denominación \n[3] Descripción \n[4] Categoría \n[5] Marca \n[6] Modelo \n[7] Precio \n[8] Stock Mínimo \n[9] Enlace \n[10] Stock \n[0] Salir \nOPCION: ");
			int opcion = leer.nextInt();
			
			admin.ModificarCatalogo(codigo, opcion);
		}	
	}
	
	public static void stockDisponible() {
				
		System.out.print("Introduzca el código del autoparte para averiguar su stock disponible: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			admin.DisponibilidadStock(codigo);
		}
	}
	
	public static void cargarPedido() {
		
		Pedido pedido = new Pedido();
		boolean verificar = true;
		int stock = 0;

		System.out.print("Introduzca el número del pedido: ");
		int numero = leer.nextInt();
		pedido.setCodigo(numero);
		
		System.out.print("Introduzca la fecha del pedido: ");
		String fecha = leer.next();
		pedido.setFecha(fecha);
		
		leer.nextLine();
		
		System.out.print("Introduzca el nombre del usuario: ");
		String usuario = leer.nextLine();
		pedido.setUsuario(usuario);
		
		
		System.out.print("Introduzca el ID de la autoparte: ");
		int id = leer.nextInt();
		pedido.setId(id); 
		
		stock = admin.DisponibilidadStock(id);
		if (stock == 0) {
			return;
		}
		//en caso de ingresar una cantidad superior al stock permite pedir nuevamente el dato sin la 
		//necesidad de realizar nuevamente el proceso
		while(verificar) {
			System.out.print("Introduzca la cantidad necesitada: ");
			int cantidad = leer.nextInt();
				if(cantidad < stock) {
					pedido.setCantidad(cantidad);
					verificar = false;
					
					// reduce el stock, reserva
					admin.ModificarStock(id, cantidad, 0);
					//envia a cargar pedido
					admin.CargarPedido(pedido);
					
					System.out.println("Pedido cargado exitosamente!");
					
				}else {
					System.out.println("Solo hay " + stock + " unidades en stock, intente nuevamente");
				}
			}
		}
	
	public static void cancelarPedido() {
		
		System.out.print("Introduzca el número de pedido a cancelar: ");
		int numero = leer.nextInt();
		admin.CancelarPedido(numero);
		
	}	
	
	public static void registrarVentaConPedido() {
		Venta venta = new Venta();
		Cliente cliente = new Cliente();
		
		System.out.print("Introduzca el código de la venta: ");
		int numero = leer.nextInt();
		venta.setCodigo(numero);
		
		System.out.print("Introduzca el número del pedido a retirar: ");
		int numPedido = leer.nextInt();

		System.out.print("Introduzca el ID del cliente: ");
		cliente.setCodigo(leer.nextInt());
		
		leer.nextLine();
		System.out.print("Introduzca el nombre del cliente: ");
		cliente.setNombre(leer.nextLine());
		
		System.out.print("Introduzca la dirección del cliente: ");
		cliente.setDireccion(leer.nextLine());
		
		System.out.print("Introduzca la localidad del cliente: ");
		cliente.setLocalidad(leer.nextLine());
		
		System.out.print("Introduzca la provincia del cliente: ");
		cliente.setProvincia(leer.nextLine());
		
		System.out.print("Introduzca el correo del cliente (ej. nombre@dom.ext): ");
		cliente.setCorreo(leer.nextLine());
		
		System.out.print("Introduzca el teléfono del cliente (sin espacios ni guiones): ");
		cliente.setTelefono(leer.nextInt());
		
		venta.setCliente(cliente);
		
		admin.RegistrarVentaConPedido(numPedido, venta);
	}
	
	public static void registrarVentaSinPedido() {
		Venta venta = new Venta();
		Cliente cliente = new Cliente();
		Pedido detalleVenta = new Pedido();
		
		System.out.print("Introduzca el código de la venta: ");
		int numero = leer.nextInt();
		venta.setCodigo(numero);
		
		//datos del cliente
		System.out.print("Introduzca el ID del cliente: ");
		cliente.setCodigo(leer.nextInt());
		
		leer.nextLine();
		System.out.print("Introduzca el nombre del cliente: ");
		cliente.setNombre(leer.nextLine());
		
		System.out.print("Introduzca la dirección del cliente: ");
		cliente.setDireccion(leer.nextLine());
		
		System.out.print("Introduzca la localidad del cliente: ");
		cliente.setLocalidad(leer.nextLine());
		
		System.out.print("Introduzca la provincia del cliente: ");
		cliente.setProvincia(leer.nextLine());
		
		System.out.print("Introduzca el correo del cliente (ej. nombre@dom.ext): ");
		cliente.setCorreo(leer.nextLine());
		
		System.out.print("Introduzca el teléfono del cliente (sin espacios ni guiones): ");
		cliente.setTelefono(leer.nextInt());
		
		venta.setCliente(cliente);
		
		
		//datos del producto vendido
		boolean verificar = true;

		System.out.print("Introduzca la fecha de la venta: ");
		String fecha = leer.next();
		detalleVenta.setFecha(fecha);

		System.out.print("Introduzca el ID de la autoparte deseada: ");
		int id = leer.nextInt();
		detalleVenta.setId(id); 
		
		int stock = admin.DisponibilidadStock(id);
		if (stock == 0) {
			System.out.println("Stock 0");
			return;
		}
		while(verificar) {
			System.out.print("Introduzca la cantidad necesitada: ");
			int cantidad = leer.nextInt();
				if(cantidad < stock) {
					detalleVenta.setCantidad(cantidad);
					verificar = false;					
					// reduce el stock, reserva
					admin.ModificarStock(id, cantidad, 0);					
				}else {
					System.out.println("Solo hay " + stock + " unidades en stock, intente nuevamente");
				}
			}
		
		admin.RegistrarVentaSinPedido(detalleVenta, venta);
	}
}

