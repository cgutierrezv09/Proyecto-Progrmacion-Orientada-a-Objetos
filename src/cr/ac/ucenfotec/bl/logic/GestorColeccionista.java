package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOColeccionista;
import cr.ac.ucenfotec.bl.entities.Coleccionista;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorColeccionista {

    /**
     * Registra un coleccionista en la base de datos aplicando validaciones.
     */
    public static String registrarColeccionista(String nombre, String apellido, LocalDate fechaNacimiento,
                                                String contrasena, String correo,
                                                int puntuacion, String direccion)
            throws SQLException, IOException, ClassNotFoundException {
        Coleccionista coleccionista = new Coleccionista(nombre, apellido, fechaNacimiento,
                contrasena, correo, puntuacion, direccion, new ArrayList<>(), new ArrayList<>());
        coleccionista.validarEdad();
        return DAOColeccionista.insertarColeccionista(coleccionista);
    }
}