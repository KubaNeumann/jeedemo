package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Person;
import com.example.jeedemo.service.PersonManager;
import com.example.jeedemo.service.SellingManager;

@SessionScoped
@Named("personBean")
public class PersonFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Person person = new Person();
	private ListDataModel<Person> persons = new ListDataModel<Person>();
	
	private Person personToShow = new Person();
	private ListDataModel<Car> ownedCars = new ListDataModel<Car>();


	@Inject
	private PersonManager pm;
	
	@Inject
	private SellingManager sm;

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public ListDataModel<Person> getAllPersons() {
		persons.setWrappedData(pm.getAllPersons());
		return persons;
	}

	public ListDataModel<Car> getOwnedCars() {
		ownedCars.setWrappedData(pm.getOwnedCars(personToShow));
		return ownedCars;
	}
	
	// Actions
	public String addPerson() {
		pm.addPerson(person);
		return "showPersons";
		//return null;
	}

	public String deletePerson() {
		Person personToDelete = persons.getRowData();
		pm.deletePerson(personToDelete);
		return null;
	}
	
	public String showDetails() {
		personToShow = persons.getRowData();
		return "details";
	}
	
	public String disposeCar(){
		Car carToDispose = ownedCars.getRowData();
		sm.disposeCar(personToShow, carToDispose);
		return null;
	}
}
