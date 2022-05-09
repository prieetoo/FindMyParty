package core;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Comentario {
    private Usuario autor;
    private Usuario destinatario;
    private Evento evento;
    private LocalDateTime fecha;
    private String contenido;
    public Comentario(Usuario autor,Usuario destinatario, LocalDateTime fecha, String contenido){

        this.autor = autor;
        this.destinatario = destinatario;
        this.fecha = fecha;
        this.contenido = contenido;
        this.evento = null;

    }
    public Comentario(Usuario autor,Evento evento, LocalDateTime fecha, String contenido){

        this.autor = autor;
        this.destinatario = null;
        this.fecha = fecha;
        this.contenido = contenido;
        this.evento = evento;

    }
    public boolean eliminar() //revisar
    {
        String consulta = "";
        if (this.evento == null)
            consulta = "DELETE FROM Comentariousuario  WHERE destinatario_id = " + this.destinatario.getId()+" AND autor_id = " + this.autor.getId() + " ;";
        else
            consulta = "DELETE FROM Comentarioevento  WHERE Usuario_id = " + this.autor.getId()+" AND Evento_id = " + this.evento.getId() + ";";

        boolean rs = DB.getInstance().executeUpdate(consulta);
        return rs;
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("autor",this.autor);
        json.put("destinatario",this.destinatario);
        json.put("fecha", this.fecha);
        json.put("contenido", this.contenido);
        return json;
    }
    public Usuario getAutor() {
        return autor;
    }
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public Usuario getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
}
