package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.dao.DAOUsuario;
import cr.ac.ucenfotec.bl.entities.Coleccionista;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.exception.OfertaInvalidaException;
import cr.ac.ucenfotec.bl.exception.SubastaInvalidaException;
import cr.ac.ucenfotec.bl.exception.UsuarioNoAutorizadoException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorOferta {

    // Crea una oferta validando todas las reglas
    public static void crearOferta(int idColeccionista, int idSubasta, double precio)
            throws SQLException, IOException, ClassNotFoundException {

        // Validar que el usuario sea coleccionista
        ArrayList<Usuario> usuarios = DAOUsuario.listarUsuarios();
        Usuario oferente = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idColeccionista) {
                oferente = u;
                break;
            }
        }

        if (!(oferente instanceof Coleccionista)) {
            throw new UsuarioNoAutorizadoException("Solo los coleccionistas pueden hacer ofertas");
        }

        // Validar que la subasta esté activa
        if (!DAOSubasta.estaActiva(idSubasta)) {
            throw new SubastaInvalidaException("La subasta no existe o ya está cerrada");
        }

        // Validar que el coleccionista no sea el creador de la subasta
        int idCreador = DAOSubasta.getIdCreador(idSubasta);
        if (idCreador == idColeccionista) {
            throw new UsuarioNoAutorizadoException("No puedes ofertar en tu propia subasta");
        }

        // Validar precio minimo
        double precioMinimo = DAOSubasta.getPrecioMinimo(idSubasta);
        if (precio < precioMinimo) {
            throw new OfertaInvalidaException("El precio ofertado es menor al precio mínimo de la subasta (" + precioMinimo + ")");
        }

        System.out.println(DAOOferta.insertarOferta(idColeccionista, idSubasta, precio));
    }

    // Lista todas las ofertas de una subasta
    public static void listarOfertasPorSubasta(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {
        DAOOferta.leerOfertasPorSubasta(idSubasta);
    }
}