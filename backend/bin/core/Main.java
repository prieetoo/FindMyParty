package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    //Usuario.registrar("Valorador","algo",LocalDate.now(),"foto","mail@mail.mail");
    //Usuario.registrar("Valorado","algo",LocalDate.now(),"foto","mail3@mail.mail");
    //Usuario.valorarUsuario(81,10,83);
    Evento.crear("Algo", new Punto(1,1),"Nuevo", LocalDate.now(),new Usuario(81),new ArrayList<String>() );
  }


}
