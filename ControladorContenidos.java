import modelo.*;
import vista.VistaGestionContenidos;
import java.util.HashMap;

public class ControladorContenidos {
    private VistaGestionContenidos vistaGestion;
    private GestorContenidos gestorContenidos;
    private Usuario usuarioActual;
    
    public ControladorContenidos(GestorContenidos gestorContenidos, Usuario usuarioActual) {
        this.gestorContenidos = gestorContenidos;
        this.usuarioActual = usuarioActual;
    }
    
    public void setVista(VistaGestionContenidos vista) {
        this.vistaGestion = vista;
    }
    
    public boolean crearContenido(String tipo, HashMap<String, Object> datos) {
        try {
            Contenido nuevoContenido = null;
            
            String titulo = (String) datos.get("titulo");
            String descripcion = (String) datos.get("descripcion");
            String autor = usuarioActual.getNombre();
            
            switch (tipo.toLowerCase()) {
                case "articulo":
                    String cuerpo = (String) datos.get("cuerpo");
                    nuevoContenido = new Articulo(titulo, descripcion, autor, cuerpo);
                    break;
                    
                case "video":
                    String urlVideo = (String) datos.get("urlVideo");
                    int duracion = (int) datos.get("duracion");
                    String resolucion = (String) datos.get("resolucion");
                    nuevoContenido = new Video(titulo, descripcion, autor, urlVideo, duracion, resolucion);
                    break;
                    
                case "imagen":
                    String urlImagen = (String) datos.get("urlImagen");
                    int ancho = (int) datos.get("ancho");
                    int alto = (int) datos.get("alto");
                    String formato = (String) datos.get("formato");
                    nuevoContenido = new Imagen(titulo, descripcion, autor, urlImagen, ancho, alto, formato);
                    break;
            }
            
            if (nuevoContenido != null) {
                gestorContenidos.agregarContenido(nuevoContenido);
                notificarVista("Contenido creado exitosamente");
                return true;
            }
            
        } catch (Exception e) {
            notificarVista("Error al crear contenido: " + e.getMessage());
        }
        return false;
    }
    
    public boolean editarContenido(int id, HashMap<String, Object> datos) {
        Contenido contenido = gestorContenidos.buscarPorId(id);
        
        if (contenido == null) {
            notificarVista("Contenido no encontrado");
            return false;
        }
        
        try {
            contenido.setTitulo((String) datos.get("titulo"));
            contenido.setDescripcion((String) datos.get("descripcion"));
            notificarVista("Contenido editado exitosamente");
            return true;
        } catch (Exception e) {
            notificarVista("Error al editar contenido: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarContenido(int id) {
        if (!validarPermisos("eliminar")) {
            notificarVista("No tiene permisos para eliminar contenidos");
            return false;
        }
        
        boolean eliminado = gestorContenidos.eliminarContenido(id);
        
        if (eliminado) {
            notificarVista("Contenido eliminado exitosamente");
        } else {
            notificarVista("No se pudo eliminar el contenido");
        }
        
        return eliminado;
    }
    
    public boolean publicarContenido(int id) {
        if (!validarPermisos("publicar")) {
            notificarVista("No tiene permisos para publicar contenidos");
            return false;
        }
        
        Contenido contenido = gestorContenidos.buscarPorId(id);
        
        if (contenido == null) {
            notificarVista("Contenido no encontrado");
            return false;
        }
        
        contenido.setEstado("publicado");
        contenido.publicar();
        notificarVista("Contenido publicado exitosamente");
        return true;
    }
    
    private boolean validarPermisos(String accion) {
        switch (accion.toLowerCase()) {
            case "publicar":
                return usuarioActual.puedePublicar();
            case "eliminar":
                return usuarioActual.puedeEliminar();
            default:
                return true;
        }
    }
    
    private void notificarVista(String mensaje) {
        if (vistaGestion != null) {
            vistaGestion.mostrarMensaje(mensaje);
        } else {
            System.out.println(mensaje);
        }
    }
    
    public GestorContenidos getGestorContenidos() {
        return gestorContenidos;
    }
}