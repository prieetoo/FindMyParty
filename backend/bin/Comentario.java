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
}
