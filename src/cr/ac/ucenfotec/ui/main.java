package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.logica.gestor.GestorSubastas;
import cr.ac.ucenfotec.logica.modelo.*;
//Leer datos
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//Gestionar fechas y periodos de tiempo
import java.time.LocalDate;
import java.time.LocalDateTime;
//Guardar objetos y subastas localmente
import java.util.ArrayList;


public class main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        GestorSubastas gestorSubastas = new GestorSubastas();

        int opcionElegida;
        ArrayList<Usuario> usuarios = new ArrayList<>();

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

            opcionElegida = Integer.parseInt(input.readLine());

            switch (opcionElegida) {
                case 1:
                    //Donde se guardan los usuarios?
                    //ArrayList<Usuario> usuarios = new ArrayList<>();
                    //usuarios.add(nuevoUsuario);

                case 3:

                    System.out.println("\n--- CREAR SUBASTA ---");

                    // Pide al usuario los datos por medio de consola
                    System.out.print("Precio Mínimo: ");
                    double precioMinimo = Double.parseDouble(input.readLine());

                    System.out.print("Días Hasta que Cierre la Subasta: ");
                    int dias = Integer.parseInt(input.readLine());

                    LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);

                    System.out.print("¿Cuántos Objetos Desea Agregar? ");
                    int cantidadObjetos = Integer.parseInt(input.readLine());

                    ArrayList<Objeto> objetos = new ArrayList<>();

                    // Aqui empieza a construir la cantidad de objetos que el usuario haya pedido
                    for (int i = 0; i < cantidadObjetos; i++) {

                        System.out.println("\nObjeto Número " + (i + 1));

                        System.out.print("Nombre: ");
                        String nombre = input.readLine();

                        System.out.print("Descripción: ");
                        String descripcion = input.readLine();

                        System.out.print("Estado: ");
                        String estado = input.readLine();

                        System.out.print("Año de Compra: ");
                        int year = Integer.parseInt(input.readLine());

                        System.out.print("Mes de Compra: ");
                        int mes = Integer.parseInt(input.readLine());

                        System.out.print("Día de Compra: ");
                        int dia = Integer.parseInt(input.readLine());

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
                            //Hacer logica del usuario que crea la subasta
                            new Usuario(),
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