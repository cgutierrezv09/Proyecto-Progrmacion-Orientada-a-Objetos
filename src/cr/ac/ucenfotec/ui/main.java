package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.logica.gestor.GestorSubastas;
import cr.ac.ucenfotec.logica.modelo.*;

//Gestionar fechas y periodos de tiempo
import java.time.LocalDate;
import java.time.LocalDateTime;
//Guardar objetos y subastas localmente
import java.util.ArrayList;
//Permitir la entrada de datos
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        GestorSubastas gestorSubastas = new GestorSubastas();

        int opcionElegida;

        //Menu de Opciones
        do {

            System.out.println("\n--- MENU ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Crear Subasta");
            System.out.println("4. Listar Subastas");
            System.out.println("5. Crear Oferta");
            System.out.println("6. Listar Ofertas");
            System.out.println("0. Salir");

            opcionElegida = entrada.nextInt();
            entrada.nextLine();

            switch (opcionElegida) {

                case 3:

                    System.out.println("\n--- CREAR SUBASTA ---");

                    //Hola, esto es mientras creas el usuario Chris
                    Usuario usuarioProvisional = new Usuario();

                    // Pide al usuario los datos por medio de consola
                    System.out.print("Precio Mínimo: ");
                    double precioMinimo = entrada.nextDouble();
                    entrada.nextLine();

                    System.out.print("Días Hasta que Cierre la Subasta: ");
                    int dias = entrada.nextInt();
                    entrada.nextLine();

                    LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);

                    System.out.print("¿Cuántos Objetos Desea Agregar? ");
                    int cantidadObjetos = entrada.nextInt();
                    entrada.nextLine();

                    ArrayList<Objeto> objetos = new ArrayList<>();

                    // Aqui empieza a construir la cantidad de objetos que el usuario haya pedido
                    for (int i = 0; i < cantidadObjetos; i++) {

                        System.out.println("\nObjeto Número " + (i + 1));

                        System.out.print("Nombre: ");
                        String nombre = entrada.nextLine();

                        System.out.print("Descripción: ");
                        String descripcion = entrada.nextLine();

                        System.out.print("Estado: ");
                        String estado = entrada.nextLine();

                        System.out.print("Año de Compra: ");
                        int year = entrada.nextInt();

                        System.out.print("Mes de Compra: ");
                        int mes = entrada.nextInt();

                        System.out.print("Día de Compra: ");
                        int dia = entrada.nextInt();
                        entrada.nextLine();

                        LocalDate fechaCompra =
                                LocalDate.of(year, mes, dia);

                        // Al tener todos los datos, crea al objeto
                        Objeto objeto = new Objeto(
                                nombre,
                                descripcion,
                                estado,
                                fechaCompra
                        );

                        // Agrega al objeto, para despues repetir el proceso si es necesario
                        objetos.add(objeto);

                    }

                    // Crea una subasta reciclando datos previamente proporcionados y el nuevo objeto
                    gestorSubastas.crearSubasta(
                            fechaVencimiento,
                            usuarioProvisional,
                            precioMinimo,
                            objetos
                    );

                    System.out.println("Subasta Creada Correctamente");

                    break;

                case 4:

                    // Imprime en consola una lista de todas las subastas del programa

                    System.out.println("\n--- LISTA DE SUBASTAS ---");

                    gestorSubastas.listarSubastas();

                    break;

            }

        } while (opcionElegida != 0);

    }
}