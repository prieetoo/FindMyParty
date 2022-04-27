package core;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    List<Usuario> usuarios = new ArrayList<>();
    Usuario u1 = new Usuario("Alex","123","/foto.png","a@gmail.com");
    Usuario u2 = new Usuario("Kike","567","/foto.png","k@gmail.com");
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
    u1.crearEvento("Test Lis",new Punto(41,92),"26/4/2022",etiquetas);
    //Modificar
    u1.modificarEvento("Test modificado",new Punto(78,23),"26/4/2022",u1.getEventos().get(0).getId());
    u1.modificarPerfil("Alex2","321","5/5/2002","/foto2.png","nuevo@gmai.com");
    //System.out.print(u1.toJson());
  }

}
