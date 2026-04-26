package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.ti.Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static cr.ac.ucenfotec.ti.Controlador.leerEntero;

public class Menu {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void menuPrincipal() throws SQLException, IOException, ClassNotFoundException {

        // Registro obligatorio del moderador antes de continuar
        while (!Controlador.registrarMod()) {
            System.out.println("Debe registrar un moderador válido para continuar.");
        }

        int opc;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Crear Subasta");
            System.out.println("4. Listar Subastas");
            System.out.println("5. Crear Oferta");
            System.out.println("6. Listar Ofertas");
            System.out.println("7. Cerrar Subasta");
            System.out.println("0. Salir");
            opc = leerEntero(input);

            switch (opc) {
                case 1:
                    Controlador.registrarUsuario();
                    break;
                case 2:
                    Controlador.listarUsuarios();
                    break;
                case 3:
                    Controlador.crearSubasta();
                    break;
                case 4:
                    Controlador.listarSubastas();
                    break;
                case 5:
                    Controlador.crearOferta();
                    break;
                case 6:
                    Controlador.listarOfertas();
                    break;
                case 7:
                    Controlador.cerrarSubasta();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opc != 0);
    }
}