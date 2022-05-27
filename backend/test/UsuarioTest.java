import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static core.Usuario.*;

public class UsuarioTest {
  public static String email1;
  public static String nombre1;
  public static String user_id1;
  public static String email2;
  public static String nombre2;
  public static String user_id2;

  @BeforeAll
  public static void init(){
    nombre1 = "juanjo";
    String password1 = "12345";
    LocalDate fechaNacimiento1 = LocalDate.now();
    String foto1 = "";
    email1 = "juanjo@uab.cat";
    String aux1 = registrar(nombre1, password1, fechaNacimiento1, foto1, email1);
    user_id1 = aux1.substring(11, 14);
    nombre2 = "Diego";
    String password2 = "123456";
    LocalDate fechaNacimiento2 = LocalDate.now();
    String foto2 = "";
    email2 = "diego@uab.cat";
    String aux2 = registrar(nombre2, password2, fechaNacimiento2, foto2, email2);
    user_id2 = aux2.substring(11, 14);
  }

  @Test
  public void TestRecover_password1(){ //correo correcto
    String result = recuperarPassword(email1);
    System.out.println(result);
    assert result.equals("{\"result\":\"12345\"}");
  }

  @Test
  public void TestRecover_password2(){ //correo incorrecto
    String email_inc = "juanjo@e-campus.uab.cat";
    String result = recuperarPassword(email_inc);
    System.out.println(result);
    assert result.equals("{\"result\":\"-1\"}");
  }

  @AfterAll
  public static void finish(){
    eliminar(Integer.parseInt(user_id1));
    eliminar(Integer.parseInt(user_id2));
  }
}
