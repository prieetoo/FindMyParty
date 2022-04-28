package core;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Valoracion {
    private Usuario autor;
    private Usuario destino;
    private Evento evento;
    private float valor;

    public Valoracion( Usuario autor, Usuario destino, float valoracion) {
         this.autor = autor;
         this.valor = valoracion;
         this.destino = destino;
         //guardamos en la base de datos
         String consulta = ("INSERT INTO Valoracionusuario VALUES (" +valor+","+autor.getId()+","+destino.getId()+")");
         DB.getInstance().executeUpdate(consulta);
    }

    public Valoracion( Usuario autor, Evento destino, float valoracion) {
        this.autor = autor;
        this.valor = valoracion;
        this.evento = destino;
        //guardamos en la base de datos
        String consulta = ("INSERT INTO Valoracionevento VALUES (" +valor+","+autor.getId()+","+destino.getId()+")");
        DB.getInstance().executeUpdate(consulta);
    }

    //setters y getters
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("autor", this.autor.toJson());
        json.put("valor",this.valor);
        return json;
    }
}
