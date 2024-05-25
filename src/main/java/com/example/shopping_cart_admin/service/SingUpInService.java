package com.example.shopping_cart_admin.service;

import com.example.shopping_cart_admin.dto.SingUpInDTO;

public interface SingUpInService {

    void insert(SingUpInDTO singUpDTO);

    boolean singIn(String email, String password);



}
