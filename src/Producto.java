public abstract class Producto {
private String nombre;
private double precio;
private int codiBarres;
private String dataCaducitat;


    //Constructor
    public Producto(String nombre, double precio, int codiBarres, String dataCaducitat) {
        this.nombre = nombre;
        this.precio = precio;
        this.codiBarres = codiBarres;
        this.dataCaducitat = dataCaducitat;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public int getCodiBarres() {
        return codiBarres;
    }

    public String getDataCaducitat() {
        return dataCaducitat;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setCodiBarres(int codiBarres) {
        this.codiBarres = codiBarres;
    }
    public void setDataCaducitat(String dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }
}
