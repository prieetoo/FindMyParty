package core;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class EventoController {

  @GetMapping("/event/join/{event_id}/{user_id}")
  public String join(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id){
    if (Evento.anadirParticipante(Integer.parseInt(event_id), Integer.parseInt(user_id))) {
      return "User joined the event successfully";
    }
    return "Error joining user to event";
  }

  @PostMapping(value = "/event/create", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.ALL_VALUE)
  public String create_event(@RequestBody Map<String, ArrayList<String>> body){
    ArrayList<String> informacion = body.get("information");
    Punto p = new Punto(Float.parseFloat(informacion.get(1)), Float.parseFloat(informacion.get(2)));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime fecha = LocalDateTime.parse(informacion.get(3), formatter);
    int user_id = Integer.parseInt(informacion.get(4));
    String ubicacion = informacion.get(5);
    Usuario anfitrion = new Usuario(user_id);
    ArrayList<String> etiquetas = body.get("tickets");
    if (!Evento.crear(informacion.get(0), p, ubicacion, fecha, anfitrion, etiquetas)) {
      return "Error while creating Event";
    }
    return "Event created successfully";
  }

  @GetMapping("/event/comment_event/{event_id}/{user_id}/{content}")
  public String comment_event(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id, @PathVariable("content") String content){
    if (Evento.comentar(Integer.parseInt(event_id), Integer.parseInt(user_id), content)) {
      return "Comment sent";
    }
    return "Error commenting this event";
  }

  @GetMapping(value = "/event/eliminate/{event_id}")
  public String create_event(@PathVariable("event_id") String event_id){
    if (!Evento.eliminar(Integer.parseInt(event_id))) {
      return "Error while eliminating Event";
    }
    return "Event eliminated successfully";
  }

  @PostMapping("/event/valorar_event")
  public String valorar_usuario(@RequestBody Map<String, String> body){
    if (!Evento.valorar(Integer.parseInt(body.get("destinatario")),Float.parseFloat(body.get("valor")), Integer.parseInt(body.get("autor")))) {
      return "Error while valuating event";
    }
    return "Event valuating successfully";
  }

}
