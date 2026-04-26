package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOUsuario {

    private static String query;

    /**
     * Retorna una lista con todos los usuarios registrados en la base de datos.
     */
    public static ArrayList<Usuario> listarUsuarios() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        query = "SELECT u.id, u.nombre, u.apellido, u.fecha_nacimiento, u.contrasena, u.correo, u.rol, " +
                "COALESCE(v.puntuacion, c.puntuacion) AS puntuacion, " +
                "COALESCE(v.direccion, c.direccion) AS direccion " +
                "FROM t_usuarios u " +
                "LEFT JOIN t_vendedores v ON u.id = v.id_usuario " +
                "LEFT JOIN t_coleccionistas c ON u.id = c.id_usuario";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        while (resultado.next()) {
            Usuario usuario = null;
            String rol = resultado.getString("rol");
            switch (rol) {
                case "MODERADOR":
                    usuario = new Moderador(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getDate("fecha_nacimiento").toLocalDate(),
                            resultado.getString("contrasena"),
                            resultado.getString("correo")
                    );
                    break;
                case "VENDEDOR":
                    usuario = new Vendedor(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getDate("fecha_nacimiento").toLocalDate(),
                            resultado.getString("contrasena"),
                            resultado.getString("correo"),
                            resultado.getInt("puntuacion"),
                            resultado.getString("direccion")
                    );
                    break;
                case "COLECCIONISTA":
                    usuario = new Coleccionista(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getDate("fecha_nacimiento").toLocalDate(),
                            resultado.getString("contrasena"),
                            resultado.getString("correo"),
                            resultado.getInt("puntuacion"),
                            resultado.getString("direccion"),
                            new ArrayList<>(),
                            new ArrayList<>()
                    );
                    break;
            }
            if (usuario != null) {
                usuario.setId(resultado.getInt("id"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}