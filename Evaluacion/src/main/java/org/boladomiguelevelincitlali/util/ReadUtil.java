package org.boladomiguelevelincitlali.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReadUtil
{
    private static Scanner scanner = new Scanner(System.in);
    private static ReadUtil readUtil;

    private ReadUtil()
    {
        scanner = new Scanner( System.in );
    }

    public Scanner getScanner()
    {
        return scanner;
    }

    public static ReadUtil getInstance( )
    {
        if(readUtil==null)
        {
            readUtil = new ReadUtil();
        }
        return readUtil;
    }

    public static String read( )
    {
        return getInstance( ).getScanner( ).nextLine();
    }

    public static Integer readInt( )
    {
        String valor = null;
        boolean flag = true;
        Integer aux = null;

        while (flag)
        {
            valor = read();
            if (valor != null && !valor.isEmpty())
            {
                try
                {
                    aux = Integer.valueOf(valor);
                    if (aux != null)
                    {
                        return aux;
                    }
                }
                catch (Exception e)
                {
                }
            }
            System.out.println( "Valor incorrecto, intentelo nuevamente" );
        }
        return null;
    }

    public static Integer string2Integer( String valor )
    {
        try
        {
            return Integer.valueOf(valor);
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static Float readFloat() {
        while (true) {
            String valor = read();
            try {
                return Float.parseFloat(valor);
            } catch (NumberFormatException e) {
                System.out.println("Valor incorrecto. Intente con un número decimal (por ejemplo: 12.34)");
            }
        }
    }

    public static Date readDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // No permite fechas como 2024-02-30
        while (true) {
            String valor = read();
            try {
                return sdf.parse(valor);
            } catch (ParseException e) {
                System.out.println("Fecha incorrecta. Usa el formato yyyy-MM-dd (ej. 2024-12-31)");
            }
        }
    }

    public static Time readTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (true) {
            try {
                System.out.print("Duración (HH:mm:ss): ");
                String input = scanner.nextLine();
                long ms = sdf.parse(input).getTime();
                return new Time(ms);
            } catch (ParseException e) {
                System.out.println("Formato inválido. Usa HH:mm:ss (ejemplo: 00:03:45).");
            }
        }
    }
}