package core;

import java.time.LocalDate;
import java.util.ArrayList;

public class Busqueda {
  private Punto ubicacionActual
  private ArrayList<Evento> eventos;

  public Busqueda (String Nombre, ArrayList<String> etiquetas, float radio, int numParticipantes, float valoracion, String diaSemana)
  {
    String consulta = "SELECT *" +
        "   FROM Evento ev, Etiqueta et" +
        "   WHERE (ev.id = et.Evento_id and" +
        "          ev.nombre = '' and" +
        "          et);";

  }



}
