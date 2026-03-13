public class Oferta {

    private String nombreOferente;
    private String puntuacionOferente;
    private double precioOferta;

    //Constructor
    public Oferta(String nombreOferente, String puntuacionOferente, double precioOferta) {
        this.nombreOferente = nombreOferente;
        this.puntuacionOferente = puntuacionOferente;
        this.precioOferta = precioOferta;
    }

    //getters

    public String getNombreOferente() {
        return nombreOferente;
    }

    public String getPuntuacionOferente() {
        return puntuacionOferente;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    //setters

    public void setNombreOferente(String nombreOferente) {
        this.nombreOferente = nombreOferente;
    }

    public void setPuntuacionOferente(String puntuacionOferente) {
        this.puntuacionOferente = puntuacionOferente;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }
}
