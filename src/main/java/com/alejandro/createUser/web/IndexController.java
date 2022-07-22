package com.alejandro.createUser.web;

import com.alejandro.createUser.domain.Person;
import com.alejandro.createUser.service.*;
import com.alejandro.createUser.util.EncryptPassword;
import com.alejandro.createUser.util.Utility;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private MailService mailSender;
    
    @GetMapping("/")
    public String index (){
        return "index";
    }
    
    @GetMapping("/userslist")
    public String usersList(Model model){
        var people = personService.listPeople();
        model.addAttribute("people", people);
        
        return "userslist";
    }
    
    @PostMapping("/save")
    public String save(@Valid Person person, Errors errors, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException{
        if(errors.hasErrors()){
            return "index";
        }
        
        String pageTitle;
        
        boolean emailExist = personService.existEmail(person.getEmail());
        
        if(emailExist){
            pageTitle = "email_in_use";
        }
        else{
            String verificationCode = RandomString.make(64);

            person.setVerificationCode(verificationCode);

            String siteURL = Utility.getSiteURL(request);

            person.setEnabled(false);
            
            EncryptPassword.encoder(person);

            personService.save(person);

            mailSender.sendVerificationEmail(person, siteURL);
            
            pageTitle = "registration-success";
        }
        
        
        return "register/" + pageTitle;
    }
    
    @GetMapping("/verify")
    public String verifyAccount(@Param("code")String code, Model model){
        boolean verified = personService.verify(code);
        
        String pageTitle = verified ? "Verification Succeeded" : "Verification Failed";
        model.addAttribute("pageTitle", pageTitle);
        
        return "register/" + (verified ? "verify_success" : "verify_fail");
    }
    
}
