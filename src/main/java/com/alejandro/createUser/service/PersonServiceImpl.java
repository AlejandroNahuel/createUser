package com.alejandro.createUser.service;

import com.alejandro.createUser.dao.PersonDao;
import com.alejandro.createUser.domain.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService{
    
    @Autowired
    private PersonDao personDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Person> listPeople() {
        return (List<Person>) personDao.findAll();
    }

    @Override
    @Transactional
    public void save(Person person) {
        personDao.save(person);
    }

    @Override
    @Transactional
    public void delete(Person person) {
        personDao.delete(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPerson(Person person) {
        return personDao.findById(person.getId()).orElse(null);
    }

    @Override
    public Person findPersonByVerificacionCode(String person) {
        return personDao.findPersonByVerificationCode(person);
    }

    @Override
    public boolean verify(String verificationCode) {
        Person person = personDao.findPersonByVerificationCode(verificationCode);
        
        if(person == null || person.isEnabled()){
            return false;
        }
        else{
            personDao.enable(person.getId());
            
            return true;
        }
    }
    
      
}
