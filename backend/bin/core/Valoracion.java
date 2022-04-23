package core;

import java.sql.ResultSet;

public class Valoracion {

    private float valor;
     public Valoracion( float valoracion)
     {
         this.valor = valoracion;
         //guardamos en la base de datos
         String consulta = ("INSERT INTO valoracion VALUES (" +valor+")"); //revisar
         ResultSet rs = DB.execute(consulta);
     }

}
