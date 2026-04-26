package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Coleccionista;
import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOOferta {

    private static String statement;
    private static String query;

    /**
     * Inserta una nueva oferta en la base de datos.
     * @param oferta Objeto Oferta a insertar.
     * @param idSubasta ID de la subasta a la que pertenece la oferta.
     * @return Mensaje de confirmación.
     */
    public static String insertarOferta(Oferta oferta, int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_ofertas (precio_oferta, id_coleccionista, id_subasta) VALUES (" +
                oferta.getPrecioOferta() + ", " +
                oferta.getOferente().getId() + ", " +
                idSubasta + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "La oferta se registró correctamente.";
    }

    /**
     * Retorna una lista con todas las ofertas de una subasta específica.
     * @param idSubasta ID de la subasta de la que se quieren listar las ofertas.
     * @return ArrayList de objetos Oferta.
     */
    public static ArrayList<Oferta> listarOfertasPorSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Oferta> ofertas = new ArrayList<>();
        query = "SELECT o.id, o.precio_oferta, o.id_subasta, " +
                "c.id_usuario, u.nombre, u.apellido, u.correo, c.puntuacion, c.direccion " +
                "FROM t_ofertas o " +
                "JOIN t_coleccionistas c ON o.id_coleccionista = c.id_usuario " +
                "JOIN t_usuarios u ON c.id_usuario = u.id " +
                "WHERE o.id_subasta = " + idSubasta + ";";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        while (resultado.next()) {
            Coleccionista oferente = new Coleccionista(
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("correo")
            );
            oferente.setId(resultado.getInt("id_usuario"));
            oferente.setPuntuacion(resultado.getInt("puntuacion"));
            oferente.setDireccion(resultado.getString("direccion"));

            Oferta oferta = new Oferta(oferente, resultado.getDouble("precio_oferta"));
            oferta.setId(resultado.getInt("id"));
            oferta.setIdSubasta(resultado.getInt("id_subasta"));
            ofertas.add(oferta);
        }
        return ofertas;
    }

    /**
     * Retorna una lista con todas las ofertas registradas en la base de datos.
     * @return ArrayList de objetos Oferta.
     */
    public static ArrayList<Oferta> listarTodasLasOfertas() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Oferta> ofertas = new ArrayList<>();
        query = "SELECT o.id, o.precio_oferta, o.id_subasta, " +
                "c.id_usuario, u.nombre, u.apellido, u.correo, c.puntuacion, c.direccion " +
                "FROM t_ofertas o " +
                "JOIN t_coleccionistas c ON o.id_coleccionista = c.id_usuario " +
                "JOIN t_usuarios u ON c.id_usuario = u.id;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        while (resultado.next()) {
            Coleccionista oferente = new Coleccionista(
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("correo")
            );
            oferente.setId(resultado.getInt("id_usuario"));
            oferente.setPuntuacion(resultado.getInt("puntuacion"));
            oferente.setDireccion(resultado.getString("direccion"));

            Oferta oferta = new Oferta(oferente, resultado.getDouble("precio_oferta"));
            oferta.setId(resultado.getInt("id"));
            oferta.setIdSubasta(resultado.getInt("id_subasta"));
            ofertas.add(oferta);
        }
        return ofertas;
    }
}
