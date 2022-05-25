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
    public Busqueda (Punto coord)
    {
        eventos = new ArrayList<>();
        radio = 5; //en kilómetros
        etiquetes = new ArrayList<>();
        eventos_json = new JSONObject();

        String consulta =  "SELECT id,nombre, ( 6371 * acos( cos( radians(" + coord.getX() + ") ) * cos( radians( e.x ) ) " +
            "* cos( radians( " + coord.getY() + " ) - radians(e.y) ) + sin( radians(" + coord.getX() + ") ) * sin(radians(e.x)) ) ) AS distance " +
            "FROM Evento e " +
            "HAVING distance <= " + radio +
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
    public Busqueda (Usuario u,float radio, ArrayList<String> etiquetes)
    {
        eventos = new ArrayList<>();
        this.radio = radio; //yo guardaria un radio en usuario que sea el que ha predefinido el al crearse el usuario y que lo pueda modificar
        this.etiquetes = new ArrayList<>(etiquetes);
        ArrayList<Evento> aux = new ArrayList<>();

        String consulta = "SELECT * "+
                "FROM Evento e" +
                "WHERE "+ this.radio+" >= SQRT(POWER("+ "u.getUbicacion().getX()" + "-e.x,2)+ POWER("+"u.getUbicacion().getY()" +" -e.y,2));";
        try{
            ResultSet rs = DB.getInstance().executeQuery(consulta);
            int i = 1; //pone que tiene que ser 1
            while (rs.next()) {
                Evento e = new Evento((Evento) rs.getObject(i));
                aux.add(e);
                i++;
            }
            //en caso de que con que cumpla una de las etiquetas ya se tenga que mostrar
            //esto habra que cambiarlo si consideramos que se tienen que cumplir todas las etiquetas
            for (Evento evento : aux) { //recorremos array auxiliar
                //recorremos nuestra lista de etiquetas
                for (int n = 0; n < evento.getEtiquetas().size(); n++) //para cada evento del array auxiliar recorremos el array de etiquetas
                    for (String etiquete : this.etiquetes) {
                        if (evento.getEtiquetas().contains(etiquete)) //si el evento del array auxiliar tiene alguna etiqueta de nuestra array
                            if (!eventos.contains(evento)) //y el evento no se ha añadido aun
                                eventos.add(evento); //lo añadimos
                    }
            }
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
