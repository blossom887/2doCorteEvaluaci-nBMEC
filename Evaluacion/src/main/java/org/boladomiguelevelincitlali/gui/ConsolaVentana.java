package org.boladomiguelevelincitlali.gui;

import org.boladomiguelevelincitlali.gui.consola.Consola;
import org.boladomiguelevelincitlali.negocio.Ejecutable;

public class ConsolaVentana  extends LecturaAccion
{
    public static ConsolaVentana consolaVentana;

    private ConsolaVentana()
    {
    }

    public static ConsolaVentana getInstance( )
    {
        if(consolaVentana==null)
        {
            consolaVentana = new ConsolaVentana();
        }
        return consolaVentana;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("Seleccione una opcion:");
        System.out.println("1.-Consola");
        System.out.println("2.-Salir");
    }
    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 2;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        if(opcion==1)
        {
            ejecutable = Consola.getInstance( );
        }

        ejecutable.setFlag( true );
        ejecutable.run( );
    }
}
