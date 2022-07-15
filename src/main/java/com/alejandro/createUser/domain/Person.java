package com.alejandro.createUser.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name="person")
public class Person implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Column(name="full_name")
    private String fullName;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    @Email
    private String email;
    
    @Column
    private boolean enabled;
    
    @Column(name="verification_code")
    private String verificationCode;
}
