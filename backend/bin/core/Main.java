package core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    //Usuario.registrar("Valorador","algo",LocalDate.now(),"foto","mail@mail.mail");
    //Usuario.registrar("Valorado","algo",LocalDate.now(),"foto","mail3@mail.mail");
    //Usuario.valorarUsuario(81,10,83);
    Busqueda busqueda = new Busqueda(new Punto(1, 0.98f),4);
    System.out.print(busqueda.getJsonEventos().toString());
  }


}
