package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.logica.excepciones.EdadInsuficienteException;
import cr.ac.ucenfotec.logica.excepciones.OfertaInvalidaException;
import cr.ac.ucenfotec.logica.excepciones.UsuarioNoAutorizadoException;
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
                System.out.println(u.mostrarRol());
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
                return;
            }
            usuarios.add(moderador);

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

                opcionElegida = leerEntero(input);

                switch (opcionElegida) {
                    case 1:
                        //caso del registro usuarios
                        System.out.println("--- REGISTRO DE USUARIOS ---");
                        System.out.println("Cual usuario deseas ser?  \n 1. Vendedor \n 2. Coleccionista");
                        int opcUsuario = leerEntero(input);

                        switch (opcUsuario) {
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

                                Vendedor vendedor = new Vendedor(nombreVendedor, apellidoVendedor, fechaVendedor, correoVendedor
                                        , contraseñaVendedor, puntuacionVendedor, direccionVendedor);
                                try {
                                    vendedor.validarEdad();
                                }catch (EdadInsuficienteException e){
                                    System.out.println(e.getMessage());
                                    return ;
                                }
                                usuarios.add(vendedor);

                                break;


                            case 2:

                                System.out.println("Ingrese el nombre:");
                                String nombreColeccionista = leerString(input);

                                System.out.println("Ingrese su apellido");
                                String apellidoColecionista = leerString(input);

                                System.out.println("Ingrese su fecha de nacimiento con el siguiente fommato AAAA-MM-DD");
                                LocalDate fechaColecionista = LeerFecha(input);

                                System.out.println("Ingrese su correo");
                                String correoColecionista = leerString(input);

                                System.out.println("Ingrese su contraseña");
                                String contraseñaColecionista = leerString(input);

                                System.out.println("Ingrese su puntuacion del 1-10");
                                int puntuacionColecionista = leerEntero(input);;

                                System.out.println("Ingrese su direccion");
                                String direccionColecionista = leerString(input);




                                // --- OBJETOS DE PROPIEDAD ---
                                ArrayList<Objeto> objColeccionista = new ArrayList<>();

                                System.out.print("¿Cuántos objetos desea agregar a su colección? ");
                                int cantObjetos = leerEntero(input);;

                                for (int i = 0; i < cantObjetos; i++) {
                                    System.out.println("\nObjeto " + (i + 1));

                                    System.out.print("Nombre: ");
                                    String nombreObj = leerString(input);

                                    System.out.print("Descripción: ");
                                    String descObj = leerString(input);

                                    System.out.print("Estado: ");
                                    String estadoObj = leerString(input);

                                    System.out.print("Año de Compra: ");
                                    int yearObj = leerEntero(input);;

                                    System.out.print("Mes de Compra: ");
                                    int mesObj = leerEntero(input);;

                                    System.out.print("Día de Compra: ");
                                    int diaObj = leerEntero(input);;

                                    LocalDate fechaCompraObj = LocalDate.of(yearObj, mesObj, diaObj);
                                    objColeccionista.add(new Objeto(nombreObj, descObj, estadoObj, fechaCompraObj));
                                }

                                // --- LISTA DE INTERESES ---
                                ArrayList<Objeto> intereses = new ArrayList<>();

                                // Recolectar objetos disponibles de otros coleccionistas ya registrados
                                ArrayList<Objeto> objetosDisponibles = new ArrayList<>();
                                for (Usuario u : usuarios) {
                                    if (u instanceof Coleccionista) {
                                        Coleccionista otroCol = (Coleccionista) u;
                                        if (otroCol.getObjPropiedad() != null && !otroCol.getObjPropiedad().isEmpty()) {
                                            objetosDisponibles.addAll(otroCol.getObjPropiedad());
                                        }
                                    }
                                }

                                if (objetosDisponibles.isEmpty()) {
                                    System.out.println("No hay objetos de otros coleccionistas disponibles para agregar como interés.");
                                } else {
                                    System.out.println("\nObjetos disponibles para agregar como interés:");
                                    for (int i = 0; i < objetosDisponibles.size(); i++) {
                                        System.out.println(i + ". " + objetosDisponibles.get(i).getNombre()
                                                + " - " + objetosDisponibles.get(i).getDescripcion());
                                    }

                                    System.out.print("¿Cuántos intereses desea agregar? ");
                                    int cantIntereses = leerEntero(input);;

                                    for (int i = 0; i < cantIntereses; i++) {
                                        System.out.print("Seleccione el índice del objeto de interés: ");
                                        int indiceInteres =leerEntero(input);;

                                        if (indiceInteres < 0 || indiceInteres >= objetosDisponibles.size()) {
                                            System.out.println("Índice inválido, se omite.");
                                            continue;
                                        }

                                        Objeto objetoInteres = objetosDisponibles.get(indiceInteres);

                                        if (intereses.contains(objetoInteres)) {
                                            System.out.println("Ese objeto ya está en su lista de intereses.");
                                        } else {
                                            intereses.add(objetoInteres);
                                            System.out.println("Interés agregado: " + objetoInteres.getNombre());
                                        }
                                    }
                                }

                                Coleccionista coleccionista = new Coleccionista(
                                        nombreColeccionista, apellidoColecionista, fechaColecionista,
                                        correoColecionista, contraseñaColecionista, puntuacionColecionista,
                                        direccionColecionista, intereses, objColeccionista
                                );

                                try {
                                    coleccionista.validarEdad();
                                }catch (EdadInsuficienteException e){
                                    System.out.println(e.getMessage());
                                    return ;
                                }
                                usuarios.add(coleccionista);


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
                        double precioMinimo = leerDouble(input);

                        System.out.print("Días Hasta que Cierre la Subasta: ");
                        int dias = leerEntero(input);;

                        LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);

                        System.out.print("¿Cuántos Objetos Desea Agregar? ");
                        int cantidadObjetos = leerEntero(input);

                        // Regla 6: No se puede crear subasta sin objetos
                        if (cantidadObjetos <= 0) {
                            System.out.println("Debe agregar al menos un objeto a la subasta.");
                            break;
                        }

                        ArrayList<Objeto> objetos = new ArrayList<>();

                        for (int i = 0; i < cantidadObjetos; i++) {

                            System.out.println("\nObjeto Número " + (i + 1));

                            System.out.print("Nombre: ");
                            String nombre = leerString(input);

                            System.out.print("Descripción: ");
                            String descripcion = leerString(input);

                            System.out.print("Estado: ");
                            String estado = leerString(input);

                            System.out.print("Año de Compra: ");
                            int year = leerEntero(input);

                            System.out.print("Mes de Compra: ");
                            int mes = leerEntero(input);;

                            System.out.print("Día de Compra: ");
                            int dia = leerEntero(input);;

                            LocalDate fechaCompra = LocalDate.of(year, mes, dia);

                            objetos.add(new Objeto(nombre, descripcion, estado, fechaCompra));
                        }

                        // Seleccionar creador
                        System.out.println("Seleccione el Usuario que Creará la Subasta");

                        for (int i = 0; i < usuarios.size(); i++) {
                            Usuario u = usuarios.get(i);
                            System.out.println(i + ". " + u.getNombre() + " " + u.getApellido());
                        }

                        int indiceUsuario = leerEntero(input);

                        if (indiceUsuario < 0 || indiceUsuario >= usuarios.size()) {
                            System.out.println("Índice inválido.");
                            break;
                        }

                        Usuario creador = usuarios.get(indiceUsuario);

                        // Regla 3: El moderador no puede crear subastas

                        try {
                            if (creador instanceof  Moderador){
                                throw new UsuarioNoAutorizadoException("El moderador no puede crear ni participar en subastas");
                            }
                            // Regla 9: Si es coleccionista, los objetos deben pertenecer a su colección
                            if (creador instanceof Coleccionista) {

                                Coleccionista c = (Coleccionista) creador;

                                if (c.getObjPropiedad() == null || c.getObjPropiedad().isEmpty()) {
                                    System.out.println("El coleccionista no tiene objetos registrados en su colección.");
                                    break;
                                }

                                boolean objetosValidos = true;

                                for (Objeto o : objetos) {

                                    boolean encontrado = false;

                                    for (Objeto objPropio : c.getObjPropiedad()) {
                                        if (objPropio.getNombre().equals(o.getNombre())) {
                                            encontrado = true;
                                            break;
                                        }
                                    }

                                    if (!encontrado) {
                                        System.out.println("El objeto '" + o.getNombre() + "' no pertenece a la colección del coleccionista.");
                                        objetosValidos = false;
                                        break;
                                    }
                                }

                                if (!objetosValidos) break;
                            }

                            gestorSubastas.crearSubasta(
                                    fechaVencimiento,
                                    creador,
                                    precioMinimo,
                                    objetos
                            );

                            System.out.println("Subasta creada correctamente por " + creador.getNombre());
                        }catch (UsuarioNoAutorizadoException e){
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 4:

                        // Imprime en consola una lista de todas las subastas del programa

                        System.out.println("\n--- LISTA DE SUBASTAS ---");

                        gestorSubastas.listarSubastas();

                        break;


                    case 5:
                        System.out.println("\n--- CREAR OFERTA ---");
                        System.out.println("Seleccione el coleccionista:");

                        // Lista separada solo con coleccionistas
                        ArrayList<Coleccionista> coleccionistas = new ArrayList<>();

                        for (Usuario u : usuarios) {
                            if (u instanceof Coleccionista) {
                                coleccionistas.add((Coleccionista) u);
                            }
                        }

                        if (coleccionistas.isEmpty()) {
                            System.out.println("No hay coleccionistas registrados.");
                            break;
                        }

                        // Mostrar solo coleccionistas con índices propios
                        for (int i = 0; i < coleccionistas.size(); i++) {
                            System.out.println(i + ". " + coleccionistas.get(i).getNombre());
                        }

                        int opcColeccionista = leerEntero(input);

                        // Validar que el índice esté dentro del rango
                        if (opcColeccionista < 0 || opcColeccionista >= coleccionistas.size()) {
                            System.out.println("Opción inválida. Solo los coleccionistas pueden hacer ofertas.");
                            break;
                        }

                        Coleccionista coleccionista = coleccionistas.get(opcColeccionista);

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

                        int indice = leerEntero(input);

                        Subasta subastaSeleccionadaOferta = gestorSubastas.getSubastas().get(indice);

                        if (subastaSeleccionadaOferta.getCreador() instanceof Coleccionista) {

                            Coleccionista creadorSubasta = (Coleccionista) subastaSeleccionadaOferta.getCreador();

                            if (creadorSubasta.getNombre().equals(coleccionista.getNombre())) {
                                System.out.println("El creador de la subasta no puede ofertar en su propia subasta.");
                                break;
                            }
                        }

                        System.out.println("Ingrese el precio ofertado:");
                        double precio = leerDouble(input);
                        try {
                            if (subastaSeleccionadaOferta.getPrecioMinimo() > precio){
                                throw new OfertaInvalidaException("No se puede ofertar menos que el precio minimo");
                            }
                            gestorSubastas.crearOferta(coleccionista, indice, precio);
                            System.out.println("Oferta creada correctamente.");
                        }catch (OfertaInvalidaException e){
                            System.out.println(e.getMessage());
                        }



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

                        int indiceSubasta = leerEntero(input);

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
