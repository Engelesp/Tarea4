package gui;

import Modelos.Usuario;
import Modelos.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class ActualizarUsuarioGUI extends JFrame {
    private JTextField campoNombreUsuario, campoNombre, campoApellido, campoTelefono, campoCorreo;
    private UsuarioDAO usuarioDAO;

    public ActualizarUsuarioGUI(int idUsuario, String nombreUsuario, String nombre, String apellido, String telefono, String correo, MainGUI mainGUI) {
        this.usuarioDAO = new UsuarioDAO();

        setTitle("Actualizar Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Actualizar Usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNombreUsuario = new JTextField(nombreUsuario, 20);
        campoNombre = new JTextField(nombre, 20);
        campoApellido = new JTextField(apellido, 20);
        campoTelefono = new JTextField(telefono, 20);
        campoCorreo = new JTextField(correo, 20);

        JButton botonGuardar = new JButton("Guardar Cambios");
        JButton botonCancelar = new JButton("Cancelar");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCancelar);

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
        panel.add(Box.createVerticalStrut(20));
        panel.add(panelBotones);

        botonGuardar.addActionListener(e -> {
            String nuevoNombreUsuario = campoNombreUsuario.getText();
            String nuevoNombre = campoNombre.getText();
            String nuevoApellido = campoApellido.getText();
            String nuevoTelefono = campoTelefono.getText();
            String nuevoCorreo = campoCorreo.getText();

            if (nuevoNombreUsuario.isEmpty() || nuevoNombre.isEmpty() || nuevoApellido.isEmpty() ||
                nuevoTelefono.isEmpty() || nuevoCorreo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            Usuario usuarioActualizado = new Usuario(nuevoNombreUsuario, nuevoNombre, nuevoApellido, nuevoTelefono, nuevoCorreo, "");
            usuarioActualizado.setId(idUsuario);
            boolean actualizado = usuarioDAO.actualizarUsuario(usuarioActualizado);

            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
                mainGUI.cargarUsuariosEnTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el usuario.");
            }
        });

        botonCancelar.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }
}
