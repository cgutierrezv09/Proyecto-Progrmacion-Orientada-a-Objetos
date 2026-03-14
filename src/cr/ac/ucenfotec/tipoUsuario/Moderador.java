package cr.ac.ucenfotec.tipoUsuario;

import java.time.LocalDate;

public class Moderador extends Usuario {


    //Super constructor
    //este no se agrega ningun atributo
    public Moderador(String nombre, String apellido, LocalDate fechaNacimiento, String contraseña, String correo) {
        super(nombre, apellido, fechaNacimiento, contraseña, correo);
    }
}

