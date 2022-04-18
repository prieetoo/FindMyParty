import netscape.javascript.JSObject;

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

  public boolean iniciarSesion(String email, String password){
    return false;
  }

  public boolean recuperarPassword(String email){
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

  private JSObject toJson(){
    return null;
  }

  public float getValoracion() {
    return valoracion;
  }
}