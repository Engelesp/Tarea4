package gui;

import Modelos.Usuario;
import Modelos.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class RegistroGUI extends JFrame {
    private JTextField campoNombreUsuario, campoNombre, campoApellido, campoTelefono, campoCorreo;
    private JPasswordField campoContraseña, campoConfirmarContraseña;
    private UsuarioDAO usuarioDAO;

    public RegistroGUI() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Registro de Usuario");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registro de Usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNombreUsuario = new JTextField(20);
        campoNombre = new JTextField(20);
        campoApellido = new JTextField(20);
        campoTelefono = new JTextField(20);
        campoCorreo = new JTextField(20);
        campoContraseña = new JPasswordField(20);
        campoConfirmarContraseña = new JPasswordField(20);

        JButton botonRegistrar = new JButton("Registrar");
        JButton botonVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(botonRegistrar);
        panelBotones.add(botonVolver);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Nombre de Usuario:"));
        panel.add(campoNombreUsuario);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Apellido:"));
        panel.add(campoApellido);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Teléfono:"));
        panel.add(campoTelefono);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContraseña);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Confirmar Contraseña:"));
        panel.add(campoConfirmarContraseña);
        panel.add(Box.createVerticalStrut(20));
        panel.add(panelBotones);

        botonRegistrar.addActionListener(e -> registrarUsuario());
        botonVolver.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        add(panel);
        setVisible(true);
    }

    private void registrarUsuario() {
        String nombreUsuario = campoNombreUsuario.getText();
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String telefono = campoTelefono.getText();
        String correo = campoCorreo.getText();
        String contraseña = new String(campoContraseña.getPassword());
        String confirmarContraseña = new String(campoConfirmarContraseña.getPassword());

        if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                telefono.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        Usuario usuario = new Usuario(nombreUsuario, nombre, apellido, telefono, correo, contraseña);
        if (usuarioDAO.registrarUsuario(usuario)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            new LoginGUI();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario.");
        }
    }
}
