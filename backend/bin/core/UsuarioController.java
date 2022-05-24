package core;

import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class UsuarioController {

  @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String login(@RequestBody Map<String, String> body){
    String pwd = body.get("password");
    String email = body.get("email");
    // >0 id user, 0 db error, -1 password error, -2 mail error
    return Usuario.iniciarSesion(email, pwd);
  }

 //Ejemplo de internet para ver los diferentes tipos de peticiones y como hacer la respuesta
  @GetMapping("/user/{id}")
  public String search(@PathVariable String id){
    return id;
  }

  @PostMapping(value = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String register_user(@RequestBody Map<String, String> body){
    String name = body.get("name");
    String pwd = body.get("password");
    String foto = body.get("photo");
    String email = body.get("email");
    String birth_date = body.get("date_birth");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate nacimiento = LocalDate.parse(birth_date, formatter);
    // >0 id user, 0 db error, -1 mail exists
    return Usuario.registrar(name,pwd, nacimiento,foto,email);
  }

  @PostMapping(value= "/user/modify_profile", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.ALL_VALUE)
  public String modify_profile(@RequestBody Map<String, String> body){
      int id = Integer.parseInt(body.get("id"));
      String name = body.get("name");
      String pwd = body.get("password");
      String foto = body.get("photo");
      String email = body.get("email");
      String birth_date = body.get("date_birth");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate nacimiento = LocalDate.parse(birth_date, formatter);
      // >0 ?, 0 db error
      return Usuario.modificarPerfil(id,name, pwd,birth_date, foto, email);
  }

  @PostMapping("/user/get_password")
  public String get_password(@RequestBody Map<String, String> body){
    String email = body.get("email");
    // string = password , 0 db error, -1 mail error
    return Usuario.recuperarPassword(email);
  }

  @GetMapping("/user/comment/{autor_id}/{destinatario_id}/{contenido}")
  public String comment(@PathVariable("autor_id") String autor_id, @PathVariable("destinatario_id") String destinatario_id, @PathVariable("contenido") String contenido){
    // 1 comented, -1 error
    if (Usuario.comentar(Integer.parseInt(autor_id), Integer.parseInt(destinatario_id), contenido)) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @GetMapping("/user/eliminate/{user_id}")
  public String eliminate_user(@PathVariable("user_id") String user_id){
    // 1 correct, -1 error
    if (!Usuario.eliminar(user_id)) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @GetMapping("/user/follow/{follower_id}/{followed_id}")
  public String follow_user(@PathVariable("follower_id") String follower_id, @PathVariable("followed_id") String followed_id){
    // 1 correct, -1 error
    if (!Usuario.seguirUsuario(follower_id, followed_id)) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @GetMapping("/user/unfollow/{follower_id}/{unfollowed_id}")
  public String unfollow_user(@PathVariable("follower_id") String follower_id, @PathVariable("unfollowed_id") String followed_id){
    // 1 correct, -1 error
    if (!Usuario.dejarSeguirUsuario(follower_id, followed_id)) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

  @PostMapping("/user/valorar_usuario")
  public String valorar_usuario(@RequestBody Map<String, String> body){
    // 1 correct, -1 error
    if (!Usuario.valorarUsuario(Integer.parseInt(body.get("destinatario")),Float.parseFloat(body.get("valor")), Integer.parseInt(body.get("autor")))) {
      return "{\"result\":1}";
    }
    return "{\"result\":-1}";
  }

}