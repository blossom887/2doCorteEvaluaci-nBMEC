package org.boladomiguelevelincitlali.model;

public class Genero extends Catalogo{
    private String genero;

    public Genero() {}

    public Genero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "genero='" + genero + '\'' +
                ", id=" + id +
                '}';
    }
}
