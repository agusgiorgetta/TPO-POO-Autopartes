package empresa;
import negocio.*;
import java.util.InputMismatchException;
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
			String usuario = leer.nextLine();
			System.out.print("CONTRASEÑA: ");
			String contrasena = leer.nextLine();
			
			if(admin.IniciarSesion(usuario, contrasena) == true && contador != 0) {
				
				while(verificador) {
					
					try {
						
						System.out.println("\n--------------------------- MENÚ ---------------------------");
						System.out.print("[1] Agregar autoparte \n[2] Listar autopartes \n[3] Eliminar autoparte del catálogo \n[4] Modificar stock autoparte \n[5] Modificar catálogo \n[6] Disponibilidad stock de una autoparte \n[7] Agregar cliente \n[8] Cargar pedido \n[9] Cancelar pedido \n[10] Registrar venta con pedido \n[11] Registrar venta sin pedido \n[0] Cerrar Sesión \n\nOPCION: ");
						
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
							agregarCliente();
						}else if(opcion == 8) {
							cargarPedido();
						}else if(opcion == 9) {
							cancelarPedido();
						}else if(opcion == 10) {
							registrarVentaConPedido();
						}else if(opcion == 11) {
							registrarVentaSinPedido();
						}else if(opcion == 0) {
							if(admin.CerrarSesion() == true) {
								System.out.println("Se ha finalizado la sesión exitosamente!\n");
								System.out.println("-------------------- TUTTA LA MACCHINA ----------------------");
								validar = false;
								leer.close();
								break;
							}
						}
					}catch (InputMismatchException ex) {
							System.out.println("\nERROR --> Solo se permite el ingreso de números, introduzca nuevamente la opción");
							leer.nextLine();
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
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el código: ");
				int codigo = leer.nextInt();
				autoparte.setCodigo(codigo);
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el código del autoparte\n");
				leer.nextLine();
			}
		} while(!entradaValida);
		
		entradaValida = false;
		
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
		
		do {
			try {
				System.out.print("Introduzca el precio: ");
				double precio = leer.nextDouble();
				autoparte.setPrecio(precio);
				entradaValida = true;
			} catch(InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números, introduzca nuevamente el precio\n");
				leer.nextLine();
			}
		} while(!entradaValida);
		
		entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el stock: ");
				int stock = leer.nextInt();
				autoparte.setCantStock(stock);
				entradaValida = true;
			} catch(InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock\n");
				leer.nextLine();			
			}
		} while(!entradaValida);

		entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el stock minimo: ");
				int stockMinimo = leer.nextInt();
				autoparte.setStockMinimo(stockMinimo);
				entradaValida = true;
			} catch(InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock mínimo\n");
				leer.nextLine();					
			}
		} while(!entradaValida);
		
		System.out.print("Introduzca el enlace: ");
		String enlace = leer.next();
		autoparte.setEnlace(enlace);
		
		admin.CargarAutoparte(autoparte);
		System.out.println("\nAutoparte cargada exitosamente!");
	}
	
	public static void mostrarCatalogo() {
		admin.ListarCatalogo();
	}
	
	public static void eliminarAutoparte() {
		
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el código del autoparte: ");
				int codigo = leer.nextInt();
				entradaValida = true;		
				if(admin.existeAutoparte(codigo) == true) {
					admin.EliminarDelCatalogo(codigo);
				}
			} catch(InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, ingrese nuevamente el código del autoparte\n");
				leer.nextLine();
			} 
		}while(!entradaValida);
	}
	
	public static void modificarStock() {
		
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el código del autoparte para modificar su stock: ");
				int codigo = leer.nextInt();
				entradaValida = true;
				if(admin.existeAutoparte(codigo) == true) {
					entradaValida = false;
					do {
						try {
							System.out.print("OPCIONES: \n[1] Sumar unidades al stock actual \n[2] Reemplazar el valor del stock \nOPCION: ");
							int opcion = leer.nextInt();
							entradaValida = true;
							do {
								entradaValida = false;
								try {
									System.out.print("Introduzca el nuevo stock: ");
									int stock = leer.nextInt();
									admin.ModificarStock(codigo, stock, opcion);
									System.out.println("Stock modificado exitosamente!");
									entradaValida = true;
								} catch (InputMismatchException ex) {
									System.out.println("\nERROR --> Solo se aceptan números enteros, ingrese nuevamente el stock\n");
									leer.nextLine();									
								} 
							} while(!entradaValida);
						} catch (InputMismatchException ex) {
							System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción\n");
							leer.nextLine();
						}
					} while(!entradaValida);
				}
			} catch(InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el stock\n");
				leer.nextLine();				
			} 
		}while(!entradaValida);
	}
	
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
						if(admin.existeAutoparte(codigo) == true) {
							System.out.println("Que desea modificar del autoparte?");
							System.out.print("OPCIONES: \n[1] Código \n[2] Denominación \n[3] Descripción \n[4] Categoría \n[5] Marca \n[6] Modelo \n[7] Precio \n[8] Stock Mínimo \n[9] Enlace \n[10] Stock \n[0] Salir \nOPCION: ");
							int opcion = leer.nextInt();
							entradaValida = true;
						
							admin.ModificarCatalogo(codigo, opcion);
						}entradaValida = true;
					} catch (InputMismatchException ex) {
						System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opcion\n");
						leer.nextLine();	
					}
				} while (!entradaValida);
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el codigo del autoparte\n");
				leer.nextLine();						
			} 
		}while(!entradaValida);
	}
	
	public static void stockDisponible() {
		
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el código del autoparte para averiguar su stock disponible: ");
				int codigo = leer.nextInt();
				
				entradaValida = true;
				
				if(admin.existeAutoparte(codigo) == true) {
					admin.DisponibilidadStock(codigo);
				}
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el codigo del autoparte\n");
				leer.nextLine();	
			}
		} while (!entradaValida);
	}
	
	public static void agregarCliente() {
		
		Cliente nuevoCliente = new Cliente();
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el ID del cliente: ");
				int id = leer.nextInt();
				leer.nextLine();
				
				entradaValida = true;
				
				if(!admin.existeCliente(id)) {
					
					nuevoCliente.setCodigo(id);
					
					System.out.print("Introduzca el nombre del cliente: ");
					nuevoCliente.setNombre(leer.nextLine());
					
					System.out.print("Introduzca la dirección del cliente: ");
					nuevoCliente.setDireccion(leer.nextLine());
					
					System.out.print("Introduzca la localidad del cliente: ");
					nuevoCliente.setLocalidad(leer.nextLine());
					
					System.out.print("Introduzca la provincia del cliente: ");
					nuevoCliente.setProvincia(leer.nextLine());
					
					System.out.print("Introduzca el correo del cliente (ej. nombre@dom.ext): ");
					nuevoCliente.setCorreo(leer.nextLine());
					
					System.out.print("Introduzca el teléfono del cliente: ");
					nuevoCliente.setTelefono(leer.nextLine());
					
					admin.agregarCliente(nuevoCliente);
					
					System.out.println("\nCliente agregado exitosamente!");
				}
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del cliente\n");
				leer.nextLine();					
			} 
		} while (!entradaValida);
	}
	
	public static void cargarPedido() {
		
	    Pedido pedido = new Pedido(); // Crear un nuevo pedido
	    boolean entradaValida = false;
	    
	    do {
	    	try {
			    if(admin.catalogoVacio() == false) {
				    System.out.print("Introduzca el número del pedido: ");
				    int numero = leer.nextInt();
				    pedido.setIdPedido(numero);
				    
				    entradaValida = true;
				    
				    System.out.print("Introduzca la fecha del pedido: ");
				    String fecha = leer.next();
				    pedido.setFecha(fecha);
			
				    leer.nextLine(); // Consumir la nueva línea
				    
				    do {
				    	entradaValida = false;
				    	try {
					    	while(true) {
					    	
						    System.out.print("Introduzca el ID del cliente: ");
						    int cliente = leer.nextInt();
						    entradaValida = true;
						    
						    if(admin.existeCliente(cliente)) {
						    	pedido.setCliente(cliente);
						    	break;
						    }else {
						    	System.out.println("\nDicho cliente NO se encuentra registrado");
						    	System.out.println("Ingrese los datos del cliente a registrar\n");
						    	agregarCliente();
						    	pedido.setCliente(cliente);
						    	break;
						    	}
					    	}
				    	} catch (InputMismatchException ex) {
							System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del cliente\n");
							leer.nextLine();		
				    	}
				    } while (!entradaValida);
				    
				    boolean pedir = true;
				    entradaValida = false;
			
				    do {
				    	try {
						    while (pedir) {
						        System.out.print("Introduzca el ID de la autoparte: ");
						        int id = leer.nextInt();
						        entradaValida = true;
					
						        if (admin.existeAutoparte(id)) {
						            int stock = admin.DisponibilidadStock(id);
					
						            if (stock == 0) {
						                System.out.println("No hay stock disponible para esta autoparte.");
						                continue;
						            }
						            do {
						            	entradaValida = false;
						            	try {
								            System.out.print("\nIntroduzca la cantidad necesitada: ");
								            int cantidad = leer.nextInt();
								            entradaValida = true;
							
								            if (cantidad <= stock) {
												double precioUnidad = admin.getPrecioAutoparte(id);
												double precioTotal = precioUnidad * cantidad;
												System.out.print("Precio total: $" + precioTotal);
								                // Agregar el detalle al pedido actual
								                pedido.agregarDetalle(id, precioUnidad, cantidad);
								                admin.ModificarStock(id, cantidad, 0); // Reducir el stock de la autoparte
								                do {
								                	entradaValida = false;
								                	try {
										                System.out.print("Desea cargar más autopartes al pedido? [1] Si | [2] No \nOPCION: ");
										                int elegir = leer.nextInt();
										                entradaValida = true;
										                
										                if (elegir == 2) {
										                    admin.CargarPedido(pedido); // Agregar el pedido completo a la lista de pedidos
										                    pedir = false;
										                } 
										                entradaValida = true;
								                	} catch (InputMismatchException ex) {
														System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opcion\n");
														leer.nextLine();
								                	}
								                } while (!entradaValida);

								            } 
								            else {
								                System.out.println("\nSolo hay " + stock + " unidades en stock, intente nuevamente");
								                entradaValida = true;
								            }						            		
						            	} catch (InputMismatchException ex) {
											System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la cantidad necesitada");
											leer.nextLine();
						            	}
						            } while (!entradaValida);
						        }
						        entradaValida = true;
						    }				    		
				    	} catch (InputMismatchException ex) {
							System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del autoparte\n");
							leer.nextLine();		
				    	}
				    } while (!entradaValida);	 
			    } 
			    entradaValida = true;
	    	} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el número del pedido\n");
				leer.nextLine();		
	    	} 
	    } while (!entradaValida);
	}
	
	public static void cancelarPedido() {
		
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca el número de pedido a cancelar: ");
				int numero = leer.nextInt();
				admin.CancelarPedido(numero);	
				entradaValida = true;
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el número del pedido\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}	
	
	public static void registrarVentaConPedido() {
		Venta venta = new Venta();
		Cliente cliente = new Cliente();
		boolean entradaValida = false;
		
		do {
			try {
				System.out.print("Introduzca un código de venta: ");
				int numero = leer.nextInt();
				venta.setCodigo(numero);
				entradaValida = true;
				
				do {
					entradaValida = false;
					try {
						System.out.print("Introduzca el número del pedido a retirar: ");
						int numPedido = leer.nextInt();
						entradaValida = true;
						
						if(admin.existePedido(numPedido) == true) {
							
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
							
							System.out.print("Introduzca el teléfono del cliente: ");
							cliente.setTelefono(leer.nextLine());
							
							venta.setCliente(cliente);
							
							admin.RegistrarVentaConPedido(numPedido, venta);
							RegistrarMedioDePago(venta);
						}
						entradaValida = true;
					} catch(InputMismatchException ex) {
						System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el número de pedido\n");
						leer.nextLine();
					}
				} while (!entradaValida);
			} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el código de venta\n");
				leer.nextLine();
			}
		} while (!entradaValida);
	}
	
	public static void registrarVentaSinPedido() {
	    Venta venta = new Venta();
	    Cliente cliente = new Cliente();
	    Pedido detalleVenta = new Pedido();
	    int stock = 0;
	    boolean pedir = true;
	    boolean verificar = true;
	    boolean entradaValida = false;
	    
	    do {
	    	try {
			    System.out.print("Introduzca el código de la venta: ");
			    int numero = leer.nextInt();
			    venta.setCodigo(numero);
			    entradaValida = true;
			    
			    // Datos del cliente
			    do {
			    	entradaValida = false;
			    	try {
					    System.out.print("Introduzca el ID del cliente: ");
					    int id = leer.nextInt();
					    cliente.setCodigo(id);
					    entradaValida = true;
					    
					    leer.nextLine(); // Consumir la nueva línea
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
					    
					    System.out.print("Introduzca el teléfono del cliente: ");
					    cliente.setTelefono(leer.nextLine());
					    
					    venta.setCliente(cliente);
				
					    System.out.print("Introduzca la fecha de la venta: ");
					    String fecha = leer.next();
					    detalleVenta.setFecha(fecha);
					    
					    // Pide "x" veces la cantidad de autopartes necesitadas
					    while (pedir) {
					    	entradaValida = false;
					    	do {
					    		try {
							        System.out.print("Introduzca el ID de la autoparte: ");
							        int codigo = leer.nextInt();
							        verificar = true;
							        entradaValida = true;
							        // Verifica que existe la autoparte y que el catálogo no esté vacío
							        if (admin.existeAutoparte(codigo)) {
							            stock = admin.DisponibilidadStock(codigo); // Devuelve el stock disponible de dicha autoparte
							            if (stock == 0) {
							                return;
							            } else {
							                // En caso de ingresar una cantidad superior al stock permite pedir nuevamente el dato sin la 
							                // necesidad de realizar nuevamente el proceso
							                while (verificar) {
							                	do {
							                		entradaValida = false;
							                		try {
									                    System.out.print("\nIntroduzca la cantidad necesitada: ");
									                    int cantidad = leer.nextInt();
									                    entradaValida = true;
									                    // Verifica que la cantidad requerida sea igual o menor al stock existente
									                    if (cantidad <= stock) {
									                    	admin.ModificarStock(codigo, cantidad, 0); // Reducir el stock de la autoparte
									    					double precioUnidad = admin.getPrecioAutoparte(codigo);
															double precioTotal = precioUnidad * cantidad;
															System.out.print("Precio total: $" + precioTotal);
									    	                // Agregar el detalle al pedido actual
									    	                detalleVenta.agregarDetalle(codigo, precioUnidad, cantidad);
									                        verificar = false;
									                        entradaValida = true;
									                        
									                        do {
									                        	entradaValida = false;
									                        	try {
									                        		System.out.print("\nDesea cargar más autopartes? [1] Si | [2] No \nOPCION: ");
											                        int elegir = leer.nextInt();
											                        entradaValida = true;
											                        
											                        if (elegir == 2) {
											                            admin.RegistrarVentaSinPedido(detalleVenta, venta); // Carga todos los datos del cliente incluyendo los detalles de las autopartes
											                            System.out.println("\nVenta cargada exitosamente!");
											                            pedir = false;
											                            RegistrarMedioDePago(venta);
											                            entradaValida = true;
											                        }
											                        entradaValida = true;
									                        	} catch (InputMismatchException ex) {
									                				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción");
									                				leer.nextLine();
									                        	}
									                        } while (!entradaValida);
									                    } else {
									                        System.out.println("\nSolo hay " + stock + " unidades en stock, intente nuevamente");
									                    }					                			
							                		} catch (InputMismatchException ex) {
							            				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la cantidad necesaria");
							            				leer.nextLine();
							                		}
							                	} while (!entradaValida);
							                }
							            }
							        } else { // Si la autoparte no existe, informa al usuario
							            System.out.println("La autoparte no existe.");
							            return;
							        }			    			
					    		} catch (InputMismatchException ex) {
									System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del autoparte\n");
									leer.nextLine();
					    		}
					    	} while (!entradaValida);
					    }	    		
			    	} catch (InputMismatchException ex) {
						System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el ID del cliente\n");
						leer.nextLine();
			    	}
			    } while (!entradaValida);
	    	} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente el código de venta\n");
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
		
		        switch (opcionMedioPago) {
		            case 1:
		                // Si el cliente paga con débito, se cobra el valor total de la venta
		                montoFinal = totalVenta;
		                venta.setMedioDePago("Tarjeta de débito");
		                venta.setCantCuotas(0);
		                break;
		            case 2:
		                // Si el cliente paga con tarjeta de crédito, se solicita la cantidad de cuotas
		            	venta.setMedioDePago("Tarjeta de crédito");
		            	entradaValida = false;
		            	do {
		            		try {
				                System.out.print("Ingrese la cantidad de cuotas (2, 3 o 6):");
				                int cantidadCuotas = leer.nextInt();
				                leer.nextLine(); // Consumir la nueva línea
				                entradaValida = true;
				                
				                // Si la cantidad de cuotas es 2, 3 o 6, se aplica el recargo correspondiente
				                switch (cantidadCuotas) {
				                    case 2:
				                        montoFinal = totalVenta * 1.06;
				                        venta.setCantCuotas(2);
				                        break;
				                    case 3:
				                        montoFinal = totalVenta * 1.12;
				                        venta.setCantCuotas(3);
				                        break;
				                    case 6:
				                        montoFinal = totalVenta * 1.20;
				                        venta.setCantCuotas(6);
				                        break;
				                    default:
				                        System.out.println("Cantidad de cuotas no válida. Solo se permiten 2, 3 o 6 cuotas.");
				                        return;
				                }
				                break;		            			
		            		} catch (InputMismatchException ex) {
		        				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la cantidad de cuotas\n");
		        				leer.nextLine();
		            		}
		            	} while (!entradaValida);

		            case 3:
		                // Si el cliente paga en efectivo, recibe un descuento del 10% del valor total de la venta
		                montoFinal = totalVenta * 0.9;
		                venta.setMedioDePago("Efectivo");
		                venta.setCantCuotas(0);
		                break;
		            default:
		                // Si se proporciona un medio de pago no válido, se informa al usuario
		                System.out.println("Medio de pago no válido. Seleccione una opción válida.");
		                return;
		        }
		        venta.setMontoFinal(montoFinal);
		        
		        System.out.println("\nMonto a abonar (según el medio de pago seleccionado): $" + montoFinal);
		        System.out.println("Generando factura de venta...\n");
		        admin.GeneradorDeFacturas(venta);        		
        	} catch (InputMismatchException ex) {
				System.out.println("\nERROR --> Solo se aceptan números enteros, introduzca nuevamente la opción\n");
				leer.nextLine();
        	}
        } while (!entradaValida);
    }
}