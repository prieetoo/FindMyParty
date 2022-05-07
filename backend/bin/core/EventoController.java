package core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EventoController {

  @GetMapping("/event/join_event/{event_id}&{user_id}")
  public String join_event(@RequestBody String event_id, String user_id){
    Evento event = new Evento(Integer.parseInt(event_id));
    if (event.anadirParticipante(Integer.parseInt(user_id))) {
      return "User joined the event successfully";
    }
    return "Error joining user to event";
  }
}
