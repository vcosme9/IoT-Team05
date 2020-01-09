package com.vicent.neverapp.ui.avisos;

public class ClaseAviso {

    public ClaseAviso(String titulo, String descripcion, int imagenId,String id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenId = imagenId;
    }

    private String titulo;
    private String descripcion;
    private int imagenId;
    private String id;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

}
