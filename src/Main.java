import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static CarroCompra carroCompra = new CarroCompra(); // Carro de compra global

    public static void main(String[] args) {
        mostrarMenuInicial();
    }

    public static void mostrarMenuInicial() {
        int opcion;
        do {
            System.out.println("\n--------MENU INICIAL--------");
            System.out.println("1. Introduir producte");
            System.out.println("2. Passar per caixa");
            System.out.println("3. Mostrar carrito de compra");
            System.out.println("4. Buscar producte per codi de barres(se mostrará unicamente el nombre del producto)");
            System.out.println("5. Buscar producte per codi de barres(se mostrará toda la información del producto)");
            System.out.println("0. Acabar");
            System.out.print("\nElije qué quieres hacer: ");

            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    mostrarMenuIntroducirProducto();
                    break;
                case 2:
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
                    System.out.println("Sortint de l'aplicació...");
                    break;
                default:
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 4");
            }
        } while (opcion != 0);
    }

    public static void mostrarMenuIntroducirProducto() {
        int opcionProducto;
        do {
            System.out.println("\n--------PRODUCTE--------");
            System.out.println("Quin tipus de producte vols introduir?");
            System.out.println("1. Alimentació");
            System.out.println("2. Tèxtil");
            System.out.println("3. Electrònica");
            System.out.println("0. Tornar enrere");
            System.out.print("\nElije qué quieres hacer: ");

            opcionProducto = scan.nextInt();
            scan.nextLine();

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
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 3");
            }
        } while (opcionProducto != 0);
    }

    private static void añadirProductoAlimentacion() {
        System.out.print("Nom del producte: ");
        String nombre = scan.nextLine();
        System.out.print("Preu: ");
        double precio = scan.nextDouble();
        scan.nextLine();
        System.out.print("Codi de barres: ");
        String codigo = "A" + scan.nextLine();
        System.out.print("Data de caducitat (dd/MM/yyyy): ");
        String dataCaducitat = scan.nextLine();

        Alimentacion alimentacion = new Alimentacion(nombre, precio, codigo, dataCaducitat);
        carroCompra.añadirProducto(alimentacion);
        System.out.println("Producte afegit amb èxit.");
    }

    private static void añadirProductoTextil() {
        System.out.print("Nom del producte: ");
        String nombre = scan.nextLine();
        System.out.print("Preu: ");
        double precio = scan.nextDouble();
        scan.nextLine();
        System.out.print("Codi de barres: ");
        String codigo = "T" + scan.nextLine();
        System.out.print("Composició tèxtil: ");
        String composicion = scan.nextLine();

        Textil textil = new Textil(nombre, precio, codigo, composicion);
        carroCompra.añadirProducto(textil);
        System.out.println("Producte afegit amb èxit.");
    }

    private static void añadirProductoElectronica() {
        System.out.print("Nom del producte: ");
        String nombre = scan.nextLine();
        System.out.print("Preu: ");
        double precio = scan.nextDouble();
        scan.nextLine();
        System.out.print("Codi de barres: ");
        String codigo = "E" + scan.nextLine();
        System.out.print("Dies de garantia: ");
        int diasGarantia = scan.nextInt();
        scan.nextLine();

        Electronica electronica = new Electronica(nombre, precio, codigo, diasGarantia);
        carroCompra.añadirProducto(electronica);
        System.out.println("Producte afegit amb èxit.");
    }

    private static void buscarProductoPorCodigoBarras() {
        System.out.print("Introduce el código de barras del producto (Recuerda que tienes que poner al inicio la letra A si es alimentación, T si es textil y E es Electronica): ");
        String codigo = scan.nextLine();
        String nombreProducto = carroCompra.buscarNombrePorCodigoBarras(codigo);
        System.out.println("Nombre del producto: " + nombreProducto);
    }
    private static void buscarProductoConMasInfoPorCodigoBarras() {
        System.out.print("Introduce el código de barras del producto: ");
        String codigo = scan.nextLine();
        ProductoCarrito productoCarrito = carroCompra.buscarProductoPorCodigo(codigo);

        if (productoCarrito != null) {
            Producto producto = productoCarrito.getProducto();
            int cantidad = productoCarrito.getCantidad();
            double precioUnitario = producto.calcularPrecio();
            double precioTotal = precioUnitario * cantidad;

            // Asumiendo que tienes un método que te dice el tipo de producto.
            String tipoProducto = CarroCompra.obtenerTipoProducto(producto);

            System.out.println("Nombre del producto: " + producto.getNombre());
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Tipo de producto: " + tipoProducto);
            System.out.println("Precio unitario: " + CarroCompra.decimalFormat.format(precioUnitario));
            System.out.println("Precio total: " + CarroCompra.decimalFormat.format(precioTotal));
        } else {
            System.out.println("Producto no encontrado con el código de barras: " + codigo);
        }
    }

}
