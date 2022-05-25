import org.junit.Test;

import java.time.LocalDate;

import static core.Usuario.*;

public class UsuarioTest {

  @Test
  public void TestRecover_password(){
    String nombre = "juanjo";
    String password = "12345";
    LocalDate fechaNacimiento = LocalDate.now();
    String foto = "";
    String email = "juanjo@uab.cat";
    String user_id = registrar(nombre, password, fechaNacimiento, foto, email);
    String result = recuperarPassword(email);
    assert result.equals("{\"result\":\"12345\"}");
  }
}
