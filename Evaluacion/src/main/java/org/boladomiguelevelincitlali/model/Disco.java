package org.boladomiguelevelincitlali.model;

import java.util.Date;

public class Disco extends Catalogo {
    private String titulo;
    private float precio;
    private int existencia;
    private float descuento;
    private Date fechaLanzamiento;
    private String imagen;
    public int idArtista;
    public int disqueraId;
    public int idGeneroMusical;

    public Disco() {
    }

    public Disco(String titulo, float precio, int existencia, float descuento, Date fechaLanzamiento, String imagen, int idArtista, int disqueraId, int idGeneroMusical) {
        this.titulo = titulo;
        this.precio = precio;
        this.existencia = existencia;
        this.descuento = descuento;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.idArtista = idArtista;
        this.disqueraId = disqueraId;
        this.idGeneroMusical = idGeneroMusical;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public int getDisqueraId() {
        return disqueraId;
    }

    public void setDisqueraId(int disqueraId) {
        this.disqueraId = disqueraId;
    }

    public int getIdGeneroMusical() {
        return idGeneroMusical;
    }

    public void setIdGeneroMusical(int idGeneroMusical) {
        this.idGeneroMusical = idGeneroMusical;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                ", descuento=" + descuento +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", imagen='" + imagen + '\'' +
                ", id=" + id +
                '}';
    }
    private Artista artista;  // Nueva propiedad

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    private Genero genero;
    public Genero getGenero(){
        return genero;
    }
    public void setGenero(Genero genero){
        this.genero = genero;
    }

    private Disquera disquera;
    public Disquera getDisquera(){
        return disquera;
    }
    public void setDisquera(Disquera disquera){
        this.disquera = disquera;
    }



}
