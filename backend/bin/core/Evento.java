package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class Evento {
  private int id;
  private String nombre;
  private Punto coordenadas; //esto hay que cambiarlo en la bd sigue como varchar
  private String ubicacion;
  private LocalDateTime fecha;
  private Usuario host;
  private float valoracion;
  private int idHost;
  private ArrayList<Valoracion> valoraciones;
  private ArrayList<String> etiquetas;
  private ArrayList<Comentario> comentarios;
  private ArrayList<Usuario> participantes;
  private ArrayList<Publicacion> publicaciones;
  private boolean activo;
  private String descripcion;
  private boolean coste;
  private int nParticipantes;

  public Evento(Evento ev) {
    this.nombre = ev.getNombre();
    this.ubicacion = ev.getUbicacion();
    this.fecha = ev.getFecha();
    this.host = ev.getHost();
    this.etiquetas = ev.getEtiquetas();
    this.valoracion = 0;
    this.valoraciones = new ArrayList<>();
    this.comentarios = new ArrayList<>();
    this.participantes = new ArrayList<>();
    this.publicaciones = new ArrayList<>();
    this.activo = fecha.isBefore(LocalDateTime.now());
    this.descripcion = ev.descripcion;
    this.coste = false;
  }

  public Evento(String nombre, Punto coordenadas, String ubicacion, LocalDateTime fecha, Usuario host, ArrayList<String> etiquetas, String descripcion, boolean coste) {
    this.nombre = nombre;
    this.coordenadas = coordenadas;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;
    this.valoracion = 0;
    this.valoraciones = new ArrayList<>();
    this.comentarios = new ArrayList<>();
    this.participantes = new ArrayList<>();
    this.publicaciones = new ArrayList<>();
    this.activo = fecha.isBefore(LocalDateTime.now());
    this.ubicacion = ubicacion;
    this.descripcion = descripcion;
    this.coste = coste;
    //Añadir a la BD
    String consulta = "INSERT INTO Evento (nombre, ubicacion, fecha, valoracion, usuario_id, x, y) " +
        "VALUES ('" +
        nombre + "', '" +
        ubicacion + "', STR_TO_DATE('" +
        fecha.toString() + "','%Y-%m-%d'), '" +
        this.valoracion + "', '" +
        host.getId() + "','" +
        coordenadas.getX() + "','" +
        coordenadas.getY()+ "','" +
        descripcion + "');";
    ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(rs.next())
        this.id = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Evento(int id){
    String consulta = "SELECT * FROM Evento e " +
        "WHERE e.id = '" + id + "';";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (rs.next()) {
        this.id = id;
        this.nombre = rs.getString(3);
        this.ubicacion = rs.getString(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.fecha = LocalDateTime.parse(rs.getString(5),formatter);
        this.valoracion = rs.getFloat(6);
        this.coordenadas = new Punto(rs.getFloat("x"),rs.getFloat("y"));
        this.idHost = rs.getInt(2);
        this.valoraciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.etiquetas = new ArrayList<>();
        this.descripcion = rs.getString(9);
        this.coste = rs.getBoolean(10);
        this.nParticipantes = rs.getInt("participantes");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Evento(int id,String nombre, Punto ubicacion, LocalDateTime fecha, Usuario host, ArrayList<String> etiquetas) {
    this.nombre = nombre;
    this.coordenadas = ubicacion;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;
    this.valoracion = 0;
    this.valoraciones = new ArrayList<>();
    this.comentarios = new ArrayList<>();
    this.participantes = new ArrayList<>();
    this.publicaciones = new ArrayList<>();
    this.activo = fecha.isBefore(LocalDateTime.now());
    this.id = id;
    String consulta = "";
    ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(rs.next())
        this.id = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static boolean crear(String nombre, Punto coordenadas, String ubicacion, LocalDateTime fecha, Usuario host, ArrayList<String> etiquetas, String descripcion, int coste) {
    int valoracion = 0;
    int event_id = 0;
    String aux = fecha.toString();
    String consulta = "INSERT INTO Evento (nombre, ubicacion, fecha, valoracion, usuario_id, x, y, descripcion, coste, participantes) " +
        "VALUES ('" + nombre + "', '" +
        ubicacion + "', STR_TO_DATE('" + aux + "','%Y-%m-%dT%H:%i:%s'), '" +
        valoracion + "', '" + host.getId() + "','" + coordenadas.getX() + "','" +
        coordenadas.getY() + "','" + descripcion + "','" + coste + "', 1);";
    ResultSet result_insert = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(result_insert.next())
        event_id = result_insert.getInt(1);
      else{
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    for (String etiqueta : etiquetas) {
      String consulta2 = "INSERT INTO Etiqueta (etiqueta, evento_id) VALUES ('" + etiqueta + "','" + event_id + "')";
      if(!DB.getInstance().executeUpdate(consulta2)){
        return false;
      }
    }
    return true;
  }

  public boolean modificar(String nombre, String ubicacion, LocalDateTime fecha, Usuario host, ArrayList<String> etiquetas, Punto coordenadas) {
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
    return DB.getInstance().executeUpdate(consulta);
  }

  public static boolean valorar(int evento, float valoracion, int autor) {
    String consulta = "SELECT valor FROM `Valoracionevento` Where Usuario_id = " + autor + " AND Evento_id = " + evento + " ;";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (rs.next()) {
        return false;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    new Valoracion(autor,evento, valoracion, true);

    consulta = "SELECT `valor`" +
            "FROM `Valoracionevento` WHERE Evento_id = "+ evento +";";
    float total = 0;
    float n = 0;
    rs = DB.getInstance().executeQuery(consulta);
    try {
      while(rs.next()) {
        total += rs.getInt("valor");
        n++;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    total = total/n;
    consulta = "UPDATE Evento" +
        " SET valoracion = " + total +
        " WHERE id = " + evento + ";";
    return DB.getInstance().executeUpdate(consulta);
  }

  public static boolean anadirParticipante(int evento_id, int participante_id) {
    int participantes = 1;
    String consulta = "INSERT INTO Participante (Evento_id, Usuario_id) VALUES (" +
        "'" + evento_id + "', " +
        "'" + participante_id + "');";
    boolean rs1 = DB.getInstance().executeUpdate(consulta);

    String consulta1 = "SELECT participantes FROM Evento WHERE id = "+ evento_id +";";
    ResultSet aux = DB.getInstance().executeQuery(consulta1);
    try {
      if (aux.next()) {
        participantes += aux.getInt("participantes");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    String consulta2 = "UPDATE Evento SET participantes = '" + participantes + "' WHERE id = " + evento_id + ";";
    return DB.getInstance().executeUpdate(consulta2);
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

  private void loadEtiquetas(){
    String consulta = "SELECT * FROM Etiqueta where evento_id = " + this.id ;
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      while (rs.next()) {
        this.etiquetas.add(rs.getString("etiqueta"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  private void loadComentarios(){
    String consulta = "SELECT * FROM Comentarioevento where evento_id = " + this.id ;
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      while (rs.next()) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(rs.getString("fecha"),formatter);
        this.comentarios.add(new Comentario(this,rs.getInt("Usuario_id"),dateTime,rs.getString("contenido")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("nombre", this.nombre);
    json.put("ubicacion", this.ubicacion);
    json.put("coordenadas", this.coordenadas.toString());
    json.put("fecha", this.fecha);
    json.put("valoracion", this.valoracion);
    json.put("descripcion", this.descripcion);
    json.put("nParticipantes",this.nParticipantes);
    Usuario u = new Usuario(this.idHost);
    json.put("host",u.toJsonGetEvents());

    JSONArray jsonArray = new JSONArray();
    this.loadEtiquetas();
    this.loadComentarios();
    jsonArray = new JSONArray();
    for (int i = 0; i < this.etiquetas.size(); i++) {
      jsonArray.put(this.etiquetas.get(i));
    }
    json.put("etiquetas", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.comentarios.size(); i++) {
      jsonArray.put(this.comentarios.get(i).toJsonEvent());
    }
    json.put("comentarios", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.participantes.size(); i++) {
      jsonArray.put(this.participantes.get(i).toJson());
    }
    //json.put("participantes", jsonArray);

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

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
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