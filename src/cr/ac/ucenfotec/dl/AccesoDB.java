package cr.ac.ucenfotec.dl;

import java.sql.*;

public class AccesoDB {

    //Atributos.
    private Connection conexion;
    private Statement statement;
    private PreparedStatement preparedStatement;


    //Métodos.
    //Constructor.
    public AccesoDB(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    public void ejecutarStatement(String statement) throws SQLException {
        this.statement=conexion.createStatement();
        this.statement.executeUpdate(statement);
    }
    public int ejecutarStatementConConteo(String statement) throws SQLException {
        this.statement = conexion.createStatement();
        return this.statement.executeUpdate(statement);
    }
    public ResultSet ejecutarQuery(String query) throws SQLException {
        preparedStatement= conexion.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

}
