package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.exception.SubastaInvalidaException;
import cr.ac.ucenfotec.bl.exception.OfertaInvalidaException;
import cr.ac.ucenfotec.bl.exception.UsuarioNoAutorizadoException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorSubastas {

    private ArrayList<Subasta> subastas;

    public GestorSubastas() {
        subastas = new ArrayList<>();
    }

    // Crea la subasta
    public void crearSubasta(LocalDateTime fechaVencimiento,
                             Usuario creador,
                             double precioMinimo,
                             ArrayList<Objeto> objetos) {

        // Validar objetos
        if (objetos == null || objetos.isEmpty()){
            throw new SubastaInvalidaException("No puedes crear una subasta sin objetos");
        }

        // Moderador no puede crear subastas
        if (creador instanceof Moderador){
            throw new UsuarioNoAutorizadoException("El moderador no puede crear subastas");
        }

        // Coleccionista solo puede usar objetos propios
        if (creador instanceof Coleccionista){

            Coleccionista c = (Coleccionista) creador;

            if (c.getObjPropiedad() == null || c.getObjPropiedad().isEmpty()){
                throw new SubastaInvalidaException("El coleccionista no tiene objetos en su colección");
            }

            for (Objeto o : objetos){

                boolean encontrado = false;

                for (Objeto propio : c.getObjPropiedad()){
                    if (propio.getNombre().equals(o.getNombre())){
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado){
                    throw new SubastaInvalidaException(
                            "El objeto '" + o.getNombre() + "' no pertenece a la colección del coleccionista"
                    );
                }
            }
        }

        // Crear subasta
        Subasta nueva = new Subasta(fechaVencimiento, creador, precioMinimo);

        for (Objeto o : objetos) {
            nueva.agregarObjeto(o);
        }

        subastas.add(nueva);
    }

    // Lista subastas
    public void listarSubastas() {

        try {
            if (subastas.isEmpty()){
                throw new SubastaInvalidaException("No hay subastas registradas");
            }
        } catch (SubastaInvalidaException e) {
            System.out.println(e.getMessage());
            return;
        }

        for (Subasta s : subastas) {
            System.out.println("--------------------");
            System.out.println(s);
        }
    }

    // Crear oferta (VALIDADO)
    public void crearOferta(Coleccionista coleccionista, int indiceSubasta, double precio){

        if (subastas.isEmpty()){
            throw new SubastaInvalidaException("No hay subastas disponibles");
        }

        // Validar índice
        if (indiceSubasta < 0 || indiceSubasta >= subastas.size()) {
            throw new SubastaInvalidaException("Índice de subasta inválido");
        }

        Subasta subasta = subastas.get(indiceSubasta);

        // 🔒 Validar estado
        if (subasta.getEstado().equals("CERRADA")) {
            throw new OfertaInvalidaException("No se puede ofertar en una subasta cerrada");
        }

        // 💰 Validar precio mínimo
        if (precio < subasta.getPrecioMinimo()) {
            throw new OfertaInvalidaException("El precio ofertado no puede ser menor al precio mínimo de la subasta");
        }

        Oferta oferta = new Oferta(
                coleccionista,
                precio
        );

        subasta.getOfertas().add(oferta);

        System.out.println("Oferta registrada correctamente.");
    }

    public void cerrarSubasta(int indiceSubasta){

        if (subastas.isEmpty()){
            throw new SubastaInvalidaException("No hay subastas disponibles");
        }

        if (indiceSubasta < 0 || indiceSubasta >= subastas.size()) {
            throw new SubastaInvalidaException("Índice de subasta inválido");
        }

        Subasta subasta = subastas.get(indiceSubasta);

        if (subasta.getEstado().equals("CERRADA")){
            System.out.println("La subasta ya está cerrada.");
            return;
        }

        subasta.cerrarSubasta();

        System.out.println("Subasta cerrada correctamente.");

        // Ganador
        Oferta ganadora = subasta.getOfertaGanadora();

        if (ganadora == null) {
            System.out.println("La subasta terminó sin ofertas.");
        } else {
            System.out.println("Oferta ganadora:");
            System.out.println(ganadora);
        }
    }

    public ArrayList<Subasta> getSubastas() {
        return subastas;
    }
}