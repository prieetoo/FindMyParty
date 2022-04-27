package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Usuario{
  private String nombre;
  private int id;
  private String password;
  private String foto;
  private String email;
  private ArrayList<Evento> eventos; //esto lo cambiamos si hacemos herencia
  private float valoracion;
  private ArrayList<Comentario> comentarios;
  private List<Valoracion> valoraciones;
  private LocalDate fechaNacimiento;

  public Usuario(String nombre,String password, String foto, String email){
    this.nombre = nombre;
    this.id = 0;
    this.password = password;
    this.foto = foto;
    this.email = email;
    this.valoracion = 0F;
    this.valoraciones = new ArrayList<>();
    this.comentarios = new ArrayList<>();
    this.eventos = new ArrayList<>();

    String consulta = "INSERT INTO Usuario (nombre,mail,password) " +
            "VALUES ('" + nombre + "','" +
            email + "','" + password + "' );";
    ResultSet rs = DB.getInstance().executeUpdateWithKeys(consulta);
    try {
      if(rs.next())
        this.id = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Usuario iniciarSesion(String email, String password){

    // Consultar a la bd mail password
    String consulta = "SELECT mail,password FROM Usuario u where u.mail = " + email + " AND u.password = " + password + " ;";
    //si existe iniciar y true
    ResultSet rs = DB.getInstance().executeQuery(consulta);
    //por que devolvemos un usuario nuevo si esta iniciando sesion?????
    return new Usuario("nombre",password, "foto",email);
  }

  public boolean recuperarPassword(String email){

    String consulta = "SELECT password FROM Usuario u where u.mail = " + email + ";";
    // Consultar a la bd password, si no existe email return false
    // ResultSet rs = DB.getInstance().execute(consulta);
    // if(rs.next() == false) { return false}
    return true;
  }

  public boolean modificarPerfil(String nombre,String password, String fechaNacimiento, String foto, String email){
    // Añadir a la bd

    String consulta = "UPDATE Usuario " +
            "SET nombre = " + nombre + "," +
            "    password  = " + password + "," +
            "    fecha_nacimiento = STR_TO_DATE('" + fechaNacimiento + "','%d-%m-%Y')" + "," +
            "    foto = " + foto + "," +
            "    mail = " + email +
            " WHERE id = " + this.id  + ";";
    this.nombre=nombre;
    this.password=password;
    this.foto=foto;
    this.email=email;
    boolean rs = DB.getInstance().executeUpdate(consulta);
    return rs;
  }

  public boolean eliminar(String password){

    // Consultar a la bd mail password
    String consulta = "SELECT mail,password FROM Usuario u where u.mail = " + this.email + " AND u.password = " + password + " ;";
    //si existe eliminar
    consulta =  "DELETE FROM Usuario u WHERE u.id = " + this.id;
    return false;
  }

  public boolean recibirValoracion(Valoracion valoracion){

    if (valoracion.getValor() >= 0 && valoracion.getValor() <= 10){
      valoraciones.add(valoracion);
      // Añadir a la bd
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
  }

  private boolean recibirComentario(String fecha, String contenido, Usuario usuario){ //echadle un ojo
    //Esperar hasta que comentario este
    LocalDate localDate = LocalDate.parse(fecha);
    Comentario c = new Comentario(id, usuario, this, localDate, contenido);
    comentarios.add(c);
    //Añadir comentario
    String consulta = "INSERT INTO ComentarioUsuario (fecha,contenido,Usuario_id,Usuario_id1) VALUES (TO_DATE('" + fecha + "'.'yyyy/mm/dd')," +
            contenido + "," + this.id + "," + this + ");";
    boolean rs = DB.getInstance().executeUpdate(consulta);
    return rs;
  }

  public boolean crearEvento(String nombre, String ubicacion, String fecha, ArrayList<String> etiquetas){
    //Esperar hasta que evento este
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    LocalDate localDate = LocalDate.parse(fecha,formatter);
    this.eventos.add(new Evento(nombre,ubicacion,localDate,this,etiquetas));
    //Modificar en la bd
    String consulta = "UPDATE Evento SET " +
            " nombre = " + nombre  + "," +
            " ubicacion = " + ubicacion + "," +
            " fecha = TO_DATE('" + fecha + "'.'yyyy/mm/dd')" +
            " WHERE Usuario_id = " + this.id + " AND id = " + id + ";";
    //boolean rs = DB.getInstance().executeUpdate(consulta);
    return true;
  }

  public boolean modificarEvento(String nombre,String ubicacion,String fecha, int id) {

    boolean owner = false;
    int y = 0;
    while (!owner && y < this.eventos.size() ){
      if (this.eventos.get(y).getId() == id)
        owner = true;

    }
    if(owner) {
      LocalDate localdate = LocalDate.parse(fecha);
      for (int i = 0; i < this.eventos.size(); i++) {
        if (eventos.get(i).getId() == id)
          return eventos.get(i).modificar(nombre, ubicacion, localdate, this, eventos.get(i).getEtiquetas());
      }
    }
    return !owner;
  }

  public void valorarEvento(Evento destinatario, float valoracion) {
    //Valoracion val = new Valoracion(this, valoracion);
    //destinatario.valorar(val);
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
    json.put("fechaNacimiento", this.fechaNacimiento);
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