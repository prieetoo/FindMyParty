package core;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Valoracion {

    //no se que parametros tiene exactamente esta clase asi que hay que mirarlo
    private int id;
    private float valor;
     public Valoracion( int id,float valoracion)
     {
         this.id = id;
         this.valor = valoracion;
         //guardamos en la base de datos
         String consulta = ("INSERT INTO valoracion VALUES (" +valor+")"); //revisar
         boolean rs = DB.executeUpdate(consulta);
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
        json.put("id", this.id);
        json.put("valor",this.valor);
        return json;
    }
}
