package org.boladomiguelevelincitlali.gui.consola;

import org.boladomiguelevelincitlali.model.Genero;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.GeneroJdbcImpl;
import org.boladomiguelevelincitlali.util.ReadUtil;

import java.io.File;
import java.util.List;

public class GeneroCatalogo extends Catalogos<Genero>{

    private static GeneroCatalogo generoCatalogo;

    public GeneroCatalogo() {
    }

   public static GeneroCatalogo getInstance(){
        if (generoCatalogo == null){
            generoCatalogo = new GeneroCatalogo();
        }
        return generoCatalogo;
   }

    @Override
    public Genero newT() {
        return new Genero();
    }

    @Override
    public boolean processNewT(Genero genero) {
        System.out.println("Nombre del genero:");
        genero.setGenero(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Genero genero) {
        System.out.println("El nombre del genero es: " + genero.getGenero());
        System.out.println("Ingrese nuevo genero");
        genero.setGenero(ReadUtil.read());
    }

    @Override
    public boolean saveToDatabase(Genero genero) {
        return GeneroJdbcImpl.getInstance().save(genero);
    }

    public List<Genero> fetchFromDatabase(){
        return GeneroJdbcImpl.getInstance().findAll();
    }

    public File getFile(){return  new File("Genero.list");}

    @Override
    public boolean deleteFromDatabaseById(int id) {
        Genero genero = new Genero();
        genero.setId(id);
        return GeneroJdbcImpl.getInstance().delete(genero);
    }

    @Override
    public boolean editFromDatabaseById(int id) {
        Genero genero = new Genero();
        genero.setId(id);

        System.out.println("Nuevo nombre de genero:");
        genero.setGenero(ReadUtil.read());

        return GeneroJdbcImpl.getInstance().update(genero);
    }
}


