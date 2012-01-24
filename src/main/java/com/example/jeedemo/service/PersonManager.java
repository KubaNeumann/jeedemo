package com.example.jeedemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Person;

@Stateless
public class PersonManager {

	@PersistenceContext
	EntityManager em;

	public void addPerson(Person person) {
		Person newPerson = new Person();

		newPerson.setFirstName(person.getFirstName());
		newPerson.setPin(person.getPin());
		newPerson.setRegistrationDate(person.getRegistrationDate());

		em.persist(newPerson);
	}

	public void deletePerson(Person person) {
		person = em.find(Person.class, person.getId());
		em.remove(person);
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons() {
		return em.createNamedQuery("person.all").getResultList();
	}

	public List<Car> getOwnedCars(Person person) {
		person = em.find(Person.class, person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Car> cars = new ArrayList<Car>(person.getCars());
		return cars;
	}

}
