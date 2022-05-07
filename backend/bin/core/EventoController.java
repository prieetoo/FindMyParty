package core;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EventoController {

  @PostMapping("/event/join_event")
  public String join_event(@RequestBody Map<String, String> body){
    int event_id = Integer.parseInt(body.get("event_id"));
    int user_id = Integer.parseInt(body.get("user_id"));
    Evento event = new Evento(event_id);
    if (event.anadirParticipante(user_id)) {
      return "User joined the event successfully";
    }
    return "Error joining user to event";
  }
}
