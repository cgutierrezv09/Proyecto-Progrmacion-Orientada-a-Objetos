package cr.ac.ucenfotec.logica.modelo;

import java.time.LocalDate;
import java.time.Period;

public class Objeto {

    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDate fechaCompra;

    // Constructor por Defecto
    public Objeto() {
    }

    // Constructor Completo
    public Objeto(String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Period calcularAntiguedad() {
        return Period.between(fechaCompra, LocalDate.now());
    }

    @Override
    public String toString() {

        Period antiguedad = calcularAntiguedad();

        return "Objeto: " + nombre +
                "\nDescripcion: " + descripcion +
                "\nEstado: " + estado +
                "\nFecha compra: " + fechaCompra +
                "\nAntiguedad: " + antiguedad.getYears() + " años, "
                + antiguedad.getMonths() + " meses, "
                + antiguedad.getDays() + " dias";
    }
}