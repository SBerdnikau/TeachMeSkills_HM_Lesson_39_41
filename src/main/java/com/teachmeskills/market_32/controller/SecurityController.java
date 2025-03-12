package com.teachmeskills.market_32.controller;

import com.teachmeskills.market_32.exception.AgeException;
import com.teachmeskills.market_32.model.dto.RegistrationRequestDto;
import com.teachmeskills.market_32.servise.SecurityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/security")
public class SecurityController {

    public SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid RegistrationRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
               if (Objects.equals(error.getCode(), "CustomAge")){
                   throw new AgeException(error.getDefaultMessage());
               }
            }
            System.out.println(bindingResult.getAllErrors());
            return "registration";
        }

        Boolean result  = securityService.registration(requestDto);
        return "user";
    }
}
