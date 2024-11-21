package gui;

import Modelos.Usuario;
import Modelos.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private UsuarioDAO usuarioDAO;

    public LoginGUI() {
        usuarioDAO = new UsuarioDAO();

        // Configuración de la ventana
        setTitle("Iniciar Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con diseño vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Títulos
        JLabel titulo = new JLabel("Inicio de Sesión");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Por favor, ingrese sus credenciales");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos de texto
        campoUsuario = new JTextField(15);
        campoContrasena = new JPasswordField(15);

        // Botones
        JButton botonLogin = new JButton("Entrar");
        JButton botonRegistro = new JButton("Registrarse");

        // Panel para botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(botonLogin);
        panelBotones.add(botonRegistro);

        // Añadir componentes al panel principal
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Usuario:"));
        panel.add(campoUsuario);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContrasena);
        panel.add(Box.createVerticalStrut(20));
        panel.add(panelBotones);

        // Acción de los botones
        botonLogin.addActionListener(e -> autenticarUsuario());
        botonRegistro.addActionListener(e -> {
            new RegistroGUI();
            dispose();
        });

        // Añadir panel a la ventana
        add(panel);
        setVisible(true);
    }

    private void autenticarUsuario() {
        String usuario = campoUsuario.getText();
        String contrasena = new String(campoContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar su usuario y contraseña.");
            return;
        }

        Usuario usuarioAutenticado = usuarioDAO.verificarUsuario(usuario, contrasena);
        if (usuarioAutenticado != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + usuarioAutenticado.getNombre());
            new MainGUI(); // Abrir la pantalla principal
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}
