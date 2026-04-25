package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Moderador;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOModerador {
    private static String statement;
    private static String query;

    public static Boolean existeMod() throws SQLException, IOException, ClassNotFoundException {
        query="SELECT * FROM t_moderadores";
        ResultSet resultado=Conector.getConexion().ejecutarQuery(query);
        if (resultado.next()){
            return true;
        }else {
            return false;
        }
    }
    public static String insertarMod(Moderador moderador) throws SQLException, IOException, ClassNotFoundException {

        // Paso 1: insertar en t_usuarios
        statement = "INSERT INTO t_usuarios (nombre, apellido, fecha_nacimiento, contrasena, correo, rol) " +
                "VALUES ('" + moderador.getNombre() + "','" +
                moderador.getApellido() + "','" +
                moderador.getFechaNacimiento() + "','" +
                moderador.getContraseña() + "','" +
                moderador.getCorreo() + "','MODERADOR')";

        Conector.getConexion().ejecutarStatement(statement);

        // Paso 2: obtener el id generado buscando por correo
        query = "SELECT id FROM t_usuarios WHERE correo = '" + moderador.getCorreo() + "'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        resultado.next();
        int idGenerado = resultado.getInt("id");

        // Paso 3: insertar en t_moderadores con ese id
        statement = "INSERT INTO t_moderadores (id_usuario) VALUES (" + idGenerado + ")";
        Conector.getConexion().ejecutarStatement(statement);

        return "El moderador se registró con éxito";
    }
}
