package com.connectiondb.view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import com.connectiondb.model.Client;
import com.connectiondb.repository.ClientRepository;
import com.connectiondb.repository.Repository;

public class SwingApp extends JFrame {
    private final Repository<Client> clientRepository;
    private final JTable clientTable;

    public SwingApp() {
        // Configurar la ventana
        setTitle("Gestión de Clients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 230);

        // Crear una tabla para mostrar los Clients
        clientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        // Crear botones para acciones
        JButton agregarButton = new JButton("Agregar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");

        // Configurar el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Establecer estilos para los botones
        agregarButton.setBackground(new Color(46, 204, 113));
        agregarButton.setForeground(Color.WHITE);
        agregarButton.setFocusPainted(false);

        actualizarButton.setBackground(new Color(52, 152, 219));
        actualizarButton.setForeground(Color.WHITE);
        actualizarButton.setFocusPainted(false);

        eliminarButton.setBackground(new Color(231, 76, 60));
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setFocusPainted(false);

        // Crear el objeto Repository para acceder a la base de datos
        clientRepository = new ClientRepository();

        // Cargar los Clients iniciales en la tabla
        refreshclientTable();

        // Agregar ActionListener para los botones
        agregarButton.addActionListener(e -> {
            try {
                agregarClient();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        actualizarButton.addActionListener(e -> actualizarClient());

        eliminarButton.addActionListener(e -> eliminarClient());
    }

    private void refreshclientTable() {
        // Obtener la lista actualizada de Clients desde la base de datos
        try {
            List<Client> clients = clientRepository.findAll();

            // Crear un modelo de tabla y establecer los datos de los Clients
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Id");
            model.addColumn("Full Name");
            model.addColumn("Phone Number");
            model.addColumn("CURP");

            for (Client client : clients) {
                Object[] rowData = {
                        client.getId(),
                        client.getFull_name(),
                        client.getPhoneNumber(),
                        client.getCurp()
                };
                model.addRow(rowData);
            }

            // Establecer el modelo de tabla actualizado
            clientTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los Clients de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarClient() throws SQLException {
        // Crear un formulario para agregar un Client
        JTextField fullNameField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField curpField = new JTextField();

        Object[] fields = {
                "Name:", fullNameField,
                "Phone:", phoneNumberField,
                "CURP:", curpField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Agregar Client", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Crear un nuevo objeto Client con los datos ingresados
            Client client = new Client();
            client.setFull_name(fullNameField.getText());
            client.setPhoneNumber(phoneNumberField.getText());
            client.setCurp(curpField.getText());

            // Guardar el nuevo Client en la base de datos
            clientRepository.save(client);

            // Actualizar la tabla con los Clients actualizados
            refreshclientTable();

            JOptionPane.showMessageDialog(this, "Client agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarClient() {
        // Obtener el ID del Client a actualizar
        String ClientIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del Client a actualizar:", "Actualizar Client", JOptionPane.QUESTION_MESSAGE);
        if (ClientIdStr != null) {
            try {
                int ClientId = Integer.parseInt(ClientIdStr);

                // Obtener el Client desde la base de datos
                Client Client = clientRepository.findById(ClientId);

                if (Client != null) {
                    // Crear un formulario con los datos del Client
                    JTextField fullNameField = new JTextField(Client.getFull_name());
                    JTextField phoneNumberField = new JTextField(Client.getPhoneNumber());
                    JTextField curpField = new JTextField(Client.getCurp());

                    Object[] fields = {
                            "Name:", fullNameField,
                            "Phone:", phoneNumberField,
                            "CURP:", curpField
                    };

                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar Client", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION) {
                        // Actualizar los datos del Client con los valores ingresados en el formulario
                        Client.setFull_name(fullNameField.getText());
                        Client.setPhoneNumber(phoneNumberField.getText());
                        Client.setCurp(curpField.getText());

                        // Guardar los cambios en la base de datos
                        clientRepository.save(Client);

                        // Actualizar la tabla de Clients en la interfaz
                        refreshclientTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún Client con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos del Client de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarClient() {
        // Obtener el ID del Client a eliminar
        String ClientIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del Client a eliminar:", "Eliminar Client", JOptionPane.QUESTION_MESSAGE);
        if (ClientIdStr != null) {
            try {
                int ClientId = Integer.parseInt(ClientIdStr);

                // Confirmar la eliminación del Client
                int confirmResult = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el Client?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Eliminar el Client de la base de datos
                    clientRepository.delete(ClientId);

                    // Actualizar la tabla de Clients en la interfaz
                    refreshclientTable();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID del Client", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
