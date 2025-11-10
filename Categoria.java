public class Categoria {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private String descripcion;
    
    public Categoria(String nombre, String descripcion) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}