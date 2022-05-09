package core;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EventoController {

  @GetMapping("/event/join_event/{event_id}/{user_id}")
  public String join_event(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id){
    if (Evento.anadirParticipante(Integer.parseInt(event_id), Integer.parseInt(user_id))) {
      return "User joined the event successfully";
    }
    return "Error joining user to event";
  }

  @GetMapping("/event/comment_event/{event_id}/{user_id}/{content}")
  public String comment_event(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id, @PathVariable("content") String content){
    if (Evento.comentar(Integer.parseInt(event_id), Integer.parseInt(user_id), content)) {
      return "Comment sent";
    }
    return "Error commenting this event";
  }
}
