package core;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Valoracion {
    private Usuario autor;
    private float valor;
    public Valoracion( Usuario autor, Usuario destino, float valoracion) {
         this.autor = autor;
         this.valor = valoracion;
         //guardamos en la base de datos
         String consulta = ("INSERT INTO Valoracionusuario VALUES (" +valor+","+autor.getId()+","+destino.getId()+")"); //revisar
         boolean rs = DB.getInstance().executeUpdate(consulta);
    }

    //setters y getters
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("autor", this.autor.toJson());
        json.put("valor",this.valor);
        return json;
    }
}
