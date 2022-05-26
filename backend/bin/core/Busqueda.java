package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Busqueda {

    private ArrayList<Evento> eventos;
    private float radio;
    private ArrayList<String> etiquetes;
    private JSONObject eventos_json;

    /**
     * metemos una ubicacion como string, convertimos
     * hacemos una consulta y devolvemos los eventos que coincidan a ese radio
     * y esa direccion, cumpliendo tmb con las etiquetas si es que hay
     */
    public Busqueda (Punto coord, float radio,ArrayList<String> etiquetes)
    {
        eventos = new ArrayList<>();
        eventos_json = new JSONObject();
        String cond = "";
        if (etiquetes.size()> 0){
            cond = " WHERE ";
            for (String e: etiquetes) {
                cond += " et.etiqueta = '" + e + "' OR ";
            }
            cond = cond.substring(0, cond.length() - 4);
        }

        String consulta =  "SELECT DISTINCT id,nombre, ( 6371 * acos( cos( radians(" + coord.getX() + ") ) * cos( radians( e.x ) ) " +
            "* cos( radians( " + coord.getY() + " ) - radians(e.y) ) + sin( radians(" + coord.getX() + ") ) * sin(radians(e.x)) ) ) AS distance " +
            " FROM Evento e " +
            " INNER JOIN Etiqueta et ON et.evento_id = e.id " + cond +
            " HAVING distance <= " + radio +
            " ORDER BY distance";
        try{
            ResultSet rs = DB.getInstance().executeQuery(consulta);
            JSONArray arrayjson = new JSONArray();
            while (rs.next()) {

                //Evento e = new Evento(rs.getInt("id"));
                //eventos.add(e);
                JSONObject json_event = new JSONObject();
                json_event.put("id", rs.getInt("id"));
                json_event.put("nombre", rs.getString("nombre"));
                json_event.put("distancia", rs.getFloat("distance"));
                arrayjson.put(json_event);
            }
            eventos_json.put("lista_eventos", arrayjson);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public JSONObject getJsonEventos() {
         return eventos_json;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public ArrayList<String> getEtiquetes() {
        return etiquetes;
    }

    public void setEtiquetes(ArrayList<String> etiquetes) {
        this.etiquetes = etiquetes;
    }
}
