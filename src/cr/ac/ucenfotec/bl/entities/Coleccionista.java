package cr.ac.ucenfotec.bl.entities;


import java.time.LocalDate;
import java.util.ArrayList;

public class Coleccionista extends Usuario {

    //Atributos
    private int puntuacion;
    private String direccion;
    private ArrayList<Objeto> listInteres;
    private ArrayList<Objeto> objPropiedad;


    //Super constructor con los atributos agregados
    public Coleccionista(String nombre, String apellido, LocalDate fechaNacimiento,
                         String contraseña, String correo, int puntuacion, String direccion,
                         ArrayList<Objeto> listInteres, ArrayList<Objeto> objPropiedad) {
        super(nombre, apellido, fechaNacimiento, contraseña, correo);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
        this.listInteres = listInteres;
        this.objPropiedad = objPropiedad;
    }

    public Coleccionista(String nombre, String apellido, String correo) {
        super(nombre, apellido, correo);
        this.listInteres = new ArrayList<>();
        this.objPropiedad = new ArrayList<>();
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

    public ArrayList<Objeto> getListInteres() {
        return listInteres;
    }
    //Setters

    public void setListInteres(ArrayList<Objeto> listInteres) {
        this.listInteres = listInteres;
    }

    public ArrayList<Objeto> getObjPropiedad() {
        return objPropiedad;
    }

    public void setObjPropiedad(ArrayList<Objeto> objPropiedad) {
        this.objPropiedad = objPropiedad;
    }

    //toString


    @Override
    public String toString() {
        return "Coleccionista: \n" + super.toString()+
                "\n puntuacion=" + puntuacion +
                "\n direccion=" + direccion +
                "\n listInteres=" + listInteres +
                "\n objPropiedad=" + objPropiedad;
    }

    @Override
    public String mostrarRol() {
        return "Coleccionista";
    }
}
