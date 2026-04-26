package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOVendedor {

    private static String statement;
    private static String query;

    /**
     * Inserta un vendedor en la base de datos.
     */
    public static String insertarVendedor(Vendedor vendedor) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_usuarios (nombre, apellido, fecha_nacimiento, contrasena, correo, rol) " +
                "VALUES ('" + vendedor.getNombre() + "','" +
                vendedor.getApellido() + "','" +
                vendedor.getFechaNacimiento() + "','" +
                vendedor.getContraseña() + "','" +
                vendedor.getCorreo() + "','VENDEDOR')";
        Conector.getConexion().ejecutarStatement(statement);

        query = "SELECT id FROM t_usuarios WHERE correo = '" + vendedor.getCorreo() + "'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        resultado.next();
        int idGenerado = resultado.getInt("id");

        statement = "INSERT INTO t_vendedores (id_usuario, puntuacion, direccion) VALUES (" +
                idGenerado + ", " + vendedor.getPuntuacion() + ", '" + vendedor.getDireccion() + "')";
        Conector.getConexion().ejecutarStatement(statement);

        return "El vendedor se registró con éxito.";
    }
}