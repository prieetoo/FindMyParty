package core;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EventoController {

  @GetMapping("/event/join_event/{event_id}&{user_id}")
  public String join_event(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id){
    Evento event = new Evento(Integer.parseInt(event_id));
    if (event.anadirParticipante(Integer.parseInt(user_id))) {
      return "User joined the event successfully";
    }
    return "Error joining user to event";
  }
}
