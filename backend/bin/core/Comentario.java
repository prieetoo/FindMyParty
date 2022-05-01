package core;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Comentario {
    private int id;
    private Usuario autor;
    private Usuario destinatario;
    private LocalDateTime fecha;
    private String contenido;

    public Comentario(int id, Usuario autor,Usuario destinatario, LocalDateTime fecha, String contenido){
        this.id = id;
        this.autor = autor;
        this.destinatario = destinatario;
        this.fecha = fecha;
        this.contenido = contenido;
        String consulta = "INSERT INTO Comentario (id, autor, destinatario, fecha, contenido) " + //aqui mirar que esto coincida con la bd
                "VALUES ('" +
                id + "', '" +
                autor + "', " +
                destinatario + "', " +
                "STR_TO_DATE('" + fecha.toString() + "','%Y-%m-%d'), '" +
               contenido + "', ');";
        ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
        try {
            if(rs.next())
                this.id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private boolean eliminar() //revisar
    {
        String consulta = "DELETE FROM Comentario p WHERE p.id = " + this.id +";";
        boolean rs = DB.getInstance().executeUpdate(consulta); //esto de dudosa procedencia por cambiar execute a static
        return rs;
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
}
