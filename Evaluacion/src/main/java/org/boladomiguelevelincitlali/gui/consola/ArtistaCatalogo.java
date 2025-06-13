package org.boladomiguelevelincitlali.gui.consola;

import org.boladomiguelevelincitlali.model.Artista;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.ArtistaJdbcImpl;
import org.boladomiguelevelincitlali.util.ReadUtil;

import java.io.File;
import java.util.List;



public class ArtistaCatalogo extends Catalogos<Artista> {

    private static ArtistaCatalogo artistaCatalogo;


    private ArtistaCatalogo() {
    }

    public static ArtistaCatalogo getInstance() {
        if (artistaCatalogo == null) {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT() {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista) {
        System.out.println("Nombre del artista:");
        artista.setArtista(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Artista artista) {
        System.out.println("El nombre del artista es: " + artista.getArtista());
        System.out.println("Dame el nuevo nombre del artista:");
        artista.setArtista(ReadUtil.read());
    }

    @Override
    public boolean saveToDatabase(Artista artista) {
        return ArtistaJdbcImpl.getInstance().save(artista);
    }

    @Override
    public List<Artista> fetchFromDatabase() {
        return ArtistaJdbcImpl.getInstance().findAll();
    }

    public File getFile() {
        return new File("Artista.list");
    }

    public boolean deleteFromDatabaseById(int id) {
        Artista artista = new Artista();
        artista.setId(id);
        return ArtistaJdbcImpl.getInstance().delete(artista);
    }

    public boolean editFromDatabaseById(int id) {
        Artista artista = new Artista();
        artista.setId(id);

        System.out.println("Dame el nuevo nombre del artista:");
        artista.setArtista(ReadUtil.read());

        return ArtistaJdbcImpl.getInstance().update(artista);
    }
}

