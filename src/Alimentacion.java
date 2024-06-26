import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;// Importar la clase ChronoUnit. Sirve para calcular la diferencia entre dos fechas.

public class Alimentacion extends Producto {
    private String dataCaducitat;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Alimentacion(String nombre, double precio, String codiBarres, String dataCaducitat) {
        super(nombre, precio, codiBarres);
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("Precio debe ser un número positivo.");
        }
        if (codiBarres == null || codiBarres.isEmpty()) {
            throw new IllegalArgumentException("Código de barras no puede ser nulo o vacío.");
        }
        this.dataCaducitat = dataCaducitat;
        if (!fechaValida(dataCaducitat)) {
            throw new IllegalArgumentException("Fecha de caducidad no válida.");
        }
    }

    @Override
    public double calcularPrecio() {
        // Convertir la cadena de la data de caducitat a LocalDate
        LocalDate fechaCaducidad = LocalDate.parse(dataCaducitat, formatter);
        // Obtenir la data actual
        LocalDate ahora = LocalDate.now();
        // Calcular els dies que falten per a la caducitat
        long diasHastaCaducidad = ChronoUnit.DAYS.between(ahora, fechaCaducidad);

        // Accedir al precio a través del getter
        double precioBase = getPrecio();

        // Aplicar la fórmula de descompte i increment per al preu
        double precioDescuento = precioBase - precioBase * (1.0 / (diasHastaCaducidad + 1));
        double precioFinal = precioDescuento + (precioBase * 0.1);

        return precioFinal;
    }


    public String getDataCaducitat() {
        return dataCaducitat;
    }
    public int diasHastaCaducidad() {
        LocalDate fechaCaducidad = LocalDate.parse(this.dataCaducitat, formatter);
        LocalDate ahora = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(ahora, fechaCaducidad);
    }

    public void setDataCaducitat(String dataCaducitat) {
        if (fechaValida(dataCaducitat)) {
            this.dataCaducitat = dataCaducitat;
        } else {
            throw new IllegalArgumentException("Fecha de caducidad no válida.");
        }
    }


    static boolean fechaValida(String data) {
        LocalDate fecha = LocalDate.parse(data, formatter);
        return !fecha.isBefore(LocalDate.now());
    }



}
