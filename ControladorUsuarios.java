import modelo.Usuario;
import modelo.Administrador;
import modelo.Editor;
import java.util.ArrayList;

public class ControladorUsuarios {
    private ArrayList<Usuario> listaUsuarios;
    private Usuario usuarioActual;
    
    public ControladorUsuarios() {
        this.listaUsuarios = new ArrayList<>();
        this.usuarioActual = null;
        inicializarUsuarios();
    }
    
    private void inicializarUsuarios() {
        // Crear usuarios predeterminados
        listaUsuarios.add(new Administrador("Admin Principal", "admin", "admin123"));
        listaUsuarios.add(new Editor("Editor Principal", "editor", "editor123"));
        listaUsuarios.add(new Administrador("Juan Pérez", "juan", "juan123"));
        listaUsuarios.add(new Editor("María García", "maria", "maria123"));
    }
    
    public Usuario autenticar(String username, String password) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.autenticar(username, password)) {
                usuarioActual = usuario;
                return usuario;
            }
        }
        return null;
    }
    
    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }
    
    public Usuario obtenerUsuarioActual() {
        return usuarioActual;
    }
    
    public void cerrarSesion() {
        usuarioActual = null;
    }
}