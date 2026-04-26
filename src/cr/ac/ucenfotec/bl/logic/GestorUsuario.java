package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOUsuario;
import cr.ac.ucenfotec.bl.entities.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorUsuario {

    // Lista todos los usuarios del sistema
    public static ArrayList<Usuario> listarUsuarios() throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuario.listarUsuarios();
    }
}