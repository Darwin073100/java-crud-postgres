package com.connectiondb.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connectiondb.model.Client;
import com.connectiondb.util.DataBaseConnection;

public class ClientRepository implements Repository<Client> {

    private Connection getConnection() throws SQLException{
        return DataBaseConnection.getConnection();
    }



    private Client createClient(ResultSet res) throws SQLException {
        Client client = new Client(res.getInt("id"), res.getString("full_name"),
                            res.getString("phone_number"), res.getString("curp"));
        return client;
    }

    @Override
    public List<Client> findAll() throws SQLException{
        List<Client> clients = new ArrayList<>();
        try (Connection connection = getConnection();
            Statement stamt = connection.createStatement();
            ResultSet res = stamt.executeQuery("SELECT * FROM client;")){
            while(res.next()){
                clients.add(createClient(res));
            }
            return clients;
        }
    }

    @Override
    public Client findById(Integer id) throws SQLException {
        Client client = null;
        try(Connection connection = getConnection();
            Statement stamt = connection.createStatement();
            ResultSet res = stamt.executeQuery("SELECT * FROM client WHERE( id = "+ id +")")){
            if(res.next()){
                client = createClient(res);
            }
            return client;
        }
    }

    @Override
    public void save(Client t) throws SQLException{
        String sql = "INSERT INTO client (full_name, phone_number, curp) VALUES(?, ?, ?);";
        try (Connection connection = getConnection();
            PreparedStatement stamt = connection.prepareStatement(sql);) {
            stamt.setString(1, t.getFull_name());
            stamt.setString(2, t.getPhoneNumber());
            stamt.setString(3, t.getCurp());
            stamt.executeUpdate();
        }
    }

    @Override
    public void update(Client t) throws SQLException{
        String sql = "UPDATE client SET full_name = ?, phone_number = ?, curp = ? WHERE(id = ?);";
        try (Connection connection = getConnection();
            PreparedStatement stamt = connection.prepareStatement(sql);) {
            stamt.setString(1, t.getFull_name());
            stamt.setString(2, t.getPhoneNumber());
            stamt.setString(3, t.getCurp());
            stamt.setInt(4, t.getId());
            stamt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException{
        String query = "DELETE FROM client WHERE(id = ?);";
        try (Connection connection = getConnection();
            PreparedStatement stamt = connection.prepareStatement(query)){
            stamt.setInt(1, id);
            stamt.executeUpdate();
        } 
    }
    
}
