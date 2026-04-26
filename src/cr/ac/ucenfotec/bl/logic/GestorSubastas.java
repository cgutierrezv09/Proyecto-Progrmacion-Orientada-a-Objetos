package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.dao.DAOUsuario;
import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.exception.OfertaInvalidaException;
import cr.ac.ucenfotec.bl.exception.SubastaInvalidaException;
import cr.ac.ucenfotec.bl.exception.UsuarioNoAutorizadoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorSubastas {

    // Crea una subasta con sus objetos
    public static String crearSubasta(LocalDateTime fechaVencimiento, int idCreador,
                                      double precioMinimo, int cantObjetos, BufferedReader input)
            throws SQLException, IOException, ClassNotFoundException {

        // Validar que no sea moderador
        ArrayList<Usuario> usuarios = DAOUsuario.listarUsuarios();
        Usuario creador = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idCreador) {
                creador = u;
                break;
            }
        }

        if (creador == null) {
            throw new SubastaInvalidaException("Usuario no encontrado");
        }

        if (creador instanceof Moderador) {
            throw new UsuarioNoAutorizadoException("El moderador no puede crear subastas");
        }

        // Recolectar objetos
        ArrayList<Integer> idsObjetos = new ArrayList<>();

        if (creador instanceof Coleccionista) {
            // Mostrar objetos del coleccionista para que elija
            ArrayList<Objeto> objPropios = DAOObjeto.leerObjetosPorColeccionista(idCreador);

            if (objPropios.isEmpty()) {
                throw new SubastaInvalidaException("El coleccionista no tiene objetos registrados");
            }

            System.out.println("Seleccione los IDs de sus objetos para la subasta:");
            DAOObjeto.mostrarObjetosPorColeccionista(idCreador);

            for (int i = 0; i < cantObjetos; i++) {
                System.out.println("ID del objeto " + (i + 1) + ":");
                int idObjeto = Integer.parseInt(input.readLine());
                idsObjetos.add(idObjeto);
            }
        } else {
            // Vendedor ingresa objetos nuevos
            for (int i = 0; i < cantObjetos; i++) {
                System.out.println("\nObjeto " + (i + 1));
                System.out.print("Nombre: ");
                String nombre = input.readLine();
                System.out.print("Descripcion: ");
                String desc = input.readLine();
                System.out.print("Estado: ");
                String estado = input.readLine();
                System.out.print("Fecha de compra (AAAA-MM-DD): ");
                LocalDate fechaCompra = LocalDate.parse(input.readLine());

                Objeto obj = new Objeto(nombre, desc, estado, fechaCompra);
                DAOObjeto.insertarObjeto(obj, idCreador);

                // Obtener id del objeto recien insertado
                String query = "SELECT id FROM t_objetos WHERE id_coleccionista = " + idCreador +
                        " ORDER BY id DESC LIMIT 1";
                java.sql.ResultSet rs = cr.ac.ucenfotec.dl.Conector.getConexion().ejecutarQuery(query);
                rs.next();
                idsObjetos.add(rs.getInt("id"));
            }
        }

        Subasta subasta = new Subasta(fechaVencimiento, creador, precioMinimo);
        return DAOSubasta.insertarSubasta(subasta, idCreador, idsObjetos);
    }

    // Lista todas las subastas
    public static void listarSubastas() throws SQLException, IOException, ClassNotFoundException {
        DAOSubasta.leerSubastas();
    }

    // Cierra una subasta y muestra el ganador
    public static void cerrarSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {

        if (!DAOSubasta.estaActiva(idSubasta)) {
            throw new SubastaInvalidaException("La subasta no existe o ya está cerrada");
        }

        System.out.println(DAOSubasta.cerrarSubasta(idSubasta));
        DAOOferta.ofertaGanadora(idSubasta);
    }
}