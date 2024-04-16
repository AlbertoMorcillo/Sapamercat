public class Electronica extends Producto{
    private int diasGarantia;

    public Electronica(String nombre, double precio, String codigoBarras, int diasGarantia) {
        super(nombre, precio, codigoBarras);
        this.diasGarantia = diasGarantia;
    }

    @Override
    public double calcularPrecio() {
        double precioBase = getPrecio();
        double precioFinal = precioBase + precioBase * (diasGarantia / 365.0) * 0.1;
        return precioFinal;
    }

    public int getDiasGarantia() {
        return diasGarantia;
    }

    public void setDiasGarantia(int diasGarantia) {
        this.diasGarantia = diasGarantia;
    }

}
