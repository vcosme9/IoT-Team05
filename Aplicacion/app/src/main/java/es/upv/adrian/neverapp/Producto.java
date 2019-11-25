package es.upv.adrian.neverapp;

public class Producto {
    private String nombreProducto;
    private String infoProducto;
    private int imagenProductoId;

    public Producto(String nombre, String info, int imagenId){
        this.nombreProducto = nombre;
        this.infoProducto = info;
        this.imagenProductoId = imagenId;
    }

    public String getNombre(){
        return nombreProducto;
    }

    public String getInfo(){
        return infoProducto;
    }

    public int getImagenId(){
        return imagenProductoId;
    }

    public void setNombre(String nombre) {
        this.nombreProducto = nombre;
    }

    public void setInfo(String info) {
        this.infoProducto = info;
    }

    public void setImagenId(int imagenId) {
        this.imagenProductoId = imagenId;
    }
}
