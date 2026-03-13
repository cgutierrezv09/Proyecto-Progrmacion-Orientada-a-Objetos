package cr.ac.ucenfotec.logica.modelo;

//Provisional Mientras hacia los casos en el switch

public class Usuario {

    private String nombreCompleto;

    public Usuario() {
    }

    public Usuario(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String toString() {
        return nombreCompleto;
    }
}
