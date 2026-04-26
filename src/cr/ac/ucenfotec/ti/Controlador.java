package cr.ac.ucenfotec.ti;

import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.exception.EdadInsuficienteException;
import cr.ac.ucenfotec.bl.logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controlador {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // --- Métodos de lectura ---

    public static int leerEntero(BufferedReader input) {
        do {
            try {
                return Integer.parseInt(input.readLine());
            } catch (Exception e) {
                System.out.println("Ingrese un número entero válido");
            }
        } while (true);
    }

    public static String leerString(BufferedReader input) {
        do {
            try {
                return input.readLine();
            } catch (Exception e) {
                System.out.println("Ingrese caracteres válidos");
            }
        } while (true);
    }

    public static double leerDouble(BufferedReader input) {
        do {
            try {
                return Double.parseDouble(input.readLine());
            } catch (Exception e) {
                System.out.println("Ingrese el decimal de manera correcta");
            }
        } while (true);
    }

    public static LocalDate leerFecha(BufferedReader input) {
        do {
            try {
                return LocalDate.parse(input.readLine());
            } catch (Exception e) {
                System.out.println("Ingrese la fecha correctamente (AAAA-MM-DD)");
            }
        } while (true);
    }

    // --- Métodos de negocio delegados a gestores ---

    public static boolean registrarMod() throws SQLException, IOException, ClassNotFoundException {
        if (GestorModerador.existeMod()) return true;

        System.out.println("--- REGISTRO MODERADOR ---");
        System.out.println("Ingrese el nombre:");
        String nombre = leerString(input);
        System.out.println("Ingrese su apellido:");
        String apellido = leerString(input);
        System.out.println("Ingrese su fecha de nacimiento (AAAA-MM-DD):");
        LocalDate fecha = leerFecha(input);
        System.out.println("Ingrese su correo:");
        String correo = leerString(input);
        System.out.println("Ingrese su contraseña:");
        String contrasena = leerString(input);

        try {
            System.out.println(GestorModerador.registrarMod(nombre, apellido, fecha, correo, contrasena));
            return true;
        } catch (EdadInsuficienteException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void registrarUsuario() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("--- REGISTRO DE USUARIOS ---");
        System.out.println("¿Qué tipo de usuario deseas registrar?\n1. Vendedor\n2. Coleccionista");
        int opcion = leerEntero(input);

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nombre:");
                String nombreV = leerString(input);
                System.out.println("Ingrese su apellido:");
                String apellidoV = leerString(input);
                System.out.println("Ingrese su fecha de nacimiento (AAAA-MM-DD):");
                LocalDate fechaV = leerFecha(input);
                System.out.println("Ingrese su correo:");
                String correoV = leerString(input);
                System.out.println("Ingrese su contraseña:");
                String contrasenaV = leerString(input);
                System.out.println("Ingrese su puntuación (1-10):");
                int puntuacionV = leerEntero(input);
                System.out.println("Ingrese su dirección:");
                String direccionV = leerString(input);

                try {
                    System.out.println(GestorVendedor.registrarVendedor(
                            nombreV, apellidoV, fechaV, contrasenaV, correoV, puntuacionV, direccionV));
                } catch (EdadInsuficienteException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                System.out.println("Ingrese el nombre:");
                String nombreC = leerString(input);
                System.out.println("Ingrese su apellido:");
                String apellidoC = leerString(input);
                System.out.println("Ingrese su fecha de nacimiento (AAAA-MM-DD):");
                LocalDate fechaC = leerFecha(input);
                System.out.println("Ingrese su correo:");
                String correoC = leerString(input);
                System.out.println("Ingrese su contraseña:");
                String contrasenaC = leerString(input);
                System.out.println("Ingrese su puntuación (1-10):");
                int puntuacionC = leerEntero(input);
                System.out.println("Ingrese su dirección:");
                String direccionC = leerString(input);

                try {
                    System.out.println(GestorColeccionista.registrarColeccionista(
                            nombreC, apellidoC, fechaC, contrasenaC, correoC, puntuacionC, direccionC));
                } catch (EdadInsuficienteException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    public static void listarUsuarios() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Usuario> usuarios = GestorUsuario.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println("--------------------");
            System.out.println(u.mostrarRol());
            System.out.println(u);
        }
    }

    public static void crearSubasta() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("--- CREAR SUBASTA ---");
        System.out.println("Precio mínimo:");
        double precioMinimo = leerDouble(input);
        System.out.println("Días hasta que cierre la subasta:");
        int dias = leerEntero(input);
        LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);
        System.out.println("¿Cuántos objetos desea agregar?");
        int cantObjetos = leerEntero(input);

        if (cantObjetos <= 0) {
            System.out.println("Debe agregar al menos un objeto.");
            return;
        }

        System.out.println("Seleccione el ID del usuario creador:");
        ArrayList<Usuario> usuarios = GestorUsuario.listarUsuarios();
        for (Usuario u : usuarios) {
            System.out.println(u.getId() + ". " + u.getNombre() + " " + u.getApellido()
                    + " (" + u.mostrarRol() + ")");
        }
        int idCreador = leerEntero(input);

        try {
            System.out.println(GestorSubastas.crearSubasta(
                    fechaVencimiento, idCreador, precioMinimo, cantObjetos, input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarSubastas() throws SQLException, IOException, ClassNotFoundException {
        GestorSubastas.listarSubastas();
    }

    public static void crearOferta() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("--- CREAR OFERTA ---");
        System.out.println("Seleccione el ID del coleccionista:");
        ArrayList<Usuario> usuarios = GestorUsuario.listarUsuarios();
        for (Usuario u : usuarios) {
            System.out.println(u.getId() + ". " + u.getNombre() + " (" + u.mostrarRol() + ")");
        }
        int idColeccionista = leerEntero(input);

        System.out.println("Seleccione el ID de la subasta:");
        GestorSubastas.listarSubastas();
        int idSubasta = leerEntero(input);

        System.out.println("Ingrese el precio ofertado:");
        double precio = leerDouble(input);

        try {
            GestorOferta.crearOferta(idColeccionista, idSubasta, precio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarOfertas() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("--- LISTAR OFERTAS ---");
        System.out.println("Seleccione el ID de la subasta:");
        GestorSubastas.listarSubastas();
        int idSubasta = leerEntero(input);
        GestorOferta.listarOfertasPorSubasta(idSubasta);
    }

    public static void cerrarSubasta() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("--- CERRAR SUBASTA ---");
        System.out.println("Seleccione el ID de la subasta a cerrar:");
        GestorSubastas.listarSubastas();
        int idSubasta = leerEntero(input);
        try {
            GestorSubastas.cerrarSubasta(idSubasta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}