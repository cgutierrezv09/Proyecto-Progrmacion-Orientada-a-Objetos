import java.time.LocalDate;
import java.time.Period;


public class Usuario {

    //Atributos
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento; //variable para que el usuario ingrese la fecha de nacimiento
    private final LocalDate hoy=LocalDate.now();  // varibale para determinar la fecha de hoy
    private int edad= Period.between(fechaNacimiento,hoy).getYears();//variable que calcula la edad en base a la feche de nacimiento y de hoy
    private  String contraseña;
    private String correo;

    //constructor
    public Usuario(String nombre, String apellido, LocalDate fechaNacimiento,
                   int edad, String contraseña, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    //getters


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getHoy() {
        return hoy;
    }

    public int getEdad() {
        return edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
