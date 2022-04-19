import com.google.gson.Gson;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Usuario{
  private String nombre;
  private int id;
  private String password;
  private String foto;
  private String email;
  private float valoracion;
  private List<Float> valoraciones;

  public Usuario(String nombre, int id, String password, String foto, String email){
    this.nombre = nombre;
    this.id = id;
    this.password = password;
    this.foto = foto;
    this.email = email;
    this.valoracion = 0F;
    this.valoraciones = new ArrayList<>();

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

  public boolean iniciarSesion(String email, String password){

    // Consultar a la bd mail password

    //si existe iniciar y true

    return false;
  }

  public boolean recuperarPassword(String email){

    // Consultar a la bd password, si no existe email return false

    return false;
  }

  public boolean modificarPerfil(String nombre,String password, String fecaNacimiento, String foto, String email){
    // Añadir a la bd

    this.nombre=nombre;
    this.password=password;
    this.foto=foto;
    this.email=email;
    return false;
  }

  public boolean eliminar(String password){
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

  private boolean recivirComentario(){
    //Esperar hasta que comentario este
    return false;
  }

  private boolean crearEvento(){
    //Esperar hasta que evento este
    return false;
  }

  private boolean modificarEvento(){
    //Esperar hasta que evento este
    return false;
  }

  public JsonObject toJson(){
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter(this.id+".json")) {
      gson.toJson(this, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public float getValoracion() {
    return valoracion;
  }
}