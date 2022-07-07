package com.alejandro.createUser.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table (name="user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String fullName;
    
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
}