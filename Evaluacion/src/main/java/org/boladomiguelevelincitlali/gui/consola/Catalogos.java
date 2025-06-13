package org.boladomiguelevelincitlali.gui.consola;

import org.boladomiguelevelincitlali.gui.LecturaAccion;
import org.boladomiguelevelincitlali.model.Catalogo;
import org.boladomiguelevelincitlali.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class Catalogos<T extends Catalogo> extends LecturaAccion
{
    protected List<T> list;
    protected T t;
    protected boolean flag2;

    public Catalogos() {
        list = new ArrayList<>();
    }

    public boolean isListEmpty() {
        return list.isEmpty();
    }

    public void print() {
        if (isListEmpty()) {
            System.out.println("No hay elementos");
            return;
        }
        list.forEach(System.out::println);
    }

    public abstract T newT();
    public abstract boolean processNewT(T t);
    public abstract void processEditT(T t);


    public abstract boolean saveToDatabase(T t);
    public abstract List<T> fetchFromDatabase();

    public void add() {
        t = newT();
        if (processNewT(t)) {
            t.setId(list.size() + 1);
            list.add(t);
        }
    }

    public void edit() {
        if (isListEmpty()) {
            System.out.println("No hay elementos");
            return;
        }
        flag2 = true;
        while (flag2) {
            System.out.println("Ingrese el id del elemento a editar");
            print();
            t = list.stream().filter(e -> e.getId().equals(ReadUtil.readInt())).findFirst().orElse(null);
            if (t == null) {
                System.out.println("Id incorrecto, intentelo nuevamente");
            } else {
                processEditT(t);
                flag2 = false;
                System.out.println("Elemento modificado");
            }
        }
    }

    public void remove() {
        if (isListEmpty()) {
            System.out.println("No hay elementos");
            return;
        }
        flag2 = true;
        while (flag2) {
            System.out.println("Ingrese el id del elemento a borrar");
            print();
            t = list.stream().filter(e -> e.getId().equals(ReadUtil.readInt())).findFirst().orElse(null);
            if (t == null) {
                System.out.println("Id incorrecto, intentelo nuevamente");
            } else {
                list.remove(t);
                flag2 = false;
                System.out.println("Elemento borrado");
            }
        }
    }
//
    @Override
    public void procesaOpcion() {
        switch (opcion) {
            case 1:
                add();
                break;
            case 2:
                edit();
                break;
            case 3:
                remove();
                break;
            case 4:
                print();
                break;
            case 5:
                guardar();
                break;
            case 6:
                mostrarDesdeBaseDeDatos();
                break;
            case 7:
                editFromDatabase();
                break;
            case 8:
                deleteFromDatabase();
                break;
            case 9:
                System.out.println("Saliendo...");
                return;
            default:
                System.out.println("Opcion invalida");
                return;
        }
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Menú");
        System.out.println("Seleccione una opcion:");
        System.out.println("1.-Agregar");
        System.out.println("2.-Editar");
        System.out.println("3.-Borrar");
        System.out.println("4.-Imprimir");
        System.out.println("5.-Guardar");
        System.out.println("6.-Registro de datos");
        System.out.println("7.-Editar en Base de datos");
        System.out.println("8.-Borrar en Base de datos");
        System.out.println("9.-Salir");
    }

    @Override
    public int valorMinMenu() {
        return 1;
    }

    @Override
    public int valorMaxMenu() {
        return 9;
    }

    public void guardar() {
        if (isListEmpty()) {
            System.out.println("No hay elementos para guardar.");
            return;
        }
        int guardados = 0;
        for (T item : list) {
            if (saveToDatabase(item)) {
                guardados++;
            }
        }
        System.out.println(guardados + " elementos guardados en la base de datos.");
    }

    public void mostrarDesdeBaseDeDatos() {
        List<T> datosBD = fetchFromDatabase();
        if (datosBD == null || datosBD.isEmpty()) {
            System.out.println("No hay elementos en la base de datos.");
        } else {
            datosBD.forEach(System.out::println);
        }
    }

    public void editFromDatabase() {
        List<T> datosBD = fetchFromDatabase();
        if (datosBD == null || datosBD.isEmpty()) {
            System.out.println("No hay elementos en la base de datos para editar.");
            return;
        }

        System.out.println("Seleccione el ID del elemento a editar:");
        datosBD.forEach(e -> System.out.println("ID: " + e.getId() + " - " + e));
        System.out.println("Ingrese ID");
        int id = ReadUtil.readInt();
        T item = datosBD.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);

        if (item == null) {
            System.out.println("ID no válido.");
            return;
        }

        processEditT(item);

        if (saveToDatabase(item)) {
            System.out.println("Elemento actualizado correctamente en la base de datos.");
        } else {
            System.out.println("Error al actualizar en la base de datos.");
        }
    }

    public void deleteFromDatabase() {
        List<T> datosBD = fetchFromDatabase();
        if (datosBD == null || datosBD.isEmpty()) {
            System.out.println("No hay elementos en la base de datos para borrar.");
            return;
        }

        System.out.println("Seleccione el ID del elemento a borrar:");
        datosBD.forEach(e -> System.out.println("ID: " + e.getId() + " - " + e));
        System.out.println("Ingrese ID");
        int id = ReadUtil.readInt();
        T item = datosBD.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);

        if (item == null) {
            System.out.println("ID no válido.");
            return;
        }

        if (deleteFromDatabaseById(id)) {
            System.out.println("Elemento borrado correctamente de la base de datos.");
        } else {
            System.out.println("Error al borrar de la base de datos.");
        }
    }

    public abstract boolean deleteFromDatabaseById(int id);
    public abstract boolean editFromDatabaseById(int id);

}