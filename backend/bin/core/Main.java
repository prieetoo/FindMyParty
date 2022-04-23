package core;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Usuario> usuarios = new ArrayList<>();
    Usuario u1 = new Usuario("Alex",1,"123","/foto.png","a@gmail.com");
    usuarios.add(u1);
    u1.recibirValoracion(4);
    u1.recibirValoracion(2);
    u1.recibirValoracion(10);
    System.out.print(u1.getValoracion() + " \n");
    System.out.print(u1.toJson());
  }

}
