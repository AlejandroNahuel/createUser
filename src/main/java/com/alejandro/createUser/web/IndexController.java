package com.alejandro.createUser.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @PostMapping("users")
    public String userRegistered(Model model,
            @RequestParam("name") String name, 
            @RequestParam("email") String email,
            @RequestParam("username") String username){
        
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("username", username);
                
        
        
        return "registered";
    }
}
