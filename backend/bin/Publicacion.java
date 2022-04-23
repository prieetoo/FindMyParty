import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Publicacion {

    private Usuario autor;
    private Date fecha;
    private List<String> img;
    private String contenido;

    public Publicacion(Usuario autor, Date fecha, List<String> img, String contenido)
    {
        this.autor = autor;
        this.fecha = new Date(fecha.getTime());
        this.img = new ArrayList<>(img);
        this.contenido = contenido;
    }
    private boolean eliminar()
    {
        return false;
    }
    private JSObject toJson(){
        return null;
    }

    //getters y setters

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
