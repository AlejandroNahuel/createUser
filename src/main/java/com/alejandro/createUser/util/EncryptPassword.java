package com.alejandro.createUser.util;

import com.alejandro.createUser.domain.Person;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword{
    
    public static void encoder(Person person){
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String encodedPassword = encoder.encode(person.getPassword());
        
        person.setPassword(encodedPassword);
        
        System.out.println("encodedPassword = " + encodedPassword);
    }
}
