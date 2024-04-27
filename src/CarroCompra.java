import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class CarroCompra {
    // El map se utiliza para almacenar los productos en el carro, con el código de barras como clave
    private Map<String, ProductoCarrito> productos;
    public static final DecimalFormat decimalFormat = new DecimalFormat("0.00'€'");
    private static final Path updatesPath = Paths.get(".\\updates");
    private static final Path logsPath = Paths.get(".\\logs");

    // Bloque estático para crear los directorios de actualizaciones y logs si no existen
    static {
        try {
            if (!Files.exists(updatesPath)) {
                Files.createDirectories(updatesPath);
            }
            if (!Files.exists(logsPath)) {
                Files.createDirectories(logsPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logException(e);
        } catch (Exception e) {
            System.out.println("Ocurrió un error genérico.");
            logException(e); // Esto también registrará la excepción
        }
    }

    public CarroCompra() {
        this.productos = new LinkedHashMap<>();
    }

    public void añadirProducto(Producto producto) {
        String clave = producto.getCodigoBarras();
        if (producto instanceof Textil && productos.containsKey(clave)) {
            System.out.println("Ja existeix un producte textil amb aquest codi de barres en el carro.");
            return;
        }
        if (productos.size() < 100) {
            productos.put(clave, new ProductoCarrito(producto));
        } else {
            System.out.println("No es pot afegir més productes. El carro està ple.");
        }
    }

    public void pasarPorCaja() {
        if (productos.isEmpty()) {
            System.out.println("El carro està buit.");
            return;
        }

        System.out.println("SAPAMERCAT");
        System.out.println("Data: " + LocalDate.now());
        final double[] total = {0};

        // Obtener la lista de productos del carro
        List<ProductoCarrito> listaOrdenada = new ArrayList<>(productos.values());

        // Ordenar la lista de productos utilizando ProductoComparator
        Collections.sort(listaOrdenada, new Comparator<ProductoCarrito>() {
            @Override
            public int compare(ProductoCarrito o1, ProductoCarrito o2) {
                Producto p1 = o1.getProducto();
                Producto p2 = o2.getProducto();
                // Si ambos productos son de tipo Textil, delegamos la comparación al método compareTo de Textil
                if (p1 instanceof Textil && p2 instanceof Textil) {
                    return ((Textil) p1).compareTo((Textil) p2);
                }
                // En caso contrario, utilizamos el ProductoComparator para otros tipos de productos
                return new ProductoComparator().compare(p1, p2);
            }
        });


        // Utilizar la lista ordenada para mostrar los productos y calcular el total.
        for (ProductoCarrito productoCarrito : listaOrdenada) {
            Producto p = productoCarrito.getProducto();
            int cantidad = productoCarrito.getCantidad();
            double precioUnitario = p.calcularPrecio();
            double precioTotal = precioUnitario * cantidad;

            System.out.println(p.getNombre() + " - " + cantidad + " unitat(s) - Preu unitari: " + decimalFormat.format(precioUnitario) + " - Preu total: " + decimalFormat.format(precioTotal));
            total[0] += precioTotal;
        }

        System.out.println("Total a pagar: " + decimalFormat.format(total[0]));
        vaciarCarro();
    }

    public void mostrarCarro() {
        if (productos.isEmpty()) {
            System.out.println("El carro està buit.");
            return;
        }

        productos.values().forEach(productoCarrito -> {
            System.out.println(productoCarrito.getProducto().getNombre() + " - " + productoCarrito.getCantidad() + " unitat(s)");
        });
    }

    private void vaciarCarro() {
        productos.clear();
    }

    // Clase interna para la comparación de productos
    class ProductoComparator implements Comparator<Producto> {
        @Override
        public int compare(Producto p1, Producto p2) {
            int precioCompare = Double.compare(p1.getPrecio(), p2.getPrecio());
            if (precioCompare != 0) {
                return precioCompare;
            }
            return p1.getNombre().compareTo(p2.getNombre());
        }
    }

    public String buscarNombrePorCodigoBarras(String codigoBarras) {
        return productos.entrySet().stream()  // Convertimos el conjunto de entradas del mapa a un stream
                .filter(entry -> entry.getKey().equals(codigoBarras))  // Filtramos por el código de barras
                .map(entry -> entry.getValue().getProducto().getNombre())  // Extraemos el nombre del producto
                .findFirst()  // Encontramos el primer (y supuestamente único) nombre que coincida
                .orElse("Producto no encontrado");  // Si no hay coincidencia, retornamos este mensaje
    }

    public static String obtenerTipoProducto(Producto producto) {
        if (producto instanceof Alimentacion) {
            return "Alimentación";
        } else if (producto instanceof Textil) {
            return "Textil";
        } else if (producto instanceof Electronica) {
            return "Electrónica";
        } else {
            return "Desconocido";
        }
    }

    public ProductoCarrito buscarProductoPorCodigo(String codigoBarras) {
        return productos.entrySet().stream()
                .filter(entry -> entry.getKey().equals(codigoBarras))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    // Método para actualizar los precios de los productos textiles.
    public void actualizarPreciosTextil() {
        Path path = updatesPath.resolve("UpdateTextilPrices.dat");
        if (!Files.exists(path)) {
            System.out.println("El archivo de actualización de precios no existe.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Formato de línea incorrecto: " + line);
                    continue;
                }

                String codigoBarras = parts[0];
                double nuevoPrecio;
                try {
                    nuevoPrecio = Double.parseDouble(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Precio no válido en la línea: " + line);
                    logException(e);
                    continue;
                }

                ProductoCarrito productoCarrito = buscarProductoPorCodigo(codigoBarras);
                if (productoCarrito != null && productoCarrito.getProducto() instanceof Textil) {
                    System.out.println("Actualizando precio para el código de producto: " + codigoBarras + " de " + productoCarrito.getProducto().getPrecio() + " a " + nuevoPrecio);
                    productoCarrito.getProducto().setPrecio(nuevoPrecio);
                } else {
                    System.out.println("No se encontró producto Textil con el código: " + codigoBarras);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de actualización de precios.");
            logException(e);
        } catch (Exception e) {
        System.out.println("Ocurrió un error genérico.");
        logException(e); // Esto también registrará la excepción
        }
    }

    // Método para registrar excepciones en un archivo de log
    public static void logException(Exception e) {
        Path logFile = logsPath.resolve("Excepcions.dat");
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(logFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            writer.println("Exception occurred: " + e.getMessage());
            e.printStackTrace(writer);
        } catch (IOException ex) {
            System.out.println("No se pudo escribir en el archivo de log.");
            ex.printStackTrace();
        }
    }
}

