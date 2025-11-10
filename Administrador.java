public class Administrador extends Usuario {
    
    public Administrador(String nombre, String username, String password) {
        super(nombre, username, password, "Administrador");
    }
    
    @Override
    public boolean puedePublicar() {
        return true;
    }
    
    @Override
    public boolean puedeEliminar() {
        return true;
    }
}