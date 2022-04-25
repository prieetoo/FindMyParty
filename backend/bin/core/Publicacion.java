package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Publicacion {

    private int id;
    private Usuario autor;
    private Date fecha;
    private ArrayList<String> img;
    private String contenido;

    public Publicacion(int id, Usuario autor, Date fecha, List<String> img, String contenido)
    {
        this.id = id;
        this.autor = autor;
        this.fecha = new Date(fecha.getTime());
        this.img = new ArrayList<>(img);
        this.contenido = contenido;

    }
    private boolean eliminar() //revisar
    {
        String consulta = "DELETE FROM Publicacion p WHERE p.id = " + this.id +";";
        boolean rs = DB.executeUpdate(consulta); //esto de dudosa procedencia por cambiar execute a static
        return rs;
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("autor",this.autor);
        json.put("fecha", this.fecha);
        json.put("contenido", this.contenido);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; this.img.size() > i; i++)
            jsonArray.put(this.img.get(i));
        json.put("img", jsonArray);
        return json;
    }

    //getters y setters

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(ArrayList<String> img) {
        this.img = img;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

