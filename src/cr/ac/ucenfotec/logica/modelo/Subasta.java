package cr.ac.ucenfotec.logica.modelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import cr.ac.ucenfotec.tipoUsuario.*;

public class Subasta {

    private LocalDateTime fechaVencimiento;
    private Usuario creador;
    private double precioMinimo;
    private ArrayList<Objeto> objetos;
    private String estado;
    ArrayList<Oferta> ofertas = new ArrayList<>();  // aca se ingresan las ofertas de las subastas

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

    public Oferta getOfertaGanadora() {

        if (ofertas.isEmpty()) {
            return null;
        }

        Oferta mejor = ofertas.get(0);

        for (Oferta o : ofertas) {
            if (o.getPrecioOferta() > mejor.getPrecioOferta()) {
                mejor = o;
            }
        }

        return mejor;
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

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public String getEstado() {
        return estado;
    }

    //getter para la oferta
    public ArrayList<Oferta> getOfertas(){
        return ofertas;
    }

    @Override
    public String toString() {

        Duration restante = calcularTiempoRestante();

        return "Subasta creada por: " + creador.getNombre() +
                "\nPrecio minimo: " + precioMinimo +
                "\nEstado: " + estado +
                "\nTiempo restante: " + restante.toHours() + " horas" +
                "\nObjetos: " + objetos;
    }
}
