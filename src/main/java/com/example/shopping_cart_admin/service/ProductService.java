package com.example.shopping_cart_admin.service;

import com.example.shopping_cart_admin.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getList();

    ProductDTO get(int id);

    void insert(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(int id);

}
