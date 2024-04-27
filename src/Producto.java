public abstract class Producto {
    private String nombre;
    private double precio;
    private String codigoBarras;

    // Constructor
    public Producto(String nombre, double precio, String codigoBarras) {
        this.nombre = nombre;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCodigoBarras() { return codigoBarras; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser un número positivo.");
        }
        if (String.valueOf(precio).length() > 15) {
            throw new IllegalArgumentException("El nombre del producto no puede exceder los 15 caracteres.");
        }
        this.precio = precio;
    }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    // Mètode abstracte que cada subclasse implementarà
    public abstract double calcularPrecio();
}
