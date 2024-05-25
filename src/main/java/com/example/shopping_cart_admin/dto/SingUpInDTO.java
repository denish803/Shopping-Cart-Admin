package com.example.shopping_cart_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class SingUpInDTO {

    //private int id;
    private String username;
    private String password;
    private String password2;
    private String email;
    private String mobileNo;

}
