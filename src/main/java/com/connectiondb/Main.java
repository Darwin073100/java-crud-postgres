package com.connectiondb;

import com.connectiondb.model.Client;
import com.connectiondb.repository.ClientRepository;
import com.connectiondb.repository.Repository;

public class Main {
    public static void main(String[] args) {
        try {
            Repository<Client> repository = new ClientRepository();
            repository.findAll().forEach( c -> System.out.println(c.toString()));
            System.out.println(repository.findById(1).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}