package core;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@RestController
public class EventoController {

  @GetMapping("/event/join/{event_id}/{user_id}")
  public String join(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id){
    // 1 correct, -1 error
    if (Evento.anadirParticipante(Integer.parseInt(event_id), Integer.parseInt(user_id))) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @GetMapping("/event/cancel/{evento_id}/{participante_id}")
  public String cancel(@PathVariable("evento_id") String evento_id, @PathVariable("participante_id") String participante_id){

      if (Evento.eliminarParticipante(Integer.parseInt(evento_id),Integer.parseInt(participante_id))){
        return "{\"result\":1}";
      }
      return "{\"result\":-1}";
  }

  @PostMapping(value = "/event/create", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String create_event(@RequestBody Map<String, ArrayList<String>> body) {
    //Formato body:
    // "information": ["nombre", latitud, longitud, "yyyy-MM-dd HH:mm:ss", id_usuario, "ubicacion", "descripcion", coste],
    // "tickets": [etiqueta1, etiqueta2, ...]
    ArrayList<String> informacion = body.get("information");
    Punto p = new Punto(Float.parseFloat(informacion.get(1)), Float.parseFloat(informacion.get(2)));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime fecha = LocalDateTime.parse(informacion.get(3), formatter);
    int user_id = Integer.parseInt(informacion.get(4));
    String ubicacion = informacion.get(5);
    Usuario anfitrion = new Usuario(user_id);
    ArrayList<String> etiquetas = body.get("tickets");
    String descripcion = informacion.get(6);
    int coste = Integer.parseInt(informacion.get(7));
    // 1 correct, -1 error
    if (Evento.crear(informacion.get(0), p, ubicacion, fecha, anfitrion, etiquetas, descripcion, coste)) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @GetMapping("/event/comment_event/{event_id}/{user_id}/{content}")
  public String comment_event(@PathVariable("event_id") String event_id, @PathVariable("user_id") String user_id, @PathVariable("content") String content){
    // 1 correct, -1 error
    if (Evento.comentar(Integer.parseInt(event_id), Integer.parseInt(user_id), content)) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @GetMapping(value = "/event/eliminate/{event_id}")
  public String delete_event(@PathVariable("event_id") String event_id){
    // 1 correct, -1 error
    if (Evento.eliminar(Integer.parseInt(event_id))) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @PostMapping("/event/valorar_event")
  public String valorar_evento(@RequestBody Map<String, String> body){
    // 1 correct, -1 error
    if (Evento.valorar(Integer.parseInt(body.get("destinatario")),Float.parseFloat(body.get("valor")), Integer.parseInt(body.get("autor")))) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @GetMapping(value = "/event/get/{event_id}")
  public String get_event(@PathVariable("event_id") String event_id){
    // returns event in json format
    Evento e = new Evento(Integer.parseInt(event_id));
    return e.toJson().toString();

  }

  @PostMapping(value = "/event/get_events")
  public String get_events(@RequestBody Map<String, String> body){
    // returns events in json format
    ArrayList<String> array = new ArrayList();
    // format => "etiquetas": "etiqueta1,etiqueta2"
    if (body.containsKey("etiquetas")){
      array = new ArrayList<>(Arrays.asList(body.get("etiquetas").split(",")));
    }
    Float radio = 1f;
    // format => "radio": number
    if (body.containsKey("radio")){
      radio = Float.parseFloat(body.get("radio"));
    }
    // format => "pago": 1
    Boolean pago = false;
    if (body.get("pago").equals("true")) {
      pago = true;
    }

    int participantes = 5;
    // format => "participantes": number
    if (body.containsKey("participantes")){
      participantes = Integer.parseInt(body.get("participantes"));
    }

    int dia = -1;
    // format => "dia": number
    //0 = Monday, 1 = Tuesday, 2 = Wednesday, 3 = Thursday, 4 = Friday, 5 = Saturday, 6 = Sunday
    if (body.containsKey("dia")){
      dia = Integer.parseInt(body.get("dia"));
    }
    Busqueda busqueda = new Busqueda(new Punto(Float.parseFloat(body.get("latitud")),
            Float.parseFloat(body.get("longitud"))), radio, array, pago, participantes, dia);
    //return format => {"lista_eventos":[{"distancia":number,"id":number,"nombre":"Algo"}]}
    return busqueda.getJsonEventos().toString();
  }

}
