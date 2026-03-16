package cr.ac.ucenfotec.logica.modelo;

public class Oferta {

    private String nombreOferente;
    private int puntuacionOferente;
    private double precioOferta;

    //Constructor
    public Oferta(String nombreOferente, int puntuacionOferente, double precioOferta) {
        this.nombreOferente = nombreOferente;
        this.puntuacionOferente = puntuacionOferente;
        this.precioOferta = precioOferta;
    }

    //getters

    public String getNombreOferente() {
        return nombreOferente;
    }

    public int getPuntuacionOferente() {
        return puntuacionOferente;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    //setters

    public void setNombreOferente(String nombreOferente) {
        this.nombreOferente = nombreOferente;
    }

    public void setPuntuacionOferente(int puntuacionOferente) {
        this.puntuacionOferente = puntuacionOferente;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }


    @Override
    public String toString() {
        return "Oferta{" +
                "nombreOferente=" + nombreOferente + "\n puntuacionOferente=" + puntuacionOferente + " \n precioOferta=" + precioOferta ;
    }
}
