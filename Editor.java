public class Editor extends Usuario {
    
    public Editor(String nombre, String username, String password) {
        super(nombre, username, password, "Editor");
    }
    
    @Override
    public boolean puedePublicar() {
        return false;
    }
    
    @Override
    public boolean puedeEliminar() {
        return false;
    }
}