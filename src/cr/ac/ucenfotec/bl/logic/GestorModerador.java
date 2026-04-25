package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOModerador;
import cr.ac.ucenfotec.bl.entities.Moderador;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorModerador {

    public static String registrarMod(String nombre, String apellido, LocalDate fecha, String correo, String contraseña) throws SQLException, IOException, ClassNotFoundException {
        return DAOModerador.insertarMod(new Moderador(nombre,apellido,fecha,correo,contraseña));
    }
}
