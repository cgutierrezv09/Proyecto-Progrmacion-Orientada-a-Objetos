package cr.ac.ucenfotec.ti;

import cr.ac.ucenfotec.bl.dao.DAOModerador;
import cr.ac.ucenfotec.bl.entities.Moderador;
import cr.ac.ucenfotec.bl.entities.Vendedor;
import cr.ac.ucenfotec.bl.exception.EdadInsuficienteException;
import cr.ac.ucenfotec.bl.logic.GestorModerador;
import cr.ac.ucenfotec.bl.logic.GestorVendedor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;

public class Controlador {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //verifiacion de los datos
    public static int leerEntero(BufferedReader input) {
        do {
            try {
                return Integer.parseInt(input.readLine());
            } catch (Exception e) {
                System.out.println("Ingrese un número entero válido");
            }
        } while (true);
    }

    public static String leerString(BufferedReader input){
        do {
            try {
                return input.readLine();
            }catch (Exception e){
                System.out.println("Ingrese carcteres valido");
            }

        }while (true);
    }

    public static double leerDouble(BufferedReader input){
        do {
            try {
                return Double.parseDouble(input.readLine());
            }catch (Exception e){
                System.out.println("Ingrese el decimal de manera correcta");
            }
        }while (true);
    }

    public static LocalDate LeerFecha(BufferedReader input){
        do {
            try {
                return LocalDate.parse(input.readLine());
            }catch (Exception e){
                System.out.println("Ingrese la fecha correctamente");
            }
        }while (true);
    }

    public static boolean registrarMod() throws SQLException, IOException, ClassNotFoundException {
        if (DAOModerador.existeMod()) {

            return true;
        }
        System.out.println("---REGISTRO MODERADOR---");
        System.out.println("Ingrese el nombre:");
        String nombreMod = leerString(input);

        System.out.println("Ingrese su apellido");
        String apellido = leerString(input);

        System.out.println("Ingrese su fecha de nacimiento con el siguiente fommato AAAA-MM-DD");
        LocalDate fecha = LeerFecha(input);

        System.out.println("Ingrese su correo");
        String correo = leerString(input);

        System.out.println("Ingrese su contraseña");
        String contraseña =leerString(input);

        Moderador moderador = new Moderador(nombreMod, apellido, fecha, contraseña, correo);

        try {
            moderador.validarEdad();
        } catch (EdadInsuficienteException e) {
            System.out.println(e.getMessage());
            return false;
        }


        System.out.println(GestorModerador.registrarMod(nombreMod,apellido,fecha,contraseña,correo));
        return true;
    }

    public static void registrarUsuario(){

        System.out.println("--- REGISTRO DE USUARIOS ---");
        System.out.println("Cual usuario deseas ser?  \n 1. Vendedor \n 2. Coleccionista ");
        int opcUsuario = leerEntero(input);

        switch (opcUsuario){
            case 1:
            System.out.println("Ingrese el nombre:");
            String nombreVendedor = leerString(input);

            System.out.println("Ingrese su apellido");
            String apellidoVendedor = leerString(input);

            System.out.println("Ingrese su fecha de nacimiento con el siguiente fommato AAAA-MM-DD");
            LocalDate fechaVendedor = LeerFecha(input);

            System.out.println("Ingrese su correo");
            String correoVendedor = leerString(input);

            System.out.println("Ingrese su contraseña");
            String contraseñaVendedor = leerString(input);

            System.out.println("Ingrese su puntuacion del 1-10");
            int puntuacionVendedor = leerEntero(input);;

            System.out.println("Ingrese su direccion");
            String direccionVendedor = leerString(input);

              Vendedor vendedor = new Vendedor(nombreVendedor, apellidoVendedor, fechaVendedor,
                      contraseñaVendedor, correoVendedor,puntuacionVendedor,direccionVendedor);

                try {
                    vendedor.validarEdad();
                } catch (EdadInsuficienteException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                //System.out.println(GestorVendedor.registrarVendedor(nombreVendedor,apellidoVendedor,fechaVendedor,contraseñaVendedor,
                        //correoVendedor,puntuacionVendedor,direccionVendedor));


            break;

            case 2:

                break;
            default:
                System.out.println("Opcion invalida");
        }
    }
}
