import netscape.javascript.JSObject;

import java.util.Date;

public class Comentario {
    private Usuario autor;
    private Date fecha;
    private String contenido;

    public Comentario(Usuario autor, Date fecha, String contenido){
        this.autor = autor;
        this.fecha = fecha;
        this.contenido = contenido;
    }
    private JSObject toJson(){
        return null;
    }

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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
