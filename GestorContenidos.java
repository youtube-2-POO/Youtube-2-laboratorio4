import java.util.ArrayList;

public class GestorContenidos implements IFiltrable {
    private ArrayList<Contenido> listaContenidos;
    private int contadorId;
    
    public GestorContenidos() {
        this.listaContenidos = new ArrayList<>();
        this.contadorId = 0;
    }
    
    public void agregarContenido(Contenido contenido) {
        listaContenidos.add(contenido);
        contadorId++;
    }
    
    public boolean eliminarContenido(int id) {
        Contenido contenido = buscarPorId(id);
        if (contenido != null) {
            listaContenidos.remove(contenido);
            return true;
        }
        return false;
    }
    
    public Contenido buscarPorId(int id) {
        for (Contenido contenido : listaContenidos) {
            if (contenido.getId() == id) {
                return contenido;
            }
        }
        return null;
    }
    
    public ArrayList<Contenido> buscarPorTitulo(String titulo) {
        ArrayList<Contenido> resultados = new ArrayList<>();
        for (Contenido contenido : listaContenidos) {
            if (contenido.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    @Override
    public ArrayList<Contenido> filtrarPorTipo(String tipo) {
        ArrayList<Contenido> resultados = new ArrayList<>();
        for (Contenido contenido : listaContenidos) {
            if (contenido.getClass().getSimpleName().equalsIgnoreCase(tipo)) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    @Override
    public ArrayList<Contenido> filtrarPorCategoria(Categoria categoria) {
        ArrayList<Contenido> resultados = new ArrayList<>();
        for (Contenido contenido : listaContenidos) {
            for (Categoria cat : contenido.getCategorias()) {
                if (cat.getId() == categoria.getId()) {
                    resultados.add(contenido);
                    break;
                }
            }
        }
        return resultados;
    }
    
    public ArrayList<Contenido> obtenerTodos() {
        return new ArrayList<>(listaContenidos);
    }
    
    public ArrayList<Contenido> obtenerPublicados() {
        ArrayList<Contenido> publicados = new ArrayList<>();
        for (Contenido contenido : listaContenidos) {
            if (contenido.getEstado().equals("publicado")) {
                publicados.add(contenido);
            }
        }
        return publicados;
    }
    
    public int contarPorTipo(String tipo) {
        return filtrarPorTipo(tipo).size();
    }
}