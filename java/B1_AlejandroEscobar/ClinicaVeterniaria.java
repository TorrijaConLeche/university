package protectora;

public class ClinicaVeterniaria {
    private double precioEsterilizacion;
    private String nombre;
    private String telefono;

    public ClinicaVeterniaria(double precioEsterilizacion, String nombre, String telefono){
        this.precioEsterilizacion = precioEsterilizacion;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public double getPrecioEsterilizacion() {
        return precioEsterilizacion;
    }
    public void setPrecioEsterilizacion(double precioEsterilizacion){
        this.precioEsterilizacion = precioEsterilizacion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "ClinicaVeterniaria{" + "precioEsterilizacion=" + precioEsterilizacion + '}';
    }

}
