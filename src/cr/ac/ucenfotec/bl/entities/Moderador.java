package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;

public class Moderador extends Usuario {


    //Super constructor
    //este no se agrega ningun atributo
    public Moderador(String nombre, String apellido, LocalDate fechaNacimiento, String contraseña, String correo) {
        super(nombre, apellido, fechaNacimiento, contraseña, correo);
    }

    @Override
    public String mostrarRol() {
        return "Moderador";
    }
}

