package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOObjeto {

    private static String statement;
    private static String query;

    // Inserta un objeto perteneciente a un coleccionista
    public static String insertarObjeto(Objeto objeto, int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_objetos (nombre, descripcion, estado, fecha_compra, id_coleccionista) " +
                "VALUES ('" + objeto.getNombre() + "','" +
                objeto.getDescripcion() + "','" +
                objeto.getEstado() + "','" +
                objeto.getFechaCompra() + "'," +
                idColeccionista + ")";

        Conector.getConexion().ejecutarStatement(statement);

        return "Objeto registrado correctamente";
    }

    // Muestra los objetos de un coleccionista en consola
    public static void mostrarObjetosPorColeccionista(int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_objetos WHERE id_coleccionista = " + idColeccionista;
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            System.out.println("Este coleccionista no tiene objetos registrados");
            return;
        }

        do {
            System.out.println("\n==== Objeto ====");
            System.out.println("ID: "           + resultado.getString("id"));
            System.out.println("Nombre: "       + resultado.getString("nombre"));
            System.out.println("Descripcion: "  + resultado.getString("descripcion"));
            System.out.println("Estado: "       + resultado.getString("estado"));
            System.out.println("Fecha compra: " + resultado.getString("fecha_compra"));
        } while (resultado.next());
    }

    // Retorna los objetos de un coleccionista como lista
    public static ArrayList<Objeto> leerObjetosPorColeccionista(int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_objetos WHERE id_coleccionista = " + idColeccionista;
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        ArrayList<Objeto> objetos = new ArrayList<>();

        while (resultado.next()) {
            Objeto obj = new Objeto(
                    resultado.getString("nombre"),
                    resultado.getString("descripcion"),
                    resultado.getString("estado"),
                    LocalDate.parse(resultado.getString("fecha_compra"))
            );
            objetos.add(obj);
        }

        return objetos;
    }

    // Obtiene el id del ultimo objeto insertado por un coleccionista
    public static int obtenerUltimoIdPorColeccionista(int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT id FROM t_objetos WHERE id_coleccionista = " + idColeccionista +
                " ORDER BY id DESC LIMIT 1";

        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (resultado.next()) {
            return resultado.getInt("id");
        }

        return -1;
    }
}