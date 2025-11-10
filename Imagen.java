public class Imagen extends Contenido {
    private String urlImagen;
    private int ancho;
    private int alto;
    private String formato;
    
    public Imagen(String titulo, String descripcion, String autor, String urlImagen, int ancho, int alto, String formato) {
        super(titulo, descripcion, autor);
        this.urlImagen = urlImagen;
        this.ancho = ancho;
        this.alto = alto;
        this.formato = formato;
    }
    
    public String getUrlImagen() {
        return urlImagen;
    }
    
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
    
    public int getAncho() {
        return ancho;
    }
    
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    
    public int getAlto() {
        return alto;
    }
    
    public void setAlto(int alto) {
        this.alto = alto;
    }
    
    public String getFormato() {
        return formato;
    }
    
    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    public String calcularResolucion() {
        return ancho + "x" + alto;
    }
    
    @Override
    public void publicar() {
        System.out.println("=== IMAGEN PUBLICADA ===");
        mostrarDetalles();
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Fecha: " + fechaCreacion);
        System.out.println("Resolución: " + calcularResolucion());
        System.out.println("Formato: " + formato);
        System.out.println("URL: " + urlImagen);
    }
}