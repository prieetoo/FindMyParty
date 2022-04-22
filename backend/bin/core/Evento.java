package core;

import java.time.LocalDate;
import java.util.ArrayList;


public class Evento {

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
  }

  public boolean modificar(String nombre, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
    //Definir condiciones para la modificación de los parámetros
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;
    return true;
  }

  public boolean comentar(Comentario comentario) { //booleano necesario?
    this.comentarios.add(comentario);
    return true;
  }

  public boolean valorar(Valoracion valoracion) { //booleano necesario?
    this.valoraciones.add(valoracion);
    return true;
  }

  public boolean añadirParticipante(Usuario participante) { //booleano necesario?
    this.participantes.add(participante);
    return true;
  }

  public boolean eliminarParticipante(Usuario participante) {
    for (int i = 0; i < participantes.size(); i++) {
      if (participantes.get(i).getId() == participante.getId()) {
        participantes.remove(i);
        return true;
      }
    }
    return false;
  }

  public boolean eliminar() {
    //Eliminar evento de la lista de eventos de usuario host (necesario en evento?)
    return false;
  }

  public boolean añadirPublicacion(Publicacion publicacion) {
    this.publicaciones.add(publicacion);
    return true;
  }

  public String toJson() {
    //Pasar a formato JSON
    return null;
  }
}