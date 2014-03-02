package spring.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.jpa.model.Person;

@Repository

public class PersonDao {
	
	@PersistenceContext(name="pu")
	private EntityManager entityManager;
	
	@Transactional
	public void addPerson(Person person){
		//entityManager.getTransaction().begin();
		entityManager.persist(person);
		//entityManager.getTransaction().commit();
	}

}
