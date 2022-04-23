package core;
import netscape.javascript.JSObject;

import java.time.LocalDate;
import java.util.Date;

public class Comentario {
    private Usuario autor;
    private LocalDate fecha;
    private String contenido;

    public Comentario(Usuario autor, LocalDate fecha, String contenido){
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
