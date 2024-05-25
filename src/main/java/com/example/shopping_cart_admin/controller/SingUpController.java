package com.example.shopping_cart_admin.controller;

import com.example.shopping_cart_admin.dto.SingUpInDTO;
import com.example.shopping_cart_admin.service.SingUpInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sing-up")
public class SingUpController {

    @Autowired
    private SingUpInService singUpService;

    @GetMapping("/user")
    public ModelAndView singUp() {
        return new ModelAndView("/sing_up_in/singup");
    }

    @PostMapping("/user")
    public ModelAndView singUp(SingUpInDTO singUpDTO) {

        if (!singUpDTO.getPassword().equals(singUpDTO.getPassword2())) {
            return new ModelAndView("redirect:/sing-up/user");
        }
        singUpService.insert(singUpDTO);
        return new ModelAndView("redirect:/sing-in/user");
    }
}
