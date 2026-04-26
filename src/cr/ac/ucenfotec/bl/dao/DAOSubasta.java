package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Coleccionista;
import cr.ac.ucenfotec.bl.entities.Moderador;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.entities.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOSubasta {
    private static String statement;
    private static String query;

    /**
     * Inserta una nueva subasta en la base de datos.
     * @param subasta Objeto Subasta a insertar.
     * @return Mensaje de confirmación.
     */
    public static String insertarSubasta(Subasta subasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_subastas (fecha_vencimiento, precio_minimo, estado, id_creador) VALUES ('" +
                subasta.getFechaVencimiento() + "', " +
                subasta.getPrecioMinimo() + ", '" +
                subasta.getEstado() + "', " +
                subasta.getCreador().getId() + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "La subasta se registró correctamente.";
    }

    /**
     * Retorna una lista con todas las subastas registradas en la base de datos.
     * @return ArrayList de objetos Subasta.
     */
    public static ArrayList<Subasta> listarSubastas() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Subasta> subastas = new ArrayList<>();
        query = "SELECT s.id, s.fecha_vencimiento, s.precio_minimo, s.estado, " +
                "u.id AS id_creador, u.nombre, u.apellido, u.correo, u.rol " +
                "FROM t_subastas s " +
                "JOIN t_usuarios u ON s.id_creador = u.id;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        while (resultado.next()) {

            // Se instancia la subclase correcta según el rol del creador
            Usuario creador = null;
            String rol = resultado.getString("rol");
            switch (rol) {
                case "COLECCIONISTA":
                    creador = new Coleccionista(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("correo")
                    );
                    break;
                case "VENDEDOR":
                    creador = new Vendedor(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("correo")
                    );
                    break;
                case "MODERADOR":
                    creador = new Moderador(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("correo")
                    );
                    break;
            }
            creador.setId(resultado.getInt("id_creador"));

            Subasta subasta = new Subasta(
                    resultado.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                    creador,
                    resultado.getDouble("precio_minimo")
            );
            subasta.setId(resultado.getInt("id"));
            subasta.setEstado(resultado.getString("estado"));
            subastas.add(subasta);
        }
        return subastas;
    }

    /**
     * Actualiza el estado de una subasta a CERRADA en la base de datos.
     * @param idSubasta ID de la subasta a cerrar.
     * @return Mensaje de confirmación.
     */
    public static String cerrarSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "UPDATE t_subastas SET estado = 'CERRADA' WHERE id = " + idSubasta + ";";
        int filasAfectadas = Conector.getConexion().ejecutarStatementConConteo(statement);
        if (filasAfectadas == 0) {
            return "No se encontró una subasta con ese ID.";
        }
        return "La subasta fue cerrada correctamente.";
    }
}
