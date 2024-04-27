import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static CarroCompra carroCompra = new CarroCompra(); // Carro de compra global

    public static void main(String[] args) {
        mostrarMenuInicial();
    }

    public static void mostrarMenuInicial() {
        int opcion = -1;
        do {
            try {
                System.out.println("\n--------MENU INICIAL--------");
                System.out.println("1. Introducir producto");
                System.out.println("2. Pasar por caja");
                System.out.println("3. Mostrar carrito de compra");
                System.out.println("4. Buscar producto por código de barras (se mostrará únicamente el nombre del producto)");
                System.out.println("5. Buscar producto por código de barras (se mostrará toda la información del producto)");
                System.out.println("0. Acabar");
                System.out.print("\nElige qué quieres hacer: ");

                opcion = scan.nextInt();
                scan.nextLine(); // Limpiar el búfer

                switch (opcion) {
                    case 1:
                        mostrarMenuIntroducirProducto();
                        break;
                    case 2:
                        carroCompra.actualizarPreciosTextil();
                        carroCompra.pasarPorCaja();
                        break;
                    case 3:
                        carroCompra.mostrarCarro();
                        break;
                    case 4:
                        buscarProductoPorCodigoBarras();
                        break;
                    case 5:
                        buscarProductoConMasInfoPorCodigoBarras();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("¡ATENCIÓN! Debe ser un valor entre 0 y 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, introduce un número entero.");
                scan.nextLine(); // Limpiar el búfer
            }
        } while (opcion != 0);
    }

    public static void mostrarMenuIntroducirProducto() {
        int opcionProducto = -1;
        do {
            try {
                System.out.println("\n--------PRODUCTO--------");
                System.out.println("¿Qué tipo de producto quieres introducir?");
                System.out.println("1. Alimentación");
                System.out.println("2. Textil");
                System.out.println("3. Electrónica");
                System.out.println("0. Volver atrás");
                System.out.print("\nElige qué quieres hacer: ");

                opcionProducto = scan.nextInt();
                scan.nextLine(); // Limpiar el búfer

                switch (opcionProducto) {
                    case 1:
                        añadirProductoAlimentacion();
                        break;
                    case 2:
                        añadirProductoTextil();
                        break;
                    case 3:
                        añadirProductoElectronica();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("¡ATENCIÓN! Debe ser un valor entre 0 y 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, introduce un número entero.");
                scan.nextLine(); // Limpiar el búfer
            }
        } while (opcionProducto != 0);
    }

    private static void añadirProductoAlimentacion() {
        boolean completado = false;
        do {
            System.out.print("Nombre del producto: ");
            String nombre = scan.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("El nombre del producto no puede estar vacío.");
                continue;
            }

            double precio = 0;
            boolean precioValido = false;
            while (!precioValido) {
                try {
                    System.out.print("Precio: ");
                    precio = scan.nextDouble();
                    scan.nextLine(); // Limpiar el búfer
                    if (precio <= 0) {
                        throw new IllegalArgumentException("El precio debe ser un número positivo.");
                    }
                    precioValido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada inválida. Introduce un número válido para el precio.");
                    scan.nextLine(); // Limpiar el búfer
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Código de barras: ");
            String codigoBarras = scan.nextLine();
            if (codigoBarras.isEmpty()) {
                System.out.println("El código de barras no puede estar vacío.");
                continue;
            }


            String fechaCaducidad = "";
            boolean fechaCaducidadValida = false;
            while (!fechaCaducidadValida) {
                System.out.print("Fecha de caducidad (dd/MM/yyyy): ");
                fechaCaducidad = scan.nextLine();
                if (Alimentacion.fechaValida(fechaCaducidad)) {
                    fechaCaducidadValida = true;
                } else {
                    System.out.println("La fecha de caducidad no es válida o es anterior a la fecha actual.");
                }
            }

            try {
                Alimentacion alimentacion = new Alimentacion(nombre, precio, codigoBarras, fechaCaducidad);
                carroCompra.añadirProducto(alimentacion);
                System.out.println("Producto añadido con éxito.");
                completado = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                // No cambio la bandera 'completado', para que el bucle permitirá reintentar
            }
        } while (!completado);
    }

    private static void añadirProductoTextil() {
        boolean completado = false;
        do {
            try {
                System.out.print("Nombre del producto: ");
                String nombre = scan.nextLine();
                if (nombre.isEmpty()) {
                    System.out.println("El nombre del producto no puede estar vacío.");
                    continue;
                }

                double precio = 0;
                boolean precioValido = false;
                while (!precioValido) {
                    try {
                        System.out.print("Precio: ");
                        precio = scan.nextDouble();
                        scan.nextLine(); // Limpiar el búfer
                        if (precio <= 0) {
                            throw new IllegalArgumentException("El precio debe ser un número positivo.");
                        }
                        precioValido = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada inválida. Introduce un número válido para el precio.");
                        scan.nextLine(); // Limpiar el búfer
                    }
                }

                System.out.print("Código de barras: ");
                String codigoBarras = scan.nextLine();
                if (codigoBarras.isEmpty()) {
                    System.out.println("El código de barras no puede estar vacío.");
                    continue;
                }

                System.out.print("Composición textil: ");
                String composicion = scan.nextLine();
                if (composicion.isEmpty() || !composicion.matches("[^\\d]+")) {
                    System.out.println("La composición textil no puede estar vacía ni contener números.");
                    continue;
                }


                Textil textil = new Textil(nombre, precio, codigoBarras, composicion);
                carroCompra.añadirProducto(textil);
                System.out.println("Producto añadido con éxito.");
                completado = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                // No cambio la bandera 'completado', para que el bucle permitirá reintentar
            }
        } while (!completado);
    }

    private static void añadirProductoElectronica() {
        boolean completado = false;
        do {
            try {
                System.out.print("Nombre del producto: ");
                String nombre = scan.nextLine();
                if (nombre.isEmpty()) {
                    System.out.println("El nombre del producto no puede estar vacío.");
                    continue;
                }

                double precio = 0;
                boolean precioValido = false;
                while (!precioValido) {
                    try {
                        System.out.print("Precio: ");
                        precio = scan.nextDouble();
                        scan.nextLine(); // Limpiar el búfer
                        if (precio <= 0) {
                            throw new IllegalArgumentException("El precio debe ser un número positivo.");
                        }
                        precioValido = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada inválida. Introduce un número válido para el precio.");
                        scan.nextLine(); // Limpiar el búfer
                    }
                }

                System.out.print("Código de barras: ");
                String codigoBarras = scan.nextLine();
                if (codigoBarras.isEmpty()) {
                    System.out.println("El código de barras no puede estar vacío.");
                    continue;
                }

                int diasGarantia = -1;
                boolean diasGarantiaValidos = false;
                while (!diasGarantiaValidos) {
                    try {
                        System.out.print("Días de garantía: ");
                        diasGarantia = scan.nextInt();
                        scan.nextLine(); // Limpiar el búfer
                        if (diasGarantia < 0) {
                            throw new IllegalArgumentException("Los días de garantía no pueden ser negativos.");
                        }
                        diasGarantiaValidos = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada inválida. Introduce un número entero válido para los días de garantía.");
                        scan.nextLine(); // Limpiar el búfer
                    }
                }

                Electronica electronica = new Electronica(nombre, precio, codigoBarras, diasGarantia);
                carroCompra.añadirProducto(electronica);
                System.out.println("Producto añadido con éxito.");
                completado = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                // No cambio la bandera 'completado', para que el bucle permitirá reintentar
            }
        } while (!completado);
    }


    private static void buscarProductoPorCodigoBarras() {
        boolean completado = false;
        do {
            System.out.print("Introduce el código de barras del producto: ");
            String codigo = scan.nextLine();

            String nombreProducto = carroCompra.buscarNombrePorCodigoBarras(codigo);
            if (nombreProducto != null && !nombreProducto.isEmpty()) {
                System.out.println("Nombre del producto: " + nombreProducto);
                completado = true;
            } else {
                System.out.println("Producto no encontrado con el código de barras: " + codigo);
                System.out.print("¿Deseas intentar otra búsqueda? (s/n): ");
                if (!scan.nextLine().trim().toLowerCase().startsWith("s")) {
                    completado = true;
                }
            }
        } while (!completado);
    }

    private static void buscarProductoConMasInfoPorCodigoBarras() {
        boolean completado = false;
        do {
            System.out.print("Introduce el código de barras del producto: ");
            String codigo = scan.nextLine();

            ProductoCarrito productoCarrito = carroCompra.buscarProductoPorCodigo(codigo);
            if (productoCarrito != null) {
                // Extracción de información del producto y su presentación
                presentarInformacionProducto(productoCarrito);
                completado = true;
            } else {
                System.out.println("Producto no encontrado con el código de barras: " + codigo);
                System.out.print("¿Deseas intentar otra búsqueda? (s/n): ");
                if (!scan.nextLine().trim().toLowerCase().startsWith("s")) {
                    completado = true;
                }
            }
        } while (!completado);
    }

    private static void presentarInformacionProducto(ProductoCarrito productoCarrito) {
        Producto producto = productoCarrito.getProducto();
        int cantidad = productoCarrito.getCantidad();
        double precioUnitario = producto.calcularPrecio();
        double precioTotal = precioUnitario * cantidad;

        String tipoProducto = CarroCompra.obtenerTipoProducto(producto);
        String infoEspecifica = "";

        if (producto instanceof Textil) {
            infoEspecifica = "Composición textil: " + ((Textil) producto).getComposicion();
        } else if (producto instanceof Electronica) {
            infoEspecifica = "Días de garantía: " + ((Electronica) producto).getDiasGarantia();
        } else if (producto instanceof Alimentacion) {
            infoEspecifica = "Días hasta caducidad: " + ((Alimentacion) producto).diasHastaCaducidad();
        }

        System.out.println("Nombre del producto: " + producto.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Tipo de producto: " + tipoProducto);
        System.out.println("Precio unitario: " + CarroCompra.decimalFormat.format(precioUnitario));
        System.out.println("Precio total: " + CarroCompra.decimalFormat.format(precioTotal));
        System.out.println(infoEspecifica);
    }



}
