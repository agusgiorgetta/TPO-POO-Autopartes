package empresa;
import negocio.*;
import java.util.Scanner;

public class Ejecutable {
	
	private static Administrador admin = new Administrador();
	private static Autoparte autoparte = new Autoparte();
	private static Pedido pedido = new Pedido();
	private static Cliente cliente = new Cliente();

	public static void main(String[] args) {
		
		boolean verificador = true;
		boolean validar = true;
		int contador = 3;
		
		Scanner leer = new Scanner(System.in);
		
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
						//cargarPedido();
					}else if(opcion == 8) {
						//cancelarPedido();
					}else if(opcion == 9) {
						//registrarVentaConPedido();
					}else if(opcion == 10) {
						//registrarVentaSinPedido();
					}else if(opcion == 11) {
						//generarFactura();
					}else if(opcion == 0) {
						if(admin.CerrarSesion() == true) {
							System.out.print("Se ha finalizado la sesión exitosamente!");
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
		Scanner leer = new Scanner(System.in);
			
		System.out.print("Introduzca el código: ");
		int codigo = leer.nextInt();
		autoparte.setCodigo(codigo);
		
		System.out.print("Introduzca la denominación: ");
		String denominacion = leer.next();
		autoparte.setDenominacion(denominacion);
		
		leer.nextLine();
		
		System.out.println("Introduzca la descripción: ");
		String descripcion = leer.nextLine();
		autoparte.setDescripcion(descripcion);
		
		System.out.print("Introduzca la categoria: ");
		String categoria = leer.next();
		autoparte.setCategoria(categoria);
		
		System.out.print("Introduzca la marca: ");
		String marca = leer.next();
		autoparte.setMarca(marca);
		
		System.out.print("Introduzca el modelo: ");
		String modelo = leer.next();
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
		
		Scanner leer = new Scanner(System.in);
		
		System.out.print("Introduzca el código del autoparte: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			admin.EliminarDelCatalogo(codigo);
		}
	}
	
	public static void modificarStock() {
		
		Scanner leer = new Scanner(System.in);
		
		System.out.print("Introduzca el código del autoparte para modificar su stock: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			System.out.print("Introduzca el nuevo stock: ");
			int stock = leer.nextInt();
			
			admin.ModificarStock(codigo, stock);
		}
	}
	
	public static void modificarCatalogo() {
		
		Scanner leer = new Scanner(System.in);
		
		System.out.print("Introduzca el código del autoparte que desea modificar: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			System.out.println("Que desea modificar del autoparte?");
			System.out.print("OPCIONES: \n[1] Código \n[2] Denominación \n[3] Descripción \n[4] Categoría \n[5] Marca \n[6] Modelo \n[7] Precio \n[8] Stock Mínimo \n[9] Enlace \n[0] Salir \nOPCION: ");
			int opcion = leer.nextInt();
			
			admin.ModificarCatalogo(codigo, opcion);
		}	
	}
	
	public static void stockDisponible() {
		
		Scanner leer = new Scanner(System.in);
		
		System.out.print("Introduzca el código del autoparte para averiguar su stock disponible: ");
		int codigo = leer.nextInt();
		
		if(admin.existeAutoparte(codigo) == true) {
			admin.DisponibilidadStock(codigo);
		}
	}
}
