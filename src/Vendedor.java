import java.time.LocalDate;

public class Vendedor extends Usuario{

    //Atributos
    private int puntuacion;
    private String direccion;

    //Super constructor con los atributos correspondientes del vendedor
    public Vendedor(String nombre, String apellido, LocalDate fechaNacimiento, int edad,
                    String contraseña, String correo, int puntuacion, String direccion) {
        super(nombre, apellido, fechaNacimiento, edad, contraseña, correo);
        this.puntuacion=puntuacion;
        this.direccion=direccion;
    }

    //Getters

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getDireccion() {
        return direccion;
    }

    //Setters


    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
