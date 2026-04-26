package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.dao.DAOUsuario;
import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.exception.OfertaInvalidaException;
import cr.ac.ucenfotec.bl.exception.SubastaInvalidaException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorOferta {

    /**
     * Crea y persiste una oferta para una subasta específica.
     */
    public static void crearOferta(int idColeccionista, int idSubasta, double precio)
            throws SQLException, IOException, ClassNotFoundException {

        // Buscar el coleccionista en la BD
        ArrayList<Usuario> usuarios = DAOUsuario.listarUsuarios();
        Coleccionista coleccionista = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idColeccionista && u instanceof Coleccionista) {
                coleccionista = (Coleccionista) u;
                break;
            }
        }

        if (coleccionista == null) {
            throw new OfertaInvalidaException("No se encontró un coleccionista con ese ID.");
        }

        // Buscar la subasta en la BD
        ArrayList<Subasta> subastas = DAOSubasta.listarSubastas();
        Subasta subasta = null;
        for (Subasta s : subastas) {
            if (s.getId() == idSubasta) {
                subasta = s;
                break;
            }
        }

        if (subasta == null) {
            throw new SubastaInvalidaException("No se encontró una subasta con ese ID.");
        }

        // Validar que el coleccionista no oferte en su propia subasta
        if (subasta.getCreador().getId() == idColeccionista) {
            throw new OfertaInvalidaException("El creador de la subasta no puede ofertar en su propia subasta.");
        }

        // Validar estado
        if (subasta.getEstado().equals("CERRADA")) {
            throw new OfertaInvalidaException("No se puede ofertar en una subasta cerrada.");
        }

        // Validar precio mínimo
        if (precio < subasta.getPrecioMinimo()) {
            throw new OfertaInvalidaException("El precio ofertado no puede ser menor al precio mínimo de la subasta.");
        }

        Oferta oferta = new Oferta(coleccionista, precio);
        System.out.println(DAOOferta.insertarOferta(oferta, idSubasta));
    }

    /**
     * Lista todas las ofertas de una subasta específica y muestra la ganadora.
     */
    public static void listarOfertasPorSubasta(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Oferta> ofertas = DAOOferta.listarOfertasPorSubasta(idSubasta);

        if (ofertas.isEmpty()) {
            System.out.println("No hay ofertas para esta subasta.");
            return;
        }

        Oferta ganadora = ofertas.get(0);
        for (Oferta o : ofertas) {
            System.out.println("--------------------");
            System.out.println(o);
            if (o.getPrecioOferta() > ganadora.getPrecioOferta()) {
                ganadora = o;
            }
        }

        System.out.println("====================");
        System.out.println("Oferta ganadora:");
        System.out.println(ganadora);
    }

    /**
     * Retorna la oferta ganadora de una subasta específica.
     */
    public static Oferta getOfertaGanadora(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Oferta> ofertas = DAOOferta.listarOfertasPorSubasta(idSubasta);

        if (ofertas.isEmpty()) return null;

        Oferta ganadora = ofertas.get(0);
        for (Oferta o : ofertas) {
            if (o.getPrecioOferta() > ganadora.getPrecioOferta()) {
                ganadora = o;
            }
        }
        return ganadora;
    }
}