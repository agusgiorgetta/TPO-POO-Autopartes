package negocio;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
	private ArrayList<Cliente> clientes;
	
	public Administrador() {
		catalogo = new ArrayList<Autoparte>(); // lista que contiene todas las autopartes
		pedidos = new ArrayList<Pedido>(); // lista que contiene todos los pedidos
		cantVentas = new ArrayList<Venta>(); // lista que contiene todas las ventas --> guarda numero factura
		clientes = new ArrayList<Cliente>(); // lista que contiene a todos los clientes registrados
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

	// Valida los datos introducidos por el Administrador
	public boolean IniciarSesion(String correo, String contra) {
		if (correo.equals("admin") && contra.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	// Finaliza la sesión actual
	public boolean CerrarSesion() {
		return true;
	}

	// Muestra por pantalla el catalogo con todas las autopartes
	public void ListarCatalogo() {
		if (catalogoVacio() == false) {
			System.out.println("------------------------ CATÁLOGO ------------------------");
			for (int i = 0; i < catalogo.size(); i++) {
				System.out.println("AUTOPARTE: " + i);
				System.out.println("\nCódigo: " + catalogo.get(i).getCodigo());
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
		} else {
			return;
		}
	}

	// Permite modificar varios datos de una autoparte
	public void ModificarCatalogo(int codigo, int opcion) {

		Scanner leer = new Scanner(System.in);
		boolean entradaValida = false; // Inicializa la variable entradaValida

		for (int i = 0; i < catalogo.size(); i++) {
			if (catalogo.get(i).getCodigo() == codigo) { // Busca la autoparte que coincida con el ID ingresado
				do {
					try {
						if (opcion == 1) { // Modifica el codigo de la autoparte
							boolean codigoInvalido = true;
							while (codigoInvalido) { // Verifica que el codigo sea valido, si no lo es, corta
								System.out.print("Introduzca el nuevo código: ");
								int codigoNuevo = leer.nextInt();
								if (codigoNuevo >= 0) { // Comprueba que el numero ingresado sea positivo
									entradaValida = true;
									codigoInvalido = false;
									catalogo.get(i).setCodigo(codigoNuevo); // Guarda el nuevo codigo de la autoparte en el catalogo
									System.out.println("Se ha modificado exitosamente el código del autoparte!");										
								} else {
									System.out.println("\nNo se permite que el código sea inferior a 0, introduzca nuevamente\n");
								}
							}
						}
						entradaValida = true;
						if (opcion == 2) { // Modifica la denominacion de la autoparte 
							System.out.print("Introduzca la nueva denominación: ");
							String denominacion = leer.next();
							catalogo.get(i).setDenominacion(denominacion); // Guarda la nueva denominacion de la autoparte en el catalogo
							System.out.println("Se ha modificado exitosamente la denominacion del autoparte!");
						} else if (opcion == 3) { // Modifica la descripcion de la autoparte
							System.out.print("Introduzca la nueva descripción: ");
							String descripcion = leer.next();
							catalogo.get(i).setDescripcion(descripcion); // Guarda la nueva descripcion de la autoparte en el catalogo
							System.out.println("Se ha modificado exitosamente la descripción del autoparte!");
						} else if (opcion == 4) { // Modifica la categoria de la autoparte
							System.out.print("Introduzca la nueva categoría: ");
							String categoria = leer.next();
							catalogo.get(i).setCategoria(categoria); // Guarda la nueva categoria de la autoparte en el catalogo
							System.out.println("Se ha modificado exitosamente la categoría del autoparte!");
						} else if (opcion == 5) { // Modifica la marca de la autoparte
							System.out.print("Introduzca la marca: ");
							String marca = leer.next();
							catalogo.get(i).setMarca(marca); // Guarda la nueva marca de la autoparte en el catalogo
							System.out.println("Se ha modificado exitosamente la marca del autoparte!");
						} else if (opcion == 6) { // Modifica el modelo de la autoparte
							System.out.print("Introduzca el modelo: ");
							String modelo = leer.next();
							catalogo.get(i).setModelo(modelo); // Guarda el nuevo modelo de la autoparte en el catalogo
							System.out.println("Se ha modificado exitosamente el modelo del autoparte!");
						}
						do {
							entradaValida = false;
							try {
								if (opcion == 7) { // Modifica el precio de la autoparte
									boolean precioInvalido = true; // Inicializa la variable precioInvalido
									while (precioInvalido) { // Verifica que el pedido sea valido, si no lo es, corta
										System.out.print("Introduzca el nuevo precio: ");
										double precio = leer.nextDouble();
										if (precio >= 0) { // Comprueba que el precio sea un numero positivo
											catalogo.get(i).setPrecio(precio); // Guarda el nuevo precio de la autoparte en el catalogo
											entradaValida = true;
											precioInvalido = false;
											System.out.println("Se ha modificado exitosamente el precio del autoparte!");
										} else {
											System.out.println("\nNo se permite que el precio sea inferior a 0, introduzca nuevamente\n");
										}
									}
								}
								entradaValida = true;
							} catch (InputMismatchException ex) {
								System.out.println(
										"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el precio\n");
								leer.nextLine();
							}
						} while (!entradaValida);

						do {
							entradaValida = false;
							try {
								if (opcion == 8) { // Modifica el stock minimo de la autoparte
									boolean stockMinimoInvalido = true; // Inicializa la variable stockMinimoInvalido
									while (stockMinimoInvalido) { // Verifica que el stock minimo sea valido, si no lo es, corta
										System.out.print("Introduzca el nuevo stock mínimo: ");
										int stockMinimo = leer.nextInt();
										if (stockMinimo >= 0) { // Comprueba que el stock minimo sea un numero positivo
											catalogo.get(i).setStockMinimo(stockMinimo); // Guarda el nuevo stock minimo de la autoparte en el catalogo
											entradaValida = true;
											stockMinimoInvalido = false;
											System.out.println("Se ha modificado exitosamente el stock mínimo del autoparte!");													
										} else {
											System.out.println("\nNo se permite que el stock mínimo sea inferior a 0, introduzca nuevamente\n");
										}
									}
								}
								entradaValida = true;
							} catch (InputMismatchException ex) {
								System.out.println(
										"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock mínimo\n");
								leer.nextLine();
							}
						} while (!entradaValida);

						if (opcion == 9) { // Modifica el enlace de la autoparte
							System.out.print("Introduzca el nuevo enlace: ");
							String enlace = leer.next();
							catalogo.get(i).setEnlace(enlace); // Guarda el nuevo enlace de la autoparte en el catalogo
							System.out.println("Se ha modificado exitosamente el enlace del autoparte!");
						}

						do {
							entradaValida = false;
							try {
								if (opcion == 10) { // Modifica el stock de la autoparte
									boolean stockInvalido = true; // Inicializa la variable stockInvalido
									while (stockInvalido) {
										System.out.print("Introduzca el nuevo stock: ");
										int stock = leer.nextInt();
										if (stock >= 0) { // Comprueba que el stock sea un numero positivo
											entradaValida = true;
											stockInvalido = false;
											catalogo.get(i).setCantStock(stock); // Guarda el nuevo stock de la autoparte en el catalogo
											System.out.println("Se ha modificado exitosamente el stock del autoparte!");											
										} else {
											System.out.println("\nNo se permite que el stock sea inferior a 0, introduzca nuevamente\n");
										}
									}
								} else {
									return;
								}
							} catch (InputMismatchException ex) {
								System.out.println(
										"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock\n");
								leer.nextLine();
							}
						} while (!entradaValida);
					} catch (InputMismatchException ex) {
						System.out.println(
								"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el codigo\n");
						leer.nextLine();
					}
				} while (!entradaValida);
			}
		}
		leer.close();
	}

	// Carga la autoparte al catalogo
	public void CargarAutoparte(Autoparte a) {
		catalogo.add(a);
	}

	// Elimina la autoparte mediante el código de la misma
	public void EliminarDelCatalogo(int codigo) {
		if (catalogoVacio() == false) { // Verifica que el catalogo no este vacio
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) { // Busca la autoparte que coincida con el ID ingresado
					catalogo.remove(i); // Elimina la autoparte del catalogo
				}
			}
			System.out.println("Autoparte eliminada exitosamente!");
		} else {
			return;
		}
	}

	// Obtiene y devuelve el numero de cliente por medio de un pedido
	public int getClienteEnPedidoById(int pedidoId) {
		int idCliente = -1; // Inicializa la variable idCliente
		if (!pedidos.isEmpty()) { // Verifica que el contenedor de pedidos no este vacio
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == pedidoId) { // Busca el pedido que coincida con el ID ingresado
					System.out.println("ID Cliente: " + pedidoId + "\nNombre: " + clientes.get(i).getNombre());
					idCliente = pedidos.get(i).getCliente(); // Obtiene el numero de cliente
					return idCliente; // Devuelve el numero de cliente
				}
			}
		}
		return idCliente; // Devuelve el valor predeterminado si no encuentra el ID del cliente
	}

	// Obtiene y devuelve el cliente ingresado por ID
	public Cliente getClienteById(int id) {
		Cliente cliente = null; // Inicializa con un valor predeterminado

		if (!clientes.isEmpty()) { // Comprueba que el contenedor de clientes no este vacio
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getCodigo() == id) { // Busca el cliente que coincida con el ID ingresado
					return clientes.get(i); // Devuelve el cliente encontrado
				}
			}
		}
		return cliente; // Devuelve el valor predeterminado si no encuentra el cliente
	}

	// Obtiene y devuelve el precio de la autoparte ingresada por ID
	public double getPrecioAutoparte(int id) {
		double precio = 0.0; // Inicializa la variable precio

		if (!catalogoVacio()) { // Comprueba que el catalogo no este vacio
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == id) { // Busca la autoparte que coincida con el ID ingresado
					System.out.println("Precio unidad: $" + catalogo.get(i).getPrecio());
					return catalogo.get(i).getPrecio(); // Devuelve el precio de la autoparte encontrada
				}
			}
		}
		return precio; // Devuelve el precio predeterminado si no encuentra la autoparte
	}

	// Modifica el stock de una autoparte mediante su codigo y lo guarda en el catalogo
	public void ModificarStock(int codigo, int nuevoStock, int opcion) {
		if (catalogoVacio() == false) { // Verifica que el catalogo no este vacio
			if (existeAutoparte(codigo) == true) { // Verifica si existe la autoparte con dicho ID
				for (int i = 0; i < catalogo.size(); i++) {
					if (catalogo.get(i).getCodigo() == codigo) { // Busca la autoparte que coincida con el ID ingresado

						int stockAntiguo = catalogo.get(i).getCantStock(); // Obtiene el stock inicial de la autoparte
						int stockMinimo = catalogo.get(i).getStockMinimo(); // Obtiene el stock minimo inicial de la autoparte
						int stockFinal = 0; // Inicializa stockFinal

						if (opcion == 1) { 
							stockFinal = stockAntiguo + nuevoStock; // Suma el stock
						} else if (opcion == 2) { 
							stockFinal = nuevoStock; // Modifica el stock
						} else { 
							stockFinal = stockAntiguo - nuevoStock; // Resta el stock
						}
						catalogo.get(i).setCantStock(stockFinal); // Guarda el nuevo stock de la autoparte en el catalogo
						StockMinimo(stockFinal, stockMinimo); // Devuelve el stock final y minimo de la autoparte
						return;
					}
				}
			} else {
				return;
			}
		} else {
			return;
		}
	}

	// Agrega un cliente nuevo al sistema
	public void agregarCliente(Cliente c) {
		clientes.add(c);
	}

	// Verifica si un cliente ya se encuentra en el sistema
	public boolean existeCliente(int id) {
		if (!clientes.isEmpty()) { // Verifica si el contenedor de clientes no esta vacio
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getCodigo() == id) { // Busca el cliente que concuerde con el ID ingresado
					System.out.print("El cliente con ID " + id + " ya está registrado\n");
					return true; // Devuelve true si el cliente ya esta registrado en el sistema
				}
			}
			return false; // Devuelve false si el cliente no esta registrado
		} else {
			return false; // Devuelve false si el contenedor de clientes esta vacio
		}
	}

	// Verifica si dicha autoparte ya existe en el sistema
	public boolean idAutoparteRepetido(int id) {
		if (catalogo.isEmpty()) { // Verifica si el catalogo no esta vacio
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == id) { // Busca la autoparte que concuerde con el ID ingresado
					System.out.print("La autoparte con ID: " + id + " ya está registrado");
					return true; // Devuelve true si dicha autoparte existe en el sistema
				}
			}
			return false; // Devuelve false si la autoparte no existe 
		} else {
			return false; // Devuelve false si el catalogo esta vacio
		}
	}

	// Carga un pedido al contenedor de pedidos
	public void CargarPedido(Pedido p) {
		if (!catalogoVacio()) { // Verifica que el catalogo no este vacio
			// Revisa el precio sin modificarlo
			for (Pedido.Detalle detalle : p.getDetalles()) {
				int id = detalle.getArticulo();
				if (id >= 0 && id < catalogo.size()) {
					Autoparte a = catalogo.get(id);
					if (a != null) {
					} else {
						System.out.println("Autoparte con ID " + id + " no encontrada en el catálogo.");
					}
				}
			}
			pedidos.add(p);
			System.out.println("Pedido agregado exitosamente!");
		}
	}

	// Cancela un pedido por medio de su ID
	public void CancelarPedido(int codigo) {
		if (existePedido(codigo) == true) { // Verifica que exista dicho pedido en el sistema
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == codigo) { // Busca el pedido que concuerde con el ID ingresado
					pedidos.remove(i); // Elimina el pedido
					return;
				}
			}
		}
	}
	
	// Muestra por consola el detalle del pedido
	public void listarPedidos() {
		if (pedidos.isEmpty()) { // Verifica si no existen pedidos en el sistema
			System.out.println("No hay pedidos registrados en el sistema.");
		} else {
			System.out.println("Listado de Pedidos:");
			for (Pedido pedido : pedidos) {
				System.out.println("ID Pedido: " + pedido.getIdPedido());
				System.out.println("Fecha: " + pedido.getFecha());
				System.out.println("Cliente: " + pedido.getCliente());
				System.out.println("Monto Total: " + pedido.getMontoTotal());
				System.out.println("Detalles:");

				// Listar detalles de cada pedido
				for (Pedido.Detalle detalle : pedido.getDetalles()) {
					System.out.println("  ID Artículo: " + detalle.getArticulo());
					System.out.println("  Precio: " + detalle.getPrecio());
					System.out.println("  Cantidad: " + detalle.getCantidad());
					System.out.println("-----");
				}
				System.out.println("--------------------");
			}
		}
	}

	// Muestra por consola el detalle de la venta
	public void listarVentas() {
		if (cantVentas.isEmpty()) { // Verifica si no existen ventas en el sistema
			System.out.println("No hay ventas registradas en el sistema.");
		} else {
			System.out.println("Listado de Ventas:");
			for (Venta venta : cantVentas) {
				System.out.println("ID Venta: " + venta.getCodigo());
				System.out.println("Nombre Cliente: " + venta.getCliente().getNombre());
				System.out.println("Monto total Venta: " + venta.getDetalleVenta().getMontoTotal());
				System.out.println("--------------------");
			}
		}
	}

	// Realiza una venta de un autoparte CON pedido para un cliente
	public void RegistrarVentaConPedido(int numPedido, Venta v) {
		Pedido p = null;
		if (existePedido(numPedido) == true) { // Verifica que el pedido existe
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == numPedido) {
					p = pedidos.get(i); // Obtengo el pedido
				}
			}
			// Añade el detalle del producto a la venta
			v.setDetalleVenta(p);

			// Añade los datos faltantes a la venta
			v.setProvincia("Buenos Aires"); // Autodefino ya que es informacion de la sucursal
			v.setLocalidad("Monserrat"); // Autodefino ya que es informacion de la sucursal
			v.setTelefono(1122334455); // Autodefino ya que es informacion de la sucursal

			// Añade la venta al registro
			cantVentas.add(v);

		}
	}

	// Realiza una venta de un autoparte para un cliente SIN un pedido previo
	public void RegistrarVentaSinPedido(Pedido detalleVenta, Venta v) {
		// Busca la autoparte
		if (!catalogoVacio()) { // Verifica que el catalogo no este vacio
			// Se revisa el precio sin modificarlo
			for (Pedido.Detalle detalle : detalleVenta.getDetalles()) {
				int id = detalle.getArticulo();
				if (id >= 0 && id < catalogo.size()) {
					Autoparte a = catalogo.get(id);
					if (a != null) {
					} else {
						System.out.println("Autoparte con ID " + id + " no encontrada en el catálogo.");
					}
				}
			}
			pedidos.add(detalleVenta); // Añade a la venta los detalles del pedido
		} else {
			System.out.println("El catálogo está vacío.");
		}

		// Añade los datos faltantes a la venta
		v.setDetalleVenta(detalleVenta);
		v.setProvincia("Buenos Aires"); // Autodefino ya que es informacion de la sucursal
		v.setLocalidad("Monserrat"); // Autodefino ya que es informacion de la sucursal
		v.setTelefono(1122334455); // Autodefino ya que es informacion de la sucursal

		// Añade la venta al registro
		cantVentas.add(v);

	}

	// Verifica la disponibilidad y la cantidad de stock de un autoparte 
	// Devuelve el stock disponible de dicha autoparte
	public int DisponibilidadStock(int codigo) {
		int stock;
		if (catalogoVacio() == false) { // Verifica que el catalogo no este vacio
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) {
					stock = catalogo.get(i).getCantStock(); //Obtiene el stock de la autoparte con dicha ID
					if (stock == 1) {
						System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidad");
						return stock; // Devuelve el stock ya modificado de la autoparte
					} else if (stock > 1) {
						System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidades");
						return stock; // Devuelve el stock ya modificado de la autoparte
					} else {
						System.out.println("La autoparte " + codigo + " no posee stock disponible");
					}
				} else {
					existeAutoparte(codigo); // Verifica que exista en el catalogo dicha autoparte
				}
			}
		}
		return 0;
	}

	// Genera la factura de la venta con o sin pedido relacionada con un cliente
	public void GeneradorDeFacturas(Venta venta) {

		Cliente cliente = venta.getCliente();
		Pedido pedido = venta.getDetalleVenta();
		double totalVenta = venta.getMontoFinal();
		String medioPago = venta.getMedioDePago();
		int cantCuotas = venta.getCantCuotas();

		System.out.println("**********************************************************************************");
		System.out.println("                         FACTURA                          ");
		System.out.println("**********************************************************************************");
		System.out.println("Fecha de venta: " + pedido.getFecha());
		System.out.println("\nDATOS DEL NEGOCIO                       	DATOS DEL CLIENTE");
		System.out.println("Nombre: TUTTA LA MACCHINA               	ID: " + cliente.getCodigo());
		System.out.println("Dirección: Avenida Corrientes 123       	Nombre: " + cliente.getNombre());
		System.out.println("Localidad: Monserrat                    	Dirección: " + cliente.getDireccion());
		System.out.println("Provincia: Buenos Aires                 	Localidad: " + cliente.getLocalidad());
		System.out.println("Teléfono: 1122334455                    	Provincia: " + cliente.getProvincia());
		System.out.println("                                        	Correo: " + cliente.getCorreo());
		System.out.println("                                        	Teléfono: " + cliente.getTelefono());
		System.out.println("**********************************************************************************");
		System.out.println("Detalles de la Venta:");
		System.out.printf("%-10s %-20s %-15s %-20s %-10s\n", "ID", "Autoparte", "Cantidad", "Precio Unitario",
				"  Total");
		System.out.println("----------------------------------------------------------------------------------");
		for (Pedido.Detalle detalle : pedido.getDetalles()) {
			String nombreAutoparte = getNombreAutoparte(detalle.getArticulo());
			System.out.printf("%-10s %-24s %-15d %-18.2f %-18.2f\n", detalle.getArticulo(), nombreAutoparte,
					detalle.getCantidad(), detalle.getPrecio(), (detalle.getCantidad() * detalle.getPrecio()));
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("%-70s %-10.2f\n", "Subtotal:", pedido.getMontoTotal());
		System.out.println("**********************************************************************************");
		System.out.println("Medio de pago: " + medioPago);
		if (cantCuotas != 0)
			System.out.println("Cantidad de Cuotas: " + cantCuotas);
		if (medioPago == "Efectivo")
			System.out.printf("%-70s %-10.2f\n", "Descuento: ", (pedido.getMontoTotal() - totalVenta) );
		System.out.printf("%-70s %-10.2f\n", "Monto Total a Pagar:", totalVenta);
		System.out.println("**********************************************************************************");
		System.out.println("Gracias por su compra!");
	}

	// Alerta en caso de que el stock quede por debajo del minimo
	public void StockMinimo(int stock, int minimo) {
		if (stock < minimo) {
			System.out.println("\nAlerta! El stock de esta autoparte se encuentra por debajo del mínimo --> " + minimo
					+ ". Contacte con proveedores.\n");
			return;
		} else {
			return;
		}
	}

	// Verifica si existe "x" autoparte dentro del catalogo.
	public boolean existeAutoparte(int codigo) {
		if (catalogoVacio() == false) { // Verifica que el catalogo no este vacio
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) {
					return true; // Devuelve true en caso de existir
				}
			}
			System.out.println("La autoparte con código " + codigo + " no existe, intente nuevamente");
			return false; // Devuelve false si la autoparte con dicho ID no existe
		} else {
			return false; // Devuelve false si el catalogo esta vacio
		}
	}
	
	// Verifica sin existe un pedido con un determinado ID
	public boolean existePedidoConId(int codigo) {
		if (catalogoVacio() == false) { // Verifica que el catalogo no este vacio
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == codigo) {
					System.out.println("El pedido con código " + codigo + "  existe, intente cargando otro código");
					return true; // Devuelve true en caso de existir
				}
			}
			return false; // Devuelve false si no existe un pedido con dicho ID
		} else {
			return false; // Devuelve false si el catalogo esta vacio
		}
	}
	
	// Verifica si existe una venta por medio del ID
	public boolean existeVentaConId(int codigo) {
		if (catalogoVacio() == false) { // Verifica que el catalogo no este vacio
			for (int i = 0; i < cantVentas.size(); i++) {
				if (cantVentas.get(i).getCodigo() == codigo) {
					System.out.println("La operación de venta con código " + codigo + " ya existe en el sistema, intente utilizando otro código");
					return true; // Devuelve true en caso de existir
				}
			}
			return false; // Devuelve false si no existe la venta con dicho ID
		} else {
			return false; // Devuelve false si el catalogo esta vacio
		}
	}

	// Devuelve la denominacion del autoparte necesitado
	public String getNombreAutoparte(int id) {
		// Inicializar con un valor predeterminado
		String nombre = "";

		// Comprueba si el catálogo no está vacío
		if (!catalogoVacio()) {
			// Recorre el catálogo
			for (int i = 0; i < catalogo.size(); i++) {
				// Comprobora si el código de la autoparte coincide con el ID buscado
				if (catalogo.get(i).getCodigo() == id) {
					// Devuelve el precio de la autoparte encontrada
					return catalogo.get(i).getDenominacion();
				}
			}
		}
		// Devuelve el valor predeterminado si no se encuentra la autoparte
		return nombre;
	}

	// Verifica si el catálogo no dispone de autopartes
	public boolean catalogoVacio() {
		if (catalogo.isEmpty()) {
			System.out.println("El catálogo está vacío, se necesita al menos 1 autoparte");
			return true; // Devuelve true si esta vacio
		}
		return false; // Devuelve false en caso de que haya al menos una autoparte 
	}

	// Verifica si existe un pedido mediante el ingreso del número del mismo.
	public boolean existePedido(int numero) {
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getIdPedido() == numero) {
				return true; // Devuelve true si existe un pedido con dicho numero
			}
		}
		System.out.println("No existe un pedido con ID: " + numero + ", intente nuevamente!");
		return false; // Devuelve false si no existe un pedido con dicho numero
	}
}