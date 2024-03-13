package com.nadhem.Test;

import com.nadhem.dao.ClientDao;
import com.nadhem.entities.Client;

public class ClientTest {
    public static void main(String[] args) {
        // Create a new client object
        Client c = new Client();
        c.setNom("Bel Hadj Nadhem");
        c.setVille("Nabeul");

        // Add the first client object to the database
        ClientDao cltDao = new ClientDao();
        cltDao.ajouter(c);

        Client c1 = new Client();
        c1.setNom("Dhia Turki");
        c1.setVille("Tunis");

        // Add the second client object to the database
        cltDao.ajouter(c1);

        // Display the list of all clients
        System.out.println("Appel de la méthode listerTous");
        for (Client cl : cltDao.listerTous())
            System.out.println(cl.getCode() + " " + cl.getNom());

        // Display the list of clients with the name containing "nadh"
        System.out.println("Appel de la méthode listerParNom");
        for (Client cl : cltDao.listerParNom("nadh"))
            System.out.println(cl.getCode() + " " + cl.getNom());
     
        Client existingClient = cltDao.consulter(new Client(), 1);
        existingClient.setNom("test"); 
        existingClient.setVille("test"); 
        cltDao.modifier(existingClient);
        System.out.println("Appel de la méthode after modif listerTous");
        for (Client cl : cltDao.listerTous())
            System.out.println(cl.getCode() + " " + cl.getNom());
       
        Client clientToDelete = cltDao.consulter(new Client(), 2);
        cltDao.supprimer(clientToDelete); // Delete the client from the database

        System.out.println("Appel de la méthode after supprimer listerTous");
        for (Client cl : cltDao.listerTous())
            System.out.println(cl.getCode() + " " + cl.getNom());
        
        Client foundClient = cltDao.consulter(new Client(), 1);
        System.out.println("Client details: " + foundClient.getNom() + " from " + foundClient.getVille());

        // You can now test other methods of the ClientDao class
    }
}
