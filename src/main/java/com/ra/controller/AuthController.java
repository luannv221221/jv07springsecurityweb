package com.ra.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model){
        System.out.println(request.getParameter("error"));
        if(request.getParameterMap().containsKey("error")){
            model.addAttribute("error","Sai tt taif khoan");
        }
        return "login";
    }
}
