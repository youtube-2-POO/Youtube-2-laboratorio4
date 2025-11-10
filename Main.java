import modelo.Usuario;
import controlador.ControladorUsuarios;
import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;

public class Main {
    
    public static void main(String[] args) {
        // Configurar look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Iniciar aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            mostrarLoginYIniciar();
        });
    }
    
    private static void mostrarLoginYIniciar() {
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        
        // Crear ventana de login
        JFrame frameLogin = new JFrame("Inicio de Sesión");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(400, 250);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(new BorderLayout(10, 10));
        
        // Panel central con campos de login
        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        JLabel lblTitulo = new JLabel("Sistema de Gestión de Contenidos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();
        
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();
        
        panelCentral.add(lblUsuario);
        panelCentral.add(txtUsuario);
        panelCentral.add(lblPassword);
        panelCentral.add(txtPassword);
        
        // Panel inferior con botón
        JPanel panelInferior = new JPanel(new FlowLayout());
        JButton btnLogin = new JButton("Iniciar Sesión");
        panelInferior.add(btnLogin);
        
        // Panel superior con título
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(lblTitulo);
        
        frameLogin.add(panelSuperior, BorderLayout.NORTH);
        frameLogin.add(panelCentral, BorderLayout.CENTER);
        frameLogin.add(panelInferior, BorderLayout.SOUTH);
        
        // Acción del botón login
        btnLogin.addActionListener(e -> {
            String username = txtUsuario.getText();
            String password = new String(txtPassword.getPassword());
            
            Usuario usuario = controladorUsuarios.autenticar(username, password);
            
            if (usuario != null) {
                frameLogin.dispose();
                ControladorPrincipal controladorPrincipal = new ControladorPrincipal(usuario);
                controladorPrincipal.iniciar();
            } else {
                JOptionPane.showMessageDialog(frameLogin, 
                    "Usuario o contraseña incorrectos", 
                    "Error de Autenticación", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Permitir login con Enter
        txtPassword.addActionListener(e -> btnLogin.doClick());
        
        // Mostrar información de usuarios disponibles
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Usuarios de Prueba"));
        panelInfo.add(new JLabel("Admin: usuario='admin', contraseña='admin123'"));
        panelInfo.add(new JLabel("Editor: usuario='editor', contraseña='editor123'"));
        
        frameLogin.add(panelInfo, BorderLayout.SOUTH);
        frameLogin.setSize(400, 300);
        
        frameLogin.setVisible(true);
    }
}