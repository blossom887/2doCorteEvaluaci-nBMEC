package org.boladomiguelevelincitlali.gui.consola;

import org.boladomiguelevelincitlali.gui.LecturaAccion;
import org.boladomiguelevelincitlali.negocio.Ejecutable;

public class ListaCatalogos extends LecturaAccion
{
    public static ListaCatalogos listaCatalogos;
    private ListaCatalogos()
    {
    }
    public static ListaCatalogos getInstance( )
    {
        if(listaCatalogos==null)
        {
            listaCatalogos = new ListaCatalogos();
        }
        return listaCatalogos;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println( "Seleccione una opcion:" );

        System.out.println( "1.-Genero");
        System.out.println( "2.-Disquera");
        System.out.println( "3.-Artista");
        System.out.println( "4.-Disco");
        System.out.println( "5.-Cancion");
        System.out.println( "6.-Salir");
    }
    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 6;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {

            case 1:
                ejecutable = GeneroCatalogo.getInstance();
                break;
            case 2:
                ejecutable = DisqueraCatalogo.getInstance();
               break;
            case 3:
                ejecutable = ArtistaCatalogo.getInstance();
                break;
            case 4:
                ejecutable = DiscoCatalogo.getInstance();
                break;
            case 5:
                ejecutable = CancionCatalogo.getInstance();
                break;
            case 6:
                System.out.println("Saliendo del menu de catalogos...");
                return;
            default:
                System.out.println("Opcion invalida");
                return;
        }
        if (ejecutable != null) {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }
}
