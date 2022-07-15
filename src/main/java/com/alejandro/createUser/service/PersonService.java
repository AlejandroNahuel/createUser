package com.alejandro.createUser.service;

import com.alejandro.createUser.domain.Person;
import java.util.List;

public interface PersonService {
    
    public List<Person> listPeople();
    
    public void save(Person person);
    
    public void delete(Person person);
    
    public Person findPerson(Person person);
    
    public Person findPersonByVerificacionCode(String person);
    
    public boolean verify(String verificationCode);
}
