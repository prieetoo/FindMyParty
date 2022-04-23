package core;

import netscape.javascript.JSObject;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Comentario {
    private int id;
    private Usuario autor;
    private LocalDate fecha;
    private String contenido;

    public Comentario(int id, Usuario autor, LocalDate fecha, String contenido){
        this.id = id;
        this.autor = autor;
        this.fecha = fecha;
        this.contenido = contenido;
    }
    private boolean eliminar() //revisar
    {
        String consulta = "DELETE FROM Comentario p WHERE p.id = " + this.id +";";
        ResultSet rs = DB.execute(consulta); //esto de dudosa procedencia por cambiar execute a static
        return rs != null;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
