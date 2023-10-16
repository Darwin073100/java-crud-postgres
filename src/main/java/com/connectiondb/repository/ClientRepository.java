package com.connectiondb.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connectiondb.model.Client;
import com.connectiondb.util.DataBaseConnection;

public class ClientRepository implements Repository<Client> {

    private Connection getConnection(){
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
        try (Statement stamt = getConnection().createStatement();
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
        try(Statement stamt = getConnection().createStatement();
            ResultSet res = stamt.executeQuery("SELECT * FROM client WHERE( id = "+ id +")")){
            if(res.next()){
                client = createClient(res);
            }
            return client;
        }
    }

    @Override
    public void save(Client t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
