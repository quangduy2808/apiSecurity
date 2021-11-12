package com.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.news.dto.UserDTO;
import com.news.service.impl.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	UserService userService = new UserService();

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("UserDTO", new UserDTO());
        return "register";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("UserDTO", new UserDTO());
        return "login";
    }

    @PostMapping("/register")
    public String userRegistration(@ModelAttribute UserDTO UserDTO, Model model){
      
        try {
            userService.register(UserDTO);
        }catch (Exception e){
            model.addAttribute("UserDTO", UserDTO);
            model.addAttribute("error","An accout has username like you!");
            return "register";
        }
        return "redirect:/login";
    }
	
}
