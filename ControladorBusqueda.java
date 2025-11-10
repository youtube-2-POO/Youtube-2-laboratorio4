package controlador;

import modelo.*;
import vista.VistaBusquedaFiltrado;
import java.util.ArrayList;

public class ControladorBusqueda {
    private VistaBusquedaFiltrado vistaBusqueda;
    private GestorContenidos gestorContenidos;
    
    public ControladorBusqueda(GestorContenidos gestorContenidos) {
        this.gestorContenidos = gestorContenidos;
    }
    
    public void setVista(VistaBusquedaFiltrado vista) {
        this.vistaBusqueda = vista;
    }
    
    public ArrayList<Contenido> buscarContenidos(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            return gestorContenidos.obtenerPublicados();
        }
        return gestorContenidos.buscarPorTitulo(termino);
    }
    
    public ArrayList<Contenido> filtrarPorTipo(String tipo) {
        if (tipo == null || tipo.equals("Todos")) {
            return gestorContenidos.obtenerPublicados();
        }
        return gestorContenidos.filtrarPorTipo(tipo);
    }
    
    public ArrayList<Contenido> filtrarPorCategoria(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.equals("Todas")) {
            return gestorContenidos.obtenerPublicados();
        }
        
        ArrayList<Contenido> resultados = new ArrayList<>();
        for (Contenido contenido : gestorContenidos.obtenerPublicados()) {
            for (Categoria cat : contenido.getCategorias()) {
                if (cat.getNombre().equals(nombreCategoria)) {
                    resultados.add(contenido);
                    break;
                }
            }
        }
        return resultados;
    }
    
    public ArrayList<Contenido> aplicarFiltros(String termino, String tipo, String categoria) {
        ArrayList<Contenido> resultados = gestorContenidos.obtenerPublicados();
        
        // Filtrar por término de búsqueda
        if (termino != null && !termino.trim().isEmpty()) {
            ArrayList<Contenido> temp = new ArrayList<>();
            for (Contenido contenido : resultados) {
                if (contenido.getTitulo().toLowerCase().contains(termino.toLowerCase())) {
                    temp.add(contenido);
                }
            }
            resultados = temp;
        }
        
        // Filtrar por tipo
        if (tipo != null && !tipo.equals("Todos")) {
            ArrayList<Contenido> temp = new ArrayList<>();
            for (Contenido contenido : resultados) {
                if (contenido.getClass().getSimpleName().equals(tipo)) {
                    temp.add(contenido);
                }
            }
            resultados = temp;
        }
        
        // Filtrar por categoría
        if (categoria != null && !categoria.equals("Todas")) {
            ArrayList<Contenido> temp = new ArrayList<>();
            for (Contenido contenido : resultados) {
                for (Categoria cat : contenido.getCategorias()) {
                    if (cat.getNombre().equals(categoria)) {
                        temp.add(contenido);
                        break;
                    }
                }
            }
            resultados = temp;
        }
        
        mostrarResultados(resultados);
        return resultados;
    }
    
    private void mostrarResultados(ArrayList<Contenido> resultados) {
        if (vistaBusqueda != null) {
            vistaBusqueda.mostrarResultados(resultados);
        }
    }
}