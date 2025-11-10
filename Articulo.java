public class Articulo extends Contenido {
    private String cuerpo;
    private int numeroPalabras;
    
    public Articulo(String titulo, String descripcion, String autor, String cuerpo) {
        super(titulo, descripcion, autor);
        this.cuerpo = cuerpo;
        this.numeroPalabras = calcularNumeroPalabras();
    }
    
    public String getCuerpo() {
        return cuerpo;
    }
    
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
        this.numeroPalabras = calcularNumeroPalabras();
    }
    
    public int getNumeroPalabras() {
        return numeroPalabras;
    }
    
    private int calcularNumeroPalabras() {
        if (cuerpo == null || cuerpo.trim().isEmpty()) {
            return 0;
        }
        String[] palabras = cuerpo.trim().split("\\s+");
        return palabras.length;
    }
    
    @Override
    public void publicar() {
        System.out.println("=== ARTÍCULO PUBLICADO ===");
        mostrarDetalles();
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Fecha: " + fechaCreacion);
        System.out.println("Número de palabras: " + numeroPalabras);
        System.out.println("Descripción: " + descripcion);
    }
}