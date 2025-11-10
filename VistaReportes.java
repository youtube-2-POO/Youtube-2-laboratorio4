package vista;

import modelo.GeneradorReportes;
import javax.swing.*;
import java.awt.*;

public class VistaReportes {
    private JFrame frame;
    private JTextArea txtReporte;
    private JButton btnGenerarReporte;
    private GeneradorReportes generadorReportes;
    
    public VistaReportes(GeneradorReportes generadorReportes) {
        this.generadorReportes = generadorReportes;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        frame = new JFrame("Reportes y Estadísticas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        
        // Panel superior con título
        JPanel panelSuperior = new JPanel();
        JLabel lblTitulo = new JLabel("Reportes del Sistema");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(lblTitulo);
        frame.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con área de texto para el reporte
        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        txtReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtReporte.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollReporte = new JScrollPane(txtReporte);
        scrollReporte.setBorder(BorderFactory.createTitledBorder("Reporte Generado"));
        frame.add(scrollReporte, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new FlowLayout());
        
        btnGenerarReporte = new JButton("Generar Reporte");
        btnGenerarReporte.addActionListener(e -> generarReporte());
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarReporte());
        
        JButton btnExportar = new JButton("Exportar");
        btnExportar.addActionListener(e -> exportarReporte());
        
        panelInferior.add(btnGenerarReporte);
        panelInferior.add(btnLimpiar);
        panelInferior.add(btnExportar);
        
        frame.add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void generarReporte() {
        String reporte = generadorReportes.generarReporteGeneral();
        mostrarReporte(reporte);
    }
    
    public void mostrarReporte(String reporte) {
        txtReporte.setText(reporte);
        txtReporte.setCaretPosition(0); // Scroll al inicio
    }
    
    public void limpiarReporte() {
        txtReporte.setText("");
    }
    
    private void exportarReporte() {
        if (txtReporte.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "No hay reporte para exportar. Genere un reporte primero.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte");
        fileChooser.setSelectedFile(new java.io.File("reporte.txt"));
        
        int resultado = fileChooser.showSaveDialog(frame);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File archivo = fileChooser.getSelectedFile();
                java.io.FileWriter writer = new java.io.FileWriter(archivo);
                writer.write(txtReporte.getText());
                writer.close();
                
                JOptionPane.showMessageDialog(frame, 
                    "Reporte exportado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, 
                    "Error al exportar: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void mostrar() {
        frame.setVisible(true);
    }
}