package cr.ac.ucenfotec.bl.entities;

public class Oferta {

    private Coleccionista oferente;
    private double precioOferta;
    private int id;
    private int idSubasta; // para saber a qué subasta pertenece al recuperar de BD

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

    public int getId() {
        return id;
    }

    public int getIdSubasta() {
        return idSubasta;
    }

    // Setters
    public void setOferente(Coleccionista oferente) {
        this.oferente = oferente;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
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