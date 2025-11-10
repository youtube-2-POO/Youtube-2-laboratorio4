package controlador;

import modelo.*;
import vista.VistaPrincipal;
import vista.VistaGestionContenidos;
import vista.VistaBusquedaFiltrado;
import vista.VistaReportes;

public class ControladorPrincipal {
    private VistaPrincipal vistaPrincipal;
    private GestorContenidos gestorContenidos;
    private Usuario usuarioActual;
    
    public ControladorPrincipal(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        this.gestorContenidos = new GestorContenidos();
        inicializarDatosPrueba();
    }
    
    private void inicializarDatosPrueba() {
        // Crear categorías de prueba
        Categoria ciencias = new Categoria("Ciencias", "Contenido científico");
        Categoria tecnologia = new Categoria("Tecnología", "Contenido tecnológico");
        Categoria arte = new Categoria("Arte", "Contenido artístico");
        
        // Crear contenidos de prueba
        Articulo art1 = new Articulo("Introducción a Java", "Aprende Java desde cero", 
                                     usuarioActual.getNombre(), "Este es el contenido del artículo sobre Java...");
        art1.agregarCategoria(tecnologia);
        art1.setEstado("publicado");
        gestorContenidos.agregarContenido(art1);
        
        Video vid1 = new Video("Tutorial POO", "Aprende POO con ejemplos", 
                              usuarioActual.getNombre(), "https://video.com/poo", 45, "1080p");
        vid1.agregarCategoria(tecnologia);
        vid1.setEstado("publicado");
        gestorContenidos.agregarContenido(vid1);
        
        Imagen img1 = new Imagen("Diagrama UML", "Diagrama de clases ejemplo", 
                                usuarioActual.getNombre(), "https://imagen.com/uml.png", 1920, 1080, "PNG");
        img1.agregarCategoria(tecnologia);
        img1.setEstado("publicado");
        gestorContenidos.agregarContenido(img1);
    }
    
    public void iniciar() {
        vistaPrincipal = new VistaPrincipal(usuarioActual, this);
        vistaPrincipal.mostrar();
    }
    
    public void abrirModuloGestion() {
        ControladorContenidos controladorContenidos = new ControladorContenidos(gestorContenidos, usuarioActual);
        VistaGestionContenidos vistaGestion = new VistaGestionContenidos(usuarioActual, controladorContenidos);
        controladorContenidos.setVista(vistaGestion);
        vistaGestion.mostrar();
    }
    
    public void abrirModuloBusqueda() {
        ControladorBusqueda controladorBusqueda = new ControladorBusqueda(gestorContenidos);
        VistaBusquedaFiltrado vistaBusqueda = new VistaBusquedaFiltrado(controladorBusqueda);
        controladorBusqueda.setVista(vistaBusqueda);
        vistaBusqueda.mostrar();
    }
    
    public void abrirModuloReportes() {
        GeneradorReportes generador = new GeneradorReportes(gestorContenidos);
        VistaReportes vistaReportes = new VistaReportes(generador);
        vistaReportes.mostrar();
    }
    
    public void cerrarSesion() {
        if (vistaPrincipal != null) {
            vistaPrincipal.cerrarVentana();
        }
        System.out.println("Sesión cerrada");
    }
    
    public GestorContenidos getGestorContenidos() {
        return gestorContenidos;
    }
}