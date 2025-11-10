public class Video extends Contenido {
    private String urlVideo;
    private int duracion;
    private String resolucion;
    
    public Video(String titulo, String descripcion, String autor, String urlVideo, int duracion, String resolucion) {
        super(titulo, descripcion, autor);
        this.urlVideo = urlVideo;
        this.duracion = duracion;
        this.resolucion = resolucion;
    }
    
    public String getUrlVideo() {
        return urlVideo;
    }
    
    public int getDuracion() {
        return duracion;
    }
    
    public String getResolucion() {
        return resolucion;
    }
    
    @Override
    public void publicar() {
        System.out.println("=== VIDEO PUBLICADO ===");
        mostrarDetalles();
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Fecha: " + fechaCreacion);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Resolución: " + resolucion);
        System.out.println("URL: " + urlVideo);
    }
}