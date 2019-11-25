package es.upv.adrian.neverapp;

public class Supermercado {

    private String nombre;
    private String info;
    private int imagenId;

    public Supermercado(String nombre, String info, int imagenId){
        this.nombre = nombre;
        this.info = info;
        this.imagenId = imagenId;
    }

    public String getNombre(){
        return nombre;
    }

    public String getInfo(){
        return info;
    }

    public int getImagenId(){
        return imagenId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
