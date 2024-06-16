package empresa;

import negocio.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejecutable {

	private static Administrador admin = new Administrador();

	static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {

		boolean verificador = true; // Inicializa la variable verificador
		boolean validar = true; // Inicializa la variable validar
		int contador = 3;

		System.out.println("-------------- BIENVENIDO A TUTTA LA MACCHINA --------------");
		System.out.println("Ingrese por favor sus datos");

		while (validar) {

			System.out.print("USUARIO: ");
			String usuario = leer.nextLine();
			System.out.print("CONTRASEÑA: ");
			String contrasena = leer.nextLine();

			if (admin.IniciarSesion(usuario, contrasena) == true && contador != 0) { // Verifica que los datos ingresados sean correctos

				while (verificador) {

					try {

						System.out.println("\n--------------------------- MENÚ ---------------------------");
						System.out.print(
								"[1] Agregar autoparte \n[2] Listar autopartes \n[3] Eliminar autoparte del catálogo \n[4] Modificar stock autoparte \n[5] Modificar catálogo \n[6] Disponibilidad stock de una autoparte \n[7] Agregar cliente \n[8] Cargar pedido \n[9] Cancelar pedido \n[10] Registrar venta con pedido \n[11] Registrar venta sin pedido \n[0] Cerrar Sesión \n\nOPCION: ");

						int opcion = leer.nextInt();

						if (opcion == 1) {
							agregarAutoparte();
						} else if (opcion == 2) {
							mostrarCatalogo();
						} else if (opcion == 3) {
							eliminarAutoparte();
						} else if (opcion == 4) {
							modificarStock();
						} else if (opcion == 5) {
							modificarCatalogo();
						} else if (opcion == 6) {
							stockDisponible();
						} else if (opcion == 7) {
							agregarCliente();
						} else if (opcion == 8) {
							cargarPedido();
						} else if (opcion == 9) {
							cancelarPedido();
						} else if (opcion == 10) {
							registrarVentaConPedido();
						} else if (opcion == 11) {
							registrarVentaSinPedido();
						} else if (opcion == 0) {
							if (admin.CerrarSesion() == true) {
								System.out.println("Se ha finalizado la sesión exitosamente!\n");
								System.out.println("-------------------- TUTTA LA MACCHINA ----------------------");
								validar = false;
								leer.close();
								break;
							}
						}
					} catch (InputMismatchException ex) {
						System.out.println(
								"\nERROR --> Solo se permite el ingreso de números, introduzca nuevamente la opción");
						leer.nextLine();
					}
				}
			} else {
				contador = contador - 1;
				System.out
						.println("ERROR --> Datos erroneos, vuelva a intentar! Intentos restantes: " + contador + "\n");
				if (contador == 0) {
					break;
				}
			}
		}
	}

	// Agrega una autoparte al catalogo
	public static void agregarAutoparte() {

		Autoparte autoparte = new Autoparte();
		boolean entradaValida = false; // Inicializa la variable entradaValida
		
		do {
            try {
            	boolean codigoInvalido = true; // Inicializa la variable codigoInvalido
            	while (codigoInvalido) { // Verifica que el codigo sea valido, si lo es, corta
            		System.out.print("Introduzca el código: ");
	                int codigo = leer.nextInt();
	                if (codigo >= 0) { // Comprueba que el codigo sea un numero positivo
		                if (admin.idAutoparteRepetido(codigo)) { // Verifica si la autoparte ya existe en el sistema
		                    System.out.print("\n");
		                    codigoInvalido = false;
		                } else {
		                    autoparte.setCodigo(codigo); // Guarda el ID de la autoparte 
		                    entradaValida = true; 
		                    codigoInvalido = false;
		                }	                	
	                } else {
	                	System.out.println("\nNo se permite que el código sea inferior a 0, introduzca nuevamente\n");
	                }
            	}
            } catch (InputMismatchException ex) {
            	System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el código del autoparte\n");
				leer.nextLine(); // Limpia el búfer de entrada después de un error
            }
        } while (!entradaValida);
		
		entradaValida = false;
		
		leer.nextLine();
		
		System.out.print("Introduzca la denominación: ");
		String denominacion = leer.nextLine();
		autoparte.setDenominacion(denominacion); // Guarda la denominacion de la autoparte

		System.out.print("Introduzca la descripción: ");
		String descripcion = leer.nextLine();
		autoparte.setDescripcion(descripcion); // Guarda la descripcion de la autoparte

		System.out.print("Introduzca la categoria: ");
		String categoria = leer.nextLine();
		autoparte.setCategoria(categoria); // Guarda la categoria de la autoparte

		System.out.print("Introduzca la marca: ");
		String marca = leer.nextLine();
		autoparte.setMarca(marca); // Guarda la marca de la autoparte

		System.out.print("Introduzca el modelo: ");
		String modelo = leer.nextLine();
		autoparte.setModelo(modelo); // Guarda el modelo de la autoparte

		do {
			try {
				boolean precioInvalido = true; // Inicializa la variable precioInvalido
				while (precioInvalido) { // Verifica que el precio sea valido, si lo es, corta
					System.out.print("Introduzca el precio: ");
					double precio = leer.nextDouble();
					if (precio >= 0) { // Comprueba que el precio es un numero positivo
						autoparte.setPrecio(precio); // Guarda el precio en la autoparte
						entradaValida = true;	
						precioInvalido = false;
					} else {
						System.out.println("\nNo se permite un precio inferior a 0, intente nuevamente\n");
					}
				}
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números, introduzca nuevamente el precio\n");
				leer.nextLine();
			}
		} while (!entradaValida);

		entradaValida = false;

		do {
			try {
				boolean stockInvalido = true; // Inicializa la variable stockInvalido
				while(stockInvalido) { // Verifica que el stock sea valido, si lo es, corta
					System.out.print("Introduzca el stock: ");
					int stock = leer.nextInt();
					if (stock >= 0) { // Comprueba que el stock es un numero positivo
						autoparte.setCantStock(stock); // Guarda el stock en la autoparte
						stockInvalido = false;
						entradaValida = true;
					} else {
						System.out.println("\nNo se permite que el stock sea inferior a 0, introduzca nuevamente\n");
					}
				}
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock\n");
				leer.nextLine();
			}
		} while (!entradaValida);

		entradaValida = false;

		do {
			try {
				boolean stockMinimoInvalido = true; // Inicializa la variable stockMinimoInvalido
				while(stockMinimoInvalido) { // Verifica que el stock minimo sea valido, si lo es, corta
					System.out.print("Introduzca el stock minimo: ");
					int stockMinimo = leer.nextInt();
					if(stockMinimo >= 0) { // Comprueba que el stock minimo sea un numero positivo
						autoparte.setStockMinimo(stockMinimo); // Guarda el stock minimo de la autoparte
						entradaValida = true;	
						stockMinimoInvalido = false;
					} else {
						System.out.println("\nNo se permite que el stock mínimo sea inferior a 0, introduzca nuevamente\n");
					}
				}
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock mínimo\n");
				leer.nextLine();
			}
		} while (!entradaValida);

		leer.nextLine();
		
		System.out.print("Introduzca el enlace: ");
		String enlace = leer.nextLine();
		autoparte.setEnlace(enlace); // Guarda el enlace de la autoparte

		admin.CargarAutoparte(autoparte); // Guarda y carga todos los datos de la autoparte 
		System.out.println("Autoparte cargada exitosamente!");
	}

	// Muestra por consola el catalogo completo con todas las autopartes existentes
	public static void mostrarCatalogo() {
		admin.ListarCatalogo();
	}

	// Elimina una autoparte del catalogo
	public static void eliminarAutoparte() {

		boolean entradaValida = false; // Inicializa la variable entradaValida

		do {
			try {
				System.out.print("Introduzca el código del autoparte: ");
				int codigo = leer.nextInt();
				entradaValida = true;
				if (admin.existeAutoparte(codigo) == true) { // Verifica que exista la autoparte
					admin.EliminarDelCatalogo(codigo); // Elimina del catalogo la autoparte
				}
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, ingrese nuevamente el código del autoparte\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	// Modifica el stock de una autoparte 
	public static void modificarStock() {

		boolean entradaValida = false;

		do {
			try {
				System.out.print("Introduzca el código del autoparte para modificar su stock: ");
				int codigo = leer.nextInt();
				entradaValida = true;
				if (admin.existeAutoparte(codigo) == true) { // Verifica que exista la autoparte
					entradaValida = false;
					do {
						try {
							System.out.print(
									"OPCIONES: \n[1] Sumar unidades al stock actual \n[2] Reemplazar el valor del stock \nOPCION: ");
							int opcion = leer.nextInt();
							entradaValida = true;
							do {
								entradaValida = false;
								try {
									boolean stockInvalido = true;
									while (stockInvalido) { // // Verifica que el stock sea valido, si lo es, corta
										System.out.print("Introduzca el nuevo stock: ");
										int stock = leer.nextInt();
										if (stock >= 0) { // Comprueba que el stock sea un numero positivo
											admin.ModificarStock(codigo, stock, opcion); // Modifica el stock de la autoparte de acuerdo a la opcion elegida
											System.out.println("Stock modificado exitosamente!");
											stockInvalido = false;
											entradaValida = true;													
										} else {
											System.out.println("\nNo se permite que el stock sea inferior a 0, introduzca nuevamente\n");
										}
									}
								} catch (InputMismatchException ex) {
									System.out.println(
											"\nERROR --> Solo se aceptan números enteros, ingrese nuevamente el stock\n");
									leer.nextLine();
								}
							} while (!entradaValida);
						} catch (InputMismatchException ex) {
							System.out.println(
									"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción\n");
							leer.nextLine();
						}
					} while (!entradaValida);
				}
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	// Modifica los datos de una autoparte
	public static void modificarCatalogo() {

		boolean entradaValida = false;

		do {
			try {
				System.out.print("Introduzca el código del autoparte que desea modificar: ");
				int codigo = leer.nextInt();
				entradaValida = true;
				do {
					entradaValida = false;
					try {
						if (admin.existeAutoparte(codigo) == true) { // Verifica que exista la autoparte
							System.out.println("Que desea modificar del autoparte?");
							System.out.print(
									"OPCIONES: \n[1] Código \n[2] Denominación \n[3] Descripción \n[4] Categoría \n[5] Marca \n[6] Modelo \n[7] Precio \n[8] Stock Mínimo \n[9] Enlace \n[10] Stock \n[0] Salir \nOPCION: ");
							int opcion = leer.nextInt();
							entradaValida = true;

							admin.ModificarCatalogo(codigo, opcion); // Modifica el dato de la autoparte de acuerdo a lo elegido
						}
						entradaValida = true;
					} catch (InputMismatchException ex) {
						System.out.println(
								"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opcion\n");
						leer.nextLine();
					}
				} while (!entradaValida);
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el codigo del autoparte\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	// Verifica que exista stock de una autoparte
	public static void stockDisponible() {

		boolean entradaValida = false;

		do {
			try {
				System.out.print("Introduzca el código del autoparte para averiguar su stock disponible: ");
				int codigo = leer.nextInt();

				entradaValida = true;

				if (admin.existeAutoparte(codigo) == true) { // Verifica que exista la autoparte 
					admin.DisponibilidadStock(codigo); // Comprueba la existencia de stock de la autoparte
				}
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el codigo del autoparte\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	public static int agregarCliente() {

		Cliente nuevoCliente = new Cliente();
		boolean entradaValida = false;
		int idUsuario = -1;

		do {
			try {
				boolean idClienteInvalido = true; // Inicializa la variable idClienteInvalido
				int idCliente = 0; // Inicializa la variable idCliente
				while (idClienteInvalido) { // Verifica que el ID del cliente sea valido, si lo es, corta
					System.out.print("Introduzca el ID del cliente: ");
					int id = leer.nextInt();
					if (id >= 0) { // Comprueba que el ID sea un numero positivo
						leer.nextLine();
		
						entradaValida = true;
						idClienteInvalido = false;
						idCliente = id; // Guarda en la variable idCliente el ID
						while (admin.existeCliente(id)) { // Verifica que exista un cliente con dicho ID, si existe, pide otro
							System.out.print("\nIntroduzca otro ID: ");
							id = leer.nextInt();
							leer.nextLine();
						}
					} else {
						System.out.println("\nNo se permite que el ID del cliente sea inferior a 0, introduzca nuevamente\n");
					}
				}
					if (!admin.existeCliente(idCliente)) { // Verifica si existe un cliente con dicho ID, si no existe, lo carga en el sistema
	
						nuevoCliente.setCodigo(idCliente); // Guarda el codigo del cliente
	
						System.out.print("Introduzca el nombre del cliente: ");
						nuevoCliente.setNombre(leer.nextLine()); // Guarda el nombre del cliente
	
						System.out.print("Introduzca la dirección del cliente: ");
						nuevoCliente.setDireccion(leer.nextLine()); // Guarda la direccion del cliente
	
						System.out.print("Introduzca la localidad del cliente: ");
						nuevoCliente.setLocalidad(leer.nextLine()); // Guarda la localidad del cliente
	
						System.out.print("Introduzca la provincia del cliente: ");
						nuevoCliente.setProvincia(leer.nextLine()); // Guarda la provincia del cliente
	
						System.out.print("Introduzca el correo del cliente (ej. nombre@dom.ext): ");
						nuevoCliente.setCorreo(leer.nextLine()); // Guarda el correo del cliente
	
						System.out.print("Introduzca el teléfono del cliente: ");
						nuevoCliente.setTelefono(leer.nextLine()); // Guarda el telefono del cliente
	
						admin.agregarCliente(nuevoCliente); // Guarda todos los datos del cliente
	
						System.out.println("\nCliente agregado exitosamente!");
						idUsuario = idCliente;
					}
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del cliente\n");
				leer.nextLine();
			}
		} while (!entradaValida);
		return idUsuario;
	}

	// Permite la carga de un pedido en el sistema
	public static void cargarPedido() {

		Pedido pedido = new Pedido(); // Crear un nuevo pedido
		boolean entradaValida = false; // Inicializa la variable entradaValida
		boolean exit = false; // Inicializa la variable exit

		do {
			try {
				boolean numeroPedidoInvalido = true; // Inicializa la variable numeroPedidoInvalido
				if (admin.catalogoVacio() == false) { // Verifica que el catalogo no este vacio
					while (numeroPedidoInvalido) { // Verifica que el pedido sea valido, si lo es, corta
						System.out.print("Introduzca el número del pedido: ");
						int numeroPedido = leer.nextInt();
						
						if (numeroPedido >= 0) { // Comprueba que el numero ingresado es un numero positivo
							
							while (admin.existePedidoConId(numeroPedido)) { // Comprueba que la existencia de un pedido con dicho id
								System.out.print("Introduzca otro número del pedido: ");
								numeroPedido = leer.nextInt();
							}
							numeroPedidoInvalido = false;
							pedido.setIdPedido(numeroPedido); // Si no existe, guarda el ID del pedido
						} else {
							System.out.println("\nNo se permite que el número de pedido sea inferior a 0, introduzca nuevamente\n");
						}
					}
				
					entradaValida = true;

					System.out.print("Introduzca la fecha del pedido: ");
					String fecha = leer.next();
					pedido.setFecha(fecha); // Guarda la fecha del pedido

					leer.nextLine(); 

					do {
						entradaValida = false;
						try {
							boolean idClienteInvalido = true; // Inicializa la variable idClienteInvalido
							while (true) {
								while (idClienteInvalido) { // Verifica que el pedido sea valido, si lo es, corta
									System.out.print("Introduzca el ID del cliente: ");
									int cliente = leer.nextInt();
									if (cliente >= 0) { // Comprueba que el ID del cliente sea un numero positivo
										entradaValida = true;
										idClienteInvalido = false;
										if (admin.existeCliente(cliente)) { // Comprueba la existencia de dicho cliente
											pedido.setCliente(cliente); // Guarda el cliente en el pedido
											break;
										} else {
											System.out.println("\nDicho cliente NO se encuentra registrado");
											System.out.println("Ingrese los datos del cliente a registrar\n");
											agregarCliente(); // Llama al metodo para agregar a un nuevo cliente al sistema
											pedido.setCliente(cliente); // Guarda el cliente en el pedido
											break;
										}
									} else {
										System.out.println("\nNo se permite que el ID del cliente sea inferior a 0, introduzca nuevamente\n");
									}
									
								}
								break;
							}
						} catch (InputMismatchException ex) {
							System.out.println(
									"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del cliente\n");
							leer.nextLine();
						}
					} while (!entradaValida);
					
					boolean pedir = true; // Inicializa la variable pedir
				
					do {
						entradaValida = false;
						try {
							while (true) {
								while (pedir) {
									System.out.println("\n-----PEDIDO DE AUTOPARTES-----");
									System.out.print("Introduzca el ID de la autoparte: ");
									int id = leer.nextInt();
	
									if (admin.existeAutoparte(id)) { // Verifica la existencia de la autoparte
										int stock = admin.DisponibilidadStock(id); // Obtiene el stock de la autoparte con dicho ID
										boolean verificar = true; // Inicializa la variable verificar
										if (stock == 0) { // Comprueba que no haya stock disponible
											 while (verificar) { // Verifica que la opcion sea valido, si lo es, corta
												 entradaValida = false;
												 do {												 
													 try {
			                                            System.out.print("¿Desea tratar con otra? \n[1] Si \n[2] No \nOPCION: ");
			                                            int seguir = leer.nextInt();
			                                            if (seguir == 2) {
			                                            	verificar = false;
			                                            	admin.CargarPedido(pedido); // Guarda los datos en el pedido, corta el ciclo
			                                                return;
			                                            } else if (seguir == 1) {
			                                            	verificar = false;
			                                                entradaValida = true;
			                                                break;
			                                            } else {
			                                                System.out.println("Valor inválido. Intente otra vez.\n");
			                                            }
			                                        } catch (InputMismatchException ex) {
			                                            System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción\n");
			                                            leer.nextLine();
			                                        }
												 } while (!entradaValida);
			                                 }
											 if (verificar == false) {
												 break;
											 }
										}
										do {
											entradaValida = false;
											try {
												boolean cantInvalida = true; // Inicializa la variable cantInvalida
												while (cantInvalida) {
													System.out.print("\nIntroduzca la cantidad necesitada: ");
													int cantidad = leer.nextInt();
													if (cantidad >= 0) { // Comprueba que la cantidad sea un numero positivo
														entradaValida = true;
														cantInvalida = false;
														if (cantidad <= stock) { // Verifica que la cantidad necesitada sea inferior o igual al stock disponible
															double precioUnidad = admin.getPrecioAutoparte(id); // Obtiene el precio de la autoparte con dicho ID
															double precioTotal = precioUnidad * cantidad; // Obtiene el monto total
															System.out.print("Precio total: $" + precioTotal);
															// Agregar el detalle al pedido actual
															pedido.agregarDetalle(id, precioUnidad, cantidad); // Agrega al pedido el detalle
															admin.ModificarStock(id, cantidad, 0); // Reduce el stock de la autoparte																									
															do {
																entradaValida = false;
																try {
																	System.out.print(
																			"\nDesea cargar más autopartes al pedido? [1] Si | [2] No \nOPCION: ");
																	int elegir = leer.nextInt();
																	entradaValida = true;
			
																	if (elegir == 2) {
																		admin.CargarPedido(pedido); // Agrega el pedido al contenedor de pedidos, corta el bucle
																		exit = true;						
																		pedir = false;
																	}
																	entradaValida = true;
																} catch (InputMismatchException ex) {
																	System.out.println(
																			"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opcion\n");
																	leer.nextLine();
																}
															} while (!entradaValida);
		
														} else {
															System.out.println("\nSolo hay " + stock
																	+ " unidades en stock, intente nuevamente");
															entradaValida = true;
														}
													} else {
														System.out.println("\nNo se permite que la cantidad necesitada sea inferior a 0, introduzca nuevamente");
													}
												}
											} catch (InputMismatchException ex) {
												System.out.println(
														"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la cantidad necesitada");
												leer.nextLine();
											}
										} while (!entradaValida);
									}								
									entradaValida = true;
								}
								if (exit == true) {
									break;
								}
							} 
						} catch (InputMismatchException ex) {
							System.out.println(
									"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del autoparte\n");
							leer.nextLine();
						}
					} while (!entradaValida);
				}
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el número del pedido\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	// Permite cancelar un pedido de acuerdo a su ID
	public static void cancelarPedido() {

		boolean entradaValida = false; // Inicializa la variable entradaValida

		do {
			try {
				System.out.print("Introduzca el número de pedido a cancelar: ");
				int numero = leer.nextInt();
				admin.CancelarPedido(numero); // Cancela un pedido por medio de su ID
				System.out.println("Pedido cancelado exitosamente!");
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el número del pedido\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	// Permite registrar una venta CON pedido
	public static void registrarVentaConPedido() {
		Venta venta = new Venta();
		Cliente cliente = new Cliente();
		boolean entradaValida = false; // Inicializa la variable entradaValida

		do {
			try {
				boolean numeroInvalido = true; // Inicializa la variable numeroInvalido
				while (numeroInvalido) {
					System.out.print("Introduzca un código de venta: ");
					int numero = leer.nextInt();
					if (numero >= 0) { // Comprueba que el numero ingresado sea un numero positivo
						numeroInvalido = false;
						while (admin.existeVentaConId(numero)) { // Verifica la existencia de la venta con dicho ID
							System.out.print("Introduzca otro número para esta venta: ");
							numero = leer.nextInt();
						}
						venta.setCodigo(numero); // Carga el ID de la venta
						entradaValida = true;
					} else {
						System.out.println("\nNo se permite que el código de venta sea inferior a 0, introduzca nuevamente\n");
					}				
				}
				do {
					entradaValida = false;
					try {
						System.out.print("Introduzca el número del pedido a retirar: ");
						int numPedido = leer.nextInt();
						entradaValida = true;

						if (admin.existePedido(numPedido) == true) { // Verifica la existencia de un pedido con dicho ID

							int idClientePedido = admin.getClienteEnPedidoById(numPedido); // Obtiene el ID de un cliente de acuerdo al ID del pedido
							venta.setCliente(admin.getClienteById(idClientePedido)); // Carga el cliente por medio de el ID del pedido

							admin.RegistrarVentaConPedido(numPedido, venta); // Registra la venta con pedido
							RegistrarMedioDePago(venta); // Registra el medio de pago
							admin.CancelarPedido(numPedido); // Elimina el pedido para efectualizarlo como una venta
						}
						entradaValida = true;
					} catch (InputMismatchException ex) {
						System.out.println(
								"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el número de pedido\n");
						leer.nextLine();
					}
				} while (!entradaValida);
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el código de venta\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	// Permite registrar una venta SIN pedido
	public static void registrarVentaSinPedido() {
		Venta venta = new Venta();
		Cliente cliente = new Cliente();
		Pedido detalleVenta = new Pedido();
		int stock = 0; // Inicializa la variable stock
		boolean pedir = true; // Inicializa la variable pedir
		boolean verificar = true; // Inicializa la variable verificar
		boolean entradaValida = false; // Inicializa la variable entradaValida
		do {
			try {
				boolean codigoInvalido = true; // Inicializa la variable codigoInvalido
				while (codigoInvalido) { // Verifica que el codigo sea valido, si lo es, corta
					System.out.print("Introduzca un código de la venta: ");
					int numero = leer.nextInt();
					if (numero >= 0) { // Comprueba que el numero sea un numero positivo
						codigoInvalido = false;
						while (admin.existeVentaConId(numero)) { // Verifica que existe una venta con dicho ID
							System.out.print("Introduzca otro número para esta venta: ");
							numero = leer.nextInt();
						}
						venta.setCodigo(numero); // Carga el codigo de la venta
						entradaValida = true;
					} else {
						System.out.println("\nNo se permite que el código de venta sea inferior a 0, introduzca nuevamente\n");
					}
				}
				// Datos del cliente
				do {
					entradaValida = false;
					try {
						int codigoCliente = 0; // Inicializa la variable codigoCliente
						while (true) {
							boolean idClienteInvalido = true;
							while (idClienteInvalido) { // Verifica que el ID del cliente sea valido, si lo es, corta
								System.out.print("Introduzca el ID del cliente: ");
								int clienteId = leer.nextInt();
								if (clienteId >= 0) { // Comprueba que el ID del cliente es un numero positivo
									codigoCliente = clienteId;
									idClienteInvalido = false;
									entradaValida = true;
								} else {
									System.out.println("\nNo se permite que el ID del cliente sea inferior a 0, introduzca nuevamente\n");
								}
							}
							if (admin.existeCliente(codigoCliente)) { // Verifica que existe un cliente con dicho ID
								Cliente clienteVenta = admin.getClienteById(codigoCliente); 
								venta.setCliente(clienteVenta); // Carga el cliente en la venta
								break;
							} else {
								System.out.println("\nDicho cliente NO se encuentra registrado");
								System.out.println("Ingrese los datos del cliente a registrar\n");
								int clienteID = agregarCliente(); 
								venta.setCliente(admin.getClienteById(clienteID)); // Carga el cliente a la venta
								break;
							}
						}

						System.out.print("Introduzca la fecha de la venta: ");
						String fecha = leer.next();
						detalleVenta.setFecha(fecha); // Carga la fecha de la venta

						// Pide "x" veces la cantidad de autopartes necesitadas
						while (pedir) {
							entradaValida = false;
							do {
								try {
									System.out.println("\n-----PEDIDO DE AUTOPARTES-----");
									System.out.print("Introduzca el ID de la autoparte: ");
									int codigo = leer.nextInt();
									verificar = true;
									entradaValida = true;
									// Verifica que existe la autoparte y que el catálogo no esté vacío
									if (admin.existeAutoparte(codigo)) {
										stock = admin.DisponibilidadStock(codigo); // Devuelve el stock disponible de dicha autoparte																					
										if (stock == 0) { // Comprueba que no haya stock disponible
											while (true) {
												try {
												    System.out.print("¿Desea tratar con otra? \n[1] Si \n[2] No \nOPCION: ");
												    int seguir = leer.nextInt();
													if (seguir == 2) { // Corta el bucle si no se desea cargar otra autoparte a la venta
													    return;
													} else if (seguir == 1) {
													    entradaValida = true;
													    break;
													} else {
													    System.out.println("Valor inválido. Intente otra vez.\n");
													}
												} 
												catch (InputMismatchException ex) {
												    System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción\n");
												    leer.nextLine();
												}
											}
											if (entradaValida) {
											    continue;
											}

										} else {
											// En caso de ingresar una cantidad superior al stock permite pedir nuevamente el dato											
											while (verificar) {
												do {
													entradaValida = false;
													try {
														boolean cantInvalida = true;
														while (cantInvalida) {
															System.out.print("\nIntroduzca la cantidad necesitada: ");
															int cantidad = leer.nextInt();
															if (cantidad >= 0) {
																entradaValida = true;
																cantInvalida = false;
																// Verifica que la cantidad requerida sea igual o menor al stock existente																
																if (cantidad <= stock) {
																	admin.ModificarStock(codigo, cantidad, 0); // Reduce el stock de la autoparte																												
																	double precioUnidad = admin.getPrecioAutoparte(codigo);
																	double precioTotal = precioUnidad * cantidad;
																	System.out.print("Precio total: $" + precioTotal);
																	// Agrega el detalle al pedido actual
																	detalleVenta.agregarDetalle(codigo, precioUnidad, cantidad);
																	verificar = false;
																	entradaValida = true;
		
																	do {
																		entradaValida = false;
																		try {
																			System.out.print(
																					"\nDesea cargar más autopartes? [1] Si | [2] No \nOPCION: ");
																			int elegir = leer.nextInt();
																			entradaValida = true;
		
																			if (elegir == 2) {
																				admin.RegistrarVentaSinPedido(detalleVenta,
																						venta); // Carga todos los datos del
																								// cliente incluyendo los
																								// detalles de las autopartes
																				System.out.println(
																						"\nVenta cargada exitosamente!");
																				pedir = false;
																				RegistrarMedioDePago(venta);
																				entradaValida = true;
																			}
																			entradaValida = true;
																		} catch (InputMismatchException ex) {
																			System.out.println(
																					"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción");
																			leer.nextLine();
																		}
																	} while (!entradaValida);
																} else {
																	System.out.println("\nSolo hay " + stock + " unidades en stock, intente nuevamente");
																}
															} else {
																System.out.println("\nNo se permite que la cantidad necesitada sea inferior a 0, introduzca nuevamente");
															}
														}
													}catch (InputMismatchException ex) {
														System.out.println(
																"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la cantidad necesaria");
														leer.nextLine();
													}
												} while (!entradaValida);
											}
										}
									} else { 
										return;
									}
								} catch (InputMismatchException ex) {
									System.out.println(
											"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del autoparte\n");
									leer.nextLine();
								}
							} while (!entradaValida);
						}
					} catch (InputMismatchException ex) {
						System.out.println(
								"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del cliente\n");
						leer.nextLine();
					}
				} while (!entradaValida);
			} catch (InputMismatchException ex) {
				System.out.println(
						"\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el código de venta\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}

	public static void RegistrarMedioDePago(Venta venta) {
	    double totalVenta = venta.getDetalleVenta().getMontoTotal();
	    double montoFinal = 0.0;
	    boolean entradaValida = false;

	    System.out.println("Seleccione el medio de pago:");
	    System.out.println("1. Tarjeta de débito");
	    System.out.println("2. Tarjeta de crédito");
	    System.out.println("3. Efectivo");
	    do {
	        try {
	            System.out.print("Opción: ");
	            int opcionMedioPago = leer.nextInt();
	            leer.nextLine(); // Consumir la nueva línea
	            entradaValida = true;

	            if (opcionMedioPago == 1) {
	                // Si el cliente paga con débito, se cobra el valor total de la venta
	                montoFinal = totalVenta;
	                venta.setMedioDePago("Tarjeta de débito");
	                venta.setCantCuotas(0);
	            } else if (opcionMedioPago == 2) {
	                // Si el cliente paga con tarjeta de crédito, se solicita la cantidad de cuotas
	                venta.setMedioDePago("Tarjeta de crédito");
	                boolean cuotasValida = false;
	                do {
	                    try {
	                        System.out.print("Ingrese la cantidad de cuotas (2, 3 o 6): ");
	                        int cantidadCuotas = leer.nextInt();
	                        if (cantidadCuotas == 2 || cantidadCuotas == 3 || cantidadCuotas == 6) {
	                            leer.nextLine(); // Consumir la nueva línea
	                            cuotasValida = true;

	                            // Si la cantidad de cuotas es 2, 3 o 6, se aplica el recargo correspondiente
	                            if (cantidadCuotas == 2) {
	                                montoFinal = totalVenta * 1.06;
	                                venta.setCantCuotas(2);
	                            } else if (cantidadCuotas == 3) {
	                                montoFinal = totalVenta * 1.12;
	                                venta.setCantCuotas(3);
	                            } else if (cantidadCuotas == 6) {
	                                montoFinal = totalVenta * 1.20;
	                                venta.setCantCuotas(6);
	                            }
	                        } else {
	                            System.out.println("Cantidad de cuotas no válida. Solo se permiten 2, 3 o 6 cuotas.");
	                        }
	                    } catch (InputMismatchException ex) {
	                        System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la cantidad de cuotas\n");
	                        leer.nextLine(); // Consumir la nueva línea en caso de error
	                    }
	                } while (!cuotasValida);
	            } else if (opcionMedioPago == 3) {
	                // Si el cliente paga en efectivo, recibe un descuento del 10% del valor total de la venta
	                montoFinal = totalVenta * 0.9;
	                venta.setMedioDePago("Efectivo");
	                venta.setCantCuotas(0);
	            } else {
	                // Si se proporciona un medio de pago no válido, se informa al usuario
	                System.out.println("Medio de pago no válido. Seleccione una opción válida.");
	                entradaValida = false;
	            }

	            // Establecer el monto final solo si se ha seleccionado una opción válida
	            if (entradaValida) {
	                venta.setMontoFinal(montoFinal);
	                System.out.println("\nMonto a abonar (según el medio de pago seleccionado): $" + montoFinal);
	                System.out.println("Generando factura de venta...\n");
	                admin.GeneradorDeFacturas(venta);
	            }
	        } catch (InputMismatchException ex) {
	            System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción\n");
	            leer.nextLine(); // Consumir la nueva línea en caso de error
	        }
	    } while (!entradaValida);
	}
}