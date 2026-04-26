package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Coleccionista;
import cr.ac.ucenfotec.bl.entities.Moderador;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.entities.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOUsuario {

    private static String query;

    // Lista todos los usuarios del sistema
    public static ArrayList<Usuario> listarUsuarios() throws SQLException, IOException, ClassNotFoundException {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        query = "SELECT u.id, u.nombre, u.apellido, u.fecha_nacimiento, u.contrasena, u.correo, u.rol, " +
                "v.puntuacion AS puntuacion_v, v.direccion AS direccion_v, " +
                "c.puntuacion AS puntuacion_c, c.direccion AS direccion_c " +
                "FROM t_usuarios u " +
                "LEFT JOIN t_vendedores v ON u.id = v.id_usuario " +
                "LEFT JOIN t_coleccionistas c ON u.id = c.id_usuario";

        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        while (resultado.next()) {
            String rol = resultado.getString("rol");
            LocalDate fecha = LocalDate.parse(resultado.getString("fecha_nacimiento"));
            int id = resultado.getInt("id");

            Usuario usuario = null;

            switch (rol) {
                case "MODERADOR":
                    usuario = new Moderador(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            fecha,
                            resultado.getString("contrasena"),
                            resultado.getString("correo")
                    );
                    break;

                case "VENDEDOR":
                    usuario = new Vendedor(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            fecha,
                            resultado.getString("contrasena"),
                            resultado.getString("correo"),
                            resultado.getInt("puntuacion_v"),
                            resultado.getString("direccion_v")
                    );
                    break;

                case "COLECCIONISTA":
                    usuario = new Coleccionista(
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            fecha,
                            resultado.getString("contrasena"),
                            resultado.getString("correo"),
                            resultado.getInt("puntuacion_c"),
                            resultado.getString("direccion_c"),
                            new ArrayList<>(),
                            new ArrayList<>()
                    );
                    break;
            }

            if (usuario != null) {
                usuario.setId(id);
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }
}