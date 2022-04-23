package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Usuario{
  private String nombre;
  private int id;
  private String password;
  private String foto;
  private String email;
  private Evento e; //esto lo cambiamos si hacemos herencia
  private float valoracion;
  private ArrayList<Comentario> comentarios;
  private List<Float> valoraciones;

  public Usuario(String nombre, int id, String password, String foto, String email){
    this.nombre = nombre;
    this.id = id;
    this.password = password;
    this.foto = foto;
    this.email = email;
    this.valoracion = 0F;
    this.valoraciones = new ArrayList<>();
    this.comentarios = new ArrayList<>();
    this.e = null;

  }
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

  public void setValoracion(float valoracion) {
    this.valoracion = valoracion;
  }

  public List<Float> getValoraciones() {
    return valoraciones;
  }

  public void setValoraciones(List<Float> valoraciones) {
    this.valoraciones = valoraciones;
  }

  public static Usuario iniciarSesion(String email, String password){

    // Consultar a la bd mail password
    String consulta = "SELECT mail,password FROM Usuario u where u.mail = " + email + " AND u.password = " + password + " ;";
    //si existe iniciar y true
    ResultSet rs = DB.execute(consulta);
    //por que devolvemos un usuario nuevo si esta iniciando sesion?????
    return new Usuario("nombre", 0 ,password, "foto",email);
  }

  public boolean recuperarPassword(String email){

    String consulta = "SELECT password FROM Usuario u where u.mail = " + email + ";";
    // Consultar a la bd password, si no existe email return false
    // ResultSet rs = DB.execute(consulta);
    // if(rs.next() == false) { return false}
    return true;
  }

  public boolean modificarPerfil(String nombre,String password, String fechaNacimiento, String foto, String email){
    // Añadir a la bd

    String consulta = "UPDATE Usuario " +
            "SET nombre = " + nombre + "," +
            "    password  = " + password + "," +
            "    fecha_nacimiento = " + fechaNacimiento + "," +
            "    foto = " + foto + "," +
            "    mail = " + email +
            " WHERE id = " + this.id  + ";";
    this.nombre=nombre;
    this.password=password;
    this.foto=foto;
    this.email=email;
    ResultSet rs = DB.execute(consulta);
    return rs!=null;
  }

  public boolean eliminar(String password){

    // Consultar a la bd mail password
    String consulta = "SELECT mail,password FROM Usuario u where u.mail = " + this.email + " AND u.password = " + password + " ;";
    //si existe eliminar
    consulta =  "DELETE FROM Usuario u WHERE u.id = " + this.id;
    return false;
  }

  public boolean recibirValoracion(float valoracion){

    if (valoracion >= 0 && valoracion <= 10){
      valoraciones.add(valoracion);
      // Añadir a la bd
      calcularValoracion();
      return true;
    }
    else return false;
  }

  private void calcularValoracion(){
    float total = 0F;
    for (float val: this.valoraciones) {
      total += val;

    }
    this.valoracion = total/this.valoraciones.size();
  }

  private boolean recivirComentario(String fecha, String contenido, Usuario usuario){ //echadle un ojo
    //Esperar hasta que comentario este
    LocalDate localDate = LocalDate.parse(fecha);
    Comentario c = new Comentario(id, usuario, this, localDate, contenido);
    comentarios.add(c);
    //Añadir comentario
    String consulta = "INSERT INTO ComentarioUsuario (fecha,contenido,Usuario_id,Usuario_id1) VALUES (TO_DATE('" + fecha + "'.'yyyy/mm/dd')," +
            contenido + "," + this.id + "," + this + ");";
    ResultSet rs = DB.execute(consulta);
    return rs!= null;
  }

  private boolean crearEvento(String nombre, String ubicacion, String fecha, ArrayList<String> etiquetas){
    //Esperar hasta que evento este

    LocalDate localDate = LocalDate.parse(fecha);
     this.e = new Evento(nombre,ubicacion,localDate,this,etiquetas);
    //Modificar en la bd
    String consulta = "UPDATE Evento SET " +
            " nombre = " + nombre  + "," +
            " ubicacion = " + ubicacion + "," +
            " fecha = TO_DATE('" + fecha + "'.'yyyy/mm/dd')" +
            "WHERE Usuario_id = " + this.id + " AND id = " + id + ";";
    ResultSet rs = DB.execute(consulta);
    return rs!=null;
  }

  private boolean modificarEvento(String nombre,String ubicacion,String fecha){

    LocalDate localdate = LocalDate.parse(fecha);
    return e.modificar(nombre,ubicacion,localdate,this, e.getEtiquetas());
  }

  public String toJson(){
    Gson gson = new GsonBuilder().setPrettyPrinting().create();;
    return gson.toJson(this);
  }


  public float getValoracion() {
    return valoracion;
  }
}