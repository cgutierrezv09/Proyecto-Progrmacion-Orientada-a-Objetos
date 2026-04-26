package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Coleccionista;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOColeccionista {

    private static String statement;
    private static String query;

    /**
     * Inserta un coleccionista en la base de datos.
     */
    public static String insertarColeccionista(Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_usuarios (nombre, apellido, fecha_nacimiento, contrasena, correo, rol) " +
                "VALUES ('" + coleccionista.getNombre() + "','" +
                coleccionista.getApellido() + "','" +
                coleccionista.getFechaNacimiento() + "','" +
                coleccionista.getContraseña() + "','" +
                coleccionista.getCorreo() + "','COLECCIONISTA')";
        Conector.getConexion().ejecutarStatement(statement);

        query = "SELECT id FROM t_usuarios WHERE correo = '" + coleccionista.getCorreo() + "'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        resultado.next();
        int idGenerado = resultado.getInt("id");

        statement = "INSERT INTO t_coleccionistas (id_usuario, puntuacion, direccion) VALUES (" +
                idGenerado + ", " + coleccionista.getPuntuacion() + ", '" + coleccionista.getDireccion() + "')";
        Conector.getConexion().ejecutarStatement(statement);

        return "El coleccionista se registró con éxito.";
    }

    public static int obtenerIdPorCorreo(String correo) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT id FROM t_usuarios WHERE correo = '" + correo + "'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (resultado.next()) {
            return resultado.getInt("id");
        }
        return -1;
    }
}