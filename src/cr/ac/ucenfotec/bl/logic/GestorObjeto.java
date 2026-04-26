package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.entities.Objeto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorObjeto {

    // Registra un objeto perteneciente a un coleccionista
    public static String registrarObjeto(Objeto objeto, int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOObjeto.insertarObjeto(objeto, idColeccionista);
    }

    // Lista los objetos de un coleccionista en consola
    public static void listarObjetosPorColeccionista(int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {
        DAOObjeto.mostrarObjetosPorColeccionista(idColeccionista);
    }

    // Retorna los objetos de un coleccionista como lista
    public static ArrayList<Objeto> obtenerObjetosPorColeccionista(int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOObjeto.leerObjetosPorColeccionista(idColeccionista);
    }

    // Obtiene el id del ultimo objeto insertado por un coleccionista
    public static int obtenerUltimoId(int idColeccionista)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOObjeto.obtenerUltimoIdPorColeccionista(idColeccionista);
    }
}