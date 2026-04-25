package cr.ac.ucenfotec.bl.entities;

public class Oferta {

    private Coleccionista oferente;
    private double precioOferta;

    // Constructor
    public Oferta(Coleccionista oferente, double precioOferta) {
        this.oferente = oferente;
        this.precioOferta = precioOferta;
    }

    // Getters
    public Coleccionista getOferente() {
        return oferente;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    // Setters
    public void setOferente(Coleccionista oferente) {
        this.oferente = oferente;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "oferente=" + oferente.getNombre() +
                ", puntuacion=" + oferente.getPuntuacion() +
                ", precioOferta=" + precioOferta +
                '}';
    }
}