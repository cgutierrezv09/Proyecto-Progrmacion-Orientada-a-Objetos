package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOOferta {

    private static String statement;
    private static String query;

    // Inserta una oferta
    public static String insertarOferta(int idColeccionista, int idSubasta, double precio)
            throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_ofertas (precio_oferta, id_coleccionista, id_subasta) " +
                "VALUES (" + precio + "," + idColeccionista + "," + idSubasta + ")";

        Conector.getConexion().ejecutarStatement(statement);

        return "Oferta registrada correctamente";
    }

    // Lista todas las ofertas de una subasta
    public static void leerOfertasPorSubasta(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT o.id, o.precio_oferta, u.nombre, u.apellido, c.puntuacion " +
                "FROM t_ofertas o " +
                "JOIN t_coleccionistas c ON o.id_coleccionista = c.id_usuario " +
                "JOIN t_usuarios u ON c.id_usuario = u.id " +
                "WHERE o.id_subasta = " + idSubasta;

        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            System.out.println("Esta subasta no tiene ofertas");
            return;
        }

        do {
            System.out.println("\n==== Oferta ====");
            System.out.println("ID: "           + resultado.getString("id"));
            System.out.println("Oferente: "     + resultado.getString("nombre") + " " + resultado.getString("apellido"));
            System.out.println("Puntuacion: "   + resultado.getString("puntuacion"));
            System.out.println("Precio: "       + resultado.getString("precio_oferta"));
        } while (resultado.next());
    }

    // Obtiene la oferta ganadora de una subasta (la de mayor precio)
    public static void ofertaGanadora(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT o.precio_oferta, u.nombre, u.apellido " +
                "FROM t_ofertas o " +
                "JOIN t_coleccionistas c ON o.id_coleccionista = c.id_usuario " +
                "JOIN t_usuarios u ON c.id_usuario = u.id " +
                "WHERE o.id_subasta = " + idSubasta +
                " ORDER BY o.precio_oferta DESC LIMIT 1";

        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            System.out.println("Esta subasta no tiene ofertas");
            return;
        }

        System.out.println("\n==== Oferta Ganadora ====");
        System.out.println("Ganador: " + resultado.getString("nombre") + " " + resultado.getString("apellido"));
        System.out.println("Precio:  " + resultado.getString("precio_oferta"));
    }
}