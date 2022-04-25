package core;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Usuario> usuarios = new ArrayList<>();
    Usuario u1 = new Usuario("Alex",1,"123","/foto.png","a@gmail.com");
    Usuario u2 = new Usuario("Kike",2,"567","/foto.png","k@gmail.com");
    usuarios.add(u1);
    usuarios.add(u2);
    u2.valorarUsuario(u1,4);
    u2.valorarUsuario(u1,2);
    u1.valorarUsuario(u2,10);
    System.out.print(u1.getValoracion() + " \n");
    System.out.print(u1.toJson());
  }

}
