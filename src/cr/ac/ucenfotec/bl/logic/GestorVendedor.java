package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOVendedor;
import cr.ac.ucenfotec.bl.entities.Vendedor;
import cr.ac.ucenfotec.bl.exception.EdadInsuficienteException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorVendedor {

    /**
     * Registra un vendedor en la base de datos aplicando validaciones.
     */
    public static String registrarVendedor(String nombre, String apellido, LocalDate fechaNacimiento,
                                           String contrasena, String correo,
                                           int puntuacion, String direccion)
            throws SQLException, IOException, ClassNotFoundException {
        Vendedor vendedor = new Vendedor(nombre, apellido, fechaNacimiento,
                contrasena, correo, puntuacion, direccion);
        vendedor.validarEdad();
        return DAOVendedor.insertarVendedor(vendedor);
    }
}