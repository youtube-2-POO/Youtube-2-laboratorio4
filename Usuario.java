public abstract class Usuario {
    private static int contadorId = 1;
    protected int id;
    protected String nombre;
    protected String username;
    protected String password;
    protected String rol;
    
    public Usuario(String nombre, String username, String password, String rol) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRol() {
        return rol;
    }
    
    public boolean autenticar(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    public abstract boolean puedePublicar();
    public abstract boolean puedeEliminar();
}