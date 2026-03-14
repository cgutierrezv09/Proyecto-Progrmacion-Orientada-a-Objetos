package cr.ac.ucenfotec.tipoUsuario;


import java.time.LocalDate;
import java.util.ArrayList;

public class Coleccionista extends Usuario {

    //Atributos
    private int puntuacion;
    private String direccion;
    private ArrayList<String> listInteres;
    private ArrayList<String> objPropiedad;


    //Super constructor con los atributos agregados
    public Coleccionista(String nombre, String apellido, LocalDate fechaNacimiento,
                         String contraseña, String correo, int puntuacion, String direccion,
                         ArrayList<String> listInteres, ArrayList<String> objPropiedad) {
        super(nombre, apellido, fechaNacimiento, contraseña, correo);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
        this.listInteres = listInteres;
        this.objPropiedad = objPropiedad;
    }

    //Getters

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getListInteres() {
        return listInteres;
    }
    //Setters

    public void setListInteres(ArrayList<String> listInteres) {
        this.listInteres = listInteres;
    }

    public ArrayList<String> getObjPropiedad() {
        return objPropiedad;
    }

    public void setObjPropiedad(ArrayList<String> objPropiedad) {
        this.objPropiedad = objPropiedad;
    }

    //toString

}
