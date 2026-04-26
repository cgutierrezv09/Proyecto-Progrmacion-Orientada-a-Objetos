package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAOSubasta {

    private static String statement;
    private static String query;

    // Inserta una subasta y sus objetos
    public static String insertarSubasta(Subasta subasta, int idCreador, ArrayList<Integer> idsObjetos)
            throws SQLException, IOException, ClassNotFoundException {

        // Paso 1: insertar en t_subastas
        statement = "INSERT INTO t_subastas (fecha_vencimiento, precio_minimo, estado, id_creador) " +
                "VALUES ('" + subasta.getFechaVencimiento() + "'," +
                subasta.getPrecioMinimo() + ",'" +
                subasta.getEstado() + "'," +
                idCreador + ")";

        Conector.getConexion().ejecutarStatement(statement);

        // Paso 2: obtener el id de la subasta recien insertada
        query = "SELECT id FROM t_subastas WHERE id_creador = " + idCreador +
                " ORDER BY id DESC LIMIT 1";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        resultado.next();
        int idSubasta = resultado.getInt("id");

        // Paso 3: insertar los objetos en t_subasta_objetos
        for (int idObjeto : idsObjetos) {
            statement = "INSERT INTO t_subasta_objetos (id_subasta, id_objeto) VALUES (" +
                    idSubasta + "," + idObjeto + ")";
            Conector.getConexion().ejecutarStatement(statement);
        }

        return "Subasta creada correctamente";
    }

    // Lee todas las subastas
    public static void leerSubastas() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT s.id, s.fecha_vencimiento, s.precio_minimo, s.estado, " +
                "u.nombre, u.apellido " +
                "FROM t_subastas s " +
                "JOIN t_usuarios u ON s.id_creador = u.id";

        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            System.out.println("No hay subastas registradas");
            return;
        }

        do {
            System.out.println("\n==== Subasta ====");
            System.out.println("ID: "              + resultado.getString("id"));
            System.out.println("Creador: "         + resultado.getString("nombre") + " " + resultado.getString("apellido"));
            System.out.println("Precio minimo: "   + resultado.getString("precio_minimo"));
            System.out.println("Estado: "          + resultado.getString("estado"));
            System.out.println("Vencimiento: "     + resultado.getString("fecha_vencimiento"));
        } while (resultado.next());
    }

    // Cambia el estado de una subasta a CERRADA
    public static String cerrarSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "UPDATE t_subastas SET estado = 'CERRADA' WHERE id = " + idSubasta;
        int filas = Conector.getConexion().ejecutarStatementConConteo(statement);

        if (filas == 0) {
            return "No se encontró ninguna subasta con ese ID";
        }

        return "Subasta cerrada correctamente";
    }

    // Verifica si una subasta existe y está activa
    public static boolean estaActiva(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT estado FROM t_subastas WHERE id = " + idSubasta;
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) return false;
        return resultado.getString("estado").equals("ACTIVA");
    }

    // Obtiene el precio minimo de una subasta
    public static double getPrecioMinimo(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT precio_minimo FROM t_subastas WHERE id = " + idSubasta;
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        resultado.next();
        return resultado.getDouble("precio_minimo");
    }

    // Obtiene el id del creador de una subasta
    public static int getIdCreador(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT id_creador FROM t_subastas WHERE id = " + idSubasta;
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        resultado.next();
        return resultado.getInt("id_creador");
    }
}