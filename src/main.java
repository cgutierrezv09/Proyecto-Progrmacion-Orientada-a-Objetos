import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class main {

    //entrada para los usuarios
    public static BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in));


    //Verificacion si ya existe un moderador
    public static boolean existeMod(ArrayList<Usuario> usuarios){
        for (Usuario usuario: usuarios){
            if (usuario instanceof Moderador){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)throws IOException{

        ArrayList<Usuario> usuarios = new ArrayList<>();


        if (existeMod(usuarios)){
            System.out.println("Ya existe un moderador en el sistema.");
        }else {
            System.out.println("---REGISTRO MODERADOR---");
            System.out.println("Ingrese el nombre:");
            String nombre=entrada.readLine();

            System.out.println("Ingrese su apellido");
            String apellido=entrada.readLine();

            System.out.println("Ingrese su fecha de nacimiento con el siguiente fommato AAAA-MM-DD");
            LocalDate fecha=LocalDate.parse(entrada.readLine());

            System.out.println("Ingrese su correo");
            String correo=entrada.readLine();

            System.out.println("Ingrese su contraseña");
            String contraseña=entrada.readLine();

            Moderador moderador=new Moderador(nombre,apellido,fecha,contraseña,correo);

            if (moderador.calcularEdad()< 18){
                System.out.println("El moderador debe de ser mayor de edad ");
            }else {
                usuarios.add(moderador);
                System.out.println("Moderador registrado correctamente");
            }


        }

    }
}
