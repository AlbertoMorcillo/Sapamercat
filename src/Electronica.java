public class Electronica extends Producto{
    private int diasGarantia;

    public Electronica(String nombre, double precio, String codigoBarras, int diasGarantia) {
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
        if (diasGarantia < 0) {
            throw new IllegalArgumentException("Días de garantía no pueden ser negativos.");
        }
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
