package vista;

import modelo.Contenido;
import controlador.ControladorBusqueda;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VistaBusquedaFiltrado {
    private JFrame frame;
    private JTextField txtBusqueda;
    private JComboBox<String> cbFiltroTipo;
    private JComboBox<String> cbFiltroCategoria;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;
    private JButton btnBuscar;
    private ControladorBusqueda controlador;
    
    public VistaBusquedaFiltrado(ControladorBusqueda controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        frame = new JFrame("Búsqueda y Filtrado de Contenidos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        
        // Panel superior con controles de búsqueda
        JPanel panelSuperior = new JPanel(new GridLayout(4, 1, 5, 5));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Búsqueda por término
        JPanel panelBusqueda = new JPanel(new BorderLayout(5, 5));
        panelBusqueda.add(new JLabel("Buscar:"), BorderLayout.WEST);
        txtBusqueda = new JTextField();
        panelBusqueda.add(txtBusqueda, BorderLayout.CENTER);
        panelSuperior.add(panelBusqueda);
        
        // Filtro por tipo
        JPanel panelTipo = new JPanel(new BorderLayout(5, 5));
        panelTipo.add(new JLabel("Filtrar por Tipo:"), BorderLayout.WEST);
        cbFiltroTipo = new JComboBox<>(new String[]{"Todos", "Articulo", "Video", "Imagen"});
        panelTipo.add(cbFiltroTipo, BorderLayout.CENTER);
        panelSuperior.add(panelTipo);
        
        // Filtro por categoría
        JPanel panelCategoria = new JPanel(new BorderLayout(5, 5));
        panelCategoria.add(new JLabel("Filtrar por Categoría:"), BorderLayout.WEST);
        cbFiltroCategoria = new JComboBox<>(new String[]{"Todas", "Ciencias", "Tecnología", "Arte", "Deportes"});
        panelCategoria.add(cbFiltroCategoria, BorderLayout.CENTER);
        panelSuperior.add(panelCategoria);
        
        // Botón de búsqueda
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> realizarBusqueda());
        panelBoton.add(btnBuscar);
        panelSuperior.add(panelBoton);
        
        frame.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con tabla de resultados
        String[] columnas = {"ID", "Título", "Tipo", "Autor", "Estado", "Fecha"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaResultados = new JTable(modeloTabla);
        tablaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTabla = new JScrollPane(tablaResultados);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Resultados"));
        
        frame.add(scrollTabla, BorderLayout.CENTER);
        
        // Panel inferior con información
        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Haga doble clic en un contenido para ver detalles"));
        frame.add(panelInferior, BorderLayout.SOUTH);
        
        // Agregar listener para doble clic
        tablaResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarDetalles();
                }
            }
        });
        
        // Cargar todos los contenidos al inicio
        realizarBusqueda();
    }
    
    private void realizarBusqueda() {
        String termino = txtBusqueda.getText();
        String tipo = (String) cbFiltroTipo.getSelectedItem();
        String categoria = (String) cbFiltroCategoria.getSelectedItem();
        
        ArrayList<Contenido> resultados = controlador.aplicarFiltros(termino, tipo, categoria);
    }
    
    public void mostrarResultados(ArrayList<Contenido> resultados) {
        limpiarResultados();
        
        for (Contenido contenido : resultados) {
            Object[] fila = {
                contenido.getId(),
                contenido.getTitulo(),
                contenido.getClass().getSimpleName(),
                contenido.getAutor(),
                contenido.getEstado(),
                contenido.getFechaCreacion()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    public void limpiarResultados() {
        modeloTabla.setRowCount(0);
    }
    
    private void mostrarDetalles() {
        int filaSeleccionada = tablaResultados.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String titulo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            String tipo = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
            String autor = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
            String estado = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
            String fecha = modeloTabla.getValueAt(filaSeleccionada, 5).toString();
            
            String detalles = "Título: " + titulo + "\n" +
                            "Tipo: " + tipo + "\n" +
                            "Autor: " + autor + "\n" +
                            "Estado: " + estado + "\n" +
                            "Fecha: " + fecha;
            
            JOptionPane.showMessageDialog(frame, detalles, "Detalles del Contenido", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public String obtenerTerminoBusqueda() {
        return txtBusqueda.getText();
    }
    
    public String obtenerFiltroTipo() {
        return (String) cbFiltroTipo.getSelectedItem();
    }
    
    public String obtenerFiltroCategoria() {
        return (String) cbFiltroCategoria.getSelectedItem();
    }
    
    public void mostrar() {
        frame.setVisible(true);
    }
}