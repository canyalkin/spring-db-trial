package db.test;

import java.util.Date;
import java.util.HashSet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.config.ApplicationConfig;
import spring.db.dao.PersonDao;
import spring.db.dao.TestDao;
import spring.jpa.model.Address;
import spring.jpa.model.Person;

public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationConfig=new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		/*TestDao testDao = applicationConfig.getBean(TestDao.class);
		String name=testDao.getName("905552552945");
		System.out.println("name:"+name);*/
		PersonDao personDao = applicationConfig.getBean(PersonDao.class);
		Person person=new Person();
		Address address=new Address();
		address.setAddress("adres");
		address.setCity("ist");
		address.setCountry("tr");
		address.setState("state");
		address.setZipPostal("124321");
		HashSet<Address> adres=new HashSet<Address>();
		adres.add(address);
		person.setAddresses(adres);
		person.setCreated(new Date());
		person.setFirstName("abc");
		person.setLastName("xyz");
		personDao.addPerson(person);
		

	}

}
