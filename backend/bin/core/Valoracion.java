package core;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Valoracion {

    //no se que parametros tiene exactamente esta clase asi que hay que mirarlo
    private int id;
    private float valor;
     public Valoracion(float valoracion)
     {
         this.valor = valoracion;
         //guardamos en la base de datos
         String consulta = ("INSERT INTO valoracion VALUES (" +valor+")"); //revisar
         boolean rs = DB.executeUpdate(consulta);
         String consulta1 = "SELECT LAST_INSERT_ID()";
         ResultSet rs1 = DB.executeQuery(consulta1);
         try {
             this.id = rs1.getInt("id");
         } catch (SQLException e) {
             e.printStackTrace();
         }
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
