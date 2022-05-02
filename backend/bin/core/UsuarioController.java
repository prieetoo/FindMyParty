package core;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {

  //creacion usuario despues de recibir request y los datos de este
  @PostMapping("/user/create")
  public Usuario index(@RequestBody Map<String, String> body){
    String name = body.get("name");
    String pwd = body.get("password");
    String foto = body.get("photo");
    String email = body.get("email");
    return new Usuario(name,pwd, LocalDate.now(),foto,email);
  }
/* Ejemplo de internet para ver los diferentes tipos de peticiones y como hacer la respuesta
  @PostMapping("/blog/search")
  public List<Blog> search(@RequestBody Map<String, String> body){
    String searchTerm = body.get("text");
    return blogMockedData.searchBlogs(searchTerm);
  }

  @PostMapping("/blog")
  public Blog create(@RequestBody Map<String, String> body){
    int id = Integer.parseInt(body.get("id"));
    String title = body.get("title");
    String content = body.get("content");
    return blogMockedData.createBlog(id, title, content);
  }

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