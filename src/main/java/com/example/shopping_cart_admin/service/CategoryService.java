package com.example.shopping_cart_admin.service;

import com.example.shopping_cart_admin.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void insert(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    List<CategoryDTO> view();

    CategoryDTO get(int id);

    int getIdByName(String name);

    void delete (int id);



}
