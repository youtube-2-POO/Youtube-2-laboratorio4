package vista;

import modelo.Usuario;
import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {
    private JFrame frame;
    private JPanel panelMenu;
    private Usuario usuarioActual;
    private ControladorPrincipal controlador;
    
    public VistaPrincipal(Usuario usuarioActual, ControladorPrincipal controlador) {
        this.usuarioActual = usuarioActual;
        this.controlador = controlador;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        frame = new JFrame("Sistema de Gestión de Contenidos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(6, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        JLabel lblBienvenida = new JLabel("Bienvenido: " + usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton btnGestion = new JButton("Gestión de Contenidos");
        JButton btnBusqueda = new JButton("Búsqueda y Filtrado");
        JButton btnReportes = new JButton("Reportes y Estadísticas");
        JButton btnSalir = new JButton("Cerrar Sesión");
        
        btnGestion.addActionListener(e -> abrirGestionContenidos());
        btnBusqueda.addActionListener(e -> abrirBusqueda());
        btnReportes.addActionListener(e -> abrirReportes());
        btnSalir.addActionListener(e -> cerrarSesion());
        
        panelMenu.add(lblBienvenida);
        panelMenu.add(btnGestion);
        panelMenu.add(btnBusqueda);
        panelMenu.add(btnReportes);
        panelMenu.add(new JLabel()); // Espacio
        panelMenu.add(btnSalir);
        
        frame.add(panelMenu);
    }
    
    public void mostrarMenu() {
        // El menú ya está visible en el frame
    }
    
    private void abrirGestionContenidos() {
        controlador.abrirModuloGestion();
    }
    
    private void abrirBusqueda() {
        controlador.abrirModuloBusqueda();
    }
    
    private void abrirReportes() {
        controlador.abrirModuloReportes();
    }
    
    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(frame, 
            "¿Está seguro de cerrar sesión?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            controlador.cerrarSesion();
        }
    }
    
    public void mostrar() {
        frame.setVisible(true);
    }
    
    public void cerrarVentana() {
        frame.dispose();
    }
}