import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;

public class CarroCompra {
    private Map<String, ProductoCarrito> productos;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00'€'");

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
}
