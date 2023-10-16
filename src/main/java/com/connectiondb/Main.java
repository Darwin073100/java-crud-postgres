package com.connectiondb;

import com.connectiondb.model.Client;
import com.connectiondb.repository.ClientRepository;
import com.connectiondb.repository.Repository;

public class Main {
    public static void main(String[] args) {
        Repository<Client> repository = new ClientRepository();
        try {
            System.out.println("\n<-------------------Inserted Client--------------------->");
            Client client = new Client(0, "Marcos Magallón Hernandez", "741-134-6789", "CURP09875");
            repository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("\n<-------------------Consulted clients--------------------->");
            // Repository<Client> repository = new ClientRepository();
            repository.findAll().forEach( c -> System.out.println(c.toString()));
            System.out.println(repository.findById(1).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("\n<-------------------Updated Client--------------------->");
            Client client = new Client(1, "Marcos Garcia Hernandez", "741-134-6789", "CURP09875");
            repository.update(client);
            repository.findAll().forEach( c -> System.out.println(c.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("\n<-------------------Deleted Client--------------------->");
            repository.delete(1);
            repository.findAll().forEach( c -> System.out.println(c.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}