package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOModerador;
import cr.ac.ucenfotec.bl.entities.Moderador;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorModerador {

    //Registra un moderador en la base de datos.

    public static String registrarMod(String nombre, String apellido, LocalDate fecha,
                                      String contrasena, String correo)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOModerador.insertarMod(new Moderador(nombre, apellido, fecha, contrasena, correo));
    }

    //Verifica si ya existe un moderador en la base de datos.

    public static boolean existeMod() throws SQLException, IOException, ClassNotFoundException {
        return DAOModerador.existeMod();
    }
}