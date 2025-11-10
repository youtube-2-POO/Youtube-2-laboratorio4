import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Contenido implements IPublicable {
    private static int contadorId = 1;
    protected int id;
    protected String titulo;
    protected String descripcion;
    protected String autor;
    protected LocalDate fechaCreacion;
    protected String estado;
    protected ArrayList<Categoria> categorias;
    protected ArrayList<Etiqueta> etiquetas;
    
    public Contenido(String titulo, String descripcion, String autor) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.fechaCreacion = LocalDate.now();
        this.estado = "borrador";
        this.categorias = new ArrayList<>();
        this.etiquetas = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void agregarCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }
    
    public void agregarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.add(etiqueta);
    }
    
    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
    
    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }
    
    @Override
    public abstract void publicar();
    
    @Override
    public abstract void mostrarDetalles();
}