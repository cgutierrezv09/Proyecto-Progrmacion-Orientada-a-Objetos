package cr.ac.ucenfotec.logica.gestor;

import cr.ac.ucenfotec.logica.modelo.Oferta;
import cr.ac.ucenfotec.logica.modelo.Subasta;
import cr.ac.ucenfotec.logica.modelo.Objeto;
import cr.ac.ucenfotec.logica.modelo.Usuario;
import cr.ac.ucenfotec.tipoUsuario.Coleccionista;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorSubastas {

    // Aqui se guardan todas las subastas creadas durante la ejecucion del programa
    private ArrayList<Subasta> subastas;

    public GestorSubastas() {
        subastas = new ArrayList<>();
    }

    // Crea la subasta
    public void crearSubasta(LocalDateTime fechaVencimiento,
                             Usuario creador,
                             double precioMinimo,
                             ArrayList<Objeto> objetos) {

        // Si no hay objetos para subastar, no hay subasta
        if (objetos == null || objetos.isEmpty()) {
            System.out.println("No se puede crear una subasta sin objetos");
            return;
        }

        Subasta nueva = new Subasta(fechaVencimiento, creador, precioMinimo);

        for (Objeto o : objetos) {
            nueva.agregarObjeto(o);
        }

        subastas.add(nueva);
    }

    // Imprime todas las subastas registradas en el sistema
    public void listarSubastas() {

        if (subastas.isEmpty()) {
            System.out.println("No hay subastas registradas");
            return;
        }

        for (Subasta s : subastas) {
            System.out.println("--------------------");
            System.out.println(s);
        }

    }

    // se crea la oferta desde aca
    public void crearOferta(Coleccionista coleccionista, int indiceSubasta, double precio){

        if(subastas.isEmpty()){
            System.out.println("No hay subastas disponibles.");
            return;
        }

        Subasta subasta = subastas.get(indiceSubasta);

        Oferta oferta = new Oferta(
                coleccionista.getNombre(),
                coleccionista.getPuntuacion(),
                precio
        );

        subasta.getOfertas().add(oferta);

        System.out.println("Oferta registrada correctamente.");
    }

    public ArrayList<Subasta> getSubastas() {
        return subastas;
    }
}