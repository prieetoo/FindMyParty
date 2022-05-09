package core;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Busqueda {

    private ArrayList<Evento> eventos;
    private float radio;
    private ArrayList<String> etiquetes;

    /**
     * metemos una ubicacion como string, convertimos
     * hacemos una consulta y devolvemos los eventos que coincidan a ese radio
     * y esa direccion, cumpliendo tmb con las etiquetas si es que hay
     */
    public Busqueda (Usuario u)
    {
        eventos = new ArrayList<>();
        radio = 2; //yo guardaria un radio en usuario que sea el que ha predefinido el al crearse el usuario y que lo pueda modificar
        etiquetes = new ArrayList<>();

        /*
        String consulta = "SELECT * "+
                "FROM Evento e" +
                "WHERE "+ radio +" >= SQRT(POWER("+ "u.getUbicacion().getX()" + "-e.x,2)+ POWER("+"u.getUbicacion().getY()" +" -e.y,2));";
        try{
            ResultSet rs = DB.getInstance().executeQuery(consulta);
            int i = 1; //pone que tiene que ser 1
            while (rs.next()) {
                Evento e = new Evento((Evento) rs.getObject(i));
                eventos.add(e);
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

         */
    }
    public Busqueda (Usuario u,float radio, ArrayList<String> etiquetes)
    {
        eventos = new ArrayList<>();
        this.radio = radio; //yo guardaria un radio en usuario que sea el que ha predefinido el al crearse el usuario y que lo pueda modificar
        this.etiquetes = new ArrayList<>(etiquetes);
        ArrayList<Evento> aux = new ArrayList<>();
        /*
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

         */
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
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
