package gui;

import Modelos.Usuario;
import Modelos.UsuarioDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainGUI extends JFrame {
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private UsuarioDAO usuarioDAO;

    public MainGUI() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Gestión de Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Usuario", "Nombre", "Apellido", "Teléfono", "Correo"}, 0);
        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);

        cargarUsuariosEnTabla();

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        JButton botonActualizar = new JButton("Actualizar Usuario");
        JButton botonEliminar = new JButton("Eliminar Usuario");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");

        panelBotones.add(botonActualizar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonCerrarSesion);

        botonActualizar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila != -1) {
                int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                String nombreUsuario = modeloTabla.getValueAt(fila, 1).toString();
                String nombre = modeloTabla.getValueAt(fila, 2).toString();
                String apellido = modeloTabla.getValueAt(fila, 3).toString();
                String telefono = modeloTabla.getValueAt(fila, 4).toString();
                String correo = modeloTabla.getValueAt(fila, 5).toString();

                new ActualizarUsuarioGUI(id, nombreUsuario, nombre, apellido, telefono, correo, this);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario.");
            }
        });

        botonEliminar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila != -1) {
                int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                if (UsuarioDAO.eliminarUsuario(id)) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado.");
                    cargarUsuariosEnTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario.");
            }
        });

        botonCerrarSesion.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public void cargarUsuariosEnTabla() {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        for (Usuario u : usuarios) {
            modeloTabla.addRow(new Object[]{u.getId(), u.getNombreUsuario(), u.getNombre(), u.getApellido(), u.getTelefono(), u.getCorreo()});
        }
    }
}
