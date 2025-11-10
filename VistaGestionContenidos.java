package vista;

import modelo.Usuario;
import modelo.Contenido;
import controlador.ControladorContenidos;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VistaGestionContenidos {
    private JFrame frame;
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JComboBox<String> cbTipoContenido;
    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnPublicar;
    private Usuario usuarioActual;
    private ControladorContenidos controlador;
    
    // Campos específicos según tipo
    private JPanel panelEspecifico;
    private JTextArea txtCuerpo; // Para Artículo
    private JTextField txtUrl; // Para Video e Imagen
    private JTextField txtDuracion; // Para Video
    private JTextField txtResolucion; // Para Video
    private JTextField txtAncho; // Para Imagen
    private JTextField txtAlto; // Para Imagen
    private JTextField txtFormato; // Para Imagen
    
    public VistaGestionContenidos(Usuario usuarioActual, ControladorContenidos controlador) {
        this.usuarioActual = usuarioActual;
        this.controlador = controlador;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        frame = new JFrame("Gestión de Contenidos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        
        // Panel superior con información del usuario
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Usuario: " + usuarioActual.getNombre() + " - " + usuarioActual.getRol()));
        frame.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con formulario
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Tipo de contenido
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTipo.add(new JLabel("Tipo de Contenido:"));
        cbTipoContenido = new JComboBox<>(new String[]{"Articulo", "Video", "Imagen"});
        cbTipoContenido.addActionListener(e -> actualizarCamposEspecificos());
        panelTipo.add(cbTipoContenido);
        panelCentral.add(panelTipo);
        
        // Título
        JPanel panelTitulo = new JPanel(new BorderLayout(5, 5));
        panelTitulo.add(new JLabel("Título:"), BorderLayout.NORTH);
        txtTitulo = new JTextField();
        panelTitulo.add(txtTitulo, BorderLayout.CENTER);
        panelCentral.add(panelTitulo);
        
        // Descripción
        JPanel panelDescripcion = new JPanel(new BorderLayout(5, 5));
        panelDescripcion.add(new JLabel("Descripción:"), BorderLayout.NORTH);
        txtDescripcion = new JTextArea(3, 30);
        txtDescripcion.setLineWrap(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        panelDescripcion.add(scrollDesc, BorderLayout.CENTER);
        panelCentral.add(panelDescripcion);
        
        // Panel para campos específicos
        panelEspecifico = new JPanel();
        panelEspecifico.setLayout(new BoxLayout(panelEspecifico, BoxLayout.Y_AXIS));
        panelEspecifico.setBorder(BorderFactory.createTitledBorder("Datos Específicos"));
        panelCentral.add(panelEspecifico);
        
        actualizarCamposEspecificos();
        
        JScrollPane scrollCentral = new JScrollPane(panelCentral);
        frame.add(scrollCentral, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnCrear = new JButton("Crear Contenido");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnPublicar = new JButton("Publicar");
        
        btnCrear.addActionListener(e -> crearContenido());
        btnEliminar.addActionListener(e -> mostrarMensaje("Funcionalidad de eliminar en desarrollo"));
        btnPublicar.addActionListener(e -> mostrarMensaje("Funcionalidad de publicar en desarrollo"));
        
        panelBotones.add(btnCrear);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnPublicar);
        
        habilitarBotones();
        
        frame.add(panelBotones, BorderLayout.SOUTH);
    }
    
    private void actualizarCamposEspecificos() {
        panelEspecifico.removeAll();
        String tipoSeleccionado = (String) cbTipoContenido.getSelectedItem();
        
        if (tipoSeleccionado.equals("Articulo")) {
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.add(new JLabel("Cuerpo del Artículo:"), BorderLayout.NORTH);
            txtCuerpo = new JTextArea(5, 30);
            txtCuerpo.setLineWrap(true);
            JScrollPane scroll = new JScrollPane(txtCuerpo);
            panel.add(scroll, BorderLayout.CENTER);
            panelEspecifico.add(panel);
            
        } else if (tipoSeleccionado.equals("Video")) {
            JPanel panelUrl = new JPanel(new BorderLayout(5, 5));
            panelUrl.add(new JLabel("URL del Video:"), BorderLayout.WEST);
            txtUrl = new JTextField(30);
            panelUrl.add(txtUrl, BorderLayout.CENTER);
            panelEspecifico.add(panelUrl);
            
            JPanel panelDuracion = new JPanel(new BorderLayout(5, 5));
            panelDuracion.add(new JLabel("Duración (minutos):"), BorderLayout.WEST);
            txtDuracion = new JTextField(10);
            panelDuracion.add(txtDuracion, BorderLayout.CENTER);
            panelEspecifico.add(panelDuracion);
            
            JPanel panelRes = new JPanel(new BorderLayout(5, 5));
            panelRes.add(new JLabel("Resolución:"), BorderLayout.WEST);
            txtResolucion = new JTextField(10);
            panelRes.add(txtResolucion, BorderLayout.CENTER);
            panelEspecifico.add(panelRes);
            
        } else if (tipoSeleccionado.equals("Imagen")) {
            JPanel panelUrl = new JPanel(new BorderLayout(5, 5));
            panelUrl.add(new JLabel("URL de la Imagen:"), BorderLayout.WEST);
            txtUrl = new JTextField(30);
            panelUrl.add(txtUrl, BorderLayout.CENTER);
            panelEspecifico.add(panelUrl);
            
            JPanel panelAncho = new JPanel(new BorderLayout(5, 5));
            panelAncho.add(new JLabel("Ancho (píxeles):"), BorderLayout.WEST);
            txtAncho = new JTextField(10);
            panelAncho.add(txtAncho, BorderLayout.CENTER);
            panelEspecifico.add(panelAncho);
            
            JPanel panelAlto = new JPanel(new BorderLayout(5, 5));
            panelAlto.add(new JLabel("Alto (píxeles):"), BorderLayout.WEST);
            txtAlto = new JTextField(10);
            panelAlto.add(txtAlto, BorderLayout.CENTER);
            panelEspecifico.add(panelAlto);
            
            JPanel panelFormato = new JPanel(new BorderLayout(5, 5));
            panelFormato.add(new JLabel("Formato:"), BorderLayout.WEST);
            txtFormato = new JTextField(10);
            panelFormato.add(txtFormato, BorderLayout.CENTER);
            panelEspecifico.add(panelFormato);
        }
        
        panelEspecifico.revalidate();
        panelEspecifico.repaint();
    }
    
    private void crearContenido() {
        try {
            HashMap<String, Object> datos = obtenerDatosFormulario();
            String tipo = (String) cbTipoContenido.getSelectedItem();
            
            if (controlador.crearContenido(tipo, datos)) {
                limpiarFormulario();
            }
        } catch (Exception e) {
            mostrarMensaje("Error al crear contenido: " + e.getMessage());
        }
    }
    
    public HashMap<String, Object> obtenerDatosFormulario() {
        HashMap<String, Object> datos = new HashMap<>();
        
        datos.put("titulo", txtTitulo.getText());
        datos.put("descripcion", txtDescripcion.getText());
        
        String tipoSeleccionado = (String) cbTipoContenido.getSelectedItem();
        
        if (tipoSeleccionado.equals("Articulo")) {
            datos.put("cuerpo", txtCuerpo.getText());
            
        } else if (tipoSeleccionado.equals("Video")) {
            datos.put("urlVideo", txtUrl.getText());
            datos.put("duracion", Integer.parseInt(txtDuracion.getText()));
            datos.put("resolucion", txtResolucion.getText());
            
        } else if (tipoSeleccionado.equals("Imagen")) {
            datos.put("urlImagen", txtUrl.getText());
            datos.put("ancho", Integer.parseInt(txtAncho.getText()));
            datos.put("alto", Integer.parseInt(txtAlto.getText()));
            datos.put("formato", txtFormato.getText());
        }
        
        return datos;
    }
    
    public void limpiarFormulario() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        
        if (txtCuerpo != null) txtCuerpo.setText("");
        if (txtUrl != null) txtUrl.setText("");
        if (txtDuracion != null) txtDuracion.setText("");
        if (txtResolucion != null) txtResolucion.setText("");
        if (txtAncho != null) txtAncho.setText("");
        if (txtAlto != null) txtAlto.setText("");
        if (txtFormato != null) txtFormato.setText("");
    }
    
    private void habilitarBotones() {
        btnEliminar.setEnabled(usuarioActual.puedeEliminar());
        btnPublicar.setEnabled(usuarioActual.puedePublicar());
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }
    
    public void mostrar() {
        frame.setVisible(true);
    }
    
    public void mostrarFormularioCrear() {
        limpiarFormulario();
        frame.setTitle("Crear Nuevo Contenido");
    }
    
    public void mostrarFormularioEditar(Contenido contenido) {
        frame.setTitle("Editar Contenido");
        txtTitulo.setText(contenido.getTitulo());
        txtDescripcion.setText(contenido.getDescripcion());
    }
}