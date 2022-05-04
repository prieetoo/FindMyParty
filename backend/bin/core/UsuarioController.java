package core;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {

  //creacion usuario despues de recibir request y los datos de este
  @PostMapping("/user/create")
  public Usuario create_user(@RequestBody Map<String, String> body){
    String name = body.get("name");
    String pwd = body.get("password");
    String foto = body.get("photo");
    String email = body.get("email");
    String birth_date = body.get("date_birth");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate nacimiento = LocalDate.parse(birth_date, formatter);
    return new Usuario(name,pwd, nacimiento,foto,email);
  }
 //Ejemplo de internet para ver los diferentes tipos de peticiones y como hacer la respuesta
  @GetMapping("/user/{id}")
  public String search(@PathVariable String id){
    return id;
  }

  @PostMapping("/user/register")
  public Usuario register_user(@RequestBody Map<String, String> body){
    String name = body.get("name");
    String pwd = body.get("password");
    String foto = body.get("photo");
    String email = body.get("email");
    String birth_date = body.get("date_birth");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate nacimiento = LocalDate.parse(birth_date, formatter);
    return new Usuario(name,pwd, nacimiento,foto,email);
  }
/*
  @PutMapping("/blog/{id}")
  public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
    int blogId = Integer.parseInt(id);
    String title = body.get("title");
    String content = body.get("content");
    return blogMockedData.updateBlog(blogId, title, content);
  }

  @DeleteMapping("blog/{id}")
  public boolean delete(@PathVariable String id){
    int blogId = Integer.parseInt(id);
    return blogMockedData.delete(blogId);
  }
*/
}