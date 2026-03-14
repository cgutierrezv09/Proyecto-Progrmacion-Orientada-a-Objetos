package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.tipoUsuario.*;
import cr.ac.ucenfotec.logica.gestor.GestorSubastas;
import cr.ac.ucenfotec.logica.modelo.Objeto;
import cr.ac.ucenfotec.logica.modelo.Subasta;
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



    //Verificacion si ya existe un moderador
    public static boolean existeMod(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Moderador) {
                return true;
            }
        }
        return false;
    }

    //Metodo para hacer una lista de usuarios
    public static void listadoUsuarios(ArrayList <Usuario> usuarios){

        if (usuarios.isEmpty()){
            System.out.println("La lista de usuarios esta vacia ");
            return;
        }else
            for (Usuario u: usuarios){
                if (u instanceof Moderador){
                    System.out.println("Tipo de usuario: Moderador");
                }else if(u instanceof Vendedor){
                    System.out.println("Tipo de usuario: Vendedor");
                }else if(u instanceof Coleccionista){
                    System.out.println("Tipo de usuario: Coleccionista");
                }
                System.out.println(u);
                System.out.println("--------------------");
            }
    }



    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        GestorSubastas gestorSubastas = new GestorSubastas();

        int opcionElegida;
        ArrayList<Usuario> usuarios = new ArrayList<>();

        //Registro del primer usuario el cual debe de ser el mod
        if (existeMod(usuarios)) {
            System.out.println("Ya existe un moderador en el sistema.");
        } else {
            System.out.println("---REGISTRO MODERADOR---");
            System.out.println("Ingrese el nombre:");
            String nombreMod = input.readLine();

            System.out.println("Ingrese su apellido");
            String apellido = input.readLine();

            System.out.println("Ingrese su fecha de nacimiento con el siguiente fommato AAAA-MM-DD");
            LocalDate fecha = LocalDate.parse(input.readLine());

            System.out.println("Ingrese su correo");
            String correo = input.readLine();

            System.out.println("Ingrese su contraseña");
            String contraseña = input.readLine();

            Moderador moderador = new Moderador(nombreMod, apellido, fecha, contraseña, correo);

            if (moderador.calcularEdad() < 18) {
                System.out.println("El moderador debe de ser mayor de edad ");
            } else {
                usuarios.add(moderador);
                System.out.println("Moderador registrado correctamente");
            }

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
                        //caso del registro usuarios
                        System.out.println("--- REGISTRO DE USUARIOS ---");
                        System.out.println("Cual usuario deseas ser?  \n 1. Vendedor \n 2. Coleccionista");
                        int opcUsuario = Integer.parseInt(input.readLine());

                        switch (opcUsuario) {
                            case 1:

                                System.out.println("Ingrese el nombre:");
                                String nombreVendedor = input.readLine();

                                System.out.println("Ingrese su apellido");
                                String apellidoVendedor = input.readLine();

                                System.out.println("Ingrese su fecha de nacimiento con el siguiente fommato AAAA-MM-DD");
                                LocalDate fechaVendedor = LocalDate.parse(input.readLine());

                                System.out.println("Ingrese su correo");
                                String correoVendedor = input.readLine();

                                System.out.println("Ingrese su contraseña");
                                String contraseñaVendedor = input.readLine();

                                System.out.println("Ingrese su puntuacion del 1-10");
                                int puntuacionVendedor = Integer.parseInt(input.readLine());

                                System.out.println("Ingrese su direccion");
                                String direccionVendedor = input.readLine();

                                Vendedor vendedor = new Vendedor(nombreVendedor, apellidoVendedor, fechaVendedor, correoVendedor
                                        , contraseñaVendedor, puntuacionVendedor, direccionVendedor);
                                if (vendedor.calcularEdad() < 18) {
                                    System.out.println("El Vendedor debe de ser mayor de edad ");
                                } else {
                                    usuarios.add(vendedor);
                                    System.out.println("Vendedor registrado correctamente");
                                }

                                break;


                            case 2:

                                System.out.println("Ingrese el nombre:");
                                String nombreColeccionista = input.readLine();

                                System.out.println("Ingrese su apellido");
                                String apellidoColecionista = input.readLine();

                                System.out.println("Ingrese su fecha de nacimiento con el siguiente fommato AAAA-MM-DD");
                                LocalDate fechaColecionista = LocalDate.parse(input.readLine());

                                System.out.println("Ingrese su correo");
                                String correoColecionista = input.readLine();

                                System.out.println("Ingrese su contraseña");
                                String contraseñaColecionista = input.readLine();

                                System.out.println("Ingrese su puntuacion del 1-10");
                                int puntuacionColecionista = Integer.parseInt(input.readLine());

                                System.out.println("Ingrese su direccion");
                                String direccionColecionista = input.readLine();


                                Coleccionista coleccionista = new Coleccionista(nombreColeccionista, apellidoColecionista, fechaColecionista, correoColecionista
                                        , contraseñaColecionista, puntuacionColecionista, direccionColecionista, null, null);

                                if (coleccionista.calcularEdad() < 18) {
                                    System.out.println("El Vendedor debe de ser mayor de edad ");
                                } else {
                                    usuarios.add(coleccionista);
                                    System.out.println("Vendedor registrado correctamente");
                                }

                                break;

                            default:
                                System.out.println("Opccion invalida");
                                break;


                        }
                        break;

                    case 2:
                        listadoUsuarios(usuarios);
                        break;


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
                        // gestorSubastas.crearSubasta(
                        //fechaVencimiento,
                        //Hacer logica del usuario que crea la subasta
                        // new Usuario(),
                        // precioMinimo,
                        // objetos
                        // );

                        System.out.println("Subasta Creada Correctamente");

                        break;

                    case 4:

                        // Imprime en consola una lista de todas las subastas del programa

                        System.out.println("\n--- LISTA DE SUBASTAS ---");

                        gestorSubastas.listarSubastas();

                        break;


                    case 5:

                        System.out.println("\n--- CREAR OFERTA ---");

                        System.out.println("Seleccione el coleccionista:");

                        boolean hayColeccionista = false;
                        int index = 0;

                        for (Usuario u : usuarios) {

                            if (u instanceof Coleccionista) {
                                System.out.println(index + ". " + u.getNombre());
                                hayColeccionista = true;
                            }

                            index++;
                        }

                        if (!hayColeccionista) {
                            System.out.println("No hay coleccionistas registrados.");
                            break;
                        }

                        int opcColeccionista = Integer.parseInt(input.readLine());
                        Usuario usuarioSeleccion = usuarios.get(opcColeccionista);

                        // Validar que sea coleccionista
                        if (!(usuarioSeleccion instanceof Coleccionista)) {
                            System.out.println("Solo los coleccionistas pueden hacer ofertas");
                            break;
                        }

                        Coleccionista coleccionista = (Coleccionista) usuarioSeleccion;

                        // Verificar que existan subastas
                        if (gestorSubastas.getSubastas().isEmpty()) {
                            System.out.println("No hay subastas disponibles.");
                            break;
                        }

                        // Mostrar subastas
                        System.out.println("Seleccione la subasta:");

                        for (int i = 0; i < gestorSubastas.getSubastas().size(); i++) {
                            System.out.println(i + ". " + gestorSubastas.getSubastas().get(i));
                        }

                        int indice = Integer.parseInt(input.readLine());

                        // Pedir precio
                        System.out.println("Ingrese el precio ofertado:");
                        double precio = Double.parseDouble(input.readLine());

                        // Crear oferta usando el gestor
                        gestorSubastas.crearOferta(coleccionista, indice, precio);

                        System.out.println("Oferta creada correctamente.");

                        break;

                    case 6:
                        System.out.println("\n--- LISTAR OFERTAS ---");

                        // Verificar si hay subastas
                        if (gestorSubastas.getSubastas().isEmpty()) {
                            System.out.println("No hay subastas registradas.");
                            break;
                        }

                        // Mostrar subastas disponibles
                        System.out.println("Seleccione la subasta:");

                        for (int i = 0; i < gestorSubastas.getSubastas().size(); i++) {
                            System.out.println(i + ". " + gestorSubastas.getSubastas().get(i));
                        }

                        int indiceSubasta = Integer.parseInt(input.readLine());

                        Subasta subastaSeleccionada =
                                gestorSubastas.getSubastas().get(indiceSubasta);

                        // Verificar si la subasta tiene ofertas
                        if (subastaSeleccionada.getOfertas().isEmpty()) {
                            System.out.println("Esta subasta no tiene ofertas.");
                        } else {

                            System.out.println("\n--- OFERTAS DE LA SUBASTA ---");

                            for (var oferta : subastaSeleccionada.getOfertas()) {
                                System.out.println(oferta);
                                System.out.println("-------------------");
                            }
                        }

                        break;

                }



            } while (opcionElegida != 0);

        }
    }
}