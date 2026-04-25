package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.utils.Utilidades;

import java.io.IOException;
import java.sql.SQLException;

public class Conector {
    private  static  AccesoDB  conexionBD = null;

    public static AccesoDB getConexion() throws SQLException, ClassNotFoundException, IOException {
        if (conexionBD != null)return conexionBD;
        String[] propiedades = Utilidades.getPropierties();
        String direccion= propiedades[0] + "//" +propiedades[1] + "/" + propiedades[2] ;
        String usuario= propiedades[3] ;
        String contrsenia= propiedades[4];
        return conexionBD= new AccesoDB(direccion,usuario,contrsenia);

    }
}
