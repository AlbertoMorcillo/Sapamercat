public class ProductoCarrito {
    private Producto producto;
    private int cantidad;

    public ProductoCarrito(Producto producto) {
        this.producto = producto;
        this.cantidad = 1; // Comenzamos con una cantidad de 1
    }

    public void incrementarCantidad() {
        this.cantidad++;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() {
        return cantidad * producto.calcularPrecio();
    }
}
