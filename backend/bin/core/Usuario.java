package core;

import org.apache.coyote.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Usuario{
  private String nombre;
  private int id;
  private String password;
  private String foto;
  private String email;
  private ArrayList<Evento> eventos;
  //private ArrayList<Evento> participa;
  private float valoracion;
  private ArrayList<Comentario> comentarios;
  private List<Valoracion> valoraciones;
  private List<Publicacion> publicaciones;
  private LocalDate fechaNacimiento;
  private Punto ubicacion;

  public Usuario(String nombre,String password, LocalDate fechaNacimiento, String foto, String email){
    this.nombre = nombre;
    this.id = 0;
    this.password = password;
    this.foto = foto;
    this.email = email;
    this.valoracion = 0F;
    this.valoraciones = new ArrayList<>();
    this.comentarios = new ArrayList<>();
    this.eventos = new ArrayList<>();
    this.publicaciones = new ArrayList<>();
    this.fechaNacimiento = fechaNacimiento;
    //this.participa = new ArrayList<>();

  }

  public Punto getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(Punto ubicacion) {
    this.ubicacion = ubicacion;
  }

  public Usuario(int id){
    String consulta = "SELECT * FROM Usuario e" +
        " WHERE e.id = '" + id + "';";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (rs.next()) {
        this.id = id;
        this.nombre = rs.getString(2);
        this.email = rs.getString(3);
        this.password = rs.getString(4);
        this.valoracion = rs.getFloat(7);
        this.valoraciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static String ConvertToJson(String str){
    return "{\"result\":\"" + str + "\"}";
  }

  public static String iniciarSesion(String email, String password){
    // Consultar en la BBDD mail i id
    String consulta = "SELECT id, password FROM Usuario u where u.mail = '" + email + "';";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if(rs.next()) {
        if(Objects.equals(rs.getString("password"), password)){
          return ConvertToJson(String.valueOf(rs.getInt("id")));
        }else{
          return ConvertToJson("-1");
        }
      }else{
        return ConvertToJson("-2");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConvertToJson("0");
  }

  public static String registrar(String nombre, String password, LocalDate fechaNacimiento, String foto, String email) {
    //Comprobar existencia de mail
    String result = "";
    String consulta_mail = "SELECT password FROM Usuario u where u.mail = '" + email + "';";
    ResultSet rs_mail = DB.getInstance().executeQuery(consulta_mail);
    try {
      if(!rs_mail.next()) {
        //Añadir a la BD en caso que no exista
        String consulta = "INSERT INTO Usuario (nombre, fecha_nacimiento, mail, foto, password, valoracion) " +
            "VALUES ('" + nombre + "','" + fechaNacimiento.toString() + "','" +
            email + "','" + foto + "','" + password + "',0 );";
        ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
        try {
          if(rs.next())
            return ConvertToJson(String.valueOf(rs.getInt(1)));
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }else{
        return ConvertToJson("-1");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConvertToJson("0");
  }

  public static String recuperarPassword(String email){
    String consulta = "select password from Usuario where mail = '" + email + "';";
    // Consultar a la bd password, si no existe email devolver mensaje de error
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if(!rs.next()){
        return ConvertToJson("-1");
      }
      return ConvertToJson(rs.getString("password"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConvertToJson("0");
  }

  public static String modificarPerfil( int id, String nombre,String password, String fechaNacimiento, String foto, String email){
    // Añadir a la bd
    //comprobar existencia de usuario
    String consulta = "UPDATE Usuario " +
            "SET nombre =' " + nombre + "'," +
            "    password  = '" + password + "'," +
            "    fecha_nacimiento = STR_TO_DATE('" + fechaNacimiento + "','%d/%m/%Y')," +
            "    foto = '" + foto + "'," +
            "    mail = '" + email +
            "' WHERE id = " + id  + ";";
    ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(rs.next())
        return ConvertToJson(String.valueOf(rs.getInt(1)));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConvertToJson("0");
  }

  public static boolean eliminar(int id){
    boolean rs = false;
    String consulta = "DELETE FROM Usuario" + " WHERE id = " + id +";";
    return DB.getInstance().executeUpdate(consulta);
  }

  public static boolean recibirValoracion(int id,float valor){

    String consulta = "SELECT `valor`" +
            "FROM `Valoracionusuario` WHERE destinatario_id = "+ id +";";
    float total = valor;
    float n = 1;
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      while(rs.next()) {
        total += rs.getInt("valor");
        n++;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    total = total/n;
    consulta = "UPDATE Usuario" +
            " SET valoracion = " + total +
            " WHERE id = " + id + ";";
    return DB.getInstance().executeUpdate(consulta);
  }

  public static boolean seguirUsuario(String seguidor_id, String seguido_id){

    String consulta = "INSERT INTO `Sigue`" +
            "(`Usuario_id`," +
            "`Usuario_id1`)" +
            "VALUES " + "('" + seguidor_id + "','" + seguido_id + "');";
    return DB.getInstance().executeUpdate(consulta);
  }

  public static boolean dejarSeguirUsuario(String seguidor_id, String seguido_id){

    String consulta = "DELETE FROM Sigue " +
            "WHERE Usuario_id = '" + seguidor_id +
            "' AND Usuario_id1 = '" + seguido_id +"';";
    return DB.getInstance().executeUpdate(consulta);
  }

  public boolean añadirPublicacion(Usuario autor, List<String> img, String contenido){
    LocalDateTime fecha = LocalDateTime.now();
    this.publicaciones.add(new Publicacion(this,fecha,img,contenido));
    return true;
    }

  public static boolean comentar(int autor_id, int destinatario_id, String contenido){
    LocalDateTime localDate = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = localDate.format(formatter);

    String consulta = "INSERT INTO `Comentariousuario` (`fecha`,`contenido`,`autor_id`,`destinatario_id`)" +
            " VALUES ( STR_TO_DATE('" + formattedDateTime + "','%Y-%m-%d %T'),'" + contenido + "'," + autor_id + "," + destinatario_id + " );";
    boolean rs = DB.getInstance().executeUpdate(consulta);
    return rs;
  }

  public boolean modificarEvento(String nombre, String ubicacion, Punto coordenadas, String fecha, int id) {

    boolean owner = false;
    int y = 0;
    while (!owner && y < this.eventos.size() ){
      if (this.eventos.get(y).getId() == id)
        owner = true;

    }
    if(owner) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
      LocalDateTime localDateTime = LocalDateTime.parse(fecha,formatter);
      for (int i = 0; i < this.eventos.size(); i++) {
        if (eventos.get(i).getId() == id)
          return eventos.get(i).modificar(nombre, ubicacion, localDateTime, this, eventos.get(i).getEtiquetas(), coordenadas);
      }
    }
    return owner;
  }

  public static boolean valorarUsuario(int destinatario, float valoracion, int id) {
    boolean firstTime = true;
      String consulta = "SELECT `valor`" +
              "FROM `Valoracionusuario` WHERE destinatario_id = "+ destinatario +" AND autor_id = " + id + ";";
      ResultSet rs = DB.getInstance().executeQuery(consulta);
      try {
        if(rs.next()) {
          firstTime = false;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    if(firstTime){
      Valoracion val = new Valoracion(id,destinatario, valoracion);
      return Usuario.recibirValoracion(destinatario, val.getValor());
    }
    else return false;
  }
  public JSONObject toJsonGetEvents() {
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("nombre", this.nombre);
    json.put("email", this.email);
    return json;
  }

  public JSONObject toJson(){
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("nombre", this.nombre);
    json.put("email", this.email);
    json.put("valoracion", this.valoracion);

    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < this.valoraciones.size(); i++) {
      jsonArray.put(this.valoraciones.get(i).toJson());
    }
    json.put("valoraciones", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.comentarios.size(); i++) {
      jsonArray.put(this.comentarios.get(i).toJson());
    }
    json.put("comentarios", jsonArray);

    jsonArray = new JSONArray();
    for (int i = 0; i < this.eventos.size(); i++) {
      jsonArray.put(this.eventos.get(i).toJson());
    }
    json.put("eventos", jsonArray);

    return json;
  }

  //Getters y setters
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ArrayList<Evento> getEventos() {
    return eventos;
  }

  public void setEventos(ArrayList<Evento> eventos) {
    this.eventos = eventos;
  }

  public float getValoracion() {
    return valoracion;
  }

  public void setValoracion(float valoracion) {
    this.valoracion = valoracion;
  }

  public ArrayList<Comentario> getComentarios() {
    return comentarios;
  }

  public void setComentarios(ArrayList<Comentario> comentarios) {
    this.comentarios = comentarios;
  }

  public List<Valoracion> getValoraciones() {
    return valoraciones;
  }

  public void setValoraciones(List<Valoracion> valoraciones) {
    this.valoraciones = valoraciones;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
}