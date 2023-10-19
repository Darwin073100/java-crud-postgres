package com.connectiondb;

import java.sql.Connection;
import java.sql.SQLException;

import com.connectiondb.model.Client;
import com.connectiondb.repository.ClientRepository;
import com.connectiondb.repository.Repository;
import com.connectiondb.util.DataBaseConnection;

public class Main {
    public static void main(String[] args) throws SQLException{
        System.out.println("---------------Listando todo------------------");
        Repository<Client> repository =  new ClientRepository();
        repository.findAll().forEach(System.out::println);
        
        System.out.println("---------------Buscando por ID----------------");
        System.out.println(repository.findById(3));
    }
}