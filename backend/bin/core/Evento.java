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
  private Punto ubicacion;
  private LocalDate fecha;
  private Usuario host;
  private float valoracion;
  private ArrayList<Valoracion> valoraciones;
  private ArrayList<String> etiquetas;
  private ArrayList<Comentario> comentarios;
  private ArrayList<Usuario> participantes;
  private ArrayList<Publicacion> publicaciones;
  private boolean activo;

  public Evento(String nombre, Punto ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
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
    this.activo = fecha.isBefore(LocalDate.now());
    //Añadir a la BD
    String consulta = "INSERT INTO Evento (nombre, ubicacion, fecha, valoracion, usuario_id) " +
        "VALUES ('" +
        nombre + "', '" +
        ubicacion.toString() + "', STR_TO_DATE('" +
        fecha.toString() + "','%Y-%m-%d'), '" +
        this.valoracion + "', '" +
        host.getId() + "');";
    ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(rs.next())
        this.id = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean modificar(String nombre, Punto ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;

    //Modificar la BD
    String consulta1 = "UPDATE Evento " +
        "SET nombre = '" + nombre + "', " +
        "    ubicacion = '" + ubicacion.toString() + "', " +
        "    fecha = STR_TO_DATE('" + fecha + "','%Y-%m-%d'), " +
        "    usuario_id = " + host.getId() +
        " WHERE id = " + this.id + ";";
    boolean rs = DB.getInstance().executeUpdate(consulta1);
    for (int i = 0; i < etiquetas.size() && rs; i++) {
      String consulta2 = "INSERT INTO Etiqueta (etiqueta, evento_id) VALUES (" +
          "'" + etiquetas.get(i) + "', " +
          "'" + this.id + "');";
      rs = DB.getInstance().executeUpdate(consulta2);
    }
    return rs;
  }

  public boolean comentar(Comentario comentario) {
    this.comentarios.add(comentario);
    String consulta = "UPDATE EVENTO" +
                      "SET Evento_id = '" + this.id +
                      "WHERE id = '" + comentario.getId() + "';";
    //Ejecutar propuesta:
    boolean rs = DB.getInstance().executeUpdate(consulta);
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
      String consulta = "UPDATE Evento" +
          " SET valoracion = " + val +
          " WHERE id = " + this.id + ";";
      //Ejecutar consulta propuesta:
      boolean rs = DB.getInstance().executeUpdate(consulta);
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
    boolean rs = DB.getInstance().executeUpdate(consulta);
    return rs;
  }

  public boolean eliminarParticipante(Usuario participante) {
    String consulta = "DELETE FROM Participante" +
        "   WHERE Usuario_id = '" + participante.getId() + "' and" +
        "        Evento_id = '" + this.id + "';";
    for (int i = 0; i < participantes.size(); i++) {
      if (participantes.get(i).getId() == participante.getId()) {
        boolean rs = DB.getInstance().executeUpdate(consulta);
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
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (rs.next()){
        String consulta1 = "DELETE FROM `Valoracionevento`" +
                " WHERE Evento_id = " + this.id + ";" +
                "DELETE FROM Participante" +
                " WHERE Evento_id = " + this.id + " ;" +
                " DELETE FROM Etiqueta" +
                " WHERE evento_id = " + this.id + ";" +
                " DELETE FROM Evento" +
                " WHERE id = '" + this.id + "';";
        boolean rs1 = DB.getInstance().executeUpdate(consulta1);
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
    json.put("ubicacion", this.ubicacion.toString());
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

  //Getters y setters
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

  public Punto getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(Punto ubicacion) {
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

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }
}