public class Etiqueta {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    
    public Etiqueta(String nombre) {
        this.id = contadorId++;
        this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}