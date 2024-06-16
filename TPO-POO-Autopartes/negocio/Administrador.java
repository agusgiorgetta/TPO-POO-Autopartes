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

	// Utilicé el scanner acá también ya que no puede recibir otro dato debido que
	// hay opciones
	// que son Strings y otras Int, por ende, recibe la opción elegida por el
	// usuario y se recorre
	// el catalogo y se modifica la autoparte de acuerdo a lo seleccionado
	public void ModificarCatalogo(int codigo, int opcion) {

		Scanner leer = new Scanner(System.in);
		boolean entradaValida = false;

		for (int i = 0; i < catalogo.size(); i++) {
			if (catalogo.get(i).getCodigo() == codigo) {
				do {
					try {
						if (opcion == 1) {
							boolean codigoInvalido = true;
							while (codigoInvalido) {
								System.out.print("Introduzca el nuevo código: ");
								int codigoNuevo = leer.nextInt();
								if (codigoNuevo >= 0) {
									entradaValida = true;
									codigoInvalido = false;
									catalogo.get(i).setCodigo(codigoNuevo);
									System.out.println("Se ha modificado exitosamente el código del autoparte!");										
								} else {
									System.out.println("\nNo se permite que el código sea inferior a 0, introduzca nuevamente\n");
								}
							}
						}
						entradaValida = true;
						if (opcion == 2) {
							System.out.print("Introduzca la nueva denominación: ");
							String denominacion = leer.next();
							catalogo.get(i).setDenominacion(denominacion);
							System.out.println("Se ha modificado exitosamente la denominacion del autoparte!");
						} else if (opcion == 3) {
							System.out.print("Introduzca la nueva descripción: ");
							String descripcion = leer.next();
							catalogo.get(i).setDescripcion(descripcion);
							System.out.println("Se ha modificado exitosamente la descripción del autoparte!");
						} else if (opcion == 4) {
							System.out.print("Introduzca la nueva categoría: ");
							String categoria = leer.next();
							catalogo.get(i).setCategoria(categoria);
							System.out.println("Se ha modificado exitosamente la categoría del autoparte!");
						} else if (opcion == 5) {
							System.out.print("Introduzca la marca: ");
							String marca = leer.next();
							catalogo.get(i).setMarca(marca);
							System.out.println("Se ha modificado exitosamente la marca del autoparte!");
						} else if (opcion == 6) {
							System.out.print("Introduzca el modelo: ");
							String modelo = leer.next();
							catalogo.get(i).setModelo(modelo);
							System.out.println("Se ha modificado exitosamente el modelo del autoparte!");
						}
						do {
							entradaValida = false;
							try {
								if (opcion == 7) {
									boolean precioInvalido = true;
									while (precioInvalido) {
										System.out.print("Introduzca el nuevo precio: ");
										double precio = leer.nextDouble();
										if (precio >= 0) {
											catalogo.get(i).setPrecio(precio);
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
								if (opcion == 8) {
									boolean stockMinimoInvalido = true;
									while (stockMinimoInvalido) {
										System.out.print("Introduzca el nuevo stock mínimo: ");
										int stockMinimo = leer.nextInt();
										if (stockMinimo >= 0) {
											catalogo.get(i).setStockMinimo(stockMinimo);
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

						if (opcion == 9) {
							System.out.print("Introduzca el nuevo enlace: ");
							String enlace = leer.next();
							catalogo.get(i).setEnlace(enlace);
							System.out.println("Se ha modificado exitosamente el enlace del autoparte!");
						}

						do {
							entradaValida = false;
							try {
								if (opcion == 10) {
									boolean stockInvalido = true;
									while (stockInvalido) {
										System.out.print("Introduzca el nuevo stock: ");
										int stock = leer.nextInt();
										if (stock >= 0) {
											entradaValida = true;
											stockInvalido = false;
											catalogo.get(i).setCantStock(stock);
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
	}

	// carga la autoparte al catalogo
	public void CargarAutoparte(Autoparte a) {
		catalogo.add(a);
	}

	// elimina la autoparte mediante el código de la misma
	public void EliminarDelCatalogo(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) {
					catalogo.remove(i);
				}
			}
			System.out.println("Autoparte eliminada exitosamente!");
		} else {
			return;
		}
	}

	public int getClienteEnPedidoById(int pedidoId) {
		int idCliente = -1;
		// Comprobar si el catálogo no está vacío
		if (!pedidos.isEmpty()) {
			// Recorrer el catálogo
			for (int i = 0; i < pedidos.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (pedidos.get(i).getIdPedido() == pedidoId) {
					System.out.println("ID Cliente: " + pedidoId + "\nNombre: " + clientes.get(i).getNombre());
					// Devolver el precio de la autoparte encontrada
					idCliente = pedidos.get(i).getCliente();
					return idCliente;
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return idCliente;
	}

	public Cliente getClienteById(int id) {
		// Inicializar con un valor predeterminado
		Cliente cliente = null;

		// Comprobar si el catálogo no está vacío
		if (!clientes.isEmpty()) {
			// Recorrer el catálogo
			for (int i = 0; i < clientes.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (clientes.get(i).getCodigo() == id) {
					// Devolver el precio de la autoparte encontrada
					return clientes.get(i);
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return cliente;
	}

	public double getPrecioAutoparte(int id) {
		// Inicializar con un valor predeterminado
		double precio = 0.0;

		// Comprobar si el catálogo no está vacío
		if (!catalogoVacio()) {
			// Recorrer el catálogo
			for (int i = 0; i < catalogo.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (catalogo.get(i).getCodigo() == id) {
					System.out.println("Precio unidad: $" + catalogo.get(i).getPrecio());
					// Devolver el precio de la autoparte encontrada
					return catalogo.get(i).getPrecio();
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return precio;
	}

	// modifica el stock de una autoparte mediante su codigo y se lo guarda en el
	// catalogo
	public void ModificarStock(int codigo, int nuevoStock, int opcion) {
		if (catalogoVacio() == false) {
			if (existeAutoparte(codigo) == true) {
				for (int i = 0; i < catalogo.size(); i++) {
					if (catalogo.get(i).getCodigo() == codigo) {

						int stockAntiguo = catalogo.get(i).getCantStock();
						int stockMinimo = catalogo.get(i).getStockMinimo();
						int stockFinal = 0;

						if (opcion == 1) { // sumar el stock
							stockFinal = stockAntiguo + nuevoStock;
						} else if (opcion == 2) { // modificar stock
							stockFinal = nuevoStock;
						} else { // restar, en caso de reservas
							stockFinal = stockAntiguo - nuevoStock;
						}
						catalogo.get(i).setCantStock(stockFinal);
						StockMinimo(stockFinal, stockMinimo);
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
		if (!clientes.isEmpty()) {
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getCodigo() == id) {
					System.out.print("El cliente con ID " + id + " ya está registrado\n");
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public boolean idAutoparteRepetido(int id) {
		if (!catalogo.isEmpty()) {
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == id) {
					System.out.print("La autoparte con ID: " + id + " ya está registrado");
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	// Carga un pedido al contenedor de pedidos
	public void CargarPedido(Pedido p) {
		if (!catalogoVacio()) {
			// Aquí es donde revisamos el precio sin cambiarlo
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

	// Verifica que exista el pedido, lo cancela en base al numero de pedido y
	// devuelve el stock
	public void CancelarPedido(int codigo) {
		if (existePedido(codigo) == true) {
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == codigo) {
					pedidos.remove(i);
					return;
				}
			}
		}
	}

	public void listarPedidos() {
		if (pedidos.isEmpty()) {
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

	public void listarVentas() {
		if (cantVentas.isEmpty()) {
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
		if (existePedido(numPedido) == true) {
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == numPedido) {
					p = pedidos.get(i);
				}
			}
			// se añade el detalle del producto a la venta
			v.setDetalleVenta(p);

			// se añaden los datos faltantes a la venta
			v.setProvincia("Buenos Aires"); // autodefino xq son de la sucursal
			v.setLocalidad("Monserrat"); // autodefino xq son de la sucursal
			v.setTelefono(1122334455); // autodefino xq son de la sucursal

			// se añade la venta al registro
			cantVentas.add(v);

		}
	}

	// Realiza una venta de un autoparte para un cliente SIN un pedido previo
	public void RegistrarVentaSinPedido(Pedido detalleVenta, Venta v) {
		// buscamos la autoparte
		if (!catalogoVacio()) {
			// Aquí es donde revisamos el precio sin cambiarlo
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
			pedidos.add(detalleVenta);
		} else {
			System.out.println("El catálogo está vacío.");
		}

		// se añaden los datos faltantes a la venta
		v.setDetalleVenta(detalleVenta);
		v.setProvincia("Buenos Aires"); // autodefino xq son de la sucursal
		v.setLocalidad("Monserrat"); // autodefino xq son de la sucursal
		v.setTelefono(1122334455); // autodefino xq son de la sucursal

		// se añade la venta al registro
		cantVentas.add(v);

	}

	// Verifica la disponibilidad y la cantidad de stock de un autoparte y devuelve
	// el stock disponible
	public int DisponibilidadStock(int codigo) {
		int stock;
		if (catalogoVacio() == false) {
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) {
					stock = catalogo.get(i).getCantStock();
					if (stock == 1) {
						System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidad");
						return stock;
					} else if (stock > 1) {
						System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidades");
						return stock;
					} else {
						System.out.println("La autoparte " + codigo + " no posee stock disponible");
					}
				} else {
					existeAutoparte(codigo);
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

	// Verifica si existe "x" autoparte dentro del catálogo. Si no existe devuelve
	// false, caso contrario true
	public boolean existeAutoparte(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) {
					return true;
				}
			}
			System.out.println("La autoparte con código " + codigo + " no existe, intente nuevamente");
			return false;
		} else {
			return false;
		}
	}

	public boolean existePedidoConId(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == codigo) {
					System.out.println("El pedido con código " + codigo + "  existe, intente cargando otro código");
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}
	public boolean existeVentaConId(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < cantVentas.size(); i++) {
				if (cantVentas.get(i).getCodigo() == codigo) {
					System.out.println("La operación de venta con código " + codigo + " ya existe en el sistema, intente utilizando otro código");
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	// Devuelve la denominacion del autoparte necesitado
	public String getNombreAutoparte(int id) {
		// Inicializar con un valor predeterminado
		String nombre = "";

		// Comprobar si el catálogo no está vacío
		if (!catalogoVacio()) {
			// Recorrer el catálogo
			for (int i = 0; i < catalogo.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (catalogo.get(i).getCodigo() == id) {
					// Devolver el precio de la autoparte encontrada
					return catalogo.get(i).getDenominacion();
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return nombre;
	}

	// Verifica si el catálogo no dispone de autopartes y devuelve true en caso de
	// serlo. Caso contrario devuelve false
	public boolean catalogoVacio() {
		if (catalogo.isEmpty()) {
			System.out.println("El catálogo está vacío, se necesita al menos 1 autoparte");
			return true;
		}
		return false;
	}

	// Verifica si existe un pedido mediante el ingreso del número del mismo.
	public boolean existePedido(int numero) {
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getIdPedido() == numero) {
				return true;
			}
		}
		System.out.println("No existe un pedido con ID: " + numero + ", intente nuevamente!");
		return false;
	}
}