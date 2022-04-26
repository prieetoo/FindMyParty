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
    u2.valorarUsuario(u1,4);
    u2.valorarUsuario(u1,2);
    u1.valorarUsuario(u2,10);
    System.out.print(u1.getValoracion() + " \n");
    ArrayList<String> etiquetas = new ArrayList<>();
    etiquetas.add("rave");
    u1.crearEvento("Test Lis","Aqui","26/4/2022",etiquetas);

    //System.out.print(u1.toJson());
  }

}
