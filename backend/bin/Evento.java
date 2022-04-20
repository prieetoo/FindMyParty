import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONObject;


public class Evento {

  private String nombre;
  private String ubicacion;
  private LocalDate fecha;
  private Usuario host;
  private float valoracion;
  private ArrayList<Valoracion> valoraciones;
  private ArrayList<String> etiquetas;

  public Evento(String nombre, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.host = host;
    this.etiquetas = etiquetas;
    this.valoracion = 0;
    this.valoraciones = new ArrayList<Valoracion>();
  }

  public boolean modificar(String nombre, String ubicacion, LocalDate fecha, Usuario host, ArrayList<String> etiquetas);
  public boolean comentar(Comentario comentario);
  public boolean valorar(Valoracion valoracion);
  public boolean añadirParticipante(Usuario participante);
  public boolean eliminarParticipante(Usuario participante);
  public boolean eiminar();
  public boolean añadirPublicacion(Publicacion publicacion);
  public JSONObject toJson();
}