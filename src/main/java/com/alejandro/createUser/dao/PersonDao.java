package com.alejandro.createUser.dao;

import com.alejandro.createUser.domain.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PersonDao extends JpaRepository<Person, Long>{
    
    @Query("UPDATE Person p SET p.enabled = 1 WHERE p.id = ?1")
    @Modifying
    public void enable(Long id);
    
    
    @Transactional (readOnly = true)
    Person findByVerificationCode (String verificationCode);
    
    @Transactional(readOnly = true)
    Person findByEmail (String email);
}
