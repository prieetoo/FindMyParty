package core;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Valoracion {
    private int autor;
    private int destino;
    private int evento;
    private float valor;

    public Valoracion( int autor, int destino, float valoracion) {
         this.autor = autor;
         this.valor = valoracion;
         this.destino = destino;
         //guardamos en la base de datos
         String consulta = ("INSERT INTO Valoracionusuario VALUES (" +valor+","+autor+","+destino+")");
         DB.getInstance().executeUpdate(consulta);
    }

    public Valoracion( int autor, int destino, float valoracion, boolean evento) {
        this.autor = autor;
        this.valor = valoracion;
        this.evento = destino;
        //guardamos en la base de datos
        String consulta = ("INSERT INTO Valoracionevento VALUES (" +valor+","+autor+","+destino+")");
        DB.getInstance().executeUpdate(consulta);
    }


    //setters y getters
    public float getValor() {
        return valor;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("autor", this.autor);
        json.put("valor",this.valor);
        return json;
    }
}
