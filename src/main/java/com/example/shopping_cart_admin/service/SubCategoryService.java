package com.example.shopping_cart_admin.service;

import com.example.shopping_cart_admin.dto.SubCategoryDTO;

import java.util.List;

public interface SubCategoryService {

    List<SubCategoryDTO> getList();

    SubCategoryDTO get(int id);

    void insert(SubCategoryDTO subCategoryDTO);

    void update(SubCategoryDTO categoryDTO);

    void delete(int id);

}
