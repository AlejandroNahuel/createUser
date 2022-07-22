package com.alejandro.createUser.web;

import com.alejandro.createUser.domain.Person;
import com.alejandro.createUser.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @GetMapping("all")
    public List<Person> getAll(){
        return personService.listPeople();
    }
    
    @GetMapping("find/{id}")
    private Person find(@PathVariable Long id){
        return personService.findPersonById(id);
    }
}
