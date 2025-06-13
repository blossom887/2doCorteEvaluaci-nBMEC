package org.boladomiguelevelincitlali.gui.consola;

import org.boladomiguelevelincitlali.model.Artista;
import org.boladomiguelevelincitlali.model.Disco;
import org.boladomiguelevelincitlali.model.Disquera;
import org.boladomiguelevelincitlali.model.Genero;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.ArtistaJdbcImpl;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.DiscoJdbcImpl;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.DisqueraJdbcImpl;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.GeneroJdbcImpl;
import org.boladomiguelevelincitlali.util.ReadUtil;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class DiscoCatalogo extends Catalogos<Disco>{

    private static DiscoCatalogo discoCatalogo;

    public DiscoCatalogo() {
    }

    public static DiscoCatalogo getInstance() {
        if (discoCatalogo == null) {
            discoCatalogo = new DiscoCatalogo();
        }
        return discoCatalogo;
    }

    public Disco newT() {
        return new Disco();
    }

    @Override
    public boolean processNewT(Disco disco) {
        List<Artista> artistas = ArtistaJdbcImpl.getInstance().findAll();
        if (artistas.isEmpty()) {
            System.out.println("No hay artistas en la base de datos. Agrega uno primero.");
            return false;
        }

        System.out.println("Selecciona un artista:");
        for (int i = 0; i < artistas.size(); i++) {
            System.out.println((i + 1) + ". " + artistas.get(i).getArtista());
        }

        int opcion;
        do {
            System.out.print("Opción: ");
            opcion = ReadUtil.readInt();
        } while (opcion < 1 || opcion > artistas.size());
        disco.setArtista(artistas.get(opcion - 1));


        List<Disquera> disqueras = DisqueraJdbcImpl.getInstance().findAll();
        if (disqueras.isEmpty()) {
            System.out.println("No hay disqueras en la base de datos. Agrega una primero.");
            return false;
        }

        System.out.println("Selecciona una disquera:");
        for (int i = 0; i < disqueras.size(); i++) {
            System.out.println((i + 1) + ". " + disqueras.get(i).getDisquera());
        }

        do {
            System.out.print("Opción: ");
            opcion = ReadUtil.readInt();
        } while (opcion < 1 || opcion > disqueras.size());
        disco.setDisquera(disqueras.get(opcion - 1));


        List<Genero> generos = GeneroJdbcImpl.getInstance().findAll();
        if (generos.isEmpty()) {
            System.out.println("No hay géneros musicales en la base de datos. Agrega uno primero.");
            return false;
        }

        System.out.println("Selecciona un género musical:");
        for (int i = 0; i < generos.size(); i++) {
            System.out.println((i + 1) + ". " + generos.get(i).getGenero());
        }

        do {
            System.out.print("Opción: ");
            opcion = ReadUtil.readInt();
        } while (opcion < 1 || opcion > generos.size());
        disco.setGenero(generos.get(opcion - 1));


        System.out.println("Título del disco:");
        disco.setTitulo(ReadUtil.read());

        System.out.println("Precio:");
        disco.setPrecio(ReadUtil.readFloat());

        System.out.println("Existencias:");
        disco.setExistencia(ReadUtil.readInt());

        System.out.println("Descuento:");
        disco.setDescuento(ReadUtil.readFloat());

        System.out.println("Fecha de lanzamiento:");
        disco.setFechaLanzamiento(ReadUtil.readDate());

        System.out.println("Imagen (URL):");
        disco.setImagen(ReadUtil.read());

        return true;
    }

    @Override
    public void processEditT(Disco disco) {
        Scanner sc = new Scanner(System.in);
        int opcion2 = 0;
        System.out.println("¿Qué elemento desea editar?");
        System.out.println("1- Título");
        System.out.println("2- Precio");
        System.out.println("3- Existencias");
        System.out.println("4- Descuento");
        System.out.println("5- Fecha de lanzamiento");
        System.out.println("6- URL imagen");

        try {
            opcion2 = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            return;
        }

        switch (opcion2) {
            case 1:
                System.out.println("Título actual: " + disco.getTitulo());
                System.out.println("Ingrese nuevo título:");
                disco.setTitulo(ReadUtil.read());
                break;
            case 2:
                System.out.println("Precio actual: " + disco.getPrecio());
                System.out.println("Ingrese nuevo precio:");
                disco.setPrecio(ReadUtil.readFloat());
                break;
            case 3:
                System.out.println("Existencias actuales: " + disco.getExistencia());
                System.out.println("Ingrese nuevo número de existencias:");
                disco.setExistencia(ReadUtil.readInt());
                break;
            case 4:
                System.out.println("Descuento actual: " + disco.getDescuento());
                System.out.println("Ingrese nuevo descuento:");
                disco.setDescuento(ReadUtil.readFloat());
                break;
            case 5:
                System.out.println("Fecha de lanzamiento actual: " + disco.getFechaLanzamiento());
                System.out.println("Ingrese nueva fecha de lanzamiento:");
                disco.setFechaLanzamiento(ReadUtil.readDate());
                break;
            case 6:
                System.out.println("URL de imagen actual: " + disco.getImagen());
                System.out.println("Ingrese nueva URL:");
                disco.setImagen(ReadUtil.read());
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    @Override
    public boolean saveToDatabase(Disco disco) {
        return DiscoJdbcImpl.getInstance().save(disco);
    }

    public List<Disco> fetchFromDatabase() {
        return DiscoJdbcImpl.getInstance().findAll();
    }

    public File getFile() {
        return new File("Disco.list");
    }

    public boolean deleteFromDatabaseById(int id) {
        Disco disco = new Disco();
        disco.setId(id);
        return DiscoJdbcImpl.getInstance().delete(disco);
    }

    @Override
    public boolean editFromDatabaseById(int id) {
        Disco disco = new Disco();
        disco.setId(id);

        System.out.println("Nuevo título del disco:");
        disco.setTitulo(ReadUtil.read());

        System.out.println("Nuevo precio:");
        disco.setPrecio(ReadUtil.readFloat());

        System.out.println("Nuevas Existencias:");
        disco.setExistencia(ReadUtil.readInt());

        System.out.println("Nuevo descuento");
        disco.setDescuento(ReadUtil.readFloat());

        System.out.println("Nueva fecha de lanzamiento");
        disco.setFechaLanzamiento(ReadUtil.readDate());

        System.out.println("Nueva imagen (URL):");
        disco.setImagen(ReadUtil.read());

        System.out.println("Nuevo id del artista:");
       disco.setIdArtista(ReadUtil.readInt());

        System.out.println("Nuevo id del género:");
        disco.setDisqueraId(ReadUtil.readInt());

        System.out.println("Nuevo id de la disquera:");
        disco.setIdGeneroMusical(ReadUtil.readInt());

        return DiscoJdbcImpl.getInstance().update(disco);
    }
}
