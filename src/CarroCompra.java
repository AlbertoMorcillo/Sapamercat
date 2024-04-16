import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarroCompra {
    //Usamos Map en lugar de List para poder acceder a los productos a través de su código de barras. Ya que cada producto es único.
    private Map<String, ProductoCarrito> productos;

    public CarroCompra() {
        this.productos = new LinkedHashMap<>();
    }

    public void añadirProducto(Producto producto) {
        // Utiliza el código de barras como a clave para identificar productos unicos
        String clave = producto.getCodigoBarras();
        if (productos.containsKey(clave)) {
            productos.get(clave).incrementarCantidad();
        } else {
            if (productos.size() < 100) {
                productos.put(clave, new ProductoCarrito(producto));
            } else {
                System.out.println("No es pot afegir més productes. El carro està ple.");
            }
        }
    }

    public void pasarPorCaja() {
        if (productos.isEmpty()) {
            System.out.println("El carro està buit.");
            return;
        }

        // Mostra capçalera del tiquet
        System.out.println("SAPAMERCAT");
        System.out.println("Data: " + LocalDate.now());
        double total = 0;

        // Mostra el detall dels productes
        for (ProductoCarrito item : productos.values()) {
            Producto p = item.getProducto();
            int cantidad = item.getCantidad();
            double precio = p.calcularPrecio();
            double precioTotal = precio * cantidad;
            System.out.println(p.getNombre() + " - " + cantidad + " unitat(s) - Preu unitari: " + precio + " - Preu total: " + precioTotal);
            total += precioTotal;
        }
        // Mostra total del tiquet
        System.out.println("Total a pagar: " + total);

        // Buida el carretó
        vaciarCarro();
    }

    public void mostrarCarro() {
        if (productos.isEmpty()) {
            System.out.println("El carro està buit.");
            return;
        }

        // Mostra només noms i quantitats
        for (ProductoCarrito item : productos.values()) {
            System.out.println(item.getProducto().getNombre() + " - " + item.getCantidad() + " unitat(s)");
        }
    }

    private void vaciarCarro() {
        productos.clear();
    }
}
