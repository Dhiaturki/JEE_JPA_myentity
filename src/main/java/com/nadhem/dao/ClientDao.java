package com.nadhem.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.nadhem.entities.Client;
import com.nadhem.util.JPAutil;

public class ClientDao {
	private EntityManager entityManager = JPAutil.getEntityManager("MonProjetJPA");

	public void ajouter(Client c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(c);
		tx.commit();
	}
	public void modifier(Client c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(c);
		tx.commit();
	}

	public void supprimer(Client c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		c = entityManager.merge(c); // important
		entityManager.remove(c);
		tx.commit();
	}

	public Client consulter(Client c, Object id) {
		return entityManager.find(c.getClass(), id);
	}


	public List<Client> listerTous() {
		List<Client> clients = entityManager.createQuery("select c from Client c").getResultList();

		return clients;
	}

	public List<Client> listerParNom(String nom) {
		List<Client> clients = entityManager.createQuery("select c from Client c where c.nom like :pnom").setParameter("pnom", "%" + nom + "%").getResultList();

		return clients;
	}
}