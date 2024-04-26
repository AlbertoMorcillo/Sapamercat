public class Textil extends Producto implements Comparable<Textil> {
    private String composicion;

    public Textil(String nombre, double precio, String codigoBarras, String composicion) {
        super(nombre, precio, codigoBarras);
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("Precio debe ser un número positivo.");
        }
        if (codigoBarras == null || codigoBarras.isEmpty()) {
            throw new IllegalArgumentException("Código de barras no puede ser nulo o vacío.");
        }
        if (composicion == null || !composicion.matches("[^\\d]+")) {
            throw new IllegalArgumentException("Composición textil no puede contener números.");
        }
        this.composicion = composicion;
    }


    @Override
    public double calcularPrecio() {
        return getPrecio();
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    @Override
    public int compareTo(Textil o) {
        return this.composicion.compareTo(o.composicion);
    }

}
