public class Textil extends Producto implements Comparable<Textil>{
    private String composicion;

    public Textil(String nombre, double precio, String codigoBarras, String composicion) {
        super(nombre, precio, codigoBarras);
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
