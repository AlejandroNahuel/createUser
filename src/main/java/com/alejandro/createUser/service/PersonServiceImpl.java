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
    public Person findPersonById(Long id) {
        return personDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPersonByVerificacionCode(Person person) {
        return personDao.findByVerificationCode(person.getVerificationCode());
    }

    @Override
    @Transactional
    public boolean verify(String verificationCode) {
        Person person = personDao.findByVerificationCode(verificationCode);
        
        if(person == null || person.isEnabled()){
            return false;
        }
        else{
            personDao.enable(person.getId());
            
            return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existEmail(String email) {
        Person person = personDao.findByEmail(email);
        
        if(person == null){
            return false;
        }
        else{
            return true;
        }
    }
    
      
}
