import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReportes {
    private GestorContenidos gestorContenidos;
    
    public GeneradorReportes(GestorContenidos gestorContenidos) {
        this.gestorContenidos = gestorContenidos;
    }
    
    public String generarReporteGeneral() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("========== REPORTE GENERAL DE CONTENIDOS ==========\n\n");
        
        // Contenidos por tipo
        reporte.append("--- CONTENIDOS POR TIPO ---\n");
        Map<String, Integer> porTipo = contarContenidosPorTipo();
        for (Map.Entry<String, Integer> entry : porTipo.entrySet()) {
            reporte.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        reporte.append("\n--- CONTENIDOS POR CATEGORÍA ---\n");
        Map<String, Integer> porCategoria = contarContenidosPorCategoria();
        for (Map.Entry<String, Integer> entry : porCategoria.entrySet()) {
            reporte.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        reporte.append("\n--- CONTENIDOS MÁS RECIENTES ---\n");
        ArrayList<Contenido> recientes = obtenerContenidosRecientes(5);
        for (Contenido contenido : recientes) {
            reporte.append("- ").append(contenido.getTitulo())
                   .append(" (").append(contenido.getClass().getSimpleName())
                   .append(") - ").append(contenido.getFechaCreacion()).append("\n");
        }
        
        return formatearReporte(reporte.toString());
    }
    
    private Map<String, Integer> contarContenidosPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        conteo.put("Articulo", 0);
        conteo.put("Video", 0);
        conteo.put("Imagen", 0);
        
        for (Contenido contenido : gestorContenidos.obtenerPublicados()) {
            String tipo = contenido.getClass().getSimpleName();
            conteo.put(tipo, conteo.get(tipo) + 1);
        }
        
        return conteo;
    }
    
    private Map<String, Integer> contarContenidosPorCategoria() {
        Map<String, Integer> conteo = new HashMap<>();
        
        for (Contenido contenido : gestorContenidos.obtenerPublicados()) {
            for (Categoria categoria : contenido.getCategorias()) {
                String nombreCat = categoria.getNombre();
                conteo.put(nombreCat, conteo.getOrDefault(nombreCat, 0) + 1);
            }
        }
        
        return conteo;
    }
    
    private ArrayList<Contenido> obtenerContenidosRecientes(int cantidad) {
        ArrayList<Contenido> todos = gestorContenidos.obtenerPublicados();
        ArrayList<Contenido> recientes = new ArrayList<>();
        
        // Ordenar por fecha (más recientes primero)
        todos.sort((c1, c2) -> c2.getFechaCreacion().compareTo(c1.getFechaCreacion()));
        
        // Tomar solo la cantidad solicitada
        for (int i = 0; i < Math.min(cantidad, todos.size()); i++) {
            recientes.add(todos.get(i));
        }
        
        return recientes;
    }
    
    private String formatearReporte(String reporte) {
        return reporte;
    }
}