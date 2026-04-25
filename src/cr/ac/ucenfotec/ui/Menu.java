package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.dl.Conector;
import cr.ac.ucenfotec.ti.Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;

import static cr.ac.ucenfotec.ti.Controlador.leerEntero;
import static cr.ac.ucenfotec.ti.Controlador.registrarMod;

public class Menu {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void menuPrincipal() throws SQLException, IOException, ClassNotFoundException {


        do {
            registrarMod();
        }while (!registrarMod());


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
            opc=leerEntero(input);

            switch (opc){
                case 1:
                    Controlador.registrarUsuario();
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        }while (opc!=0);

    }
}
