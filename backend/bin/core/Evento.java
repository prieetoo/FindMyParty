package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Evento {
  private int id;
  private String nombre;
  private Punto coordenadas; //esto hay que cambiarlo en la bd sigue como varchar
  private String ubicacion;
  private LocalDate fecha;
  private Usuario host;
  private float valoracion;
  private ArrayList<Valoracion> valoraciones;
  private ArrayList<String> etiquetas;
  private ArrayList<Comentario> comentarios;
  private ArrayList<Usuario> participantes;
  private ArrayList<Publicacion> publicaciones;
  private boolean activo;

  public Evento(Evento ev) {
    this.nombre = ev.getNombre();
    this.ubicacion = ev.getUbicacion();
    this.fecha = ev.getFecha();
    this.host = ev.getHost();
    this.etiquetas = ev.getEtiquetas();
    this.valoracion = 0;
    this.valoraciones = new ArrayList<Valoracion>();
    this.comentarios = new ArrayList<Comentario>();
    this.participantes = new ArrayList<Usuario>();
    this.publicaciones = new ArrayList<Publicacion>();
    this.activo = fecha.isBefore(LocalDate.now());
  }

  public Evento(String nombre, Punto coordenadas, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
    this.nombre = nombre;
    this.coordenadas = coordenadas;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;
    this.valoracion = 0;
    this.valoraciones = new ArrayList<Valoracion>();
    this.comentarios = new ArrayList<Comentario>();
    this.participantes = new ArrayList<Usuario>();
    this.publicaciones = new ArrayList<Publicacion>();
    this.activo = fecha.isBefore(LocalDate.now());
    this.ubicacion = ubicacion;
    //Añadir a la BD
    String consulta = "INSERT INTO Evento (nombre, ubicacion, fecha, valoracion, usuario_id, x, y) " +
        "VALUES ('" +
        nombre + "', '" +
        ubicacion + "', STR_TO_DATE('" +
        fecha.toString() + "','%Y-%m-%d'), '" +
        this.valoracion + "', '" +
        host.getId() + "','" +
        coordenadas.getX() + "','" +
        coordenadas.getY()+ "');";
    ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(rs.next())
        this.id = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Evento(int id){
    String consulta = "SELECT * FROM Evento e" +
        "WHERE e.id = '" + id + "';";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (rs.next()) {
        this.id = id;
        this.nombre = rs.getString(3);
        this.ubicacion = rs.getString(4);
        this.fecha = LocalDate.parse(rs.getString(5));
        this.valoracion = rs.getFloat(6);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static boolean crear(String nombre, Punto coordenadas, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
    int valoracion = 0;
    String consulta = "INSERT INTO Evento (nombre, ubicacion, fecha, valoracion, usuario_id, x, y) " +
        "VALUES ('" +
        nombre + "', '" +
        ubicacion + "', STR_TO_DATE('" +
        fecha.toString() + "','%Y-%m-%d'), '" +
        valoracion + "', '" +
        host.getId() + "','" +
        coordenadas.getX() + "','" +
        coordenadas.getY()+ "');";
    return DB.getInstance().executeUpdate(consulta);
  }

  public boolean modificar(String nombre, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas, Punto coordenadas) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;

    //Modificar la BD
    String consulta1 = "UPDATE Evento " +
        "SET nombre = '" + nombre + "', " +
        "    ubicacion = '" + ubicacion + "', " +
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

  public static boolean comentar(int event_id, int user_id,  String contenido) {
    LocalDateTime localDate = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDate= localDate.format(formatter);
    String consulta = "INSERT INTO `Comentarioevento` (`fecha`,`contenido`,`Usuario_id`,`Evento_id`)" +
            " VALUES ( STR_TO_DATE('" + formattedDate+ "','%Y-%m-%d %T'),'" + contenido + "'," + user_id + ","  + event_id +  " );";
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

  public static boolean anadirParticipante(int evento_id, int participante_id) {
    String consulta = "INSERT INTO Participante (Evento_id, Usuario_id) VALUES (" +
        "'" + evento_id + "', " +
        "'" + participante_id + "');";
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

  public static boolean eliminar(int id) {
    String consulta = "SELECT * " +
        "              FROM Evento" +
        "              WHERE id = '" + id + "';";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (rs.next()){
        String consulta1 = " DELETE FROM Evento" +
                " WHERE id = '" + id + "';";
        return DB.getInstance().executeUpdate(consulta1);
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
    json.put("coordenadas", this.coordenadas.toString());
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

  public Punto getCoordenadas() {
    return coordenadas;
  }

  public void setCoordenadas(Punto coordenadas) {
    this.coordenadas = coordenadas;
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

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }
}