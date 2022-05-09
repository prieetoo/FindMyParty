package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
  private List<Publicacion> publicacions;
  private LocalDate fechaNacimiento;

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
    this.publicacions = new ArrayList<>();
    this.fechaNacimiento = fechaNacimiento;
    //this.participa = new ArrayList<>();

    String consulta = "INSERT INTO Usuario (nombre, fecha_nacimiento, mail,password,valoracion) " +
            "VALUES ('" + nombre + "', STR_TO_DATE('" + fechaNacimiento.toString() + "','%Y-%m-%d'),'" +
            email + "','" + password + "',0 );";
    ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(rs.next())
        this.id = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Usuario(int id){
    String consulta = "SELECT * FROM Usuario e" +
        "WHERE e.id = '" + id + "';";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (rs.next()) {
        this.id = id;
        this.nombre = rs.getString(2);
        this.email = rs.getString(3);
        this.password = rs.getString(4);
        this.fechaNacimiento = LocalDate.parse(rs.getString(5));
        this.valoracion = rs.getFloat(6);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static String iniciarSesion(String email, String password){
    // Consultar en la BBDD mail i id
    String consulta = "SELECT id, password FROM Usuario u where u.mail = '" + email + "';";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if(rs.next()) {
        if(Objects.equals(rs.getString("password"), password)){
          return "Id del usuario: " + String.valueOf(rs.getInt("id"));
        }else{
          return "Wrong Password";
        }
      }else{
        return "This Email has no user";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "-1";
  }

  public static String registrar(String nombre, String password, LocalDate fechaNacimiento, String foto, String email) {
    //Comprobar existencia de mail
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
            return String.valueOf(rs.getInt(1));
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }else{
        return "This Email has already a user account";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "-1";
  }

  public static String recuperarPassword(String email){

    String consulta = "SELECT password FROM Usuario u where u.mail = " + email + ";";
    // Consultar a la bd password, si no existe email devolver mensaje de error
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if(rs.next() == false) { return "This email doesn't belong to any user"; }
      return rs.getString("password");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "-1";
  }

  public boolean modificarPerfil(String nombre,String password, String fechaNacimiento, String foto, String email){
    // Añadir a la bd

    String consulta = "UPDATE Usuario " +
            "SET nombre =' " + nombre + "'," +
            "    password  = '" + password + "'," +
            "    fecha_nacimiento = STR_TO_DATE('" + fechaNacimiento + "','%d/%m/%Y')," +
            "    foto = '" + foto + "'," +
            "    mail = '" + email +
            "' WHERE id = " + this.id  + ";";
    this.nombre=nombre;
    this.password=password;
    this.foto=foto;
    this.email=email;
    boolean rs = DB.getInstance().executeUpdate(consulta);
    return rs;
  }

  public boolean eliminar(String password){
    boolean rs = false;
    String consulta = "DELETE FROM `Comentariousuario" +
            " WHERE autor_id = " + this.id + "OR destinatario_id = " + this.id +";" +
            " DELETE FROM `Participante`" +
            " WHERE autor_id = '" + this.id + "';" +
            "DELETE FROM `Valoracionevento`" +
            " WHERE autor_id = '" + this.id + "';" +
            "DELETE FROM `Valoracionusuario`" +
            " WHERE autor_id = '" + this.id + "' OR destinatario_id = '" + this.id + "';" +
            "DELETE FROM Usuario" +
            " WHERE id = " + this.id;
    if (Objects.equals(password, this.password)){
      for (Evento e: this.eventos) {
        Evento.eliminar(e.getId());
      }
      rs = DB.getInstance().executeUpdate(consulta);
    }

    return rs;
  }

  public boolean recibirValoracion(Valoracion valoracion){

    if (valoracion.getValor() >= 0 && valoracion.getValor() <= 10){
      valoraciones.add(valoracion);
      calcularValoracion();
      return true;
    }
    else return false;
  }

  private void calcularValoracion(){
    float total = 0F;
    for (int i =0; i< this.valoraciones.size(); i++){
      total +=this.valoraciones.get(i).getValor();
    }
    this.valoracion = total/this.valoraciones.size();
    String consulta = "UPDATE Usuario" +
            " SET valoracion = " + this.valoracion +
            " WHERE id = " + this.id + ";";
    boolean rs = DB.getInstance().executeUpdate(consulta);
  }
  public boolean seguirUsuario(Usuario usuario){

    String consulta = "INSERT INTO `Sigue`" +
            "(`autor_id`," +
            "`destinatario_id`)" +
            "VALUES " +
            "(" + this.id + "," +
            usuario.getId() + ");";
    return DB.getInstance().executeUpdate(consulta);
  }
  public boolean dejarSeguirUsuario(Usuario usuario){

    String consulta = "DELETE FROM Sigue " +
            "WHERE autor_id = " + this.id +
            " AND destinatario_id = " + usuario.getId() +";";
    return DB.getInstance().executeUpdate(consulta);
  }

  public boolean añadirPublicacion(Usuario autor, List<String> img, String contenido){
    LocalDateTime fecha = LocalDateTime.now();
    this.publicacions.add(new Publicacion(this,fecha,img,contenido));
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

  public boolean crearEvento(String nombre, String ubicacion, String fecha, ArrayList<String> etiquetas, Punto coordenadas){
    //Esperar hasta que evento este
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    LocalDate localDate = LocalDate.parse(fecha,formatter);
    this.eventos.add(new Evento(nombre,ubicacion,localDate,this,etiquetas, coordenadas));
    //Modificar en la bd
    String consulta = "UPDATE Evento SET " +
            " nombre = " + nombre  + "," +
            " ubicacion = " + ubicacion + "," +
            " fecha = TO_DATE('" + fecha + "'.'yyyy/mm/dd')" +
            " WHERE autor_id = " + this.id + " AND id = " + id + ";";
    //boolean rs = DB.getInstance().executeUpdate(consulta);
    return true;
  }

  public boolean modificarEvento(String nombre, String ubicacion,String fecha, int id, Punto coordenadas) {

    boolean owner = false;
    int y = 0;
    while (!owner && y < this.eventos.size() ){
      if (this.eventos.get(y).getId() == id)
        owner = true;

    }
    if(owner) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
      LocalDate localDate = LocalDate.parse(fecha,formatter);
      for (int i = 0; i < this.eventos.size(); i++) {
        if (eventos.get(i).getId() == id)
          return eventos.get(i).modificar(nombre, ubicacion, localDate, this, eventos.get(i).getEtiquetas(), coordenadas);
      }
    }
    return owner;
  }

  public boolean valorarEvento(Evento destinatario, float valoracion) {
    String consulta = "SELECT * FROM `Comentarioevento` Where autor_id != " + this.id + " AND Evento_id != " + destinatario.getId() + " ;";
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    try {
      if (!rs.next()) {
        return destinatario.valorar(new Valoracion(this, destinatario, valoracion));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

    public boolean valorarUsuario(Usuario destinatario, float valoracion) {
    boolean firstTime = true;
    int i = 0;
    while (firstTime && i < destinatario.getValoraciones().size() ){
      if (destinatario.getValoraciones().get(i).getDestino().getId() == destinatario.getId())
        firstTime = false;

    }
    if(firstTime){
      Valoracion val = new Valoracion(this,destinatario, valoracion);
      destinatario.recibirValoracion(val);
      return true;
    }
    else return false;

  }

  public JSONObject toJson(){
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("nombre", this.nombre);
    json.put("password", this.password);
    json.put("fechaNacimiento", this.fechaNacimiento.toString());
    json.put("foto", this.foto);
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