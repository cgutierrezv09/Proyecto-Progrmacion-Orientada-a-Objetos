package cr.ac.ucenfotec.bl.entities;

import cr.ac.ucenfotec.bl.exception.EdadInsuficienteException;

import java.time.LocalDate;
import java.time.Period;


public abstract class Usuario {

    //Atributos
    protected String nombre;
    protected String apellido;
    protected LocalDate fechaNacimiento; //variable para que el usuario ingrese la fecha de nacimiento
    protected   String contraseña;
    protected String correo;

    protected int id;

    //constructor
    public Usuario(String nombre, String apellido, LocalDate fechaNacimiento, String contraseña, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    public Usuario(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
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


    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public int getId() {
        return id;
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



    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setId(int id) {
        this.id = id;
    }

    //metodo para calcular la edad
    public int calcularEdad(){
        return Period.between(fechaNacimiento,LocalDate.now()).getYears();
    }

    //metodo para validar la edad
    public  void validarEdad(){

       if (calcularEdad()<18){
           throw new EdadInsuficienteException("El usuario no puede ser menor de 18 años");
       }
    }

    //toString


    @Override
    public String toString() {
        return
                "nombre= " + nombre  + "\n apellido= " + apellido + "\n fechaNacimiento=" + fechaNacimiento;
    }

    // Metodo abstracto
     public abstract String mostrarRol();
}
