import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

public class CarroCompra {
    private Map<String, ProductoCarrito> productos;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00'€'");

    public CarroCompra() {
        this.productos = new LinkedHashMap<>();
    }

    public void añadirProducto(Producto producto) {
        String clave = producto.getCodigoBarras();
        // Si el producte és Textil, comprovar si ja existeix un amb el mateix codi de barres.
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

        // Crear una llista ordenada si es necessari.
        List<ProductoCarrito> listaOrdenada = new ArrayList<>(productos.values());
        // Ordenar si són productes textils
        listaOrdenada.sort(Comparator.comparing((ProductoCarrito pc) -> {
            Producto p = pc.getProducto();
            return (p instanceof Textil) ? ((Textil) p).getComposicion() : "";
        }));

        // Utilitzar la llista ordenada per a mostrar els productes i calcular el total
        listaOrdenada.forEach(productoCarrito -> {
            Producto p = productoCarrito.getProducto();
            int cantidad = productoCarrito.getCantidad();
            double precioUnitario = p.calcularPrecio();
            double precioTotal = precioUnitario * cantidad;

            System.out.println(p.getNombre() + " - " + cantidad + " unitat(s) - Preu unitari: " + decimalFormat.format(precioUnitario) + " - Preu total: " + decimalFormat.format(precioTotal));
            total[0] += precioTotal;
        });

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
}
