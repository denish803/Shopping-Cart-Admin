package com.example.shopping_cart_admin.controller;

import com.example.shopping_cart_admin.service.SingUpInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sing-in")
public class SingInController {

    @Autowired
    private SingUpInService singUpInService;

    @GetMapping("/user")
    public ModelAndView singIn() {
        return new ModelAndView("singin");
    }

    @PostMapping("/user")
    public ModelAndView singIn(@RequestParam String email, @RequestParam String password) {
        boolean isValid = singUpInService.singIn(email, password);
        if (!isValid) {
            return new ModelAndView("redirect:/sing-in/user");
        }
        return new ModelAndView("redirect:/sing-up/user");
    }
}
