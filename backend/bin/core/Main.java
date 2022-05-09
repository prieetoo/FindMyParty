package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Usuario u1 = new Usuario("Alex","123", LocalDate.now(),"/foto.png","a@gmail.com");
    Usuario u2 = new Usuario("Kike","567", LocalDate.now(),"/foto.png","k@gmail.com");
    ArrayList<String> etiquetas = new ArrayList<>();
    etiquetas.add("rave");
    //Crear evento
    u1.crearEvento("Test Lis","Sitio","26/4/2022",etiquetas, new Punto(41,92));
    Evento.anadirParticipante(u1.getEventos().get(0).getId(), u2.getId());
    Usuario.comentar(u1.getId(), u2.getId(), "NewComentario");
    Evento.comentar(u1.getEventos().get(0).getId(), u2.getId(), "NewComentario");
    int id = u1.getEventos().get(0).getId();
    Evento.eliminar(id);
    u1.getComentarios().get(0).eliminar();
    //u2.valorarUsuario(u1,4);
    //System.out.print(u1.toJson());
  }
  private static void creacion(){
    List<Usuario> usuarios = new ArrayList<>();
    Usuario u1 = new Usuario("Alex","123", LocalDate.now(),"/foto.png","a@gmail.com");
    Usuario u2 = new Usuario("Kike","567", LocalDate.now(),"/foto.png","k@gmail.com");
    System.out.print(u1.getId() + " \n");
    usuarios.add(u1);
    usuarios.add(u2);
    //Valorar usuario
    u2.valorarUsuario(u1,4);
    u2.valorarUsuario(u1,2);
    u1.valorarUsuario(u2,10);
    System.out.print(u1.getValoracion() + " \n");
    ArrayList<String> etiquetas = new ArrayList<>();
    etiquetas.add("rave");
    //Crear evento
    u1.crearEvento("Test Lis","Sitio","26/4/2022",etiquetas, new Punto(41,92));
    //Modificar
    u1.modificarEvento("Test modificado","Sitio","26/4/2022",u1.getEventos().get(0).getId(), new Punto(78,23));
    u1.modificarPerfil("Alex2","321","5/5/2002","/foto2.png","nuevo@gmai.com");
  }

}
