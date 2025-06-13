package org.boladomiguelevelincitlali.model;

import java.sql.Time;

public class Cancion extends Catalogo {
    private String titulo;
    private Time duracion;
    public int idDisco;


    public Cancion() {
    }

    public Cancion(String titulo, Time duracion, int idDisco) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.idDisco = idDisco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }


    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", id=" + id +
                '}';
    }

    private Disco disco;

    public Disco getDisco(){
        return disco;
    }

    public void setDisco(Disco disco){
        this.disco = disco;
    }
}
