package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Publicacion {

    private int id;
    private Usuario autor;
    private LocalDateTime fecha;
    private ArrayList<String> img;
    private String contenido;

    public Publicacion(Usuario autor, LocalDateTime fecha, List<String> img, String contenido)
    {
        this.autor = autor;
        this.fecha = fecha;
        this.img = new ArrayList<>(img);
        this.contenido = contenido;

        //tema de las fotos revisar
        String consulta = "INSERT INTO Publicacion (autor, fecha, img, contenido) " +
                "VALUES ('" +
                id + "', '" +
                autor + "', " +
                "STR_TO_DATE('" + fecha.toString() + "','%Y-%m-%d'), '" +
                img + "', '" +
                contenido + "');";
        ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
        try {
            if(rs.next())
                this.id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    private boolean eliminar() //revisar
    {
        String consulta = "DELETE FROM Publicacion p WHERE p.id = " + this.id +";";
        boolean rs = DB.getInstance().executeUpdate(consulta);
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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

