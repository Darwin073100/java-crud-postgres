package com.connectiondb;

import java.sql.Connection;
import java.sql.SQLException;

import com.connectiondb.model.Client;
import com.connectiondb.repository.ClientRepository;
import com.connectiondb.repository.Repository;
import com.connectiondb.util.DataBaseConnection;

public class Main {
    public static void main(String[] args) throws SQLException{
        try(Connection connection = DataBaseConnection.getConnection()){
            if(connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }

            try{
                Repository<Client> repository = new ClientRepository(connection);
                System.out.println("---------------------------Insertando Clientes----------------------");
                Client client = new Client();
                client.setFull_name("Mario Moreno Cantinflas");
                client.setPhoneNumber("741-189-0987");
                client.setCurp("NOOITNORITB234");
                repository.save(client);
                connection.commit();
            } catch(SQLException e){
                connection.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}