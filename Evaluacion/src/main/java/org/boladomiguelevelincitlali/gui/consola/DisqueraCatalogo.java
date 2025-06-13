package org.boladomiguelevelincitlali.gui.consola;

import org.boladomiguelevelincitlali.model.Disquera;
import org.boladomiguelevelincitlali.repository.jdbc.Impl.DisqueraJdbcImpl;
import org.boladomiguelevelincitlali.util.ReadUtil;

import java.io.File;
import java.util.List;

public class DisqueraCatalogo extends Catalogos<Disquera>{

    private static DisqueraCatalogo disqueraCatalogo;

    public DisqueraCatalogo() {
    }

    public static DisqueraCatalogo getInstance(){
        if (disqueraCatalogo == null){
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera) {
        System.out.println("Nombre de disquera:");
        disquera.setDisquera(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Disquera disquera) {
        System.out.println("El nombre de la disquera es: " + disquera.getDisquera());
        System.out.println("Ingrese nueva disquera");
        disquera.setDisquera(ReadUtil.read());
    }

    @Override
    public boolean saveToDatabase(Disquera disquera) {
        return DisqueraJdbcImpl.getInstance().save(disquera);
    }

    public List<Disquera> fetchFromDatabase(){
        return DisqueraJdbcImpl.getInstance().findAll();
    }

    public File getFile(){return new File("Disquera.list");}

    public boolean deleteFromDatabaseById(int id) {
        Disquera disquera = new Disquera();
        disquera.setId(id);
        return DisqueraJdbcImpl.getInstance().delete(disquera);
    }

    public boolean editFromDatabaseById(int id) {
        Disquera disquera = new Disquera();
        disquera.setId(id);

        System.out.println("Dame el nuevo nombre de la disquera:");
        disquera.setDisquera(ReadUtil.read());

        return DisqueraJdbcImpl.getInstance().update(disquera);
    }
}
