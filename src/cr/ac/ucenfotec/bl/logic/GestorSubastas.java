package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.dao.DAOUsuario;
import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.exception.SubastaInvalidaException;
import cr.ac.ucenfotec.bl.exception.UsuarioNoAutorizadoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorSubastas {

    /**
     * Crea y persiste una nueva subasta en la base de datos.
     */
    public static String crearSubasta(LocalDateTime fechaVencimiento,
                                      int idCreador,
                                      double precioMinimo,
                                      int cantObjetos,
                                      BufferedReader input) throws SQLException, IOException, ClassNotFoundException {

        // Buscar el usuario creador en la BD
        ArrayList<Usuario> usuarios = DAOUsuario.listarUsuarios();
        Usuario creador = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idCreador) {
                creador = u;
                break;
            }
        }

        if (creador == null) {
            throw new SubastaInvalidaException("No se encontró un usuario con ese ID.");
        }

        // Moderador no puede crear subastas
        if (creador instanceof Moderador) {
            throw new UsuarioNoAutorizadoException("El moderador no puede crear subastas.");
        }

        // Capturar objetos
        ArrayList<Objeto> objetos = new ArrayList<>();
        for (int i = 0; i < cantObjetos; i++) {
            System.out.println("Objeto " + (i + 1));
            System.out.println("Nombre:");
            String nombre = input.readLine();
            System.out.println("Descripción:");
            String descripcion = input.readLine();
            System.out.println("Estado:");
            String estado = input.readLine();
            System.out.println("Fecha de compra (AAAA-MM-DD):");
            LocalDate fechaCompra = LocalDate.parse(input.readLine());
            objetos.add(new Objeto(nombre, descripcion, estado, fechaCompra));
        }

        if (objetos.isEmpty()) {
            throw new SubastaInvalidaException("No puedes crear una subasta sin objetos.");
        }

        // Coleccionista solo puede usar objetos propios
        if (creador instanceof Coleccionista) {
            Coleccionista c = (Coleccionista) creador;
            if (c.getObjPropiedad() == null || c.getObjPropiedad().isEmpty()) {
                throw new SubastaInvalidaException("El coleccionista no tiene objetos en su colección.");
            }
            for (Objeto o : objetos) {
                boolean encontrado = false;
                for (Objeto propio : c.getObjPropiedad()) {
                    if (propio.getNombre().equals(o.getNombre())) {
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    throw new SubastaInvalidaException(
                            "El objeto '" + o.getNombre() + "' no pertenece a la colección del coleccionista.");
                }
            }
        }

        Subasta nueva = new Subasta(fechaVencimiento, creador, precioMinimo);
        for (Objeto o : objetos) {
            nueva.agregarObjeto(o);
        }

        return DAOSubasta.insertarSubasta(nueva);
    }

    /**
     * Lista todas las subastas consultando la base de datos.
     */
    public static void listarSubastas() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Subasta> subastas = DAOSubasta.listarSubastas();

        if (subastas.isEmpty()) {
            System.out.println("No hay subastas registradas.");
            return;
        }

        for (Subasta s : subastas) {
            System.out.println("--------------------");
            System.out.println("ID: " + s.getId());
            System.out.println(s);
        }
    }

    /**
     * Cierra una subasta actualizando su estado en la base de datos.
     */
    public static void cerrarSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
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

        if (subasta.getEstado().equals("CERRADA")) {
            System.out.println("La subasta ya está cerrada.");
            return;
        }

        System.out.println(DAOSubasta.cerrarSubasta(idSubasta));
    }

    /**
     * Retorna todas las subastas de la base de datos.
     */
    public static ArrayList<Subasta> getSubastas() throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.listarSubastas();
    }
}