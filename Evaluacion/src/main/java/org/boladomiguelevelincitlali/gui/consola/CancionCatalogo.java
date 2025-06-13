package org.boladomiguelevelincitlali.gui.consola;

import org.boladomiguelevelincitlali.model.Cancion;
import org.boladomiguelevelincitlali.model.Disco;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.CancionJdbcImpl;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.DiscoJdbcImpl;
import org.eve.pixup.model.*;
import org.eve.pixup.repository.jdbc.Impl.*;
import org.boladomiguelevelincitlali.util.ReadUtil;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class CancionCatalogo extends  Catalogos<Cancion>{

    private static CancionCatalogo cancionCatalogo;

    public CancionCatalogo() {
    }

    public static CancionCatalogo getInstance(){
        if(cancionCatalogo == null){
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }

    public Cancion newT(){
        return new Cancion();
    }

    public boolean processNewT(Cancion cancion) {
        List<Disco> discos = DiscoJdbcImpl.getInstance().findAll();
        if (discos.isEmpty()) {
            System.out.println("No hay Discos en la base de datos. Agrega uno primero.");
            return false;
        }

        System.out.println("Selecciona un disco:");
        for (int i = 0; i < discos.size(); i++) {
            System.out.println((i + 1) + ". " + discos.get(i).getTitulo());
        }

        int opcion;
        do {
            System.out.print("Opción: ");
            opcion = ReadUtil.readInt();
        } while (opcion < 1 || opcion > discos.size());
        cancion.setDisco(discos.get(opcion - 1));


        System.out.println("Título de la cancion:");
        cancion.setTitulo(ReadUtil.read());

        System.out.println("Duracion:");
        cancion.setDuracion(ReadUtil.readTime());

        return true;
    }

    @Override
    public void processEditT(Cancion cancion) {
        Scanner scanner = new Scanner(System.in);
        int opcion2 = 0;
        System.out.println("¿Qué elemento desea editar?");
        System.out.println("1- Título");
        System.out.println("2- Duracion");
        System.out.println("3- Disco");
    }

    @Override
    public boolean saveToDatabase(Cancion cancion) {
        return CancionJdbcImpl.getInstance().save(cancion);
    }

    public List<Cancion> fetchFromDatabase(){
        return CancionJdbcImpl.getInstance().findAll();
    }
    public File getFile(){
        return new File("Cancion.list");
    }

    @Override
    public boolean deleteFromDatabaseById(int id) {
        Cancion cancion = new Cancion();
        cancion.setId(id);
        return CancionJdbcImpl.getInstance().delete(cancion);
    }


    public boolean editFromDatabaseById(int id) {
        Cancion cancion = new Cancion();
        cancion.setId(id);

        System.out.println("Nuevo título de la canción:");
        cancion.setTitulo(ReadUtil.read());

        System.out.println("Nueva duración (HH:MM:SS):");
        cancion.setDuracion(ReadUtil.readTime());

        System.out.println("Nuevo id del disco:");
        cancion.setIdDisco(ReadUtil.readInt());

        return CancionJdbcImpl.getInstance().update(cancion);
    }

}
