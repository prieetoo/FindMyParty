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
    // 1 correct, -1 error
    if (Evento.anadirParticipante(Integer.parseInt(event_id), Integer.parseInt(user_id))) {
      return "\"result\":1";
    }
    return "\"result\":-1";
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
    // 1 correct, -1 error
    if (!Evento.crear(informacion.get(0), p, ubicacion, fecha, anfitrion, etiquetas)) {
      return "\"result\":-1";
    }
    return "\"result\":1";
  }

  @GetMapping("/event/comment_event/{event_id}/{user_id}/{content}")
  public String comment_event(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id, @PathVariable("content") String content){
    // 1 correct, -1 error
    if (Evento.comentar(Integer.parseInt(event_id), Integer.parseInt(user_id), content)) {
      return "\"result\":1";
    }
    return "\"result\"-1";
  }

  @GetMapping(value = "/event/eliminate/{event_id}")
  public String create_event(@PathVariable("event_id") String event_id){
    // 1 correct, -1 error
    if (!Evento.eliminar(Integer.parseInt(event_id))) {
      return "\"result\":-1";
    }
    return "\"result\":1";
  }

  @PostMapping("/event/valorar_event")
  public String valorar_usuario(@RequestBody Map<String, String> body){
    // 1 correct, -1 error
    if (!Evento.valorar(Integer.parseInt(body.get("destinatario")),Float.parseFloat(body.get("valor")), Integer.parseInt(body.get("autor")))) {
      return "\"result\"-1";
    }
    return "\"result\":1";
  }

}
