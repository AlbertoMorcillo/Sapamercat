public class Textil extends Producto{
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

}
