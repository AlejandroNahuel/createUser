package com.alejandro.createUser.dao;

import com.alejandro.createUser.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PersonDao extends JpaRepository<Person, Long>{
    
    @Query("UPDATE Person p SET p.enabled = true WHERE p.id = ?1")
    @Modifying
    public void enable(Long id);
    
    @Query("SELECT enabled FROM Person p WHERE p.verification_code = ?1")
    public Person findPersonByVerificationCode(String code);
}
