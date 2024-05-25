package com.example.shopping_cart_admin.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@ToString
public class ProductDTO {

    private int id;
    private String name;
    private float price;
    private String image;
    private int subCategoryId;

}
