package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Evento {
  private int id;
  private String nombre;
  private String ubicacion;
  private LocalDate fecha;
  private Usuario host;
  private float valoracion;
  private ArrayList<Valoracion> valoraciones;
  private ArrayList<String> etiquetas;
  private ArrayList<Comentario> comentarios;
  private ArrayList<Usuario> participantes;
  private ArrayList<Publicacion> publicaciones;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public Usuario getHost() {
    return host;
  }

  public void setHost(Usuario host) {
    this.host = host;
  }

  public float getValoracion() {
    return valoracion;
  }

  public void setValoracion(float valoracion) {
    this.valoracion = valoracion;
  }

  public ArrayList<Valoracion> getValoraciones() {
    return valoraciones;
  }

  public void setValoraciones(ArrayList<Valoracion> valoraciones) {
    this.valoraciones = valoraciones;
  }

  public ArrayList<String> getEtiquetas() {
    return etiquetas;
  }

  public void setEtiquetas(ArrayList<String> etiquetas) {
    this.etiquetas = etiquetas;
  }

  public ArrayList<Comentario> getComentarios() {
    return comentarios;
  }

  public void setComentarios(ArrayList<Comentario> comentarios) {
    this.comentarios = comentarios;
  }

  public ArrayList<Usuario> getParticipantes() {
    return participantes;
  }

  public void setParticipantes(ArrayList<Usuario> participantes) {
    this.participantes = participantes;
  }

  public ArrayList<Publicacion> getPublicaciones() {
    return publicaciones;
  }

  public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
    this.publicaciones = publicaciones;
  }

  public Evento(String nombre, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;
    this.valoracion = 0;
    this.valoraciones = new ArrayList<Valoracion>();
    this.comentarios = new ArrayList<Comentario>();
    this.participantes = new ArrayList<Usuario>();
    this.publicaciones = new ArrayList<Publicacion>();

    //Añadir a la BD
    String consulta = "INSERT INTO EVENTO (nombre, ubicacion, fecha, valoracion, usuario_id) " +
        "OUTPUT Inserted.id" +
        "VALUES ('" +
        nombre + "', '" +
        ubicacion + "', TO_DATE('" +
        fecha + "'.'dd/mm/yyyy'), '" +
        this.valoracion + "', '" +
        host.getId() + "');";
    boolean rs = DB.executeUpdate(consulta);
    String consulta1 = "SELECT LAST_INSERT_ID()";
    ResultSet rs1 = DB.executeQuery(consulta1);
    try {
      this.id = rs1.getInt("id");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean modificar(String nombre, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;

    //Modificar la BD
    String consulta1 = "UPDATE EVENTO " +
        "SET nombre = '" + nombre + "', " +
        "    ubicacion = '" + ubicacion + "', " +
        "    fecha = TO_DATE('" + fecha + "'.'dd/mm/yyyy'), " +
        "    usuario_id = '" + host.getId() +
        "WHERE id = '" + this.id + "';";
    boolean rs = DB.executeUpdate(consulta1);
    for (int i = 0; i < etiquetas.size() && rs; i++) {
      String consulta2 = "INSERT INTO Etiqueta (etiqueta, Evento_id) VALUES (" +
          "'" + etiquetas.get(i) + "', " +
          "'" + this.id + "');";
      rs = DB.executeUpdate(consulta2);
    }
    return rs;
  }

  public boolean comentar(Comentario comentario) {
    this.comentarios.add(comentario);
    String consulta = "UPDATE EVENTO" +
                      "SET Evento_id = '" + this.id +
                      "WHERE id = '" + comentario.getId() + "';";
    //Ejecutar propuesta:
    boolean rs = DB.executeUpdate(consulta);
    return rs;
  }

  public boolean valorar(Valoracion valoracion) {
    if (valoracion.getValor() >= 0 && valoracion.getValor() <= 5) {
      this.valoraciones.add(valoracion);
      float val = 0;
      for (int i = 0; i < valoraciones.size(); i++){
        val += valoraciones.get(i).getValor();
      }
      val /= valoraciones.size();
      this.valoracion = val;
      String consulta = "UPDATE EVENTO" +
          "SET valoracion = '" + val +
          "WHERE id = '" + this.id + "';";
      //Ejecutar consulta propuesta:
      boolean rs = DB.executeUpdate(consulta);
      return rs;
    } else {
      return false;
    }
  }

  public boolean anadirParticipante(Usuario participante) {
    this.participantes.add(participante);
    String consulta = "INSERT INTO Participante (Evento_id, Usuario_id) VALUES (" +
        "'" + this.id + "', " +
        "'" + participante.getId() + "');";
    boolean rs = DB.executeUpdate(consulta);
    return rs;
  }

  public boolean eliminarParticipante(Usuario participante) {
    String consulta = "DELETE FROM Participante" +
        "   WHERE Usuario_id = '" + participante.getId() + "' and" +
        "        Evento_id = '" + this.id + "';";
    for (int i = 0; i < participantes.size(); i++) {
      if (participantes.get(i).getId() == participante.getId()) {
        boolean rs = DB.executeUpdate(consulta);
        participantes.remove(i);
        return rs;
      }
    }
    return false;
  }

  public boolean anadirPublicacion(Publicacion publicacion) {
    this.publicaciones.add(publicacion);
    return false;
  }

  public boolean eliminar() {
    String consulta = "SELECT * " +
        "              FROM Evento" +
        "              WHERE id = '" + this.id + "';";
    ResultSet rs = DB.executeQuery(consulta);
    try {
      if (rs.next()){
        String consulta1 = "DELETE FROM Evento" +
            "   WHERE id = '" + this.id + "';";
        boolean rs1 = DB.executeUpdate(consulta1);
        return rs1;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("nombre", this.nombre);
    json.put("ubicacion", this.ubicacion);
    json.put("fecha", this.fecha);
    json.put("host", this.host.toJson());
    json.put("valoracion", this.valoracion);

    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < this.valoraciones.size(); i++) {
      jsonArray.put(this.valoraciones.get(i).toJson());
    }
    json.put("valoraciones", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.etiquetas.size(); i++) {
      jsonArray.put(this.etiquetas.get(i));
    }
    json.put("etiquetas", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.comentarios.size(); i++) {
      jsonArray.put(this.comentarios.get(i).toJson());
    }
    json.put("comentarios", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.participantes.size(); i++) {
      jsonArray.put(this.participantes.get(i).toJson());
    }
    json.put("participantes", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.publicaciones.size(); i++) {
      jsonArray.put(this.publicaciones.get(i).toJson());
    }
    json.put("publicaciones", jsonArray);
    return json;
  }
}