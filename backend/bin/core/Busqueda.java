package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public Busqueda(Punto coord, float radio, ArrayList<String> etiquetes, Boolean pago, int participantes, int dia)
    {
        eventos = new ArrayList<>();
        eventos_json = new JSONObject();
        String cond = "";
        String join = "";
        if (etiquetes.size()> 0){
            cond = " AND (";
            join = " INNER JOIN Etiqueta et ON et.evento_id = e.id ";
            for (String e: etiquetes) {
                cond += " et.etiqueta = '" + e + "' OR ";
            }
            cond = cond.substring(0, cond.length() - 4);
            cond += ")";
        }
        String pagoCond = " WHERE e.coste = 0";
        if (pago) {
            pagoCond = " WHERE e.coste > 0";
        }
        String part = " AND e.participantes <= " + participantes + " ";

        String weekdayCond = "";
        if (dia >= 0) {
            weekdayCond = "AND WEEKDAY(e.fecha) = " + dia + " ";
        }

        String formula = "( 6371 * acos( cos( radians(" + coord.getX() + ") ) * cos( radians( e.x ) ) " +
                "* cos( radians( " + coord.getY() + " ) - radians(e.y) ) + sin( radians(" + coord.getX() + ") ) * sin(radians(e.x)) ) ) AS distance ";

        String consulta =  "SELECT DISTINCT id,nombre,descripcion,participantes,usuario_id, x, y, " + formula +
            " FROM Evento e " +
            join + pagoCond + part + weekdayCond + cond +
            " HAVING distance <= " + radio +
            " ORDER BY distance";
        try{
            ResultSet rs = DB.getInstance().executeQuery(consulta);
            JSONArray arrayjson = new JSONArray();
            while (rs.next()) {
                JSONObject json_event = new JSONObject();
                json_event.put("id", rs.getInt("id"));
                json_event.put("nombre", rs.getString("nombre"));
                json_event.put("latitud", rs.getString("x"));
                json_event.put("longitud", rs.getString("y"));
                json_event.put("distancia", rs.getFloat("distance"));
                json_event.put("descripcion", rs.getString("descripcion"));
                json_event.put("participantes",rs.getString("participantes"));
                json_event.put("autor",rs.getString("usuario_id"));
                arrayjson.put(json_event);
            }
            eventos_json.put("lista_eventos", arrayjson);
        }catch(SQLException e){
            e.printStackTrace();
        }
        processComments();
    }
    private void processComments(){
        JSONArray arrayjson  = (JSONArray) this.eventos_json.get("lista_eventos");
        JSONArray newArray = new JSONArray();
        for (int i=0; i < arrayjson.length(); i++) {
           JSONObject event = arrayjson.getJSONObject(i);
           int id = event.getInt("id");
           String consulta = "SELECT * FROM Comentarioevento where evento_id = " + id ;
           ResultSet rs = DB.getInstance().executeQuery(consulta);
           JSONArray comments = new JSONArray();
           try {
               while (rs.next()) {
                   JSONObject json_comment = new JSONObject();
                   json_comment.put("contenido",rs.getString("contenido"));
                   json_comment.put("fecha",rs.getString("fecha"));
                   json_comment.put("autor",rs.getString("Usuario_id"));
                   comments.put(json_comment);
               }
               event.put("comment",comments);
           } catch (SQLException e) {
               e.printStackTrace();
           }
           newArray.put(event);
        }
        this.eventos_json.put("lista_eventos",newArray);

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
