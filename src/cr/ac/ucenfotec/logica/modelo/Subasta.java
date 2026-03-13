package cr.ac.ucenfotec.logica.modelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Subasta {

    private LocalDateTime fechaVencimiento;
    private Usuario creador;
    private double precioMinimo;
    private ArrayList<Objeto> objetos;
    private String estado;

    // Constructor por Defecto
    public Subasta() {
        objetos = new ArrayList<>();
    }

    // Constructor Completo
    public Subasta(LocalDateTime fechaVencimiento, Usuario creador, double precioMinimo) {
        this.fechaVencimiento = fechaVencimiento;
        this.creador = creador;
        this.precioMinimo = precioMinimo;
        this.estado = "ACTIVA";
        objetos = new ArrayList<>();
    }

    // A traves de los datos proporcionados por el usuario y la fecha actual, calcula el tiempo en subasta
    public Duration calcularTiempoRestante() {

        return Duration.between(LocalDateTime.now(), fechaVencimiento);

    }

    // Cambia el estado de la subasta
    public void cerrarSubasta() {
        estado = "CERRADA";
    }

    // Getters, algunos de los setters se hacen a partir de consola

    public void agregarObjeto(Objeto obj) {
        objetos.add(obj);
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public Usuario getCreador() {
        return creador;
    }

    @Override
    public String toString() {

        Duration restante = calcularTiempoRestante();

        return "Subasta creada por: " + creador.getNombreCompleto() +
                "\nPrecio minimo: " + precioMinimo +
                "\nEstado: " + estado +
                "\nTiempo restante: " + restante.toHours() + " horas" +
                "\nObjetos: " + objetos;
    }
}
