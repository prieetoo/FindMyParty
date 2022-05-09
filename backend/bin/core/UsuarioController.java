package core;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {

  @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.ALL_VALUE)
  public String login(@RequestBody Map<String, String> body){
    String pwd = body.get("password");
    String email = body.get("email");
    return Usuario.iniciarSesion(email, pwd);
  }

 //Ejemplo de internet para ver los diferentes tipos de peticiones y como hacer la respuesta
  @GetMapping("/user/{id}")
  public String search(@PathVariable String id){
    return id;
  }

  @PostMapping(value = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.ALL_VALUE)
  public String register_user(@RequestBody Map<String, String> body){
    String name = body.get("name");
    String pwd = body.get("password");
    String foto = body.get("photo");
    String email = body.get("email");
    String birth_date = body.get("date_birth");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate nacimiento = LocalDate.parse(birth_date, formatter);
    return Usuario.registrar(name,pwd, nacimiento,foto,email);
  }

  @PostMapping("/user/get_password")
  public String get_password(@RequestBody Map<String, String> body){
    String email = body.get("email");
    return Usuario.recuperarPassword(email);
  }

  @GetMapping("/user/comment/{autor_id}/{destinatario_id}/{contenido}")
  public String comment(@PathVariable("autor_id") String autor_id, @PathVariable("destinatario_id") String destinatario_id, @PathVariable("contenido") String contenido){
    if (Usuario.comentar(Integer.parseInt(autor_id), Integer.parseInt(destinatario_id), contenido)) {
      return "Comment sent";
    }
    return "Error commenting this user";
  }

  @GetMapping("/user/eliminate/{user_id}")
  public String eliminate_user(@PathVariable("user_id") String user_id){
    if (!Usuario.eliminar(user_id)) {
      return "Error while eliminating User";
    }
    return "User eliminated successfully";
  }

  @GetMapping("/user/follow/{follower_id}/{followed_id}")
  public String follow_user(@PathVariable("follower_id") String follower_id, @PathVariable("followed_id") String followed_id){
    if (!Usuario.seguirUsuario(follower_id, followed_id)) {
      return "Error while following the User";
    }
    return "User followed successfully";
  }

  @GetMapping("/user/unfollow/{follower_id}/{unfollowed_id}")
  public String unfollow_user(@PathVariable("follower_id") String follower_id, @PathVariable("unfollowed_id") String followed_id){
    if (!Usuario.dejarSeguirUsuario(follower_id, followed_id)) {
      return "Error while unfollowing the User";
    }
    return "User unfollowed successfully";
  }

}